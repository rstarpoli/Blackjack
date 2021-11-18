//class that creates turns for the players to place bets and take their turns
import java.util.*;

public class Game {
	
	private boolean automatic;
	private boolean printing;
	private boolean playing = true;
	private boolean autoBet;
	private int numOfPlayers;
	private Shoe shoe;
	private Dealer dealer = new Dealer();
	private Player[] player;
	
	Game(int players, boolean auto, boolean print, boolean autoB){
		int i;
		this.automatic = auto;
		this.autoBet = autoB;
		this.printing = print;
		this.numOfPlayers = players;
		this.player = new Player[numOfPlayers*25];
		InitiatePlayers();
		while(playing) {
			this.shoe = new Shoe();
			shoe.Shuffle();
			shoe.Shuffle();
			shoe.Shuffle();
			while(shoe.GetSize() > (11*(players+1))){
				PlaceBets();
				Deal();
				if(printing) {
					for(i = 0; i < players*25; i++) {
						if(player[i] != null) {
							if(player[i].HandSize() != 0)
								PrintPlayer(i, true, true);
						}
					}
				}
				if(printing)
					dealer.PrintHand(true);
				if(dealer.TotalValue() == 21)
					DetermineWinner();
				else {
					PlayerTurn();
					DealerTurn();
					System.out.println();
					DetermineWinner();
				}
				CombineBalance();
				for(i = 0; i < player.length; i++) {
					if(player[i] != null) {
						player[i].ClearHand();
						if(i != 0 && i != 25 && i != 50 && i != 75 && i != 100 && i != 125 && i != 150)
							player[i] = null;
					}
				}
				dealer.ClearHand();
				try {
					if(printing)
						Thread.sleep(5000);
					if(printing == false)
						Thread.sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(printing)
					System.out.println("\n_______________________________________________________________________________________________\n\n");
			}
		}
	}
	
	void InitiatePlayers() {
		int i, index = 0;
		for(i = 0; i < numOfPlayers; i++) {
			if(i > 0)
				index = i*25;
			player[index] = new Player();
		}
		
	}
	
	void PlaceBets() {
		int i, index = 0;
		boolean hasPlacedBet;
		for(i = 0; i < numOfPlayers; i++) {
			if(i > 0)
				index = i*25;
			hasPlacedBet = false;
			while(hasPlacedBet == false) {
				if(printing && autoBet == false)
					PrintPlayer(index, false, false);
				hasPlacedBet = player[index].PlaceBet(automatic, printing, autoBet);
			}
		}
		System.out.println();
	}
	
	void CombineBalance() {
		int i, j;
		for(i = 0; i < player.length; i++) {
			if(player[i] != null && i != 0 && i != 25 && i != 50 && i != 75 && i != 100 && i != 125 && i != 150) {
				for(j = 0; j < numOfPlayers; j++) {
					if(j*25 <= i && i < (j+1)*25) {
						player[j*25].AddBalance(player[i].GetBalance());
					}
					
				}
			}
		}
		
	}
	
	void Deal(){
		int i, j, index = 0;
		///*
		for(i = 0; i < 2; i++) {
			for(j = 0; j < numOfPlayers; j++) {
				if(j > 0)
					index = j*25;
				player[index].ReceiveCard(shoe.NextCard());
			}
			index = 0;
			dealer.ReceiveCard(shoe.NextCard());
		}
		//*/
		/*
		player[0].ReceiveCard(new Card(1, 2));
		player[0].ReceiveCard(new Card(1, 6));
		dealer.ReceiveCard(new Card(1, 2));
		dealer.ReceiveCard(new Card(1, 2));
		*/
	}
	
	void PlayerTurn(){
		boolean hitting;
		int i;
		if(printing)
			System.out.println();
		for(i = 0; i < player.length; i++) {
			hitting = true;
			if(player[i] != null) {
				while(hitting) {
					if(player[i].HandSize() == 1 && printing)
						PrintPlayer(i, false, true);
					hitting = Action(i);
				}
				if(printing)
					System.out.println();
			}
		}
	}
	
	boolean Action(int playerNum) {
		int i, value = player[playerNum].TotalValue();
		String response;
		Decision decision = new Decision();
		Scanner scanner = new Scanner(System.in);
		if(value != 21) {
			if(automatic == false){
				PrintPlayer(playerNum, false, false);
				if(player[playerNum].HasPair() == true)
					System.out.println(": would you like to hit or stand? (hit/stand/double/split) (" + player[playerNum].TotalValue() + ")");
				else if(player[playerNum].HandSize() <= 2)
					System.out.println(": would you like to hit or stand? (hit/stand/double) (" + player[playerNum].TotalValue() + ")");
				else
					System.out.println(": would you like to hit or stand? (hit/stand) (" + player[playerNum].TotalValue() + ")");
				response = scanner.nextLine();
			}
			else {
				response = decision.Decide(player[playerNum], dealer.Faceupcard());
			}
			if(response.equals("hit")) {
				player[playerNum].ReceiveCard(shoe.NextCard());
				if(printing)
					PrintPlayer(playerNum, false, true);
			}
			if(response.equals("stand"))
				return false;
			if(response.equals("double") && player[playerNum].HandSize() <= 2) {
				player[playerNum].ChangeBet(player[playerNum].GetBet());
				player[playerNum].ReceiveCard(shoe.NextCard());
				if(printing)
					PrintPlayer(playerNum, false, true);
				value = player[playerNum].TotalValue();
				if(value > 21 && printing)
					System.out.println("Player Busts.");
				return false;
			}
			if(response.equals("split") && player[playerNum].HasPair() == true) {
				for(i = 1; i < 25; i++) {
					if(player[playerNum+i] == null) {
						player[playerNum+i] = new Player();
						player[playerNum+i].ChangeBet(player[playerNum].GetBet());
						player[playerNum+i].ReceiveCard(player[playerNum].Split());
						player[playerNum+i].IsSplitting();
						return true;
					}
				}
			}
			value = player[playerNum].TotalValue();
			if(value > 21) {
				if(printing)
					System.out.println("Player Busts.");
				return false;
			}
			return true;
		}
		else 
			return false;
	}
	
	void DealerTurn() {
		int value = dealer.TotalValue();
		if(printing) {
			System.out.println();
			dealer.PrintHand(false);
		}
		if(value == 17 && dealer.TotalAces() == 1) {
			dealer.ReceiveCard(shoe.NextCard());
			value = dealer.TotalValue();
			if(printing)
				dealer.PrintHand(false);
		}
		while(value < 17) {
			dealer.ReceiveCard(shoe.NextCard());
			value = dealer.TotalValue();
			if(printing)
				dealer.PrintHand(false);
		}
		if(value > 21 && printing)
			System.out.println("Dealer Busts.");
	}
	
	void DetermineWinner() {
		int i;
		for(i = 0; i < player.length; i++) {
			if(player[i] != null) {
				if(player[i].TotalValue() == 21 && player[i].HandSize() == 2 && player[i].HasSplit() == false) {
					player[i].EndBet(3);
					PrintPlayer(i, false, false);
					System.out.println(" has Blackjack.");
				}
				else if(player[i].TotalValue() > 21) {
					player[i].EndBet(2);
					PrintPlayer(i, false, false);
					System.out.println(" has busted.");
				}
				else if(dealer.TotalValue() > 21) {
					player[i].EndBet(0);
					PrintPlayer(i, false, false);
					System.out.println(" wins.");
				}
				else {
					if(dealer.TotalValue() > player[i].TotalValue() && dealer.TotalValue() <= 21) {
						player[i].EndBet(2);
						PrintPlayer(i, false, false);
						System.out.println(" loses to the dealer.");
					}
					if(dealer.TotalValue() < player[i].TotalValue() && player[i].TotalValue() <= 21) {
						player[i].EndBet(0);
						PrintPlayer(i, false, false);
						System.out.println(" beats the dealer.");
					}
					if(dealer.TotalValue() == player[i].TotalValue() && dealer.TotalValue() <= 21) {
						player[i].EndBet(1);
						PrintPlayer(i, false, false);
						System.out.println(" push.");
					}
				}
			}
		}
	}
	
	void PrintPlayer(int currentPlayer, boolean init, boolean printHand) {
		int[] hand = GetHandNum(currentPlayer);
		System.out.print("Player " + (hand[0]+1));
		if(hand[1] != 0)
			System.out.print(", hand " + hand[1]);
		System.out.print(" (bet:" + player[currentPlayer].GetBet() + " balance:" + player[currentPlayer].GetBalance() + ")");
		if(printHand)
			player[currentPlayer].PrintHand(init);
 	}
	
	int[] GetHandNum(int playert25) {
		int[] hand = {0, 0};
		int i;
		for(i = 0; i < numOfPlayers; i++) {
			if(i*25 <= playert25 && ((i+1)*25-1) >= playert25) {
				hand[0] = i;
			}
		}
		if(playert25 == hand[0]*25 && player[hand[0]*25+1] != null) {
			hand[1] = 1;
		}
		else if(player[hand[0]*25] != null) {
			for(i = 1; i < 25; i++) {
				if(playert25 == hand[0]*25+i)
					hand[1] = i+1;
			}
		}
		return hand;
	}
	
}

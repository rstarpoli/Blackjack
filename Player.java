//class for a player's hand and takes input for their decision
import java.util.*;

public class Player {
	
	private Card[] hand = new Card[15];
	private int handSize = 0;
	private int bet;
	private int balance;
	private boolean hasSplit = false;
	
	Player(){
		
	}
	
	boolean PlaceBet(boolean automatic, boolean printing, boolean autoBet) {
		if(printing && autoBet == false)
			System.out.print(", place your bet. (Minimum $15, Maximum $1000)\n");
		//System.out.print("\n");
		if(automatic || autoBet) {
			bet = 15;
			balance = balance - 15;
			return true;
		}
		int response;
		Scanner scanner = new Scanner(System.in);
		try {
			response = scanner.nextInt();
		}catch(InputMismatchException a) {
			return false;
		}
		if(MultipleOf5(response) == true) {
			bet = response;
			balance = balance - bet;
			return true;
		}
		else
			return false;
	}
	
	boolean MultipleOf5(int value) {
		int i;
		boolean multiple = false;
		for(i = 15; i <= 1000; i=i+5) {
			if(i == value) {
				multiple = true;
				i = 1001;
			}
		}
		return multiple;
	}
	
	int GetBet() {
		return bet;
	}
	
	int GetBalance() {
		return balance;
	}
	
	int AddBalance(int add) {
		balance = balance + add;
		return balance;
	}
	
	void ChangeBet(int add) {
		bet = bet + add;
		balance = balance - add;
	}
	
	void EndBet(int i) {
		double temp = 0;
		if(i == 0) {
			balance = balance + bet*2;
		}
		if(i == 1) {
			balance = balance + bet;
		}//2 is loss
		if(i == 3) {
			temp = balance + bet*2.5;
			balance = (int)temp;
		}
		bet = 0;
	}
	
	int HandSize() {
		return handSize;
	}
	
	boolean HasPair() {
		if(handSize == 2 && hand[0].GetRank() == hand[1].GetRank())
			return true;
		else
			return false;
	}
	
	void ReceiveCard(Card card) {
		hand[handSize] = card;
		handSize++;
	}
	
	Card Split() {
		hasSplit = true;
		Card temp = hand[1];
		handSize--;
		hand[1] = null;
		return temp;
	}
	
	void IsSplitting() {
		hasSplit = true;
	}
	
	boolean HasSplit() {
		return hasSplit;
	}
	
	int TotalAces() {
		int i, aces = 0;
		for(i = 0; i < handSize; i++) {
			if(hand[i].GetRank().equals("Ace"))
				aces++;
		}
		return aces;
	}
	
	int TotalValue() {
		int i, value = 0, aces = 0;
		aces = TotalAces();
		for(i = 0; i < handSize; i++) {
			value = value + hand[i].GetValue();
		}
		if(value > 21 && aces > 0) {
			for(i = 0; i < aces; i++) {
				if(value > 21)
					value = value - 10;
			}
		}
		return value;
	}
	
	void ClearHand() {
		int i;
		hasSplit = false;
		for(i = 0; i < handSize; i++)
			hand[i] = null;
		handSize = 0;
	}
	
	Card[] Playerhand() {
		return hand;
	}
	
	void PrintHand(boolean initial) {
		int i;
		if(initial == true && TotalValue() == 21)
			System.out.print(" has Blackjack: ");
		else
			System.out.print(" has: ");
		for(i = 0; i < handSize; i++) {
			System.out.print(hand[i].GetRank() + " of " + hand[i].GetSuit());
			if(i != (handSize-1))
				System.out.print(", ");
		}
		System.out.print(" (" + TotalValue() + ")\n");
	}
	
}

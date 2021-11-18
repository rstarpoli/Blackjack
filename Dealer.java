//class for the dealer's hand and decisions
public class Dealer {
	
	private int handSize = 0;
	private Card[] hand = new Card[15];
	
	Dealer(){
		
	}
	
	void ReceiveCard(Card card) {
		hand[handSize] = card;
		handSize++;
	}
	
	int TotalValue() {
		int i, value = 0, aces = 0;
		aces = TotalAces();
		for(i = 0; i < handSize; i++)
			value = value + hand[i].GetValue();
		if(value > 21 && aces > 0) {
			for(i = 0; i < aces; i++)
				if(value > 21)
					value = value - 10;
		}
		return value;
	}
	
	int TotalAces() {
		int i, aces = 0;
		for(i = 0; i < handSize; i++) {
			if(hand[i].GetRank().equals("Ace"))
				aces++;
		}
		return aces;
	}
	
	void ClearHand() {
		int i;
		handSize = 0;
		for(i = 0; i < 10; i++)
			hand[i] = null;
	}
	
	Card Faceupcard() {
		return hand[0];
	}
	
	void PrintHand(boolean initial) {
		int i;
		if(initial) {
			if(TotalValue() == 21)
				System.out.println("Dealer has Blackjack: " + hand[0].GetRank() + " of " + hand[0].GetSuit() 
									+ " and " + hand[1].GetRank() + " of " + hand[1].GetSuit());
			else {
				System.out.print("Dealer is showing: " + hand[0].GetRank() + " of " + hand[0].GetSuit());
				System.out.print(" (" + hand[0].GetValue() + ")\n");
			}
		}
		else {
		System.out.print("Dealer has: ");
		for(i = 0; i < handSize; i++) {
			System.out.print(hand[i].GetRank() + " of " + hand[i].GetSuit());
			if(i != (handSize-1))
				System.out.print(", ");
			}
		System.out.print(" (" + TotalValue() + ")\n");
		}
	}

}

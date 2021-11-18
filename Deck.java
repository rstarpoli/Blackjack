//class to create a deck of 52 cards
public class Deck{
	
	private int size = 52;
	private Card[] deck = new Card[size];
	
	Deck() {
		int i, j, index = 0;
		for(i = 0; i < 4; i++) {
			for(j = 0; j < 13; j++) {
				Card card = new Card(i, j);
				deck[index] = card;
				index++;
			}
		}
		
	}
	
	Card GetCard(int n) {
		return deck[n];
	}
	
	void PrintDeck(){
		int i;
		for(i = 0; i < size; i++) {
			System.out.println(deck[i].GetRank() + " of " + deck[i].GetSuit());
		}
		
	}

}

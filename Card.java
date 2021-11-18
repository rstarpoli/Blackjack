//class for a single card with a rank and suit
public class Card {
	
	private String[] suits = {"Diamonds", "Hearts", "Spades", "Clubs"};
	private String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
	private String suit;
	private String rank;
	
	Card(int s, int n){
		this.suit = suits[s];
		this.rank = ranks[n];
	}
	
	public String GetSuit(){
		return this.suit;
	}
	
	public String GetRank() {
		return this.rank;
	}
	
	public int GetValue() {
		int value;
		if(this.rank == "Ace")
			value = 11;
		else if(this.rank == "Jack")
			value = 10;
		else if(this.rank == "Queen")
			value = 10;
		else if(this.rank == "King")
			value = 10;
		else
			value = Integer.parseInt(this.rank);
		return value;
	}
	
}

//class that creates a shoe of 8 decks
import java.util.*;

public class Shoe {
	
	private int size = 416;
	private Card[] shoe = new Card[size];
	
	Shoe(){
		int i, j, index = 0;
		Deck deck = new Deck();
		for(i = 0; i < 8; i++) {
			for(j = 0; j < 52; j++) {
				shoe[index] = deck.GetCard(j);
				index++;
			}
		}
		
	}
	
	Card NextCard() {
		size--;
		return shoe[size];
	}
	
	int GetSize() {
		return size;
	}
	
	void Shuffle() {
		Card temp;
		int index;
	    Random random = new Random();
	    for (int i = shoe.length - 1; i > 0; i--)
	    {
	        index = random.nextInt(i + 1);
	        temp = shoe[index];
	        shoe[index] = shoe[i];
	        shoe[i] = temp;
	    }
	}
	
	void PrintShoe(){
		int i;
		for(i = 0; i < size; i++) {
			System.out.println(shoe[i].GetRank() + " of " + shoe[i].GetSuit());
		}
		
	}

}

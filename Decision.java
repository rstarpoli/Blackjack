//class that takes the input of the hand and the card the dealer is showing and returns the decision of hit, stand, double, or split
public class Decision {
	
	private String[] decision = {"hit", "stand", "double", "split"}; //0=hit, 1=stand, 2=double, 3=split
	private String[][] array = {
			{	""	,	""	,	""	,	""	,	""	,	""	,	""	,	""	,	""	,	""	,	""	},
			{	""	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	},
			{	""	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	},
			{	""	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	},
			{	""	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	},
			{	""	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	},
			{	""	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	},
			{	""	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	},
			{	""	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	},
			{	""	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	},
			{	""	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	},
			{	""	,	"hit"	,	"hit"	,	"stand"	,	"stand"	,	"stand"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	},
			{	""	,	"stand"	,	"stand"	,	"stand"	,	"stand"	,	"stand"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	},
			{	""	,	"stand"	,	"stand"	,	"stand"	,	"stand"	,	"stand"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	},
			{	""	,	"stand"	,	"stand"	,	"stand"	,	"stand"	,	"stand"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	},
			{	""	,	"stand"	,	"stand"	,	"stand"	,	"stand"	,	"stand"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	,	"hit"	}
								};
	
	public Decision() {
		
	}
	
	public String Decide(Player player, Card dealerHand) {
		Card[] hand = player.Playerhand();
		int ace, second;
		if(player.HandSize() == 2) {
			if((hand[0].GetValue() == 11|| hand[0].GetValue() == 8) && player.HasPair())
				return decision[3];
			if(hand[0].GetValue() == 10 && player.HasPair())
				return decision[1];
			if(hand[0].GetValue() == 9 && player.HasPair()) {
				if(dealerHand.GetValue() == 7 || dealerHand.GetValue() == 10 || dealerHand.GetValue() == 11)
					return decision[1];
				else
					return decision[3];
			}
			if(hand[0].GetValue() == 7 && player.HasPair()) {
				if(dealerHand.GetValue() >= 8)
					return decision[0];
				else
					return decision[3];
			}
			if(hand[0].GetValue() == 6 && player.HasPair()) {
				if(dealerHand.GetValue() >= 7)
					return decision[0];
				else
					return decision[3];
			}
			if(hand[0].GetValue() == 5 && player.HasPair()) {
				if(dealerHand.GetValue() >= 10)
					return decision[0];
				else
					return decision[2];
			}
			if(hand[0].GetValue() == 4 && player.HasPair()) {
				if(dealerHand.GetValue() == 5 || dealerHand.GetValue() == 6)
					return decision[3];
				else
					return decision[0];
			}
			if((hand[0].GetValue() == 3 || hand[0].GetValue() == 2) && player.HasPair()) {
				if(dealerHand.GetValue() >= 8)
					return decision[0];
				else
					return decision[3];
			}
			
			if(player.TotalAces() == 1) {
				if(hand[0].GetRank() == "Ace") {
					ace = 0;
					second = 1;
				}
				else {
					ace = 1;
					second = 0;
				}
				if(hand[second].GetValue() >= 8)
					return decision[1];
				
				if(hand[second].GetValue() == 7) {
					if(dealerHand.GetValue() >= 9)
						return decision[0];
					if(dealerHand.GetValue() == 2 || dealerHand.GetValue() == 7 || dealerHand.GetValue() == 8)
						return decision[1];
					if(dealerHand.GetValue() >= 3 && dealerHand.GetValue() <= 6)
						return decision[2];
				}
				if(hand[second].GetValue() == 6) {
					if(dealerHand.GetValue() >= 3 && dealerHand.GetValue() <= 6)
						return decision[2];
					else
						return decision[0];
				}
				if(hand[second].GetValue() == 5 || hand[second].GetValue() == 4) {
					if(dealerHand.GetValue() >= 4 && dealerHand.GetValue() <= 6)
						return decision[2];
					else
						return decision[0];
				}
				if(hand[second].GetValue() == 3 || hand[second].GetValue() == 2) {
					if(dealerHand.GetValue() >= 5 && dealerHand.GetValue() <= 6)
						return decision[2];
					else
						return decision[0];
				}
				
				
			}
			
		}
		if(player.TotalValue() >= 17)
			return decision[1];
		//player.PrintHand(false);
		if(player.HandSize() <= 2) {
			if(player.TotalValue() == 9 && dealerHand.GetValue() >= 3 && dealerHand.GetValue() <= 6)
				return decision[2];
			if(player.TotalValue() == 10 && dealerHand.GetValue() <= 9)
				return decision[2];
			if(player.TotalValue() == 11 && dealerHand.GetValue() <= 10)
				return decision[2];
		}
		return array[player.TotalValue()-1][dealerHand.GetValue()-1];
	}

}

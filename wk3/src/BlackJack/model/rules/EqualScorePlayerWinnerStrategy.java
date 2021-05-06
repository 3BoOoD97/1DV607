package BlackJack.model.rules;

import BlackJack.model.Dealer;
import BlackJack.model.Player;

class EqualScorePlayerWinnerStrategy implements IWinnerStrategy {

	public boolean IsDealerWinner(Player a_player, Dealer a_dealer) {
		
		if (a_player.CalcScore() > a_player.getMaxScore()){
			return true;
		}
		else if (a_dealer.CalcScore() > a_dealer.getMaxScore()){
			return false;
		}
		
		if(a_player.CalcScore() == a_dealer.CalcScore()){
			
			return false;
		}
		
		return a_dealer.CalcScore() > a_player.CalcScore();
		
	}
	
}


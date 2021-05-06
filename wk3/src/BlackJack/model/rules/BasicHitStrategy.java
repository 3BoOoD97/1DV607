package BlackJack.model.rules;

import BlackJack.model.Player;

class BasicHitStrategy implements IHitStrategy {
	
    public boolean DoHit(Player a_dealer) {
      return a_dealer.CalcScore() < g_hitLimit;  
    }
}
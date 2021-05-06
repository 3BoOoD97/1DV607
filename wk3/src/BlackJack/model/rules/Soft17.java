package BlackJack.model.rules;

import BlackJack.model.Card;
import BlackJack.model.Player;

class Soft17 implements IHitStrategy {

	private int count;

	@Override
	public boolean DoHit(Player a_dealer) {
		if (a_dealer.GetHand() != null) {

			for (Card c : a_dealer.GetHand()) {
				if (c.GetValue() == Card.Value.Ace && count <= 1) {
					count++;
					return true;
				}

				else if (c.GetValue().ordinal() <= 4 && count == 1) {
					return a_dealer.CalcScore() <= 21;
				}
				count++;
			}
		}
		return a_dealer.CalcScore() < g_hitLimit;

	}
}

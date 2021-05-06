package BlackJack.controller;

import BlackJack.model.Card.Value;
import BlackJack.model.IObserver;
import BlackJack.view.IView;
import BlackJack.view.GameOptions;

public class DisplayController implements IObserver{
	
	private BlackJack.view.IView a_view;
	private BlackJack.model.Game a_game;
	
	
	
	public DisplayController(BlackJack.model.Game a_game, BlackJack.view.IView a_view){
		this.a_game = a_game;
		this.a_view = a_view;
	    a_game.addSubscriber(this);
	}
	
	public void startGame() {
		a_view.DisplayWelcomeMessage();
	}

	
	
	public boolean Play(BlackJack.model.Game a_game) {
		if (a_game.IsGameOver()) {
			a_view.DisplayGameOver(a_game.IsDealerWinner());
			a_view.DisplayWelcomeMessage();
		}
		if (a_view.GetInput() == GameOptions.Play) {
			a_game.NewGame();
		} else if (a_view.GetInput() == GameOptions.Hit) {
			a_game.Hit();
		} else if (a_view.GetInput() == GameOptions.Stand) {
			a_game.Stand();
		}
		return !(a_view.GetInput() == GameOptions.Quit);
	}

	@Override
	public void dealtCard() {
		
		try {
			Thread.sleep(1000);
			a_view.DisplayDealerHand(a_game.GetDealerHand(), a_game.GetDealerScore());
			a_view.DisplayPlayerHand(a_game.GetPlayerHand(), a_game.GetPlayerScore());
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	
	}
}
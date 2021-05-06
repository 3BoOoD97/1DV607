package BlackJack.view;

public class SimpleView implements IView 
{
	public String choiceValue = "Quit the game";
	
	@Override
	public void DisplayWelcomeMessage() {
		System.out.println("Hello Black Jack World");
		System.out.println("Type '1' to Play, '2' to Hit, '3' to Stand or '4' to Quit\n");
		GetInput();
	}
	
	
// Get input from the user
	public GameOptions GetInput() {
		try {
			int c = System.in.read();
			while (c == '\r' || c == '\n') {
				c = System.in.read();
			}

			if (c == '1') {
				return GameOptions.Play;
			} else if (c == '2') {
				return(GameOptions.Hit);
			} else if (c == '3') {
				return(GameOptions.Stand);
			}else if (c == '4') {
				return(GameOptions.Quit);
			}
			else 
			{
				return GetInput();
			}

		} catch (java.io.IOException e) {
			System.out.println("" + e);
			return GetInput();
		}
	}

	@Override
	public void DisplayCard(BlackJack.model.Card a_card) {
		System.out.println("" + a_card.GetValue() + " of " + a_card.GetColor());
	}

	@Override
	public void DisplayPlayerHand(Iterable<BlackJack.model.Card> a_hand, int a_score) {
		DisplayHand("Player", a_hand, a_score);
		GetInput();
	}
	
	@Override
	public void DisplayDealerHand(Iterable<BlackJack.model.Card> a_hand, int a_score) {
		DisplayHand("Dealer", a_hand, a_score);
	}

        private void DisplayHand(String a_name, Iterable<BlackJack.model.Card> a_hand, int a_score)
        {
            System.out.println(a_name + " Has: ");
            for(BlackJack.model.Card c : a_hand)
            {
                DisplayCard(c);
            }
            System.out.println("Score: " + a_score);
            System.out.println("");
        }

        public void DisplayGameOver(boolean a_dealerIsWinner)
        {
            System.out.println("GameOver ");
            if (a_dealerIsWinner)
            {
                System.out.println("Dealer Won! X_X");
            }
            else
            {
                System.out.println("You Won! ^_^");
            }
            
        }
    }

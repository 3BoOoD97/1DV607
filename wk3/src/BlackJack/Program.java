package BlackJack;

import BlackJack.model.Game;
import BlackJack.view.*;
import BlackJack.controller.*;

public class Program
{

  public static void main(String[] a_args)
  {
	  IView v = new SimpleView(); 
      Game g = new Game();
   
    
    DisplayController displayController = new DisplayController(g, v);
    displayController.startGame();
    while (displayController.Play(g));
  }
}
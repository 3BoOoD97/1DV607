package BlackJack.model;

import java.util.List;
import java.util.LinkedList;

public class Player {

  private List<Card> m_hand;
  protected final int g_maxScore = 21;
private IObserver listener ;


public void setListener(IObserver listener) {
	this.listener = listener;
}

  public Player()
  {
    m_hand = new LinkedList<Card>();
  }
  
  
  public void DealCard(Card a_addToHand)
  {
    m_hand.add(a_addToHand);
    if (listener != null) {
    	listener.dealtCard();
    }
  }
  
  public Iterable<Card> GetHand()
  {
    return m_hand;
  }
  
  public void ClearHand()
  {
    m_hand.clear();
  }
  
  public void ShowHand()
  {
    for(Card c : m_hand)
    {
      c.Show(true);
    }
  }
  
  public int CalcScore()
  {
   
    int cardScores[] = {
        2, 3, 4, 5, 6, 7, 8, 9, 10, 10 ,10 ,10, 11
    };
    assert (cardScores.length == Card.Value.Count.ordinal()) : "Card Scores array size does not match number of card values";
  
    
    int score = 0;

    for(Card c : GetHand()) {
        if (c.GetValue() != Card.Value.Hidden)
        {
            score += cardScores[c.GetValue().ordinal()];
        }
    }

    if (score > g_maxScore)
    {
        for(Card c : GetHand())
        {
            if (c.GetValue() == Card.Value.Ace && score > g_maxScore
            		&& c.GetValue().ordinal() <= 4)
            {
                score -= 10;
            }
        }
    }

    return score;
  }
  
  public int getMaxScore(){
	  
	  return g_maxScore;
  }
  
}




package BlackJack.model.rules;

public class RulesFactory {

  public IHitStrategy GetHitRule() {
    return new Soft17();
  }

  public INewGameStrategy GetNewGameRule() {
    return new InternationalNewGameStrategy();
  }
  
  public IWinnerStrategy GetWinnerRule(){
	  return new EqualScoreDealerWinnerStrategy();
  }
  
}
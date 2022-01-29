public class MoneyMarket extends EconomicModel
{
  public MoneyMarket()
  {
    super();
    
    setAxisLabelX("QUANTITY OF $");
    setAxisLabelY("NOMINAL INTEREST RATE");
  }
  
  @Override 
  public void raiseDemand()
  {
    this.y += 10;
  } 
  // Overrides BC. the money supply is independant of the demand 
  
  @Override
  public void lowerDemand()
  {
    this.y -= 10;
  }
} /** Represents the money supply curves.**/

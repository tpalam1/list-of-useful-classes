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
  
  @Override
  public String getStatus()
  {
    double change_in_NIR = this.y - 100;
    
    if(change_in_NIR == 0)
    {
      return "Running as usual.";
    }
    else if(change_in_NIR > 0)
    {
      return "Experiencing expansionary policy: nominal IR is higher than normal.";
    }
    else
    {
      return "Experiencing contractionary policy: nominal IR is lower than normal.";
    }
  }
} /** Represents the money supply curves.**/

public class AggregateEconomy extends EconomicModel
{
  public AggregateEconomy()
  {
    super();
    
    setAxisLabelX("GDP");
    setAxisLabelY("PRICE LEVEL");
  }
  
  @Override
  public String getStatus()
  {
    switch(getStatusX())
    {
      case "Positive change.":
        return "Inflationary gap.";
      case "Negative change.":
        return "Recessionary gap.";
      case "Negligible change.":
        return "Running as normal.";
      default:
        return "Unsure, check AggregateEconomy.getStatus().";
    }
  }
} /** Represents the AD-AS curves. **/

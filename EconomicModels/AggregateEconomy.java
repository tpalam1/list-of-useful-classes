public class AggregateEconomy extends EconomicModel
{
  public AggregateEconomy()
  {
    super();
    
    setAxisLabelX("GDP");
    setAxisLabelY("PRICE LEVEL");
  }
  
  public String getStatus() throws Exception
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
        throw new Exception(
          "AggregateEconomy.getStatus(): switch statement failed to identify " + 
          "current status of economy.");
    }
  }
} /** Represents the AD-AS curves. **/

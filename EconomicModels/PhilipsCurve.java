public class PhilipsCurve extends EconomicModel
{
  public PhilipsCurve()
  {
    super();
    
    setAxisLabelX("UNEMPLOYMENT");
    setAxisLabelY("INFLATION");
  }
  /**
   * Creates a PhilipsCurve graph,
   * where the equilibrium point is 
   * where the Long Run PhilipsCurve ("LRPC")
   * meets the Short Run PhilipsCurve ("SRPC").
   **/
   
   @Override
   public void raiseDemand()
   {
     this.y += 10;
   } // raises the SRPC curve 
   
   @Override
   public void lowerDemand()
   {
     this.y -= 10;
   } // lowers the SRPC curve 
   
   /**
    * When Aggregate Demand shifts,
    * there will be inverse movement along the PC;
    * however, when Aggregate Supply shifts,
    * the entier SRPC will shift in the opposite direction.
    **/ 
   
   @Override
   public String getStatus()
   {
     switch(getStatusY())
     {
       case "Negative change.":
         return "Currently in a recession: high unemployment, low inflation.";
       case "Positive change.":
         return "Currently in an inflationary gap: low unemployment, high inflation.";
       case "Negligible change.":
        return "Running at full employment.";
       default:
        return "Unsure, check PhilipsCurve.getStatus().";
     }
   }
}

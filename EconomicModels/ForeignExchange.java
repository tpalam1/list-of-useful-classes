public class ForeignExchange extends EconomicModel
{
  String currency_A;
  String currency_B;
  
  public ForeignExchange()
  {
    super();
    
    currency_A = "N/A";
    currency_B = "N/A";
  }
  
  public ForeignExchange(String a, String b)
  {
    super();
    
    currency_A = a;
    currency_B = b;
    
    setAxisLabels();
  }
  
  public void setAxisLabels()
  {
    setAxisLabelX("QUANTITY OF " + currency_A);
    setAxisLabelY("PRICE OF " + currency_A + " IN " + currency_B);
  }
} /** Represents the exchange rate between two currencies. **/

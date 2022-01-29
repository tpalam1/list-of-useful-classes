public class EconomicModel
{
  double x;
  double y; 
  
  String x_axis;
  String y_axis;
  // CLASS VARS.
  
  public EconomicModel()
  {
    x = 100;
    y = 100;
    
    x_axis = "X_AXIS";
    y_axis = "Y_AXIS";
  }
  
  public EconomicModel(String x_a, String y_a)
  {
    x = 100;
    y = 100;
    
    x_axis = x_a;
    y_axis = y_a;
  }
  // CONSTRUCTORS
  
  public void raiseDemand()
  {
    x += 10;
    y += 10;
  }
  
  public void lowerDemand()
  {
    x -= 10;
    y -= 10;
  }
  
  public void raiseSupply()
  {
    x += 10;
    y -= 10;
  }
  
  public void lowerSupply()
  {
    x -= 10;
    y += 10;
  }
  
  public void setAxisLabelX(String s)
  {
    x_axis = s;
  }
  
  public void setAxisLabelY(String s)
  {
    y_axis = s;
  }
  // MUTATORS 
  
  public static double getXGiven(double specific_y)
  {
    return (200 - specific_y);
  }
  
  public static double getYGiven(double specific_x)
  {
    return (200 - specific_x);
  }
  
  public double getX()
  {
    return x;
  }
  
  public double getY()
  {
    return y;
  }
  
  public String getStatusX()
  {
    double change_in_x = x - 100;
       
    if(change_in_x == 0)
    {
      return "Negligible change.";
    }
    else if(change_in_x > 0)
    {
      return "Positive change.";
    }
    else 
    {
      return "Negative change.";
    }
  }
  
  public String getStatusY()
  {
    double change_in_y = y - 100;
    
    if(change_in_y == 0)
    {
      return "Negligible change.";
    }
    else if(change_in_y > 0)
    {
      return "Positive change.";
    }
    else 
    {
      return "Negative change.";
    }
  }
  
  public String getStatus()
  {
    String s = "";
    
    s += "(" + x_axis + " = " + getStatusX() + ", ";
    s +=       y_axis + " = " + getStatusY() + ")";
    
    return s; 
  }
  
  public String toString()
  {
    String s = "";
    
    s += "(" + x_axis + " = " + x + ", ";
    s +=       y_axis + " = " + y + ")";
    
    return s; 
  }
  // ACCESSORS 
  
} /** Superclass for dealing with economic models.**/


import java.util.ArrayList;

public class InfoDistribution
{
  ArrayList<Double> x = new ArrayList<Double>();
  ArrayList<Double> y = new ArrayList<Double>();
  
  String mode; 
	
	public InfoDistribution()
	{
	  this("RANDOM");
	}
	
	public InfoDistribution(String m)
	{
	  mode = m;
	  
	  fillX();
	  fillY();
	  
	  normalizeY();
	}
	
  public static double getRandDouble(double min, double max)
  {
    return Math.random() * (max - min + 1) + min;
  } // returns a random double, [min, max]
	
	public void fillX()
	{
    x.add(.0);
    x.add(0.5);
    x.add(1.0);
	}
	
	public void fillY()
	{
	  double y1 = 0;
	  double y2 = 0;
	  double y3 = 0;
	  // init. y-vars.
	  
	  switch(mode)
	  {
	    case "RANDNORMAL":
	      y1 = getRandDouble(0, 100);
	      y3 = getRandDouble(0, y1);
	      y2 = getRandDouble(y1, y3);
	      break;
	    case "RANDCUMUL":
	      y1 = getRandDouble(0, 100);
	      y2 = getRandDouble(0, y1);
	      y3 = getRandDouble(0, y3);
	      break;
	    case "RANDOM":
      default:
        y1 = getRandDouble(0, 100);
        y2 = getRandDouble(0, 100);
        y3 = getRandDouble(0, 100);
        break;
	  }
	  
    y.add(y1);
    y.add(y2);
    y.add(y3);
	}
	
	public double getTotalArea()
	{
    ArrayList<Double> f1 = getFirstRegression();
    ArrayList<Double> f2 = getSecondRegression();
    
    double m1 = f1.get(0);
	  double b1 = f1.get(1);
	  
	  double m2 = f2.get(0);
	  double b2 = f2.get(1);
    
    double a1 = m1 * (0.5) * (0.5) / 2 + b1 * 0.5;
    double a2 = m2 / 2 + b2 - (m2 * (0.5) * (0.5) / 2 + b2 * 0.5);
    double a_total = a1 + a2; 
    // Finding the total area; used to find the scaling coef.
    
    return a_total;
	}
	
	public ArrayList<Double> getFirstRegression()
	{
	  ArrayList<Double> output = new ArrayList<Double>();
	  
    double y_AB = y.get(1) - y.get(0);
    double x_AB = x.get(1) - x.get(0); // should be 0.5 by definition?
    double m1 = y_AB / x_AB;
    
    double b1 = y.get(1) - m1 * 0.5;
    
    output.add(m1);
    output.add(b1);
    
    return output;
	} // returns y = mx + b for {A, B}
	
	public ArrayList<Double> getSecondRegression()
	{
	  ArrayList<Double> output = new ArrayList<Double>();
	  
    double y_BC = y.get(2) - y.get(1);
    double x_BC = x.get(2) - x.get(1); 
    double m2 = y_BC / x_BC;
    
    double b2 = y.get(1) - m2 * 0.5;
    
    output.add(m2);
    output.add(b2);
    
    return output;
	} // returns y = mx + b for {B, C}
	
	public void normalizeY()
	{
    double k = 1 / getTotalArea();
    
    for(int i = 0; i < y.size(); i++)
    {
      double curr_val = y.get(i);
      double scaled_val = k * curr_val;
      
      y.set(i, scaled_val);
    }
	} // normalizes all Y such the total area = 1.
	
	public double getAreaTo(double x_max)
	{
	  boolean isLarge = (x_max > 0.5 ? true: false);
	  // checks whether or not we will need to use two separate piecewise integrals 
	  
	  double cumul_area = 0; // init. cumul_area
	  
	  ArrayList<Double> f1 = getFirstRegression();
	  ArrayList<Double> f2 = getSecondRegression();
	  
	  double m1 = f1.get(0);
	  double b1 = f1.get(1);
	  
	  double m2 = f2.get(0);
	  double b2 = f2.get(1);
	  
	  if(isLarge)
	  {
	    cumul_area += m1 * (0.5) * (0.5) / 2 + b1 * (0.5);
	    
	    cumul_area += m2 * (x_max) * (x_max) / 2 + b2 * (x_max);
	    cumul_area -= m2 * (0.5) * (0.5) / 2 + b2 * (0.5); 
	  }
	  else 
	  {
	    cumul_area += m1 * (x_max) * (x_max) / 2 + b1 * (x_max);
	  }
	  
	  return cumul_area;
	} // returns the area from x = [0, x_max]
	
	public double getAreaBetween(double x_min, double x_max)
	{
	  double output = getAreaTo(x_max) - getAreaTo(x_min);
	  return output;
	}
	
	public String toString()
	{
	  return y.toString();
	}
}

import java.util.ArrayList;

public class Main
{
	public static void main(String[] args) 
	{
	  double area_threshold = 0.50;
	  
	  for(int i = 0; i < 1000; i++)
	  {
  	  InfoDistribution id = new InfoDistribution("RANDCUMUL");
  	  ArrayList<Double> arr = getOptimalStrategy(id, area_threshold);
  	  
  	  System.out.println(arr.get(1) + "\t" + arr.get(2)
  	  + "\t" + arr.get(3) + "\t" + arr.get(4) + "\t" + arr.get(5));
	  }
	}
	
	public static ArrayList<Double> getOptimalStrategy(InfoDistribution id, double area_threshold)
	{
	  ArrayList<Double> output = new ArrayList<Double>();
	  
	  double min_read_i = 1e3; // we are trying to minimize this 
	  double best_start = 0;
	  double best_end = 0;
    
    for(double i = 0; i <= 1; i += 0.001)
    {
      double i2 = getCoresspondingX(id, i, area_threshold);
      double total_i = i + i2; // current total amount to have been read 
      
      // System.out.println(fix(i) + "\t" + fix(i2) + "\t" + fix((i + i2)));
      
      if(total_i < min_read_i)
      {
        min_read_i = total_i;
        best_start = i;
        best_end = i2;
      }
    }
    
    output.add(fix(min_read_i));
    output.add(fix(best_start));
    output.add(fix(best_end));
    // stores the results 
    
	  output.add(fix(id.y.get(0)));
  	output.add(fix(id.y.get(1)));
  	output.add(fix(id.y.get(2)));
  	// stores the coefficients used to achieve the results 
    
    return output;
	} 
	/**
	 * Returns (total_read, x1, x2), such (0, x1)U(1-x2, 1) = area_threshold,
	 * and total_read is minimized. Last 3 vars represent coefficients used to achieve the results.
	 **/
	
	public static double getCoresspondingX(InfoDistribution id, double x1, double area_threshold)
	{
    double area_1 = id.getAreaBetween(0, x1); // area from (0, x1)
    
    for(double i = 0; i <= (1-x1); i += 0.01)
    {
      double area_2 = id.getAreaBetween(1-i, 1);
      
      double area_total = area_1 + area_2;
      
      if(area_total > area_threshold)
      {
        return i;
      }
    }
    
    return -1; // bad output 
	} // finds X2 SUCH (0, x1)U(1-x2, 1) = area_threshold.
	
  public static double getRandDouble(double min, double max)
  {
    return Math.random() * (max - min) + min;
  } // returns a random double, [min, max)
  
  public static double fix(double d)
  {
    d = Math.floor(d * 1000) / 1000;
    
    return d;
  } // sets a given Double to 3 decimal precision 
  
  public static void fix(ArrayList<Double> arr)
  {
    for(int i = 0; i < arr.size(); i++)
    {
      double curr_val = arr.get(i);
      arr.set(i, fix(curr_val));
    }
  } // fixes all values in an ArrayList to 3 decimal precision 
} /** Driver class that finds the optimal (first, last) reading strategy of a book. **/


class InfoDistribution
{
  ArrayList<Double> x = new ArrayList<Double>();
  ArrayList<Double> y = new ArrayList<Double>();
  
  String mode = ""; 
	
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
    
    return Math.random() * (max - min) + min;
  } // returns a random double, [min, max)
	
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
	      y2 = getRandDouble(0, y3);
	      break;
	    case "RANDCUMUL":
	      y1 = getRandDouble(0, 100);
	      y2 = getRandDouble(0, y1);
	      y3 = getRandDouble(0, y2);
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

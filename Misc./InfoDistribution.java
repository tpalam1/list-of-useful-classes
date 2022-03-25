import java.util.ArrayList;
import java.util.Collections;

public class Main
{
	public static void main(String[] args) 
	{
	  ArrayList<Double> arr = new ArrayList<Double>();
	  ArrayList<Double> arr2 = new ArrayList<Double>();
	  ArrayList<Double> arrTotal = new ArrayList<Double>();
	  
	  for(int i = 0; i < 1000; i++)
	  {
	    InfoDistribution id = new InfoDistribution();
	    
	    double start_x = id.getAsymmetricXValOf(0.8).get(0);
	    double end_x = id.getAsymmetricXValOf(0.8).get(1);
	    double min_pct_read = id.getAsymmetricXValOf(0.8).get(2);
	    
	    arr.add(id.fix(start_x));
	    arr2.add(id.fix(end_x));
	    arrTotal.add(id.fix(min_pct_read));
	  }
	  
	  Collections.sort(arr);
	  Collections.sort(arr2);
	  Collections.sort(arrTotal);
	  
	  System.out.println("Data points for amount needed to have read from [0, X1]: " + arr);
	  System.out.println();
	  System.out.println("Data points for amount needed to have read from [X2, 1]: " + arr2);
	  System.out.println();
	  System.out.println("Total amount read (X1 + X2): " + arrTotal);
	}
}

/**
 * Driver class for the InfoDistribution class. 
 * Takes in random InfoDistributions,
 * finds the optimal % read from the front 
 * + % read from the back of the distribution
 * SUCH satisfies a total of 80% total understanding minimum,
 * AND minimizes the amount needed to have read.
 **/ 


class InfoDistribution 
{
  ArrayList<Double> x = new ArrayList<Double>(); // stores the time progression
  ArrayList<Double> y = new ArrayList<Double>(); // stores the info density 

  public InfoDistribution()
  {
    fillArrX();
    
    do
    {
      y.clear();
      
      fillArrY();
    } while(!isStrictlyPositive());
    // continue getting random information distributions until they are strictly positive 
  }
  
  public static double getRandDouble(double min, double max)
  {
    return Math.random() * (max - min + 1) + min;
  } // returns a random double, [min, max]
  
  public static double fix(double d)
  {
    d = Math.floor(d * 1000) / 1000;
    
    return d;
  } // sets a given Double to 3 decimal precision 
  
  public static double getValAt(double a, double b, double c, double x)
  {
    double output = 0;
    
    output += c; 
    output += b * x;
    output += a * x * x;
    
    return output;
  } // returns f(x_i), where f(x) = ax2 + bx + c.
  
  public static double getAreaAt(double a, double b, double c, double x)
  {
    double output = 0;
    
    output += c * x; 
    output += b * x * x * 0.5;
    output += a * (x * x * x) / 3;
    
    return output;
  } // returns F(x_i), where F(x) = ax3 / 3 + bx2 /2 + cx.
  
  public static double getMinimizingX(double a, double b)
  {
    return -b / (2 * a);
  } // returns the x-value that minimizes the quadratic 
  
  public static double getMinimum(double a, double b, double c)
  {
    double min_x = getMinimizingX(a, b);
    
    return getValAt(a, b, c, min_x);
  } // returns the minimum y-value 
  
  public static boolean isStrictlyPositive(double a, double b, double c)
  {
    if(getMinimum(a, b, c) > 0)
    {
      return true;
    }
    return false;
  } // checks whether or not f(x) is positive for all X 
  
  public boolean isStrictlyPositive()
  {
    ArrayList<Double> quadReg = getQuadRegression();
    
    double a = quadReg.get(0);
    double b = quadReg.get(1);
    double c = quadReg.get(2);
    
    if(getMinimum(a, b, c) > 0)
    {
      return true;
    }
    return false;
  } 
  
  /**
   * Checks whether or not f(x) is positive for all X;
   * 80% of all random info-distributions appear to be strictly positive.
   **/
   
  public double getAreaAt(double x)
  {
    ArrayList<Double> qr = getQuadRegression();
    
    double a = qr.get(0);
    double b = qr.get(1);
    double c = qr.get(2);
    
    double output = 0;
    
    output += c * x; 
    output += b * x * x * 0.5;
    output += a * (x * x * x) / 3;
    
    return Math.abs(output);
  }
  /**
   * Returns F(x_i), where F(x) = ax3 / 3 + bx2 / 2 + cx;
   * domain = [0, 1], range = [0, 1], BUT returns -1 for bad inputs.
   **/
   
  public double getTotalArea()
  {
    return getAreaAt(1);
  }
  
  public double getXValOf(double area_threshold)
  {
    for(double i = 0; i <= 1; i += 0.001)
    {
      if((getAreaAt(i) / getTotalArea()) >= area_threshold)
      {
        return (i - 0.001);
      }
    }
    
    System.out.println("InfoDistribution.getXValOf(double area): bad output.");
    return -1;
  }
  
  /**
   * Returns the x-value of the corresponding area,
   * using the reading from [0, x] technique;
   * aka: "reading straight through".
   **/ 
   
 public double getSymmetricXValOf(double area_threshold)
 {
  for(double i = 0; i <= 1; i += 0.001)
  {
    if(((getAreaAt(i) + (getTotalArea() - getAreaAt(1 - i))) / getTotalArea()) >= area_threshold)
    {
      return (i - 0.001);
    }
  }
  
  System.out.println("InfoDistribution.getSymmetricXValOf(double area): bad output.");
  return -1;
 }
 /**
  * Returns the x-value of the corresponding area,
  * using reading from [0, x]U[1-x, 1] technique;
  * aka: "skipping the middle".
  * 
  * NB: since this distribution is symmetric, the x-val. needs to be doubled
  * to obtain the total % read.
  **/ 
    
   public ArrayList<Double> getAsymmetricXValOf(double area_threshold)
   {
     ArrayList<Double> output = new ArrayList<Double>();
     
     double start_x = 0;
     double end_x = 1;
     
     double min_x = start_x + end_x;
     double min_pct_read = 1;
     
    for(double i = 0; i <= 0.5; i += 0.01)
    {
      for(double j = 1; j >= 0.5; j -= 0.01)
      {
        double cumul_area = ((getAreaAt(i) + (getTotalArea() - getAreaAt(j))) / getTotalArea());
        
        if(cumul_area < area_threshold)
        {
          continue;
        }
        
        double curr_x = i + (1 - j);
        
        if(curr_x < min_pct_read)
        {
          start_x = i;
          end_x = j;
          
          min_pct_read = curr_x;
        }
      }
      
      /** old code 
      if(((getAreaAt(i) + (getTotalArea() - getAreaAt(1 - i))) / getTotalArea()) >= area_threshold)
      {
        return (i - 0.001);
      }
      */
    }
    
    output.add(start_x);
    output.add(end_x);
    output.add(min_pct_read);
    
    return output;
   }
   /**
    * Returns the x-value of the corresponding area,
    * using reading from [0, x1]U[1-x2, 1] technique;
    * aka: "optimized skipping".
    * 
    * NB: since this distribution is symmetric, the x-val. needs to be summed
    * over the two outputs [0, x1]% read from the beginning,
    * and [x2, 1]% read from the end,
    * to obtain the total % read.
    **/ 
  
  public void fillArrX()
  {
	  x.add(0.0); // beginning of the story 
	  x.add(.5); // ... middle 
	  x.add(1.0); // ... end
  }	// fills the X-axis as a percentage of the story completed; aka time 
  
  public void fillArrY()
  {
		y.add(getRandDouble(50, 100)); // information density at the beginning 
	  y.add(getRandDouble(0, y.get(0))); // ...at the end
		y.add(getRandDouble(0, y.get(1))); // ... at the middle 
		
		/** 
		 * The end is calculated before the middle since the 
		 * information density of the middle must be smaller
		 * than the two other stages.
		 **/
		
		Collections.swap(y, 1, 2); // correcting the end-middle being out of order 
  } // fills the y-axis w/ information density at the given stage
  
	/**
	 * Information density is highest at the beginning;
	 * since the beginning can be called the "most" surprising stage, 
	 * it never goes below 50% info-dense;
	 * see also: primacy effect.
	 * 
	 * The end is info-dense too, but less than the beginning;
	 * it offers a denouement that ties together information introduced
	 * in the earlier stages: summarizing the information + making it 
	 * aerodynamic; see also: recency effect.
	 * 
	 * The middle is the least-info dense of them all,
	 * it is filled w/ filler and details on ideas introduced in the beginning.
	 * 
	 * Lastly, all parameters must be non-negative.
	 **/ // Notes on information density parameters
	 
	 public ArrayList<Double> getQuadRegression()
	 {
	   ArrayList<Double> output = new ArrayList<Double>();
	   
	   double start_density = y.get(0);
	   double mid_density = y.get(1);
	   double end_density = y.get(2);
	   
	   double b = 4 * mid_density - 3 * start_density - end_density;
	   double c = start_density; // taken from the y-intercept 
	   double a = 4 * (mid_density - b * 0.5 - start_density);
	   
	   /** 
	    * These above quadratic coefficients were derived from
	    * solving the general quadratic of form 
	    * {(0, Y1), (50, Y2), (100, Y3)}.
	    **/
	    
	    b = fix(b);
	    c = fix(c);
	    a = fix(a);
	    // fixing the digits before adding to the output 
	    
	   output.add(a);
	   output.add(b);
	   output.add(c);
	   
	   return output;
	 } // returns a regression of form ax2 + bx + c 
  
  public String toString()
  {
    String s = "";
    
    s += "{";
    for(int i = 0; i < x.size(); i++)
    {
      s += "(";
      s += fix(x.get(i));
      s += ", ";
      s += fix(y.get(i));
      s += ")";
      
      if(i != x.size() - 1)
      {
        s += ", ";
      }
    }
    s += "}";
    
    return s;
  }
}

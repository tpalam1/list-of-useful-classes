import java.util.ArrayList;

public class QuadReg 
{
  ListOfPoints lop; // stores the set of points to approximate 
  
  double m;
  double b; 
  double c;
  // constants used in the linear approximation function, f = mx2 + bx + c
  
  static int NUM_TRIALS_APPROXIMATION = 10000000;
  // number of Monte Carlo trials to run when approximating 
  
  // CLASS VARS. 
  
  
  public QuadReg(ListOfPoints l)
  {
    lop = l; 
    approximate(); 
  }
  
  public QuadReg(ArrayList<Double> x_list, ArrayList<Double> y_list)
  {
    try 
    {
      lop = new ListOfPoints(x_list, y_list);
    }
    catch(Exception e)
    {
      System.out.println(e.getMessage());
      System.exit(0);
    }
    
    approximate();
  }
  
  // CONSTRUCTORS 
  
  
  public void approximate()
  {
    for(int i = 0; i < NUM_TRIALS_APPROXIMATION; i++)
    {
      double CURR_SQUARED_ERROR = getSquaredError();
      
      double M_TEST = getRandDouble(0, 1000);
      double B_TEST = getRandDouble(-1000, 1000);
      double C_TEST = getRandDouble(0, 1000);
      // grabs random values of M and B as a part of a Monte Carlo simulation 
      
      double SE_TEST = getSquaredError(M_TEST, B_TEST, C_TEST);
      // grabs the error under the random values of (M, B)
      
      if(SE_TEST < CURR_SQUARED_ERROR)
      {
        m = M_TEST;
        b = B_TEST;
        c = C_TEST;
      } // IF the random (M, B, C) are a better fit than (m, b, c), 
      // update the current (m,b, c)
      
    } // does the above for a set number of trials 
  } // uses a Monte Carlo simulation to approximate the given ListOfPoints 
  /** 
   * Nota bene: the larger the range given to M_TEST and B_TEST,
   * the larger the range of ListOfPoints that this QuadReg Object can approximate;
   * However, this QuadReg object will approach the ListOfPoints much slower.
   * 
   * Nota bene: both (m, c) are restricted to positive numbers currently for
   * use in the information theory application regarding optimal reading.
   */ 
  
  // MUTATORS 
  
  public static double getRandDouble(double min, double max)
  {
    return Math.random() * (max - min + 1) + min;
  } // returns a random double, [min, max]
  
  public static double fix(double d)
  {
    d = Math.floor(d * 1000) / 1000;
    
    return d;
  } // sets a given Double to 3 decimal precision 
  
  public double getValAt(double x)
  {
    return m * x * x + b * x + c;
  } // returns f = mx2 + bx + c
  
  public double getSquaredError()
  {
    double SQUARED_ERROR = 0;
    
    for(Point q : lop.getCoords())
    {
      double CURR_X = q.getX();
      
      double PREDICTED = getValAt(CURR_X);
      double ACTUAL = q.getY();
      
      double DIFF = ACTUAL - PREDICTED; 
      double DIFF_SQUARED = DIFF * DIFF;
      
      SQUARED_ERROR += DIFF_SQUARED;
    }
    
    return SQUARED_ERROR;
  } 
  /**
   * Loops through all points in LOP; 
   * Gets the SQUARED_ERROR of each point; 
   * Adds to the cumulative SQUARED_ERROR;
   * Returns the cumulative SQUARED_ERROR.
   **/
  
  public double getSquaredError(double M_TEST, double B_TEST, double C_TEST)
  {
    double SQUARED_ERROR = 0;
    
    for(Point q : lop.getCoords())
    {
      double CURR_X = q.getX();
      
      double PREDICTED = M_TEST * CURR_X * CURR_X + B_TEST * CURR_X + C_TEST;
      double ACTUAL = q.getY();
      
      double DIFF = ACTUAL - PREDICTED; 
      double DIFF_SQUARED = DIFF * DIFF;
      
      SQUARED_ERROR += DIFF_SQUARED;
    }
    
    return SQUARED_ERROR;
  } // ~getSquaredError() BUT allows for custom values of (M, B, C)
  
  public double getM()
  {
    return m;
  } 
  
  public double getB()
  {
    return b;
  }
  
  public double getC()
  {
    return c;
  }
  
  public ArrayList<Double> getApproximation()
  {
    ArrayList<Double> output = new ArrayList<Double>();
    
    output.add(m);
    output.add(b);
    output.add(c);
    
    return output;
  } // returns (M, B) 
  
  public String toString()
  {
    double M_DISPLAY = fix(m);
    double B_DISPLAY = fix(b);
    double C_DISPLAY = fix(c);
    double SE_DISPLAY = fix(getSquaredError());
    // rounds all values before displaying; makes output easier to read. 
    
    String s = "";
    
    s += "f(x) = " + M_DISPLAY + " * X ^ 2 + " + B_DISPLAY + " * X + " + C_DISPLAY;
    s += " | SQUARED_ERROR = " + SE_DISPLAY;
    
    return s;
  } // returns the approximation as a String 
  
  // ACCESSORS 
  
} /** Used for getting a quadratic approximation to a set of points. **/

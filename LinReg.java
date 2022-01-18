import java.util.ArrayList;

public class LinReg 
{
  ListOfPoints lop; // stores the set of points to approximate 
  
  double m;
  double b; 
  // constants used in the linear approximation function, f = mx + b
  
  static int NUM_TRIALS_APPROXIMATION = 100000;
  // number of Monte Carlo trials to run when approximating 
  
  // CLASS VARS. 
  
  
  public LinReg(ListOfPoints l)
  {
    lop = l; 
    approximate(); 
  }
  
  // CONSTRUCTORS 
  
  
  public void approximate()
  {
    for(int i = 0; i < NUM_TRIALS_APPROXIMATION; i++)
    {
      double CURR_SQUARED_ERROR = getSquaredError();
      
      double M_TEST = getRandDouble(-10, 10);
      double B_TEST = getRandDouble(-10, 10);
      // grabs random values of M and B as a part of a Monte Carlo simulation 
      
      double SE_TEST = getSquaredError(M_TEST, B_TEST);
      // grabs the error under the random values of (M, B)
      
      if(SE_TEST < CURR_SQUARED_ERROR)
      {
        m = M_TEST;
        b = B_TEST;
      } // IF the random (M, B) are a better fit than (m, b), update the current (m,b)
    } // does the above for a set number of trials 
  } // uses a Monte Carlo simulation to approximate the given ListOfPoints 
  /** 
   * Nota bene: the larger the range given to M_TEST and B_TEST,
   * the larger the range of ListOfPoints that this LinReg Object can approximate;
   * However, this LinReg object will approach the ListOfPoints much slower.
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
    return m * x + b;
  } // returns f = mx + b
  
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
  
  public double getSquaredError(double M_TEST, double B_TEST)
  {
    double SQUARED_ERROR = 0;
    
    for(Point q : lop.getCoords())
    {
      double CURR_X = q.getX();
      
      double PREDICTED = M_TEST * CURR_X + B_TEST;
      double ACTUAL = q.getY();
      
      double DIFF = ACTUAL - PREDICTED; 
      double DIFF_SQUARED = DIFF * DIFF;
      
      SQUARED_ERROR += DIFF_SQUARED;
    }
    
    return SQUARED_ERROR;
  } // ~getSquaredError() BUT allows for custom values of (M, B)
  
  public double getM()
  {
    return m;
  } 
  
  public double getB()
  {
    return b;
  }
  
  public ArrayList<Double> getApproximation()
  {
    ArrayList<Double> output = new ArrayList<Double>();
    
    output.add(m);
    output.add(b);
    
    return output;
  } // returns (M, B) 
  
  public String toString()
  {
    double M_DISPLAY = fix(m);
    double B_DISPLAY = fix(b);
    double SE_DISPLAY = fix(getSquaredError());
    // rounds all values before displaying; makes output easier to read. 
    
    String s = "";
    
    s += "f(x) = " + M_DISPLAY + " * X + " + B_DISPLAY;
    s += " | SQUARED_ERROR = " + SE_DISPLAY;
    
    return s;
  } // returns the approximation as a String 
  
  // ACCESSORS 
  
} /** Used for getting a linear approximation to a set of points. **/

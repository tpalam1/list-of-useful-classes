public class Point 
{
  double x;
  double y;
  
  // CLASS VARS. 
  
  
  public Point()
  {
    x = Math.random();
    y = Math.random();
    
    fixDigits();
  }
  
  public Point(double h, double v)
  {
    x = h;
    y = v;
  }
  
  // CONSTRUCTORS 
  
  
  public double fix(double d)
  {
    d = Math.floor(d * 1000) / 1000;
    
    return d;
  } // sets a given Double to 3 decimal precision 
  
  public void fixDigits()
  {
    x = fix(x);
    y = fix(y);
  } // fixes both coordinates to a specific precision
  
  // MUTATORS
  
  
  public String toString()
  {
    String s = "";
    
    s += "(" + x + ", " + y + ")";
    
    return s;
  }
  
  // ACCESSORS 
} /** Represents a single point on an XY plane. **/

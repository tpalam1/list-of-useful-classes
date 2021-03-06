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
  
  
  public static double fix(double d)
  {
    d = Math.floor(d * 1000) / 1000;
    
    return d;
  } // sets a given Double to 3 decimal precision 
  
  public void fixDigits()
  {
    x = fix(x);
    y = fix(y);
  } // fixes both coordinates to a specific precision
  
  public void flipHorizontal()
  {
    x *= -1;
  }
  
  public void flipVertical()
  {
    y *= -1;
  }
  
  public void rotateHalfCircle()
  {
    flipHorizontal();
    flipVertical();
  }
  
  public void setX(double d)
  {
    x = d;
  }
  
  public void setY(double d)
  {
    y = d;
  }
  
  // MUTATORS
  
  public static Point getMidPointWith(Point p, Point q)
  {
    double MID_X = 0.5 * (q.getX() + p.getX());
    double MID_Y = 0.5 * (q.getY() + p.getY());
    
    return new Point(MID_X, MID_Y);
  }
  
  // STATIC ACCESSORS 
  
 
  public Point getMidPointWith(Point p)
  {
    double MID_X = 0.5 * (this.getX() + p.getX());
    double MID_Y = 0.5 * (this.getY() + p.getY());
    
    return new Point(MID_X, MID_Y);
  }  
    
  public double getX()
  {
    return x;
  }
  
  public double getY()
  {
    return y;
  }
  
  public double getDist(Point p)
  {
    double delta_x = x - p.getX();
    double delta_y = y - p.getY();
    
    double dx_squared = delta_x * delta_x;
    double dy_squared = delta_y * delta_y;
    
    double pythag = Math.sqrt(dx_squared + dy_squared);
    
    return pythag;
  }
  
  public String toString()
  {
    fixDigits(); // makes the Point's coords easier to read 
    
    String s = "";
    
    s += "(" + x + ", " + y + ")";
    
    return s;
  }
  
  // ACCESSORS 
} /** Represents a single point on an XY plane. **/

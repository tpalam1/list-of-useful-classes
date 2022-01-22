public class Point3D
{
  Point p; // stores the (X, Y) coords.
  double z; 
  
  /** 
   * By using a Point Object as a class variable,
   * and not as a superclass,
   * we follow the principle of "composition over inheritance":
   * this makes the Point3D class less sensitive to small changes
   * in the Point class's code.
  **/
  
  // CLASS VARS. 
  
  
  public Point3D()
  {
    p = new Point(); 
    z = Math.random();
  }
  
  public Point3D(Point q)
  {
    p = q;
    z = 0;
  } // Constructs a 3D point with z = 0
  
  public Point3D(double horizontal, double vertical, double depth)
  {
    p = new Point(horizontal, vertical);
    z = depth;
  }
  
  // CONSTURCTORS 
  
  
  public static double fix(double d)
  {
    d = Math.floor(d * 1000) / 1000;
    
    return d;
  } // returns a given Double to 3 decimal precision 
  
  public void fixDigits()
  {
    p.fixDigits();
    z = fix(z);
  } // fixes all coordinates to a specific precision
  
  // MUTATORS
  
  
  public static Point3D getMidPoint(Point3D A, Point3D B)
  {
		Point3D C = new Point3D(
		  0.5 * (A.getX() + B.getX()),
		  0.5 * (A.getY() + B.getY()),
		  0.5 * (A.getZ() + B.getZ()));
		  
		return C;
  }
  
  // STATIC ACCESSORS 
  
  
  public double getX()
  {
    return p.getX();
  }
  
  public double getY()
  {
    return p.getY();
  }
  
  public double getZ()
  {
    return z;
  }
  
  public double getDist(Point3D q)
  {
    double delta_x = this.getX() - q.getX();
    double delta_y = this.getY() - q.getY();
    double delta_z = z - q.getZ();
    
    double dx_squared = delta_x * delta_x;
    double dy_squared = delta_y * delta_y;
    double dz_squared = delta_z * delta_z;
    
    double pythag = Math.sqrt(dx_squared + dy_squared + dz_squared);
    
    return pythag;
  }
  
  public Point getProjectionOnto(String plane) throws Exception
  {
    switch(plane.toUpperCase())
    {
      case "XY":
        return new Point(this.getX(), this.getY());
      case "XZ":
        return new Point(this.getX(), this.getZ());
      case "YZ":
        return new Point(this.getY(), this.getZ());
      default:
        throw new Exception(
          "Point3D.getProjectionOnto(String plane): Error, " + 
          "'String' is not recognized as a valid coordinate plane " +
          "in the XYZ space.");
    }
  }
  
  public String toString()
  {
    fixDigits(); // makes the Point3D's coords easier to read 
    
    String s = "";
    
    s += "(" + getX() + ", " + getY() + ", " + getZ() + ")";
    
    return s;
  }
  
  // NON-STATIC ACCESSORS
  
} /** Represents a single point in an XYZ space. **/

import java.util.ArrayList;

public final class VectorMath
{	
	public static double getDotProduct(
	  double x_1, double y_1, double z_1,
	  double x_2, double y_2, double z_2)
  {
    return x_1 * x_2 + y_1 * y_2 + z_1 *z_2;
  }
  
  public static double getDotProduct(
    double x_1, double y_1,
    double x_2, double y_2)
  {
    return getDotProduct(
      x_1, y_1, 0,
      x_2, y_2, 0);
  }
  
  public static boolean isOrthogonal(
    double x_1, double y_1, double z_1,
    double x_2, double y_2, double z_2)
  {
    double dp = getDotProduct(
      x_1, y_1, z_1,
      x_2, y_2, z_2);
    
    if(dp == 0)
    {
      return true;
    }
    else 
    {
      return false;
    }
  }
  
  public static boolean isOrthogonal(
    double x_1, double y_1,
    double x_2, double y_2)
  {
    return isOrthogonal(
      x_1, y_1, 0,
      x_2, y_2, 0);
  }
  
  public static double getAngleBetween(
    double x_1, double y_1, double z_1,
    double x_2, double y_2, double z_2)
  {
    double numerator = getDotProduct(
      x_1, y_1, z_1,
      x_2, y_2, z_2);
      
    double denominator = getMagnitude(x_1, y_1, z_1) * getMagnitude(x_2, y_2, z_2);
    
    double angle = Math.acos((numerator / denominator));
    
    return angle;
  }
  
  public static double getAngleBetween(
    double x_1, double y_1,
    double x_2, double y_2)
  {
    return getAngleBetween(
      x_1, y_1, 0,
      x_2, y_2, 0);
  }
  
  
	public static ArrayList<Double> getCrossProduct(
	  double x_1, double y_1, double z_1,
	  double x_2, double y_2, double z_2)
  {
    ArrayList<Double> output = new ArrayList<Double>();
    
    double i = y_1 * z_2 - z_1 * y_2;
    double j = z_1 * x_2 - x_1 * z_2;
    double k = x_1 * y_2 - y_1 * x_2;
    
    output.add(i);
    output.add(j);
    output.add(k);
    
    return output;
  }
  
  public static ArrayList<Double> getCrossProduct(
    double x_1, double y_1,
    double x_2, double y_2)
  {
    return getCrossProduct(
      x_1, y_1, 0,
      x_2, y_2, 0);
  }
  
  public static double getMagnitude(
    double x_1, double y_1, double z_1)
  {
    double d = x_1 * x_1 + y_1 * y_1 + z_1*z_1;
    
    d = Math.sqrt(d);
    
    
    return d;
  }
  
  public static double getMagnitude(
    double x_1, double y_1)
  {
    return getMagnitude(x_1, y_1, 0);
  }
  
  public static double getMagnitude(
    double dot_product)
  {
    return Math.abs(dot_product);
  } // returns the absolute value 
  
  public static ArrayList<Double> normalize(
    double x_1, double y_1, double z_1)
  {
    double magnitude = getMagnitude(x_1, y_1, z_1);
    
    ArrayList<Double> output = new ArrayList<Double>();
    
    double i_normalized = x_1 / magnitude;
    double j_normalized = y_1 / magnitude;
    double k_normalized = z_1 / magnitude;
    
    output.add(i_normalized);
    output.add(j_normalized);
    output.add(k_normalized);
    
    return output;
  } // returns unit vector in direction of given vector 
  
  public static ArrayList<Double> normalize(
    double x_1, double y_1)
  {
    return normalize(x_1, y_1, 0);
  }
  
  public static double getVolume(
    double x_1, double y_1, double z_1,
    double x_2, double y_2, double z_2,
    double x_3, double y_3, double z_3)
  {
    ArrayList<Double> crossProduct = getCrossProduct(
      x_1, y_1, z_1,
      x_2, y_2, z_2);
    
    double dotProduct = getDotProduct(
      crossProduct.get(0), crossProduct.get(1), crossProduct.get(2),
      x_3, y_3, z_3);
    
    double magnitude = getMagnitude(dotProduct);
    
    return magnitude;
  } // returns the volume of a parallelepiped defined by 3 Vectors 
  
  public static double getArea(
    double x_1, double y_1, double z_1,
    double x_2, double y_2, double z_2)
  {
    ArrayList<Double> crossProduct = getCrossProduct(
      x_1, y_1, z_1,
      x_2, y_2, z_2);
    
    return getMagnitude(crossProduct);
  } // returns area of a parallelogram defined by 2 Vectors 
} /** Class for calculating quantities related to 3D vectors. **/

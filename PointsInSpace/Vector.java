public class Vector
{
  Point start;
  Point end;
  
  String DISPLAY_SETTING; 
  // CLASS VARS.


  public Vector()
  {
    start = new Point();
    end = new Point();
    
    setDisplaySetting("DEFAULT");
  }
  
  public Vector(Point terminal)
  {
    start = new Point(0, 0);
    end = terminal;
    
    setDisplaySetting("DEFAULT");
  } // sets the start Point at the Origin 

  public Vector(Point initial, Point terminal)
  {
    start = initial;
    end = terminal;
    
    setDisplaySetting("DEFAULT");
  }
  
  public Vector(double magnitude, double direction)
  {
    start = new Point(0, 0);
    
    direction = direction % (2 * Math.PI);
    
    double x = magnitude * Math.cos(direction);
    double y = magnitude * Math.sin(direction);
    end = new Point(x, y);
    // constructs the end point 
    
    setDisplaySetting("DEFAULT");
  }

  // CONSTRUCTORS 
  
  
  public void setDisplaySetting(String s)
  {
    DISPLAY_SETTING = s;
  }
  
  public void add(Vector v)
  {
    double curr_x = end.getX();
    double curr_y = end.getY();
    // gets this Vector's current (x,y)
    
    double new_x = curr_x + v.getDistX();
    double new_y = curr_y + v.getDistY();
    // updates by the Vector V's coordinates 
    
    end.setX(new_x);
    end.setY(new_y);
    // copies the updates to this Vector's (x, y)
  } // adds each Vector, componentwise
  
  public void multiply(double scalar)
  {
    double curr_x = end.getX();
    double curr_y = end.getY();
    // gets this Vector's current (x,y)
    
    double new_x = curr_x * scalar;
    double new_y = curr_y * scalar;
    // updates by the Vector V's coordinates 
    
    end.setX(new_x);
    end.setY(new_y);
    // copies the updates to this Vector's (x, y)
  }
  
  // MUTATORS 


  public static double fix(double d)
  {
    d = Math.floor (d * 1000) / 1000;

    return d;
  }	// sets a given Double to 3 decimal precision 

  public double getMagnitude()
  {
    return start.getDist (end);
  }

  public double getDistX()
  {
    return end.getX() - start.getX();
  }

  public double getDistY()
  {
    return end.getY() - start.getY();
  }

  public double getDirection()
  {
    double angle = Math.atan (getDistY() / getDistX());
    // ATAN is restricted to [-pi/2, pi/2]
    
    String quadrant = getQuadrant();
    
    switch(quadrant)
    {
      case "II":
      case "III": 
        angle += Math.PI;
        break;
      case "IV":
        angle += 2 * Math.PI;
        break;
      case "I":
      default:
        break;
    } // fixes the angle to be in the correct quadrant 
    
    return angle;
  }	
  /** 
   * Returns angle made to the positive X axis;
   * ranges from [0, 2 * PI].
  **/
  
  public Point getStartPoint()
  {
    return start;
  }
  
  public Point getEndPoint()
  {
    return end;
  }
  
  public Vector copy()
  {
    return new Vector(getStartPoint(), getEndPoint());
  }

  public String getQuadrant()
  {
    double x = getDistX();
    double y = getDistY();
    
    if(x > 0)
    {
      if(y > 0)
      {
        return "I";
      }
      else 
      {
        return "IV";
      }
    }
    else 
    {
      if(y > 0)
      {
        return "II";
      }
      else 
      {
        return "III";
      }
    }
  }
  
  public String toString()
  {
    switch(DISPLAY_SETTING)
    {
      case "XY":
      case "COMPONENTS":
        return getComponents();
      case "MAGNITUDE-DIRECTION":
      case "DEFAULT":
      default:
        return getMagnitudeDirection();
    }
  }
  
  public String getMagnitudeDirection()
  {
    double DISPLAY_MAGNITUDE = fix (getMagnitude());
    
    double DISPLAY_DIRECTION = fix (getDirection() / Math.PI);

    String s = "";

    s += "<"; 
    s += "R = " + DISPLAY_MAGNITUDE + ", ";
    s += "θ [rad.] = " + DISPLAY_DIRECTION + "π";
    s += ">";

    return s;
  }
  
  public String getComponents()
  {
    double DISPLAY_X = fix(getDistX());
    double DISPLAY_Y = fix(getDistY());
    
    String s = "";

    s += "<"; 
    s += DISPLAY_X + "i, ";
    s += DISPLAY_Y + "j";
    s += ">";

    return s;
  }

  // ACCESSORS 
} /** Represents a single Vector on an XY plane. **/

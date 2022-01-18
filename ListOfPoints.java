import java.util.ArrayList;

public class ListOfPoints
{
  ArrayList<Point> coords = new ArrayList<Point>(); 
  // stores the points in the array 
  
  // CLASS VARS. 
  
  public ListOfPoints(Point ... p)
  {
    for(Point q : p)
    {
      coords.add(q);
    }
  } // creates a list of several instantiated points 
  
  // CONSTRUCTORS 
  
  public void add(Point p)
  {
    coords.add(p);
  } // appends P to the 'coords' array 
  
  // MUTATORS 
  
  public ArrayList<Point> getCoords()
  {
    return coords;
  }
  
  public int getCountOfPoints()
  {
    return coords.size();
  }
  
  public String toString()
  {
    return coords.toString();
  }
  
  public void display()
  {
    System.out.println(toString());
  } // void toString()
  
  // ACCESSORS 
  
} /** Stores multiple 2D points. **/

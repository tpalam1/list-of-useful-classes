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
  
  public ListOfPoints(ArrayList<Double> X_LIST, ArrayList<Double> Y_LIST) 
    throws Exception 
  {
    int X_SIZE = X_LIST.size();
    int Y_SIZE = Y_LIST.size();
    
    if(X_SIZE != Y_SIZE)
    {
      throw new Exception("ListOfPoints(X_LIST, Y_LIST): " + 
        "lists are not of the same size.");
    }
    
    for(int i = 0; i < X_SIZE; i++)
    {
      int curr_x = X_LIST.get(i);
      int curr_y = Y_LIST.get(i);
      
      coords.add(new Point(curr_x, curr_y));
    }
  } // creates a list from splicing together an X_LIST and Y_LIST
  
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

import java.util.ArrayList;

public class Grid<E>
{
	ArrayList<ArrayList<E>> arr = new ArrayList<ArrayList<E>>();
	
	int X_SIZE;
	int Y_SIZE;
	
	E default_token;
	E active_token;
	// CLASS VARS. 
	
	
	public Grid() throws Exception 
	{
	  throw new Exception("Grid(): sorry, the generic constructor needs inputs.");
	} // requires specification of class type E; thus throws Exception 
	
	public Grid(E dt, E at)
	{
	  default_token = dt;
	  active_token = at;
	  
	  populateGrid(2, 2);
	} // creates a default 2x2 grid, with default default token 'dt' 
	// + active token 'at' 
	
	public Grid(E dt, E at, int h, int v)
	{
	  default_token = dt;
	  active_token = at;
	  
	  populateGrid(h, v);
	} // see Grid(E, E) 
	
	// CONSTRUCTORS 
	
	
	public void populateGrid(int x, int y)
	{
	  X_SIZE = x;
	  Y_SIZE = y;
	  // init. the size of the grid 
	  
	  for(int v = 0; v < Y_SIZE; v++)
	  {
	    arr.add(new ArrayList<E>());
	    // adds a new Y-coord. each line 
	    
	    for(int h = 0; h < X_SIZE; h++)
	    {
	      arr.get(v).add(getDefaultToken());
	    } // adds a new X-element in the Y-coord 
	  }
	} // creates a Grid of (0, x - 1) (0, y - 1)
	
	public void setCellAt(int x, int y, E value)
	{
	  arr.get(y).set(x, value);
	}
	
	public void setCellActive(int x, int y)
	{
	  arr.get(y).set(x, getActiveToken()); 
	}
	
	public void deactivateCell(int x, int y)
	{
	  arr.get(y).set(x, getDefaultToken()); 
	}
	
	public void setDefaultToken(E i)
	{
	  default_token = i;
	}
	
	public void setActiveToken(E i)
	{
	  active_token = i;
	}
	
	// MUTATORS 
	
	
	public E getDefaultToken()
	{
	  return default_token;
	}
	
	public E getActiveToken()
	{
	  return active_token;
	}
	
	public E getCellAt(int x, int y)
	{
	  return arr.get(y).get(x);
	}
	
	public String toString()
	{
	  String s = "";
	  
	  for(int v = 0; v < Y_SIZE; v++)
	  {
	    for(int h = 0; h < X_SIZE; h++)
	    {
	      s += "[";
	      s += getCellAt(h, v);
	      s += "]";
	      
	      s += " ";
	    }
	    
	    s += "\n";
	  }
	  
	  return s;
	}
	
	// ACCESSORS 
	
} 

/** 
 * Used for displaying an M x N grid of squares; 
 * such as a chess board,
 * or a creature's environment. 
 **/

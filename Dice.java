import java.util.ArrayList;

public class Dice 
{
  ArrayList<Integer> d = new ArrayList<Integer>(); 
  // stores the values on the Dice faces 
  
  public Dice()
  {
    for(int i = 1; i <= 6; i++)
    {
      d.add(i);
    }
  } // creates a 6 sided die
  
  public Dice(int n)
  {
    for(int i = 1; i <= n; i++)
    {
      d.add(i);
    }
  } // creates an N-sided dice 
  
  public Dice(int ... faces)
  {
    for(int i : faces)
    {
      d.add(i);
    }
  } // creates a custom sided die
  
  public Dice(ArrayList<Integer> arr)
  {
    d = arr;
  } // ...custom sided die 
  
  public int roll()
  {
    int size = d.size();
    
    int rand_index = (int)(Math.random() * size);
    
    return d.get(rand_index);
  } // returns a random face on this Dice 
  
  public boolean isEquals(Dice d1)
  {
    if(d1.d.size() != this.d.size())
    {
      return false;
    } // return false if both arrays are different lengths 
    
    for(int i = 0; i < d.size(); i++)
    {
      int n = getFaceAt(i);
      int j = d1.getFaceAt(i);
      // gets the Ith index of both die 
      
      if(n != j)
      {
        return false;
      }
    } // check if any elements differ; if true, return false 
    
    return true; 
  } // checks whether this Dice is the same as another 
  
  public int getFaceAt(int index)
  {
    return d.get(index);
  }
  
  public ArrayList<Integer> getAllFaces()
  {
    return d;
  }
  
  public Dice clone()
  {
    ArrayList<Integer> copyArr = d;
    
    return new Dice(copyArr);
  } // returns shallow copy of this Dice object 
  
  public String toString()
  {
    return d.toString();
  } // return the Dice faces as a text output 
  
  public void display()
  {
    System.out.println(toString());
  }
}

import java.util.Collections;
import java.util.ArrayList;

public class Deck 
{
  ArrayList<Card> d; // stores Cards in the Deck  
  
  public Deck()
  {
    d = new ArrayList<Card>();
    
    for(int i = 1; i <= 4; i++)
    {
      for(int j = 1; j <= 13; j++)
      {
        d.add(new Card(j));
      } // adds a suit of 13 cards 
    } // ... does the above 4 times 
    
    Collections.shuffle(d);
  } // creates a new Deck of 52 Cards 
  
  public String toString()
  {
    String output = "[";
    
    for(int i = 1; i <= d.size(); i++)
    {
      Card curr = d.get(i-1);
      
      output += curr.toString();
      
      if(i < d.size())
      {
        output += ", ";
      }
      else 
      {
        output += "]";
      }
    }
    
    return output;
  }
} /** Represents a group of Cards. **/

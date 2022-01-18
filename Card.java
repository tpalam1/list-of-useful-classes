public class Card 
{
  int value; // stores the value of this Card 
  
  public Card()
  {
    value = 0; 
  } // creates a new blank Card 
  
  public Card(int d)
  {
    value = d;
  } // creates a new Card with value D 
  
  public String toString()
  {
    return String.valueOf(value);
  } 
  
} /** Represents a single playing card. **/

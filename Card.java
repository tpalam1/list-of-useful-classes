public class Card 
{
  double value; // stores the value of this Card 
  
  public Card()
  {
    value = 0; 
  } // creates a new blank Card 
  
  public Card(double d)
  {
    value = d;
  } // creates a new Card with value D 
  
  public double getValue()
  {
    return value;
  }
  
  public String toString()
  {
    return String.valueOf(value);
  } 
  
} /** Represents a single playing card. **/

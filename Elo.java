public class Elo 
{
  int e; // stores current Elo value 
  final int k = 20; // modifies how strongly ratings change 
  
  public Elo()
  {
    e = 1000;
  } // sets an initial Elo rating of 1000 
  
  public Elo(int n)
  {
    e = n;
  } // sets a custom Elo 
  
  public int getValue()
  {
    return e;
  } // returns the current Elo value 
  
  public void setValue(int n)
  {
    e = n;
  } // sets the current Elo 
  
  public double getExpectedScore(int n)
  {
    double a = 1; 
    double b = 1 + Math.pow(10, (n - e) / 400);
    
    return a / b; 
  } // returns the percent chance of winning against a foe of N Elo 
  
  public double getExpectedScore(Elo e_1)
  {
    return getExpectedScore(e_1.e);
  } // returns the percent chance of winning against another Elo 
  
  public void updateElo(Elo e_1, boolean hasWon, boolean onlyUpdateFoeElo)
  {
    if(e == 0 || e_1.e == 0)
    {
      return;
    }
    // avoid updating Elo if either player has an Elo of 0 
    
    double expected = getExpectedScore(e_1);
    double actual = (hasWon ? 1 : 0); 
    // set up the inputs for the formula 
    
    double diff = Math.floor(k * (actual - expected));
    if(!onlyUpdateFoeElo) { this.e += diff; } 
    e_1.e -= diff;
    // update both Elo objects in different directions 
    
    this.correctSubZeroElo();
    e_1.correctSubZeroElo();
    // correct for subzero elos 
  } 
  /* update this Elo object and the other by opposite directions;
  is a zero sum game. */
  
  public void correctSubZeroElo()
  {
    if(e < 0)
    {
      e = 0;
    }
  } // sets a minimum elo of 0 during corrections 
  
  public String toString()
  {
    return String.valueOf(e); 
  } // returns the current Elo 
  
  public void display()
  {
    System.out.println(toString());
  } // void toString()  
}
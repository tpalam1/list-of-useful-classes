public class FeigenbaumPopulation
{
  double lambda; // determines the fertility rate 
  double population; 
  
  // CLASS VARS. 
  
  
  public FeigenbaumPopulation()
  {
    population = Math.random();
    lambda = 1;
  }
  
  public FeigenbaumPopulation(double l)
  {
    population = Math.random();
    lambda = l;
  }
  
  public FeigenbaumPopulation(double l, double init_population)
  {
    lambda = l;
    population = init_population;
  }
  
  // CONSTRUCTORS 
  
  public static double fix(double d)
  {
    d = Math.floor(d * 1000) / 1000;
    
    return d;
  } // sets a given Double to 3 decimal precision 
  
  public void step()
  {
    double BIRTH_RATE = lambda * population;
    double DEATH_RATE = 1 - population;
    
    double next_year_population = BIRTH_RATE * DEATH_RATE;
    
    setCurrPopulation(next_year_population);
  }
  
  public void setCurrPopulation(double d)
  {
    population = d;
  }
  
  // MUTATORS 
  
  
  public double getLambda()
  {
    return lambda;
  }
  
  public double getPopulation()
  {
    return population;
  }
  
  public String toString()
  {
    double LAMBDA_DISPLAY = fix(lambda);
    double POP_DISPLAY = fix(population);
    // makes the output easier to read by fixing the digits
    
    String s = "";
    
    s += "(" + "Lambda = " + LAMBDA_DISPLAY + ", ";
    s += "population = " + POP_DISPLAY + ")";
    
    return s;
  }
  
  // ACCESSORS 
} 
/** 
 * Short class to model Feigenbaum's population, 
 * a recursive function explaining the population cycles of rabbits;
 * see also: https://www.youtube.com/watch?v=ETrYE4MdoLQ
 **/ 

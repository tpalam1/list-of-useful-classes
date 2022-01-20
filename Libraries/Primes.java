import java.util.ArrayList; 
import java.util.Collections;

public final class Primes
{
  public static ArrayList<Integer> getPrimesBelow(int i)
  {
    ArrayList<Integer> output = new ArrayList<Integer>();
	  
	  for(int n = 1; n <= i; n++)
	  {
	    if(isPrime(n) == true)
	    {
	      output.add(n);
	    }
	  }
	  
	  return output; 
	}
  
	public static ArrayList<Integer> getFactors(int n)
	{
	  ArrayList<Integer> output = new ArrayList<Integer>(); 
	  // intit. the output 
	  
	  output.add(1); 
	  output.add(n);
	  // all numbers are divisible by 1 and themselves by definition 
	  
	  for(int i = 2; i <= (int)(n/2); i++)
	  {
	    if(n % i == 0)
	    {
	      output.add(i);
	    }
	  }
	  
	  Collections.sort(output); // sort the output 
	  
	  return output;
	  
	} // returns the factors of N 
	
	public static boolean isPrime(int n)
	{
	  for(int i = 2; i <= (int)Math.pow(n, 0.5) + 1; i++)
	  {
	    if(n % i == 0)
	    {
	      return false;
	    }
	  }
	  
	  return true;
	  
	} // checks if N is prime IF N is divisible by any [2, sqrt(N)]
  
	public static boolean isCoprime(int m, int n)
	{
	  ArrayList<Integer> FACTORS_N = getFactors(n);
	  ArrayList<Integer> FACTORS_M = getFactors(m);
	  
	  for(int i = 0; i < FACTORS_N.size(); i++)
	  {
	    for(int j = 0; j < FACTORS_M.size(); j++)
	    {
	      Integer CURR_ENTRY_N = FACTORS_N.get(i); 
	      Integer CURR_ENTRY_M = FACTORS_M.get(j);
	      
	      if(CURR_ENTRY_N.equals(1) || CURR_ENTRY_M.equals(1))
	      {
	        continue;
	      } // continue if either current entry is 1 
	      
	      if(CURR_ENTRY_N.equals(CURR_ENTRY_M))
	      {
	        return false;
	      } // break IF any factor is shared 
	    }
	  }
	  
	  // compares each factor of N and each factor of M; 
	  // breaks loop if any are shared.
	  
	  return true; // null hypothesis 
	} // returns TRUE IF N and M share any factors besides 1; FALSE elsewise. 

} /** Library with various functions relating to prime numbers. **/

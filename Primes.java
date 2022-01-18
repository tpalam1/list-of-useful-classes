import java.util.ArrayList; 
import java.util.Collections;

public static Primes
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
} /** Class with various functions relating to prime numbers. **/

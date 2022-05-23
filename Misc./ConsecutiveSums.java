import java.util.ArrayList;

public class Main
{
	public static void main(String[] args) 
	{
	  int increment = 4;
	  
	  System.out.println("N\tSTARTING VALUE\tNUMBER OF CONSECUTIVE INTEGERS\tVERDICT");
		for(int i = 3; i <= 100; i++)
		{
		  ArrayList<Double> results = getLargestComposition(i, increment);
		  
		  double A = results.get(0);
		  double B = results.get(1);
		  
		  String verdict = getVerdict(A, B);
		  
		  System.out.println(i + "\t" + A + "\t\t" + B + "\t\t\t\t" + verdict);
		}
	}
	
	public static double getA(int num_consect_values)
	{
	  return getA(num_consect_values, 1);
	}
	
	public static double getA(int num_consect_values, int increment)
	{
	  return 0.5 * (num_consect_values * num_consect_values - num_consect_values) * increment;
	}
	/**
	 * A = the first number in the the series,
	 * such that (A) + (A + 1 * increment) + (A + 2 * increment) + ... 
	 *    for 'num_consect_values' = number of terms in the formula above.
	 **/ 
	
	
	public static double getB(int num_consect_values)
	{
	  return num_consect_values;
	}
	
	public static double getStartingValueOfConsecutiveDecomposition(int num, 
	  int num_consect_values)
	{
	  return getStartingValueOfConsecutiveDecomposition(num, num_consect_values, 1);
	}
	
	public static double getStartingValueOfConsecutiveDecomposition(int num, 
	  int num_consect_values,
	  int increment)
	{
	  double A = getA(num_consect_values, increment);
	  double B = getB(num_consect_values);
	  
	  return (num - A) / B;
	}
	/**
	 * The series of consecutive numbers summing up to N can be written as:
	 * N = the summation from n = [0, number of consecutive values] of the function 
	 *    f = starting value + n * the increment.
	 * 
	 * This above formula is also written as:
	 *    N = Number of consecutive values * ...
	 *      ... (starting value + 1/2 * (number of consecutive values + 1)) + ...
	 *      ... starting value 
	 **/
	
	public static boolean isComposable(int num, int num_consect_values)
	{
	  return isComposable(num, num_consect_values, 1);
	}
	
	public static boolean isComposable(int num, int num_consect_values, int increment)
	{
	  double a = getStartingValueOfConsecutiveDecomposition(num, num_consect_values, increment);
	  
	  if(isInteger(a))
	  {
	    return true;
	  }
	  return false;
	} // determines if a number can be decomposed into an N length string 
	
	public static boolean isInteger(double num)
	{
	  if(num % 1 == 0)
	  {
	    return true;
	  }
	  return false;
	}
	
	public static ArrayList<Double> getLargestComposition(int num)
	{
    return getLargestComposition(num, 1);
	}
	
	public static ArrayList<Double> getLargestComposition(int num, int increment)
	{
	  double starting_value = -1;
	  double num_consect_values = -1; 
	  // null return in case the loop returns nothing 
	  
	  for(int i = 2; i < num; i++)
	  {
	    if(isComposable(num, i, increment))
	    {
	      starting_value = getStartingValueOfConsecutiveDecomposition(num, i, increment);
	      num_consect_values = (i - 1);
	      
	      // System.out.println(starting_value + "\t" + num_consect_values);
	    }
	  }
	  
	  ArrayList<Double> output = new ArrayList<Double>();
	  output.add((double)starting_value);
	  output.add(num_consect_values);
	  // returns the output as a (starting_value, number of consec. values pair)
	  
	  return output;
	} // finds the largest string of consecutive numbers which sum up to the given number 
	
	public static String getVerdict(double A, double B)
	{
	  String verdict = "";
	  
    if(B <= 0)
    {
      verdict += "IMPOSSIBLE";
    }
    else 
    {
      verdict += "POSSIBLE, ";
      
      if(A > 0)
      {
        verdict += "USING ONLY POSITIVE INTEGERS";
      }
      else if((A == 0))
      {
        verdict += "IF STARTING WITH ZERO";
      }
      else
      {
        verdict += "IF STARTING WITH NEGATIVE INTEGERS";
      }
    }
    
    
    return verdict;
	} // categorizes the results
}

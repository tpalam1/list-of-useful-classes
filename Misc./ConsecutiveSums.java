import java.util.ArrayList;

public class Main
{
	public static void main(String[] args) 
	{
	  System.out.println("N\tSTARTING VALUE\tNUMBER OF CONSECUTIVE INTEGERS\tVERDICT");
		for(int i = 3; i <= 1000; i++)
		{
		  ArrayList<Double> results = getLargestComposition(i);
		  
		  double A = results.get(0);
		  double B = results.get(1);
		  
		  String verdict = getVerdict(A, B);
		  
		  System.out.println(i + "\t" + A + "\t\t" + B + "\t" + verdict);
		}
	}
	
	public static double getA(int num_consect_values)
	{
	  return 0.5 * (num_consect_values * num_consect_values - num_consect_values);
	}
	
	public static double getStartingValueOfConsecutiveDecomposition(int num, int num_consect_values)
	{
	  double A = getA(num_consect_values);
	  double B = num_consect_values;
	  
	  return (num - A) / B;
	}
	
	public static boolean isComposable(int num, int num_consect_values)
	{
	  double a = getStartingValueOfConsecutiveDecomposition(num, num_consect_values);
	  
	  if(isInteger(a))
	  {
	    return true;
	  }
	  return false;
	}
	
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
	  double starting_value = -1;
	  double num_consect_values = -1; 
	  // null return in case the loop returns nothing 
	  
	  for(int i = 2; i < num; i++)
	  {
	    if(isComposable(num, i))
	    {
	      starting_value = getStartingValueOfConsecutiveDecomposition(num, i);
	      num_consect_values = i;
	      
	      // System.out.println(starting_value + "\t" + num_consect_values);
	    }
	  }
	  
	  ArrayList<Double> output = new ArrayList<Double>();
	  output.add((double)starting_value);
	  output.add(num_consect_values);
	  // returns the output as a (starting_value, number of consec. values pair)
	  
	  return output;
	}
	
	public static String getVerdict(double A, double B)
	{
	  String verdict = "";
	  
    if((A == -1) && (B == -1))
    {
      verdict = "IMPOSSIBLE";
    }
    else if((A == 0))
    {
      verdict = "POSSIBLE, IF STARTING WITH ZERO";
    }
    else if((A < 0))
    {
      verdict = "POSSIBLE, IF STARTING WITH NEGATIVE NUMBERS";
    }
    else 
    {
      verdict = "POSSIBLE";
    }
    
    return verdict;
	}
}

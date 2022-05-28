import java.util.ArrayList;
import java.util.Collections;

public class Main
{
	public static void main(String[] args) 
	{
	  int trials = 1000000;
	  ArrayList<Integer> count_matching_ARR = new ArrayList<Integer>();
	  
		for(int i = 0; i < trials; i++)
		{
		  String A = getRandID(); // id_1, the original id to compare everything else to;
		  String B = getRandID();
		  String C = getRandID();
		  String D = getRandID();
		  String E = getRandID();
		  
		  String F = getRandID();
		  String G = getRandID();
		  
		  int count_matching = getCountMatchingDigits(A, B, C, D, E,
		                                              F, G);
		  
		  // System.out.println(A + "\t" + B + "\t" + count_matching);
		  
		  count_matching_ARR.add(count_matching);
		}
		
		Collections.sort(count_matching_ARR);
		
		System.out.println("RESULTS");
		System.out.println("NUM. MATCHING DIGITS\tREL. FREQ. [%]");
		for(int i = 0; i <= 5; i++)
		{
		  System.out.println(i + "\t\t\t" + getRelFreqOf(i, count_matching_ARR));
		}
	}
	
  public static double getRelFreqOf(int min_value_inclusive,
                                    int max_value_inclusive,
                                    ArrayList<Integer> arr)
  {
    if(min_value_inclusive == max_value_inclusive)
    {
      return getRelFreqOf(min_value_inclusive, arr);
    }
    else 
    {
      return (double) countNumBetween(min_value_inclusive, max_value_inclusive, arr) / (double) arr.size();
    }
  }
  
	public static double getRelFreqOf(int specific_value, ArrayList<Integer> arr)
	{
	  int count = countNumOf(specific_value, arr);
	  
	  return (double) count / arr.size();
	}
	
	public static int countNumOf(int specific_value, ArrayList<Integer> arr)
	{
	  Collections.sort(arr);
	  
	  return Collections.frequency(arr, specific_value);
	}
	
	public static int countNumBetween(int min_value_inclusive, 
	                                  int max_value_inclusive,
	                                  ArrayList<Integer> arr)
  {
    Collections.sort(arr);
    
    int count = 0;
    for(int i = 0; i < arr.size(); i++)
    {
      int curr_entry = arr.get(i);
      
      if(min_value_inclusive <= curr_entry && curr_entry <= max_value_inclusive)
      {
        count++;
      }
    }
    
    return count;
  }
	
	public static int getCountMatchingDigits(
	  String id_1,
	  String id_2)
  {
    int count = 0;
    
    for(int i = 1; i <= id_1.length(); i++)
    {
      char c1 = getDigitAt(i, id_1);
      char c2 = getDigitAt(i, id_2);
      
      if(c1 == c2)
      {
        count++;
      }
      else 
      {
        break;
      }
    }
    
    return count;
  }
  
  public static int getCountMatchingDigits(
    String id_1,
    String id_2,
    String id_3)
  {
    int case_A = getCountMatchingDigits(id_1, id_2);
    int case_B = getCountMatchingDigits(id_1, id_3);
    
    return Math.max(case_A, case_B);
  }
  
  public static int getCountMatchingDigits(
    String id_1,
    String ... ids)
  {
    ArrayList<Integer> results = new ArrayList<Integer>();
    
    for(String s : ids)
    {
      results.add(getCountMatchingDigits(id_1, s));
    }
    
    return Collections.max(results);
  } // returns the largest string of matching digits to 'id_1' from a set of Strings 
	
	public static char getDigitAt(int position_from_right, String id)
	{
	  int length = id.length();
	  int position = length - position_from_right;
	  
	  
	  return id.charAt(position);
	} // ranges from [1, 5]
	
	public static String getRandID()
	{
	  int length = 5;
	  
	  String output = "";
	  for(int i = 0; i < length; i++)
	  {
	    int digit_to_add = getRandDigit();
	    String string_to_add = Integer.toString(digit_to_add);
	    
	    output += string_to_add;
	  }
	  
	  return output;
	}
	
	public static int getRandDigit()
	{
	  return getRandInt(0, 9);
	} // returns a random integer from [0, 9]
	
  public static double getRandDouble(double min, double max)
  {
    return Math.random() * (max - min + 1) + min;
  } // returns a random double, [min, max]
  
  public static int getRandInt(int min, int max)
  {
    return (int) getRandDouble((double) min, (double) max);
  } // see getRandDouble(min, max)
}

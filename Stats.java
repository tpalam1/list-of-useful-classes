import java.util.ArrayList;
import java.util.Collections;

public final class Stats 
{
	public static double getMedian(ArrayList<Double> arr)
	{
    Collections.sort(arr);
    // sanitizes the input before operations 
    
	  double size = arr.size();
    double half_size = size / 2;
	  
	  int HALF_SIZE_LOWER_BOUND = (int)(half_size) - 1;
	  int HALF_SIZE_UPPER_BOUND = (int)(half_size);
	  
	  if(size % 2 == 0)
	  {
	    double a1 = (double)arr.get(HALF_SIZE_LOWER_BOUND);
	    double a2 = (double)arr.get(HALF_SIZE_UPPER_BOUND);
	    
	    return getAvg(a1, a2);
	  } // IF even? find the middle two values + average them! 
	  else 
	  {
	    return arr.get((int)(half_size));
	  } // ELSE? returns the value in the middle of the ArrayList 
	}

	public static double getValueAt(double percentile, ArrayList<Double> arr)
	{
    Collections.sort(arr);
    
	  int index = (int)(arr.size() * percentile);
	  
	  return arr.get(index);
	}
  
	public static double getSum(ArrayList<Double> arr)
	{
	  double sum = 0;
	  for(Double d : arr)
	  {
	    sum += d;
	  }
	  return sum; 
	}
  
  public static double getAvg(double d1, double d2)
  {
    return (d1 + d2) / 2;
  }
  
  public static double getAvg(ArrayList<Double> arr)
  {
    return getSum(arr) / (double)(arr.size());
  }
  
}
/** Custom library for retrieving handy summary statistics from ArrayLists. **/

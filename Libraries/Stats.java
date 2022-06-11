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

	public static ArrayList<Double> getSummary(ArrayList<Integer> arr)
	{
	  ArrayList<Double> output = new ArrayList<Double>();

	  Collections.sort(arr);

	  output.add(getValueAt(10.0, arr));
	  output.add(getValueAt(50.0, arr));
	  output.add(getValueAt(90.0, arr));

	  return output;
	} // returns the (10th, 50th, 90th) percentiles of the Array 

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

	public static double getRelFreqOf(double specific_val, ArrayList<Double> arr)
	{
	  double count = arr.size();
	  double count_of_num = getCountOf(specific_val, arr);

	  return (count_of_num / count);
	}

	public static double getValueAt(double percentile, ArrayList<Integer> arr)
	{
		Collections.sort(arr);

	  int index = (int)(arr.size() * percentile / 100);

	  return arr.get(index);
	}

	public static double getMin(ArrayList<Double> arr)
	{
	  Collections.sort(arr);

	  return arr.get(0);
	}

	public static double getMax(ArrayList<Double> arr)
	{
	  Collections.sort(arr);

	  int list_size = arr.size();
	  int last_entry = list_size - 1;

	  return arr.get(last_entry);
	}
	public static double getCountOf(double num, ArrayList<Double> arr)
	{
	  int count = 0;

	  for(Double d : arr)
	  {
	    if(isEqual(num, d))
	    {
	      count++;
	    }
	  }

	  return count;
	}

	public static double getCountBetween(
					  double min_value_inclusive, 
					  double max_value_inclusive,
					  ArrayList<Double> arr)
	{
		Collections.sort(arr);

		double count = 0;
		for(int i = 0; i < arr.size(); i++)
		{
		double curr_entry = arr.get(i);

		if(min_value_inclusive <= curr_entry && curr_entry <= max_value_inclusive)
		{
		count++;
		}
	}

	return count;
	}

	public static boolean isEqual(double d1, double d2)
	{
	  if(Double.compare(d1,d2) == 0)
	  {
	    return true;
	  }

	  return false;
	}

	public static ArrayList<ArrayList<Double>> getHistogram(ArrayList<Double> arr)
	{
	  ArrayList<ArrayList<Double>> output = new ArrayList<ArrayList<Double>>();

	  double min = getMin(arr);
	  double max = getMax(arr);

	  for(double i = min; i <= max; i++)
	  {
	    ArrayList<Double> curr_entry = new ArrayList<Double>();
	    curr_entry.add(i); 
	    curr_entry.add(getRelFreqOf(i, arr));

	    output.add(curr_entry);
	  }

	  return output;
	}

	public static void displayHistogram(ArrayList<Double> arr)
	{
	  ArrayList<ArrayList<Double>> histogram = getHistogram(arr);

	  System.out.println("HISTOGRAM RESULTS");
	  System.out.println("X" + "\t" + "PDF(X)");
	  System.out.println("-------------------");
	  for(int i = 0; i < histogram.size(); i++)
	  {
	    ArrayList<Double> curr_arr = histogram.get(i);

	    double curr_val = curr_arr.get(0);
	    double curr_rel_freq = curr_arr.get(1);

	    System.out.println(curr_val + "\t" + curr_rel_freq);
	  }
	}
}
/** Custom library for retrieving handy summary statistics from ArrayLists. **/

import java.util.ArrayList;
import java.util.Collections;

public class Main
{
	public static void main(String[] args) 
	{
	  int num_rand_trials = 10000;
	  double min_sdev = 1E99;
	  double optimal_angle = 1E99;
	  
	  double curr_score = 0;
	  
	  int num_angles = 100;
	  
	  System.out.println("TRIAL\tANGLE[deg]\tMEAN\tSDEV\tSCORE");
	  for(int i = 0; i < num_rand_trials; i++)
	  {
  	  double angle_increment = fix(Math.random());
  	  double curr_sdev = getSdevOf(angle_increment, num_angles);
  	  double curr_avg = getAvg(angle_increment, num_angles);
  	  
  	  if(getScore(curr_avg, curr_sdev) > curr_score)
  	  {
   	    min_sdev = curr_sdev;
   	    optimal_angle = angle_increment;
   	    
   	    curr_score = getScore(getAvg(angle_increment, num_angles), getSdevOf(angle_increment, num_angles));
    	    
  	    System.out.println(i + "\t" + 
  	      (angle_increment*360) + "\t" +
  	      getAvg(angle_increment, num_angles) + "\t" +
  	      getSdevOf(angle_increment, num_angles) + "\t" + 
  	      curr_score);
  	  }
	  }
	  
	  System.out.println("\nFINAL RESULTS [deg]:");
	  System.out.println((optimal_angle*360) + "\t" + min_sdev);
	}
	
  public static double getRandDouble(double min, double max)
  {
    return Math.random() * (max - min + 1) + min;
  } // returns a random double, [min, max]
  
  public static void addNewAngleTo(ArrayList<Double> arr, double angle_increment)
  {
    int curr_length = arr.size() - 1;
    
    if(curr_length < 0)
    {
      arr.add(angle_increment);
    }
    else 
    {
      double curr_angle = arr.get(curr_length);
      
      double next_angle = curr_angle + angle_increment;
      next_angle = next_angle % 1; // cuts the range back down to [0, 1] around a circle 
      
      arr.add(next_angle);
    }
  }
  
  public static void addMultipleAnglesTo(ArrayList<Double> arr, 
    double angle_increment, 
    int num_angles)
  {
    for(int i = 0; i < num_angles; i++)
    {
      addNewAngleTo(arr, angle_increment);
    }
  }
  
  public static double fix(double d)
  {
    d = Math.floor(d * 1E9) / 1E9;
    
    return d;
  } // returns a given Double to some decimal precision
  
  public static void fix(ArrayList<Double> d)
  {
    for(int i = 0; i < d.size(); i++)
    {
      double curr_val = d.get(i);
      d.set(i, fix(curr_val));
    }
  }
  
  public static ArrayList<Double> getArrConsectAngleDiff(ArrayList<Double> angle_measures)
  {
    ArrayList<Double> output = new ArrayList<Double>(); 
    
    for(int i = 0; i < angle_measures.size() - 1; i++)
    {
      double angle_A = angle_measures.get(i);
      double angle_B = angle_measures.get(i + 1);
      
      double diff = angle_B - angle_A;
      
      output.add(diff);
    }
    
    fix(output);
    
    return output;
  }
  
  public static double getSdevOf(ArrayList<Double> consecutive_angle_arr)
  {
    double sum = 0;
    double avg = getAvg(consecutive_angle_arr);
    
    for(int i = 0; i < consecutive_angle_arr.size(); i++)
    {
      double curr_value = consecutive_angle_arr.get(i);
      
      sum += Math.pow(curr_value - avg, 2);
    }
    
    double temp = sum / (consecutive_angle_arr.size() - 1);
    double sdev = Math.pow(sum, 0.5);
    
    return sdev;
  }
  
  public static double getSum(ArrayList<Double> arr)
  {
    double sum = 0;
    
    for(int i = 0; i < arr.size(); i++)
    {
      double curr_value = arr.get(i);
      
      sum += curr_value;
    }
    
    return sum;
  }
  
  public static double getAvg(ArrayList<Double> arr)
  {
    return getSum(arr) / arr.size();
  }
  
  public static double getAvg(double angle_increment, int num_angles)
  {
	  ArrayList<Double> angle_measures = new ArrayList<Double>();
	  
	  addMultipleAnglesTo(angle_measures, angle_increment, num_angles);
	  fix(angle_measures);
	  
	  Collections.sort(angle_measures);
	  
	  return getAvg(getArrConsectAngleDiff(angle_measures));
  }

  public static double getSdevOf(double angle_increment, int num_angles)
  {
	  ArrayList<Double> angle_measures = new ArrayList<Double>();
	  
	  addMultipleAnglesTo(angle_measures, angle_increment, num_angles);
	  fix(angle_measures);
	  
	  Collections.sort(angle_measures);
	  
	  return getSdevOf(getArrConsectAngleDiff(angle_measures));
  }
  
  public static double getScore(double mean, double sdev)
  {
    double max_score = 100;
    double weight_mean = 1000;
    double weight_sdev = 250;
    
    max_score -= mean * weight_mean;
    max_score -= sdev * weight_sdev;
    
    return max_score;
  }
}

/*
My first attempt at the golden angle problem;
see: https://docs.google.com/document/d/12lcr8iLZzvaFq2tOrKeehm52Ep0v-2shD1QGTkgJXy8/edit?usp=sharing
*/

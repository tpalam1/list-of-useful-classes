import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;

public class Performance
{
  static OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(
                                        OperatingSystemMXBean.class);
                                        
  long startTime; 
  long endTime; // ...both in Unix time [secs]
  
  // CLASS VARS.
  
  
  public Performance()
  {
    setStartTimeAsCurrTime();
  } // initializes the start time as the current time 
  
  // CONSTRUCTORS
  
  
  public void setStartTimeAsCurrTime()
  {
    startTime = System.currentTimeMillis() / 1000L; 
  } 
  
  public void setEndTimeAsCurrTime()
  {
    endTime = System.currentTimeMillis() / 1000L; 
  } 
  
  // MUTATORS 
  
  
  public static double fix(double d)
  {
    d = Math.floor(d * 1000) / 1000;
    
    return d;
  } // sets a given Double to 3 decimal precision
  
  public static long getCommittedVirtualMemorySize()
  {
    return osBean.getCommittedVirtualMemorySize();
  } 
  /** Returns the amount of virtual memory 
   * that is guaranteed to be available 
   * to the running process in bytes, 
   * or -1 if this operation is not supported.
   **/

  public static long getFreePhysicalMemorySize()
  {
    return osBean.getFreePhysicalMemorySize();
  } // Returns the amount of free physical memory in bytes.
  
  public static long getTotalPhysicalMemorySize()
  {
    return osBean.getTotalPhysicalMemorySize();
  }
  
  public static long getUsedMemorySize()
  {
    return getTotalPhysicalMemorySize() - getFreePhysicalMemorySize();
  }
  
  public static double getUsedMemoryPct()
  {
    double percentageUsed = 100.0 * getUsedMemorySize() / getCommittedVirtualMemorySize();
    // converting longs to double w/ the "100.0" scalar 

    percentageUsed *= 1.0 / 100.0;
    percentageUsed = fix(percentageUsed);
    // converting down to [0, 1]
    
    
    return percentageUsed;
  }
  
  public static double getProcessCpuTime()
  {
    return osBean.getProcessCpuTime() / 1E9;
  } // Returns the current processor time used, in seconds 
  
  public long getTimeElapsed()
  {
    return endTime - startTime;
  }
  
  public String toString()
  {
    setEndTimeAsCurrTime();
    
    String s = "";
    
    s += "Time elapsed [s] = " + getTimeElapsed() + "\t";
    s += "Memory usage [%] = " + getUsedMemoryPct(); 
    
    return s;
  }
  /** Returns the current time elapsed + memory usage percent; 
   * calls setEndTimeAsCurrTime() before printing the toString().
   **/ 
  
  // ACCESSORS 
} 
/** Class for measuring the space-time efficiency of methods. **/

/** HOW TO USE:
 * Initialize the Performance Object before calling method X,
 *    this sets the current time as the start time;
 * Call Method X;
 * Print this Performance Object,
 *    assumes the time which this 'toString' is called to be the end time.
 **/ 

/** KNOWN BUGS:
 * getUsedMemoryPct() varies wildly each trial. 
 *    Perhaps remedy w/ multiple trials, averaged?
 **/ 

import java.io.File;
import java.io.PrintWriter; 

public class Main
{
  int NUM_POW_MANDELBROT = 2;
  
	public static void main(String[] args) 
	{
	  File f = new File("output.txt");
	  
	  try 
	  {
	    f.createNewFile();
	  } catch(Exception ignored) {}
	  
	  try 
	  {
	    PrintWriter pw = new PrintWriter(f);
	    
  	  for(double x = -10; x < 10; x += .01)
  	  {
  	    for(double y = -10; y < 10; y += .01)
  	    {
  	      if(!doesExplode(new ComplexNumber(x, y)))
  	      {
  	        pw.println(x + "\t" + y);
  	        pw.flush();
  	      }
  	    }
  	  }
  	  
  	  pw.close();
	  }
	  catch(Exception ignored) {}
	  
	  System.out.println("Finished.");
	  
	}
	
	
	public static boolean doesExplode(ComplexNumber addend)
	{
	  ComplexNumber start = new ComplexNumber(0, 0);
	  ComplexNumber copy = start.copy();
	  
	  for(int i = 0; i < 500; i++)
	  {
	    copy.add(addend);
	    
	    copy = ComplexNumber.pow(copy, NUM_POW_MANDELBROT);
	    
	    if(copy.isNaN())
	    {
	      return true;
	    }
	  }
	  
	  return false;
	}
	
	/** Measures whether or not a given value will explode in the Mandelbrot set. **/
}

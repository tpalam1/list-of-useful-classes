import java.util.ArrayList;
import java.io.File;
import java.io.PrintWriter;

public class Main
{
	static Deck dk = new Deck();
	static int NUM_ROUNDS_EACH = 13;
	static int NUM_TRIALS = 10000;
  
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


	    for(int i = 0; i < NUM_TRIALS; i++)
	    {
	      dk = new Deck();
	      
	      for(int j = 1; j <= NUM_ROUNDS_EACH; j++)
	      {
    	    pw.print(getAvg(true, 4));
    	    pw.flush();
    	    
    	    if(j != NUM_ROUNDS_EACH)
    	    {
      	    pw.print("\t");
      	    pw.flush();
    	    }
	      }
	      
  	    pw.println();
  	    pw.flush();
	    }
	   
	    
	    pw.close();
	  } catch(Exception ignored) {}
	  
	  System.out.println("Finished!");
	}
	
	public static ArrayList<Double> draw(boolean removeFromDeck, int num_cards)
	{
		ArrayList<Card> draw_original = dk.draw(removeFromDeck, num_cards);
		
		ArrayList<Double> draw_converted = new ArrayList<Double>();
		for(Card c : draw_original)
		{
		  draw_converted.add(c.getValue());
		}
		
		return draw_converted;
	}
	
	public static String getAvg(boolean removeFromDeck, int num_cards)
	{
	  ArrayList<Double> draw_values = draw(removeFromDeck, num_cards);
	  
	  double avg = Stats.getAvg(draw_values);
	  
	  String s = String.valueOf(avg);
	  
	  return s;
	}
}
/** 
 * Driver class for drawing sets of cards. 
 * (1) The Driver generates a new Deck. 
 * (2) The Driver draws a set of 4 Cards from the Deck. 
 * (3) Each set of 4 cards is averaged by their point value. 
 * (4) This value is pasted to the output file. 
 * (5) Repeats steps (2-4) 13 times, or until the Deck runs out.
 * (6) Repeats steps (1-5) NUM_TRIALS times.
 * (7) Returns the values of all average sets in a text file named "output.txt".
 **/

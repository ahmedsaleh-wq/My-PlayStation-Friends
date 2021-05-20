import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Class to represent a PlayStation game.
 * Created for Data Structures & Algorithms, UPEI
 * @author James Baumeister
 * @version 1.0
 */
public class Game {
	private String name;
    private Calendar released;
    private int totalTrophies;
    private Game next;
    
    
    public Game() {
    	name = "";
    	released = null;
    	totalTrophies=0;
    	//next=null;
    }

    public Game(String name, Calendar released, int totalTrophies) {
    	this.name = name;
    	this.released = released;
    	this.totalTrophies = totalTrophies;
    	//next = null;
    }
    
    public String getName() {

        return name;

         }

    public Calendar getReleased() {

              return released;

         }
   
    public int getTotalTrophies() {

              return totalTrophies;

         }

    public Game getNext() {

              return next;

         }

    public void setNext(Game game) {
    	next = game;
         }

    public String toString() {
    	SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        return "\""+name+"\", released on: "+ sdf.format(released.getTime());
    }

    @Override
    public boolean equals(Object o) {
        // TODO: Implement equals for all instance variables
        return false;
    }
}

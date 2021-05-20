import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * Class to represent a PlayStation user.
 * Created for Data Structures & Algorithms, UPEI
 * @author James Baumeister
 * @version 1.0
 */
public class User {
    private String username;
    private int level;
    private double key;
    private ArrayList<Trophy> trophies;
    private GameList games;
    private Calendar dob;
    private User left;
    private User right;
    int height;
    public User(String username, Calendar dob, int level) {
    	this.username=username;
    	this.dob=dob;
    	this.level=level;
    	key =calculateKey();
    	height = 1;
    }
    
    String getUsername() {
    	return username;
    }
    Calendar getDob() {
    	return dob;
    }
    int getLevel() {
    	return level;
    }
    double getKey() {
    	return key;
    }
    public User getLeft() {
		return left;
	}
    public User getRight() {
		return right;
	}
    public void setLeft(User left) {
		this.left = left;
	}
    public ArrayList<Trophy> getTrophies() {
		return trophies;
	}
    public void setRight(User right) {
		this.right = right;
	}
    void setGames(GameList games) {
    	this.games=games;
    }
    void addGames(Game game) {
    	this.games.addGame(game);
    }
    void setTrophies(ArrayList<Trophy> trophies) {
    	this.trophies=trophies;
    }
    public void setDob(Calendar dob) {
		this.dob = dob;
	}
    public void setKey(double key) {
		this.key = key;
	}
    public void setLevel(int level) {
		this.level = level;
	}
    public void setUsername(String username) {
		this.username = username;
	}
    public GameList getGames() {
		return games;
	}
    int getPlat() {
    	String string = "Platinum";
    	int c =0;
    	for (int i = 0; i < trophies.size(); i++) {
    		if (trophies.get(i).getName()==string) {
				c++;
			}
    		
		}
    	return c;
    }
    String printTrophies(){
	   StringBuilder strBuilder = new StringBuilder();
	   for (int i = 0; i < trophies.size(); i++) 
		strBuilder.append(trophies.get(i).toString()+"\n");
	   
	   return strBuilder.toString();
	
   }
    String printGames(){
 	   StringBuilder strBuilder = new StringBuilder();
 	   for (int i = 0; i < trophies.size(); i++) 
 		strBuilder.append(trophies.get(i).toString()+"\n");
 	   
 	   return strBuilder.toString();
 	
    }

    public void setHeight(int height) {
		this.height = height;
	}
    public int getHeight() {
		return height;
	}
    /**
     * DO NOT MODIFY THIS METHOD
     * This method uses the username and level to create a unique key.
     * As we don't want the username's hash to increase the level, it's first converted
     * to a floating point, then added to the level.
     * @return the unique key
     */
    public double calculateKey() {
        int hash = Math.abs(username.hashCode());
        // Calculate number of zeros we need
        int length = (int)(Math.log10(hash) + 1);
        // Make a divisor 10^x
        double divisor = Math.pow(10, length);
        // Return level.hash
        return level + hash / divisor;
    }

    public String toString() {
    	SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
    	String rtnStr = "User: "+username+"\n" +
                "\n" +
                "Trophies: \n" +
                printTrophies()+
                "\nGames: \n" +
                games.printList() +
                "\nBirth Date: "+sdf.format(dob.getTime());
        return rtnStr;
    }
}

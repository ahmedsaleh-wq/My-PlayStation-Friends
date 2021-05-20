import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Class to represent a PlayStation game trophy. A trophy comes in
 * four ranks: bronze, silver, gold and platinum. The date the trophy was
 * earned and its respective game is also stored.
 * Created for Data Structures & Algorithms, UPEI
 * @author James Baumeister
 * @version 1.0
 */
public class Trophy {
	
    public enum Rank {BRONZE, SILVER, GOLD, PLATINUM}
    public enum Rarity {COMMON, UNCOMMON, RARE, VERY_RARE, ULTRA_RARE}
    String name;
    Rank rank;
    Rarity rarity;
    Calendar obtained;
    Game game;
    public Trophy() {
    	name = "";
    	rank=null;
    	rarity=null;
    	obtained=null;
    	game=null;
    }

    public Trophy(String name, Rank rank, Rarity rarity, Calendar obtained, Game game) {
    	this.name= name;
    	this.rank = rank;
    	this.rarity=rarity;
    	this.obtained=obtained;
    	this.game=game;
    }
    String getName() {
    	return name;
    }
    Rank getRank() {
    	return rank;
    }
    Rarity getRarity() {
    	return rarity;
    }
    Calendar getObtained() {
    	return obtained;
    }
    Game getGame() {
    	return 	game;
    }
    
    public String toString() {
    	SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        return "\""+name+"\", rank: "+rank+", " +
                "rarity: "+rarity+", obtained on: "+ sdf.format(obtained.getTime());
    }
}

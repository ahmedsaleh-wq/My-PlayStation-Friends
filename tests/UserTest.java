import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class UserTest {
    private String username;
    private int level;
    private ArrayList<Trophy> trophies;
    private Game g1;
    private Game g2;
    private Game g3;
    private GameList games;
    private Calendar dob;

    @BeforeEach
    public void init() {
        username = "lewdster"; // Honestly, it's the first name that came up on the name generator!
        level = 4;
        trophies = new ArrayList<Trophy>(); {
            trophies.add(new Trophy("Whole game, no kills", Trophy.Rank.GOLD, Trophy.Rarity.ULTRA_RARE,
                    new GregorianCalendar(2015, 12, 25), new Game("Fallout 4", new GregorianCalendar(2015, 11, 10), 10)));
            trophies.add(new Trophy("Big dragon, you killed it", Trophy.Rank.SILVER, Trophy.Rarity.UNCOMMON,
                    new GregorianCalendar(2014, 5, 15), new Game("Dragon Age: Inquisition", new GregorianCalendar(2014, 3, 13), 10)));
            trophies.add(new Trophy("How did you manage to get this on PS4?!", Trophy.Rank.PLATINUM, Trophy.Rarity.ULTRA_RARE,
                    new GregorianCalendar(2016, 1, 21), new Game("Halo 5", new GregorianCalendar(2016, 1, 21), 10)));
            trophies.add(new Trophy("Watched a cutscene, here's a trophy", Trophy.Rank.BRONZE, Trophy.Rarity.COMMON,
                    new GregorianCalendar(2015, 12, 15), new Game("Lego Star Wars", new GregorianCalendar(2015, 8, 5), 10)));
        }
        g1 = new Game("Assassin's Creed IV: Black Flag", new GregorianCalendar(2013, 10, 29), 10);
        g2 = new Game("Child of Light", new GregorianCalendar(2014, 4, 1), 10);
        g3 = new Game("Dragon Age: Inquisition", new GregorianCalendar(2014, 11, 18), 10);
        games = new GameList(g1);
        g1.setNext(g2);
        g2.setNext(g3);
        dob = new GregorianCalendar(1980, 5, 16);
    }

    @Test
    public void testConstructor() {
        try {
            float marks = 2f;
            AssignmentMarker.marks_failed.put("testConstructor", marks);
            User user = new User(username, dob, level);

            assertEquals(username, user.getUsername(), "Checking that the username was correctly set");
            assertEquals(dob, user.getDob(), "Checking that the birth date was correctly set");
            assertEquals(level, user.getLevel(), "Checking that the level was correctly set");
            assertEquals(4.210369182, user.getKey(), 0.00001, "Checking that the key was correctly calculated");
            AssignmentMarker.marks_success.put("testConstructor", marks);
            AssignmentMarker.marks_failed.remove("testConstructor");
        }
        catch (Exception e) {
            fail("Checking that the User constructor does not throw any exceptions");
        }
    }

    @Test
    public void toStringTest() {
        float marks = 3f;
        AssignmentMarker.marks_failed.put("toStringTest", marks);
        User user = new User(username, dob, level);
        user.setGames(games);
        user.setTrophies(trophies);

        String rtnStr = "User: lewdster\n" +
                "\n" +
                "Trophies: \n" +
                "\"Whole game, no kills\", rank: GOLD, rarity: ULTRA_RARE, obtained on: Jan 25, 2016\n" +
                "\"Big dragon, you killed it\", rank: SILVER, rarity: UNCOMMON, obtained on: Jun 15, 2014\n" +
                "\"How did you manage to get this on PS4?!\", rank: PLATINUM, rarity: ULTRA_RARE, obtained on: Feb 21, " +
                "2016\n" +
                "\"Watched a cutscene, here's a trophy\", rank: BRONZE, rarity: COMMON, obtained on: Jan 15, 2016\n" +
                "\n" +
                "Games: \n" +
                "\"Assassin's Creed IV: Black Flag\", released on: Nov 29, 2013\n" +
                "\"Child of Light\", released on: May 01, 2014\n" +
                "\"Dragon Age: Inquisition\", released on: Dec 18, 2014\n" +
                "\n" +
                "Birth Date: Jun 16, 1980";

        assertEquals(rtnStr, user.toString(), "Checking that toString returns the correct String");
        AssignmentMarker.marks_success.put("toStringTest", marks);
        AssignmentMarker.marks_failed.remove("toStringTest");
    }
}

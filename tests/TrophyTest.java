import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TrophyTest {
    private String name;
    private Trophy.Rank rank;
    private Trophy.Rarity rarity;
    private Calendar obtained;
    private Game game;

    @BeforeEach
    public void init() {
        name = "What Did You Call Me?";
        rank = Trophy.Rank.BRONZE;
        rarity = Trophy.Rarity.RARE;
        obtained = new GregorianCalendar(2014, 4, 4);
        game = new Game("inFamous: Second Son", new GregorianCalendar(2014, 3, 21), 48);
    }

    @Test
    public void testConstructor() {
        try {
            float marks = 2f;
            AssignmentMarker.marks_failed.put("testConstructor", marks);
            Trophy t = new Trophy(name, rank, rarity, obtained, game);

            assertEquals(name, t.getName(), "Checking that the name was correctly set");
            assertEquals(rank, t.getRank(), "Checking that the rank was correctly set");
            assertEquals(rarity, t.getRarity(), "Checking that the rarity was correctly set");
            assertEquals(obtained, t.getObtained(), "Checking that the obtained date was correctly set");
            assertEquals(game, t.getGame(), "Checking that the parent game was correctly set");
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
        Trophy t = new Trophy(name, rank, rarity, obtained, game);
        assertEquals("\"What Did You Call Me?\", rank: BRONZE, " +
                "rarity: RARE, obtained on: May 04, 2014", t.toString(),
                "Checking that tostring returns the correct string");
        AssignmentMarker.marks_success.put("toStringTest", marks);
        AssignmentMarker.marks_failed.remove("toStringTest");
    }
}

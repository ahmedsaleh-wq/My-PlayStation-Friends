import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class GameTest {
    private String name;
    private Calendar released;
    private int totalTrophies;

    @BeforeEach
    public void init() {
        name = "Assassin's Creed IV: Black Flag";
        released = new GregorianCalendar(2013, 10, 29);
        totalTrophies = 14;
    }

    @Test
    public void testConstructor() {
        try {
            float marks = 2f;
            AssignmentMarker.marks_failed.put("testConstructor", marks);
            Game g = new Game(name, released, totalTrophies);

            assertEquals(name, g.getName(), "Checking that the name has been correctly set");
            assertEquals(released, g.getReleased(), "Checking that the release date has been correctly set");
            assertEquals(totalTrophies, g.getTotalTrophies(), "Checking that the total trophies has been correctly set");
            AssignmentMarker.marks_success.put("testConstructor", marks);
            AssignmentMarker.marks_failed.remove("testConstructor");
        }
        catch (Exception e) {
            fail("Checking that the Game constructor does not throw any exceptions");
        }
    }

    @Test
    public void toStringTest() {
        float marks = 3f;
        AssignmentMarker.marks_failed.put("toStringTest", marks);
        Game g = new Game(name, released, totalTrophies);

        assertEquals("\"Assassin's Creed IV: Black Flag\", released on: Nov 29, 2013", g.toString(), "Checking that toString returns the correct String");
        AssignmentMarker.marks_success.put("toStringTest", marks);
        AssignmentMarker.marks_failed.remove("toStringTest");
    }
}

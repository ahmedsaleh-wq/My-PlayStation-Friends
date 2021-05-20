import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class GameListTest {
    private Game g1;
    private Game g2;
    private Game g3;
    private GameList gl;

    @BeforeEach
    public void init() {
        g1 = new Game("Assassin's Creed IV: Black Flag", new GregorianCalendar(2013, 10, 29), 10);
        g2 = new Game("Child of Light", new GregorianCalendar(2014, 4, 1), 24);
        g3 = new Game("Dragon Age: Inquisition", new GregorianCalendar(2014, 11, 18), 53);
        gl = new GameList(null);
    }

    @Test
    public void getGameNullArg() {
        float marks = 1f;
        AssignmentMarker.marks_failed.put("getGameNullArg", marks);
        try {
            gl.getGame(null);

            fail("Checking that getGame throws an IllegalArgumentException when null arg supplied");
        }
        catch (IllegalArgumentException ignored) {
            AssignmentMarker.marks_success.put("getGameNullArg", marks);
            AssignmentMarker.marks_failed.remove("getGameNullArg");
        }
    }

    @Test
    public void getGame() {
        float marks = 2f;
        AssignmentMarker.marks_failed.put("getGame", marks);
        gl.head = g1;
        g1.setNext(g2);
        g2.setNext(g3);

        assertEquals(g1, gl.getGame("Assassin's Creed IV: Black Flag"), "Checking that getGame returns the correct Database.Game");
        assertEquals(g3, gl.getGame("Dragon Age: Inquisition"), "Checking that getGame returns the correct Database.Game");
        assertNull(gl.getGame("Far Cry 4"), "Checking that getGame returns null if game not present");
        AssignmentMarker.marks_success.put("getGame", marks);
        AssignmentMarker.marks_failed.remove("getGame");
    }

    @Test
    public void addGame() {
        float marks = 2f;
        AssignmentMarker.marks_failed.put("addGame", marks);
        gl.addGame(g2);
        gl.addGame(g1);

        assertEquals(g2, gl.head, "Checking that the head is correctly set");
        assertEquals(g1, g2.getNext(), "Checking next set correctly");
        assertNull(g1.getNext(), "Checking next set correctly");
        AssignmentMarker.marks_success.put("addGame", marks);
        AssignmentMarker.marks_failed.remove("addGame");
    }

    @Test
    public void addGameExists() {
        float marks = 1f;
        AssignmentMarker.marks_failed.put("addGameExists", marks);
        gl.addGame(g1);
        gl.addGame(g1);

        assertNull(g1.getNext(), "Checking that a duplicate game is not added");
        AssignmentMarker.marks_success.put("addGameExists", marks);
        AssignmentMarker.marks_failed.remove("addGameExists");
    }

    @Test
    public void addGameNullArg() {
        float marks = 1f;
        AssignmentMarker.marks_failed.put("addGameNullArg", marks);
        try {
            gl.addGame(null);

            fail("Checking that addGame throws an IllegalArgumentException when null arg supplied");
        }
        catch (IllegalArgumentException ignored) {
            AssignmentMarker.marks_success.put("addGameNullArg", marks);
            AssignmentMarker.marks_failed.remove("addGameNullArg");
        }
    }

    @Test
    public void removeGameNullArg() {
        float marks = 1f;
        AssignmentMarker.marks_failed.put("removeGameNullArg", marks);
        try {
            String str = null;
            gl.removeGame(str);
            Game g = null;
            gl.removeGame(g);

            fail("Checking that both removeGame methods throw IllegalArgumentException when null args are supplied");
        }
        catch (IllegalArgumentException ignored) {
            AssignmentMarker.marks_success.put("removeGameNullArg", marks);
            AssignmentMarker.marks_failed.remove("removeGameNullArg");
        }
    }

    @Test
    public void removeGameString() {
        float marks = 2f;
        AssignmentMarker.marks_failed.put("removeGameString", marks);
        gl.head = g1;
        g1.setNext(g2);
        g2.setNext(g3);

        gl.removeGame("Crash Bandicoot");
        assertEquals(g1, gl.head, "Checking that a non-existant name does not alter the list (1)");
        assertEquals(g2, gl.head.getNext(), "Checking that a non-existant name does not alter the list (2)");
        assertEquals(g3, gl.head.getNext().getNext(), "Checking that a non-existant name does not alter the list (3)");
        assertNull(gl.head.getNext().getNext().getNext(), "Checking that a non-existant name does not alter the list (4)");

        gl.removeGame(g1.getName());
        assertEquals(g2, gl.head, "Checking that the head is correctly replaced");

        g3.setNext(g1);
        g1.setNext(null);
        gl.removeGame(g3.getName());
        assertEquals(g1, g2.getNext(), "Checking that middle game removed correctly");

        gl.removeGame(g1.getName());
        assertNull(g2.getNext(), "Checking that last game correctly removed");
        AssignmentMarker.marks_success.put("removeGameString", marks);
        AssignmentMarker.marks_failed.remove("removeGameString");
    }

    @Test
    public void removeGameObject() {
        float marks = 2f;
        AssignmentMarker.marks_failed.put("removeGameObject", marks);
        gl.head = g1;
        g1.setNext(g2);
        g2.setNext(g3);

        gl.removeGame(new Game("Far Cry 4", new GregorianCalendar(2014, 11, 18), 47));
        assertEquals(g1, gl.head, "Checking that a non-existant name does not alter the list (1)");
        assertEquals(g2, gl.head.getNext(), "Checking that a non-existant name does not alter the list (2)");
        assertEquals(g3, gl.head.getNext().getNext(), "Checking that a non-existant name does not alter the list (3)");
        assertNull(gl.head.getNext().getNext().getNext(), "Checking that a non-existant name does not alter the list (4)");

        gl.removeGame(g1);
        assertEquals(g2, gl.head, "Checking that the head is correctly replaced");

        g3.setNext(g1);
        g1.setNext(null);
        gl.removeGame(g3);
        assertEquals(g1, g2.getNext(), "Checking that middle game removed correctly");

        gl.removeGame(g1);
        assertNull(g2.getNext(), "Checking that last game correctly removed");
        AssignmentMarker.marks_success.put("removeGameObject", marks);
        AssignmentMarker.marks_failed.remove("removeGameObject");
    }

    @Test
    public void toStringTest() {
        float marks = 3f;
        AssignmentMarker.marks_failed.put("toStringTest", marks);
        assertEquals(gl.toString(), "Empty game list", "Testing that toString with an empty list returns the correct String");

        gl.head = g1;
        g1.setNext(g2);

        assertEquals("\"Assassin's " +
                "Creed IV: " +
                "Black Flag\", released on: Nov 29, 2013\n" + "\"Child of Light\", released on: May 01, 2014", gl.toString(),
                "Testing that toString returns the correct String");
        AssignmentMarker.marks_success.put("toStringTest", marks);
        AssignmentMarker.marks_failed.remove("toStringTest");
    }
}

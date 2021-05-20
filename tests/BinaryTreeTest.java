import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryTreeTest {
    public BinaryTree bt;

    // Start testing setup (see assignment spec for tree diagrams)
    Game massEffect, persona, yooka, yooka2, prey, prey2, prey3, crash, crash2;
    GameList gl1, gl2, gl3, gl4;

    Calendar c;
    Trophy massEffectT1, massEffectT2, personaT1, personaT2, personaT3, yookaT1, preyT1, preyT2, preyT3, preyT4, preyT5,
            crashT1, crashT2;

    ArrayList<Trophy> tl1, tl2, tl3, tl4, tl5;

    Calendar dob;

    User oberon, pippin, medraut, astaroth, lunete, guiomar, faust, sophronia, haidee, dulcinea, cosette, nelida;

    /*
     * There's a LOT of setting up to do here. Refer to the assignment spec for
     * a diagram explaining how this tree is constructed.
     */
    @BeforeEach
    public void init() {
        massEffect = new Game("Mass Effect Andromeda", new GregorianCalendar(2017, 3, 21), 56);
        persona = new Game("Persona 5", new GregorianCalendar(2017, 4, 4), 49);
        yooka = new Game("Yooka-Laylee", new GregorianCalendar(2017, 4, 11), 36);
        yooka2 = new Game("Yooka-Laylee", new GregorianCalendar(2017, 4, 11), 36);
        prey = new Game("Prey", new GregorianCalendar(2017, 5, 5), 50);
        prey2 = new Game("Prey", new GregorianCalendar(2017, 5, 5), 50);
        prey3 = new Game("Prey", new GregorianCalendar(2017, 5, 5), 50);
        crash = new Game("Crash Bandicoot N. Sane Trilogy", new GregorianCalendar(2017, 6, 30), 88);
        crash2 = new Game("Crash Bandicoot N. Sane Trilogy", new GregorianCalendar(2017, 6, 30), 88);

        gl1 = new GameList(yooka); {
            gl1.addGame(massEffect);
            gl1.addGame(persona);
        }
        gl2 = new GameList(prey); {
            gl2.addGame(crash);
        }
        gl3 = new GameList(prey2);
        gl4 = new GameList(prey3); {
            gl4.addGame(crash2);
            gl4.addGame(yooka2);
        }

        c = new GregorianCalendar(2017, 2, 26); // Trophies can have the same date - we don't care for these tests

        massEffectT1 = new Trophy("Shady Trees", Trophy.Rank.GOLD, Trophy.Rarity.ULTRA_RARE, c, massEffect);
        massEffectT2 = new Trophy("Keep", Trophy.Rank.SILVER, Trophy.Rarity.RARE, c, massEffect);
        personaT1 = new Trophy("War Never Changes", Trophy.Rank.BRONZE, Trophy.Rarity.COMMON, c, persona);
        personaT2 = new Trophy("The Nuclear Option", Trophy.Rank.SILVER, Trophy.Rarity.UNCOMMON, c, persona);
        personaT3 = new Trophy("Prepared for the Future", Trophy.Rank.GOLD, Trophy.Rarity.UNCOMMON, c, persona);
        yookaT1 = new Trophy("Platinum", Trophy.Rank.PLATINUM, Trophy.Rarity.ULTRA_RARE, c, yooka);
        preyT1 = new Trophy("Xenonaut", Trophy.Rank.BRONZE, Trophy.Rarity.RARE, c, prey);
        preyT2 = new Trophy("Walked the Path", Trophy.Rank.GOLD, Trophy.Rarity.VERY_RARE, c, prey);
        preyT3 = new Trophy("Humpty Dumpty", Trophy.Rank.BRONZE, Trophy.Rarity.VERY_RARE, c, prey);
        preyT4 = new Trophy("Can't Touch This!", Trophy.Rank.BRONZE, Trophy.Rarity.RARE, c, prey);
        preyT5 = new Trophy("Overkill", Trophy.Rank.SILVER, Trophy.Rarity.RARE, c, prey);
        crashT1 = new Trophy("Expert Fortune Hunter", Trophy.Rank.BRONZE, Trophy.Rarity.UNCOMMON, c, crash);
        crashT2 = new Trophy("Platinum", Trophy.Rank.PLATINUM, Trophy.Rarity.ULTRA_RARE, c, crash);

        tl1 = new ArrayList<Trophy>(); {
            tl1.add(yookaT1);
            tl1.add(personaT1);
            tl1.add(massEffectT2);
        }
        tl2 = new ArrayList<Trophy>(); {
            tl2.add(personaT1);
            tl2.add(personaT2);
            tl2.add(personaT3);
            tl2.add(massEffectT2);
        }
        tl3 = new ArrayList<Trophy>(); {
            tl3.add(crashT1);
            tl3.add(crashT2);
            tl3.add(preyT4);
            tl3.add(preyT3);
            tl3.add(preyT1);
        }
        tl4 = new ArrayList<Trophy>(); {
            tl4.add(preyT1);
            tl4.add(preyT2);
            tl4.add(preyT5);
        }
        tl5 = new ArrayList<Trophy>(); {
            tl5.add(crashT2);
            tl5.add(yookaT1);
        }

        dob = new GregorianCalendar(1980, 4, 23); // Users can have the same dob - we don't care for these tests

        oberon = new User("Oberon", dob, 7);
        pippin = new User("Pippin", dob, 10);
        medraut = new User("Medraut", dob, 8);
        astaroth = new User("Astaroth", dob, 9);
        lunete = new User("Lunete", dob, 12);
        guiomar = new User("Guiomar", dob, 14);
        faust = new User("Faust", dob, 4);
        sophronia = new User("Sophronia", dob, 6);
        haidee = new User("Haidee", dob, 5);
        cosette = new User("Cosette", dob, 3);
        dulcinea = new User("Dulcinea", dob, 3);
        nelida = new User("Nelida", dob, 1);

        oberon.setGames(gl1);
        oberon.setTrophies(tl1);
        oberon.setLeft(faust);
        oberon.setRight(pippin);


        faust.setGames(gl2);
        faust.setTrophies(tl3);
        faust.setLeft(cosette);
        faust.setRight(sophronia);


        cosette.setGames(gl3);
        cosette.setTrophies(tl4);
        cosette.setLeft(nelida);
        cosette.setRight(dulcinea);


        nelida.setGames(gl3);
        nelida.setTrophies(tl4);


        dulcinea.setGames(gl1);
        dulcinea.setTrophies(tl1);


        sophronia.setGames(gl2);
        sophronia.setTrophies(tl3);
        sophronia.setLeft(haidee);


        haidee.setGames(gl3);
        haidee.setTrophies(tl4);


        pippin.setGames(gl1);
        pippin.setTrophies(tl2);
        pippin.setLeft(medraut);
        pippin.setRight(lunete);


        medraut.setGames(gl4);
        medraut.setTrophies(tl5);
        medraut.setRight(astaroth);


        astaroth.setGames(gl2);
        astaroth.setTrophies(tl3);


        lunete.setGames(gl1);
        lunete.setTrophies(tl1);
        lunete.setRight(guiomar);


        guiomar.setGames(gl2);
        guiomar.setTrophies(tl4);


        bt = new BinaryTree();
        bt.root = oberon;
    }

    @Test
    public void beFriendNullArg() {
        float marks = 1f;
        AssignmentMarker.marks_failed.put("beFriendNullArg", marks);
        try {
            bt.beFriend(null);

            fail("Checking that beFriend throws an IllegalArgumentException when null arg supplied");
        }
        catch (IllegalArgumentException ignored) {
            AssignmentMarker.marks_success.put("beFriendNullArg", marks);
            AssignmentMarker.marks_failed.remove("beFriendNullArg");
        }
    }

    @Test
    public void beFriendDuplicate() {
        float marks = 1f;
        AssignmentMarker.marks_failed.put("beFriendDuplicate", marks);
        assertFalse(bt.beFriend(sophronia), "Checking that beFriend returns false when user already in tree (1)");
        AssignmentMarker.marks_success.put("beFriendDuplicate", marks);
        AssignmentMarker.marks_failed.remove("beFriendDuplicate");
    }

    @Test
    public void beFriend() {
        float marks = 6f;
        AssignmentMarker.marks_failed.put("beFriend", marks);
        BinaryTree emptyBT = new BinaryTree();
        User bob = new User("bob", dob, 6);
        bob.setGames(gl1);
        bob.setTrophies(tl1);
        assertTrue(emptyBT.beFriend(bob), "Checking that beFriend returns true when adding to an empty tree");
        assertEquals(bob, emptyBT.root, "Checking that the root has been correctly set");

        assertTrue(bt.beFriend(bob), "Checking that beFriend returns true when adding a new user (1)");
        assertEquals(bob, sophronia.getRight(), "Checking that Sophronia's right variable correctly set");

        sophronia.setRight(null);
        bob = new User("bpb", dob, 7);
        bob.setGames(gl1);
        bob.setTrophies(tl1);
        assertTrue(bt.beFriend(bob), "Checking that beFriend returns true when adding a new user (2)");
        assertEquals(bob, medraut.getLeft(), "Checking that Medrauts's left variable correctly set");
        AssignmentMarker.marks_success.put("beFriend", marks);
        AssignmentMarker.marks_failed.remove("beFriend");
    }

    @Test
    public void deFriendNullArg() {
        float marks = 1f;
        AssignmentMarker.marks_failed.put("deFriendNullArg", marks);
        try {
            bt.deFriend(null);

            fail("Checking that deFriend throws an IllegalArgumentException when null arg supplied");
        }
        catch (IllegalArgumentException ignored) {
            AssignmentMarker.marks_success.put("deFriendNullArg", marks);
            AssignmentMarker.marks_failed.remove("deFriendNullArg");
        }
    }

    @Test
    public void deFriendNonExistent() {
        float marks = 1f;
        AssignmentMarker.marks_failed.put("deFriendNonExistent", marks);
        User bob = new User("bob", dob, 6);
        bob.setGames(gl1);
        bob.setTrophies(tl1);
        assertFalse(bt.deFriend(bob), "Checking that deFriend returns false when user not in tree");
        AssignmentMarker.marks_success.put("deFriendNonExistent", marks);
        AssignmentMarker.marks_failed.remove("deFriendNonExistent");
    }

    @Test
    public void deFriend() {
        float marks = 7f;
        AssignmentMarker.marks_failed.put("deFriend", marks);
        assertTrue(bt.deFriend(astaroth), "Checking that deFriend returns true when removing a user (1)");
        assertNull(medraut.getRight(), "Checking that Medraut's right variable correctly set");

        assertTrue(bt.deFriend(haidee), "Checking that deFriend returns true when removing a user (2)");
        assertNull(sophronia.getLeft(), "Checking that Sophronia's left variable correctly set");

        assertTrue(bt.deFriend(faust), "Checking that deFriend returns true when removing a user (3)");
        assertEquals(dulcinea.getKey(), oberon.getLeft().getKey(), 0.01, "Checking that Oberon's right correctly set (1)");
        assertEquals(dulcinea.getUsername(), oberon.getLeft().getUsername(), "Checking that Oberon's right correctly set (2)");
        assertEquals(dulcinea.getTrophies(), oberon.getLeft().getTrophies(), "Checking that Oberon's right correctly set (3)");

        assertTrue(bt.deFriend(oberon), "Checking that deFriend returns true when removing the root");
        assertEquals(sophronia.getKey(), bt.root.getKey(), 0.01, "Checking that Sophronia is the new root");
        assertEquals(sophronia.getUsername(), bt.root.getUsername(), "Checking that Sophronia is the new root");
        assertEquals(sophronia.getTrophies(), bt.root.getTrophies(), "Checking that Sophronia is the new root");
        AssignmentMarker.marks_success.put("deFriend", marks);
        AssignmentMarker.marks_failed.remove("deFriend");
    }

    @Test
    public void countBetterPlayersNullArg() {
        float marks = 1f;
        AssignmentMarker.marks_failed.put("countBetterPlayersNullArg", marks);
        try {
            bt.countBetterPlayers(null);

            fail("Checking that deFriend throws an IllegalArgumentException when null arg supplied");
        }
        catch (IllegalArgumentException ignored) {
            AssignmentMarker.marks_success.put("countBetterPlayersNullArg", marks);
            AssignmentMarker.marks_failed.remove("countBetterPlayersNullArg");
        }
    }

    @Test
    public void countBetterPlayersNonExistent() {
        float marks = 1f;
        AssignmentMarker.marks_failed.put("countBetterPlayersNonExistent", marks);
        User bob = new User("bob", dob, 6);
        bob.setGames(gl1);
        bob.setTrophies(tl1);
        assertEquals(-1, bt.countBetterPlayers(bob), "Checking that countBetterPlayers returns -1 when user not in tree");
        AssignmentMarker.marks_success.put("countBetterPlayersNonExistent", marks);
        AssignmentMarker.marks_failed.remove("countBetterPlayersNonExistent");
    }

    @Test
    public void countBetterPlayers() {
        float marks = 4f;
        AssignmentMarker.marks_failed.put("countBetterPlayers", marks);

        assertEquals(11, bt.countBetterPlayers(nelida), "Checking that countBetterPlayers returns the correct number (1)");     
        assertEquals(0, bt.countBetterPlayers(guiomar), "Checking that countBetterPlayers returns the correct number (2)");
        assertEquals(6, bt.countBetterPlayers(sophronia), "Checking that countBetterPlayers returns the correct number (3)");
        assertEquals(3, bt.countBetterPlayers(astaroth), "Checking that countBetterPlayers returns the correct number (4)");

        User bob = new User("aaaaaa", dob, 7);
        bob.setGames(gl1);
        bob.setTrophies(tl1);
        sophronia.setRight(bob);
        assertEquals(6, bt.countBetterPlayers(bob), "Checking that countBetterPlayers handles same-level users correctly");
        AssignmentMarker.marks_success.put("countBetterPlayers", marks);
        AssignmentMarker.marks_failed.remove("countBetterPlayers");
    }

    @Test
    public void countWorsePlayersNullArg() {
        float marks = 1f;
        AssignmentMarker.marks_failed.put("countWorsePlayersNullArg", marks);
        try {
            bt.countWorsePlayers(null);

            fail("Checking that countWorsePlayers throws an IllegalArgumentException when null arg supplied");
        }
        catch (IllegalArgumentException ignored) {
            AssignmentMarker.marks_success.put("countWorsePlayersNullArg", marks);
            AssignmentMarker.marks_failed.remove("countWorsePlayersNullArg");
        }
    }

    @Test
    public void countWorsePlayersNonExistent() {
        float marks = 1f;
        AssignmentMarker.marks_failed.put("countWorsePlayersNonExistent", marks);
        User bob = new User("bob", dob, 6);
        bob.setGames(gl1);
        bob.setTrophies(tl1);
        assertEquals(-1, bt.countWorsePlayers(bob), "Checking that countWorsePlayers returns -1 when user not in tree");
        AssignmentMarker.marks_success.put("countWorsePlayersNonExistent", marks);
        AssignmentMarker.marks_failed.remove("countWorsePlayersNonExistent");
    }

    @Test
    public void countWorsePlayers() {
        float marks = 4f;
        AssignmentMarker.marks_failed.put("countWorsePlayers", marks);
        
        assertEquals(0, bt.countWorsePlayers(nelida), "Checking that countWorsePlayers returns the correct number (1)");
        assertEquals(11, bt.countWorsePlayers(guiomar), "Checking that countWorsePlayers returns the correct number (2)");
        assertEquals(5, bt.countWorsePlayers(sophronia), "Checking that countWorsePlayers returns the correct number (3)");
        assertEquals(8, bt.countWorsePlayers(astaroth), "Checking that countWorsePlayers returns the correct number (4)");

        User bob = new User("bob", dob, 6);
        bob.setGames(gl1);
        bob.setTrophies(tl1);
        sophronia.setRight(bob);
        assertEquals(6, bt.countWorsePlayers(bob), "Checking that countWorsePlayers handles same-level users correctly");
        AssignmentMarker.marks_success.put("countWorsePlayers", marks);
        AssignmentMarker.marks_failed.remove("countWorsePlayers");
    }

    @Test
    public void mostPlatinums() {
        float marks = 4f;
        AssignmentMarker.marks_failed.put("mostPlatinums", marks);
        assertEquals(medraut, bt.mostPlatinums(), "Checking that mostPlatinums returns the correct user");

        ArrayList<Trophy> tl6 = new ArrayList<Trophy>(); {
            tl6.add(crashT2);
            tl6.add(yookaT1);
            tl6.add(preyT2);
        }
        pippin.setTrophies(tl6);

        assertEquals(pippin, bt.mostPlatinums(), "Checking that mostPlatinums correctly resolves ties");
        AssignmentMarker.marks_success.put("mostPlatinums", marks);
        AssignmentMarker.marks_failed.remove("mostPlatinums");
    }

    @Test
    public void addGameNullArg() {
        float marks = 1f;
        AssignmentMarker.marks_failed.put("addGameNullArg", marks);
        try {
            bt.addGame(null, null);

            fail("Checking that addGame throws an IllegalArgumentException when null arg supplied");
        }
        catch (IllegalArgumentException ignored) {
        }
        try {
            bt.addGame("bob", null);

            fail("Checking that addGame throws an IllegalArgumentException when null arg supplied");
        }
        catch (IllegalArgumentException ignored) {
        }
        try {
            bt.addGame(null, prey);

            fail("Checking that addGame throws an IllegalArgumentException when null arg supplied");
        }
        catch (IllegalArgumentException ignored) {
        }
        AssignmentMarker.marks_success.put("addGameNullArg", marks);
        AssignmentMarker.marks_failed.remove("addGameNullArg");
    }

    @Test
    public void addGame() {
        float marks = 4f;
        AssignmentMarker.marks_failed.put("addGame", marks);
        Game cod3 = new Game("Call of Duty: Black Ops III", new GregorianCalendar(2015, 11, 6), 48);
        bt.addGame("Sophronia", cod3);

        assertEquals(prey.toString(), sophronia.getGames().head.toString(), "Checking that addGame correctly adds a game");
        assertEquals(crash.toString(), sophronia.getGames().head.getNext().toString(), "Checking that addGame correctly adds a game");
        assertEquals(cod3, sophronia.getGames().head.getNext().getNext(), "Checking that addGame correctly adds a game");
        AssignmentMarker.marks_success.put("addGame", marks);
        AssignmentMarker.marks_failed.remove("addGame");
    }

    @Test
    public void addTrophyNullArg() {
        float marks = 1f;
        AssignmentMarker.marks_failed.put("addTrophyNullArg", marks);
        try {
            bt.addTrophy(null, null);

            fail("Checking that addTrophy throws an IllegalArgumentException when null arg supplied");
        }
        catch (IllegalArgumentException ignored) {
        }
        try {
            bt.addTrophy("bob", null);

            fail("Checking that addTrophy throws an IllegalArgumentException when null arg supplied");
        }
        catch (IllegalArgumentException ignored) {
        }
        try {
            bt.addTrophy(null, preyT1);

            fail("Checking that addTrophy throws an IllegalArgumentException when null arg supplied");
        }
        catch (IllegalArgumentException ignored) {
        }
        AssignmentMarker.marks_success.put("addTrophyNullArg", marks);
        AssignmentMarker.marks_failed.remove("addTrophyNullArg");
    }

    @Test
    public void addTrophy() {
        float marks = 4f;
        AssignmentMarker.marks_failed.put("addTrophy", marks);
        bt.addTrophy("Astaroth", preyT2);

        assertEquals(crashT1, astaroth.getTrophies().get(0), "Checking that addTrophy correctly adds a trophy");
        assertEquals(crashT2, astaroth.getTrophies().get(1), "Checking that addTrophy correctly adds a trophy");
        assertEquals(preyT4, astaroth.getTrophies().get(2), "Checking that addTrophy correctly adds a trophy");
        assertEquals(preyT3, astaroth.getTrophies().get(3), "Checking that addTrophy correctly adds a trophy");
        assertEquals(preyT1, astaroth.getTrophies().get(4), "Checking that addTrophy correctly adds a trophy");
        assertEquals(preyT2, astaroth.getTrophies().get(5), "Checking that addTrophy correctly adds a trophy");
        AssignmentMarker.marks_success.put("addTrophy", marks);
        AssignmentMarker.marks_failed.remove("addTrophy");
    }

    @Test
    public void levelUpNullArgs() {
        float marks = 1f;
        AssignmentMarker.marks_failed.put("levelUpNullArgs", marks);
        try {
            bt.levelUp(null);

            fail("Checking that levelUp throws IllegalArgumentException when null args are supplied");
        }
        catch (IllegalArgumentException ignored) {
            AssignmentMarker.marks_success.put("levelUpNullArgs", marks);
            AssignmentMarker.marks_failed.remove("levelUpNullArgs");
        }
    }

    @Test
    public void levelUp() {
        float marks = 7f;
        AssignmentMarker.marks_failed.put("levelUp", marks);
        bt.levelUp("Sophronia");
        assertEquals("Oberon", bt.root.getUsername(), "Checking that levelUp correctly modifies tree (1)");

        bt.levelUp("Oberon");
        assertEquals("Sophronia", bt.root.getUsername(), "Checking that levelUp correctly modifies tree (2)");
        AssignmentMarker.marks_success.put("levelUp", marks);
        AssignmentMarker.marks_failed.remove("levelUp");
    }

    @Test
    public void toStringTest() {
        float marks = 3f;
        AssignmentMarker.marks_failed.put("toStringTest", marks);
        System.out.println(outputString);
        assertEquals(outputString, bt.toString(), "Checking that toString returns the correct String");
        AssignmentMarker.marks_success.put("toStringTest", marks);
        AssignmentMarker.marks_failed.remove("toStringTest");
    }

    // Ridiculously long return string
    private final String outputString = "User: Nelida\n" +
            "\n" +
            "Trophies: \n" +
            "\"Xenonaut\", rank: BRONZE, rarity: RARE, obtained on: Mar 26, 2017\n" +
            "\"Walked the Path\", rank: GOLD, rarity: VERY_RARE, obtained on: Mar 26, 2017\n" +
            "\"Overkill\", rank: SILVER, rarity: RARE, obtained on: Mar 26, 2017\n" +
            "\n" +
            "Games: \n" +
            "\"Prey\", released on: Jun 05, 2017\n" +
            "\n" +
            "Birth Date: May 23, 1980\n" +
            "User: Cosette\n" +
            "\n" +
            "Trophies: \n" +
            "\"Xenonaut\", rank: BRONZE, rarity: RARE, obtained on: Mar 26, 2017\n" +
            "\"Walked the Path\", rank: GOLD, rarity: VERY_RARE, obtained on: Mar 26, 2017\n" +
            "\"Overkill\", rank: SILVER, rarity: RARE, obtained on: Mar 26, 2017\n" +
            "\n" +
            "Games: \n" +
            "\"Prey\", released on: Jun 05, 2017\n" +
            "\n" +
            "Birth Date: May 23, 1980\n" +
            "User: Dulcinea\n" +
            "\n" +
            "Trophies: \n" +
            "\"Platinum\", rank: PLATINUM, rarity: ULTRA_RARE, obtained on: Mar 26, 2017\n" +
            "\"War Never Changes\", rank: BRONZE, rarity: COMMON, obtained on: Mar 26, 2017\n" +
            "\"Keep\", rank: SILVER, rarity: RARE, obtained on: Mar 26, 2017\n" +
            "\n" +
            "Games: \n" +
            "\"Yooka-Laylee\", released on: May 11, 2017\n" +
            "\"Mass Effect Andromeda\", released on: Apr 21, 2017\n" +
            "\"Persona 5\", released on: May 04, 2017\n" +
            "\n" +
            "Birth Date: May 23, 1980\n" +
            "User: Faust\n" +
            "\n" +
            "Trophies: \n" +
            "\"Expert Fortune Hunter\", rank: BRONZE, rarity: UNCOMMON, obtained on: Mar 26, 2017\n" +
            "\"Platinum\", rank: PLATINUM, rarity: ULTRA_RARE, obtained on: Mar 26, 2017\n" +
            "\"Can't Touch This!\", rank: BRONZE, rarity: RARE, obtained on: Mar 26, 2017\n" +
            "\"Humpty Dumpty\", rank: BRONZE, rarity: VERY_RARE, obtained on: Mar 26, 2017\n" +
            "\"Xenonaut\", rank: BRONZE, rarity: RARE, obtained on: Mar 26, 2017\n" +
            "\n" +
            "Games: \n" +
            "\"Prey\", released on: Jun 05, 2017\n" +
            "\"Crash Bandicoot N. Sane Trilogy\", released on: Jul 30, 2017\n" +
            "\n" +
            "Birth Date: May 23, 1980\n" +
            "User: Haidee\n" +
            "\n" +
            "Trophies: \n" +
            "\"Xenonaut\", rank: BRONZE, rarity: RARE, obtained on: Mar 26, 2017\n" +
            "\"Walked the Path\", rank: GOLD, rarity: VERY_RARE, obtained on: Mar 26, 2017\n" +
            "\"Overkill\", rank: SILVER, rarity: RARE, obtained on: Mar 26, 2017\n" +
            "\n" +
            "Games: \n" +
            "\"Prey\", released on: Jun 05, 2017\n" +
            "\n" +
            "Birth Date: May 23, 1980\n" +
            "User: Sophronia\n" +
            "\n" +
            "Trophies: \n" +
            "\"Expert Fortune Hunter\", rank: BRONZE, rarity: UNCOMMON, obtained on: Mar 26, 2017\n" +
            "\"Platinum\", rank: PLATINUM, rarity: ULTRA_RARE, obtained on: Mar 26, 2017\n" +
            "\"Can't Touch This!\", rank: BRONZE, rarity: RARE, obtained on: Mar 26, 2017\n" +
            "\"Humpty Dumpty\", rank: BRONZE, rarity: VERY_RARE, obtained on: Mar 26, 2017\n" +
            "\"Xenonaut\", rank: BRONZE, rarity: RARE, obtained on: Mar 26, 2017\n" +
            "\n" +
            "Games: \n" +
            "\"Prey\", released on: Jun 05, 2017\n" +
            "\"Crash Bandicoot N. Sane Trilogy\", released on: Jul 30, 2017\n" +
            "\n" +
            "Birth Date: May 23, 1980\n" +
            "User: Oberon\n" +
            "\n" +
            "Trophies: \n" +
            "\"Platinum\", rank: PLATINUM, rarity: ULTRA_RARE, obtained on: Mar 26, 2017\n" +
            "\"War Never Changes\", rank: BRONZE, rarity: COMMON, obtained on: Mar 26, 2017\n" +
            "\"Keep\", rank: SILVER, rarity: RARE, obtained on: Mar 26, 2017\n" +
            "\n" +
            "Games: \n" +
            "\"Yooka-Laylee\", released on: May 11, 2017\n" +
            "\"Mass Effect Andromeda\", released on: Apr 21, 2017\n" +
            "\"Persona 5\", released on: May 04, 2017\n" +
            "\n" +
            "Birth Date: May 23, 1980\n" +
            "User: Medraut\n" +
            "\n" +
            "Trophies: \n" +
            "\"Platinum\", rank: PLATINUM, rarity: ULTRA_RARE, obtained on: Mar 26, 2017\n" +
            "\"Platinum\", rank: PLATINUM, rarity: ULTRA_RARE, obtained on: Mar 26, 2017\n" +
            "\n" +
            "Games: \n" +
            "\"Prey\", released on: Jun 05, 2017\n" +
            "\"Crash Bandicoot N. Sane Trilogy\", released on: Jul 30, 2017\n" +
            "\"Yooka-Laylee\", released on: May 11, 2017\n" +
            "\n" +
            "Birth Date: May 23, 1980\n" +
            "User: Astaroth\n" +
            "\n" +
            "Trophies: \n" +
            "\"Expert Fortune Hunter\", rank: BRONZE, rarity: UNCOMMON, obtained on: Mar 26, 2017\n" +
            "\"Platinum\", rank: PLATINUM, rarity: ULTRA_RARE, obtained on: Mar 26, 2017\n" +
            "\"Can't Touch This!\", rank: BRONZE, rarity: RARE, obtained on: Mar 26, 2017\n" +
            "\"Humpty Dumpty\", rank: BRONZE, rarity: VERY_RARE, obtained on: Mar 26, 2017\n" +
            "\"Xenonaut\", rank: BRONZE, rarity: RARE, obtained on: Mar 26, 2017\n" +
            "\n" +
            "Games: \n" +
            "\"Prey\", released on: Jun 05, 2017\n" +
            "\"Crash Bandicoot N. Sane Trilogy\", released on: Jul 30, 2017\n" +
            "\n" +
            "Birth Date: May 23, 1980\n" +
            "User: Pippin\n" +
            "\n" +
            "Trophies: \n" +
            "\"War Never Changes\", rank: BRONZE, rarity: COMMON, obtained on: Mar 26, 2017\n" +
            "\"The Nuclear Option\", rank: SILVER, rarity: UNCOMMON, obtained on: Mar 26, 2017\n" +
            "\"Prepared for the Future\", rank: GOLD, rarity: UNCOMMON, obtained on: Mar 26, 2017\n" +
            "\"Keep\", rank: SILVER, rarity: RARE, obtained on: Mar 26, 2017\n" +
            "\n" +
            "Games: \n" +
            "\"Yooka-Laylee\", released on: May 11, 2017\n" +
            "\"Mass Effect Andromeda\", released on: Apr 21, 2017\n" +
            "\"Persona 5\", released on: May 04, 2017\n" +
            "\n" +
            "Birth Date: May 23, 1980\n" +
            "User: Lunete\n" +
            "\n" +
            "Trophies: \n" +
            "\"Platinum\", rank: PLATINUM, rarity: ULTRA_RARE, obtained on: Mar 26, 2017\n" +
            "\"War Never Changes\", rank: BRONZE, rarity: COMMON, obtained on: Mar 26, 2017\n" +
            "\"Keep\", rank: SILVER, rarity: RARE, obtained on: Mar 26, 2017\n" +
            "\n" +
            "Games: \n" +
            "\"Yooka-Laylee\", released on: May 11, 2017\n" +
            "\"Mass Effect Andromeda\", released on: Apr 21, 2017\n" +
            "\"Persona 5\", released on: May 04, 2017\n" +
            "\n" +
            "Birth Date: May 23, 1980\n" +
            "User: Guiomar\n" +
            "\n" +
            "Trophies: \n" +
            "\"Xenonaut\", rank: BRONZE, rarity: RARE, obtained on: Mar 26, 2017\n" +
            "\"Walked the Path\", rank: GOLD, rarity: VERY_RARE, obtained on: Mar 26, 2017\n" +
            "\"Overkill\", rank: SILVER, rarity: RARE, obtained on: Mar 26, 2017\n" +
            "\n" +
            "Games: \n" +
            "\"Prey\", released on: Jun 05, 2017\n" +
            "\"Crash Bandicoot N. Sane Trilogy\", released on: Jul 30, 2017\n" +
            "\n" +
            "Birth Date: May 23, 1980";
}

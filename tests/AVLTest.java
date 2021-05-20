import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AVLTest {
    private BinaryTree bt;
    private User oberon, pippin, medraut, astaroth, lunete, guiomar, faust, sophronia, haidee, dulcinea, cosette, nelida;
    private Calendar dob;

    @BeforeEach
    public void init() {
        bt = new BinaryTree();
        dob = new GregorianCalendar(1980, 4, 23); // Users can have the same dob - we don't care for these tests
        Game massEffect = new Game("Mass Effect Andromeda", new GregorianCalendar(2017, 3, 21), 56);
        Calendar c = new GregorianCalendar(2016, 1, 28); // Trophies can have the same date - we don't care for these tests
        Trophy massEffectT1 = new Trophy("Shady Trees", Trophy.Rank.GOLD, Trophy.Rarity.ULTRA_RARE, c, massEffect);

        GameList gl = new GameList(massEffect);
        ArrayList<Trophy> tl = new ArrayList<Trophy>(); tl.add(massEffectT1);

        oberon = new User("Oberon", dob, 7); oberon.setGames(gl); oberon.setTrophies(tl);
        pippin = new User("Pippin", dob, 10); pippin.setGames(gl); pippin.setTrophies(tl);
        medraut = new User("Medraut", dob, 8); medraut.setGames(gl); medraut.setTrophies(tl);
        astaroth = new User("Astaroth", dob, 9); astaroth.setGames(gl); astaroth.setTrophies(tl);
        lunete = new User("Lunete", dob, 12); lunete.setGames(gl); lunete.setTrophies(tl);
        guiomar = new User("Guiomar", dob, 14); guiomar.setGames(gl); guiomar.setTrophies(tl);
        faust = new User("Faust", dob, 4); faust.setGames(gl); faust.setTrophies(tl);
        sophronia = new User("Sophronia", dob, 6); sophronia.setGames(gl); sophronia.setTrophies(tl);
        haidee = new User("Haidee", dob, 5); haidee.setGames(gl); haidee.setTrophies(tl);
        cosette = new User("Cosette", dob, 3); cosette.setGames(gl); cosette.setTrophies(tl);
        dulcinea = new User("Dulcinea", dob, 3); dulcinea.setGames(gl); dulcinea.setTrophies(tl);
        nelida = new User("Nelida", dob, 1); nelida.setGames(gl); nelida.setTrophies(tl);
    }

    @Test
    public void addAVLStage1() {
        float marks = 6f;
        AssignmentMarker.marks_failed.put("addAVLStage1", marks);
        bt.addAVL(nelida);
        bt.addAVL(cosette);
        bt.addAVL(dulcinea);

        assertEquals(cosette, bt.root, "Check root after rotation");
        assertEquals(nelida, bt.root.getLeft(), "Check root left");
        assertEquals(dulcinea, bt.root.getRight(), "Check root right");

        bt.addAVL(faust);
        bt.addAVL(haidee);

        assertEquals(cosette, bt.root, "Check root after rotation");
        assertEquals(nelida, bt.root.getLeft(), "Check root left");
        assertEquals(faust, bt.root.getRight(), "Check root right");
        assertEquals(dulcinea, faust.getLeft(), "Check Faust's left");
        assertEquals(haidee, faust.getRight(), "Check Faust's right");

        bt.addAVL(sophronia);

        assertEquals(faust, bt.root, "Check new root");
        assertEquals(cosette, bt.root.getLeft(), "Check root left");
        assertEquals(haidee, bt.root.getRight(), "Check root right");
        assertEquals(dulcinea, cosette.getRight(), "Check that Faust's old left subtree moved");
        AssignmentMarker.marks_success.put("addAVLStage1", marks);
        AssignmentMarker.marks_failed.remove("addAVLStage1");
    }

    @Test
    public void addAVLStage2() {
        float marks = 5f;
        AssignmentMarker.marks_failed.put("addAVLStage2", marks);
        // Stage 1 adds
        bt.addAVL(nelida);
        bt.addAVL(cosette);
        bt.addAVL(dulcinea);
        bt.addAVL(faust);
        bt.addAVL(haidee);
        bt.addAVL(sophronia);

        bt.addAVL(oberon);

        assertEquals(sophronia, bt.root.getRight(), "Check root right");
        assertNull(haidee.getRight(), "Check Haidee's right");
        assertEquals(haidee, sophronia.getLeft(), "Check Sophronia's left");

        bt.addAVL(medraut);
        bt.addAVL(astaroth);

        assertNull(oberon.getRight(), "Check Oberon's right");
        assertEquals(oberon, medraut.getLeft(), "Check Medraut's left");
        assertEquals(astaroth, medraut.getRight(), "Check Medraut's right");

        bt.addAVL(pippin);

        assertEquals(pippin, astaroth.getRight(), "Check Astaroth's right");
        assertEquals(medraut, bt.root.getRight(), "Check root right");
        assertEquals(oberon, sophronia.getRight(), "Check that Medraut's old left subtree moved");
        assertEquals(sophronia, medraut.getLeft(), "Check Medraut's left");
        assertEquals(astaroth, medraut.getRight(), "Check Medraut's right");
        AssignmentMarker.marks_success.put("addAVLStage2", marks);
        AssignmentMarker.marks_failed.remove("addAVLStage2");
    }

    @Test
    public void addAVLStage3() {
        float marks = 5f;
        AssignmentMarker.marks_failed.put("addAVLStage3", marks);
        // Stage 1 adds
        bt.addAVL(nelida);
        bt.addAVL(cosette);
        bt.addAVL(dulcinea);
        bt.addAVL(faust);
        bt.addAVL(haidee);
        bt.addAVL(sophronia);

        // Stage 2 adds
        bt.addAVL(oberon);
        bt.addAVL(medraut);
        bt.addAVL(astaroth);
        bt.addAVL(pippin);

        bt.addAVL(lunete);

        assertEquals(lunete, pippin.getRight(), "Check Pippin's right");
        assertEquals(pippin, medraut.getRight(), "Check Medraut's right");
        assertEquals(astaroth, pippin.getLeft(), "Check Pippin's left");
        assertNull(astaroth.getLeft(), "Check Astaroth's left");
        assertNull(astaroth.getRight(), "Check Astaroth's right");

        bt.addAVL(guiomar);

        assertEquals(medraut, bt.root, "Check root");
        assertEquals(faust, medraut.getLeft(), "Check Medraut's left");
        assertEquals(pippin, medraut.getRight(), "Check Medraut's right");
        assertEquals(cosette, faust.getLeft(), "Check Faust's left");
        assertEquals(sophronia, faust.getRight(), "Check Faust's right");
        assertEquals(oberon, sophronia.getRight(), "Check Sophronia's right");
        AssignmentMarker.marks_success.put("addAVLStage3", marks);
        AssignmentMarker.marks_failed.remove("addAVLStage3");
    }
}

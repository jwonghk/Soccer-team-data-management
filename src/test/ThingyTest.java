import model.Thingy;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ThingyTest {

    JSONObject p3Profile;

    @BeforeEach
    void runBefore() {
//        JSONObject p1Profile = new JSONObject();
//        JSONObject p2Profile = new JSONObject();
//        Thingy thingy1 = new Thingy("P1", p1Profile);
//        Thingy thingy2 = new Thingy("P2", p2Profile);
        p3Profile = new JSONObject();



    }

    @Test
    public void testConstructorAndMethodsInThingy() throws Exception {
        p3Profile.put("gameWon", 4);
        p3Profile.put("gameLost", 5);
        p3Profile.put("goals", 5);
        p3Profile.put("conceals", 5);
        p3Profile.put("position", "striker");
        Thingy thingy3 = new Thingy("P3", p3Profile);
        thingy3.setAllFieldsOfPlayer(3,3,3,3,"midfield");
        JSONObject profileObject = thingy3.returnPlayerProfile();
        assertTrue(thingy3.getThingName().equals("P3"));
        assertTrue(thingy3.returnPlayerProfile().equals(profileObject));

        try {
            thingy3.setGoals(-5);
            fail("Should have caught an exception");
        } catch (Exception exc) {
            System.out.println("Good that an exception is caught");
        };

        try {
            thingy3.setConceal(-5);
            fail("Should have caught an exception");
        } catch (Exception exc) {
            System.out.println("Good that an exception is caught");
        };

        try {
            thingy3.setGameWon(-5);
            fail("Should have caught an exception");
        } catch (Exception exc) {
            System.out.println("Good that an exception is caught");
        };

        try {
            thingy3.setGameLost(-5);
            fail("Should have caught an exception");
        } catch (Exception exc) {
            System.out.println("Good that an exception is caught");
        };

        try {
            thingy3.setPosition("Striker"); // has to be small letter
            fail("Should have caught an exception");
        } catch (Exception exc) {
            System.out.println("Good that an exception is caught");
        };

        try {
            thingy3.setPosition("striker"); // valid entry
        } catch (Exception exc) {
            fail("It is a valid entry, no excpetion should be caught");
        };

        try {
            thingy3.setPosition("defender"); // valid entry
        } catch (Exception exc) {
            fail("It is a valid entry, no excpetion should be caught");
        };

        try {
            thingy3.setPosition("goal keeper"); // valid entry
        } catch (Exception exc) {
            fail("It is a valid entry, no excpetion should be caught");
        };





        thingy3.setGoals(5);
        thingy3.setConceal(15);
        thingy3.setGameWon(25);
        thingy3.setGameLost(35);
        thingy3.setPosition("striker");
        assertEquals(thingy3.getThingName(),"P3");
        assertEquals(thingy3.getGoals(),5);
        assertEquals(thingy3.getConceal(),15);
        assertEquals(thingy3.getGameWon(),25);
        assertEquals(thingy3.getGameLost(),35);
        assertEquals(thingy3.getPosition(),"striker");

        JSONObject fortestingtoJson = new JSONObject();
        fortestingtoJson = thingy3.toJson();
        assertTrue(fortestingtoJson instanceof JSONObject);

        thingy3.setName("Simon");
        assertTrue(thingy3.getThingName().equals("Simon"));

    }





}

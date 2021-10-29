import model.Thingy;
import model.WorkRoom;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WorkRoomTest {


    @Test
    public void testWorkRoom() throws Exception{
        JSONObject p1Profile = new JSONObject();
        JSONObject p2Profile = new JSONObject();
        Thingy thingy1;
        Thingy thingy2;
        thingy1 = new Thingy("P1", p1Profile);
        thingy2 = new Thingy( "P2", p2Profile);
        thingy1.setAllFieldsOfPlayer(1,2,3,4,"midfield");
        thingy2.setAllFieldsOfPlayer(5,6,7,8,"striker");

        WorkRoom soccerRoom= new WorkRoom("SoccerPlayerSquadRoom");
        assertTrue(soccerRoom.getName().equals("SoccerPlayerSquadRoom"));
        List<Thingy> twoPlayersThingyList = new ArrayList<Thingy>();
        twoPlayersThingyList.add(thingy1);
        twoPlayersThingyList.add(thingy2);
        soccerRoom.addThingy(thingy1);
        soccerRoom.addThingy(thingy2);
        assertTrue(soccerRoom.getThingies().equals(twoPlayersThingyList));

        JSONObject p3Profile = new JSONObject();
        JSONObject p4Profile = new JSONObject();
        Thingy thingy3;
        Thingy thingy4;
        thingy3 = new Thingy("P3", p3Profile);
        thingy4 = new Thingy( "P4", p4Profile);
        thingy3.setAllFieldsOfPlayer(10,11,12,13,"defender");
        thingy4.setAllFieldsOfPlayer(14,15,17,18,"defender");
        List<Thingy> NewtwoPlayersThingyList = new ArrayList<Thingy>();
        NewtwoPlayersThingyList.add(thingy3);
        NewtwoPlayersThingyList.add(thingy4);
        soccerRoom.setThingies(NewtwoPlayersThingyList);
        assertTrue(soccerRoom.getThingies().equals(NewtwoPlayersThingyList));
        assertEquals(soccerRoom.getThingies().size(),2);
        assertEquals(soccerRoom.numThingies(), 2);

        assertTrue(soccerRoom.toJson() instanceof JSONObject);




    }

}

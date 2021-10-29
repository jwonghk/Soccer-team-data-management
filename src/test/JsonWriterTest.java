import model.Thingy;
import model.WorkRoom;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() throws Exception{
        try {
            WorkRoom wr = new WorkRoom("to test invalid file workroom");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() throws Exception{
        try {
            WorkRoom wr = new WorkRoom("An Empty workroom");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            wr = reader.read();
            assertEquals("An Empty workroom", wr.getName());
            assertEquals(0, wr.numThingies());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() throws Exception{
        try {
            JSONObject player1Profile = new JSONObject();
            JSONObject player2Profile = new JSONObject();
            player1Profile.put("goals", 3);
            player1Profile.put("conceals", 5);
            player1Profile.put("gameWon", 7);
            player1Profile.put("gameLost", 9);
            player1Profile.put("position", "striker");

            player2Profile.put("goals", 2);
            player2Profile.put("conceals", 4);
            player2Profile.put("gameWon", 6);
            player2Profile.put("gameLost", 8);
            player2Profile.put("position", "defender");

            WorkRoom wr = new WorkRoom("My Soccer room");
            wr.addThingy(new Thingy("Player1", player1Profile));
            wr.addThingy(new Thingy("Player2", player2Profile));

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            wr = reader.read();
            assertEquals("My Soccer room", wr.getName());
            List<Thingy> thingies = wr.getThingies();
            assertEquals(2, thingies.size());


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }





}

package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ManageInformation {


    private WorkRoom workRoom;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private List<Thingy> thingyList;
    private String roomOwnerName;
    private String jsonDataFileName;

    private Scanner input;

    private String name;
    private Integer goals;
    private Integer conceal;
    private Integer gameWon;
    private Integer gameLost;
    private String position;


    public ManageInformation(String roomOwnerName, Boolean haveBeenHere)  {

        jsonDataFileName = "./data/" + roomOwnerName + "workroom.json";
        jsonWriter = new JsonWriter(jsonDataFileName);
        jsonReader = new JsonReader(jsonDataFileName);
        if (haveBeenHere) {
            loadWorkRoom();
        }

    }

    // EFFECTS: return the workRoom
    public WorkRoom getWorkRoom() {
        return this.workRoom;
    }

    // EFFECTS: set the WorkRoom
    public void setWorkRoom(WorkRoom managerworkRoom) {
        this.workRoom = managerworkRoom;
    }


    // MODIFIES: this
    // EFFECTS: loads workroom from file
    protected void loadWorkRoom() {
        try {
            workRoom = jsonReader.read();
            thingyList = workRoom.getThingies();
            System.out.println("Loaded " + workRoom.getName() + " from " + jsonDataFileName);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + jsonDataFileName);
        }
    }

    // EFFECTS: saves the workroom to file
    protected void saveWorkRoom() {
        try {
            jsonWriter.open();
            jsonWriter.write(this.workRoom);
            jsonWriter.close();
            System.out.println("Saved " + workRoom.getName() + " to " + jsonDataFileName);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + jsonDataFileName);
        }
    }


}

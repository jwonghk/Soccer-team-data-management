package ui;

import model.*;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


// This class is to manage information collected by gatherinformation class
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


    // EFFECT: a constructor of ManageInformation class
    public ManageInformation(String roomOwnerName, Boolean haveBeenHere, GatherInformation gat)  {

        jsonDataFileName = "./data/" + roomOwnerName + "workroom.json";
        jsonWriter = new JsonWriter(jsonDataFileName);
        jsonReader = new JsonReader(jsonDataFileName);
        if (haveBeenHere) {
            loadWorkRoom();
        } else if (!haveBeenHere) {
            gat.returnJTextArea().setText("Welcome to using our application. \n"
                    + "A file has been created for you since you are new user!");
            WorkRoom workRoom = new WorkRoom(roomOwnerName);
            this.workRoom = workRoom;
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
    public void saveWorkRoom() {
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

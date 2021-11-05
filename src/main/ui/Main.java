package ui;

import java.io.FileNotFoundException;


// citation: codes here are borrowed or modified based on the demo application
//          from CPSC 210
//   https://edge.edx.org/courses/course-v1:UBC+CPSC210+all/courseware
public class Main {
    public static void main(String[] args) {
        try {
            //PlayerApp playerApp = new PlayerApp();
            new GatherInformation();

        } catch (FileNotFoundException fle) {
            System.out.println("Unable to run application: file not found");
        }
    }
}

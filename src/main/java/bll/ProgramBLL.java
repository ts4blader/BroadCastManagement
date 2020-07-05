package bll;

import entities.Program;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProgramBLL {

    public static ObservableList<Program> getAllProgram(){
        ObservableList programs = FXCollections.observableArrayList(
                new Program("PR001", "West Journey", "HOR", "UNI"),
                new Program("PR002", "Call Of Duty:WWII","DEC","UNI"));

        return programs;
    }

    public static void update(Program program){
    }

    public static void add(Program program){

    }

    public static Program getProgramById(String id){

        ObservableList<Program> programs = getAllProgram();
        for( Program program : programs){
            if(program.getId().equals(id)) return program;
        }

        return null;

    }
}

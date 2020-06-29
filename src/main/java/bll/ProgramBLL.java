package bll;

import entities.Program;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProgramBLL {

    public static ObservableList<Program> getAllProgram(){
        ObservableList west_journey = FXCollections.observableArrayList(new Program("PR001", "West Journey", "HOR", "UNI"));
        return west_journey;
    }

    public static void update(Program program){
    }

    public static void add(Program program){

    }
}

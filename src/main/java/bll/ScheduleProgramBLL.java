package bll;

import entities.ScheduleProgram;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalTime;
import java.util.ArrayList;

public class ScheduleProgramBLL {

    public static ObservableList<ScheduleProgram> getAllSchedule(){

        ArrayList<Boolean> arrayList = new ArrayList<>();
        for (int i = 0; i < 7 ; i++)
            if(i % 2 == 0) arrayList.add(true);
            else arrayList.add(false);

        ScheduleProgram scheduleProgram = new ScheduleProgram("PR001",
                "THVL",
                LocalTime.of(7,30),
                arrayList,
                45,
                15
                );

        return FXCollections.observableArrayList(scheduleProgram);
    }

}

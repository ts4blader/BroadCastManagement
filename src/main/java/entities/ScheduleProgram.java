package entities;

import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalTime;
import java.util.ArrayList;

public class ScheduleProgram {
    private String programID;
    private String channelID;
    private LocalTime time;
    private ArrayList<Boolean> dayOfWeek;
    private int duration;
    private int chapter;


    public ScheduleProgram(String programID, String channelID, LocalTime time, ArrayList<Boolean> dayOfWeek, int duration, int chapter) {
        this.programID = programID;
        this.channelID = channelID;
        this.time = time;
        this.dayOfWeek = dayOfWeek;
        this.duration = duration;
        this.chapter = chapter;
    }

    public String getProgramID() {
        return programID;
    }

    public void setProgramID(String programID) {
        this.programID = programID;
    }

    public String getChannelID() {
        return channelID;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public SimpleObjectProperty<LocalTime> getTimeProperty(){
        return new SimpleObjectProperty<>(time);
    }

    public SimpleObjectProperty<ArrayList<Boolean>> getDayOfWeekProperty(){
        return new SimpleObjectProperty<>(dayOfWeek);
    }

    public ArrayList<Boolean> getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(ArrayList<Boolean> dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public SimpleObjectProperty<Boolean> getDayOfWeekProperty(int index){
        return new SimpleObjectProperty<>(dayOfWeek.get(index));
    }
}

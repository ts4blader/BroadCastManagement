package entities;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;

public class Channel {
    private String id;
    private String name;
    private boolean isCenter;
    private LocalDate beginDate;
    private String city;


    @Override
    public String toString() {
        return this.getName();
    }

    public Channel(){
        this.name = "";
    }

    public Channel(String id, String name, boolean isCenter, LocalDate beginDate, String city) {
        this.id = id;
        this.name = name;
        this.isCenter = isCenter;
        this.beginDate = beginDate;
        this.city = city;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCenter() {
        return isCenter;
    }

    public void setCenter(boolean center) {
        isCenter = center;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public SimpleObjectProperty<LocalDate> getBeginDateProperty(){

        return new SimpleObjectProperty<>(beginDate );

    }
}

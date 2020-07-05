package entities;


import bll.NationBLL;
import javafx.beans.property.SimpleObjectProperty;

public class Producer {
    private String id;
    private String name;
    private String nationID;

    public String toString(){
        return this.name;
    }

    public Producer(){
        this.name = "";
    }

    public Producer(String id, String name, String nation) {
        this.id = id;
        this.name = name;
        this.nationID = nation;
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

    public String getNationID() {
        return nationID;
    }

    public void setNationID(String nationID) {
        this.nationID = nationID;
    }

    public SimpleObjectProperty<Nation> getNationProperty(){

        return new SimpleObjectProperty<>(NationBLL.getNationById(getNationID()));

    }

}

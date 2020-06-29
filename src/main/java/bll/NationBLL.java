package bll;

import entities.Nation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NationBLL {

    public static ObservableList<Nation> getAllNation(){

        Nation china = new Nation("CHI","China");
        Nation india = new Nation("IND","India");
        Nation usa = new Nation("USA","United State America");

        return FXCollections.observableArrayList(china, india,usa);

    }

    public static Nation getNationById(String id){

        ObservableList<Nation> nations = getAllNation();

        for ( Nation nation : nations){
            if(nation.getId().equals(id)) return nation;
        }

        return null;
    }
}

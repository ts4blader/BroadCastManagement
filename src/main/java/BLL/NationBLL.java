package BLL;

import Enities.Nation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NationBLL {

    public static ObservableList<Nation> getAllNation(){

        Nation china = new Nation("CHI","China");
        Nation india = new Nation("IND","India");
        Nation usa = new Nation("USA","United State America");

        return FXCollections.observableArrayList(china, india,usa);

    }


}

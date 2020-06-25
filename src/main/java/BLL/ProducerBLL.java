package BLL;

import Enities.Nation;
import Enities.Producer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProducerBLL {

    public static ObservableList<Producer> getAllProducer(){

        ObservableList<Nation> nations = NationBLL.getAllNation();

        Producer hollyWood = new Producer("HLW","HollyWood", nations.get(0).getId());
        Producer bollyWood = new Producer("BLW","BoollyWood", nations.get(1).getId());
        Producer univeser = new Producer("UNI","Univeser", nations.get(2).getId());

        return FXCollections.observableArrayList(hollyWood, bollyWood, univeser);
    }

    public static Producer getProByID(String ID){

        ObservableList<Producer> producers = getAllProducer();

        for (Producer pro : producers){
            if(pro.getId().equals(ID)) return pro;
        }

        return null;
    }

}

package bll;

import entities.Channel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Date;

public class ChannelBLL {

    public static ObservableList<Channel> getAllChannel(){

        Channel c1 = new Channel("THVL", "Truyen Hinh Vinh Long",true, new Date(10/7/2020), "Vinh Long");
        Channel c2 = new Channel("VTV1", "VietNam TV 1",false, new Date(2/7/2003), "Ha Noi");
        Channel c3 = new Channel("CN", "Cartoon",true, new Date(12/3/1999), "London");

        return FXCollections.observableArrayList(c1, c2, c3);
    }

    public static void add(Channel channel){

    }

}

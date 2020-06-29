package bll;

import entities.Channel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.Date;

public class ChannelBLL {

    public static ObservableList<Channel> getAllChannel(){


        Channel c1 = new Channel("THVL",
                "Truyen Hinh Vinh Long",
                true,
                LocalDate.of(2010, 7, 12),
                "Vinh Long");
        Channel c2 = new Channel("VTV1", "VietNam TV 1",false, LocalDate.of(1999, 7, 4) , "Ha Noi");
        Channel c3 = new Channel("CN", "Cartoon",true, LocalDate.of(2001, 2, 21), "London");

        return FXCollections.observableArrayList(c1, c2, c3);
    }

    public static void add(Channel channel){

    }

    public static void update(Channel channel){

    }

}

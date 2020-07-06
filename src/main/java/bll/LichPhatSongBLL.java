package bll;

import dao.ChuongTrinhDAO;
import dao.LichPhatSongDAO;
import dto.ChuongTrinh;
import dto.LichPhatSong;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utilities.MyDialog;

import java.time.LocalTime;
import java.util.ArrayList;

public class LichPhatSongBLL {

    private static LichPhatSongDAO dao = LichPhatSongDAO.getInstance();

    public static ObservableList<LichPhatSong> getAll(){
        return FXCollections.observableArrayList(dao.getAll());
    }

    public static LichPhatSong get(String id){

        return dao.getByID(id);

    }

    public static ObservableList<LichPhatSong> get(LichPhatSong lichPS){

        return FXCollections.observableArrayList(dao.getByObj(lichPS));
    }

    public static void save(LichPhatSong lichPS){
        if(!dao.save(lichPS))
            MyDialog.showDialog("Save Error",null,"Can't Save",MyDialog.ERRO);
    }

    public static void delete(LichPhatSong lichPS){
        if(!dao.delete(lichPS))
            MyDialog.showDialog("Delete Error",null,"Can't Delete",MyDialog.ERRO);
    }

    public static void update(LichPhatSong lichPS){
        if(!dao.update(lichPS))
            MyDialog.showDialog("Update Error",null,"Can't Update",MyDialog.ERRO);
    }

    public static int getRowNumber(){
        return dao.getNumberOfRows();
    }
}

package bll;

import dao.ChuongTrinhDAO;
import dao.KenhTVDAO;
import dto.ChuongTrinh;
import dto.KenhTV;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utilities.MyDialog;

import java.time.LocalDate;

public class KenhTVBLL {

    private static KenhTVDAO dao = KenhTVDAO.getInstance();

    public static ObservableList<KenhTV> getAll(){
        return FXCollections.observableArrayList(dao.getAll());
    }

    public static KenhTV get(String id){

        return dao.getByID(id);

    }

    public static ObservableList<KenhTV> get(KenhTV kenhTV){

        kenhTV.setMaKenh("");
        return FXCollections.observableArrayList(dao.getByObj(kenhTV));
    }

    public static void save(KenhTV kenhTV){
        if(!dao.save(kenhTV))
            MyDialog.showDialog("Save Error",null,"Can't Save",MyDialog.ERRO);
    }

    public static void delete(KenhTV kenhTV){
        if(!dao.delete(kenhTV))
            MyDialog.showDialog("Delete Error",null,"Can't Delete",MyDialog.ERRO);
    }

    public static void update(KenhTV kenhTV){
        if(!dao.update(kenhTV))
            MyDialog.showDialog("Update Error",null,"Can't Update",MyDialog.ERRO);
    }

    public static int getRowNumber(){
        return dao.getNumberOfRows();
    }
}

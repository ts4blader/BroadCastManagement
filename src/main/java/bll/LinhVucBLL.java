package bll;

import dao.ChuongTrinhDAO;
import dao.LinhVucDAO;
import dto.ChuongTrinh;
import dto.LinhVuc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utilities.MyDialog;

public class LinhVucBLL {
    private static LinhVucDAO dao = LinhVucDAO.getInstance();

    public static ObservableList<LinhVuc> getAll(){
        return FXCollections.observableArrayList(dao.getAll());
    }

    public static LinhVuc get(String id){

        return dao.getByID(id);

    }

    public static ObservableList<LinhVuc> get(LinhVuc linhVuc){

        return FXCollections.observableArrayList(dao.getByObj(linhVuc));
    }

    public static void save(LinhVuc linhVuc){
        if(!dao.save(linhVuc))
            MyDialog.showDialog("Save Error",null,"Can't Save",MyDialog.ERRO);
    }

    public static void delete(LinhVuc linhVuc){
        if(!dao.delete(linhVuc))
            MyDialog.showDialog("Delete Error",null,"Can't Delete",MyDialog.ERRO);
    }

    public static void update(LinhVuc linhVuc){
        if(!dao.update(linhVuc))
            MyDialog.showDialog("Update Error",null,"Can't Update",MyDialog.ERRO);
    }

    public static int getRowNumber(){
        return dao.getNumberOfRows();
    }
}

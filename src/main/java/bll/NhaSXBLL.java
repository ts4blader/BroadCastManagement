package bll;

import dao.ChuongTrinhDAO;
import dao.NhaSXDAO;
import dto.ChuongTrinh;
import dto.NhaSX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utilities.MyDialog;

public class NhaSXBLL {

    private static NhaSXDAO dao = NhaSXDAO.getInstance();

    public static ObservableList<NhaSX> getAll(){
        return FXCollections.observableArrayList(dao.getAll());
    }

    public static NhaSX get(String id){

        return dao.getByID(id);

    }

    public static ObservableList<NhaSX> get(NhaSX nhaSX){

        nhaSX.setMaNSX("");
        return FXCollections.observableArrayList(dao.getByObj(nhaSX));
    }

    public static void save(NhaSX nhaSX){
        if(!dao.save(nhaSX))
            MyDialog.showDialog("Save Error",null,"Can't Save",MyDialog.ERRO);
    }

    public static void delete(NhaSX nhaSX){
        if(!dao.delete(nhaSX))
            MyDialog.showDialog("Delete Error",null,"Can't Delete",MyDialog.ERRO);
    }

    public static void update(NhaSX nhaSX){
        if(!dao.update(nhaSX))
            MyDialog.showDialog("Update Error",null,"Can't Update",MyDialog.ERRO);
    }

    public static int getRowNumber(){
        return dao.getNumberOfRows();
    }
}

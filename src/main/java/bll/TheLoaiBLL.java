package bll;

import dao.TheLoaiDAO;
import dto.TheLoai;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utilities.MyDialog;

public class TheLoaiBLL {

    private static TheLoaiDAO dao = TheLoaiDAO.getInstance();

    public static ObservableList<TheLoai> getAll(){
        return FXCollections.observableArrayList(dao.getAll());
    }

    public static TheLoai get(String id){

        return dao.getByID(id);

    }

    public static ObservableList<TheLoai> get(TheLoai theLoai){

        theLoai.setMaTheLoai("");
        return FXCollections.observableArrayList(dao.getByObj(theLoai));
    }

    public static void save(TheLoai category){
        if(!dao.save(category))
            MyDialog.showDialog("Save Error",null,"Can't Save",MyDialog.ERRO);
    }

    public static void delete(TheLoai category){
        if(!dao.delete(category))
            MyDialog.showDialog("Delete Error",null,"Can't Delete",MyDialog.ERRO);
    }

    public static void update(TheLoai category){
        if(!dao.update(category))
            MyDialog.showDialog("Update Error",null,"Can't Update",MyDialog.ERRO);
    }

    public static int getRowNumber(){

        return dao.getNumberOfRows();

    }
}

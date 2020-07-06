package bll;

import dao.ChuongTrinhDAO;
import dao.QuocGiaDAO;
import dto.ChuongTrinh;
import dto.QuocGia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utilities.MyDialog;

public class QuocGiaBLL {

    private static QuocGiaDAO dao = QuocGiaDAO.getInstance();

    public static ObservableList<QuocGia> getAll(){
        return FXCollections.observableArrayList(dao.getAll());
    }

    public static QuocGia get(String id){

        return dao.getByID(id);

    }

    public static ObservableList<QuocGia> get(QuocGia quocGia){

        return FXCollections.observableArrayList(dao.getByObj(quocGia));
    }

    public static void save(QuocGia quocGia){
        if(!dao.save(quocGia))
            MyDialog.showDialog("Save Error",null,"Can't Save",MyDialog.ERRO);
    }

    public static void delete(QuocGia quocGia){
        if(!dao.delete(quocGia))
            MyDialog.showDialog("Delete Error",null,"Can't Delete",MyDialog.ERRO);
    }

    public static void update(QuocGia quocGia){
        if(!dao.update(quocGia))
            MyDialog.showDialog("Update Error",null,"Can't Update",MyDialog.ERRO);
    }

    public static int getRowNumber(){
        return dao.getNumberOfRows();
    }
}

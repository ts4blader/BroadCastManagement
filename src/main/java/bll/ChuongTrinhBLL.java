package bll;

import dao.ChuongTrinhDAO;
import dao.TheLoaiDAO;
import dto.ChuongTrinh;
import dto.TheLoai;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utilities.MyDialog;

public class ChuongTrinhBLL {

    private static ChuongTrinhDAO dao = ChuongTrinhDAO.getInstance();

    public static ObservableList<ChuongTrinh> getAll(){
        return FXCollections.observableArrayList(dao.getAll());
    }

    public static ChuongTrinh get(String id){

        return dao.getByID(id);

    }

    public static ObservableList<ChuongTrinh> get(ChuongTrinh chuongTrinh){

        return FXCollections.observableArrayList(dao.getByObj(chuongTrinh));
    }

    public static void save(ChuongTrinh chuongTrinh){
        if(!dao.save(chuongTrinh))
            MyDialog.showDialog("Save Error",null,"Can't Save",MyDialog.ERRO);
    }

    public static void delete(ChuongTrinh chuongTrinh){
        if(!dao.delete(chuongTrinh))
            MyDialog.showDialog("Delete Error",null,"Can't Delete",MyDialog.ERRO);
    }

    public static void update(ChuongTrinh chuongTrinh){
        if(!dao.update(chuongTrinh))
            MyDialog.showDialog("Update Error",null,"Can't Update",MyDialog.ERRO);
    }

    public static int getRowNumber(){
        return dao.getNumberOfRows();
    }
}

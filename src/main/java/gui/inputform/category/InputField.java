package gui.inputform.category;


import bll.TheLoaiBLL;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.TheLoai;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utilities.ImageGetter;
import utilities.MyDialog;
import utilities.MyLayout;

import java.util.Formatter;


public class InputField extends VBox {
    // Define const


    //Define var
    private static JFXTextField idField;

    private static JFXTextField nameField;

    private static HBox buttonBar;

    private static JFXButton addBtn;
    private static JFXButton clearBtn;
    private static JFXButton searchBtn;
    private static JFXButton updateBtn;


    public InputField() {

        idField = MyLayout.getTextField("ID");
        idField.setText(setAutoID());
        idField.setDisable(true);

        nameField = MyLayout.getTextField("Name");
        nameField.setOnAction(e -> save());
        MyLayout.setValidator(nameField, "Not match Requirement");


        //====================== Button Bar ======================

        createButtonBar();

        //================ main layout =============

        getMainLayout();

    }

    //====================== Controller Section ======================

    public void getMainLayout() {

        this.setPadding(new Insets(30));
        this.setSpacing(40);
        this.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().addAll(idField, nameField, buttonBar);

    }

    public void createButtonBar() {

        addBtn = MyLayout.getJFXButton("Add", ImageGetter.ADD, MyLayout.ICON_SIZE);
        addBtn.setOnAction( e -> save());

        clearBtn = MyLayout.getJFXButton("Clear", ImageGetter.CLEAR, MyLayout.ICON_SIZE);
        clearBtn.setOnAction( e -> clearInput());

        searchBtn = MyLayout.getJFXButton("Search", ImageGetter.SEARCH, MyLayout.ICON_SIZE);
        searchBtn.setOnAction(e -> search());

        updateBtn = MyLayout.getJFXButton("Update", ImageGetter.SAVE, MyLayout.ICON_SIZE);
        updateBtn.setOnAction(e -> update());
        //add to Button Bar
        buttonBar = new HBox(10);
        buttonBar.setAlignment(Pos.CENTER);
        buttonBar.getChildren().addAll(addBtn, clearBtn, searchBtn, updateBtn);


    }

    public void clearInput() {
        try {
            idField.setText(setAutoID());
            nameField.setText("");

        } catch (Exception e) {
            MyDialog.showDialog("Clear Input", "Clear Error", e.getMessage(), MyDialog.ERRO);
        }
    }

    public static boolean getObjFromTable(TheLoai category){

        try {
            idField.setText(category.getMaTheLoai());
            nameField.setText(category.getTenTheLoai());

            return true;
        } catch (Exception e){
            MyDialog.showDialog("Set Category From DataTable",null, "Unknown Error",MyDialog.ERRO);
        }

        return false;
    }

    public static TheLoai getObjFormField(){

        String id = idField.getText().trim();
        String name = nameField.getText().trim();

        return new TheLoai(id, name);

    }

    public static void setAction(){



    }

    //====================== Controller ======================

    public static String setAutoID(){

        int rows = TheLoaiBLL.getRowNumber();
        rows++;
        if(rows/10 > 1 && rows/10 < 10) return "tl0" + String.valueOf(rows);
        else if(rows/10 < 1) return "tl00" + String.valueOf(rows);
            else return "tl" + String.valueOf(rows);

    }

    public static boolean validator() {

        if (nameField.getText().equals("")) {
            MyDialog.showDialog("Input requirement", null, "Name not Null", MyDialog.ERRO);
            return false;
        }

        return true;
    }

    public static void save() {

        if(!validator()) return;
        TheLoaiBLL.save(getObjFormField());
        idField.setFocusTraversable(true);

    }

    public static ObservableList<TheLoai> search(){
        return TheLoaiBLL.get(getObjFormField());
    }

    public static void update(){
        TheLoaiBLL.update(getObjFormField());
        MyDialog.showDialog("Update",null,"Update Success",MyDialog.INFO);
    }
}

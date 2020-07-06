package gui.inputform.nation;


import bll.QuocGiaBLL;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.QuocGia;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utilities.ImageGetter;
import utilities.MyDialog;
import utilities.MyLayout;


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
        idField.setDisable(true);
        idField.setText(setAutoID());

        nameField = MyLayout.getTextField("Name");
        MyLayout.setValidator(nameField, "Not match Requirement");


        //====================== Button Bar ======================

        buttonBar = getButtonBar();

        //====================== Set All Action ======================

        setAction();

        //================ main layout =============

        getMainLayout();

    }

    //====================== Create Method Section ======================

    public void getMainLayout() {

        this.setPadding(new Insets(30));
        this.setSpacing(40);
        this.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().addAll(idField, nameField, buttonBar);

    }

    public static boolean validator() {

        if (nameField.getText().equals("")) {
            MyDialog.showDialog("Input requirement", null, "Name not match Requirement", MyDialog.ERRO);
            return false;
        }

        return true;
    }

    public HBox getButtonBar() {

        HBox hBox;

        addBtn = MyLayout.getJFXButton("Add", ImageGetter.ADD, MyLayout.ICON_SIZE);
        addBtn.setOnAction( e -> save());

        clearBtn = MyLayout.getJFXButton("Clear", ImageGetter.CLEAR, MyLayout.ICON_SIZE);
        clearBtn.setOnAction( e -> clearInput());

        searchBtn = MyLayout.getJFXButton("Search", ImageGetter.SEARCH, MyLayout.ICON_SIZE);
        searchBtn.setOnAction(e -> search());

        updateBtn = MyLayout.getJFXButton("Update", ImageGetter.SAVE, MyLayout.ICON_SIZE);
        updateBtn.setOnAction(e -> update());




        //add to Button Bar
        hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(addBtn, clearBtn, searchBtn, updateBtn);


        return hBox;

    }

    public boolean clearInput() {
        try {
            idField.setText(setAutoID());
            nameField.setText("");

            return true;
        } catch (Exception e) {
            MyDialog.showDialog("Clear Input", "Clear Error", e.getMessage(), MyDialog.ERRO);
        } finally {
            return false;
        }
    }

    public static boolean setObjFromTable(QuocGia nation){

        try {
            idField.setText(nation.getMaQuocGia());
            nameField.setText(nation.getTenQuocGia());

            return true;
        } catch (Exception e){
            MyDialog.showDialog("Set Nation From Table",null,"Unknown Error",MyDialog.ERRO);
        }
        return false;


    }

    public void setAction(){

        nameField.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER)
                save();
        });


    }
    //====================== Controller =================================

    public static String setAutoID(){

        int rows = QuocGiaBLL.getRowNumber();
        rows++;
        if(rows/10 > 1 && rows/10 < 10) return "qg0" + String.valueOf(rows);
        else if(rows/10 < 1) return "qg00" + String.valueOf(rows);
        else return "qg" + String.valueOf(rows);

    }

    public static QuocGia getObjFormField(){

        String id = idField.getText().trim();
        String name = nameField.getText().trim();

        return new QuocGia(id, name);
    }

    public static void save() {

        if(!validator()) return;
        QuocGiaBLL.save(getObjFormField());
        idField.setFocusTraversable(true);

        DataTable.refreshTable();

    }

    public static void search(){
        DataTable.setItems(QuocGiaBLL.get(getObjFormField()));
    }

    public static void update(){
        QuocGiaBLL.update(getObjFormField());
        DataTable.refreshTable();
        MyDialog.showDialog("Update",null,"Update Success",MyDialog.INFO);
    }
}

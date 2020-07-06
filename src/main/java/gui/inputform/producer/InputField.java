package gui.inputform.producer;


import bll.KenhTVBLL;
import bll.NhaSXBLL;
import bll.TheLoaiBLL;
import bll.QuocGiaBLL;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.NhaSX;
import dto.QuocGia;
import dto.TheLoai;
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

    private HBox nationField;
    private static JFXComboBox<QuocGia> nationSelector;
    private JFXTextField nationName;

    private HBox buttonBar;

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

        //====================== Nation Field ======================

        nationField = getNationField();

        //====================== Button Bar ======================

        buttonBar = getButtonBar();

        //====================== Set All Action ======================

        setAction();

        //================ main layout =============

        getMainLayout();

    }

    //====================== Controller Section ======================

    public void getMainLayout() {

        this.setPadding(new Insets(30));
        this.setSpacing(40);
        this.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().addAll(idField, nameField, nationField, buttonBar);

    }

    public boolean validatorCategory() {
        if (idField.getText().equals("")) {
            MyDialog.showDialog("Input requirement", null, "ID not match Requirement", MyDialog.ERRO);
            return false;
        }
        if (nameField.getText().equals("")) {
            MyDialog.showDialog("Input requirement", null, "Name not match Requirement", MyDialog.ERRO);
            return false;
        }

        return true;
    }

    public HBox getNationField() {

        HBox hBox;

        nationSelector = getNationSelector();
        nationSelector.setPrefHeight(MyLayout.INPUT_HEIGHT);
        nationSelector.prefWidthProperty().bind(this.widthProperty().multiply(0.6));

        nationName = MyLayout.getSubTextField("Nation ID");
        nationName.setDisable(true);
        nationName.prefWidthProperty().bind(this.widthProperty().multiply(MyLayout.NAME_FIELD));

        hBox = new HBox(10);
        hBox.getChildren().addAll(nationSelector, nationName);

        return hBox;
    }

    public JFXComboBox<QuocGia> getNationSelector() {

        JFXComboBox<QuocGia> comboBox;

        comboBox = new JFXComboBox<>();
        comboBox.setPromptText("Nation");
        comboBox.setLabelFloat(true);
        comboBox.prefWidthProperty().bind(this.widthProperty().multiply(0.5));
        comboBox.setItems(QuocGiaBLL.getAll());

        comboBox.setOnAction(e -> nationName.setText(comboBox.getValue().getMaQuocGia()));
        comboBox.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER)
                save();
        });

        return comboBox;
    }

    public HBox getButtonBar() {

        HBox hBox;

        addBtn = MyLayout.getJFXButton("Add", ImageGetter.ADD, MyLayout.ICON_SIZE);
        addBtn.setOnAction(e -> save());

        clearBtn = MyLayout.getJFXButton("Clear", ImageGetter.CLEAR, MyLayout.ICON_SIZE);
        clearBtn.setOnAction(e -> clearInput());

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

    public static boolean setNhaSXFromTable(NhaSX nhaSX){

        try {
             idField.setText(nhaSX.getMaNSX());
             nameField.setText(nhaSX.getTenNSX());
             nationSelector.setValue(QuocGiaBLL.get(nhaSX.getMaQuocGia()));

             return true;
        }catch (Exception e){
            MyDialog.showDialog("Set Data From Table",null,"Unknown Error",MyDialog.ERRO);
        }

        return false;

    }

    public boolean clearInput() {
        try {
            idField.setText(setAutoID());
            nameField.setText("");
            nationSelector.setValue(new QuocGia());
            return true;
        } catch (Exception e) {
            MyDialog.showDialog("Clear Input", "Clear Error", e.getMessage(), MyDialog.ERRO);
        } finally {
            return false;
        }
    }

    public static NhaSX getObjFormField(){

        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String nationID = nationSelector.getValue().getMaQuocGia();

        return new NhaSX(id, name, nationID);

    }

    public void setAction(){

        nameField.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER)
                save();
        });
        nationSelector.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER)
                save();
        });


    }

    //====================== Controller ======================

    public static String setAutoID(){

        int rows = NhaSXBLL.getRowNumber();
        rows++;
        if(rows/10 > 1 && rows/10 < 10) return "sx0" + String.valueOf(rows);
        else if(rows/10 < 1) return "sx00" + String.valueOf(rows);
        else return "sx" + String.valueOf(rows);

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
        NhaSXBLL.save(getObjFormField());
        idField.setFocusTraversable(true);

    }

    public static ObservableList<NhaSX> search(){
        return NhaSXBLL.get(getObjFormField());
    }

    public static void update(){
        NhaSXBLL.update(getObjFormField());
        MyDialog.showDialog("Update",null,"Update Success",MyDialog.INFO);
    }
}

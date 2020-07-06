package gui.inputform.program;

import bll.TheLoaiBLL;
import bll.KenhTVBLL;
import bll.NhaSXBLL;
import bll.ChuongTrinhBLL;
import dto.ChuongTrinh;
import dto.KenhTV;
import dto.NhaSX;
import dto.TheLoai;
import javafx.collections.ObservableList;
import utilities.ImageGetter;
import utilities.MyDialog;
import utilities.MyLayout;
import com.jfoenix.controls.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class InputField extends VBox {
    // Define const


    //Define var
    private static JFXTextField idField;

    private static JFXTextField nameField;

    private static HBox kenhTVField;
    private static JFXComboBox<KenhTV> kenhTVSelector;
    private static JFXTextField kenhTVName;

    private static HBox theLoaiField;
    private static JFXComboBox<TheLoai> theLoaiSelector;
    private static JFXTextField theLoaiName;

    private static HBox nhaSXField;
    private static JFXComboBox<NhaSX> nhaSXSelector;
    private static JFXTextField nhaSXName;

    private static HBox buttonBar;

    private static JFXButton addBtn;
    private static JFXButton clearBtn;
    private static JFXButton searchBtn;
    private static JFXButton updateBtn;


    public InputField() throws Exception {

        idField = MyLayout.getTextField("ID");
        idField.setDisable(true);
        idField.setText(setAutoID());

        nameField = MyLayout.getTextField("Name");
        MyLayout.setValidator(nameField, "Not match Requirement");


        //====================== KenhTV Field ======================

        kenhTVField = getKenhTVField();

        //================= TheLoai Field ===================

        theLoaiField = getTheLoaiField();

        //============== NhaSX Field ==================

        nhaSXField = getNhaSXField();

        //====================== Set All Action ======================

        setAction();

        //====================== Button Bar ======================

        buttonBar = getButtonBar();

        //================ main layout =============

        getMainLayout();

    }

    //====================== Controller Section ======================

    public void getMainLayout() {

        this.setPadding(new Insets(30));
        this.setSpacing(40);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(idField, nameField, theLoaiField, nhaSXField, buttonBar);

    }

    public HBox getKenhTVField() {

        HBox hBox;

        kenhTVSelector = getKenhTVSelector();
        kenhTVSelector.setPrefHeight(MyLayout.INPUT_HEIGHT);
        kenhTVName = MyLayout.getSubTextField("KenhTV ID");
        kenhTVName.prefWidthProperty().bind(this.widthProperty().multiply(MyLayout.NAME_FIELD));
        kenhTVName.setDisable(true);

        hBox = new HBox(10);
        hBox.getChildren().addAll(kenhTVSelector, kenhTVName);

        return hBox;
    }

    public HBox getTheLoaiField() {

        HBox hBox;

        theLoaiSelector = getTheLoaiSelector();
        theLoaiSelector.setPrefHeight(MyLayout.INPUT_HEIGHT);

        theLoaiName = MyLayout.getSubTextField("TheLoai ID");
        theLoaiName.setDisable(true);
        theLoaiName.prefWidthProperty().bind(this.widthProperty().multiply(MyLayout.NAME_FIELD));

        hBox = new HBox(15);
        hBox.getChildren().addAll(theLoaiSelector, theLoaiName);

        return hBox;
    }

    public HBox getNhaSXField() {

        HBox hBox;

        nhaSXSelector = getNhaSXSelector();
        nhaSXSelector.setPrefHeight(MyLayout.INPUT_HEIGHT);

        nhaSXName = MyLayout.getSubTextField("NhaSX ID");
        nhaSXName.setDisable(true);
        nhaSXName.prefWidthProperty().bind(this.widthProperty().multiply(MyLayout.NAME_FIELD));

        hBox = new HBox(15);
        hBox.getChildren().addAll(nhaSXSelector, nhaSXName);

        return hBox;

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

    public JFXComboBox<KenhTV> getKenhTVSelector() {

        JFXComboBox<KenhTV> comboBox;

        comboBox = new JFXComboBox<>();
        comboBox.setPromptText("KenhTV");
        comboBox.setLabelFloat(true);
        comboBox.prefWidthProperty().bind(this.widthProperty().multiply(MyLayout.SELECTOR_FIELD));
        comboBox.setItems(KenhTVBLL.getAll());

        comboBox.setOnAction(e -> kenhTVName.setText(comboBox.getValue().getMaKenh()));
        comboBox.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER)
                save();
        });

        return comboBox;
    }

    public JFXComboBox<TheLoai> getTheLoaiSelector() {

        JFXComboBox<TheLoai> comboBox;

        comboBox = new JFXComboBox<>();
        comboBox.setPromptText("TheLoai");
        comboBox.setLabelFloat(true);
        comboBox.prefWidthProperty().bind(this.widthProperty().multiply(MyLayout.SELECTOR_FIELD));
        comboBox.setItems(TheLoaiBLL.getAll());

        comboBox.setOnAction(e -> theLoaiName.setText(comboBox.getValue().getMaTheLoai()));
        comboBox.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER)
                save();
        });

        return comboBox;
    }

    public JFXComboBox<NhaSX> getNhaSXSelector() {

        JFXComboBox<NhaSX> comboBox;

        comboBox = new JFXComboBox<>();
        comboBox.setPromptText("NhaSX");
        comboBox.setLabelFloat(true);
        comboBox.prefWidthProperty().bind(this.widthProperty().multiply(MyLayout.SELECTOR_FIELD));
        comboBox.setItems(NhaSXBLL.getAll());

        comboBox.setOnAction(e -> nhaSXName.setText(comboBox.getValue().getMaNSX()));
        comboBox.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER)
                save();
        });

        return comboBox;
    }

    public boolean clearInput() {
        try {
            idField.setText(setAutoID());
            nameField.setText("");
            kenhTVSelector.setValue(new KenhTV());
            theLoaiSelector.setValue(new TheLoai());
            nhaSXSelector.setValue(new NhaSX());

            return true;
        } catch (Exception e) {
            MyDialog.showDialog("Clear Input", "Clear Error", e.getMessage(), MyDialog.ERRO);
        } finally {
            return false;
        }
    }

    public static boolean setObjFromField(ChuongTrinh program){

        try {
            idField.setText(program.getMaCT());
            nameField.setText(program.getTenCT());
            theLoaiSelector.setValue(TheLoaiBLL.get(program.getMaTheLoai()));
            nhaSXSelector.setValue(NhaSXBLL.get(program.getMaNSX()));

            return true;
        } catch (Exception e){
            MyDialog.showDialog("Set Program From Table", null, "Unknown Error",MyDialog.ERRO);
        }
        return false;

    }

    public static ChuongTrinh getObjFormField(){

        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String categoryID, nhaSXID;

        if(theLoaiSelector.getValue() == null) categoryID = "";
        else categoryID = theLoaiSelector.getValue().getMaTheLoai();

        if(nhaSXSelector.getValue() == null) nhaSXID = "";
        else nhaSXID = nhaSXSelector.getValue().getMaNSX();


        return new ChuongTrinh(id, name, categoryID, nhaSXID);

    }

    public void setAction(){

        nameField.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER)
                save();
        });
        theLoaiSelector.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER)
                save();
        });
        nhaSXSelector.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER)
                save();
        });

    }
    //====================== Controller ======================

    public static String setAutoID(){

        int rows = ChuongTrinhBLL.getRowNumber();
        rows++;
        if(rows/10 > 1 && rows/10 < 10) return "ct0" + String.valueOf(rows);
        else if(rows/10 < 1) return "ct00" + String.valueOf(rows);
        else return "ct" + String.valueOf(rows);

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
        ChuongTrinhBLL.save(getObjFormField());
        idField.setFocusTraversable(true);
        DataTable.refreshTable();

    }

    public static void search(){

        DataTable.setItems(ChuongTrinhBLL.get(getObjFormField()));

    }

    public static void update(){
        ChuongTrinhBLL.update(getObjFormField());
        DataTable.refreshTable();
        MyDialog.showDialog("Update",null,"Update Success",MyDialog.INFO);
    }

}

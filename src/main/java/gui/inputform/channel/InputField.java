package gui.inputform.channel;


import bll.KenhTVBLL;
import bll.LinhVucBLL;
import com.jfoenix.controls.*;
import dto.KenhTV;
import dto.LinhVuc;
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

    private static JFXCheckBox isCenterBox;

    private static HBox majorField;
    private static JFXComboBox<LinhVuc> majorComboxBox;
    private static JFXTextField majorName;

    private static JFXTextField cityField;

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


        //====================== isCenter Box ======================

        isCenterBox = getCheckBox("Is Center?");

        //============== City Field ==================

        cityField = MyLayout.getTextField("City");

        //====================== Major ======================

        majorField = getMajorField();

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
        this.getChildren().addAll(idField, nameField, isCenterBox,majorField, cityField, buttonBar);

    }

    public JFXCheckBox getCheckBox(String label){

        JFXCheckBox checkBox = new JFXCheckBox(label);

        return checkBox;


    }

    public JFXComboBox<LinhVuc> getMajorComboBox() {

        JFXComboBox<LinhVuc> comboBox;

        comboBox = new JFXComboBox<>();
        comboBox.setPromptText("Linh Vuc");
        comboBox.setLabelFloat(true);
        comboBox.prefWidthProperty().bind(this.widthProperty().multiply(MyLayout.SELECTOR_FIELD));
        comboBox.setItems(LinhVucBLL.getAll());

        comboBox.setOnAction(e -> majorName.setText(comboBox.getValue().getTenLinhVuc()));
        comboBox.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER)
                save();
        });

        return comboBox;
    }

    public HBox getMajorField() {

        HBox hBox;

        majorComboxBox = getMajorComboBox();
        majorComboxBox.setPrefHeight(MyLayout.INPUT_HEIGHT);
        majorComboxBox.prefWidthProperty().bind(this.widthProperty().multiply(0.6));

        majorName = MyLayout.getSubTextField("Nha SX ID");
        majorName.setDisable(true);
        majorName.prefWidthProperty().bind(this.widthProperty().multiply(MyLayout.NAME_FIELD));

        hBox = new HBox(15);
        hBox.getChildren().addAll(majorComboxBox, majorName);

        return hBox;

    }

    public static boolean validator() {

        if (nameField.getText().equals("")) {
            MyDialog.showDialog("Input requirement", null, "Name not Null", MyDialog.ERRO);
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
            cityField.setText("");
            isCenterBox.setSelected(false);

            return true;
        } catch (Exception e) {
            MyDialog.showDialog("Clear Input", "Clear Error", e.getMessage(), MyDialog.ERRO);
        } finally {
            return false;
        }
    }

    public static boolean setObjFromTable(KenhTV channel){

        try {
            idField.setText(channel.getMaKenh());

            nameField.setText(channel.getTenKenh());

            if (channel.getThuocTU() == 1)
                isCenterBox.setSelected(true);
            else
                isCenterBox.setSelected(false);

            majorComboxBox.setValue(LinhVucBLL.get(channel.getMaLV()));

            cityField.setText(channel.getTinhTP());

            return true;
        } catch (Exception e){
            MyDialog.showDialog("Set Channel From DataTable",null,"Unknown Error",MyDialog.ERRO);
        }

        return false;
    }

    public static KenhTV getObjFormField(){

        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String city = cityField.getText().trim();
        String majorID = majorComboxBox.getValue().getMaLinhVuc();
        int isCenter = 0;
        if(isCenterBox.isSelected())
            isCenter = 1;

        return new KenhTV(id, name, city, majorID, isCenter);
    }

    public void setAction(){

        nameField.setOnAction(e -> save());
        isCenterBox.setOnKeyPressed(e ->{
            if(e.getCode() == KeyCode.ENTER)
                save();
        });
        cityField.setOnKeyPressed(e ->{
            if(e.getCode() == KeyCode.ENTER)
                save();
        });
        majorComboxBox.setOnKeyPressed(e ->{
            if(e.getCode() == KeyCode.ENTER)
                save();
        });
    }
    //====================== Controller ======================

    public static String setAutoID(){

        int rows = KenhTVBLL.getRowNumber();
        rows++;
        if(rows/10 > 1 && rows/10 < 10) return "tv0" + String.valueOf(rows);
        else if(rows/10 < 1) return "tv00" + String.valueOf(rows);
        else return "tv" + String.valueOf(rows);

    }

    public static void save() {

        if(!validator()) return;
        KenhTVBLL.save(getObjFormField());
        idField.setFocusTraversable(true);

        DataTable.refreshTable();
    }

    public static void search(){
        DataTable.setItems(KenhTVBLL.get(getObjFormField()));
    }

    public static void update(){
        KenhTVBLL.update(getObjFormField());
        DataTable.refreshTable();
        MyDialog.showDialog("Update",null,"Update Success",MyDialog.INFO);
    }
}

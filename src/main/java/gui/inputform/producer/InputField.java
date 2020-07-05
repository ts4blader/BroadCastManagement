package gui.inputform.producer;


import bll.CategoryBLL;
import bll.NationBLL;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entities.Category;
import entities.Nation;
import entities.Producer;
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
    private static JFXComboBox<Nation> nationSelector;
    private JFXTextField nationName;

    private HBox buttonBar;

    private JFXButton addBtn;
    private JFXButton clearBtn;
    private JFXButton searchBtn;


    public InputField() {

        idField = MyLayout.getTextField("ID");
        idField.setOnAction(e -> addProducer());
        MyLayout.setValidator(idField, "Not match Requirement");

        nameField = MyLayout.getTextField("Name");
        nameField.setOnAction(e -> addProducer());
        MyLayout.setValidator(nameField, "Not match Requirement");

        //====================== Nation Field ======================

        nationField = getNationField();

        //====================== Button Bar ======================

        buttonBar = getButtonBar();

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

        nationName = MyLayout.getSubTextField("Nation ID");
        nationName.setDisable(true);
        nationName.prefWidthProperty().bind(this.widthProperty().multiply(MyLayout.NAME_FIELD));

        hBox = new HBox(15);
        hBox.getChildren().addAll(nationSelector, nationName);

        return hBox;
    }

    public JFXComboBox<Nation> getNationSelector() {

        JFXComboBox<Nation> comboBox;

        comboBox = new JFXComboBox<>();
        comboBox.setPromptText("Nation");
        comboBox.setLabelFloat(true);
        comboBox.prefWidthProperty().bind(this.widthProperty().multiply(0.5));
        comboBox.setItems(NationBLL.getAllNation());

        comboBox.setOnAction(e -> nationName.setText(comboBox.getValue().getId()));
        comboBox.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER)
                addProducer();
        });

        return comboBox;
    }

    public HBox getButtonBar() {

        HBox hBox;

        addBtn = MyLayout.getJFXButton("Add", ImageGetter.ADD, MyLayout.ICON_SIZE);
        addBtn.setOnAction(e -> addProducer());
        clearBtn = MyLayout.getJFXButton("Clear", ImageGetter.CLEAR, MyLayout.ICON_SIZE);
        clearBtn.setOnAction(e -> clearInput());
        searchBtn = MyLayout.getJFXButton("Search", ImageGetter.SEARCH, MyLayout.ICON_SIZE);


        //add to Button Bar
        hBox = new HBox(15);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(addBtn, clearBtn, searchBtn);


        return hBox;

    }

    public boolean addProducer() {
        try {
            if (!validatorCategory()) return false;

            String id = idField.getText().trim();
            String name = nameField.getText().trim();

            CategoryBLL.addCategory(new Category(id, name));
            return true;

        } catch (Exception e) {
            MyDialog.showDialog("Can not Add Program", "Unknow Error", e.getMessage(), MyDialog.ERRO);
        } finally {
            return false;
        }

    }

    public static boolean setProducerFromTable(Producer producer){

        try {
             idField.setText(producer.getId());
             nameField.setText(producer.getName());
             nationSelector.setValue(NationBLL.getNationById(producer.getNationID()));

             return true;
        }catch (Exception e){
            MyDialog.showDialog("Set Data From Table",null,"Unknown Error",MyDialog.ERRO);
        }

        return false;

    }

    public boolean clearInput() {
        try {
            idField.setText("");
            nameField.setText("");
            nationSelector.setValue(new Nation());
            return true;
        } catch (Exception e) {
            MyDialog.showDialog("Clear Input", "Clear Error", e.getMessage(), MyDialog.ERRO);
        } finally {
            return false;
        }
    }
    //====================== End =================================
}

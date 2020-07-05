package gui.inputform.program;

import bll.CategoryBLL;
import bll.ChannelBLL;
import bll.ProducerBLL;
import bll.ProgramBLL;
import entities.Category;
import entities.Channel;
import entities.Producer;
import entities.Program;
import javafx.scene.control.SingleSelectionModel;
import utilities.ImageGetter;
import utilities.MyDialog;
import utilities.MyLayout;
import com.jfoenix.controls.*;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.File;

public class InputField extends VBox {
    // Define const


    //Define var
    private static JFXTextField idField;

    private static JFXTextField nameField;

    private HBox channelField;
    private static JFXComboBox<Channel> channelSelector;
    private static JFXTextField channelName;

    private HBox categoryField;
    private static JFXComboBox<Category> categorySelector;
    private static JFXTextField categoryName;

    private HBox producerField;
    private static JFXComboBox<Producer> producerSelector;
    private static JFXTextField producerName;

    private HBox buttonBar;

    private JFXButton addBtn;
    private JFXButton clearBtn;
    private JFXButton searchBtn;


    public InputField() throws Exception {

        idField = MyLayout.getTextField("ID");
        MyLayout.setValidator(idField, "Not match Requirement");

        nameField = MyLayout.getTextField("Name");
        MyLayout.setValidator(nameField, "Not match Requirement");


        //====================== Channel Field ======================

        channelField = getChannelField();

        //================= Category Field ===================

        categoryField = getCategoryField();

        //============== Producer Field ==================

        producerField = getProducerField();

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
        this.getChildren().addAll(idField, nameField, channelField, categoryField, producerField, buttonBar);

    }

    public HBox getChannelField() {

        HBox hBox;

        channelSelector = getChannelSelector();
        channelSelector.setPrefHeight(MyLayout.INPUT_HEIGHT);
        channelName = MyLayout.getSubTextField("Channel ID");
        channelName.prefWidthProperty().bind(this.widthProperty().multiply(MyLayout.NAME_FIELD));
        channelName.setDisable(true);

        hBox = new HBox(10);
        hBox.getChildren().addAll(channelSelector, channelName);

        return hBox;
    }

    public HBox getCategoryField() {

        HBox hBox;

        categorySelector = getCategorySelector();
        categorySelector.setPrefHeight(MyLayout.INPUT_HEIGHT);

        categoryName = MyLayout.getSubTextField("Category ID");
        categoryName.setDisable(true);
        categoryName.prefWidthProperty().bind(this.widthProperty().multiply(MyLayout.NAME_FIELD));

        hBox = new HBox(15);
        hBox.getChildren().addAll(categorySelector, categoryName);

        return hBox;
    }

    public HBox getProducerField() {

        HBox hBox;

        producerSelector = getProducerSelector();
        producerSelector.setPrefHeight(MyLayout.INPUT_HEIGHT);

        producerName = MyLayout.getSubTextField("Producer ID");
        producerName.setDisable(true);
        producerName.prefWidthProperty().bind(this.widthProperty().multiply(MyLayout.NAME_FIELD));

        hBox = new HBox(15);
        hBox.getChildren().addAll(producerSelector, producerName);

        return hBox;

    }

    public HBox getButtonBar() {

        HBox hBox;

        addBtn = MyLayout.getJFXButton("Add", ImageGetter.ADD, MyLayout.ICON_SIZE);
        addBtn.setOnAction(e -> addProgram());

        clearBtn = MyLayout.getJFXButton("Clear", ImageGetter.CLEAR, MyLayout.ICON_SIZE);
        clearBtn.setOnAction(e -> clearInput());

        searchBtn = MyLayout.getJFXButton("Search", ImageGetter.SEARCH, MyLayout.ICON_SIZE);


        //add to Button Bar
        hBox = new HBox(15);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(addBtn, clearBtn, searchBtn);


        return hBox;

    }

    public JFXComboBox<Channel> getChannelSelector() {

        JFXComboBox<Channel> comboBox;

        comboBox = new JFXComboBox<>();
        comboBox.setPromptText("Channel");
        comboBox.setLabelFloat(true);
        comboBox.prefWidthProperty().bind(this.widthProperty().multiply(MyLayout.SELECTOR_FIELD));
        comboBox.setItems(ChannelBLL.getAllChannel());

        comboBox.setOnAction(e -> channelName.setText(comboBox.getValue().getId()));
        comboBox.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER)
                addProgram();
        });

        return comboBox;
    }

    public JFXComboBox<Category> getCategorySelector() {

        JFXComboBox<Category> comboBox;

        comboBox = new JFXComboBox<>();
        comboBox.setPromptText("Category");
        comboBox.setLabelFloat(true);
        comboBox.prefWidthProperty().bind(this.widthProperty().multiply(MyLayout.SELECTOR_FIELD));
        comboBox.setItems(CategoryBLL.getAllCategory());

        comboBox.setOnAction(e -> categoryName.setText(comboBox.getValue().getId()));
        comboBox.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER)
                addProgram();
        });

        return comboBox;
    }

    public JFXComboBox<Producer> getProducerSelector() {

        JFXComboBox<Producer> comboBox;

        comboBox = new JFXComboBox<>();
        comboBox.setPromptText("Producer");
        comboBox.setLabelFloat(true);
        comboBox.prefWidthProperty().bind(this.widthProperty().multiply(MyLayout.SELECTOR_FIELD));
        comboBox.setItems(ProducerBLL.getAllProducer());

        comboBox.setOnAction(e -> producerName.setText(comboBox.getValue().getId()));
        comboBox.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER)
                addProgram();
        });

        return comboBox;
    }

    public boolean validatorProgram() {
        if (nameField.getText().equals("")) {
            MyDialog.showDialog("Input requirement", null, "Name not match Requirement", MyDialog.ERRO);
            return false;
        }
        if (channelSelector.getValue() == null) {
            MyDialog.showDialog("Input requirement", null, "Channel not null", MyDialog.ERRO);
            return false;
        }
        if (categorySelector.getValue() == null) {
            MyDialog.showDialog("Input requirement", null, "Category not null", MyDialog.ERRO);
            return false;
        }
        if (producerSelector.getValue() == null) {
            MyDialog.showDialog("Input requirement", null, "Producer not null", MyDialog.ERRO);
            return false;
        }

        return true;
    }

    public boolean addProgram() {
        try {
            if (!validatorProgram()) return false;

            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String categoryID = categorySelector.getValue().getId();
            String channelID = channelSelector.getValue().getId();
            String producerID = producerSelector.getValue().getId();

            ProgramBLL.add(new Program(id, name, categoryID, producerID));
            return true;

        } catch (Exception e) {
            MyDialog.showDialog("Can not Add Program", "Unknow Error", e.getMessage(), MyDialog.ERRO);
        } finally {
            return false;
        }

    }

    public boolean clearInput() {
        try {
            idField.setText("");
            nameField.setText("");
            channelSelector.setValue(new Channel());
            categorySelector.setValue(new Category());
            producerSelector.setValue(new Producer());

            return true;
        } catch (Exception e) {
            MyDialog.showDialog("Clear Input", "Clear Error", e.getMessage(), MyDialog.ERRO);
        } finally {
            return false;
        }
    }

    public static boolean setProgramFromTable(Program program){

        try {
            idField.setText(program.getId());
            nameField.setText(program.getName());
            categorySelector.setValue(CategoryBLL.getCategoryByID(program.getCategoryID()));
            producerSelector.setValue(ProducerBLL.getProducerById(program.getProducerID()));

            return true;
        } catch (Exception e){
            MyDialog.showDialog("Set Program From Table", null, "Unknown Error",MyDialog.ERRO);
        }
        return false;

    }

}

package GUI.Program;

import BLL.CategoryBLL;
import BLL.ChannelBLL;
import BLL.ProducerBLL;
import BLL.ProgramBLL;
import Enities.Category;
import Enities.Channel;
import Enities.Producer;
import Enities.Program;
import Utilities.MyDialog;
import com.jfoenix.controls.*;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class InputField extends VBox {

    private JFXTextField idField;

    private JFXTextField nameField;

    private HBox channelField;
    private JFXComboBox<Channel> channelSelector;
    private JFXTextField channelName;

    private HBox categoryField;
    private JFXComboBox<Category> categorySelector;
    private JFXTextField categoryName;

    private HBox producerField;
    private JFXComboBox<Producer> producerSelector;
    private JFXTextField producerName;

    private HBox buttonBar;

    private JFXButton addBtn;
    private JFXButton clearBtn;
    private JFXButton searchBtn;


    public InputField(){

        //================id Field=====================
        idField = new JFXTextField();
        idField.setPrefHeight(30);
        idField.setFont(new Font(16));
        idField.setPromptText("ID");
        idField.setLabelFloat(true);

        //================ end ====================


        //================== name Field =================
        nameField = new JFXTextField();
        nameField.setFont(new Font(16));
        nameField.setPrefHeight(30);
        nameField.setLabelFloat(true);
        nameField.setPromptText("Name");

        //====================== end =========================

        //=====================name validator=======================
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Require");
        nameField.setValidators(validator);
        nameField.focusedProperty().addListener( (o ,oldVla, newVal) -> {
            if(!newVal)
                nameField.validate();
        });

        //==================== end ======================

        //==================== channel ComboBox ======================
        channelSelector = new JFXComboBox<>();
        channelSelector.setPromptText("Channel");
        channelSelector.setLabelFloat(true);
        channelSelector.prefWidthProperty().bind(this.widthProperty().multiply(0.5));
        channelSelector.setItems(ChannelBLL.getAllChannel());

        //==================== Channel Name TextField ==========================
        channelName = new JFXTextField();
        channelName.prefWidthProperty().bind(this.widthProperty().multiply(0.5));
        channelName.setPromptText("Channel ID");
        channelName.setDisable(true);
        channelName.setLabelFloat(true);

        //add to channel Field
        channelField = new HBox(15);
        channelField.getChildren().addAll(channelSelector, channelName);

        //event handler
        channelSelector.setOnAction( (e) -> {
            channelName.setText(channelSelector.getValue().getId());
        });

        //===================== end ===========================

        //================= category ComboBox ===================
        categorySelector = new JFXComboBox<>();
        categorySelector.setPromptText("Category");
        categorySelector.setLabelFloat(true);
        categorySelector.prefWidthProperty().bind(this.widthProperty().multiply(0.5));
        //set list for category selector
        ObservableList<Category> categories = CategoryBLL.getAllCategory();
        categorySelector.setItems(categories);

        //event handler
        categorySelector.setOnAction( (e) -> {
            categoryName.setText(categorySelector.getValue().getId());
        });


        //add Category Name Textfield
        categoryName = new JFXTextField();
        categoryName.setLabelFloat(true);
        categoryName.setDisable(true);
        categoryName.setPromptText("Category ID");
        categoryName.prefWidthProperty().bind(this.widthProperty().multiply(0.5));
        //add to category Field
        categoryField = new HBox(15);
        categoryField.getChildren().addAll(categorySelector, categoryName);

        //================ end ============

        //============== Producer ==================

        producerSelector = new JFXComboBox<>();
        producerSelector.prefWidthProperty().bind(this.widthProperty().multiply(0.5));
        producerSelector.setItems(ProducerBLL.getAllProducer());
        producerSelector.setPromptText("Producer");
        producerSelector.setLabelFloat(true);

        producerName = new JFXTextField();
        producerName.prefWidthProperty().bind(this.widthProperty().multiply(0.5));
        producerName.setPromptText("Producer ID");
        producerName.setLabelFloat(true);
        producerName.setDisable(true);

        producerSelector.setOnAction( e ->{
            producerName.setText(producerSelector.getValue().getId());
        });
        //add to produceField

        producerField = new HBox(15);
        producerField.getChildren().addAll(producerSelector, producerName);

        //============== end =====================

        //====================== Button Bar ======================

            addBtn = new JFXButton("Add");
            addBtn.setOnAction(e -> {
                validatorProgram();
            });

            clearBtn = new JFXButton("Clear");
            clearBtn.setOnAction( e ->{
                clearInput();
            });

            searchBtn = new JFXButton("Search");

            //add to Button Bar
            buttonBar = new HBox(15);
            buttonBar.setAlignment(Pos.CENTER);
            buttonBar.getChildren().addAll(addBtn, clearBtn, searchBtn);

        //====================== End ======================


        //================ main layout =============


        this.setPadding(new Insets(30));
        this.setSpacing(40);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(idField, nameField, channelField, categoryField, producerField, buttonBar);


    }

    //====================== Controller Section ======================

    public void validatorProgram(){
        if( nameField.getText().equals("")) {
            MyDialog.showDialog("Input requirement", null, "Name not match Requirement", MyDialog.ERRO);
            return ;
        }
        if( channelSelector.getValue() == null) {
            MyDialog.showDialog("Input requirement", null, "Channel not null", MyDialog.ERRO);
            return;
        }
        if( categorySelector.getValue() == null) {
            MyDialog.showDialog("Input requirement", null, "Category not null", MyDialog.ERRO);
            return;
        }        if( producerSelector.getValue() == null) {
            MyDialog.showDialog("Input requirement", null, "Producer not null", MyDialog.ERRO);
            return;
        }    }

    public boolean addProgram(){
        try {
            validatorProgram();

            String id = (idField.getText());
            String name = nameField.getText();
            String categoryID = categorySelector.getValue().getId();
            String channelID = channelSelector.getValue().getId();
            String producerID = producerSelector.getValue().getId();

            ProgramBLL.add(new Program(id, name, categoryID, producerID));
            return true;

        } catch (Exception e){
            MyDialog.showDialog("Can not Add Program", "Unknow Error",e.getMessage(), MyDialog.ERRO);
        } finally {
            return false;
        }

    }

    public boolean clearInput(){
        try{
            idField.setText("");
            nameField.setText("");
            channelSelector.setValue(null);
            categorySelector.setValue(null);
            producerSelector.setValue(null);

            return true;
        } catch (Exception e){
            MyDialog.showDialog("Clear Input", "Clear Error",e.getMessage(),MyDialog.ERRO);
        } finally {
            return false;
        }
    }
    //====================== End =================================
}

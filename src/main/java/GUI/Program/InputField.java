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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.File;
import java.net.MalformedURLException;

public class InputField extends VBox {
    // Define const
    public final static int ICON_SIZE = 15;


    //Define var
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

    private File addImage = new File("src/main/resources/icons/plus.png");
    private File clearImage = new File("src/main/resources/icons/clean.png");
    private File searchImage = new File("src/main/resources/icons/search.png");


    public InputField() throws Exception{

        idField = getTextField("ID");
        setValidator(idField,"Not match Requirement");

        nameField = getTextField("Name");
        setValidator(nameField, "Not match Requirement");


        //====================== Channel Field ======================

        channelSelector = getChannelSelector();

        channelName = getTextField("Channel ID");
        channelName.prefWidthProperty().bind(this.widthProperty().multiply(0.5));
        channelName.setDisable(true);

        channelField = new HBox(15);
        channelField.getChildren().addAll(channelSelector, channelName);


        //================= Category Field ===================
        categorySelector = getCategorySelector();

        categoryName = getTextField("Category ID");
        categoryName.setDisable(true);
        categoryName.prefWidthProperty().bind(this.widthProperty().multiply(0.5));

        categoryField = new HBox(15);
        categoryField.getChildren().addAll(categorySelector, categoryName);


        //============== Producer Field ==================

        producerSelector = getProducerSelector();

        producerName = getTextField("Producer ID");
        producerName.setDisable(true);
        producerName.prefWidthProperty().bind(this.widthProperty().multiply(0.5));

        producerField = new HBox(15);
        producerField.getChildren().addAll(producerSelector, producerName);

        //====================== Button Bar ======================


        addBtn = new JFXButton("Add");
        ImageView addImageView = getImageView(addImage, ICON_SIZE);
        addBtn.setGraphic(addImageView);

        addBtn.setOnAction(e -> {
            validatorProgram();
        });

        clearBtn = new JFXButton("Clear");
        clearBtn.setOnAction( e ->{
            clearInput();
        });

        ImageView clearImageView = getImageView(clearImage, ICON_SIZE);
        clearBtn.setGraphic(clearImageView);

        searchBtn = new JFXButton("Search");
        ImageView searchImageView = getImageView(searchImage, ICON_SIZE);
        searchBtn.setGraphic(searchImageView);

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

    public JFXTextField getTextField(String promtText){

        JFXTextField textField;

        textField = new JFXTextField();
        textField.setPrefHeight(30);
        textField.setFont(new Font(16));
        textField.setPromptText(promtText);
        textField.setLabelFloat(true);

        textField.setOnAction( e -> addProgram());

        return textField;
    }

    public JFXComboBox<Channel> getChannelSelector(){

        JFXComboBox<Channel> comboBox;

        comboBox = new JFXComboBox<>();
        comboBox.setPromptText("Channel");
        comboBox.setLabelFloat(true);
        comboBox.prefWidthProperty().bind(this.widthProperty().multiply(0.5));
        comboBox.setItems(ChannelBLL.getAllChannel());

        comboBox.setOnAction( e -> channelName.setText(comboBox.getValue().getId()));

        return comboBox;
    }

    public JFXComboBox<Category> getCategorySelector(){

        JFXComboBox<Category> comboBox;

        comboBox = new JFXComboBox<>();
        comboBox.setPromptText("Category");
        comboBox.setLabelFloat(true);
        comboBox.prefWidthProperty().bind(this.widthProperty().multiply(0.5));
        comboBox.setItems(CategoryBLL.getAllCategory());

        comboBox.setOnAction( e -> categoryName.setText(comboBox.getValue().getId()));

        return comboBox;
    }

    public JFXComboBox<Producer> getProducerSelector(){

        JFXComboBox<Producer> comboBox;

        comboBox = new JFXComboBox<>();
        comboBox.setPromptText("Producer");
        comboBox.setLabelFloat(true);
        comboBox.prefWidthProperty().bind(this.widthProperty().multiply(0.5));
        comboBox.setItems(ProducerBLL.getAllProducer());

        comboBox.setOnAction( e -> producerName.setText(comboBox.getValue().getId()));

        return comboBox;
    }

    public void setValidator(JFXTextField textField, String message){
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage(message);
        textField.setValidators(validator);
        textField.focusedProperty().addListener( (o ,oldVla, newVal) -> {
            if(!newVal)
                textField.validate();
        });
    }

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
        }
    }

    public ImageView getImageView(File image, int iconSize){

        ImageView imageView = null;
        try {
            imageView = new ImageView(new Image(image.toURI().toURL().toString()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        imageView.setFitHeight(iconSize);
        imageView.setFitWidth(iconSize);

        return imageView;

    }

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

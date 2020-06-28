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

    public void getMainLayout(){

        this.setPadding(new Insets(30));
        this.setSpacing(40);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(idField, nameField, channelField, categoryField, producerField, buttonBar);

    }

    public HBox getChannelField(){

        HBox hBox;

        channelSelector = getChannelSelector();

        channelName = getTextField("Channel ID");
        channelName.prefWidthProperty().bind(this.widthProperty().multiply(0.5));
        channelName.setDisable(true);

        hBox = new HBox(15);
        hBox.getChildren().addAll(channelSelector, channelName);

        return hBox;
    }

    public HBox getCategoryField(){

        HBox hBox;

        categorySelector = getCategorySelector();

        categoryName = getTextField("Category ID");
        categoryName.setDisable(true);
        categoryName.prefWidthProperty().bind(this.widthProperty().multiply(0.5));

        hBox = new HBox(15);
        hBox.getChildren().addAll(categorySelector, categoryName);

        return hBox;
    }

    public HBox getProducerField(){

        HBox hBox;

        producerSelector = getProducerSelector();

        producerName = getTextField("Producer ID");
        producerName.setDisable(true);
        producerName.prefWidthProperty().bind(this.widthProperty().multiply(0.5));

        hBox = new HBox(15);
        hBox.getChildren().addAll(producerSelector, producerName);

        return hBox;

    }

    public HBox getButtonBar(){

        HBox hBox;

        addBtn = getButton("Add", addImage, ICON_SIZE);
        addBtn.setOnAction(e -> addProgram());

        clearBtn = getButton("Clear", clearImage, ICON_SIZE);
        clearBtn.setOnAction(e -> clearInput());

        searchBtn = getButton("Search",searchImage, ICON_SIZE);


        //add to Button Bar
        hBox = new HBox(15);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(addBtn, clearBtn, searchBtn);


        return hBox;

    }

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

    public JFXButton getButton(String Label, File image, int iconSize){

        JFXButton button = new JFXButton(Label);
        ImageView imageView = getImageView(image, iconSize);
        button.setGraphic(imageView);

        return button;
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

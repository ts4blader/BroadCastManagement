package gui.inputform.channel;


import bll.ChannelBLL;
import com.jfoenix.controls.*;
import entities.Channel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import utilities.ImageGetter;
import utilities.MyDialog;
import utilities.MyLayout;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InputField extends VBox {
    // Define const


    //Define var
    private static JFXTextField idField;

    private static JFXTextField nameField;

    private static JFXCheckBox isCenterBox;

    private VBox dateField;
    private static JFXTextField beginDateField;
    private Text exampleDate;

    private static JFXTextField cityField;

    private HBox buttonBar;

    private JFXButton addBtn;
    private JFXButton clearBtn;
    private JFXButton searchBtn;


    public InputField() {

        idField = MyLayout.getTextField("ID");
        idField.setOnAction( e -> addChannel());
        MyLayout.setValidator(idField, "Not match Requirement");

        nameField = MyLayout.getTextField("Name");
        nameField.setOnAction(e -> addChannel());
        MyLayout.setValidator(nameField, "Not match Requirement");


        //====================== isCenter Box ======================

        isCenterBox = getCheckBox("Is Center?");
        isCenterBox.setOnKeyPressed( e ->{
            if( e.getCode() == KeyCode.ENTER)
                addChannel();
        });

        //================= Date Field ===================

        createDateField();

        //============== City Field ==================

        cityField = MyLayout.getTextField("City");
        cityField.setOnAction(e -> addChannel());

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
        this.getChildren().addAll(idField, nameField, isCenterBox, dateField, cityField, buttonBar);

    }

    public JFXCheckBox getCheckBox(String label){

        JFXCheckBox checkBox = new JFXCheckBox(label);

        return checkBox;


    }

    public boolean validatorProgram() {
        if (idField.getText().equals("")){
            MyDialog.showDialog("Input requirement", null, "ID not match Requirement", MyDialog.ERRO);
            return false;
        }
        if (nameField.getText().equals("")) {
            MyDialog.showDialog("Input requirement", null, "Name not match Requirement", MyDialog.ERRO);
            return false;
        }
        if (beginDateField.getText().matches("[0-9][1-9]/[0-9][1-9]/[1-9][0-9]{1,3}")) {
            MyDialog.showDialog("Input requirement", null, "Category not null", MyDialog.ERRO);
            return false;
        }
        if (cityField.getText().equals("")) {
            MyDialog.showDialog("Input requirement", null, "City not null", MyDialog.ERRO);
            return false;
        }

        return true;
    }

    public HBox getButtonBar() {

        HBox hBox;

        addBtn = MyLayout.getJFXButton("Add", ImageGetter.ADD, MyLayout.ICON_SIZE);
        addBtn.setOnAction( e -> addChannel());
        clearBtn = MyLayout.getJFXButton("Clear", ImageGetter.CLEAR, MyLayout.ICON_SIZE);
        clearBtn.setOnAction( e -> clearInput());
        searchBtn = MyLayout.getJFXButton("Search", ImageGetter.SEARCH, MyLayout.ICON_SIZE);


        //add to Button Bar
        hBox = new HBox(15);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(addBtn, clearBtn, searchBtn);


        return hBox;

    }

    public void createDateField(){

        beginDateField = MyLayout.getTextField("Begin Date");
        beginDateField.setOnAction( e -> addChannel());

        exampleDate = new Text("Example: 07/04/1999, 07-04-1999");
        exampleDate.setFill(Color.valueOf("#666"));
        exampleDate.setFont(new Font(17));

        dateField = new VBox(MyLayout.SPACE);
        dateField.getChildren().addAll(beginDateField, exampleDate);

    }

    public boolean addChannel() {
        try {
            if (!validatorProgram()) return false;

            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            Boolean isCenter = isCenterBox.isSelected();
            String beginDateString = beginDateField.getText();
            LocalDate beginDate = LocalDate.parse(beginDateField.getText().trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String city = cityField.getText();
            //Convert to date

            ChannelBLL.add(new Channel(id, name, isCenter, beginDate, city));
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
            beginDateField.setText("");
            cityField.setText("");
            isCenterBox.setSelected(false);

            return true;
        } catch (Exception e) {
            MyDialog.showDialog("Clear Input", "Clear Error", e.getMessage(), MyDialog.ERRO);
        } finally {
            return false;
        }
    }

    public static boolean setChannelFromTable(Channel channel){

        try {
            idField.setText(channel.getId());
            nameField.setText(channel.getName());
            isCenterBox.setSelected(channel.isCenter());
            cityField.setText(channel.getCity());
            beginDateField.setText(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(channel.getBeginDate()));

            return true;
        } catch (Exception e){
            MyDialog.showDialog("Set Channel From DataTable",null,"Unknown Error",MyDialog.ERRO);
        }

        return false;
    }
    //====================== End =================================
}

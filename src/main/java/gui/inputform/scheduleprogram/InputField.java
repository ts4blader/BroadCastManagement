package gui.inputform.scheduleprogram;

import bll.ChannelBLL;
import bll.ProgramBLL;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entities.Channel;
import entities.Program;
import entities.ScheduleProgram;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import utilities.ImageGetter;
import utilities.MyDialog;
import utilities.MyLayout;

public class InputField extends VBox {
    // Define const


    //Define var
    private HBox programField;
    private static JFXComboBox<Program> programSelector;
    private JFXTextField programName;

    private HBox channelField;
    private static JFXComboBox<Channel> channelSelector;
    private JFXTextField channelName;

    private HBox dayOfWeekField;
    private VBox[] dayOfWeekSelector;
    private Text[] dayOfWeekLabel;
    private static JFXCheckBox[] dayOfWeekCheckBox;

    private HBox timeField;
    private static JFXTextField hourField;
    private static JFXTextField minuteField;

    private static JFXTextField durationField;

    private static JFXTextField chapterField;

    private HBox buttonBar;

    private JFXButton addBtn;
    private JFXButton clearBtn;
    private JFXButton searchBtn;


    public InputField() {

        //====================== Program Field ======================

        programField = getProgramField();

        //====================== Channel Field ======================

        channelField = getChannelField();

        //====================== Day Of Week Field ======================

        dayOfWeekField = getDayOfWeekField();

        //====================== Time Field ======================

        timeField = getTimeField();

        //====================== Duration Field ======================

        durationField = MyLayout.getTextField("Duration");

        //====================== Chapter Field ======================

        chapterField = MyLayout.getTextField("Chapter");

        //====================== Button Bar ======================

        buttonBar = getButtonBar();

        //====================== Main Layout ======================

        getMainLayout();

    }

    //====================== Controller Section ======================

    public void getMainLayout() {

        this.setPadding(new Insets(30));
        this.setSpacing(40);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(programField, channelField, dayOfWeekField, timeField, durationField, chapterField, buttonBar);

    }

    public HBox getTimeField(){

        HBox hBox = new HBox(MyLayout.SPACE);
        hourField = MyLayout.getTextField("Hour");
        minuteField = MyLayout.getTextField("Minute");

        hBox.getChildren().addAll(hourField, minuteField);

        return hBox;

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

    public HBox getProgramField() {

        HBox hBox;

        programSelector = getProgramSelector();
        programSelector.setPrefHeight(MyLayout.INPUT_HEIGHT);
        programName = MyLayout.getSubTextField("Program ID");
        programName.prefWidthProperty().bind(this.widthProperty().multiply(MyLayout.NAME_FIELD));
        programName.setDisable(true);

        hBox = new HBox(10);
        hBox.getChildren().addAll(programSelector, programName);

        return hBox;
    }

    public HBox getButtonBar() {

        HBox hBox;

        addBtn = MyLayout.getJFXButton("Add", ImageGetter.ADD, MyLayout.ICON_SIZE);
        addBtn.setOnAction(e -> addSchedule());

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
                addSchedule();
        });

        return comboBox;
    }

    public JFXComboBox<Program> getProgramSelector() {

        JFXComboBox<Program> comboBox;

        comboBox = new JFXComboBox<>();
        comboBox.setPromptText("Program");
        comboBox.setLabelFloat(true);
        comboBox.prefWidthProperty().bind(this.widthProperty().multiply(MyLayout.SELECTOR_FIELD));
        comboBox.setItems(ProgramBLL.getAllProgram());

        comboBox.setOnAction(e -> programName.setText(comboBox.getValue().getId()));
        comboBox.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER)
                addSchedule();
        });

        return comboBox;
    }

    public HBox getDayOfWeekField(){

        initDayField();
        HBox hBox = new HBox(MyLayout.SPACE + 3);
        hBox.setAlignment(Pos.CENTER);

        for(int i = 0 ; i < 7 ; i++){
            dayOfWeekLabel[i] = new Text(MyLayout.dayOfWeek[i]);
            dayOfWeekLabel[i].setFont(new Font(MyLayout.SUB_FONT_SIZE));

            dayOfWeekCheckBox[i] = new JFXCheckBox();

            dayOfWeekSelector[i] = new VBox(4);
            dayOfWeekSelector[i].setAlignment(Pos.CENTER);
            dayOfWeekSelector[i].getChildren().addAll(dayOfWeekLabel[i], dayOfWeekCheckBox[i]);

            hBox.getChildren().add(dayOfWeekSelector[i]);

        }

        return hBox;
    }

    public void initDayField(){

        dayOfWeekLabel = new Text[10];
        dayOfWeekCheckBox = new JFXCheckBox[10];
        dayOfWeekSelector = new VBox[10];

    }

    public boolean validatorSchedule() {
        if (programSelector.getValue() == null) {
            MyDialog.showDialog("Input requirement", null, "Name not match Requirement", MyDialog.ERRO);
            return false;
        }
        if (channelSelector.getValue() == null) {
            MyDialog.showDialog("Input requirement", null, "Channel not null", MyDialog.ERRO);
            return false;
        }

        return true;
    }

    public boolean addSchedule() {
        try {
            if (!validatorSchedule()) return false;

            return true;

        } catch (Exception e) {
            MyDialog.showDialog("Can not Add Program", "Unknow Error", e.getMessage(), MyDialog.ERRO);
        } finally {
            return false;
        }

    }

    public boolean clearInput() {
        try {
            programSelector.setValue(new Program());
            channelSelector.setValue(new Channel());

            for (JFXCheckBox checkBox : dayOfWeekCheckBox){
                checkBox.setSelected(false);
            }

            hourField.setText("");
            minuteField.setText("");
            durationField.setText("");
            chapterField.setText("");

            return true;
        } catch (Exception e) {
            MyDialog.showDialog("Clear Input", "Clear Error", e.getMessage(), MyDialog.ERRO);
        } finally {
            return false;
        }
    }

    public static boolean setScheduleFromTable(ScheduleProgram scheduleProgram){

        try {
            programSelector.setValue(ProgramBLL.getProgramById(scheduleProgram.getProgramID()));
            channelSelector.setValue(ChannelBLL.getChannelById(scheduleProgram.getChannelID()));

            for (int i = 0; i < 7 ; i++){
                dayOfWeekCheckBox[i].setSelected(scheduleProgram.getDayOfWeek().get(i));
            }

            hourField.setText(String.valueOf(scheduleProgram.getTime().getHour()));
            minuteField.setText(String.valueOf(scheduleProgram.getTime().getMinute()));

            durationField.setText(String.valueOf(scheduleProgram.getDuration()));
            chapterField.setText(String.valueOf(scheduleProgram.getChapter()));

            return true;
        } catch (Exception e){
            MyDialog.showDialog("Set Schedule From DataTable",null,"Unknown Error",MyDialog.ERRO);
        }
        return false;

    }
    //====================== End =================================
}

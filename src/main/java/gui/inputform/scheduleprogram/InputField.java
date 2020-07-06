package gui.inputform.scheduleprogram;

import bll.KenhTVBLL;
import bll.ChuongTrinhBLL;
import bll.LichPhatSongBLL;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.ChuongTrinh;
import dto.KenhTV;
import dto.LichPhatSong;
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
    private static HBox chuongTrinhField;
    private static JFXComboBox<ChuongTrinh> chuongTrinhSelector;
    private static JFXTextField chuongTrinhName;

    private static HBox kenhTVField;
    private static JFXComboBox<KenhTV> kenhTVSelector;
    private static JFXTextField kenhTVName;

    private static HBox dateField;
    private static JFXTextField dayField;
    private static JFXTextField monthField;

    private static HBox timeField;
    private static JFXTextField hourField;
    private static JFXTextField minuteField;

    private static HBox schedulField1;
    private static JFXTextField durationField;
    private static JFXTextField dayOfWeekField;

    private static HBox schedulField2;
    private static JFXTextField chapterField;
    private static JFXTextField broadField;

    private static HBox buttonBar;

    private static JFXButton addBtn;
    private static JFXButton clearBtn;
    private static JFXButton searchBtn;
    private static JFXButton updateBtn;


    public InputField() {

        //====================== ChuongTrinh Field ======================

        chuongTrinhField = getChuongTrinhField();

        //====================== KenhTV Field ======================

        kenhTVField = getKenhTVField();

        createDateField();

        //====================== Schedule Field ======================

        createScheduleField();

        //====================== Set All Action ======================

        setAction();

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
        this.getChildren().addAll(chuongTrinhField, kenhTVField, dateField, schedulField1, schedulField2, buttonBar);

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

    public HBox getChuongTrinhField() {

        HBox hBox;

        chuongTrinhSelector = getChuongTrinhSelector();
        chuongTrinhSelector.setPrefHeight(MyLayout.INPUT_HEIGHT);
        chuongTrinhName = MyLayout.getSubTextField("ChuongTrinh ID");
        chuongTrinhName.prefWidthProperty().bind(this.widthProperty().multiply(MyLayout.NAME_FIELD));
        chuongTrinhName.setDisable(true);

        hBox = new HBox(10);
        hBox.getChildren().addAll(chuongTrinhSelector, chuongTrinhName);

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

    public void createDateField(){

        dayField = MyLayout.getTextField("Day");
        monthField = MyLayout.getTextField("Month");

        dateField = new HBox(10);
        dateField.setAlignment(Pos.CENTER);

        hourField = MyLayout.getTextField("h");
        minuteField = MyLayout.getTextField("m");

        dateField.getChildren().addAll(dayField, monthField, hourField, minuteField);

        hourField.prefWidthProperty().bind(this.widthProperty().multiply(0.2));
        minuteField.prefWidthProperty().bind(this.widthProperty().multiply(0.2));

        dayField.prefWidthProperty().bind(this.widthProperty().multiply(0.3));
        monthField.prefWidthProperty().bind(this.widthProperty().multiply(0.3));

    }

    public void createScheduleField(){

        durationField = MyLayout.getTextField("Duration");
        durationField.prefWidthProperty().bind(this.widthProperty().multiply(0.5));

        dayOfWeekField = MyLayout.getTextField("Week Day");
        dayOfWeekField.prefWidthProperty().bind(this.widthProperty().multiply(0.5));

        schedulField1 = new HBox(10);
        schedulField1.setAlignment(Pos.CENTER);

        schedulField1.getChildren().addAll(dayOfWeekField, durationField);

        chapterField = MyLayout.getTextField("Chapter");
        chapterField.prefWidthProperty().bind(this.widthProperty().multiply(0.5));

        broadField = MyLayout.getTextField("Broad Time");
        broadField.prefWidthProperty().bind(this.widthProperty().multiply(0.5));

        schedulField2 = new HBox(10);
        schedulField2.setAlignment(Pos.CENTER);

        schedulField2.getChildren().addAll(chapterField, broadField);

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

    public JFXComboBox<ChuongTrinh> getChuongTrinhSelector() {

        JFXComboBox<ChuongTrinh> comboBox;

        comboBox = new JFXComboBox<>();
        comboBox.setPromptText("ChuongTrinh");
        comboBox.setLabelFloat(true);
        comboBox.prefWidthProperty().bind(this.widthProperty().multiply(MyLayout.SELECTOR_FIELD));
        comboBox.setItems(ChuongTrinhBLL.getAll());

        comboBox.setOnAction(e -> chuongTrinhName.setText(comboBox.getValue().getMaCT()));
        comboBox.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER)
                save();
        });

        return comboBox;
    }

    public boolean clearInput() {
        try {
            chuongTrinhSelector.setValue(new ChuongTrinh());
            kenhTVSelector.setValue(new KenhTV());

            hourField.setText("");
            minuteField.setText("");

            dayField.setText("");
            monthField.setText("");

            durationField.setText("");
            chapterField.setText("");

            dayOfWeekField.setText("");
            broadField.setText("");

            return true;
        } catch (Exception e) {
            MyDialog.showDialog("Clear Input", "Clear Error", e.getMessage(), MyDialog.ERRO);
        } finally {
            return false;
        }
    }

    public static boolean setScheduleFromTable(LichPhatSong scheduleChuongTrinh){

        try {
            chuongTrinhSelector.setValue(ChuongTrinhBLL.get(scheduleChuongTrinh.getMaCT()));
            kenhTVSelector.setValue(KenhTVBLL.get(scheduleChuongTrinh.getMaKenh()));

            dayField.setText(String.valueOf(scheduleChuongTrinh.getNgay()));
            monthField.setText(String.valueOf(scheduleChuongTrinh.getThang()));

            hourField.setText(String.valueOf(scheduleChuongTrinh.getGio()));
            minuteField.setText(String.valueOf(scheduleChuongTrinh.getPhut()));

            dayOfWeekField.setText(String.valueOf(scheduleChuongTrinh.getThu()));
            durationField.setText(String.valueOf(scheduleChuongTrinh.getThoiLuong()));

            chapterField.setText(String.valueOf(scheduleChuongTrinh.getKy_Tap()));
            broadField.setText(String.valueOf(scheduleChuongTrinh.getLanPhatSong()));



            return true;
        } catch (Exception e){
            MyDialog.showDialog("Set Schedule From DataTable",null,"Unknown Error",MyDialog.ERRO);
        }
        return false;

    }

    public static LichPhatSong getObjFormField(){

        String programID = chuongTrinhSelector.getValue().getMaCT();
        String channelID = kenhTVSelector.getValue().getMaKenh();
        String dayOfWeek = dayOfWeekField.getText().trim();

        int day = Integer.valueOf(dayField.getText());
        int month = Integer.valueOf(monthField.getText());

        int hour = Integer.valueOf(hourField.getText());
        int minute = Integer.valueOf(minuteField.getText());

        int duration = Integer.valueOf(durationField.getText().trim());
        int chapter = Integer.valueOf(chapterField.getText().trim());
        int broadTimes = Integer.valueOf(broadField.getText().trim());

        return new LichPhatSong(channelID, programID, dayOfWeek, month, day, hour, minute, duration, chapter, broadTimes);

    }

    public void setAction(){

        chuongTrinhSelector.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER)
                save();
        });
        kenhTVSelector.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER)
                save();
        });
        dayField.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER)
                save();
        });
        monthField.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER)
                save();
        });
        hourField.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER)
                save();
        });
        minuteField.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER)
                save();
        });
        chapterField.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER)
                save();
        });
        dayOfWeekField.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER)
                save();
        });
        durationField.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER)
                save();
        });
        broadField.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER)
                save();
        });


    }
    //====================== Controller ======================

    public static boolean validator() {

        if (chuongTrinhSelector.getValue() == null) {
            MyDialog.showDialog("Input requirement", null, "Program not Null", MyDialog.ERRO);
            return false;
        }
        if (kenhTVSelector.getValue() == null) {
            MyDialog.showDialog("Input requirement", null, "TV Channel not Null", MyDialog.ERRO);
            return false;
        }

        return true;
    }

    public static void save() {

        if(!validator()) return;
        LichPhatSongBLL.save(getObjFormField());
        chuongTrinhSelector.setFocusTraversable(true);

    }

    public static ObservableList<LichPhatSong> search(){
        return LichPhatSongBLL.get(getObjFormField());
    }

    public static void update(){
        LichPhatSongBLL.update(getObjFormField());
        MyDialog.showDialog("Update",null,"Update Success",MyDialog.INFO);
    }
}

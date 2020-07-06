package gui.inputform.scheduleprogram;

import bll.*;
import dto.ChuongTrinh;
import dto.KenhTV;
import dto.LichPhatSong;
import dto.QuocGia;
import gui.inputform.scheduleprogram.InputField;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import utilities.ImageGetter;
import utilities.MyDialog;
import utilities.MyLayout;

import java.time.LocalDate;
import java.time.LocalTime;

public class DataTable extends VBox {


    //TableView
    private static TableView<LichPhatSong> tableView;
    //ChuongTrinh col
    private static TableColumn<LichPhatSong, ChuongTrinh> chuongTrinhCol;
    //KenhTV Col
    private static TableColumn<LichPhatSong, KenhTV> kenhCol;
    //Duration Col
    private static TableColumn<LichPhatSong, Integer> durationCol;
    //Chapter Col
    private static TableColumn<LichPhatSong, Integer> chapterCol;
    //day col
    private static TableColumn<LichPhatSong, Integer> dayCol;
    //month col
    private static TableColumn<LichPhatSong, Integer> monthCol;
    //hour col
    private static TableColumn<LichPhatSong, Integer> hourCol;
    //minute col
    private static TableColumn<LichPhatSong, Integer> minuteCol;
    //day of week col
    private static TableColumn<LichPhatSong, String> dayOfWeekCol;
    //Broad Time
    private static TableColumn<LichPhatSong, Integer> broadTimeCol;
    //Button Bar
    private static HBox buttonBar;
    //Buttons
    private static Button refreshBtn;
    private static Button deleteBtn;
    private static Button deleteAllBtn;


    public DataTable() {

        //====================== ChuongTrinh Col ======================

        chuongTrinhCol = getChuongTrinhCol();

        //====================== KenhTV col ======================

        kenhCol = getKenhCol();

        //====================== Date Col ======================

        createDateCol();

        //====================== Time Col ======================

        createTimeCol();

        //====================== Day Of Week Col ======================

        dayOfWeekCol = getStringCol("DOW", "thu");

        //====================== Duration Col ======================

        durationCol = getTextCol("Duration", "thoiLuong");

        //====================== Chapter Col ======================

        chapterCol = getTextCol("Chapter", "ky_Tap");

        //====================== Broad Time ======================

        broadTimeCol = getTextCol("Broad Time", "lanPhatSong");

        //====================== TableView ======================

        tableView = getTableView();

        //====================== Button Bar ======================

        buttonBar = getButtonBar();

        //======================== Main Layout =============================

        getMainLayout();

    }

    public void createDateCol() {

        dayCol = getTextCol("Day", "ngay");

        monthCol = getTextCol("Month","thang");


    }

    public void createTimeCol() {

        hourCol = getTextCol("Hour", "gio");

        minuteCol = getTextCol("Minute","phut");


    }

    public TableColumn<LichPhatSong, Integer> getTextCol(String text, String property) {

        TableColumn<LichPhatSong, Integer> tableColumn;
        tableColumn = new TableColumn<>(text);

        tableColumn.setCellValueFactory(new PropertyValueFactory<>(property));
        tableColumn.setMinWidth(50);


        return tableColumn;
    }

    public TableColumn<LichPhatSong, String> getStringCol(String text, String property) {

        TableColumn<LichPhatSong, String> tableColumn;
        tableColumn = new TableColumn<>(text);

        tableColumn.setCellValueFactory(new PropertyValueFactory<>(property));
        tableColumn.setMinWidth(50);


        return tableColumn;
    }

    public TableColumn<LichPhatSong, ChuongTrinh> getChuongTrinhCol() {
        //Tao Column
        TableColumn<LichPhatSong, ChuongTrinh> tableColumn;
        tableColumn = new TableColumn<>("ChuongTrinh");
        //Set Cach hien thi du lieu
        tableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LichPhatSong, ChuongTrinh>, ObservableValue<ChuongTrinh>>() {
            @Override
            public ObservableValue<ChuongTrinh> call(TableColumn.CellDataFeatures<LichPhatSong, ChuongTrinh> param) {
                //Get Lich Phat Song
                LichPhatSong LichPhatSong = param.getValue();
                //Tra ve 1 Property la ma Chuong Trinh
                return new SimpleObjectProperty<>(ChuongTrinhBLL.get(LichPhatSong.getMaCT()));
            }
        });
        //Set do rong toi thieu
        tableColumn.setMinWidth(100);

        return tableColumn;


    }

    public TableColumn<LichPhatSong, KenhTV> getKenhCol() {

        TableColumn<LichPhatSong, KenhTV> tableColumn;
        tableColumn = new TableColumn<>("KenhTV");

        tableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LichPhatSong, KenhTV>, ObservableValue<KenhTV>>() {
            @Override
            public ObservableValue<KenhTV> call(TableColumn.CellDataFeatures<LichPhatSong, KenhTV> param) {
                LichPhatSong LichPhatSong = param.getValue();

                return new SimpleObjectProperty<KenhTV>(KenhTVBLL.get(LichPhatSong.getMaKenh()));
            }
        });

        tableColumn.setMinWidth(100);

        return tableColumn;


    }

    public HBox getButtonBar() {

        HBox hBox = new HBox(MyLayout.SPACE);

        createButton();

        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(refreshBtn, deleteBtn, deleteAllBtn);

        return hBox;

    }

    public TableView<LichPhatSong> getTableView() {

        TableView<LichPhatSong> tableView = new TableView<>();

        tableView.setItems(LichPhatSongBLL.getAll());
        tableView.setMinHeight(450);
        tableView.setEditable(true);
        tableView.prefHeightProperty().bind(this.heightProperty().divide(5.5 / 4.5));
        tableView.getColumns().addAll(chuongTrinhCol, kenhCol, dayOfWeekCol,
                dayCol, monthCol,
                hourCol, minuteCol
                ,durationCol, broadTimeCol, chapterCol);
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<LichPhatSong>() {
            @Override
            public void changed(ObservableValue<? extends LichPhatSong> observable, LichPhatSong oldValue, LichPhatSong newValue) {
                if (tableView.getSelectionModel().getSelectedItem() != null) {
                    InputField.setScheduleFromTable(newValue);
                }
            }
        });
        return tableView;
    }

    public void getMainLayout() {

        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(20);
        this.getChildren().addAll(tableView, buttonBar);


    }

    //====================== Controller ======================

    public void createButton(){


        deleteBtn = MyLayout.getButton("Delete", ImageGetter.DELETE, MyLayout.ICON_SIZE);
        deleteBtn.setOnAction(e -> delete());

        deleteAllBtn = MyLayout.getButton("Delete All", ImageGetter.DELETE, MyLayout.ICON_SIZE);
        deleteAllBtn.setOnAction(e -> deleteAll());

        refreshBtn = MyLayout.getButton("Refresh", ImageGetter.REFRESH, MyLayout.ICON_SIZE);
        refreshBtn.setOnAction(e -> tableView.setItems(LichPhatSongBLL.getAll()));

    }

    public static void delete(){

        ObservableList<LichPhatSong> list = tableView.getSelectionModel().getSelectedItems();
        if(list == null)
            MyDialog.showDialog("Non Selected Item",null, "Select an Item Pls",MyDialog.ERRO);
        for(LichPhatSong item : list)
            LichPhatSongBLL.delete(item);

    }

    public static void deleteAll(){

        ObservableList<LichPhatSong> list = tableView.getItems();
        for(LichPhatSong item : list)
            LichPhatSongBLL.delete(item);
        MyDialog.showDialog("Delete Success",null, "Done!", MyDialog.INFO);

    }
}

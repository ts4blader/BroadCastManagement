package gui.inputform.scheduleprogram;

import bll.*;
import entities.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import utilities.ImageGetter;
import utilities.MyLayout;

import java.time.LocalTime;
import java.util.ArrayList;

public class DataTable extends VBox {


    //TableView
    private TableView<ScheduleProgram> tableView;
    //Program col
    private TableColumn<ScheduleProgram, Program> programCol;
    //Channel Col
    private TableColumn<ScheduleProgram, Channel> channelCol;
    //Time Col
    private TableColumn<ScheduleProgram, LocalTime> timeCol;
    //Day Of Week Col
    private TableColumn<ScheduleProgram, Boolean> monCol;
    private TableColumn<ScheduleProgram, Boolean> tuesCol;
    private TableColumn<ScheduleProgram, Boolean> wedCol;
    private TableColumn<ScheduleProgram, Boolean> thusCol;
    private TableColumn<ScheduleProgram, Boolean> friCol;
    private TableColumn<ScheduleProgram, Boolean> satCol;
    private TableColumn<ScheduleProgram, Boolean> sunCol;
    //Duration Col
    private TableColumn<ScheduleProgram, Integer> durationCol;
    //Chapter Col
    private TableColumn<ScheduleProgram, Integer> chapterCol;
    //Button Bar
    private HBox buttonBar;
    //Buttons
    private Button saveBtn;
    private Button undoBtn;
    private Button redoBtn;
    private Button deleteBtn;
    private Button deleteAllBtn;


    public DataTable() {

        //====================== Program Coll ======================

        programCol = getProgramCol();

        //====================== Channel col ======================

        channelCol = getChannelCol();

        //====================== Time Col ======================

        timeCol = getTimeCol();

        //====================== MonDay Col ======================
        int i = 0;
        monCol = getDayOfWeekCol(i++, "2");
        tuesCol = getDayOfWeekCol(i++, "3");
        wedCol = getDayOfWeekCol(i++, "4");
        thusCol = getDayOfWeekCol(i++, "5");
        friCol = getDayOfWeekCol(i++, "6");
        satCol = getDayOfWeekCol(i++, "7");
        sunCol = getDayOfWeekCol(i++, "CN");

        //====================== Duration Col ======================

        durationCol = getTextCol("Duration", "duration");

        //====================== Chapter Col ======================

        chapterCol = getTextCol("Chapter","chapter");

        //====================== TableView ======================

        tableView = getTableView();

        //====================== Button Bar ======================

        buttonBar = getButtonBar();

        //======================== Main Layout =============================

        getMainLayout();

    }

    public TableColumn<ScheduleProgram, Integer> getTextCol(String text, String property) {

        TableColumn<ScheduleProgram, Integer> tableColumn;
        tableColumn = new TableColumn<>(text);

        tableColumn.setCellValueFactory(new PropertyValueFactory<>(property));
        tableColumn.setMinWidth(50);


        return tableColumn;
    }

    public TableColumn<ScheduleProgram, Program> getProgramCol() {

        TableColumn<ScheduleProgram, Program> tableColumn;
        tableColumn = new TableColumn<>("Program");

        tableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ScheduleProgram, Program>, ObservableValue<Program>>() {
            @Override
            public ObservableValue<Program> call(TableColumn.CellDataFeatures<ScheduleProgram, Program> param) {
                ScheduleProgram scheduleProgram = param.getValue();

                return new SimpleObjectProperty<>(ProgramBLL.getProgramById(scheduleProgram.getProgramID()));
            }
        });

        tableColumn.setMinWidth(100);

        return tableColumn;


    }

    public TableColumn<ScheduleProgram, Channel> getChannelCol() {

        TableColumn<ScheduleProgram, Channel> tableColumn;
        tableColumn = new TableColumn<>("Channel");

        tableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ScheduleProgram, Channel>, ObservableValue<Channel>>() {
            @Override
            public ObservableValue<Channel> call(TableColumn.CellDataFeatures<ScheduleProgram, Channel> param) {
                ScheduleProgram scheduleProgram = param.getValue();

                return new SimpleObjectProperty<Channel>(ChannelBLL.getChannelById(scheduleProgram.getChannelID()));
            }
        });

        tableColumn.setMinWidth(100);

        return tableColumn;


    }

    public TableColumn<ScheduleProgram, LocalTime> getTimeCol(){

        TableColumn<ScheduleProgram, LocalTime> tableColumn = new TableColumn<>("Time");

        tableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ScheduleProgram, LocalTime>, ObservableValue<LocalTime>>() {
            @Override
            public ObservableValue<LocalTime> call(TableColumn.CellDataFeatures<ScheduleProgram, LocalTime> param) {
                ScheduleProgram scheduleProgram = param.getValue();

                return new SimpleObjectProperty<LocalTime>(scheduleProgram.getTime());

            }
        });
        return tableColumn;
    }

    public TableColumn<ScheduleProgram, Boolean> getDayOfWeekCol(int index, String label){

        TableColumn<ScheduleProgram, Boolean> tableColumn = new TableColumn<>(label);

        tableColumn.getStyleClass().addAll("table-cell-center", "font-bold");
        tableColumn.setMinWidth(20);
        tableColumn.setPrefWidth(30);
        tableColumn.setCellValueFactory( cellData -> cellData.getValue().getDayOfWeekProperty(index));

        tableColumn.setCellFactory(column ->{
            return new TableCell<ScheduleProgram, Boolean>(){
                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    if(item == null || empty)
                        setText("");
                    else
                        if(item == true) setText("+");
                        else  setText("-");
                }
            };
        });

        return tableColumn;
    }

    public HBox getButtonBar() {

        HBox hBox = new HBox(MyLayout.SPACE);

        saveBtn = MyLayout.getButton("Save", ImageGetter.SAVE, MyLayout.ICON_SIZE);

        redoBtn = MyLayout.getButton("Redo", ImageGetter.REDO, MyLayout.ICON_SIZE);

        undoBtn = MyLayout.getButton("Undo", ImageGetter.UNDO, MyLayout.ICON_SIZE);

        deleteBtn = MyLayout.getButton("Delete", ImageGetter.DELETE, MyLayout.ICON_SIZE);

        deleteAllBtn = MyLayout.getButton("Delete All", ImageGetter.DELETE, MyLayout.ICON_SIZE);

        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(saveBtn, undoBtn, redoBtn, deleteBtn, deleteAllBtn);

        return hBox;

    }

    public TableView<ScheduleProgram> getTableView() {

        TableView<ScheduleProgram> tableView = new TableView<>();

        tableView.setItems(ScheduleProgramBLL.getAllSchedule());
        tableView.setMinHeight(450);
        tableView.setEditable(true);
        tableView.prefHeightProperty().bind(this.heightProperty().divide(5.5 / 4.5));
        tableView.getColumns().addAll(programCol, channelCol, monCol, tuesCol,
                wedCol, thusCol, friCol, satCol, sunCol, timeCol, durationCol, chapterCol);
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ScheduleProgram>() {
            @Override
            public void changed(ObservableValue<? extends ScheduleProgram> observable, ScheduleProgram oldValue, ScheduleProgram newValue) {
                if(tableView.getSelectionModel().getSelectedItem() != null){
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
}

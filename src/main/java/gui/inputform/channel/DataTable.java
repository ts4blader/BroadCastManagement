package gui.inputform.channel;

import bll.CategoryBLL;
import bll.ChannelBLL;
import bll.ProducerBLL;
import bll.ProgramBLL;
import com.mysql.cj.xdevapi.Table;
import entities.*;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import utilities.ImageGetter;
import utilities.MyLayout;

import javax.security.auth.callback.LanguageCallback;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DataTable extends VBox {

    public final static int ICON_SIZE = 15;

    //TableView
    private TableView<Channel> tableView;
    //ID Col
    private TableColumn<Channel, String> idCol;
    //Name Col
    private TableColumn<Channel, String> nameCol;
    //is Center Col
    private TableColumn<Channel, Boolean> isCenterCol;
    //Date Col
    private TableColumn<Channel, LocalDate> beginDateCol;
    //City Col
    private TableColumn<Channel, String> cityCol;
    //Button Bar
    private HBox buttonBar;
    //Buttons
    private Button saveBtn;
    private Button undoBtn;
    private Button redoBtn;
    private Button deleteBtn;
    private Button deleteAllBtn;


    public DataTable() {

        //====================== ID Col ======================

        idCol = getTextCol("#", "id");
        idCol.setStyle("-fx-alignment: CENTER-RIGHT");

        //====================== Name Col ======================

        nameCol = getTextCol("Name", "name");

        //====================== isCenter CheckBox ======================

        createIsCenterCol();

        //====================== Begin Date Col ======================

        createBeginDateCol();

        //====================== City Col ======================

        cityCol = getTextCol("City","city");

        //====================== Button Bar ======================

        buttonBar = getButtonBar();

        //===================== tableView =======================

        tableView = getTableView();

        //======================== Main Layout =============================

        getMainLayout();

    }

    public TableColumn<Channel, String> getTextCol(String text, String property) {

        TableColumn<Channel, String> tableColumn;
        tableColumn = new TableColumn<>(text);

        tableColumn.setCellValueFactory(new PropertyValueFactory<>(property));
        tableColumn.setMinWidth(50);

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

    public void createIsCenterCol(){

        isCenterCol = new TableColumn<>("Center");

        isCenterCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Channel, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Channel, Boolean> param) {
                Channel channel = param.getValue();

                SimpleBooleanProperty property = new SimpleBooleanProperty(channel.isCenter());

                return property;
            }
        });

        isCenterCol.setCellFactory(new Callback<TableColumn<Channel, Boolean>, TableCell<Channel, Boolean>>() {
            @Override
            public TableCell<Channel, Boolean> call(TableColumn<Channel, Boolean> param) {
                CheckBoxTableCell<Channel, Boolean> cell = new CheckBoxTableCell<>();
                cell.getStyleClass().addAll("table-cell-center");
                cell.setDisable(true);
                return cell;
            }
        });


    }

    public void createBeginDateCol() {
        beginDateCol = new TableColumn<>("Begin");
        beginDateCol.setCellValueFactory(cellData -> cellData.getValue().getBeginDateProperty());
        beginDateCol.setCellFactory(column -> {
            return new TableCell<Channel, LocalDate>(){
                @Override
                protected void updateItem(LocalDate item, boolean empty) {
                    if(item == null || empty)
                        setText("");
                    else
                        setText(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(item));
                }
            };
        });
        beginDateCol.setMinWidth(100);
    }

    public TableView<Channel> getTableView() {

        TableView<Channel> tableView = new TableView<>();

        tableView.setItems(ChannelBLL.getAllChannel());
        tableView.setMinHeight(450);
        tableView.setEditable(true);
        tableView.prefHeightProperty().bind(this.heightProperty().divide(5.5 / 4.5));
        tableView.getColumns().addAll(idCol, nameCol, isCenterCol, beginDateCol, cityCol);
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Channel>() {
            @Override
            public void changed(ObservableValue<? extends Channel> observable, Channel oldValue, Channel newValue) {
                if(tableView.getSelectionModel().getSelectedItem() != null){
                    InputField.setChannelFromTable(newValue);
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

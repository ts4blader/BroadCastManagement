package gui.inputform.channel;

import bll.KenhTVBLL;
import bll.LinhVucBLL;
import bll.TheLoaiBLL;
import dto.KenhTV;
import dto.LinhVuc;
import dto.TheLoai;
import gui.inputform.channel.InputField;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import utilities.ImageGetter;
import utilities.MyDialog;
import utilities.MyLayout;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataTable extends VBox {

    public final static int ICON_SIZE = 15;

    //TableView
    private static TableView<KenhTV> tableView;
    //ID Col
    private static TableColumn<KenhTV, String> idCol;
    //Name Col
    private static TableColumn<KenhTV, String> nameCol;
    //is Center Col
    private static TableColumn<KenhTV, Integer> isCenterCol;
    //Major Col
    private static TableColumn<KenhTV, LinhVuc> majorCol;
    //City Col
    private static TableColumn<KenhTV, String> cityCol;
    //Button Bar
    private static HBox buttonBar;
    //Buttons
    private static Button deleteBtn;
    private static Button deleteAllBtn;
    private static Button refreshBtn;


    public DataTable() {

        //====================== ID Col ======================

        idCol = getTextCol("#", "maKenh");
        idCol.setStyle("-fx-alignment: CENTER-RIGHT");

        //====================== Name Col ======================

        nameCol = getTextCol("Name", "tenKenh");

        //====================== isCenter CheckBox ======================

        createIsCenterCol();

        //====================== Major Col ======================

        createMajorCol();

        //====================== City Col ======================

        cityCol = getTextCol("City","tinhTP");

        //====================== Button Bar ======================

        buttonBar = getButtonBar();

        //===================== tableView =======================

        tableView = getTableView();

        //======================== Main Layout =============================

        getMainLayout();

    }

    public TableColumn<KenhTV, String> getTextCol(String text, String property) {

        TableColumn<KenhTV, String> tableColumn;
        tableColumn = new TableColumn<>(text);

        tableColumn.setCellValueFactory(new PropertyValueFactory<>(property));
        tableColumn.setMinWidth(50);

        return tableColumn;
    }

    public HBox getButtonBar() {

        HBox hBox = new HBox(MyLayout.SPACE);

        createButton();

        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(refreshBtn, deleteBtn, deleteAllBtn);

        return hBox;

    }

    public void createIsCenterCol(){

        isCenterCol = new TableColumn<KenhTV, Integer>("Center");

        isCenterCol.setCellValueFactory(cellData -> cellData.getValue().getThuocTUProperty());

        isCenterCol.setCellFactory( column -> {
            return new TableCell<KenhTV, Integer>(){
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    if(item == null || empty)
                        setText("");
                    else if(item == 1) setText("True");
                        else setText("False");
                }
            };
        });


    }

    public void createButton(){


        deleteBtn = MyLayout.getButton("Delete", ImageGetter.DELETE, MyLayout.ICON_SIZE);
        deleteBtn.setOnAction(e -> delete());

        deleteAllBtn = MyLayout.getButton("Delete All", ImageGetter.DELETE, MyLayout.ICON_SIZE);
        deleteAllBtn.setOnAction(e -> deleteAll());

        refreshBtn = MyLayout.getButton("Refresh", ImageGetter.REFRESH, MyLayout.ICON_SIZE);
        refreshBtn.setOnAction(e -> tableView.setItems(KenhTVBLL.getAll()));

    }

    public void createMajorCol(){

        majorCol = new TableColumn<>("LinhVuc");
        majorCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<KenhTV, LinhVuc>, ObservableValue<LinhVuc>>() {
            @Override
            public ObservableValue<LinhVuc> call(TableColumn.CellDataFeatures<KenhTV, LinhVuc> param) {
                KenhTV kenhTV = param.getValue();
                return new SimpleObjectProperty<>(LinhVucBLL.get(kenhTV.getMaLV()));
            }
        });

    }

    public TableView<KenhTV> getTableView() {

        TableView<KenhTV> tableView = new TableView<>();

        tableView.setItems(KenhTVBLL.getAll());
        tableView.setMinHeight(450);
        tableView.setEditable(true);
        tableView.prefHeightProperty().bind(this.heightProperty().divide(5.5 / 4.5));
        tableView.getColumns().addAll(idCol, nameCol, isCenterCol, cityCol, majorCol);
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<KenhTV>() {
            @Override
            public void changed(ObservableValue<? extends KenhTV> observable, KenhTV oldValue, KenhTV newValue) {
                if(tableView.getSelectionModel().getSelectedItem() != null){
                    InputField.setObjFromTable(newValue);
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

    public static void delete(){

        ObservableList<KenhTV> list = tableView.getSelectionModel().getSelectedItems();
        if(list == null)
            MyDialog.showDialog("Non Selected Item",null, "Select an Item Pls",MyDialog.ERRO);
        for(KenhTV item : list)
            KenhTVBLL.delete(item);

    }

    public static void deleteAll(){

        ObservableList<KenhTV> list = tableView.getItems();
        for(KenhTV item : list)
            KenhTVBLL.delete(item);
        MyDialog.showDialog("Delete Success",null, "Done!", MyDialog.INFO);

    }

}

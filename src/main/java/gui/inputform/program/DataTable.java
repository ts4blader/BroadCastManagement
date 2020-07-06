package gui.inputform.program;

import bll.*;
import dto.*;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import utilities.ImageGetter;
import utilities.MyDialog;
import utilities.MyLayout;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class DataTable extends VBox {

    public final static int ICON_SIZE = 15;

    //TableView
    private static TableView<ChuongTrinh> tableView;
    //ID Col
    private static TableColumn<ChuongTrinh, String> idCol;
    //Name Col
    private static TableColumn<ChuongTrinh, String> nameCol;
    //TheLoail
    private static TableColumn<ChuongTrinh, TheLoai> theLoaiCol;
    //NhaSX Col
    private static TableColumn<ChuongTrinh, NhaSX> nhaSXCol;
    //QuocGia Col
    private static TableColumn<ChuongTrinh, QuocGia> quocGiaCol;
    //Button Bar
    private static HBox buttonBar;
    //Buttons
    private static Button deleteBtn;
    private static Button deleteAllBtn;
    private static Button refreshBtn;


    public DataTable() {

        //====================== ID Col ======================

        idCol = getTextCol("#", "maCT");
        idCol.setStyle("-fx-alignment: CENTER-RIGHT");

        //====================== Name Col ======================

        nameCol = getTextCol("Name", "tenCT");

        //====================== Nha SX Col ======================

        nhaSXCol = getNhaSXCol();

        //====================== The Loai Col ======================

        theLoaiCol = getTheLoaiCol();

        //====================== Button Bar ======================

        buttonBar = getButtonBar();

        //===================== tableView =======================

        tableView = getTableView();

        //======================== Main Layout =============================

        getMainLayout();

    }

    public TableColumn<ChuongTrinh, String> getTextCol(String text, String property) {

        TableColumn<ChuongTrinh, String> tableColumn;
        tableColumn = new TableColumn<>(text);

        tableColumn.setCellValueFactory(new PropertyValueFactory<>(property));
        tableColumn.setMinWidth(50);

        //Commit
//        tableColumn.setOnEditCommit((TableColumn.CellEditEvent<ChuongTrinh, String> e) -> {
//            TablePosition<ChuongTrinh, String> pos = e.getTablePosition();
//
//            String newValue = e.getNewValue();
//
//            int row = pos.getRow();
//            ChuongTrinh ChuongTrinh = e.getTableView().getItems().get(row);
//
//            ChuongTrinhBLL.update(ChuongTrinh);
//        });

        return tableColumn;
    }

    public TableColumn<ChuongTrinh, TheLoai> getTheLoaiCol() {

        TableColumn<ChuongTrinh, TheLoai> tableColumn;
        tableColumn = new TableColumn<>("The Loai");

        tableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ChuongTrinh, TheLoai>, ObservableValue<TheLoai>>() {
            @Override
            public ObservableValue<TheLoai> call(TableColumn.CellDataFeatures<ChuongTrinh, TheLoai> param) {
                ChuongTrinh chuongTrinh = param.getValue();
                return new SimpleObjectProperty<>(TheLoaiBLL.get(chuongTrinh.getMaTheLoai()));
            }
        });
        //set cell factory
//        tableColumn.setCellFactory(ComboBoxTableCell.forTableColumn(TheLoai.getAllTheLoai);
        //Commit
//        tableColumn.setOnEditCommit((TableColumn.CellEditEvent<ChuongTrinh, TheLoai) -> {
//            TablePosition<ChuongTrinh, TheLoaios = e.getTablePosition();
//            //get new value
//            TheLoaiwTheLoaie.getNewValue();
//            //get row
//            int row = pos.getRow();
//            ChuongTrinh ChuongTrinh = e.getTableView().getItems().get(row);
//
//            ChuongTrinhBLL.update(ChuongTrinh);
//        });

        tableColumn.setMinWidth(100);

        return tableColumn;


    }

    public TableColumn<ChuongTrinh, NhaSX> getNhaSXCol() {

        TableColumn<ChuongTrinh, NhaSX> tableColumn;
        tableColumn = new TableColumn<>("NhaSX");

        tableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ChuongTrinh, NhaSX>, ObservableValue<NhaSX>>() {
            @Override
            public ObservableValue<NhaSX> call(TableColumn.CellDataFeatures<ChuongTrinh, NhaSX> param) {
                ChuongTrinh ChuongTrinh = param.getValue();

                NhaSX nhaSX = NhaSXBLL.get(ChuongTrinh.getMaNSX());

                return new SimpleObjectProperty<NhaSX>(nhaSX);
            }
        });

//        tableColumn.setCellFactory(ComboBoxTableCell.forTableColumn(NhaSXBLL.getAllNhaSX()));

//        tableColumn.setOnEditCommit((TableColumn.CellEditEvent<ChuongTrinh, NhaSX> e) -> {
//            TablePosition<ChuongTrinh, NhaSX> position = e.getTablePosition();
//
//            int row = position.getRow();
//
//            ChuongTrinh ChuongTrinh = e.getTableView().getItems().get(row);
//
//            //update NhaSX
//        });

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

    public TableView<ChuongTrinh> getTableView() {

        TableView<ChuongTrinh> tableView = new TableView<>();

        tableView.setItems(ChuongTrinhBLL.getAll());
        tableView.setMinHeight(450);
        tableView.setEditable(true);
        tableView.prefHeightProperty().bind(this.heightProperty().divide(5.5 / 4.5));
        tableView.getColumns().addAll(idCol, nameCol, theLoaiCol, nhaSXCol);
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ChuongTrinh>() {
            @Override
            public void changed(ObservableValue<? extends ChuongTrinh> observable, ChuongTrinh oldValue, ChuongTrinh newValue) {
                if(tableView.getSelectionModel().getSelectedItem() != null){
                    InputField.setObjFromField(newValue);
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

    public static void refreshTable(){
        tableView.setItems(ChuongTrinhBLL.getAll());
    }

    public static void setItems(ObservableList<ChuongTrinh> items){
        tableView.setItems(items);
    }

    //====================== Controller ======================

    public void createButton(){


        deleteBtn = MyLayout.getButton("Delete", ImageGetter.DELETE, MyLayout.ICON_SIZE);
        deleteBtn.setOnAction(e -> delete());

        deleteAllBtn = MyLayout.getButton("Delete All", ImageGetter.DELETE, MyLayout.ICON_SIZE);
        deleteAllBtn.setOnAction(e -> deleteAll());

        refreshBtn = MyLayout.getButton("Refresh", ImageGetter.REFRESH, MyLayout.ICON_SIZE);
        refreshBtn.setOnAction(e -> tableView.setItems(ChuongTrinhBLL.getAll()));

    }

    public static void delete(){

        ObservableList<ChuongTrinh> list = tableView.getSelectionModel().getSelectedItems();
        if(list == null)
            MyDialog.showDialog("Non Selected Item",null, "Select an Item Pls",MyDialog.ERRO);
        for(ChuongTrinh item : list)
            ChuongTrinhBLL.delete(item);
        refreshTable();

    }

    public static void deleteAll(){

        ObservableList<ChuongTrinh> list = tableView.getItems();
        for(ChuongTrinh item : list)
            ChuongTrinhBLL.delete(item);
        refreshTable();
        MyDialog.showDialog("Delete Success",null, "Done!", MyDialog.INFO);

    }
}

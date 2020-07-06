package gui.inputform.producer;

import bll.QuocGiaBLL;
import bll.NhaSXBLL;
import dto.NhaSX;
import dto.QuocGia;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import utilities.ImageGetter;
import utilities.MyDialog;
import utilities.MyLayout;

public class DataTable extends VBox {

    public final static int ICON_SIZE = 15;

    //TableView
    private static TableView<NhaSX> tableView;
    //ID Col
    private static TableColumn<NhaSX, String> idCol;
    //Name Col
    private static TableColumn<NhaSX, String> nameCol;
    //QuocGia Col
    private static TableColumn<NhaSX, QuocGia> quocGiaCol;
    //Button Bar
    private static HBox buttonBar;
    //Buttons
    private static Button deleteBtn;
    private static Button deleteAllBtn;
    private static Button refreshBtn;


    public DataTable() {

        //====================== ID Col ======================

        idCol = getTextCol("#", "maNSX");
        idCol.setStyle("-fx-alignment: CENTER-RIGHT");

        //====================== Name Col ======================

        nameCol = getTextCol("Name", "tenNSX");
        nameCol.setMinWidth(200);

        //====================== Quoc Gia ======================

        createQuocGiaCol();

        //====================== Button Bar ======================

        buttonBar = getButtonBar();

        //===================== tableView =======================

        tableView = getTableView();

        //======================== Main Layout =============================

        getMainLayout();

    }

    public TableColumn<NhaSX, String> getTextCol(String text, String property) {

        TableColumn<NhaSX, String> tableColumn;
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

    public void createQuocGiaCol(){

        quocGiaCol = new TableColumn<>("QuocGia");
        quocGiaCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<NhaSX, QuocGia>, ObservableValue<QuocGia>>() {
            @Override
            public ObservableValue<QuocGia> call(TableColumn.CellDataFeatures<NhaSX, QuocGia> param) {

                NhaSX NhaSX = param.getValue();
                 return new SimpleObjectProperty<>(QuocGiaBLL.get(NhaSX.getMaQuocGia()));

            }
        });
        quocGiaCol.setMinWidth(200);
    }

    public TableView<NhaSX> getTableView() {

        TableView<NhaSX> tableView = new TableView<>();

        tableView.setItems(NhaSXBLL.getAll());
        tableView.setMinHeight(450);
        tableView.setEditable(true);
        tableView.prefHeightProperty().bind(this.heightProperty().divide(5.5 / 4.5));
        tableView.getColumns().addAll(idCol, nameCol, quocGiaCol);
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<NhaSX>() {
            @Override
            public void changed(ObservableValue<? extends NhaSX> observable, NhaSX oldValue, NhaSX newValue) {
                if(tableView.getSelectionModel().getSelectedItem() != null){
                    InputField.setNhaSXFromTable(newValue);
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
        refreshBtn.setOnAction(e -> tableView.setItems(NhaSXBLL.getAll()));

    }

    public static void delete(){

        ObservableList<NhaSX> list = tableView.getSelectionModel().getSelectedItems();
        if(list == null)
            MyDialog.showDialog("Non Selected Item",null, "Select an Item Pls",MyDialog.ERRO);
        for(NhaSX item : list)
            NhaSXBLL.delete(item);

    }

    public static void deleteAll(){

        ObservableList<NhaSX> list = tableView.getItems();
        for(NhaSX item : list)
            NhaSXBLL.delete(item);
        MyDialog.showDialog("Delete Success",null, "Done!", MyDialog.INFO);

    }
}

package gui.inputform.nation;

import bll.QuocGiaBLL;
import bll.TheLoaiBLL;
import dto.QuocGia;
import dto.TheLoai;
import gui.inputform.nation.InputField;
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
import utilities.ImageGetter;
import utilities.MyDialog;
import utilities.MyLayout;

public class DataTable extends VBox {

    public final static int ICON_SIZE = 15;

    //TableView
    private static TableView<QuocGia> tableView;
    //ID Col
    private static TableColumn<QuocGia, String> idCol;
    //Name Col
    private static TableColumn<QuocGia, String> nameCol;
    //Button Bar
    private static HBox buttonBar;
    //Buttons
    private static Button deleteBtn;
    private static Button deleteAllBtn;
    private static Button refreshBtn;


    public DataTable() {

        //====================== ID Col ======================

        idCol = getTextCol("#", "maQuocGia");
        idCol.setStyle("-fx-alignment: CENTER-RIGHT");

        //====================== Name Col ======================

        nameCol = getTextCol("Name", "tenQuocGia");

        //====================== Button Bar ======================

        buttonBar = getButtonBar();

        //===================== tableView =======================

        tableView = getTableView();

        //======================== Main Layout =============================

        getMainLayout();

    }

    public TableColumn<QuocGia, String> getTextCol(String text, String property) {

        TableColumn<QuocGia, String> tableColumn;
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

    public TableView<QuocGia> getTableView() {

        TableView<QuocGia> tableView = new TableView<>();

        tableView.setItems(QuocGiaBLL.getAll());
        tableView.setMinHeight(450);
        tableView.setEditable(true);
        tableView.prefHeightProperty().bind(this.heightProperty().divide(5.5 / 4.5));
        tableView.getColumns().addAll(idCol, nameCol);
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<QuocGia>() {
            @Override
            public void changed(ObservableValue<? extends QuocGia> observable, QuocGia oldValue, QuocGia newValue) {
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

    public void createButton(){


        deleteBtn = MyLayout.getButton("Delete", ImageGetter.DELETE, MyLayout.ICON_SIZE);
        deleteBtn.setOnAction(e -> delete());

        deleteAllBtn = MyLayout.getButton("Delete All", ImageGetter.DELETE, MyLayout.ICON_SIZE);
        deleteAllBtn.setOnAction(e -> deleteAll());

        refreshBtn = MyLayout.getButton("Refresh", ImageGetter.REFRESH, MyLayout.ICON_SIZE);
        refreshBtn.setOnAction(e -> tableView.setItems(QuocGiaBLL.getAll()));

    }

    public static void delete(){

        ObservableList<QuocGia> list = tableView.getSelectionModel().getSelectedItems();
        if(list == null)
            MyDialog.showDialog("Non Selected Item",null, "Select an Item Pls",MyDialog.ERRO);
        for(QuocGia item : list)
            QuocGiaBLL.delete(item);

    }

    public static void deleteAll(){

        ObservableList<QuocGia> list = tableView.getItems();
        for(QuocGia item : list)
            QuocGiaBLL.delete(item);
        MyDialog.showDialog("Delete Success",null, "Done!", MyDialog.INFO);

    }
}

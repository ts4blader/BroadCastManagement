package gui.inputform.category;

import dto.TheLoai;
import bll.TheLoaiBLL;
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
    private static TableView<TheLoai> tableView;
    //ID Col
    private static TableColumn<TheLoai, String> idCol;
    //Name Col
    private static TableColumn<TheLoai, String> nameCol;
    //Button Bar
    private static HBox buttonBar;
    //Buttons
    private static Button deleteBtn;
    private static Button deleteAllBtn;
    private static Button refreshBtn;

    public DataTable() {

        //====================== ID Col ======================

        idCol = getTextCol("#", "maTheLoai");
        idCol.setStyle("-fx-alignment: CENTER-RIGHT");

        //====================== Name Col ======================

        nameCol = getTextCol("Name", "tenTheLoai");

        //====================== Button Bar ======================

        buttonBar = getButtonBar();

        //===================== tableView =======================

        tableView = getTableView();

        //======================== Main Layout =============================

        getMainLayout();

    }

    public TableColumn<TheLoai, String> getTextCol(String text, String property) {

        TableColumn<TheLoai, String> tableColumn;
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

    public TableView<TheLoai> getTableView() {

        TableView<TheLoai> tableView = new TableView<>();

        tableView.setItems(TheLoaiBLL.getAll());
        tableView.setMinHeight(450);
        tableView.prefHeightProperty().bind(this.heightProperty().divide(5.5 / 4.5));
        tableView.getColumns().addAll(idCol, nameCol);
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TheLoai>() {
            @Override
            public void changed(ObservableValue<? extends TheLoai> observable, TheLoai oldValue, TheLoai newValue) {
                if(tableView.getSelectionModel().getSelectedItem() != null){
                    InputField.getObjFromTable(newValue);
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
        refreshBtn.setOnAction(e -> tableView.setItems(TheLoaiBLL.getAll()));

    }

    public static void delete(){

        ObservableList<TheLoai> list = tableView.getSelectionModel().getSelectedItems();
        if(list == null)
            MyDialog.showDialog("Non Selected Item",null, "Select an Item Pls",MyDialog.ERRO);
        for(TheLoai item : list)
            TheLoaiBLL.delete(item);

    }

    public static void deleteAll(){

        ObservableList<TheLoai> list = tableView.getItems();
        for(TheLoai item : list)
            TheLoaiBLL.delete(item);
        MyDialog.showDialog("Delete Success",null, "Done!", MyDialog.INFO);

    }
}

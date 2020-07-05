package gui.inputform.category;

import bll.CategoryBLL;
import bll.ChannelBLL;
import entities.Category;
import entities.Channel;
import javafx.beans.property.SimpleBooleanProperty;
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

import java.time.LocalDate;

public class DataTable extends VBox {

    public final static int ICON_SIZE = 15;

    //TableView
    private TableView<Category> tableView;
    //ID Col
    private TableColumn<Category, String> idCol;
    //Name Col
    private TableColumn<Category, String> nameCol;
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

        //====================== Button Bar ======================

        buttonBar = getButtonBar();

        //===================== tableView =======================

        tableView = getTableView();

        //======================== Main Layout =============================

        getMainLayout();

    }

    public TableColumn<Category, String> getTextCol(String text, String property) {

        TableColumn<Category, String> tableColumn;
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

    public TableView<Category> getTableView() {

        TableView<Category> tableView = new TableView<>();

        tableView.setItems(CategoryBLL.getAllCategory());
        tableView.setMinHeight(450);
        tableView.setEditable(true);
        tableView.prefHeightProperty().bind(this.heightProperty().divide(5.5 / 4.5));
        tableView.getColumns().addAll(idCol, nameCol);
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Category>() {
            @Override
            public void changed(ObservableValue<? extends Category> observable, Category oldValue, Category newValue) {
                if(tableView.getSelectionModel().getSelectedItem() != null){
                    InputField.setCategoryFromTable(newValue);
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

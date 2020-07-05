package gui.inputform.producer;

import bll.CategoryBLL;
import bll.NationBLL;
import bll.ProducerBLL;
import entities.Category;
import entities.Nation;
import entities.Producer;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import utilities.ImageGetter;
import utilities.MyLayout;

public class DataTable extends VBox {

    public final static int ICON_SIZE = 15;

    //TableView
    private TableView<Producer> tableView;
    //ID Col
    private TableColumn<Producer, String> idCol;
    //Name Col
    private TableColumn<Producer, String> nameCol;
    //Nation Col
    private TableColumn<Producer, Nation> nationCol;
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
        nameCol.setMinWidth(200);

        //====================== Nation Col ======================

        createNationCol();

        //====================== Button Bar ======================

        buttonBar = getButtonBar();

        //===================== tableView =======================

        tableView = getTableView();

        //======================== Main Layout =============================

        getMainLayout();

    }

    public TableColumn<Producer, String> getTextCol(String text, String property) {

        TableColumn<Producer, String> tableColumn;
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

    public void createNationCol(){

        nationCol = new TableColumn<>("Nation");
        nationCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Producer, Nation>, ObservableValue<Nation>>() {
            @Override
            public ObservableValue<Nation> call(TableColumn.CellDataFeatures<Producer, Nation> param) {

                Producer producer = param.getValue();
                 return new SimpleObjectProperty<>(NationBLL.getNationById(producer.getNationID()));

            }
        });
        nationCol.setMinWidth(200);
    }

    public TableView<Producer> getTableView() {

        TableView<Producer> tableView = new TableView<>();

        tableView.setItems(ProducerBLL.getAllProducer());
        tableView.setMinHeight(450);
        tableView.setEditable(true);
        tableView.prefHeightProperty().bind(this.heightProperty().divide(5.5 / 4.5));
        tableView.getColumns().addAll(idCol, nameCol, nationCol);
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Producer>() {
            @Override
            public void changed(ObservableValue<? extends Producer> observable, Producer oldValue, Producer newValue) {
                if(tableView.getSelectionModel().getSelectedItem() != null){
                    InputField.setProducerFromTable(newValue);
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

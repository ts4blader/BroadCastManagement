package gui.inputform.program;

import bll.CategoryBLL;
import bll.NationBLL;
import bll.ProducerBLL;
import bll.ProgramBLL;
import entities.Category;
import entities.Nation;
import entities.Producer;
import entities.Program;
import javafx.beans.value.ChangeListener;
import utilities.ImageGetter;
import utilities.MyLayout;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.File;

public class DataTable extends VBox {

    public final static int ICON_SIZE = 15;

    //TableView
    private TableView<Program> tableView;
    //ID Col
    private TableColumn<Program, String> idCol;
    //Name Col
    private TableColumn<Program, String> nameCol;
    //Category Col
    private TableColumn<Program, Category> categoryCol;
    //Producer Col
    private TableColumn<Program, Producer> producerCol;
    //Nation Col
    private TableColumn<Program, Nation> nationCol;
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

        //====================== Category Col ======================

        categoryCol = getCategoryCol();

        //====================== Producer Col ======================

        producerCol = getProducerCol();

        //====================== Nation Col ======================

        nationCol = getNationCol();

        //====================== Button Bar ======================

        buttonBar = getButtonBar();

        //===================== tableView =======================

        tableView = getTableView();

        //======================== Main Layout =============================

        getMainLayout();

    }

    public TableColumn<Program, String> getTextCol(String text, String property) {

        TableColumn<Program, String> tableColumn;
        tableColumn = new TableColumn<>(text);

        tableColumn.setCellValueFactory(new PropertyValueFactory<>(property));
        tableColumn.setMinWidth(50);

        //Commit
//        tableColumn.setOnEditCommit((TableColumn.CellEditEvent<Program, String> e) -> {
//            TablePosition<Program, String> pos = e.getTablePosition();
//
//            String newValue = e.getNewValue();
//
//            int row = pos.getRow();
//            Program program = e.getTableView().getItems().get(row);
//
//            ProgramBLL.update(program);
//        });

        return tableColumn;
    }

    public TableColumn<Program, Category> getCategoryCol() {

        TableColumn<Program, Category> tableColumn;
        tableColumn = new TableColumn<>("Category");

        tableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Program, Category>, ObservableValue<Category>>() {
            @Override
            public ObservableValue<Category> call(TableColumn.CellDataFeatures<Program, Category> param) {
                Program program = param.getValue();

                String categoryID = program.getCategoryID();
                Category category = CategoryBLL.getCategoryByID(categoryID);


                return new SimpleObjectProperty<Category>(category);
            }
        });
        //set cell factory
//        tableColumn.setCellFactory(ComboBoxTableCell.forTableColumn(CategoryBLL.getAllCategory()));
        //Commit
//        tableColumn.setOnEditCommit((TableColumn.CellEditEvent<Program, Category> e) -> {
//            TablePosition<Program, Category> pos = e.getTablePosition();
//            //get new value
//            Category newCategory = e.getNewValue();
//            //get row
//            int row = pos.getRow();
//            Program program = e.getTableView().getItems().get(row);
//
//            ProgramBLL.update(program);
//        });

        tableColumn.setMinWidth(100);

        return tableColumn;


    }

    public TableColumn<Program, Producer> getProducerCol() {

        TableColumn<Program, Producer> tableColumn;
        tableColumn = new TableColumn<>("Producer");

        tableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Program, Producer>, ObservableValue<Producer>>() {
            @Override
            public ObservableValue<Producer> call(TableColumn.CellDataFeatures<Program, Producer> param) {
                Program program = param.getValue();

                Producer producer = ProducerBLL.getProducerById(program.getProducerID());

                return new SimpleObjectProperty<Producer>(producer);
            }
        });

//        tableColumn.setCellFactory(ComboBoxTableCell.forTableColumn(ProducerBLL.getAllProducer()));

//        tableColumn.setOnEditCommit((TableColumn.CellEditEvent<Program, Producer> e) -> {
//            TablePosition<Program, Producer> position = e.getTablePosition();
//
//            int row = position.getRow();
//
//            Program program = e.getTableView().getItems().get(row);
//
//            //update Producer
//        });

        tableColumn.setMinWidth(100);

        return tableColumn;


    }

    public TableColumn<Program, Nation> getNationCol(){

        TableColumn<Program, Nation> tableColumn = new TableColumn<>("Nation");
        tableColumn.setCellValueFactory( cellData -> {
            Producer producer = ProducerBLL.getProducerById(cellData.getValue().getProducerID());

            return producer.getNationProperty();
        });

        tableColumn.setCellFactory( column -> {
            return new TableCell<Program, Nation>(){
                @Override
                protected void updateItem(Nation item, boolean empty) {
                    if(item == null || empty)
                        setText("");
                    else
                        setText(item.getName());
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

    public TableView<Program> getTableView() {

        TableView<Program> tableView = new TableView<>();

        tableView.setItems(ProgramBLL.getAllProgram());
        tableView.setMinHeight(450);
        tableView.setEditable(true);
        tableView.prefHeightProperty().bind(this.heightProperty().divide(5.5 / 4.5));
        tableView.getColumns().addAll(idCol, nameCol, categoryCol, producerCol, nationCol);
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Program>() {
            @Override
            public void changed(ObservableValue<? extends Program> observable, Program oldValue, Program newValue) {
                if(tableView.getSelectionModel().getSelectedItem() != null){
                    InputField.setProgramFromTable(newValue);
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

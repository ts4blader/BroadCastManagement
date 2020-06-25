package GUI.Program;

import BLL.CategoryBLL;
import BLL.ProducerBLL;
import BLL.ProgramBLL;
import Enities.Category;
import Enities.Nation;
import Enities.Producer;
import Enities.Program;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class DataTable extends VBox {
    //============TableView
    private TableView<Program> tableView = new TableView<>();
    //============ID Col
    private TableColumn<Program, Integer> idCol = new TableColumn<>("#");
    //=============Name Col
    private TableColumn<Program, String> nameCol = new TableColumn<>("Name");
    //============Category Col
    private TableColumn<Program, Category> categoryCol = new TableColumn<>("Category");
    //============Producer Col
    private TableColumn<Program, Producer> producerCol = new TableColumn<>("Producer");
    //============Nation Col
    private TableColumn<Program, Nation> nationCol = new TableColumn<>("Nation");
    //=========== Button Bar
    private HBox buttonBar;
    //=========== Buttons
    private Button saveBtn;
    private Button undoBtn;
    private Button redoBtn;
    private Button deleteBtn;
    private Button deleteAllBtn;

    public DataTable(){
        //set editable
        tableView.setEditable(true);
        //=======================
        //ID Col
        //========================
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setStyle("-fx-alignment: CENTER-RIGHT");
        //==========================
        // Name Col <TextField>
        //========================
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        nameCol.setCellFactory(TextFieldTableCell.<Program>forTableColumn());

        nameCol.setMinWidth(150);

        //Commit
        nameCol.setOnEditCommit( (TableColumn.CellEditEvent<Program, String> e) -> {
            TablePosition<Program, String> pos = e.getTablePosition();

            String name = e.getNewValue();

            int row = pos.getRow();
            Program program = e.getTableView().getItems().get(row);

            ProgramBLL.update(program);
        });

        //======================
        //Category Col <ComboBox>
        //======================
        categoryCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Program, Category>, ObservableValue<Category>>() {
            @Override
            public ObservableValue<Category> call(TableColumn.CellDataFeatures<Program, Category> param) {
                Program program = param.getValue();

                String categoryID = program.getCategoryID();
                Category category = CategoryBLL.getCategoryByID(categoryID);


                return new SimpleObjectProperty<Category>(category);
            }
        });
        categoryCol.setCellFactory(ComboBoxTableCell.forTableColumn(CategoryBLL.getAllCategory()));

        categoryCol.setOnEditCommit( (TableColumn.CellEditEvent<Program, Category> e) -> {
            TablePosition<Program, Category> pos = e.getTablePosition();

            Category newCategory = e.getNewValue();

            int row = pos.getRow();
            Program program = e.getTableView().getItems().get(row);

            ProgramBLL.update(program);
        });

        categoryCol.setMinWidth(100);

        //=====================
        //Producer Col
        //====================
        producerCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Program, Producer>, ObservableValue<Producer>>() {
            @Override
            public ObservableValue<Producer> call(TableColumn.CellDataFeatures<Program, Producer> param) {
                Program program = param.getValue();

                String producerID = program.getProducerID();

                Producer producer = ProducerBLL.getProByID(producerID);

                return new SimpleObjectProperty<Producer>(producer);
            }
        });

        producerCol.setCellFactory(ComboBoxTableCell.forTableColumn(ProducerBLL.getAllProducer()));

        producerCol.setOnEditCommit((TableColumn.CellEditEvent<Program, Producer> e) ->{
            TablePosition<Program, Producer> position = e.getTablePosition();

            int row = position.getRow();

            Program program = e.getTableView().getItems().get(row);

            //update Producer
        });

        producerCol.setMinWidth(100);

        //====================== Button Bar ======================

        saveBtn = new Button("Save");
        undoBtn = new Button("Undo");
        redoBtn = new Button("Redo");
        deleteBtn = new Button("Delete");
        deleteAllBtn = new Button("Delete All");

        buttonBar = new HBox(10);
        buttonBar.setAlignment(Pos.CENTER);
        buttonBar.getChildren().addAll(saveBtn, undoBtn, redoBtn, deleteBtn, deleteAllBtn);

        //====================== End ======================


        //===================== tableView =======================

        tableView.setItems(ProgramBLL.getAllProgram());
        tableView.setMinHeight(450);
        tableView.prefHeightProperty().bind(this.heightProperty().divide(5.5/4.5));
        tableView.getColumns().addAll(idCol, nameCol, categoryCol, producerCol);


        //======================== Main Layout =============================

        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(20);
        this.getChildren().addAll(tableView, buttonBar);
    }


}

package GUI.Program;

import BLL.CategoryBLL;
import BLL.ProducerBLL;
import BLL.ProgramBLL;
import Enities.Category;
import Enities.Nation;
import Enities.Producer;
import Enities.Program;
import Utilities.ImageGetter;
import Utilities.MyLayout;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.StringConverter;

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


    public DataTable(){

        //====================== ID Col ======================

        idCol = getTextCol("#","id");
        idCol.setStyle("-fx-alignment: CENTER-RIGHT");

        //====================== Name Col ======================

        nameCol = getTextCol("Name", "name");

        //====================== Category Col ======================

        categoryCol = getCategoryCol();

        //====================== Producer Col ======================

        producerCol = getProducerCol();

        //====================== Button Bar ======================

        buttonBar = getButtonBar();

        //===================== tableView =======================

        tableView = getTableView();


        //======================== Main Layout =============================

        getMainLayout();

    }

    public TableColumn<Program, String> getTextCol(String text, String property){

        TableColumn<Program, String> tableColumn;
        tableColumn = new TableColumn<>(text);

        tableColumn.setCellValueFactory(new PropertyValueFactory<>(property));
        tableColumn.setCellFactory(TextFieldTableCell.<Program>forTableColumn());
        tableColumn.setMinWidth(50);

        //Commit
        tableColumn.setOnEditCommit( (TableColumn.CellEditEvent<Program, String> e) -> {
            TablePosition<Program, String> pos = e.getTablePosition();

            String newValue = e.getNewValue();

            int row = pos.getRow();
            Program program = e.getTableView().getItems().get(row);

            ProgramBLL.update(program);
        });

        return tableColumn;
    }

    public TableColumn<Program, Category> getCategoryCol(){

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
        tableColumn.setCellFactory(ComboBoxTableCell.forTableColumn(CategoryBLL.getAllCategory()));
        //Commit
        tableColumn.setOnEditCommit( (TableColumn.CellEditEvent<Program, Category> e) -> {
            TablePosition<Program, Category> pos = e.getTablePosition();
            //get new value
            Category newCategory = e.getNewValue();
            //get row
            int row = pos.getRow();
            Program program = e.getTableView().getItems().get(row);

            ProgramBLL.update(program);
        });

        tableColumn.setMinWidth(100);

        return tableColumn;


    }

    public TableColumn<Program, Producer> getProducerCol(){

        TableColumn<Program, Producer> tableColumn;
        tableColumn = new TableColumn<>("Producer");

        tableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Program, Producer>, ObservableValue<Producer>>() {
            @Override
            public ObservableValue<Producer> call(TableColumn.CellDataFeatures<Program, Producer> param) {
                Program program = param.getValue();

                String producerID = program.getProducerID();

                Producer producer = ProducerBLL.getProByID(producerID);

                return new SimpleObjectProperty<Producer>(producer);
            }
        });

        tableColumn.setCellFactory(ComboBoxTableCell.forTableColumn(ProducerBLL.getAllProducer()));

        tableColumn.setOnEditCommit((TableColumn.CellEditEvent<Program, Producer> e) ->{
            TablePosition<Program, Producer> position = e.getTablePosition();

            int row = position.getRow();

            Program program = e.getTableView().getItems().get(row);

            //update Producer
        });

        tableColumn.setMinWidth(100);

        return tableColumn;


    }

    public HBox getButtonBar(){

        HBox hBox = new HBox(MyLayout.SPACE);

        saveBtn = getButton("Save",ImageGetter.SAVE, MyLayout.ICON_SIZE);

        redoBtn = getButton("Redo",ImageGetter.REDO, MyLayout.ICON_SIZE);

        undoBtn = getButton("Undo",ImageGetter.UNDO, MyLayout.ICON_SIZE);

        deleteBtn = getButton("Delete",ImageGetter.DELETE, MyLayout.ICON_SIZE);

        deleteAllBtn = getButton("Delete All",ImageGetter.DELETE, MyLayout.ICON_SIZE);

        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(saveBtn, undoBtn, redoBtn, deleteBtn, deleteAllBtn);

        return hBox;

    }

    public Button getButton(String Label, File image, int iconSize){

        Button button = new Button(Label);
        button.setGraphic(ImageGetter.getImageView(image, iconSize));
        button.setOnAction(e -> {});

        return button;
    }

    public TableView<Program> getTableView(){

        TableView<Program> tableView = new TableView<>();

        tableView.setItems(ProgramBLL.getAllProgram());
        tableView.setMinHeight(450);
        tableView.setEditable(true);
        tableView.prefHeightProperty().bind(this.heightProperty().divide(5.5/4.5));
        tableView.getColumns().addAll(idCol, nameCol, categoryCol, producerCol);

        return tableView;
    }

    public void getMainLayout(){

        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(20);
        this.getChildren().addAll(tableView, buttonBar);


    }
}

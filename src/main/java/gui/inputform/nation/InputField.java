package gui.inputform.nation;


import bll.CategoryBLL;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.Category;
import entities.Nation;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utilities.ImageGetter;
import utilities.MyDialog;
import utilities.MyLayout;


public class InputField extends VBox {
    // Define const


    //Define var
    private static JFXTextField idField;

    private static JFXTextField nameField;

    private HBox buttonBar;

    private JFXButton addBtn;
    private JFXButton clearBtn;
    private JFXButton searchBtn;


    public InputField() {

        idField = MyLayout.getTextField("ID");
        idField.setOnAction( e -> addNation());
        MyLayout.setValidator(idField, "Not match Requirement");

        nameField = MyLayout.getTextField("Name");
        nameField.setOnAction(e -> addNation());
        MyLayout.setValidator(nameField, "Not match Requirement");


        //====================== Button Bar ======================

        buttonBar = getButtonBar();

        //================ main layout =============

        getMainLayout();

    }

    //====================== Controller Section ======================

    public void getMainLayout() {

        this.setPadding(new Insets(30));
        this.setSpacing(40);
        this.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().addAll(idField, nameField, buttonBar);

    }

    public boolean validatorNation() {
        if (idField.getText().equals("")){
            MyDialog.showDialog("Input requirement", null, "ID not match Requirement", MyDialog.ERRO);
            return false;
        }
        if (nameField.getText().equals("")) {
            MyDialog.showDialog("Input requirement", null, "Name not match Requirement", MyDialog.ERRO);
            return false;
        }

        return true;
    }

    public HBox getButtonBar() {

        HBox hBox;

        addBtn = MyLayout.getJFXButton("Add", ImageGetter.ADD, MyLayout.ICON_SIZE);
        addBtn.setOnAction( e -> addNation());
        clearBtn = MyLayout.getJFXButton("Clear", ImageGetter.CLEAR, MyLayout.ICON_SIZE);
        clearBtn.setOnAction( e -> clearInput());
        searchBtn = MyLayout.getJFXButton("Search", ImageGetter.SEARCH, MyLayout.ICON_SIZE);


        //add to Button Bar
        hBox = new HBox(15);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(addBtn, clearBtn, searchBtn);


        return hBox;

    }

    public boolean addNation() {
        try {
            if (!validatorNation()) return false;

            String id = idField.getText().trim();
            String name = nameField.getText().trim();

            CategoryBLL.addCategory(new Category(id, name));
            return true;

        } catch (Exception e) {
            MyDialog.showDialog("Can not Add Program", "Unknow Error", e.getMessage(), MyDialog.ERRO);
        } finally {
            return false;
        }

    }

    public boolean clearInput() {
        try {
            idField.setText("");
            nameField.setText("");

            return true;
        } catch (Exception e) {
            MyDialog.showDialog("Clear Input", "Clear Error", e.getMessage(), MyDialog.ERRO);
        } finally {
            return false;
        }
    }

    public static boolean setNationFromTable(Nation nation){

        try {
            idField.setText(nation.getId());
            nameField.setText(nation.getName());

            return true;
        } catch (Exception e){
            MyDialog.showDialog("Set Nation From Table",null,"Unknown Error",MyDialog.ERRO);
        }
        return false;


    }
    //====================== End =================================
}

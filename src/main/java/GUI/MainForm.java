package GUI;

import GUI.Program.DataTable;
import GUI.Program.InputField;
import com.jfoenix.controls.*;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.xml.validation.Validator;
import java.io.File;

public class MainForm extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        //left Pane (input)

        InputField inputField = new InputField();

        //right Pane (table)

        DataTable dataTable = new DataTable();

        //split Pane

        SplitPane programDiv = new SplitPane(inputField, dataTable);

        //====== Program Tab ==============

        Tab programTab = new Tab("Program");
        programTab.setContent(programDiv);

        //======= JFXTabPane ==============

        JFXTabPane tabPane = new JFXTabPane();
        tabPane.getTabs().addAll(programTab);

        //====== scene ===============

        Scene scene = new Scene(tabPane, 800, 600);
        File css = new File("src/main/java/GUI/style.css");
        scene.getStylesheets().add("file:///" + css.getAbsolutePath().replace("\\", "/"));

        //primary Stage

        primaryStage.setTitle("BroadCast Management");
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

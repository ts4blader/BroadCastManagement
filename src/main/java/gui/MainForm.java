package gui;

import entities.Program;
import gui.inputform.channel.ChannelPane;
import gui.inputform.program.DataTable;
import gui.inputform.program.InputField;
import com.jfoenix.controls.*;
import gui.inputform.program.ProgramPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.stage.Stage;
import utilities.MyLayout;

import java.io.File;

public class MainForm extends Application {

    private JFXTabPane tabPane;

    private Tab programTab;
    private SplitPane programPane;


    private Tab channelTab;
    private SplitPane channelPane;

    public MainForm() throws Exception {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //====================== SplitPane ======================

        programPane = ProgramPane.getSplitPane();
        channelPane = ChannelPane.getSplitPane();

        //====================== Tabs ======================

        programTab = MyLayout.getTab("Program", programPane);
        channelTab = MyLayout.getTab("Channel", channelPane);

        //============= JFXTabPane ==============
        createMainLayout();

        Scene scene = new Scene(tabPane, 1000, 600);
        File css = new File("src/main/java/gui/style.css");
        scene.getStylesheets().add("file:///" + css.getAbsolutePath().replace("\\", "/"));

        primaryStage.setTitle("Input Window");
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void createMainLayout(){
        tabPane = new JFXTabPane();
        tabPane.getTabs().addAll(programTab, channelTab);
    }
}

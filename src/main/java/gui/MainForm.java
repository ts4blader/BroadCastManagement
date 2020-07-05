package gui;

import entities.Program;
import gui.inputform.category.CategoryPane;
import gui.inputform.channel.ChannelPane;
import gui.inputform.nation.NationPane;
import gui.inputform.producer.ProducerPane;
import gui.inputform.program.DataTable;
import gui.inputform.program.InputField;
import com.jfoenix.controls.*;
import gui.inputform.program.ProgramPane;
import gui.inputform.scheduleprogram.SchedulePane;
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

    private Tab categoryTab;
    private SplitPane categoryPane;

    private Tab producerTab;
    private SplitPane producerPane;

    private Tab nationTab;
    private SplitPane nationPane;

    private Tab scheduleTab;
    private SplitPane schedulePane;



    public MainForm() throws Exception {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //====================== SplitPane ======================

        programPane = ProgramPane.getSplitPane();
        channelPane = ChannelPane.getSplitPane();
        categoryPane = CategoryPane.getSplitPane();
        producerPane = ProducerPane.getSplitPane();
        nationPane = NationPane.getSplitPane();
        schedulePane = SchedulePane.getSplitPane();


        //====================== Tabs ======================

        programTab = MyLayout.getTab("Program", programPane);
        channelTab = MyLayout.getTab("Channel", channelPane);
        categoryTab = MyLayout.getTab("Category", categoryPane);
        producerTab = MyLayout.getTab("Producer", producerPane);
        nationTab = MyLayout.getTab("Nation", nationPane);
        scheduleTab = MyLayout.getTab("Schedule", schedulePane);


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
        tabPane.setDisableAnimation(false);
        tabPane.setDisableAnimation(true);
        tabPane.getTabs().addAll(programTab, channelTab, categoryTab, producerTab, nationTab, scheduleTab);
    }
}

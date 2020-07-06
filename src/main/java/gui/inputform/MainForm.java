package gui.inputform;

import gui.MainMenu;
import gui.inputform.category.CategoryPane;
import gui.inputform.channel.ChannelPane;
import gui.inputform.nation.NationPane;
import gui.inputform.producer.ProducerPane;
import com.jfoenix.controls.*;
import gui.inputform.program.ProgramPane;
import gui.inputform.scheduleprogram.SchedulePane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.stage.Stage;
import utilities.MyLayout;

import java.io.File;

public class MainForm {

    private static JFXTabPane tabPane;

    private static Tab programTab;
    private static Tab channelTab;
    private static Tab categoryTab;
    private static Tab producerTab;
    private static Tab nationTab;
    private static Tab scheduleTab;

    private static Stage primaryStage;


    public MainForm() throws Exception {
    }

    public static void start() throws Exception {

        //====================== Tabs ======================

        programTab = MyLayout.getTab("Program", ProgramPane.getSplitPane());
        channelTab = MyLayout.getTab("Channel", ChannelPane.getSplitPane());
        categoryTab = MyLayout.getTab("Category", CategoryPane.getSplitPane());
        producerTab = MyLayout.getTab("Producer", ProducerPane.getSplitPane());
        nationTab = MyLayout.getTab("Nation", NationPane.getSplitPane());
        scheduleTab = MyLayout.getTab("Schedule", SchedulePane.getSplitPane());


        //============= JFXTabPane ==============
        createMainLayout();

        Scene scene = new Scene(tabPane, 1000, 600);
        File css = new File("src/main/java/gui/style.css");
        scene.getStylesheets().add("file:///" + css.getAbsolutePath().replace("\\", "/"));

        primaryStage = new Stage();
        primaryStage.setTitle("Input Window");
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(e ->{
            MainMenu.show();
            primaryStage.close();
        });

    }
    //create main layout
    public static void createMainLayout(){
        tabPane = new JFXTabPane();
        tabPane.setDisableAnimation(false);
        tabPane.setDisableAnimation(true);
        tabPane.getTabs().addAll(programTab, channelTab, categoryTab, producerTab, nationTab, scheduleTab);
    }
}

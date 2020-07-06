package gui;

import gui.inputform.MainForm;
import utilities.ImageGetter;
import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utilities.MyDialog;

import java.io.File;


public class MainMenu extends Application {


    private static VBox mainLayout;
//    top Pane
    private static VBox topPane;
//    mid Pane
    private static HBox midPane;
    private static JFXButton inputBtn;
    private static JFXButton advancedBtn;
    private static JFXButton updateBtn;
    private static JFXButton reportBtn;
//    Bot Pane
    private static HBox botPane;
    
    private static VBox devSection;
    private static Label devTitle;
    private static Label herobmdk;
    private static Label trisTran;
    private static Label nameLess;
    
    private static HBox contactPane;

    private static Stage main;

    @Override
    public void start(Stage primaryStage) throws Exception {

        //====================== mid pane ======================

        creatMidPane();

        //====================== top Pane ======================

        creatTopPane();

        //====================== bot pane ======================
        
        creatBotPane();

        //====================== Main Layout ======================

        mainLayout = new VBox(topPane, botPane);

        Scene scene = new Scene(mainLayout);
        File css = new File("src/main/java/gui/style.css");
        scene.getStylesheets().add("file:///" + css.getAbsolutePath().replace("\\", "/"));

        main = primaryStage;
        main.setTitle("BroadCast Management");
        main.setMinWidth(800);
        main.setScene(scene);
        main.setResizable(false);
        main.show();
    }

    public static void show(){
        main.show();
    }

    public void creatTopPane(){
        Text title = new Text("BroadCast Management");
        title.setStyle("-fx-font-size: 40");
        topPane = new VBox();
        topPane.setSpacing(30);
        topPane.getStyleClass().addAll("topPane");

        topPane.getChildren().addAll(title, midPane);
        topPane.setAlignment(Pos.CENTER);
    }

    public JFXButton getButton(String label, File image, int iconSize){

        JFXButton button;

        ImageView inputImageView = ImageGetter.getImageView(image, iconSize );

        button = new JFXButton(label);
        button.setGraphic(inputImageView);
        button.setContentDisplay(ContentDisplay.TOP);

        return button;

    }

    public void creatMidPane(){

        int iconSize = 80;

        inputBtn = getButton("Input", ImageGetter.KEYBOARD, iconSize);
        inputBtn.setOnAction(e -> showMainForm());

        advancedBtn = getButton("Advanced", ImageGetter.DATABASE, iconSize);

        updateBtn = getButton("Update", ImageGetter.UPDATE, iconSize);

        reportBtn = getButton("Report", ImageGetter.REPORT, iconSize);


        midPane = new HBox(40);
        midPane.setAlignment(Pos.CENTER);
        midPane.getStyleClass().addAll("midPane");
        midPane.getChildren().addAll(inputBtn, advancedBtn, updateBtn, reportBtn);


    }

    public void creatBotPane(){

        devTitle = new Label("Developer Team:");
        devTitle.getStyleClass().addAll("title-dev");
        herobmdk = new Label("herobmdk");
        trisTran = new Label("Tris Tran");
        nameLess = new Label("NamelessKing");

        devSection = new VBox(3);
        devSection.getChildren().addAll(devTitle, herobmdk, trisTran, nameLess);

        int contactIconSize = 40;
        ImageView facebookView = ImageGetter.getImageView(ImageGetter.FA,contactIconSize);
        ImageView mailView = ImageGetter.getImageView(ImageGetter.MAIL,contactIconSize);
        ImageView gitView = ImageGetter.getImageView(ImageGetter.GIT,contactIconSize);


        contactPane = new HBox(facebookView, mailView, gitView);
        contactPane.setSpacing(15);
        contactPane.setAlignment(Pos.CENTER);

        botPane = new HBox(350);
        botPane.getStyleClass().addAll("botPane");
        botPane.setAlignment(Pos.CENTER);
        botPane.setPadding(new Insets(25));
        botPane.getChildren().addAll(devSection, contactPane);


    }

    public void showMainForm(){

        try {
            MainForm.start();
            main.close();
        } catch (Exception e){
            MyDialog.showDialog("Error",null, "Unknow Error",MyDialog.ERRO);
        }

    }
}

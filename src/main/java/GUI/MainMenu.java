package GUI;

import Utilities.ImageGetter;
import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;


public class MainMenu extends Application {


    private VBox mainLayout;
//    top Pane
    private VBox topPane;
//    mid Pane
    private HBox midPane;
    private JFXButton inputBtn;
    private JFXButton advancedBtn;
    private JFXButton updateBtn;
    private JFXButton exitBtn;
//    Bot Pane
    private HBox botPane;
    
    private VBox devSection;
    private Label devTitle;
    private Label herobmdk;
    private Label trisTran;
    private Label nameLess;
    
    private HBox contactPane;

    

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
        File css = new File("src/main/java/GUI/style.css");
        scene.getStylesheets().add("file:///" + css.getAbsolutePath().replace("\\", "/"));

        primaryStage.setTitle("BroadCast Management");
        primaryStage.setMinWidth(800);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
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

        inputBtn = getButton("Input", ImageGetter.INPUT, iconSize);

        advancedBtn = getButton("Advanced", ImageGetter.DATABASE, iconSize);

        updateBtn = getButton("Update", ImageGetter.EXCHANGE, iconSize);

        exitBtn = getButton("Exit", ImageGetter.EXIT, iconSize);


        midPane = new HBox(40);
        midPane.setAlignment(Pos.CENTER);
        midPane.getStyleClass().addAll("midPane");
        midPane.getChildren().addAll(inputBtn, advancedBtn, updateBtn, exitBtn);


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
}

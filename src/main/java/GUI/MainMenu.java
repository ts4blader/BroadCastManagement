package GUI;

import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;

public class MainMenu extends Application {

    private final Font mainFont = Font.loadFont("src/main/resources/fonts/FiraSans-SemiBold.ttf",20);

    private VBox mainLayout;
//    top Pane
    private StackPane topPane;
//    mid Pane
    private HBox midPane;
    private JFXButton inputBtn;
    private JFXButton advancedBtn;
    private JFXButton updateBtn;
    private JFXButton exitBtn;
//    Bot Pane
    private HBox botPane;
    
    private VBox devSection;
    private Text devTitle;
    private Text herobmdk;
    private Text trisTran;
    private Text nameLess;
    
    private HBox contactPane;
    private File facebook = new File("src/main/resources/icons/facebook.png");
    private File mail = new File("src/main/resources/icons/mail.png");
    private File github = new File("src/main/resources/icons/github.png");
    

    @Override
    public void start(Stage primaryStage) throws Exception {

        //====================== top Pane ======================
        
            Text title = new Text("BroadCast Management");
            title.setFont(mainFont);
            title.setStyle("-fx-font-size: 30");
            topPane = new StackPane(title);
            topPane.getStyleClass().addAll("topPane");
            
                    
        //====================== End ======================
        
        //====================== mid pane ======================

            File inputImage = new File("src/main/resources/icons/electric-keyboard.png");
            ImageView inputImageView = new ImageView(new Image(inputImage.toURI().toURL().toString()));
            inputImageView.setFitWidth(40);
            inputImageView.setFitHeight(40);

            inputBtn = new JFXButton("Input");
            inputBtn.setGraphic(inputImageView);
            inputBtn.setPadding(new Insets(10, 15, 10, 15));

            File advancedImage = new File("src/main/resources/icons/database.png");
            ImageView advancedImageView = new ImageView(new Image(advancedImage.toURI().toURL().toString()));
            advancedImageView.setFitHeight(40);
            advancedImageView.setFitWidth(40);

            advancedBtn = new JFXButton("Advanced");
            advancedBtn.setGraphic(advancedImageView);
            advancedBtn.setPadding(new Insets(10, 15, 10, 15));

            File updateImage = new File("src/main/resources/icons/exchange.png");
            ImageView updateImageView = new ImageView(new Image(updateImage.toURI().toURL().toString()));
            updateImageView.setFitWidth(40);
            updateImageView.setFitHeight(40);

            updateBtn = new JFXButton("Update");
            updateBtn.setGraphic(updateImageView);
            updateBtn.setPadding(new Insets(10, 15, 10, 15));

            File exitImage = new File("src/main/resources/icons/exit.png");
            ImageView exitImageView = new ImageView(new Image(exitImage.toURI().toURL().toString()));
            exitImageView.setFitHeight(40);
            exitImageView.setFitWidth(40);

            exitBtn = new JFXButton("Exit");
            exitBtn.setGraphic(exitImageView);
            exitBtn.setPadding(new Insets(10, 15, 10, 15));

            midPane = new HBox(20);
            midPane.setPadding(new Insets(20, 30, 20, 30));
            midPane.getChildren().addAll(inputBtn, advancedBtn, updateBtn, exitBtn);
                    
        //====================== End ======================
        
        //====================== bot pane ======================
        
            devTitle = new Text("Developer Team:");
            herobmdk = new Text("herobmdk");
            trisTran = new Text("Tris Tran");
            nameLess = new Text("NamelessKing");

            devSection = new VBox(2);
            devSection.getChildren().addAll(devTitle, herobmdk, trisTran, nameLess);

            ImageView facebookView = new ImageView(new Image(facebook.toURI().toURL().toString()));
            ImageView mailView = new ImageView(new Image(mail.toURI().toURL().toString()));
            ImageView gitView = new ImageView(new Image(github.toURI().toURL().toString()));

            facebookView.setFitWidth(40);
            facebookView.setFitHeight(40);

            mailView.setFitWidth(40);
            mailView.setFitHeight(40);

            gitView.setFitWidth(40);
            gitView.setFitHeight(40);

            contactPane = new HBox(facebookView, mailView, gitView);
            contactPane.setSpacing(5);

            botPane = new HBox(20);
            botPane.setPadding(new Insets(30));
            botPane.getChildren().addAll(devSection, contactPane);

        //====================== End ======================
        

        mainLayout = new VBox(topPane, midPane, botPane);

        primaryStage.setTitle("BroadCast Management");
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(800);
        Scene scene = new Scene(mainLayout, 800, 600);
        File css = new File("src/main/java/GUI/style.css");
        scene.getStylesheets().add("file:///" + css.getAbsolutePath().replace("\\", "/"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

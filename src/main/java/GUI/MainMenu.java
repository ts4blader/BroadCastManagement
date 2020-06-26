package GUI;

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
    private File facebook = new File("src/main/resources/icons/facebook.png");
    private File mail = new File("src/main/resources/icons/mail.png");
    private File github = new File("src/main/resources/icons/github.png");
    

    @Override
    public void start(Stage primaryStage) throws Exception {
        //====================== top Pane ======================
            Text title = new Text("BroadCast Management");
            title.setStyle("-fx-font-size: 40");
            topPane = new VBox();
            topPane.setSpacing(30);
            topPane.getStyleClass().addAll("topPane");

                    
        //====================== End ======================
        
        //====================== mid pane ======================

            int iconSize = 80;

            File inputImage = new File("src/main/resources/icons/electric-keyboard.png");
            ImageView inputImageView = new ImageView(new Image(inputImage.toURI().toURL().toString()));
            inputImageView.setFitWidth(iconSize);
            inputImageView.setFitHeight(iconSize);

            inputBtn = new JFXButton("Input");
            inputBtn.setGraphic(inputImageView);
            inputBtn.setContentDisplay(ContentDisplay.TOP);

            File advancedImage = new File("src/main/resources/icons/database.png");
            ImageView advancedImageView = new ImageView(new Image(advancedImage.toURI().toURL().toString()));
            advancedImageView.setFitHeight(iconSize);
            advancedImageView.setFitWidth(iconSize);

            advancedBtn = new JFXButton("Advanced");
            advancedBtn.setGraphic(advancedImageView);
            advancedBtn.setContentDisplay(ContentDisplay.TOP);

            File updateImage = new File("src/main/resources/icons/exchange.png");
            ImageView updateImageView = new ImageView(new Image(updateImage.toURI().toURL().toString()));
            updateImageView.setFitWidth(iconSize);
            updateImageView.setFitHeight(iconSize);

            updateBtn = new JFXButton("Update");
            updateBtn.setGraphic(updateImageView);
            updateBtn.setContentDisplay(ContentDisplay.TOP);

            File exitImage = new File("src/main/resources/icons/exit.png");
            ImageView exitImageView = new ImageView(new Image(exitImage.toURI().toURL().toString()));
            exitImageView.setFitHeight(iconSize);
            exitImageView.setFitWidth(iconSize);

            exitBtn = new JFXButton("Exit");
            exitBtn.setGraphic(exitImageView);
            exitBtn.setContentDisplay(ContentDisplay.TOP);

            midPane = new HBox(40);
            midPane.setAlignment(Pos.CENTER);
            midPane.getStyleClass().addAll("midPane");
            midPane.getChildren().addAll(inputBtn, advancedBtn, updateBtn, exitBtn);


            topPane.getChildren().addAll(title, midPane);
            topPane.setAlignment(Pos.CENTER);
                    
        //====================== End ======================
        //====================== bot pane ======================
        
            devTitle = new Label("Developer Team:");
            devTitle.getStyleClass().addAll("title-dev");
            herobmdk = new Label("herobmdk");
            trisTran = new Label("Tris Tran");
            nameLess = new Label("NamelessKing");

            devSection = new VBox(3);
            devSection.getChildren().addAll(devTitle, herobmdk, trisTran, nameLess);

            ImageView facebookView = new ImageView(new Image(facebook.toURI().toURL().toString()));
            ImageView mailView = new ImageView(new Image(mail.toURI().toURL().toString()));
            ImageView gitView = new ImageView(new Image(github.toURI().toURL().toString()));

            int contactIconSize = 40;

            facebookView.setFitWidth(contactIconSize);
            facebookView.setFitHeight(contactIconSize);

            mailView.setFitWidth(contactIconSize);
            mailView.setFitHeight(contactIconSize);

            gitView.setFitWidth(contactIconSize);
            gitView.setFitHeight(contactIconSize);

            contactPane = new HBox(facebookView, mailView, gitView);
            contactPane.setSpacing(15);
            contactPane.setAlignment(Pos.CENTER);

            botPane = new HBox(350);
            botPane.getStyleClass().addAll("botPane");
            botPane.setAlignment(Pos.CENTER);
            botPane.setPadding(new Insets(25));
            botPane.getChildren().addAll(devSection, contactPane);

        //====================== End ======================
        

        mainLayout = new VBox(topPane, botPane);

        primaryStage.setTitle("BroadCast Management");
        primaryStage.setMinWidth(800);
        Scene scene = new Scene(mainLayout);
        File css = new File("src/main/java/GUI/style.css");
        scene.getStylesheets().add("file:///" + css.getAbsolutePath().replace("\\", "/"));
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }
}

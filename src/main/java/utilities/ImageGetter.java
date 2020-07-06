package utilities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.MalformedURLException;

public class ImageGetter {

    public final static File ADD = new File("src/main/resources/icons/plus.png");
    public final static File CLEAR = new File("src/main/resources/icons/clean.png");
    public final static File SEARCH = new File("src/main/resources/icons/search.png");
    public final static File INPUT = new File("src/main/resources/icons/electric-keyboard.png");
    public final static File EXCHANGE = new File("src/main/resources/icons/exchange.png");

    public final static File EXIT = new File("src/main/resources/icons/exit.png");
    public final static File FA = new File("src/main/resources/icons/facebook.png");
    public final static File MAIL = new File("src/main/resources/icons/mail.png");
    public final static File GIT = new File("src/main/resources/icons/git.png");
    public final static File DATABASE = new File("src/main/resources/icons/database.png");
    public final static File DELETE = new File("src/main/resources/icons/delete.png");
    public final static File REDO = new File("src/main/resources/icons/redo.png");
    public final static File SAVE = new File("src/main/resources/icons/save.png");
    public final static File UNDO = new File("src/main/resources/icons/undo.png");
    public final static File REFRESH = new File("src/main/resources/icons/refresh.png");
    public final static File REPORT = new File("src/main/resources/icons/report.png");
    public final static File UPDATE = new File("src/main/resources/icons/update.png");
    public final static File KEYBOARD = new File("src/main/resources/icons/keyboard.png");

    public static ImageView getImageView(File image, int iconSize){

        ImageView imageView = null;
        try {
            imageView = new ImageView(new Image(image.toURI().toURL().toString()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        imageView.setFitHeight(iconSize);
        imageView.setFitWidth(iconSize);

        return imageView;

    }
}

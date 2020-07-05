package utilities;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.io.File;
import java.util.HashMap;

public class MyLayout {

    public final static int ICON_SIZE = 15;
    public final static int FONT_SIZE = 16;
    public final static int SUB_FONT_SIZE = 14;
    public final static int INPUT_HEIGHT = 30;
    public final static double DIVIDER = 0.4;

    public final static double NAME_FIELD = 0.4;
    public final static double SELECTOR_FIELD = 0.6;

    public final static int SPACE = 10;
    public final static int NORMAL_SPACE = 25;
    public final static int BIG_SPACE = 40;

    public final static String[] dayOfWeek = {
            "Mon", "Tue", "Web", "Thu", "Fri", "Sat", "Sun"
    };

    public static JFXButton getJFXButton(String Label, File image, int iconSize) {

        JFXButton button = new JFXButton(Label);
        ImageView imageView = ImageGetter.getImageView(image, iconSize);
        button.setGraphic(imageView);

        return button;
    }

    public static Button getButton(String Label, File image, int iconSize) {

        Button button = new Button(Label);
        ImageView imageView = ImageGetter.getImageView(image, iconSize);
        button.setGraphic(imageView);

        return button;
    }

    public static JFXTextField getTextField(String promtText) {

        JFXTextField textField;

        textField = new JFXTextField();
        textField.setPrefHeight(30);
        textField.setFont(new Font(FONT_SIZE));
        textField.setPromptText(promtText);
        textField.setLabelFloat(true);


        return textField;
    }

    public static JFXTextField getSubTextField(String promtText) {

        JFXTextField textField;

        textField = new JFXTextField();
        textField.setPrefHeight(30);
        textField.setFont(new Font(SUB_FONT_SIZE));
        textField.setPromptText(promtText);
        textField.setLabelFloat(true);


        return textField;
    }

    public static void setValidator(JFXTextField textField, String message) {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage(message);
        textField.setValidators(validator);
        textField.focusedProperty().addListener((o, oldVla, newVal) -> {
            if (!newVal)
                textField.validate();
        });
    }

    public static Tab getTab(String title, Node content){

        Tab tab = new Tab();
        tab.setText(title);
        tab.setContent(content);

        return tab;
    }
}

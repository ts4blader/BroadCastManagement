package Utilities;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class MyDialog{

    public static final Alert.AlertType INFO = Alert.AlertType.INFORMATION;
    public static final Alert.AlertType WARN = Alert.AlertType.WARNING;
    public static final Alert.AlertType ERRO = Alert.AlertType.ERROR;
    public static final Alert.AlertType CONF = Alert.AlertType.CONFIRMATION;

    public static Optional<ButtonType> showDialog(String title, String header, String content, Alert.AlertType alertType){
        Alert alert = new Alert(alertType);

        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        return alert.showAndWait();
    }



}

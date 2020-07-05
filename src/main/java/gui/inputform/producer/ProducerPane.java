package gui.inputform.producer;

import javafx.scene.control.SplitPane;
import utilities.MyLayout;

public class ProducerPane {

    private static DataTable dataTable;

    private static InputField inputField;

    public ProducerPane(){


    }

    public static SplitPane getSplitPane(){

        inputField = new InputField();
        dataTable = new DataTable();

        SplitPane splitPane = new SplitPane(inputField, dataTable);
        splitPane.setDividerPositions(MyLayout.DIVIDER);


        return splitPane;

    }

}

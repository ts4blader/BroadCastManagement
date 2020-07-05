package gui.inputform.nation;

import javafx.scene.control.SplitPane;
import utilities.MyLayout;

public class NationPane {

    private static DataTable dataTable;

    private static InputField inputField;

    public NationPane(){


    }

    public static SplitPane getSplitPane(){

        inputField = new InputField();
        dataTable = new DataTable();

        SplitPane splitPane = new SplitPane(inputField, dataTable);
        splitPane.setDividerPositions(MyLayout.DIVIDER);


        return splitPane;

    }

}

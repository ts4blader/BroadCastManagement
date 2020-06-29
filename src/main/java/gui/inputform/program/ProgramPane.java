package gui.inputform.program;

import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;
import utilities.MyLayout;

public class ProgramPane {

    private static DataTable dataTable;
    private static InputField inputField;

    public ProgramPane() throws Exception {


    }
    public static SplitPane getSplitPane() throws Exception{

        inputField = new InputField();
        dataTable = new DataTable();

        SplitPane splitPane = new SplitPane(inputField, dataTable);
        splitPane.setDividerPositions(MyLayout.DIVIDER);

        return splitPane;

    }

}

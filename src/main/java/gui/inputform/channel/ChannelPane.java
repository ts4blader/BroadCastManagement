package gui.inputform.channel;

import javafx.scene.control.SplitPane;
import utilities.MyLayout;

public class ChannelPane {

    private static DataTable dataTable;

    private static InputField inputField;

    public ChannelPane(){


    }

    public static SplitPane getSplitPane() throws Exception{

        inputField = new InputField();
        dataTable = new DataTable();

        SplitPane splitPane = new SplitPane(inputField, dataTable);
        splitPane.setDividerPositions(MyLayout.DIVIDER);


        return splitPane;

    }

}

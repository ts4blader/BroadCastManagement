package entities;

public class Program {
    private String id;
    private String name;
    private String categoryID;
    private String producerID;


    private static int cout = 0;

    public Program() {
        name = "";
    }

    public Program(String id, String name, String categoryID, String producerID) {
        this.id = id;
        this.name = name;
        this.categoryID = categoryID;
        this.producerID = producerID;
        cout++;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getProducerID() {
        return producerID;
    }

    public void setProducerID(String producerID) {
        this.producerID = producerID;
    }
    public static int getCout() {
        return cout;
    }

    public static void setCout(int cout) {
        Program.cout = cout;
    }

    @Override
    public String toString() {
        return getName();
    }
}

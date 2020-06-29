package bll;

import entities.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CategoryBLL {

    public static ObservableList<Category> getAllCategory(){
        Category horror = new Category("HOR","Horror");
        Category romance = new Category("ROM","Romance");
        Category detective = new Category("DEC","Detective");

        return FXCollections.observableArrayList(horror, romance,detective);
    }

    public static Category getCategoryByID(String ID){

        ObservableList<Category> list = getAllCategory();

        for (Category category : list) {
            if (category.getId().equals(ID))
                return category;
        }

        return null;
    }
}

package gigo.emroxriprap.com.gigo;

import java.util.List;

/**
 * Created by Scott Durica on 12/28/2014.
 */
public class MyList {

    private String listName;
    private String itemsList;
    private int id;

    public MyList() {
    }

    public MyList(String listName, String itemsList) {
        this.listName = listName;
        this.itemsList = itemsList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getItemsList() {
        return itemsList;
    }

    public void setItemsList(String itemsList) {
        this.itemsList = itemsList;
    }
}

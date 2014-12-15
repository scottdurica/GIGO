package gigo.emroxriprap.com.gigo;

/**
 * Created by Suzanne on 12/15/2014.
 */
public class Store {

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String storeName;
    private String storeAddress;
    private int id;
}

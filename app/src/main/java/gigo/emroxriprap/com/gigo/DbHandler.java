package gigo.emroxriprap.com.gigo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Scott Durica on 12/14/2014.
 */
public class DbHandler extends SQLiteOpenHelper {
    String tag = "*****DbHandler*****";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "gigo.db";

    //tables
    private static final String TABLE_STORES = "stores";
    private static final String TABLE_LISTS = "lists";

    //common column names
    public static final String COLUMN_ID = "id";

    //columns in store table
    public static final String COLUMN_STORE_NAME = "storename";
    public static final String COLUMN_STORE_ADDRESS = "storeaddress";


    //columns in lists table
    public static final String COLUMN_LIST_NAME = "listname";
    public static final String COLUMN_LIST_ITEMS = "listitems";

    private static String CREATE_STORES_TABLE = "CREATE TABLE " +
            TABLE_STORES + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," +
            COLUMN_STORE_NAME + " VARCHAR," + COLUMN_STORE_ADDRESS + " VARCHAR" + ")";
    private static String CREATE_LISTS_TABLE = "CREATE TABLE " +
            TABLE_LISTS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," +
            COLUMN_LIST_NAME + " VARCHAR," + COLUMN_LIST_ITEMS + " VARCHAR" + ")";


    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }
    public DbHandler(Context context,String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory,DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STORES_TABLE);
        db.execSQL(CREATE_LISTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LISTS);
        onCreate(db);
    }

    public boolean addStore (Store store){
        ContentValues values = new ContentValues();
        values.put(COLUMN_STORE_NAME, store.getStoreName());
        values.put(COLUMN_STORE_ADDRESS, store.getStoreAddress());

        SQLiteDatabase db = this.getWritableDatabase();
        long finished = db.insert(TABLE_STORES, null, values);
        db.close();
        if (finished != -1) {
            return true;
        }
        return false;

    }    public boolean addLists (MyList list){
        ContentValues values = new ContentValues();
        values.put(COLUMN_LIST_NAME, list.getListName());
        values.put(COLUMN_LIST_ITEMS, list.getItemsList());

        SQLiteDatabase db = this.getWritableDatabase();
        long finished = db.insert(TABLE_LISTS, null, values);
        db.close();
        if (finished != -1) {
            return true;
        }
        return false;

    }
    public List<Store> findStore(String storeName){
        String query = "Select * FROM " + TABLE_STORES + " WHERE " + COLUMN_STORE_NAME + " = \"" + storeName + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        List<Store> stores = new ArrayList<Store>();

        while(cursor.moveToNext()){
            Store s = new Store();
            stores.add(s);
        }
        return stores;
    }

    public List<Store> getAllStores() {
        String query = "Select * from " + TABLE_STORES + "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        List<Store> stores = new ArrayList<Store>();
        if (cursor.moveToFirst()) {
            do {
                Store s = new Store();
                s.setStoreName(cursor.getString(1));
                s.setStoreAddress(cursor.getString(2));
                stores.add(s);
            } while (cursor.moveToNext());

        }
        return stores;
    }
    public List<MyList> getAllLists() {
        String query = "Select * from " + TABLE_LISTS + "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        List<MyList> listLists = new ArrayList<MyList>();
        if (cursor.moveToFirst()) {
            do {
                MyList s = new MyList();
                s.setListName(cursor.getString(1));
                s.setItemsList(cursor.getString(2));
                listLists.add(s);
            }while(cursor.moveToNext());

        }
        return listLists;

    }
    public void deleteAllStores(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_STORES);
    }
}

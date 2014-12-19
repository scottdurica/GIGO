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
 * Created by Suzanne on 12/14/2014.
 */
public class DbHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "gigo.db";
    private static final String TABLE_STORES = "stores";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_STORE_NAME = "storename";
    public static final String COLUMN_STORE_ADDRESS = "storeaddress";


    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);

    }
    public DbHandler(Context context,String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory,DATABASE_VERSION);
    }
    //this is a comment test to see if it's pushing to Git


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STORES_TABLE = "CREATE TABLE " +
                TABLE_STORES + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_STORE_NAME + " VARCHAR," + COLUMN_STORE_ADDRESS + " VARCHAR" + ")";
        db.execSQL(CREATE_STORES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORES);
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
            }while(cursor.moveToNext());

        }
        return stores;

    }
    public void deleteAllStores(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_STORES);
    }
}

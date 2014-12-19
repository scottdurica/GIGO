package gigo.emroxriprap.com.gigo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class SummaryScreen extends ActionBarActivity implements View.OnClickListener {

    private EditText storeName, storeAddress;
    private Button go, enterStoreToDb;
    private String name, address;
    private ListView storeListView;

    private MyCustomAdapter adapter;
    private List<Store> storeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DbHandler db = new DbHandler(this);
        storeList = db.getAllStores();
        setContentView(R.layout.activity_summary_screen);

        loadViews();
    }

    private void loadViews() {
        go = (Button)findViewById(R.id.b_go_to_items);
        enterStoreToDb = (Button)findViewById(R.id.b_enter_store);
        storeName = (EditText)findViewById(R.id.et_store_name);
        storeAddress = (EditText) findViewById(R.id.et_store_address);
        storeListView = (ListView)findViewById(R.id.lv_stores);

        go.setOnClickListener(this);
        enterStoreToDb.setOnClickListener(this);
        storeAddress.setOnClickListener(this);
        storeAddress.setOnClickListener(this);

        adapter = new MyCustomAdapter(this);
        storeListView.setAdapter(adapter);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_summary_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.b_enter_store:
                name = storeName.getText().toString();
                address = storeAddress.getText().toString();
                Store s = new Store();
                s.setStoreName(name);
                s.setStoreAddress(address);

                DbHandler db = new DbHandler(this);

                if (db.addStore(s)) {
                    Toast.makeText(this, "Store saved", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(this, "Error saving store", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.b_go_to_items:
                Intent i = new Intent(SummaryScreen.this, ItemsScreen.class);
                startActivity(i);
                break;
        }

    }
    private class MyCustomAdapter extends BaseAdapter{
        private Context myContext;


        public MyCustomAdapter(Context context) {
            myContext = context;
        }


        @Override
        public int getCount() {
            return storeList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_item_storelist, null);
            }
            TextView nameView = (TextView)convertView.findViewById(R.id.tv_list_item_storelist_storename);
            TextView addressView = (TextView)convertView.findViewById(R.id.tv_list_item_storelist_storeaddress);

            Store s = storeList.get(position);
            nameView.setText(s.getStoreName());
            addressView.setText(s.getStoreAddress());
            return convertView;
        }
    }
}

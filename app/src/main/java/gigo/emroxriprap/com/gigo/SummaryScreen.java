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
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class SummaryScreen extends ActionBarActivity implements View.OnClickListener {


    private ListView storeListView;

    private MyCustomAdapter adapter;
    private List<Store> storeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DbHandler db = new DbHandler(this);
        storeList = db.getAllStores();
//        testAddSampleStores(db);
        setContentView(R.layout.activity_summary_screen);

        loadViews();
    }

    private void testAddSampleStores(DbHandler db) {
        Store one = new Store("Walmart","Manchester");
        Store two = new Store("Walmart","Derry");
        Store three = new Store("Market Basket","Londonderry");
        db.addStore(one);
        db.addStore(two);
        db.addStore(three);
        storeList=db.getAllStores();
    }

    private void loadViews() {
        storeListView = (ListView)findViewById(R.id.lv_stores);
        adapter = new MyCustomAdapter(this);
        storeListView.setAdapter(adapter);

        storeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Store s = storeList.get(position);
                Log.d("LOOKIE HERE","Value is "+ s.getStoreName());
                Intent intent = new Intent(SummaryScreen.this, ListScreen.class);
                intent.putExtra("id", s.getId());
                intent.putExtra("name",s.getStoreName());
                intent.putExtra("address", s.getStoreAddress());
                startActivity(intent);
            }
        });


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
            case 0:

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

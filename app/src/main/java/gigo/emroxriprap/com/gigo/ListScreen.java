package gigo.emroxriprap.com.gigo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class ListScreen extends ActionBarActivity {

    private TextView storeName, storeAddress;
    private ListView listListView;
    private int storeId;

    private MyCustomAdapter adapter;
    private List<MyList> listsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DbHandler db = new DbHandler(this);
//        testAddSampleListsToDB(db);
        listsList = db.getAllLists();
        setContentView(R.layout.activity_list_screen);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String storeName = this.getIntent().getStringExtra("name");
        String storeAddress = this.getIntent().getStringExtra("address");
        storeId = this.getIntent().getIntExtra("id",0);
        setTitle(storeName + " (" + storeAddress + ")");

        loadViews();
    }

    private void testAddSampleListsToDB(DbHandler db) {
        MyList one = new MyList("Weekly List", "eggs, bacon, milk");
        MyList two = new MyList("Bi-Weekly List", "sugar, flour, coffee");
        db.addLists(one);
        db.addLists(two);
    }

    private void loadViews() {
        listListView = (ListView)findViewById(R.id.lv_lists);
        listListView.setAdapter(new MyCustomAdapter(this));

        listListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyList l = listsList.get(position);
//                Log.d("LOOKIE HERE","Value is "+ s.getStoreName());
                Intent intent = new Intent(ListScreen.this, GroceryListScreen.class);
                intent.putExtra("id",l.getId());
                intent.putExtra("storeId",storeId);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_screen, menu);
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
    private class MyCustomAdapter extends BaseAdapter {
        private Context myContext;


        public MyCustomAdapter(Context context) {
            myContext = context;
        }


        @Override
        public int getCount() {
            return listsList.size();
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
            TextView countView = (TextView)convertView.findViewById(R.id.tv_list_item_storelist_storeaddress);

            MyList l = listsList.get(position);
            nameView.setText(l.getListName());
            countView.setText(l.getItemsList());
            return convertView;
        }
    }
}

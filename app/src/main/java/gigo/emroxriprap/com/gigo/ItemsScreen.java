package gigo.emroxriprap.com.gigo;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ItemsScreen extends ActionBarActivity {

    private ExpandableListView listView;
    private MyCustomAdapter adapter;
    private List<Store>storeList;
    private List<String>listForView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_screen);
        DbHandler db = new DbHandler(this);
        storeList = db.getAllStores();
        adapter = new MyCustomAdapter(this);
        listView = (ExpandableListView)findViewById(R.id.lv_items);
        listView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_items_screen, menu);
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
    private class MyCustomAdapter extends BaseExpandableListAdapter{
        private  Context myContext;

        public MyCustomAdapter(Context context) {
            myContext = context;
        }
        @Override
        public int getGroupCount() {
            return storeList.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return 0;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return null;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return null;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return 0;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            if(convertView == null){
                LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_item_itemlist, null);
            }
            TextView name = (TextView)convertView.findViewById(R.id.tv_itemslist_storename);
            TextView address = (TextView)convertView.findViewById(R.id.tv_itemslist_address);
            TextView itemsCount = (TextView)convertView.findViewById(R.id.tv_itemslist_itemscount);

            Store s = storeList.get(groupPosition);
            name.setText(s.getStoreName());
            address.setText(s.getStoreAddress());
            itemsCount.setText("10");


            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            return null;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }
}

package gigo.emroxriprap.com.gigo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SummaryScreen extends ActionBarActivity implements View.OnClickListener {

    EditText storeName, storeAddress;
    Button go, enterStoreToDb;
    String name, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_screen);
        loadViews();
    }

    private void loadViews() {
        go = (Button)findViewById(R.id.b_go_to_items);
        enterStoreToDb = (Button)findViewById(R.id.b_enter_store);
        storeName = (EditText)findViewById(R.id.et_store_name);
        storeAddress = (EditText) findViewById(R.id.et_store_address);

        go.setOnClickListener(this);
        enterStoreToDb.setOnClickListener(this);
        storeAddress.setOnClickListener(this);
        storeAddress.setOnClickListener(this);

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
//                Log.d("tag", "here");
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
        }

    }
}

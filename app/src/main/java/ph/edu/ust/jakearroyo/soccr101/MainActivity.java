package ph.edu.ust.jakearroyo.soccr101;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;

import ph.edu.ust.jakearroyo.soccr101.adapter.ListViewAdapter;
import ph.edu.ust.jakearroyo.soccr101.db.DaoMaster;
import ph.edu.ust.jakearroyo.soccr101.db.DaoSession;
import ph.edu.ust.jakearroyo.soccr101.db.DatabaseHandler;
import ph.edu.ust.jakearroyo.soccr101.db.Org;
import ph.edu.ust.jakearroyo.soccr101.db.OrgDao;


public class MainActivity extends Activity {

    private SQLiteDatabase db;

    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private OrgDao orgDao;

    private ListViewAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler helper = DatabaseHandler.getInstance(getApplication());
        db = helper.getReadableDatabase();

        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        orgDao = daoSession.getOrgDao();

        adapter = new ListViewAdapter(this, orgDao.loadAll());

        FadingActionBarHelper fadingActionBarHelper = new FadingActionBarHelper()
                .actionBarBackground(R.drawable.ab_background)
                .headerLayout(R.layout.header)
                .contentLayout(R.layout.activity_main);
        setContentView(fadingActionBarHelper.createView(this));
        fadingActionBarHelper.initActionBar(this);

        listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(adapter);
        final Context self = this;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i > 0){
                    Org org = (Org)adapter.getItem(i - 1);
                    Intent intent = new Intent(self, About.class);
                    Bundle extras = new Bundle();
                    extras.putLong("id", org.getId());
                    intent.putExtras(extras);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

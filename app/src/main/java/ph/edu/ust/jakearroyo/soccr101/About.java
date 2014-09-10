package ph.edu.ust.jakearroyo.soccr101;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.achep.header2actionbar.FadingActionBarHelper;

import ph.edu.ust.jakearroyo.soccr101.db.DaoMaster;
import ph.edu.ust.jakearroyo.soccr101.db.DaoSession;
import ph.edu.ust.jakearroyo.soccr101.db.DatabaseHandler;
import ph.edu.ust.jakearroyo.soccr101.db.Org;
import ph.edu.ust.jakearroyo.soccr101.db.OrgDao;

public class About extends Activity {
    private FadingActionBarHelper mFadingActionBarHelper;

    private Long id;

    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private OrgDao orgDao;
    private Org org;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        mFadingActionBarHelper = new FadingActionBarHelper(getActionBar(),
                getResources().getDrawable(R.drawable.actionbar_bg));

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new ContentFragment())
                    .commit();
        }

        Bundle extras = getIntent().getExtras();
        id = extras.getLong("id");

        DatabaseHandler helper = DatabaseHandler.getInstance(getApplication());
        db = helper.getReadableDatabase();

        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        orgDao = daoSession.getOrgDao();
        org = orgDao.queryRaw("WHERE _id = " + id).get(0);

        setTitle(org.getName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.about, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public FadingActionBarHelper getFadingActionBarHelper() {
        return mFadingActionBarHelper;
    }

    public Org getOrg(){
        return org;
    }
}

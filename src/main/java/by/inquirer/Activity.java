package by.inquirer;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import by.inquirer.db.DbHelper;
import by.inquirer.fragments.InquirerFragment;
import by.inquirer.fragments.InquirersFragment;
import by.inquirer.fragments.OnFragmentInteractionListener;
import by.inquirer.helpers.UriHelper;


public class Activity extends AppCompatActivity implements OnFragmentInteractionListener {

    private DbHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, InquirersFragment.newInstance())
                    .commit();
        }
        getHelper();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_help) {
            return true;
            //TODO: move to help page
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

        switch (UriHelper.getTarget(uri)) {
            case QUESTION:
                break;
            case INQUIRERS_LIST:
                getFragmentManager().beginTransaction()
                        .add(R.id.container, InquirersFragment.newInstance())
                        .commit();
                break;
            case INQUIRER:
                getFragmentManager().beginTransaction()
                        .add(R.id.container, InquirerFragment.newInstance(UriHelper.getId(uri)))
                        .commit();
                break;
        }
    }

    public DbHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper =
                    OpenHelperManager.getHelper(this, DbHelper.class);
        }
        return databaseHelper;
    }

}

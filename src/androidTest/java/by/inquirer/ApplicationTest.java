package by.inquirer;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.lang.Exception;

import by.inquirer.db.DbHelper;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    Application mApplication;
    public ApplicationTest() {
        super(Application.class);
        DbHelper.setContext(getContext());
    }

    public void setUp() {
        try {
            super.setUp();
            createApplication();
            mApplication = getApplication();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // final RenamingMockContext mockContext = new RenamingMockContext(
        // getContext());
        // setContext(mockContext);


    }


}
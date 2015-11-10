package by.inquirer.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import by.inquirer.Activity;
import by.inquirer.BuildConfig;
import by.inquirer.R;
import by.inquirer.buisness.*;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;


public class DbHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "questinario.db";
    private static final int DATABASE_VERSION = 1;
    private static Context context;
    private static DbHelper dbHelper;
    private static Dao<Question, Integer> qDao;



    public DbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);

        dbHelper = this;

    }

    public static void setContext(Context ctx) {
        context = ctx;
    }

    public static DbHelper getDbHelper() {
        if (dbHelper == null) dbHelper = new DbHelper(context);
        return dbHelper;
    }

    static Context getContext() {
        return context;
    }

    static void setContext(Activity activity) {
        setContext(activity.getApplicationContext());
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            Log.i(DbHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, Answer.class);
            TableUtils.createTable(connectionSource, Inquirer.class);
            TableUtils.createTable(connectionSource, Question.class);
            TableUtils.createTable(connectionSource, SelectedAnswer.class);
            TableUtils.createTable(connectionSource, AnswerList.class);
        } catch (java.sql.SQLException e) {
            if (BuildConfig.DEBUG) Log.e(DbHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DbHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, Answer.class, true);
            TableUtils.dropTable(connectionSource, Question.class, true);
            TableUtils.dropTable(connectionSource, Inquirer.class, true);
            TableUtils.dropTable(connectionSource, SelectedAnswer.class, true);
            TableUtils.dropTable(connectionSource, AnswerList.class, true);
            // after we drop the old databases, we create the new ones
            onCreate(db, connectionSource);
        } catch (java.sql.SQLException e) {
            if (BuildConfig.DEBUG) Log.e(DbHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    public Dao<Question, Integer> getQuestionDao() throws java.sql.SQLException {
        if (qDao == null) qDao = getDao(Question.class);
        return qDao;
    }



    @Override
    public void close() {
        super.close();
        qDao = null;
    }


}

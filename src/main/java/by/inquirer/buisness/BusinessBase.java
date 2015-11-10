package by.inquirer.buisness;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.misc.BaseDaoEnabled;

import java.sql.SQLException;

import by.inquirer.db.DbHelper;


public abstract class BusinessBase extends BaseDaoEnabled implements IIdble
{
    protected static final String ID = "id";
    public static final long MISS_ID = -1;

    @DatabaseField(generatedId = true, columnName = ID)
    long id= MISS_ID;

    public BusinessBase(){
        try {
            setDao(DbHelper.getDbHelper().getDao(this.getClass()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BusinessBase(long id){
        this();
        try {
            this.id = id;
            this.refresh();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long getId() {
        return id;
    }

}

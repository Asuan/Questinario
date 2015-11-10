package by.inquirer.buisness;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.inquirer.helpers.UriHelper;


@DatabaseTable(tableName = Inquirer.TABLE_NAME)
public class Inquirer extends BusinessBase implements  IUrible {
    public static final String TABLE_NAME = "inquirers";

    @DatabaseField(canBeNull = false)
    public String name;

    public Inquirer(){
        super();
    }

    public Inquirer (long id){
        super(id);
    }

    @NonNull
    public List<Question> getQuestions(){
        return Question.getAll(id);
    }

    public long getId() {
        return id;
    }

    @Override
    public Uri getUri() {
        return UriHelper.getInquirerUri(id);
    }

    @NonNull
    public static List<Inquirer> getAll() {
        Inquirer i = new Inquirer();

        try {
            final QueryBuilder qb = i.getDao().queryBuilder();
            qb.orderBy(ID, false);
            return i.getDao().query(qb.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

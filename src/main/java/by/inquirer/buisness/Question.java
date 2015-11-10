package by.inquirer.buisness;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.inquirer.db.DbHelper;
import by.inquirer.helpers.UriHelper;


@DatabaseTable(tableName = Question.TABLE_NAME)
public class Question extends BusinessBase implements IUrible {
    public static final String TABLE_NAME = "questions";
    private static final String ORDER_NUMBER = "orderNumber";
    private static final String INQUIRER_ID = "inquirer_id";


    @DatabaseField(canBeNull = false)
    public String question = "";

    @DatabaseField (canBeNull = false)
    public int answerType = QuestionTypes.SINGLE_ANSWER.ordinal();

    @ForeignCollectionField(eager = true)
    public ForeignCollection<Answer> answers;

    @DatabaseField(foreign = true, canBeNull = false, columnName = INQUIRER_ID)
    Inquirer inquirer;

    @DatabaseField(canBeNull = false, columnName = ORDER_NUMBER)
    long orderNumber;


    enum  QuestionTypes {
        SINGLE_ANSWER,
        MULTI_ANSWER,
    }

    private Question(){}

    public Question(Inquirer inquirer) {
        super();
        setInquirer(inquirer);
    }

    public Question(long id){
        super(id);
    }

    @NonNull
    public static List getAll(long inquirerId){
        try {
            Dao<Question, Integer> qDao = DbHelper.getDbHelper().getQuestionDao();
            final QueryBuilder qb = qDao.queryBuilder();
            qb.where().eq(INQUIRER_ID, inquirerId);
            qb.orderBy(ORDER_NUMBER, true);
            return qDao.query(qb.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @NonNull
    public static List<Question> getAll(Inquirer inquirer){
        return getAll(inquirer.id);
    }

    public void setInquirer(long inquirerId ){
        setInquirer(new Inquirer(inquirerId));
    }

    public void setInquirer(Inquirer inquirer){
        this.inquirer = inquirer;
        this.orderNumber = inquirer.getQuestions().size() + 1;
    }

    public long getId(){
        return id;
    }

    @Override
    public Uri getUri() {
        return UriHelper.getQuestionUri(id);
    }

}

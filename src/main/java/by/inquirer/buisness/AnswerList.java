package by.inquirer.buisness;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = AnswerList.TABLE_NAME)
public class AnswerList extends BusinessBase {
    public static final String TABLE_NAME = "answers_list";

    @DatabaseField (canBeNull = false)
    Date date;

    @DatabaseField(foreign = true, canBeNull = false)
    Inquirer inquirer;

    private AnswerList(){}

    public  AnswerList(Inquirer inquirer){
        super();
        this.inquirer = inquirer;
    }

    public AnswerList (long id) { super(id); }

}

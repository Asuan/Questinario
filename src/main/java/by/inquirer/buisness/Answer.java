package by.inquirer.buisness;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = Answer.TABLE_NAME)
public class Answer extends BusinessBase {
    public static final String TABLE_NAME = "answers";

    @DatabaseField(canBeNull = false)
    public String value;

    @DatabaseField(canBeNull = false)
    private int type = AnswerTypes.CHOOSE_ITEM.ordinal();

    @DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
    Question question;

    private Answer(){}

    public Answer(Question question){
        super();
        this.question = question;
    }

    public void setType(AnswerTypes types) {
        type = types.ordinal();
    }

    public AnswerTypes getType() {
        return AnswerTypes.values()[type];
    }

    public Answer (long id, Question question){
        super(id);
        this.question = question;
    }

    enum AnswerTypes {
        FREE_STRING,
        CHOOSE_ITEM,
    }
}

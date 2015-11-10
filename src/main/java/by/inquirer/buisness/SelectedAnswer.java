package by.inquirer.buisness;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.misc.BaseDaoEnabled;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = SelectedAnswer.TABLE_NAME)
public class SelectedAnswer extends BusinessBase {
    public static final String TABLE_NAME = "selected_answer";

    @DatabaseField(foreign = true, canBeNull = false)
    Answer answer;

    @DatabaseField(foreign = true, canBeNull = false)
    Question question;

    @DatabaseField(foreign = true, canBeNull = false)
    AnswerList answerList;

    SelectedAnswer(){super();}

    SelectedAnswer(long id){ super(id);}
}

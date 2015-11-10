package by.inquirer.db;


import by.inquirer.buisness.*;
import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.File;

public class DatabaseConfigUtil extends OrmLiteConfigUtil {

    private static final Class<?>[] classes = new Class[]{
            Answer.class, Inquirer.class, Question.class, SelectedAnswer.class, AnswerList.class};

    public static void main(String[] args) throws Exception {
        writeConfigFile(new File("c:/temp/ormlite_config.txt"), classes);
    }
}

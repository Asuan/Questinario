package by.inquirer.helpers;

import android.net.Uri;
import by.inquirer.buisness.IUrible;

/**
 * Created by Sergey on 11.02.2015.
 */
public class UriHelper {

    static String APP_BUILD = "QUESTINARIO";
    static String QUESTIONS_LIST = "QUESTION_LIST";
    static String QUESTION = "QUESTION";
    static String INQUIRERS_LIST = "INQUIRERS_LIST";
    static String INQUIRER = "INQUIRER";

    public enum uriTargets {

        QUESTION,
        INQUIRERS_LIST,
        INQUIRER
    }

    public static uriTargets getTarget(Uri uri){
        String ssp = uri.getSchemeSpecificPart();
        if (ssp.equals(QUESTION)) return uriTargets.QUESTION;
        if (ssp.equals(INQUIRER)) return uriTargets.INQUIRER;
        if (ssp.equals(INQUIRERS_LIST)) return uriTargets.INQUIRERS_LIST;

        return uriTargets.INQUIRERS_LIST;
    }

    public static long getId(Uri uri){
        String uri_fragment = uri.getFragment();
        if (uri_fragment == null) return 0;
        return Long.parseLong(uri_fragment);
    }

    public static Uri getQuestionsUri(long id){
        return Uri.fromParts(APP_BUILD, QUESTIONS_LIST, Long.toString(id));
    }

    public static Uri getQuestionUri(long id){
        return Uri.fromParts(APP_BUILD, QUESTION, Long.toString(id));
    }

    public static Uri getInquirerUri(long id){
        return Uri.fromParts(APP_BUILD, INQUIRER, Long.toString(id));
    }

    public static Uri getInquirersUri(){
        return Uri.fromParts(APP_BUILD, INQUIRERS_LIST,"");
    }

    public static Uri getUri(IUrible object){
        return object.getUri();
    }





}

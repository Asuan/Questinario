package by.inquirer;

/**
 * Created by Sergey on 17.03.2015.
 */

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import by.inquirer.buisness.AnswerList;
import by.inquirer.buisness.Question;
import by.inquirer.fragments.QuestionPlayerFragment;

import java.util.List;


/**
 * A {@link android.support.v4.app.FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class QuestionPlayerAdapter extends FragmentPagerAdapter {
    private Context ctx;
    private List<Question> questions;
    private AnswerList answerList;

    public QuestionPlayerAdapter(FragmentManager fm, Context context, List<Question> questions, AnswerList aList) {
        super(fm);
        ctx = context;
        this.questions = questions;
        answerList = aList;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return QuestionPlayerFragment.newInstance(questions.get(position).getId(), answerList.getId());
    }

    @Override
    public int getCount() {
        return questions.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return ctx.getString(R.string.question_number) + String.valueOf (position +1);
    }
}

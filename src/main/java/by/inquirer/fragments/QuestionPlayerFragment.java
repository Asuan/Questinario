package by.inquirer.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import by.inquirer.R;
import by.inquirer.buisness.AnswerList;
import by.inquirer.buisness.Question;
import by.inquirer.buisness.SelectedAnswer;

/**
 * A placeholder fragment containing a simple view.
 */
public class QuestionPlayerFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_QUESTION_ID = "question_id";
    private static final String ARG_ANS_LIST_ID = "answer_list_id";

    private Question question;
    private AnswerList answerList;
    private SelectedAnswer selectedAnswer;

    /**
     * Returns a new instance of this fragment for the given Ids.
     */
    public static QuestionPlayerFragment newInstance(long questionId, long answerListId) {
        QuestionPlayerFragment fragment = new QuestionPlayerFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_QUESTION_ID, questionId);
        args.putLong(ARG_ANS_LIST_ID,answerListId);
        fragment.setArguments(args);
        return fragment;
    }

    public QuestionPlayerFragment() {  }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_questions_player, container, false);
        question = new Question(getArguments().getLong(ARG_QUESTION_ID, -1));
        answerList = new AnswerList(getArguments().getLong(ARG_ANS_LIST_ID, -1));

        return rootView;
    }

    private void setDataToUI() {
        View rootView = this.getView();


    }
}

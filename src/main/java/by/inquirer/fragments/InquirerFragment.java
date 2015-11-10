package by.inquirer.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import by.inquirer.R;

import by.inquirer.buisness.BusinessBase;
import by.inquirer.buisness.Inquirer;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class InquirerFragment extends Fragment implements ExpandableListView.OnGroupClickListener{

    private static final String INQUIRER_ID = "INQUIRER_ID";
    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private ExpandableListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private BaseExpandableListAdapter mAdapter;
    private Inquirer mInquirer;

    public static InquirerFragment newInstance(long inquirerId) {
        Bundle bundle = new Bundle();
        bundle.putLong(INQUIRER_ID,inquirerId);
        InquirerFragment fragment = new InquirerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public InquirerFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            mInquirer = new Inquirer(getArguments().getLong(INQUIRER_ID, BusinessBase.MISS_ID));

        mAdapter = new QuestionListAdapter(getActivity().getApplicationContext(), mInquirer.getQuestions());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_questions_list, container, false);

        // Set the adapter
        mListView = (ExpandableListView) view.findViewById(android.R.id.list);
        mListView.setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnGroupClickListener(this);
        mListView.setEmptyView(view.findViewById(android.R.id.empty));
        setEmptyText(this.getActivity().getString(R.string.no_questions));
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }


    /**
     * Callback method to be invoked when a group in this expandable list has
     * been clicked.
     *
     * @param parent        The ExpandableListConnector where the click happened
     * @param v             The view within the expandable list/ListView that was clicked
     * @param groupPosition The group position that was clicked
     * @param id            The row id of the group that was clicked
     * @return True if the click was handled
     */
    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        parent.getAdapter().getItem(groupPosition);
        return true;
    }
}

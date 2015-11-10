package by.inquirer.fragments;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import by.inquirer.R;
import by.inquirer.buisness.Inquirer;

public class InquirersListAdapter extends ArrayAdapter<Inquirer> {

    List<Inquirer> inquirers;
    Context context;

    private int layoutResourceId;

    public InquirersListAdapter(Context context, List<Inquirer> data) {

        super(context, R.layout.inquirer_list_item, data);
        layoutResourceId = R.layout.inquirer_list_item;
        this.inquirers = data;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        InqItem holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new InqItem();
            holder.name = (TextView) row.findViewById(R.id.inquirer_name_la);
            holder.questionsNumber = (TextView) row.findViewById(R.id.inquirer_questions_count_la);
            row.setTag(holder);
        } else
            holder = (InqItem) row.getTag();

        Inquirer inquirer = inquirers.get(position);
        holder.name.setText(inquirer.name);
        holder.questionsNumber.setText(inquirer.getQuestions().size());
        return row;
    }

    @Override
    public Inquirer getItem(int position) {
        return inquirers.get(position);
    }

    class InqItem {
        TextView name;
        TextView questionsNumber;
    }
}

package com.example.nln.nedroid.Forum;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nln.nedroid.Helper.Book;
import com.example.nln.nedroid.NewsAndEvents.ItemClickListener;
import com.example.nln.nedroid.PageTwo.FragmentAssignment;
import com.example.nln.nedroid.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NLN on 8/12/2016.
 */
public class QuestionAnswerAdapter extends ArrayAdapter<Answers> {

    private int layoutResource;
    ArrayList<Answers> answers;

    public QuestionAnswerAdapter(Context context, int layoutResource, ArrayList<Answers> answers) {
        super(context, layoutResource, answers);
        this.layoutResource = layoutResource;
        this.answers = answers;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(layoutResource, null);
        }

        TextView name = (TextView) view.findViewById(R.id.name);
        TextView answer = (TextView) view.findViewById(R.id.answer);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);

        Answers ans = answers.get(position);

        name.setText(ans.getName());
        answer.setText(ans.getAnswer());
        Glide.with(icon.getContext())
                .load(ans.getPhotourl())
                .into(icon);

        return view;
    }
}
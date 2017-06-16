package com.example.nln.nedroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by NLN on 8/12/2016.
 */
public class SettingsAdapter extends ArrayAdapter<String> {

    ArrayList<String> arrayList;
    private int layoutResource;

    public SettingsAdapter(Context context, int layoutResource, ArrayList<String> strings) {
        super(context, layoutResource, strings);
        this.layoutResource = layoutResource;
        this.arrayList = strings;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(layoutResource, null);
        }

        TextView tilte = (TextView) view.findViewById(R.id.title);
        String str = arrayList.get(position);

        tilte.setText(str);

        return view;
    }
}
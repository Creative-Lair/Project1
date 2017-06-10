package com.example.nln.nedroid.NavigationMenu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nln.nedroid.R;

import java.util.List;

/**
 * Created by NLN on 4/22/2017.
 */

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.MyViewHolder> {

    public Context mContext;
    private List<AttendanceList> albumList;

    public AttendanceAdapter(Context mContext, List<AttendanceList> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.attendance_list, parent, false);

        final MyViewHolder MyViewHolder = new MyViewHolder(itemView);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        AttendanceList list = albumList.get(position);

        holder.code.setText(list.getCode());
        holder.tclasses.setText("" + list.getClassHeld());
        holder.aclasses.setText("" + list.getClassAttend());
        holder.percen.setText(list.getPercentage() + "%");

    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView code, tclasses, aclasses, percen;

        public MyViewHolder(View view) {
            super(view);

            code = (TextView) view.findViewById(R.id.code);
            tclasses = (TextView) view.findViewById(R.id.tclasses);
            aclasses = (TextView) view.findViewById(R.id.aclasses);
            percen = (TextView) view.findViewById(R.id.percent);

        }

    }
}
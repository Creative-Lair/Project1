package com.example.nln.nedroid.Attendance;

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

public class DetailAdaptor extends RecyclerView.Adapter<DetailAdaptor.MyViewHolder> {

    public Context mContext;
    String buttonSelected;
    private List<Lecture_detail> albumList;
    private ItemClickListener clickListener;

    public DetailAdaptor(Context mContext, List<Lecture_detail> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lecture_detail, parent, false);

        final MyViewHolder MyViewHolder = new MyViewHolder(itemView);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Lecture_detail detail = albumList.get(position);

        holder.topic.setText(detail.getTopic());
        holder.time.setText(detail.getTime());
        holder.date.setText(detail.getDate());

    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView time, date, topic;

        public MyViewHolder(View view) {
            super(view);
            time = (TextView) view.findViewById(R.id.date);
            date = (TextView) view.findViewById(R.id.date);
            topic = (TextView) view.findViewById(R.id.topic);


        }

    }
}
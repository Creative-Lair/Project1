package com.example.nln.nedroid.Notification;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nln.nedroid.NewsAndEvents.ItemClickListener;
import com.example.nln.nedroid.R;

import java.util.List;

/**
 * Created by NLN on 4/22/2017.
 */

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.MyViewHolder> {

    private Context mContext;
    private List<Notice> albumList;
    private ItemClickListener clickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView date;
        public TextView title;
        public TextView des;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            date = (TextView) view.findViewById(R.id.date);
            title = (TextView) view.findViewById(R.id.title);
            des = (TextView) view.findViewById(R.id.description);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            view.setTag(view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) clickListener.onClick(v, getAdapterPosition());
        }
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }


    public NoticeAdapter(Context mContext, List<Notice> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public NoticeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_notification_card, parent, false);

        final NoticeAdapter.MyViewHolder MyViewHolder = new NoticeAdapter.MyViewHolder( itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(v, MyViewHolder.getPosition());
//                Toast.makeText(mContext, " Just cliked item at position " + MyViewHolder.getPosition(), Toast.LENGTH_LONG).show();
            }
        });
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final NoticeAdapter.MyViewHolder holder, int position) {
        Notice album = albumList.get(position);
        holder.date.setText(album.getDate());
        holder.title.setText(album.getTitle());
        holder.des.setText(album.getDesciption());
        // loading album cover using Glide library
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}

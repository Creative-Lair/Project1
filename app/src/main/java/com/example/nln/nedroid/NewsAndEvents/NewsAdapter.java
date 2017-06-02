package com.example.nln.nedroid.NewsAndEvents;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nln.nedroid.PageOne.FragmentOne;
import com.example.nln.nedroid.R;

import java.util.List;

/**
 * Created by NLN on 4/22/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private FragmentOne mContext;
    private List<News> albumList;
    private ItemClickListener clickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView title;
        public ImageView thumbnail;
        public ImageView pic1;
        public TextView name1;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            pic1 = (ImageView) view.findViewById(R.id.pic1);
            name1 = (TextView) view.findViewById(R.id.name1);
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


    public NewsAdapter(FragmentOne mContext, List<News> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_news_events, parent, false);

        final MyViewHolder MyViewHolder = new MyViewHolder( itemView);

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
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        News album = albumList.get(position);
        holder.title.setText(album.getName());
        holder.name1.setText(album.getName1());
        // loading album cover using Glide library
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);
        Glide.with(mContext).load(album.getThumbnail()).into(holder.pic1);
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}

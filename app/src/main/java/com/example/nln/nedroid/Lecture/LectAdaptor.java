package com.example.nln.nedroid.Lecture;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nln.nedroid.NewsAndEvents.ItemClickListener;
import com.example.nln.nedroid.PageTwo.FragmentLecture;
import com.example.nln.nedroid.R;

import java.util.List;

/**
 * Created by NLN on 8/14/2016.
 */
public class LectAdaptor extends RecyclerView.Adapter<LectAdaptor.MyViewHolder> {

    private FragmentLecture lContext;
    private List<Lect> LectList;

    private ItemClickListener clickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView title;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
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

    public LectAdaptor(FragmentLecture mContext, List<Lect> albumList) {
        this.lContext = mContext;
        this.LectList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_lect, parent, false);
        final MyViewHolder MyViewHolder = new MyViewHolder( itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(v, MyViewHolder.getPosition());
            }
        });
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Lect album = LectList.get(position);
        holder.title.setText(album.getName());

        // loading album cover using Glide library
//        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return LectList.size();
    }

}

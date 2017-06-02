package com.example.nln.nedroid.Assigment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nln.nedroid.NewsAndEvents.ItemClickListener;
import com.example.nln.nedroid.PageTwo.FragmentAssignment;
import com.example.nln.nedroid.R;

import java.util.List;

/**
 * Created by NLN on 8/12/2016.
 */
public class AssgnAdapter extends RecyclerView.Adapter<AssgnAdapter.MyViewHolder> {

    private FragmentAssignment mContext;
    private List<Assg> albumList;

    private ItemClickListener clickListener;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView title;
//        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
//            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
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

    public AssgnAdapter(FragmentAssignment mContext, List<Assg> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.card_assgn, parent, false);
//
//        return new MyViewHolder(itemView);

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_assgn, parent, false);
        final MyViewHolder MyViewHolder = new MyViewHolder( itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                listener.onItemClick(v, MyViewHolder.getPosition());
                clickListener.onClick(v, MyViewHolder.getPosition());
//                Toast.makeText(mContext, " Just cliked item at position " + MyViewHolder.getPosition(), Toast.LENGTH_LONG).show();
            }
        });
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Assg album = albumList.get(position);
        holder.title.setText(album.getName());

        // loading album cover using Glide library
//        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

}
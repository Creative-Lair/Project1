package com.example.nln.nedroid.Forum;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nln.nedroid.NewsAndEvents.ItemClickListener;
import com.example.nln.nedroid.PageOne.FragmentThree;
import com.example.nln.nedroid.R;

import java.util.List;

/**
 * Created by NLN on 4/22/2017.
 */

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.MyViewHolder> {

    private FragmentThree mContext;
    private List<Question> albumList;
    private ItemClickListener clickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView questionDescription;
        public ImageView profile;
        public TextView name;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.textView_name);
            profile = (ImageView) view.findViewById(R.id.image_profile);
            questionDescription = (TextView) view.findViewById(R.id.textView_question);
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


    public QuestionAdapter(FragmentThree mContext, List<Question> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_three_forum, parent, false);

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
        Question album = albumList.get(position);
        holder.name.setText(album.getName());
        holder.questionDescription.setText(album.getQuestion());
        // loading album cover using Glide library

        System.out.println(album.getPhotourl());
        Glide.with(holder.profile.getContext()).load(album.getPhotourl()).into(holder.profile);
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}

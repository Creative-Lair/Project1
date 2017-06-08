package com.example.nln.nedroid.Attendance;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.nln.nedroid.R;

import java.util.List;

/**
 * Created by NLN on 4/22/2017.
 */

public class ABAdaptor extends RecyclerView.Adapter<ABAdaptor.MyViewHolder> {

    private List<AButton> albumList;
    private ItemClickListener clickListener;
    public Context mContext;
    String buttonSelected;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public RadioGroup rg_attnd;
        public RadioButton rb_present, rb_absent;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.TextView_Name);
            rg_attnd = (RadioGroup) view.findViewById(R.id.RadioGroup_Attnd);
            rb_absent = (RadioButton) view.findViewById(R.id.RadioButton_Absent);
            rb_present = (RadioButton) view.findViewById(R.id.RadioButton_Present);
        }

    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }


    public ABAdaptor(Attendance1 mContext, List<AButton> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_attendance_buttons, parent, false);

        final MyViewHolder MyViewHolder = new MyViewHolder(itemView);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final AButton aButton = albumList.get(position);

        holder.name.setText(aButton.getName());
        holder.rg_attnd.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i){
                    case R.id.RadioButton_Absent:
                        buttonSelected = "Absent";
                        aButton.setPresent(false);
                        holder.rb_present.setChecked(false);
                        break;
                    case R.id.RadioButton_Present:
                        buttonSelected = "Present";
                        aButton.setPresent(true);
                        holder.rb_absent.setChecked(false);
                        break;
                    default:

                }
                Toast.makeText(mContext, "Selected.. " + buttonSelected, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
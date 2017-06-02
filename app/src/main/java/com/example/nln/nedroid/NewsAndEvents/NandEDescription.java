package com.example.nln.nedroid.NewsAndEvents;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nln.nedroid.R;

public class NandEDescription extends AppCompatActivity {

    ImageView image2;
    ImageView image1,image3,image4,image5;
    TextView EventDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nand_description);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("News and Events");

        EventDescription = (TextView) findViewById(R.id.textView_EventDescription);
        EventDescription.setText(
                "Full Description of the selected Event.. " + "Full Description of the selected Event" +
                "Full Description of the selected Event.. " + "Full Description of the selected Event" +
                "Full Description of the selected Event.. " + "Full Description of the selected Event" +
                "Full Description of the selected Event.. " + "Full Description of the selected Event" +
                "Full Description of the selected Event.. " + "Full Description of the selected Event" +
                "Full Description of the selected Event.. " + "Full Description of the selected Event"
        );

        image2 = (ImageView)findViewById(R.id.image2);
        image1 = (ImageView)findViewById(R.id.image1);
        image3 = (ImageView)findViewById(R.id.image3);
        image4 = (ImageView)findViewById(R.id.image4);
        image5 = (ImageView)findViewById(R.id.image5);
    }

    public void onClick(View view) {

        if( view == image2) {
            image1.setImageResource(R.drawable.fr_five);
        } else if( view == image3) {
            image1.setImageResource(R.drawable.fr_one);
        } else if( view == image4) {
            image1.setImageResource(R.drawable.fr_four);
        } else if( view == image5) {
            image1.setImageResource(R.drawable.fr_zero);
        }

    }
}

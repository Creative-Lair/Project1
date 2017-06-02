package com.example.nln.nedroid.NewsAndEvents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.nln.nedroid.R;

public class NandECreate extends AppCompatActivity {

    ImageButton imageadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nand_ecreate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("News and Events");

        imageadd = (ImageButton) findViewById(R.id.addimage);
    }

    public void onClick (View view){
        if( view == imageadd){
            Toast pass = Toast.makeText(this, "Add Image...", Toast.LENGTH_SHORT);
            pass.show();
        }
        Intent i = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        final int ACTIVITY_SELECT_IMAGE = 1234;
        startActivityForResult(i, ACTIVITY_SELECT_IMAGE);
    }
}

package com.example.nln.nedroid.NewsAndEvents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.nln.nedroid.R;

public class NandECreate extends AppCompatActivity {

    ImageButton imageadd;

    private EditText title, description;
    final int ACTIVITY_SELECT_IMAGE = 1234;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nand_ecreate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("News and Events");

        imageadd = (ImageButton) findViewById(R.id.addimage);
        title = (EditText) findViewById(R.id.title);
        description = (EditText) findViewById(R.id.description);

    }

    public void onClick (View view){

        int id = view.getId();

        switch (id){
            case R.id.addimage:

                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(i, ACTIVITY_SELECT_IMAGE);

                break;
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}

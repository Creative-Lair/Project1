package com.example.nln.nedroid.NewsAndEvents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.nln.nedroid.Login;
import com.example.nln.nedroid.R;
import com.example.nln.nedroid.Session;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class NandECreate extends AppCompatActivity implements View.OnClickListener{

    final int ACTIVITY_SELECT_IMAGE = 1234;
    Session session;
    ArrayList<String> photosUri;
    private ImageButton imageadd;
    private Button create;
    private EditText title, description;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference newsRef;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nand_ecreate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("News and Events");

        photosUri = new ArrayList<>();
        session = new Session(this);
        if(!session.getLogin()){
            Intent i = new Intent(this, Login.class);
            startActivity(i);
            finish();
        }

        firebaseDatabase = FirebaseDatabase.getInstance();
        newsRef = firebaseDatabase.getReference().child("News");
        firebaseStorage = FirebaseStorage.getInstance();

        storageReference = firebaseStorage.getReference().child("News_photos");

        imageadd = (ImageButton) findViewById(R.id.addimage);
        title = (EditText) findViewById(R.id.title);
        description = (EditText) findViewById(R.id.description);
        create = (Button) findViewById(R.id.create);
        create.setEnabled(false);
        imageadd.setOnClickListener(this);
        create.setOnClickListener(this);

    }

    public void onClick (View view){

        int id = view.getId();

        switch (id){
            case R.id.addimage:

                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                i.setType("image/*");
                startActivityForResult(i, ACTIVITY_SELECT_IMAGE);

                break;

            case R.id.create:

                String photoUri;
                String t;
                String d;

                String username = session.getUsername();
                String photoUser = session.getPhoto();
                boolean verify = false;

                if(photosUri.size() == 0){
                    Toast.makeText(this, "Please Select Atleast one image for the event", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    photoUri = photosUri.get(0);
                }

                if(title.getText().toString().trim().equals("")){
                    Toast.makeText(this, "Please set the Title of the event", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    t = title.getText().toString().trim();
                }

                if(description.getText().toString().trim().equals("")){
                    Toast.makeText(this, "Please set the Description of the event", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    d = description.getText().toString().trim();
                }


                News news = new News(t, d, username, photoUser, photoUri, verify, photosUri);

                DatabaseReference ref = newsRef.push();
                ref.setValue(news);
                ref.child("timestamp").setValue(ServerValue.TIMESTAMP);

                finish();

                break;

        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ACTIVITY_SELECT_IMAGE){
            if(resultCode == RESULT_OK){
                Uri imageUri = data.getData();
                final StorageReference photoRef = storageReference.child((imageUri.getLastPathSegment()));

                photoRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Uri downloadUri = taskSnapshot.getDownloadUrl();
                        photosUri.add(downloadUri.toString());
                        create.setEnabled(true);
                    }
                });
            }
        }
    }
}

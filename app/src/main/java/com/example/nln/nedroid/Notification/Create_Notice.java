package com.example.nln.nedroid.Notification;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nln.nedroid.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Create_Notice extends AppCompatActivity {

    private static final int ACTIVITY_SELECT_IMAGE = 100;
    private EditText title, description;
    private ImageButton imgBtn;
    private Button btn;
    private ImageView img;
    private String imageUrl;
    private FirebaseDatabase firebasedatebase;
    private DatabaseReference dataRef;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private Uri imageUri;
    private Notice notice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__notice);

        firebasedatebase = FirebaseDatabase.getInstance();
        dataRef = firebasedatebase.getReference().child("Notice");

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference().child("Notice_pics");

        title = (EditText) findViewById(R.id.title);
        description = (EditText) findViewById(R.id.description);

        imgBtn = (ImageButton) findViewById(R.id.imgbtn);
        btn = (Button) findViewById(R.id.btn);

        img = (ImageView) findViewById(R.id.image);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (title.getText().toString().trim().length() < 1) {
                    Toast.makeText(Create_Notice.this, "Please set the title of the Notice", Toast.LENGTH_SHORT).show();
                    return;
                } else if (description.getText().toString().trim().length() < 1) {
                    Toast.makeText(Create_Notice.this, "Please set the description of the Notice", Toast.LENGTH_SHORT).show();
                    return;
                }

                Calendar c = Calendar.getInstance();

                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                final String formattedDate = df.format(c.getTime());



                if(imageUri!=null) {

                    final StorageReference photoRef = storageReference.child((imageUri.getLastPathSegment()));

                    photoRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadUri = taskSnapshot.getDownloadUrl();
                            imageUrl = downloadUri.toString();
                            Toast.makeText(Create_Notice.this, "Image Uploaded", Toast.LENGTH_SHORT).show();
                            notice = new Notice(formattedDate,
                                    title.getText().toString().trim(),
                                    description.getText().toString().trim(),
                                    imageUrl);

                            dataRef.push().setValue(notice);
                            finish();
                        }
                    });
                }

                else {
                    notice = new Notice(formattedDate,
                            title.getText().toString().trim(),
                            description.getText().toString().trim(),
                            "https://firebasestorage.googleapis.com/v0/b/nedroid-3bcc1.appspot.com/o/19265206_1596287747062182_624483868_n.jpg?alt=media&token=176a9473-de1e-473f-a559-14efa66b5220");
                    dataRef.push().setValue(notice);
                    finish();
                }

            }
        });

        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                i.setType("image/*");
                startActivityForResult(i, ACTIVITY_SELECT_IMAGE);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ACTIVITY_SELECT_IMAGE) {
            if (resultCode == RESULT_OK) {
                imageUri = data.getData();

                Glide.with(img.getContext())
                        .load(imageUri.toString())
                        .into(img);

            }
        }
    }

}

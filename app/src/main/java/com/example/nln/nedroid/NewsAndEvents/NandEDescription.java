package com.example.nln.nedroid.NewsAndEvents;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nln.nedroid.R;
import com.example.nln.nedroid.Session;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class NandEDescription extends AppCompatActivity {

    private ImageView image1, pic1;
    private TextView EventDescription, name1, title;



    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference newsRef;

    private Session session;

    private LinearLayout layout;
    private LinearLayout.LayoutParams layoutParams;
    private ActionBar actionbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nand_description);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionbar = getSupportActionBar();
        actionbar.setHomeButtonEnabled(true);
        actionbar.setDisplayHomeAsUpEnabled(true);



        EventDescription = (TextView) findViewById(R.id.textView_EventDescription);

        layout = (LinearLayout) findViewById(R.id.imageContainer);
        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        image1 = (ImageView) findViewById(R.id.image1);
        pic1 = (ImageView) findViewById(R.id.pic1);
        name1 = (TextView) findViewById(R.id.name1);
        title = (TextView) findViewById(R.id.title);

        session = new Session(this);

        firebaseDatabase = FirebaseDatabase.getInstance();
        newsRef = firebaseDatabase.getReference().child("News");

        String id = session.getNewsId();

        newsRef.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                News n = dataSnapshot.getValue(News.class);
                setTitle(n.getTitle());
                title.setText(n.getTitle());
                Glide.with(image1.getContext())
                        .load(n.getCoverPhoto())
                        .into(image1);
                Glide.with(pic1.getContext())
                        .load(n.getPhoto_user())
                        .into(pic1);
                name1.setText(n.getUsername());

                loadImages(n.getPhotos());

                EventDescription.setText(n.getDescription());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public void loadImages(ArrayList<String> photos){
        for (int i = 1; i < photos.size(); i++) {
            layoutParams.setMargins(10, 10, 10, 10);
            ImageView imageView = new ImageView(this);
            Glide.with(imageView.getContext())
                    .load(photos.get(i))
                    .into(imageView);
            imageView.setLayoutParams(layoutParams);
            imageView.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
            imageView.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

            layout.addView(imageView);

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                finish();
                break;
        }



        return true;
    }
}

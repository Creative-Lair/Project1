package com.example.nln.nedroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nln.nedroid.Helper.Student;
import com.example.nln.nedroid.Helper.Teacher;
import com.example.nln.nedroid.PageOne.FirstNav;
import com.example.nln.nedroid.PageOne.Teacher_FirstNav;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;
import org.xml.sax.helpers.LocatorImpl;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private String username, password; // for shared pref
    private Button ok;
    private EditText editTextUsername, editTextPassword;
    private CheckBox saveLoginCheckBox;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference rootRef;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        firebaseDatabase = FirebaseDatabase.getInstance();

        session = new Session(this);

        ok = (Button) findViewById(R.id.button_login);
        editTextUsername = (EditText) findViewById(R.id.editText_name_login);
        editTextPassword = (EditText) findViewById(R.id.editText_pass_login);
        saveLoginCheckBox = (CheckBox) findViewById(R.id.checkBox);

        setSupportActionBar(toolbar);
        setTitle("Login");
        ok.setOnClickListener(this);

        if(session.getLogin()){
            if(session.getUserId().charAt(0)=='s'){
                Intent i = new Intent(this, FirstNav.class);
                startActivity(i);
                finish();

            }
        }

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch(id){
            case R.id.button_login:
                username = editTextUsername.getText().toString().toLowerCase();
                password = editTextPassword.getText().toString();


                //Student
                if(username.charAt(0)=='s'){
                    rootRef = firebaseDatabase.getReference().child("Students");
                    rootRef.child(username).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            //Toast.makeText(Login.this, dataSnapshot.getValue(), Toast.LENGTH_SHORT).show();
                            Student student = dataSnapshot.getValue(Student.class);
                            System.out.println(student);
                            if(password.equals(student.getPassword())){
                                session.setLogin(true);
                                session.setUserId(username);
                                session.setUsername(student.getName());
                                session.setSemester(student.getSemester());
                                session.setPhoto(student.getPhotourl());
                                session.setCourses(student.getCourses());
                                for (int i=0;i< student.getCourses().size();i++){
                                    System.out.println(student.getCourses().get(i));
                                }
                                Intent i = new Intent(Login.this, FirstNav.class);
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(Login.this, "Wrong Id or Password!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                } else if (username.charAt(0)=='t'){
                    rootRef = firebaseDatabase.getReference().child("Teachers");
                    rootRef.child(username).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Teacher teacher = dataSnapshot.getValue(Teacher.class);
                            if(password.equals(teacher.getPassword())){
                                session.setLogin(true);
                                session.setUserId(username);
                                Intent i = new Intent(Login.this, Teacher_FirstNav.class);
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(Login.this, "Wrong Id or Password!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }

                break;
        }
    }

}

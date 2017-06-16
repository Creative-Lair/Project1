package com.example.nln.nedroid;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.nln.nedroid.Attendance.DetailAdaptor;
import com.example.nln.nedroid.Attendance.Lecture;
import com.example.nln.nedroid.Attendance.Lecture_detail;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Nav_AttendThree_pdf extends AppCompatActivity {

    AppBarLayout appBarLayout;
    private ActionBar actionBar;
    private String section, code;
    private Session session;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference lectureRef;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Lecture_detail> details;
    private DetailAdaptor adapter;
    private ChildEventListener childEventListener;
    private Button btn;
    private ArrayList<Lecture_detail> newList;

    public static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState);
    }

    public static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(extStorageState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav__attend_three_pdf);
        recyclerView = (RecyclerView) findViewById(R.id.list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Lecture Details");
        session = new Session(this);
        if (!session.getLogin()) {
            Intent i = new Intent(this, Login.class);
            startActivity(i);
            finish();
        }
        btn = (Button) findViewById(R.id.btn);
        newList = new ArrayList<>();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        details = new ArrayList<>();

        adapter = new DetailAdaptor(this, details);

        mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        section = session.getSection();
        code = session.getSubject();

        firebaseDatabase = FirebaseDatabase.getInstance();
        lectureRef = firebaseDatabase.getReference().child("Classes");

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Lecture lecture = dataSnapshot.getValue(Lecture.class);
                Lecture_detail detail = new Lecture_detail(lecture.getTimeslot(), lecture.getDate(), lecture.getLectureTopic());
                details.add(detail);
                newList.add(detail);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        lectureRef.child(code).child(section).addChildEventListener(childEventListener);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!Settings.System.canWrite(getApplication())) {
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE}, 2909);
                    } else {
                        // continue with your code
                        saveFile();
                    }
                } else {
                    // continue with your code
                    saveFile();
                }

            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();

        details.clear();
        if (childEventListener != null) {
            lectureRef.removeEventListener(childEventListener);
            childEventListener = null;
        }


    }

    @Override
    protected void onResume() {
        super.onResume();

        if (childEventListener == null) {
            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Lecture lecture = dataSnapshot.getValue(Lecture.class);
                    Lecture_detail detail = new Lecture_detail(lecture.getTimeslot(), lecture.getDate(), lecture.getLectureTopic());
                    details.add(detail);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            lectureRef.child(code).child(section).addChildEventListener(childEventListener);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                break;
        }


        return true;
    }

    public void saveFile() {
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            Log.e("EXCEL SHEET", "Storage not available or read only");
            return;
        }

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(code + " " + section);

        Map<String, Object[]> data = new HashMap<>();
        int i = 1;
        Row r = sheet.createRow(0);
        Cell c = r.createCell(0);
        c.setCellValue("Date");
        c = r.createCell(1);
        c.setCellValue("Timeslot");
        c = r.createCell(2);
        c.setCellValue("Topic");
        System.out.println(newList.size());
        for (Lecture_detail detail : newList) {
            data.put("" + i, new Object[]{detail.getDate(), detail.getTime(), detail.getTopic()});
            i++;

            System.out.println(detail.getTopic());
        }
        Set<String> keyset = data.keySet();
        int rownum = 1;
        for (String key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                System.out.println(obj);
                if (obj instanceof Date)
                    cell.setCellValue((Date) obj);
                else if (obj instanceof Boolean)
                    cell.setCellValue((Boolean) obj);
                else if (obj instanceof String)
                    cell.setCellValue((String) obj);
                else if (obj instanceof Double)
                    cell.setCellValue((Double) obj);
            }
        }

        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "Lecture_details_" + code + "_" + section + ".xls");
        FileOutputStream os = null;

        try {
            os = new FileOutputStream(file);
            workbook.write(os);
            Toast.makeText(this, "File Created", Toast.LENGTH_SHORT).show();
            Log.w("FileUtils", "Writing file" + file);
        } catch (IOException e) {
            Log.w("FileUtils", "Error writing " + file, e);
        } catch (Exception e) {
            Log.w("FileUtils", "Failed to save file", e);
        } finally {
            try {
                if (null != os)
                    os.close();
            } catch (Exception ex) {
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 2909: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("Permission", "Granted");
                    saveFile();
                } else {
                    Log.e("Permission", "Denied");
                    Toast.makeText(this, "Can't save the file.. write permission not granted!!", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}

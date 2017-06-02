package com.example.nln.nedroid.PageTwo;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nln.nedroid.R;

import static com.example.nln.nedroid.R.layout.activity_listview_f3;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentBook extends Fragment {

    View v;
    FragmentManager fragmentManager = getFragmentManager();

    String[] TextBooks = {"Text_Book 1","Text_Book 2","Text_Book 3","Text_Book 4"};
    String[] RefBooks = {"Ref_Book 1","Ref_Book 2","Ref_Book 3","Ref_Book 4","Ref_Book 5","Ref_Book 6","Ref_Book 7","Ref_Book 8"};


    public FragmentBook() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_book, container, false);

        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), activity_listview_f3, TextBooks);
        ListView listView = (ListView) v.findViewById(R.id.listView1);
        listView.setAdapter(adapter);

        ArrayAdapter adapter1 = new ArrayAdapter<String>(getActivity(), activity_listview_f3, RefBooks);
        ListView listView2 = (ListView) v.findViewById(R.id.listView2);
        listView2.setAdapter(adapter1);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3){
                // TODO Auto-generated method stub

                String value = (String)arg0.getItemAtPosition(arg2);
//                Toast.makeText(getActivity(), value , Toast.LENGTH_SHORT).show();
//                ----------This is to OPEN PDF FILES ONLY i.e. Interacting with other apps as well---------
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                startActivity(intent);
            }
        });

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3){
                // TODO Auto-generated method stub

                String value = (String)arg0.getItemAtPosition(arg2);
                Toast.makeText(getActivity(), value , Toast.LENGTH_LONG).show();
//                ----------This is to Drive from CHROME i.e. Interacting with other apps as well---------
                Uri webpage = Uri.parse("https://drive.google.com/drive/my-drive");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(webIntent);
            }
        });

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(isRemoving()){
            // onBackPressed()
        }
    }


}

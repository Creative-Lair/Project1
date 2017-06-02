package com.example.nln.nedroid.PageOne;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nln.nedroid.PageTwo.SsecondNav;
import com.example.nln.nedroid.R;

import static com.example.nln.nedroid.R.layout.listview_pageone_f2;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwo extends Fragment {

    View v;
    ListView listView;
    ArrayAdapter adapter;
    String[] Subject = {
            "[CODE: xxxx]   Subject 1",
            "[CODE: xxxx]   Subject 2",
            "[CODE: xxxx]   Subject 3",
            "[CODE: xxxx]   Subject 4",
            "[CODE: xxxx]   Subject 5",
            "[CODE: xxxx]   Subject 6"
    };

    public FragmentTwo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_fragment_two2, container, false);

        adapter = new ArrayAdapter<String>(getActivity(), listview_pageone_f2, Subject);
        final ListView listView = (ListView) v.findViewById(R.id.ListView_FragTwoSubject);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3){
                // TODO Auto-generated method stub
//                String value = (String)arg0.getItemAtPosition(arg2);
                int position = arg2;
                String selectedFromList = (String) listView.getItemAtPosition(position);
                Toast.makeText(getActivity(), "Clicked on \"" + selectedFromList + "\"", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), SsecondNav.class);
                startActivity(i);
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

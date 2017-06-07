package com.example.nln.nedroid.PageOne;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nln.nedroid.Login;
import com.example.nln.nedroid.Nav_AttendOne;
import com.example.nln.nedroid.Nav_AttendThree;
import com.example.nln.nedroid.Nav_AttendTwo;
import com.example.nln.nedroid.R;
import com.example.nln.nedroid.Session;


/**
 * A simple {@link Fragment} subclass.
 */
public class Teacher_FragmentOne extends android.support.v4.app.Fragment {

    private View inflateView;
    private CardView cv_create,cv_LectrureCount,cv_LectureDetail;
    private Session session;

    public Teacher_FragmentOne() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflateView=inflater.inflate(R.layout.fragment_two, container, false);
        session = new Session(getContext());

        if(!session.getLogin()){
            Intent i = new Intent(getContext(), Login.class);
            startActivity(i);
            getActivity().finish();
        }

        session = new Session(getContext());

        if(!session.getLogin()){
            Intent i = new Intent(getContext(), Login.class);
            startActivity(i);
            getActivity().finish();
        }

        cv_create = (CardView) inflateView.findViewById(R.id.cardView_CreateNew);
        cv_LectrureCount = (CardView) inflateView.findViewById(R.id.cardView_LectureCount);
        cv_LectureDetail = (CardView) inflateView.findViewById(R.id.cardView_LectureDetail);

        cv_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Button Clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), Nav_AttendOne.class);
                startActivity(i);
            }
        });
        cv_LectrureCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Button Clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), Nav_AttendTwo.class);
                startActivity(i);
            }
        });
        cv_LectureDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Button Clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), Nav_AttendThree.class);
                startActivity(i);
            }
        });



        return inflateView;
    }

}

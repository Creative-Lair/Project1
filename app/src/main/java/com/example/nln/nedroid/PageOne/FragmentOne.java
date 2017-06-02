package com.example.nln.nedroid.PageOne;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nln.nedroid.NewsAndEvents.ItemClickListener;
import com.example.nln.nedroid.NewsAndEvents.NandECreate;
import com.example.nln.nedroid.NewsAndEvents.NandEDescription;
import com.example.nln.nedroid.NewsAndEvents.News;
import com.example.nln.nedroid.NewsAndEvents.NewsAdapter;
import com.example.nln.nedroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends android.support.v4.app.Fragment implements ItemClickListener {

    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private List<News> albumList;
    private RecyclerView.LayoutManager mLayoutManager;

    public FragmentOne() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Converting dp to pixel
     */

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        // Inflate the layout for this fragment
//        View  v=  inflater.inflate(R.layout.fragment_fragment_one, container, true);
        View v = inflater.inflate(R.layout.fragment_fragment_one2, container, false);

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast pass = Toast.makeText(getActivity(), "Clicked on Fab...", Toast.LENGTH_SHORT);
                pass.show();
                Intent i = new Intent(getActivity(), NandECreate.class);
                startActivity(i);
            }
        });

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);

        albumList = new ArrayList<>();
        adapter = new NewsAdapter(this, albumList);

        mLayoutManager = new GridLayoutManager(getActivity(), 1);
//        mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        adapter.setClickListener(this);

        prepareAlbums();

        return v;
    }


    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {
        int[] covers = new int[]{

                // ADD PICTURES IN CARD
                R.drawable.fr_zero,
                R.drawable.fr_one,
                R.drawable.fr_two,
                R.drawable.fr_three,
                R.drawable.fr_four,
                R.drawable.fr_five,
                R.drawable.fr_six,
        };

        int[] profile = new int[]{

                // ADD PICTURES IN CARD
                R.drawable.fr_zero,
                R.drawable.fr_one,
                R.drawable.fr_two,
                R.drawable.fr_three,
                R.drawable.fr_four,
                R.drawable.fr_five,
                R.drawable.fr_six,
        };


        News a = new News(profile[5], "Name ",
                "Short Description of Event, Limited to THREE LINES Press to see further Details         ...  Short Description of Event...  Short Description of Event...  Short Description of Event...  ",
                covers[6]);
        albumList.add(a);

        a = new News(profile[2], "Name ",
                "Short Description of Event, Limited to THREE LINES Press to see further Details         ...  Short Description of Event...  Short Description of Event...  Short Description of Event...  Short Description of Event...  Short Description of Event...  ",
                covers[0]);
        albumList.add(a);

        a = new News(profile[3], "Name ",
                "Short Description of Event, Limited to THREE LINES Press to see further Details         ...  Short Description of Event...  Short Description of Event...  Short Description of Event...  ",
                covers[1]);
        albumList.add(a);

        a = new News(profile[4], "Name ",
                "Short Description of Event, Limited to THREE LINES Press to see further Details         ", covers[2]);
        albumList.add(a);

        a = new News(profile[5], "Name ",
                "Short Description of Event...  Short Description of Event...  Short Description of Event...  Short Description of Event...  Short Description of Event...  Short Description of Event...  " +
                "Descrition", covers[3]);
        albumList.add(a);


        a = new News(profile[6], "Name ",
                "Short Description of Event...  Short Description of Event...  Short Description of Event...  Short Description of Event...  Short Description of Event...  Short Description of Event...  ",
                covers[5]);
        albumList.add(a);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(isRemoving()){
            // onBackPressed()
        }
    }


    @Override
    public void onClick(View view, int position) {
//        final News city = albumList.get(position);
//        Log.i("hello", city.getName());
//        Toast.makeText(getActivity(), city.getName(), Toast.LENGTH_SHORT).show();// To show the text on toast

        Intent i = new Intent(getActivity(), NandEDescription.class);
        startActivity(i);

    }
}
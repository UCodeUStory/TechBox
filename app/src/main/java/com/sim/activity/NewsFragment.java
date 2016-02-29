package com.sim.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {
    public static final String NEWS_KEY="titles";
    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // View view= inflater.inflate(R.layout.fragment_news2, container, false);
      //  TextView textView=(TextView)view.findViewById(R.id.titles);
       // String title=getArguments().getString(NEWS_KEY);
       // textView.setText(title);
        return  null;
    }


}

package com.sim.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.test2.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener {



    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_one,container,false);
      //  view.findViewById(R.id.back).setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View v) {
       // if(v.getId()==R.id.back){

      //  }
    }
}

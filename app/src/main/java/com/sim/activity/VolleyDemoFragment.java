package com.sim.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.test2.R;
import com.sim.volley.activity.RequestActivity;
import com.sim.volley.fragment.ImageLoaderFragment;
import com.sim.volley.fragment.ImageRequestFragment;
import com.sim.volley.fragment.JsonRequestFragment;
import com.sim.volley.fragment.NetworkImageViewFragment;
import com.sim.volley.fragment.PostRequestFragment;
import com.sim.volley.fragment.StringRequestFragment;
import com.sim.volley.fragment.XmlRequestFragment;
import com.sim.volley.util.Constants;


/**
 * A simple {@link Fragment} subclass.
 */
public class VolleyDemoFragment extends Fragment implements View.OnClickListener {


    public VolleyDemoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.volley_demo_fragment,container,false);
        initView(rootView);
        return rootView;
    }

    private void initView(View v) {
        // String请求
        v.findViewById(R.id.btn_string_request).setOnClickListener(this);
        // Json请求
        v.findViewById(R.id.btn_json_request).setOnClickListener(this);
        // Image请求
        v.findViewById(R.id.btn_image_request).setOnClickListener(this);
        // ImageLoader
        v.findViewById(R.id.btn_image_loader).setOnClickListener(this);
        // NetworkImageView
        v.findViewById(R.id.btn_network_image_view).setOnClickListener(this);
        // Xml请求
        v.findViewById(R.id.btn_xml_request).setOnClickListener(this);
        // post请求
        v.findViewById(R.id.btn_post_request).setOnClickListener(this);



    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), RequestActivity.class);
        switch (v.getId()) {
            case R.id.btn_string_request:
                intent.putExtra(Constants.Extra.FRAGMENT_INDEX, StringRequestFragment.INDEX);
                break;
            case R.id.btn_json_request:
                intent.putExtra(Constants.Extra.FRAGMENT_INDEX, JsonRequestFragment.INDEX);
                break;
            case R.id.btn_image_request:
                intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImageRequestFragment.INDEX);
                break;
            case R.id.btn_image_loader:
                intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImageLoaderFragment.INDEX);
                break;
            case R.id.btn_network_image_view:
                intent.putExtra(Constants.Extra.FRAGMENT_INDEX, NetworkImageViewFragment.INDEX);
                break;
            case R.id.btn_xml_request:
                intent.putExtra(Constants.Extra.FRAGMENT_INDEX, XmlRequestFragment.INDEX);
                break;
            case R.id.btn_post_request:
                intent.putExtra(Constants.Extra.FRAGMENT_INDEX, PostRequestFragment.INDEX);
                break;
            default:
                break;
        }
        startActivity(intent);
    }
}

package com.sim.volley.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.administrator.test2.R;
import com.sim.volley.adapter.ImageBaseAdapter;
import com.sim.volley.adapter.ImageRequestAdapter;
import com.sim.volley.util.Constants;


public class ImageRequestFragment extends Fragment {
	public static final int INDEX = 21;

	private String[] imageUrlArray = Constants.IMAGE_URLS;

	private GridView gvCar;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fr_image_request, container,false);

		gvCar = (GridView) view.findViewById(R.id.gv_car);
		ImageBaseAdapter adapter = new ImageRequestAdapter(getActivity(),imageUrlArray);
		gvCar.setAdapter(adapter);

		return view;
	}
}

package com.ustory.techbox.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.administrator.test2.R;
import com.ustory.techbox.adapter.ImageBaseAdapter;
import com.ustory.techbox.adapter.NetworkImageViewAdapter;
import com.ustory.techbox.utils.Constants;


public class NetworkImageViewFragment extends Fragment{
	public static final int INDEX=23;
	
	private String[] imageUrlArray = Constants.IMAGE_URLS;

	private GridView gvCar;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fr_image_request, container,false);

		gvCar = (GridView) view.findViewById(R.id.gv_car);
		ImageBaseAdapter adapter = new NetworkImageViewAdapter(getActivity(),imageUrlArray);
		gvCar.setAdapter(adapter);

		return view;
	}
}

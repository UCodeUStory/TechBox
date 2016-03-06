package com.ustory.techbox.views;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.test2.R;
import com.ustory.techbox.adapter.VolleyAdapter;
import com.ustory.techbox.bean.VolleyTech;
import com.ustory.techbox.core.BaseFragment;
import com.ustory.techbox.iviews.VolleyView;
import com.ustory.techbox.presenter.VolleyTechPresenter;
import com.ustory.techbox.test.DataProvider;
import com.ustory.techbox.utils.Constants;
import com.ustory.techbox.utils.L;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class VolleyDemoFragment extends BaseFragment implements VolleyView,View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView listView;
    private VolleyAdapter adapter;
    private List<VolleyTech> mVolleyTechList = new ArrayList<VolleyTech>();
    private VolleyTechPresenter presenter;
    public VolleyDemoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setRootView(R.layout.volley_demo_fragment);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initView(View rootView) {
         listView = $(R.id.volley_list);

    }

    @Override
    protected void initListener() {
         listView.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
         adapter = new VolleyAdapter(getActivity(),mVolleyTechList,R.layout.demo_item);
         listView.setAdapter(adapter);
         this.presenter = new VolleyTechPresenter();
         this.presenter.attachView(this);
         presenter.findAllVolleyTech();
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void querySuccess(List<VolleyTech> volleyTeches) {
         adapter.setDatas(volleyTeches);
         adapter.notifyDataSetChanged();
         L.i("volley","volleyQuerySuccess");
    }

    @Override
    public void delete(VolleyTech volleyTech) {

    }

    @Override
    public boolean add(VolleyTech volleyTech) {
        return false;
    }

    @Override
    public void update(VolleyTech volleyTech) {

    }

    @Override
    public void onFailure(String failedMessage) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.presenter.detachView();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        VolleyTech volleyTech = (VolleyTech)parent.getItemAtPosition(position);
        int volleyTechId = volleyTech.getId();
        Intent intent = new Intent(getActivity(), RequestActivity.class);
        switch (volleyTechId) {
            case DataProvider.STRING_REQUEST:
                intent.putExtra(Constants.Extra.FRAGMENT_INDEX, StringRequestFragment.INDEX);
                break;
            case DataProvider.JSON_REQUEST:
                intent.putExtra(Constants.Extra.FRAGMENT_INDEX, JsonRequestFragment.INDEX);
                break;
            case DataProvider.IMAGE_REQUEST:
                intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImageRequestFragment.INDEX);
                break;
            case DataProvider.IMAGE_LOADER:
                intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImageLoaderFragment.INDEX);
                break;
            case DataProvider.NETWORK_IMAGEVIEW:
                intent.putExtra(Constants.Extra.FRAGMENT_INDEX, NetworkImageViewFragment.INDEX);
                break;
            case DataProvider.XML_REQUEST:
                intent.putExtra(Constants.Extra.FRAGMENT_INDEX, XmlRequestFragment.INDEX);
                break;
            case DataProvider.POST_REQUEST:
                intent.putExtra(Constants.Extra.FRAGMENT_INDEX, PostRequestFragment.INDEX);
                break;
            default:
                break;
        }
        startActivity(intent);
    }
}

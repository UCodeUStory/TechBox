package com.ustory.techbox.views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.test2.R;
import com.ustory.techbox.adapter.NewTechRecyclerAdapter;
import com.ustory.techbox.adapter.RecycleViewDivider;
import com.ustory.techbox.bean.ITTech;
import com.ustory.techbox.iviews.ITTechView;
import com.ustory.techbox.presenter.NewTechPresenter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewMessageFragment extends Fragment implements ITTechView, View.OnClickListener {

    private RecyclerView mRecyclerView;
    private NewTechRecyclerAdapter mAdapter;
    //private List<String> mDatas;
    private List<ITTech> mITTechList = new ArrayList<ITTech>();
    private NewTechPresenter presenter;
    public NewMessageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lasted_new_activities_fragment,container,false);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.id_recyclerview);


        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerview);
        mAdapter = new NewTechRecyclerAdapter(getActivity(), mITTechList);

       // mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
          //     StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRecyclerView.setAdapter(mAdapter);
        //添加分割线
        // mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));
        //默认分割线
        mRecyclerView.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.HORIZONTAL));
        //设置某图片为分割线
        //mRecyclerView.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.HORIZONTAL, R.drawable.divider_line));
        // 设置item动画
        //mRecyclerView.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.HORIZONTAL, 10, getResources().getColor(R.color.purple)));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        initData();
        initEvent();
        return view;

    }
    private void initEvent()
    {
        mAdapter.setOnItemClickLitener(new NewTechRecyclerAdapter.OnItemClickLitener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                Toast.makeText(getActivity(), position + " click",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position)
            {
                Toast.makeText(getActivity(), position + " long click",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void initData()
    {
        this.presenter = new NewTechPresenter();
        this.presenter.attachView(this);
        presenter.findAllItTech();
        /*mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++)
        {
            mDatas.add("" + (char) i);
        }*/
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.id_action_add:
                mAdapter.addData(1);
                break;
            case R.id.id_action_delete:
                mAdapter.removeData(1);
                break;
            case R.id.id_action_gridview:
                mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
                break;
            case R.id.id_action_listview:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                break;
            case R.id.id_action_horizontalGridView:
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
                        StaggeredGridLayoutManager.HORIZONTAL));
                break;

            case R.id.id_action_staggeredgridview:
               // Intent intent = new Intent(this , StaggeredGridLayoutActivity.class);
               // startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void querySuccess(List<ITTech> itTech) {
        mAdapter.setDataList(itTech);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void error() {

    }

    @Override
    public void onFailure(String failedMessage) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.presenter.detachView();
    }
}

package com.ustory.techbox.views;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.test2.R;
import com.ustory.techbox.adapter.BooksAdapter;
import com.ustory.techbox.adapter.SpacesItemDecoration;
import com.ustory.techbox.bean.Book;
import com.ustory.techbox.core.BaseFragment;
import com.ustory.techbox.iviews.IBookView;
import com.ustory.techbox.presenter.BookPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/6
 */
public class RecommendBooksFragment extends BaseFragment implements IBookView {

    private RecyclerView mRecyclerView;
    private BooksAdapter adapter;
    private List<Book> books = new ArrayList<Book>();
    private BookPresenter mBookPresenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setRootView(R.layout.recommenentd_books_fragment);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initView(View rootView) {
        mRecyclerView= $(R.id.recycler);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        BooksAdapter.RecycleItemClickListener itemClickListener=new BooksAdapter.RecycleItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Log.e("position","="+position);
//                Toast.makeText(MainActivity.this, books.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                //Intent intent=new Intent();
               // intent.setClass(getActivity(),BookDetailActivity.class);
               // startActivity(intent);
            }
        };
        adapter = new BooksAdapter(books,itemClickListener);
        mRecyclerView.setAdapter(adapter);
        SpacesItemDecoration decoration=new SpacesItemDecoration(16);
        mRecyclerView.addItemDecoration(decoration);


    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        this.mBookPresenter = new BookPresenter();
        this.mBookPresenter.attachView(this);
        this.mBookPresenter.loadAllBooks();
    }

    @Override
    public void querySuccess(List<Book> books) {
        adapter.setBooks(books);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void delete(Book book) {

    }

    @Override
    public boolean add(Book book) {
        return false;
    }

    @Override
    public void update(Book book) {

    }

    @Override
    public void onFailure(String failedMessage) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mBookPresenter.detachView();
    }
}

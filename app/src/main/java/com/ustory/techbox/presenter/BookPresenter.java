package com.ustory.techbox.presenter;

import android.content.Context;

import com.ustory.techbox.bean.Book;
import com.ustory.techbox.core.mvp.BasePresenter;
import com.ustory.techbox.core.mvp.MvpView;
import com.ustory.techbox.iviews.IBookView;
import com.ustory.techbox.model.BookModel;
import com.ustory.techbox.model.IBookModel;

import java.util.List;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/7
 */
public class BookPresenter extends BasePresenter<IBookView> {

    private BookModel mBookModel;
    private Context mContext;
    private android.os.Handler mHandler;
    public BookPresenter(){
        this.mBookModel = new BookModel();
        mHandler = new android.os.Handler();

    }

    public void loadAllBooks(){

        this.mBookModel.queryAllBook(new IBookModel.BookListener() {
            @Override
            public void showData(List<Book> list) {
                mMvpView.querySuccess(list);
            }

            @Override
            public void success() {

            }

            @Override
            public void failed() {

            }
        });
    }
}

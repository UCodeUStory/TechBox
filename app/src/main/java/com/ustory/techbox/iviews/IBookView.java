package com.ustory.techbox.iviews;

import com.ustory.techbox.bean.Book;
import com.ustory.techbox.core.mvp.MvpView;

import java.util.List;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/7
 */
public interface IBookView extends MvpView {
    void querySuccess(List<Book> books);

    void delete(Book book);

    boolean add(Book book);

    void update(Book book);
}

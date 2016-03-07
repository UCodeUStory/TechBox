package com.ustory.techbox.model;

import com.ustory.techbox.bean.Book;

import java.util.List;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/7
 */
public interface IBookModel {
    void add (Book book , BookListener listener);

    void delete (Book book,BookListener listener);

    void modify (Long bookId, BookListener listener);

    void queryItTechById (Long bookId, BookListener listener);

    List<Book> queryAllBook (BookListener listener);

    interface BookListener
    {
        void showData(List<Book> list);

        void success();

        void failed();
    }
}

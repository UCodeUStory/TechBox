package com.ustory.techbox.model;

import com.ustory.techbox.bean.Book;
import com.ustory.techbox.test.DataProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/7
 */
public class BookModel implements IBookModel {

    @Override
    public void add(Book book, BookListener listener) {
        
    }

    @Override
    public void delete(Book book, BookListener listener) {

    }

    @Override
    public void modify(Long bookId, BookListener listener) {

    }

    @Override
    public void queryItTechById(Long bookId, BookListener listener) {

    }

    @Override
    public List<Book> queryAllBook(BookListener listener) {
        List<Book> list = new ArrayList<Book>();
        /**
         * 异步线程网络请求
         */
        for (int i = 0; i< DataProvider.bookName.length; i++){
            Book book = new Book();
            book.setId(i);
            book.setImg(DataProvider.bookImage[i]);
            book.setName(DataProvider.bookName[i]);
            list.add(book);
        }
        if (list!=null)
        {
            listener.showData(list);
        } else {
            listener.failed();
        }
        return null;
    }
}

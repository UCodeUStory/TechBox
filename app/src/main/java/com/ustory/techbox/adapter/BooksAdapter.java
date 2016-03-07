package com.ustory.techbox.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.test2.R;
import com.ustory.techbox.bean.Book;

import java.util.List;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/7
 */
public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookView>{


    private List<Book> books;
    private static RecycleItemClickListener itemClickListener;


    public BooksAdapter(List<Book> list, RecycleItemClickListener clickListener) {
        books = list;
        itemClickListener = clickListener;
    }

    @Override
    public BookView onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recommend_book_item, viewGroup, false);
        return new BookView(view);
    }

    @Override
    public void onBindViewHolder(BookView bookView, int position) {
        bookView.imageView.setImageResource(books.get(position).getImg());

    }


    @Override
    public int getItemCount() {
        return books.size();
    }

    //viewholder
    public static class BookView extends  RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView imageView;
        //private TextView textView;


       public BookView(View itemView){
           super(itemView);
           imageView= (ImageView) itemView.findViewById(R.id.masonry_item_img );
           itemView.setOnClickListener(this);

       }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v,this.getLayoutPosition());
        }
    }

    public interface RecycleItemClickListener {
        void onItemClick(View view,int position);
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}

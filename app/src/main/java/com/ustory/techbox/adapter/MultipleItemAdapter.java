package com.ustory.techbox.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.test2.R;
import com.ustory.techbox.bean.ITTech;
import com.ustory.techbox.utils.L;

import java.util.ArrayList;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/9
 */
public class MultipleItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public static enum ITEM_TYPE {
        HEADER_ITEM, CONTENT_ITEM_ONE,CONTENT_ITEM_TWO
    }

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private ArrayList<ITTech> mItTeches;
    private final SparseArray<View> views = new SparseArray<>();;
    public MultipleItemAdapter(Context context, ArrayList<ITTech> mItTeches) {
        L.i("qiyue","MultipleItemAdapter ");
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mItTeches = mItTeches;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        L.i("qiyue","onCreateViewHolder1");
        if (viewType == ITEM_TYPE.HEADER_ITEM.ordinal()) {
            L.i("qiyue","onCreateViewHolder2");
            return new HeaderViewHolder(mLayoutInflater.inflate(R.layout.header_item, parent, false));
        } else{
            L.i("qiyue","onCreateViewHolder3");
            return new ContentViewHolder(mLayoutInflater.inflate(R.layout.content_item_one, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        L.i("qiyue","onBindViewHolder");
        if(holder instanceof HeaderViewHolder){
            L.i("qiyue","onBindViewHolder1");
           // ((TextViewHolder) holder).mTextView.setText(mTitle.get(position));
        }else if(holder instanceof HeaderViewHolder){
            L.i("qiyue","onBindViewHolder2");
          //  ((ImageViewHolder) holder).text.setText(mTitle.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return mItTeches.size() ;
    }

    @Override
    public int getItemViewType(int position) {

        return position == 0 ? ITEM_TYPE.HEADER_ITEM.ordinal(): ITEM_TYPE.CONTENT_ITEM_ONE.ordinal();
    }

    public <T extends View> T findViewById(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
           // view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }




    public static class ContentViewHolder extends RecyclerView.ViewHolder {

        protected static final String TAG = "ContentViewHolder";

        //TextView mTextView;

        public ContentViewHolder(View itemView) {
            super(itemView);
            //mTextView = (TextView) itemView.findViewById(R.id.textView);

           /* itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Log.d(TAG, "TextViewHolder"+getPosition());
                }
            });*/
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {

        protected static final String TAG = "HeaderViewHolder";

        ImageView mImage;
        TextView text;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            //mImage = (ImageView) itemView.findViewById(R.id.image);
           // text = (TextView) itemView.findViewById(R.id.text);
           // itemView.setOnClickListener(new View.OnClickListener() {

             //   @Override
             //   public void onClick(View v) {
                  //  Log.d(TAG, "ImageViewHolder"+getPosition());
            //    }
          //  });
        }
    }


}

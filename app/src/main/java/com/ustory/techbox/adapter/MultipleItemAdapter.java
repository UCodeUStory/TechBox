package com.ustory.techbox.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.test2.R;
import com.ustory.techbox.bean.ITTech;
import com.ustory.techbox.utils.L;
import com.ustory.techbox.views.WebViewActivity;

import java.util.ArrayList;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/9
 */
public class MultipleItemAdapter extends RecyclerView.Adapter<MultipleItemAdapter.HeaderViewHolder> {


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
    public HeaderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HeaderViewHolder(mLayoutInflater.inflate(R.layout.header_item, parent, false));
    }

    @Override
    public void onBindViewHolder(HeaderViewHolder holder, int position) {
        L.i("qiyue","onBindViewHolder"+position);
        holder.mLinearLayout.removeAllViews();
        if (mItTeches.get(position).getType() == ITTech.HADER) {
            //holder.mLinearLayout.removeAllViews();
            L.i("qiyue","1111111");

            holder.mLinearLayout.addView(createHeaderView(mItTeches.get(position).getDetailHeaderTitle()));
            holder.mLinearLayout.addView(createHeaderContentView(1));

            //TextView textView = new TextView(mContext);

        }


        if (mItTeches.get(position).getType() == ITTech.ITEM) {

                holder.mLinearLayout.addView(createHeaderView(mItTeches.get(position).getDetailContentHeader()));
                for (int i=0;i<mItTeches.get(position).getDetailContentItems().size();i++) {
                 //   createItemContentView
                    holder.mLinearLayout.addView(createItemContentView(mItTeches.get(position).getDetailContentItems().get(i),"https://github.com/UCodeUStory"));
                }

        }
    }





    public TextView createHeaderView(String text){
        TextView textView = new TextView(mContext);
        textView.setText(text);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100));
        textView.setTextColor(mContext.getResources().getColor(R.color.green));
        textView.setBackgroundColor(mContext.getResources().getColor(R.color.cardView_header));
       // textView.setClickable(true);
        textView.setTextSize(20);
        textView.setPadding(15,15,15,15);
        textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                WebViewActivity.toUrl(mContext,"https://github.com/UCodeUStory");
            }
        });
        return textView;
    }

    public ImageView createHeaderContentView(int id){
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(R.drawable.image);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        lp.setMargins(10, 10, 10, 10);
        imageView.setLayoutParams(lp);
        imageView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                WebViewActivity.toUrl(mContext,"https://github.com/UCodeUStory");
            }
        });
        return imageView;

    }
    /*<TextView
                android:layout_width="match_parent"
                android:layout_height="50dp"
                android:text="把上一个改回默认把当前改为需要的颜色 "
                android:clickable="true"
                android:background="@drawable/selector_item_clickable"
                android:gravity="center_vertical"
                android:textStyle="bold"
                android:textColor="@color/black"/>*/

    public TextView createItemContentView(String text, final String url){
        TextView textView = new TextView(mContext);
        textView.setText(text);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100));
        textView.setTextColor(mContext.getResources().getColor(R.color.black));
        textView.setBackgroundResource(R.drawable.selector_item_clickable);

        textView.setClickable(true);
        textView.setTextSize(15);
        textView.setPadding(15,15,15,15);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                WebViewActivity.toUrl(mContext,url);
            }
        });
        return textView;
    }

        /*
            if (getItemViewType(position) == ITEM_TYPE.CONTENT_ITEM_ONE.ordinal()) {
                L.i("qiyue","holder--3");
                for (int i = 1; i < mItTeches.size(); i++) {
                    L.i("qiyue","holder--4");
                    if (mItTeches.get(position).getType() == ITTech.ITEM) {
                        L.i("qiyue","holder--5");
                        LinearLayout linearLayout =  ((ContentViewHolder) holder).mLinearLayout;
                        linearLayout.removeAllViews();
                        // LinearLayout llyHeader = findViewById(R.id.lly_header);
                        TextView textView = new TextView(mContext);
                        textView.setText("IOS");
                        linearLayout.addView(textView);
                        //TextView textView = new TextView(mContext);
                        ImageView imageView = new ImageView(mContext);
                        // imageView.setImageBitmap(R.mipmap.);
                        linearLayout.addView(imageView);
                        L.i("qiyue","holder--6");
                    }
                }
            }
            */



        //if(holder instanceof HeaderViewHolder){
          //  L.i("qiyue","onBindViewHolder1");
           // ((TextViewHolder) holder).mTextView.setText(mTitle.get(position));
        //}else if(holder instanceof HeaderViewHolder){
          //  L.i("qiyue","onBindViewHolder2");
          //  ((ImageViewHolder) holder).text.setText(mTitle.get(position));
        //}

    //}

    @Override
    public int getItemCount() {
        return mItTeches.size() ;
    }



    public <T extends View> T findViewById(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
           // view = convertView.findViewById(viewId);
           // View inflate = mLayoutInflater.inflate();/
          //  inflate(layoutId, parent,
           //         false);
            views.put(viewId, view);
        }
        return (T) view;
    }





    public static class HeaderViewHolder extends RecyclerView.ViewHolder {

        protected static final String TAG = "HeaderViewHolder";

        private LinearLayout mLinearLayout;

        public HeaderViewHolder(View itemView) {
            super(itemView);

            mLinearLayout = (LinearLayout)itemView.findViewById(R.id.lly_header);
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

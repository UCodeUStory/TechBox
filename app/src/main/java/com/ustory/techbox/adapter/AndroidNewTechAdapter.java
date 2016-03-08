package com.ustory.techbox.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.test2.R;
import com.ustory.techbox.bean.AndroidTech;

import java.util.List;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/7
 */
public class AndroidNewTechAdapter extends RecyclerView.Adapter<AndroidNewTechAdapter.AndroidTechView>{


    public static RecycleItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public static void setItemClickListener(RecycleItemClickListener itemClickListener) {
        AndroidNewTechAdapter.itemClickListener = itemClickListener;
    }

    private List<AndroidTech> androidTechs;
    private static RecycleItemClickListener itemClickListener;


    public AndroidNewTechAdapter(List<AndroidTech> list, RecycleItemClickListener clickListener) {
        androidTechs = list;
        itemClickListener = clickListener;
    }

    @Override
    public AndroidTechView onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.android_tech_item, viewGroup, false);
        return new AndroidTechView(view);
    }

    @Override
    public void onBindViewHolder(AndroidTechView techView, int position) {
       // techView.imageView.setImageResource(androidTechs.get(position).getImg());
          techView.content.setText(androidTechs.get(position).getContent());
          techView.type.setText(androidTechs.get(position).getType());
          techView.date.setText(androidTechs.get(position).getDate());
    }


    @Override
    public int getItemCount() {
        return androidTechs.size();
    }

    //viewholder
    public static class AndroidTechView extends  RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView content;
        private TextView date;
        private TextView type;


       public AndroidTechView(View itemView){
           super(itemView);
           //imageView= (ImageView) itemView.findViewById(R.id.masonry_item_img );
           date = (TextView) itemView.findViewById(R.id.android_tech_date_tv);
           type = (TextView) itemView.findViewById(R.id.android_tech_type);
           content = (TextView) itemView.findViewById(R.id.android_tech_title_tv);
           itemView.setOnClickListener(this);
       }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v,this.getLayoutPosition());
        }
    }

    public interface RecycleItemClickListener {
        void onItemClick(View view, int position);
    }

    public List<AndroidTech> getAndroidTechs() {
        return androidTechs;
    }

    public void setAndroidTechs(List<AndroidTech> androidTechs) {
        this.androidTechs = androidTechs;
    }
}

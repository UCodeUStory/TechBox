package com.ustory.techbox.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.test2.R;
import com.ustory.techbox.bean.ITTech;
import com.ustory.techbox.utils.GlideUtils;

import java.util.List;

public class NewTechRecyclerAdapter extends RecyclerView.Adapter<NewTechRecyclerAdapter.MyViewHolder>
{

	//private List<String> mDatas;
	private List<ITTech> mITTechList;
	private LayoutInflater mInflater;

	public interface OnItemClickLitener
	{
		void onItemClick(View view, int position);
		void onItemLongClick(View view, int position);
	}

	private OnItemClickLitener mOnItemClickLitener;

	public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
	{
		this.mOnItemClickLitener = mOnItemClickLitener;
	}
	

	public NewTechRecyclerAdapter(Context context, List<ITTech> datas)
	{
		mInflater = LayoutInflater.from(context);
		this.mITTechList = datas;
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		MyViewHolder holder = new MyViewHolder(mInflater.inflate(
				R.layout.news_ittech_item, parent, false));
		return holder;
	}

	@Override
	public void onBindViewHolder(final MyViewHolder holder, final int position)
	{
		holder.label_one.setText(mITTechList.get(position).getSimilar_key_0());
		holder.label_two.setText(mITTechList.get(position).getSimilar_key_1());
		holder.content.setText(mITTechList.get(position).getContent());
		holder.date.setText(mITTechList.get(position).getDate());
		GlideUtils.displayNative(holder.photo,R.mipmap.test0);
		// 如果设置了回调，则设置点击事件
		if (mOnItemClickLitener != null)
		{
			holder.itemView.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					int pos = holder.getLayoutPosition();
					mOnItemClickLitener.onItemClick(holder.itemView, pos);
				}
			});
			
			holder.itemView.setOnLongClickListener(new OnLongClickListener()
			{
				@Override
				public boolean onLongClick(View v)
				{
					int pos = holder.getLayoutPosition();
					mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
					//removeData(pos);
					return false;
				}
			});
		}
	}

	@Override
	public int getItemCount()
	{
		return mITTechList.size();
	}

	public void setDataList(List<ITTech>list){
		this.mITTechList = list;
	}
	private List<ITTech> getDataList(){
	    return this.mITTechList;
    }

	public void addData(int position)
	{
		//mDatas.add(position, "Insert One");
		mITTechList.add(position,new ITTech());
		notifyItemInserted(position);
	}


	public void removeData(int position)
	{
		mITTechList.remove(position);
		notifyItemRemoved(position);
	}

	class MyViewHolder extends ViewHolder
	{

		TextView date;
		TextView label_one;
		TextView label_two;
		TextView content;
		ImageView photo;

		public MyViewHolder(View view)
		{
			super(view);
			date = (TextView) view.findViewById(R.id.date);
			label_one = (TextView) view.findViewById(R.id.label_one);
			label_two = (TextView) view.findViewById(R.id.label_two);
			content = (TextView) view.findViewById(R.id.content);
		    ImageView photo = (ImageView) view.findViewById(R.id.photo);
		
		}
	}
}
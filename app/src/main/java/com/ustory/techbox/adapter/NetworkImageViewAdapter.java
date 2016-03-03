package com.ustory.techbox.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.administrator.test2.R;
import com.ustory.techbox.utils.LruImageCache;
import com.ustory.techbox.utils.StringUtil;
import com.ustory.techbox.utils.VolleyUtil;


public class NetworkImageViewAdapter extends ImageBaseAdapter{

	private ImageLoader imageLoader;
	
	public NetworkImageViewAdapter(Context context, String[] imageUrlArray) {
		super(context, imageUrlArray);
		this.imageLoader=new ImageLoader(VolleyUtil.getQueue(context), new LruImageCache());
	}

	@Override
	int getItemLayout() {
		return R.layout.fr_network_image_view_list_item;
}

	@Override
	void setImage(ImageView imageView, String imageUrl) {
		NetworkImageView networkImageView=(NetworkImageView)imageView;
		networkImageView.setDefaultImageResId(R.mipmap.ic_empty);
		networkImageView.setErrorImageResId(R.mipmap.ic_empty);
		networkImageView.setImageUrl(StringUtil.preUrl(imageUrl),  imageLoader);
	}

}

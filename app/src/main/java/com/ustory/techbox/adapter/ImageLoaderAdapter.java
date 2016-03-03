package com.ustory.techbox.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.example.administrator.test2.R;
import com.ustory.techbox.utils.LruImageCache;
import com.ustory.techbox.utils.StringUtil;
import com.ustory.techbox.utils.VolleyUtil;


public class ImageLoaderAdapter extends ImageBaseAdapter{
	
	private ImageLoader imageLoader;
	

	public ImageLoaderAdapter(Context context, String[] imageUrlArray) {
		super(context, imageUrlArray);
		this.imageLoader=new ImageLoader(VolleyUtil.getQueue(context), new LruImageCache());
	}

	@Override
	int getItemLayout() {
		
		return R.layout.fr_image_request_list_item;
	}

	@Override
	void setImage(ImageView imageView, String imageUrl) {
		
		ImageLoader.ImageContainer container;
		
		try {
			//濡傛灉褰撳墠ImageView涓婂瓨鍦ㄨ姹傦紝鍏堝彇娑�			if(imageView.getTag()!=null)
			{
				container=(ImageLoader.ImageContainer)imageView.getTag();
				container.cancelRequest();
			}
		} catch (Exception e) {

		}
		
		ImageLoader.ImageListener listener=ImageLoader.getImageListener(imageView, R.mipmap.ic_empty, R.mipmap.ic_empty);
		
		container= imageLoader.get(StringUtil.preUrl(imageUrl),listener);
		
		//鍦↖mageView涓婂瓨鍌ㄥ綋鍓嶈姹傜殑Container锛岀敤浜庡彇娑堣姹�		imageView.setTag(container);
		
	}

}

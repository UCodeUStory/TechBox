package com.ustory.techbox.utils;

import android.util.Log;

/*
 * create by qiyue
 * this is a logUtils
 */
public class L {

	private final static String TAG = "UStoryFrame";
	private final static boolean DEBUG = true;
	public static void i(String message){
		if (DEBUG){			
			Log.i(TAG,message);
		}
	}
	public static void i(String tag,String message){
		if (DEBUG){			
			Log.i(tag,message);
		}
	}
	public static void w(String message){
		if (DEBUG){			
			Log.w(TAG,message);
		}
	}
	public static void w(String tag,String message){
		if (DEBUG){			
			Log.w(tag,message);
		}
	}
	public static void e(String message){
		if (DEBUG){			
			Log.e(TAG,message);
		}
	}
	public static void e(String tag,String message){
		if (DEBUG){			
			Log.e(tag,message);
		}
	}
	
}

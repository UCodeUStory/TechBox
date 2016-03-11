package com.ustory.techbox.utils;

import android.util.Log;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/6
 */
public class L {

	private final static String TAG = "techbox";
	private final static boolean DEBUG = true;
	private final static boolean CANCEL_TAG = false;
	public static void i(String message){
		if (DEBUG) {
			Log.i(TAG, message);
		}
	}
	public static void i(String tag,String message){
		if (DEBUG){
			if (!CANCEL_TAG) {
				Log.i(tag, message);
			}else{
				i(message);
			}
		}
	}
	public static void w(String message){
		if (DEBUG){
			Log.w(TAG,message);
		}
	}
	public static void w(String tag,String message){
		if (DEBUG){
			if (!CANCEL_TAG) {
				Log.w(tag, message);
			}else{
				w(message);
			}
		}

	}
	public static void e(String message){
		if (DEBUG){			
			Log.e(TAG,message);
		}
	}
	public static void e(String tag,String message){
		if (DEBUG){
			if (!CANCEL_TAG) {
				Log.i(tag, message);
			}else{
				e(message);
			}
		}
	}
	
}

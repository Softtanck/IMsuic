package com.softtanck.imusic.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * 
 * 
 * @Description 日志管理工具
 * 
 * @author Tanck
 * 
 * @date Jan 16, 2015 8:43:09 PM
 * 
 * 
 */
public class LogUtils {

	private static final boolean DEBUG = true;
	private static final String TAG = "IMusic";

	public static void i(String text) {
		if (DEBUG && !TextUtils.isEmpty(text))
			Log.i(TAG, text);
	}

	public static void d(String text) {
		if (DEBUG && !TextUtils.isEmpty(text))
			Log.d(TAG, text);
	}

	public static void w(String text) {
		if (DEBUG && !TextUtils.isEmpty(text))
			Log.w(TAG, text);
	}

	public static void e(String text) {
		if (DEBUG && !TextUtils.isEmpty(text))
			Log.e(TAG, text);
	}
}

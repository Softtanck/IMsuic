package com.softtanck.imusic.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 
 * @Description TODO 配置文件工具类
 * 
 * @author Tanck
 * 
 * @date May 7, 2015 2:42:24 PM
 * 
 */
public class ConfigUtils {

	public static final String CONFIG_NAME = "com.softtanck.imusic";

	/**
	 * 存放Boolean
	 * 
	 * @param context
	 * @param name
	 * @param value
	 */
	public static void saveBoolConfig(Context context, String name, boolean value) {
		SharedPreferences mySharedPreferences = context.getSharedPreferences(CONFIG_NAME, context.MODE_PRIVATE);
		SharedPreferences.Editor editor = mySharedPreferences.edit();
		editor.putBoolean(name, value);
		editor.commit();
	}
}

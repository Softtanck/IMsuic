package com.softtanck.imusic;

import com.softtanck.imusic.utils.LogUtils;
import com.softtanck.imusic.utils.ScreenUtils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * 
 * @Description TODO 应用程序启动 <b>单例模式</b>
 * 
 * @author Tanck
 * 
 * @date Apr 14, 2015 8:22:04 PM
 * 
 */
public class IMusic extends Application {

	private static IMusic instance;

	public static synchronized IMusic getInstance() {
		return instance;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		instance = this;

		ConstantValue.WINDOW_HIGHT = ScreenUtils
				.getScreenHeight(getApplicationContext());

		ConstantValue.WINDOW_WIDTH = ScreenUtils
				.getScreenWidth(getApplicationContext());

		LogUtils.d("init Appliction");
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		ActivityContainer.finishAll();
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		// TODO 对map降低引用,低内存下适应.
	}

	/************************ 数据保存 ***********************/

	/**
	 * 保存Object到sp
	 */
	public void saveToSharedPreferences(String key, Object value) {
		SharedPreferences sp = getSharedPreferences(
				ConstantValue.App.SHARENAME_CONFIG, Context.MODE_PRIVATE);
		sp.edit().putString(key, value.toString()).commit();
	}

	/**
	 * 从sp获取object
	 */
	public String getFromSharedPreferences(String key) {
		SharedPreferences sp = getSharedPreferences(
				ConstantValue.App.SHARENAME_CONFIG, Context.MODE_PRIVATE);
		return sp.getString(key, null);
	}

	/**
	 * 保存String到sp
	 */
	public void saveToSharedPreferences(String key, String value) {
		SharedPreferences sp = getSharedPreferences(
				ConstantValue.App.SHARENAME_CONFIG, Context.MODE_PRIVATE);
		sp.edit().putString(key, value).commit();
	}

	/**
	 * 从sp获取String
	 */
	public String getStringFromSharedPreferences(String key) {
		SharedPreferences sp = getSharedPreferences(
				ConstantValue.App.SHARENAME_CONFIG, Context.MODE_PRIVATE);
		return sp.getString(key, null);
	}

	/**
	 * 保存boolean到sp
	 */
	public void saveToSharedPreferences(String key, boolean value) {
		SharedPreferences sp = getSharedPreferences(
				ConstantValue.App.SHARENAME_CONFIG, Context.MODE_PRIVATE);
		sp.edit().putBoolean(key, value).commit();
	}

	/**
	 * 从sp获取object
	 */
	public boolean getBooleanFromSharedPreferences(String key) {
		SharedPreferences sp = getSharedPreferences(
				ConstantValue.App.SHARENAME_CONFIG, Context.MODE_PRIVATE);
		return sp.getBoolean(key, true);
	}

	/**
	 * 清理sp
	 */
	public boolean removeFromSharedPreference(String key) {
		SharedPreferences sp = getSharedPreferences(
				ConstantValue.App.SHARENAME_CONFIG, Context.MODE_PRIVATE);
		return sp.edit().remove(key).commit();
	}
}

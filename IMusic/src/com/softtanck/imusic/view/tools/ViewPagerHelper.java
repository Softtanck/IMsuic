package com.softtanck.imusic.view.tools;

import android.annotation.SuppressLint;
import android.view.View;

/**
 * 
 * @Description TODO 图片滚动帮助类
 * 
 * @author Tanck
 * 
 * @date May 11, 2015 3:56:48 PM
 * 
 */
@SuppressLint("NewApi")
public class ViewPagerHelper {

	/**
	 * 
	 * 缩放
	 * 
	 * @param view
	 *            视图
	 * @param offset
	 *            偏移
	 * @param value
	 *            缩放值
	 */
	public static void setScale(View view, float offset, float value) {
		view.setScaleX(Math.max(value, 1 - Math.abs(offset)));
		view.setScaleY(Math.max(value, 1 - Math.abs(offset)));
	}

}

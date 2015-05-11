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
	 *            缩放值 0.9
	 */
	public static void setScale(View view, float offset, float value) {
		view.setScaleX(Math.max(value, 1 - Math.abs(offset)));
		view.setScaleY(Math.max(value, 1 - Math.abs(offset)));
	}

	/**
	 * 
	 * 旋转
	 * 
	 * @param view
	 *            视图
	 * @param offset
	 *            偏移
	 * @param value
	 *            缩放值 0.5f
	 */
	public static void setFlip(View view, float offset, float value) {
		view.setPivotX(offset < 0f ? view.getWidth() : 0f);
		view.setPivotY(view.getHeight() * value);
		view.setRotationY(offset * 90f);
	}

}

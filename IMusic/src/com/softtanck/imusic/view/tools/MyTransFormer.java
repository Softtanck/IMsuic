package com.softtanck.imusic.view.tools;

import android.annotation.SuppressLint;
import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

/**
 * 
 * 
 * @Description TODO 页面切换器
 * 
 * @author Tanck
 * 
 * @date May 11, 2015 10:40:04 AM
 * 
 */
@SuppressLint("NewApi")
public class MyTransFormer implements PageTransformer {

	/**
	 * 手指滑动的时候不停的回调
	 */
	public void transformPage(View view, float position) {

		if (-1 > position) { // 不可见
			view.setAlpha(0);
		} else if (1 >= position) {
			view.setAlpha(1);
			ViewPagerHelper.setFlip(view, position, 0.5f);
		} else {
			view.setAlpha(0);
		}
	}

}

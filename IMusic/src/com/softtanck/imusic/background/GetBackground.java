package com.softtanck.imusic.background;

import com.softtanck.imusic.R;
import com.softtanck.imusic.ui.MusicActivity;
import com.softtanck.imusic.utils.BlurUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.LinearLayout;

/**
 * 
 * @Description TODO 异步加载毛玻璃背景图片
 * 
 * @author Tanck
 * 
 * @date Apr 16, 2015 5:55:23 PM
 * 
 */
public class GetBackground extends AsyncTask<Integer, Void, Drawable> {

	/**
	 * 上下文
	 */
	private Context context;

	/**
	 * 背景布局
	 */
	private LinearLayout contentLayout;

	public GetBackground(LinearLayout layout, Context context) {
		this.context = context;
		this.contentLayout = layout;
	}

	@SuppressWarnings({ "unused", "deprecation" })
	@Override
	protected Drawable doInBackground(Integer... params) {
		Bitmap bmp = BitmapFactory.decodeResource(context.getResources(),
				params[0]);// 从资源文件中得到图片，并生成Bitmap图片
		Bitmap blurBmp = BlurUtil.fastblur(context, bmp, params[1]);// 0-25，表示模糊值
		Drawable newBitmapDrawable = new BitmapDrawable(blurBmp); // 将Bitmap转换为Drawable
		return newBitmapDrawable;
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onPostExecute(final Drawable result) {
		if (null != result) {

			// TODO 做个加载的动画
			AlphaAnimation animation = new AlphaAnimation(0, 1);
			animation.setDuration(700);
			animation.setFillAfter(true);
			animation.setAnimationListener(new AnimationListener() {

				@Override
				public void onAnimationStart(Animation animation) {
					contentLayout.setBackgroundDrawable(result);// 设置背景
				}

				@Override
				public void onAnimationRepeat(Animation animation) {

				}

				@Override
				public void onAnimationEnd(Animation animation) {

				}
			});
			contentLayout.startAnimation(animation);
		}
	}
}

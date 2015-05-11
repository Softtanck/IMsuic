package com.softtanck.imusic.anim;

import com.softtanck.imusic.ConstantValue;
import com.softtanck.imusic.R;
import com.softtanck.imusic.bean.Music;
import com.softtanck.imusic.fragment.OnPlayAnimListener;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * 
 * @Description TODO 播放动画
 * 
 * @author Tanck
 * 
 * @date May 8, 2015 4:38:57 PM
 * 
 */
public class PlayMusicAnim {

	/**
	 * 动画层
	 */
	private static ViewGroup manimLayout;

	/**
	 * 开始动画视图
	 */
	private static ImageView startview;

	/**
	 * 顶级窗口
	 */
	private static ViewGroup rootView;

	/**
	 * 真实动画层
	 */
	private static LinearLayout animLayout;

	/**
	 * 动画监听
	 */
	private static OnPlayAnimListener listener;

	/**
	 * 为播放的Item设置动画
	 * 
	 * @param context
	 *            上下文
	 * @param music
	 *            音乐实体
	 * @param endview
	 *            结束播放的位置的视图
	 * @param startLocation
	 *            开始播放的位置
	 */
	public static void setAnim(Activity context, final Music music, View endview, int[] startLocation) {

		startLocation[0] = ConstantValue.WINDOW_WIDTH / 2;// 初始化宽度

		startview = new ImageView(context);
		startview.setImageResource(R.drawable.ic_launcher);

		manimLayout = creatAnimView(context);// 创建动画层
		manimLayout.addView(startview);// 添加动画到动画层
		final View view = setViewinAnimLayout(startview, startLocation);

		int[] endlocation = new int[2];// 这是用来存储动画结束位置的X、Y坐标
		endview.getLocationInWindow(endlocation);// shopCart是那个购物车

		// 计算位移
		int endX = 0 - startLocation[0] + 40;// 动画位移的X坐标
		int endY = endlocation[1] - startLocation[1];// 动画位移的y坐标
		TranslateAnimation translateAnimationX = new TranslateAnimation(0, endX, 0, 0);
		translateAnimationX.setInterpolator(new LinearInterpolator());
		translateAnimationX.setRepeatCount(0);// 动画重复执行的次数
		translateAnimationX.setFillAfter(true);

		TranslateAnimation translateAnimationY = new TranslateAnimation(0, 0, 0, endY);
		translateAnimationY.setInterpolator(new AccelerateInterpolator());
		translateAnimationY.setRepeatCount(0);// 动画重复执行的次数
		translateAnimationX.setFillAfter(true);

		AnimationSet set = new AnimationSet(false);
		set.setFillAfter(false);
		set.addAnimation(translateAnimationY);
		set.addAnimation(translateAnimationX);
		set.setDuration(800);// 动画的执行时间
		view.startAnimation(set);
		// 动画监听事件
		set.setAnimationListener(new AnimationListener() {
			// 动画的开始
			@Override
			public void onAnimationStart(Animation animation) {
				view.setVisibility(View.VISIBLE);
				if (null != listener) {
					listener.OnAnimStarted(music);
				}
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
			}

			// 动画的结束
			@Override
			public void onAnimationEnd(Animation animation) {
				view.setVisibility(View.GONE);
				if (null != listener) {
					listener.OnAnimEnded(music);
				}
			}
		});

	}

	/**
	 * 创建动画层
	 * 
	 * @param context
	 * @return
	 */
	private static ViewGroup creatAnimView(Activity context) {
		rootView = (ViewGroup) context.getWindow().getDecorView();// 获取顶级窗口
		animLayout = new LinearLayout(context);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		animLayout.setLayoutParams(lp);
		animLayout.setId(Integer.MAX_VALUE);
		animLayout.setBackgroundResource(android.R.color.transparent);// 设置动画层的背景颜色为透明的.
		rootView.addView(animLayout);// 让顶级窗口添加该动画层
		return animLayout;
	}

	/**
	 * 设置动画在动画层的位置
	 * 
	 * @param view
	 * @param location
	 * @return
	 */
	private static View setViewinAnimLayout(final View view, int[] location) {
		int x = location[0];
		int y = location[1];
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		lp.leftMargin = x;// 屏幕的一半
		lp.topMargin = y;
		view.setLayoutParams(lp);// 要做动画的View设置位置
		return view;
	}

	public static void setListener(OnPlayAnimListener listener) {
		PlayMusicAnim.listener = listener;
	}

}

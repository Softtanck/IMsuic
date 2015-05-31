package com.softtanck.imusic.anim;

import com.softtanck.imusic.ConstantValue;
import com.softtanck.imusic.R;
import com.softtanck.imusic.bean.Music;
import com.softtanck.imusic.fragment.OnPlayAnimListener;
import com.softtanck.imusic.utils.BaseUtils;
import com.softtanck.imusic.utils.LogUtils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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
	 * 列表循环
	 */
	private static ImageView mListLoop;

	/**
	 * 单曲循环
	 */
	private static ImageView mOneLoop;

	/**
	 * 顺序播放
	 */
	private static ImageView mOrderPlay;

	/**
	 * 随机播放
	 */
	private static ImageView mRounderPlay;

	private static RelativeLayout ranimLayout;

	/**
	 * 模式知之间的距离
	 */
	private static int mMargin = 100;

	/**
	 * 动画展示标志
	 */
	private static boolean isShow;

	/**
	 * 模式监听
	 */

	public static void setModeAnim(Activity context, ImageView mMode, OnClickListener onClickListener) {

		if (isShow) {
			isShow = false;
			clearAnim();
			return;
		}

		isShow = true;
		int[] location = new int[2];
		mMode.getLocationInWindow(location);

		manimLayout = creatRelativeAnimView(context);// 创建动画层

		// 实例化几个控件
		mListLoop = new ImageView(context);
		mOneLoop = new ImageView(context);
		mOrderPlay = new ImageView(context);
		mRounderPlay = new ImageView(context);

		mListLoop.setImageResource(R.drawable.play_list_loop_selector);
		mOneLoop.setImageResource(R.drawable.play_one_loop_selector);
		mOrderPlay.setImageResource(R.drawable.play_order_selector);
		mRounderPlay.setImageResource(R.drawable.play_runder_selector);

		manimLayout.addView(mListLoop);// 添加动画到动画层
		manimLayout.addView(mOneLoop);// 添加动画到动画层
		manimLayout.addView(mOrderPlay);// 添加动画到动画层
		manimLayout.addView(mRounderPlay);// 添加动画到动画层

		if (null != onClickListener) {
			mListLoop.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					LogUtils.d("----");
				}
			});
			mOneLoop.setOnClickListener(onClickListener);
			mOrderPlay.setOnClickListener(onClickListener);
			mRounderPlay.setOnClickListener(onClickListener);
		}

		// 设置四个控件的初始位置
		View vlistLoop = setViewinRelativeAnimLayout(mListLoop, location);
		View voneloop = setViewinRelativeAnimLayout(mOneLoop, location);
		View vorderplay = setViewinRelativeAnimLayout(mOrderPlay, location);
		View vrounderplay = setViewinRelativeAnimLayout(mRounderPlay, location);

		// 计算位移
		AnimationSet set_one = doTransetAnim(vlistLoop, mMode, ConstantValue.WINDOW_WIDTH - mMode.getWidth() - mMargin);
		vlistLoop.startAnimation(set_one);

		// 计算位移
		AnimationSet set_two = doTransetAnim(voneloop, mMode, ConstantValue.WINDOW_WIDTH / 4 * 3 - mMode.getWidth() - mMargin);
		voneloop.startAnimation(set_two);

		// 计算位移
		AnimationSet set_three = doTransetAnim(vorderplay, mMode, ConstantValue.WINDOW_WIDTH / 4 * 2 - mMode.getWidth() - mMargin + 10);
		vorderplay.startAnimation(set_three);

		// 计算位移
		AnimationSet set_four = doTransetAnim(vrounderplay, mMode, ConstantValue.WINDOW_WIDTH / 4 - mMode.getWidth() - mMargin);
		vrounderplay.startAnimation(set_four);

	}

	/**
	 * 动画移动轨迹
	 * 
	 * @param mMode
	 * @param endX
	 * @return
	 */
	private static AnimationSet doTransetAnim(final View view, ImageView mMode, final int endX) {
		final int endY = mMode.getHeight() + 70;// 动画位移的y坐标

		TranslateAnimation translateAnimationX = new TranslateAnimation(0, endX, 0, 0);
		translateAnimationX.setInterpolator(new LinearInterpolator());
		translateAnimationX.setRepeatCount(0);// 动画重复执行的次数
		translateAnimationX.setFillEnabled(true);
		translateAnimationX.setFillAfter(true);

		TranslateAnimation translateAnimationY = new TranslateAnimation(0, 0, 0, -endY);
		translateAnimationY.setInterpolator(new AccelerateInterpolator());
		translateAnimationY.setRepeatCount(0);// 动画重复执行的次数
		translateAnimationX.setFillEnabled(true);
		translateAnimationX.setFillAfter(true);

		AnimationSet set = new AnimationSet(false);
		set.setFillAfter(true);
		set.addAnimation(translateAnimationY);
		set.addAnimation(translateAnimationX);
		set.setDuration(300);// 动画的执行时间
		set.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				view.setVisibility(View.GONE);
				view.layout(endX, endY, endX+view.getWidth(), endY+view.getHeight());
				view.setVisibility(View.VISIBLE);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				
//				int[] startlocation = new int[2];
//				startlocation[0] = endX;
//				startlocation[1] = endY;
//				setViewinRelativeAnimLayout(view, startlocation);
			}
		});
		return set;
	}

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
	 * 创建动画层
	 * 
	 * @param context
	 * @return
	 */
	private static ViewGroup creatRelativeAnimView(Activity context) {
		rootView = (ViewGroup) context.getWindow().getDecorView();// 获取顶级窗口
		ranimLayout = new RelativeLayout(context);
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
		ranimLayout.setLayoutParams(lp);
		ranimLayout.setId(Integer.MAX_VALUE);
		ranimLayout.setBackgroundResource(android.R.color.transparent);// 设置动画层的背景颜色为透明的.
		rootView.addView(ranimLayout);// 让顶级窗口添加该动画层
		return ranimLayout;
	}

	/**
	 * 设置动画在动画层的位置
	 * 
	 * @param view
	 * @param location
	 * @return
	 */
	private static View setViewinRelativeAnimLayout(final View view, int[] location) {
		int x = location[0];
		int y = location[1];
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.height = BaseUtils.dip(view.getContext(), 24);
		lp.width = BaseUtils.dip(view.getContext(), 24);
		lp.leftMargin = x;
		lp.topMargin = y;
		view.setLayoutParams(lp);// 要做动画的View设置位置
		return view;
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

	/**
	 * 清除动画
	 */
	private static void clearAnim() {
		manimLayout.removeAllViews();
	}

	/**
	 * 返回当前模式是否展示
	 * 
	 * @return
	 */
	public static boolean isShowMode() {
		return isShow;
	}
}

package com.softtanck.imusic.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.LinearLayout;

import com.softtanck.imusic.R;

/**
 * 
 * @Description TODO 启动动画页面
 * 
 * @author Tanck
 * 
 * @date Jun 5, 2015 11:53:48 AM
 * 
 */
public class SplashActivity extends Activity {

	private LinearLayout mWelcome;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// 去掉信息栏
		setContentView(R.layout.activity_splash);
		mWelcome = (LinearLayout) findViewById(R.id.ll_welcome);
		AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
		animation.setDuration(3000);
		animation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				Intent guide = new Intent(SplashActivity.this, StartAnimActivity.class);
				startActivity(guide);
				finish();
			}
		});
		mWelcome.startAnimation(animation);
	}
}

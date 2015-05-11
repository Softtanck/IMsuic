package com.softtanck.imusic.ui;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.softtanck.imusic.ActivityContainer;
import com.softtanck.imusic.BaseActivity;
import com.softtanck.imusic.R;
import com.softtanck.imusic.fragment.HomeFragment;
import com.softtanck.imusic.service.LocalBinder;
import com.softtanck.imusic.service.PlayService;
import com.softtanck.imusic.ui.utils.RoundedCornerImageView;

/**
 * 
 * @Description TODO 播放器主界面
 * 
 * @author Tanck
 * 
 * @date Apr 14, 2015 8:20:31 PM
 * 
 */
public class HomeActivity extends BaseActivity {

	/**
	 * 退出时间
	 */
	private long currTime;

	/**
	 * 音乐头像:主界面
	 */
	public static RoundedCornerImageView songHead;

	/**
	 * 音乐播放服务
	 */
	public static PlayService mService;

	/**
	 * 播放或者暂停按钮
	 */
	private ImageView mplay_pause;

	/**
	 * 服务是否被绑定
	 */
	protected boolean isBinded;

	@Override
	protected int getViewId() {
		return R.layout.activity_main;
	}

	@Override
	public void onDestroyed() {
		// 取消绑定
		unbindService(mConnection);
	}

	@Override
	protected void onActivityCreate() {

		initView();

		songHead = (RoundedCornerImageView) findViewById(R.id.home_iv_now_play_song);

		songHead.setOnClickListener(this);

		mplay_pause = (ImageView) findViewById(R.id.iv_main_play_pause);
		mplay_pause.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mplay_pause.startAnimation(AnimationUtils.loadAnimation(context, R.anim.icon_translate));
			}
		});

		// 初始化服务
		initService();

	}

	/**
	 * 初始化服务
	 */
	private void initService() {
		// Bind to LocalService
		Intent intent = new Intent(this, PlayService.class);
		bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
	}

	/**
	 * 回调服务
	 */
	private ServiceConnection mConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName className, IBinder service) {
			LocalBinder binder = (LocalBinder) service;
			mService = binder.getService();
			isBinded = true;
		}

		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			isBinded = false;
		}
	};

	/**
	 * 初始化布局
	 */
	private void initView() {
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		middleFragment = new HomeFragment();
		transaction.add(R.id.home_content, middleFragment);
		transaction.commit();
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.home_iv_now_play_song:// 主界面音乐头像
			Intent music = new Intent(HomeActivity.this, MusicActivity.class);
			startActivity(music);
			break;
		}
	}

	@Override
	public void onBackPressed() {
		if (fragmentManager.popBackStackImmediate())
			return;
		if ((System.currentTimeMillis() - currTime) > 2000) {
			showToast("再按一次,退出应用");
			currTime = System.currentTimeMillis();
			return;
		} else {
			ActivityContainer.finishAll();
		}
	}

}

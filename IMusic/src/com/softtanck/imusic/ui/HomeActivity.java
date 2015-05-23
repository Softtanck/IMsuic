package com.softtanck.imusic.ui;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.softtanck.imusic.ActivityContainer;
import com.softtanck.imusic.BaseActivity;
import com.softtanck.imusic.ConstantValue;
import com.softtanck.imusic.R;
import com.softtanck.imusic.bean.Music;
import com.softtanck.imusic.bean.PlayMsg;
import com.softtanck.imusic.fragment.HomeFragment;
import com.softtanck.imusic.service.LocalBinder;
import com.softtanck.imusic.service.PlayService;
import com.softtanck.imusic.ui.utils.RoundedCornerImageView;
import com.softtanck.imusic.utils.BaseUtils;
import com.softtanck.imusic.utils.LogUtils;

/**
 * 
 * @Description TODO 播放器主界面
 * 
 * @author Tanck
 * 
 * @date Apr 14, 2015 8:20:31 PM
 * 
 */
public class HomeActivity extends BaseActivity implements OnMusicStartPlayListener {

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
	public static ImageView mplay_pause;

	/**
	 * 下一首
	 */
	private ImageView mNextsong;

	/**
	 * 菜单
	 */
	private ImageView mMenu;

	/**
	 * 服务是否被绑定
	 */
	protected boolean isBinded;

	/**
	 * 发送消息
	 */
	private PlayMsg msg;

	/**
	 * 歌曲名字
	 */
	public static TextView msongName;

	/**
	 * 歌曲作者
	 */
	public static TextView msongSinger;

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

		initTitleView();

		initView();

		songHead = (RoundedCornerImageView) findViewById(R.id.home_iv_now_play_song);

		songHead.setOnClickListener(this);

		mplay_pause = (ImageView) findViewById(R.id.iv_main_play_pause);
		mNextsong = (ImageView) findViewById(R.id.iv_main_next_song);
		mMenu = (ImageView) findViewById(R.id.iv_main_menu);

		// 歌曲名字-作者
		msongName = (TextView) findViewById(R.id.tv_main_song_name);
		msongSinger = (TextView) findViewById(R.id.tv_main_song_singer);

		mNextsong.setOnClickListener(this);
		mplay_pause.setOnClickListener(this);
		mMenu.setOnClickListener(this);

		// 初始化服务
		initService();

	}

	/**
	 * 初始化标题
	 */
	private void initTitleView() {
		titleView.addLeftTextMenu(context, R.string.menu_left_string, 15, null);
		titleView.addRightTextMenu(context, R.string.menu_right_string, 0, null);
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
			mService.setListener(HomeActivity.this);
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
		if (null == ConstantValue.currentMusic)
			return;
		switch (v.getId()) {
		case R.id.home_iv_now_play_song:// 主界面音乐头像
			Intent music = new Intent(HomeActivity.this, MusicActivity.class);
			startActivity(music);
			break;
		case R.id.iv_main_play_pause:// 播放暂停
			LogUtils.d("当前状态:" + ConstantValue.MUSIC_CURRENT_STATE);
			mplay_pause.startAnimation(AnimationUtils.loadAnimation(context, R.anim.icon_translate));
			if (ConstantValue.MUSIC_CURRENT_STATE == ConstantValue.MUSIC_STATE_PLAYING) {// 去暂停
				// 设置标志
				ConstantValue.CURRENT_TAG = ConstantValue.currentMusic.hashCode();
				msg = new PlayMsg(ConstantValue.currentMusic, ConstantValue.MSG_PAUSE, ConstantValue.TYPE_MSG_MUSIC);
				mService.MusicCoreService(msg);
				mplay_pause.setImageResource(R.drawable.music_play_selector);
			} else {// 去播放
				// 设置标志
				ConstantValue.CURRENT_TAG = ConstantValue.currentMusic.hashCode();
				msg = new PlayMsg(ConstantValue.currentMusic, ConstantValue.MSG_CONTINUE, ConstantValue.TYPE_MSG_MUSIC);
				mService.MusicCoreService(msg);
				mplay_pause.setImageResource(R.drawable.music_pause_selector);
			}
			break;
		case R.id.iv_main_next_song:// 下一首
			// 设置标志
			ConstantValue.CURRENT_TAG = ConstantValue.currentMusic.hashCode();
			msg = new PlayMsg(BaseUtils.calcInMusicByMusic(ConstantValue.currentMusic), ConstantValue.MSG_NEXT_SONG, ConstantValue.TYPE_MSG_MUSIC);
			mService.MusicCoreService(msg);
			break;
		case R.id.iv_main_menu:// 菜单
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

	/**
	 * 由于主界面属于特殊界面,所以消息只能自己更新
	 */
	private void UpdateUi() {
		// 更新播放暂停按钮
		if (ConstantValue.MUSIC_CURRENT_STATE == ConstantValue.MUSIC_STATE_PLAYING) {
			mplay_pause.setImageResource(R.drawable.music_pause_selector);
		} else {
			mplay_pause.setImageResource(R.drawable.music_play_selector);
		}
		// 更新歌名和作者
		msongName.setText(ConstantValue.currentMusic.getTitle());
		msongSinger.setText(ConstantValue.currentMusic.getSinger());
		// 更新专辑头像
	}

	@Override
	public void OnStartPlay(Music music) {
		UpdateUi();
	}

}

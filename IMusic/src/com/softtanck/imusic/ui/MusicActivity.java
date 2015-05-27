package com.softtanck.imusic.ui;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.softtanck.imusic.BaseActivity;
import com.softtanck.imusic.ConstantValue;
import com.softtanck.imusic.R;
import com.softtanck.imusic.adapter.HomeContentAdapter;
import com.softtanck.imusic.background.GetBackground;
import com.softtanck.imusic.bean.Music;
import com.softtanck.imusic.bean.PlayMsg;
import com.softtanck.imusic.fragment.LrcFragment;
import com.softtanck.imusic.fragment.SongInfoFragment;
import com.softtanck.imusic.ui.utils.MusicTimer;
import com.softtanck.imusic.utils.BaseUtils;
import com.softtanck.imusic.utils.LogUtils;
import com.softtanck.imusic.view.tools.MyTransFormer;

/**
 * 
 * @Description TODO 音乐播放器主界面,用来显示歌词等信息
 * 
 * @author Tanck
 * 
 * @date Apr 16, 2015 4:24:17 PM
 * 
 */
@SuppressLint({ "HandlerLeak", "DefaultLocale" })
public class MusicActivity extends BaseActivity implements OnPageChangeListener, OnMusicEndPlayListener, OnMusicStartPlayListener {

	/**
	 * 播放界面背景
	 */
	private LinearLayout background;

	/**
	 * 播放界面布局:暂定三个
	 */
	private ViewPager musicPager;

	/**
	 * 歌曲的页面集合
	 */
	private List<Fragment> mList;

	/**
	 * 进度条
	 */
	private SeekBar mPlayBar;

	/**
	 * ViewPager的适配器
	 */
	private HomeContentAdapter hadapter;

	/**
	 * 播放暂停按钮
	 */
	private ImageView mPlay;

	/**
	 * 上一首
	 */
	private ImageView mPre;

	/**
	 * 下一首
	 */
	private ImageView mNext;

	/**
	 * 模式
	 */
	private ImageView mMode;

	/**
	 * 歌曲列表
	 */
	private ImageView mMenu;

	/**
	 * 计时器任务:用于更新歌词
	 */
	private MusicTimer mTimer;

	/**
	 * 当前时间
	 */
	private TextView mCurrentTime;

	/**
	 * 播放消息
	 */
	private PlayMsg msg;

	/**
	 * 消息处理
	 */
	private Handler mhandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (MusicTimer.REFRESH_PROGRESS_EVENT == msg.what) {// 消息类型
				refreshUI(HomeActivity.mService.getMediaPosition(), HomeActivity.mService.getMediaDuration());
			}
		};
	};

	@Override
	protected int getViewId() {
		return R.layout.activity_music;
	}

	/**
	 * 刷新进度
	 * 
	 * @param curTime
	 * @param totalTime
	 */
	@SuppressLint("DefaultLocale")
	private void refreshUI(int curTime, int totalTime) {

		int tempCurTime = curTime;

		curTime /= 1000;
		totalTime /= 1000;
		String curTimeString = calcSpecialTime(curTime, totalTime);

		mCurrentTime.setText(curTimeString);

		mPlayBar.setProgress(calcRate(curTime, totalTime));

		LrcFragment.loadHelper.notifyTime(tempCurTime);
	}

	@Override
	public void onDestroyed() {
		if (null != mTimer)
			mTimer.stopTimer();
	}

	@Override
	protected void onActivityCreate() {

		initAllView();

		initContentView();
		// 加载毛玻璃
		new GetBackground(background, context).execute(R.drawable.tmp_head, 25);

	}

	/**
	 * 初始化所有控件
	 */
	private void initAllView() {

		background = (LinearLayout) findViewById(R.id.music_bg);
		musicPager = (ViewPager) findViewById(R.id.music_content);

		mPlay = (ImageView) findViewById(R.id.music_lrc_play_pause);
		mMode = (ImageView) findViewById(R.id.music_lrc_play_mode);
		mPre = (ImageView) findViewById(R.id.music_lrc_pre_sone);
		mNext = (ImageView) findViewById(R.id.music_lrc_next_song);
		mMenu = (ImageView) findViewById(R.id.music_lrc_menu);

		mPlayBar = (SeekBar) findViewById(R.id.sb_music);
		mCurrentTime = (TextView) findViewById(R.id.music_lrc_current_time);

		// 初始化状态
		updateViewState();

		mMode.setOnClickListener(this);
		mPre.setOnClickListener(this);
		mNext.setOnClickListener(this);
		mMenu.setOnClickListener(this);
		mPlay.setOnClickListener(this);
		// 设置SeekBar的Drag
	}

	/**
	 * 更新控件状态
	 */
	private void updateViewState() {
		if (ConstantValue.MUSIC_CURRENT_STATE == ConstantValue.MUSIC_STATE_PLAYING) {// 正在播放
			// 正在播放
			mPlay.setImageResource(R.drawable.music_pause_selector);
		} else if (ConstantValue.MUSIC_CURRENT_STATE == ConstantValue.MUSIC_STATE_PAUSE) {// 暂停
			refreshUI(HomeActivity.mService.getMediaPosition(), HomeActivity.mService.getMediaDuration());
			// // 更新进度条
			// mPlayBar.setProgress(calcRate(HomeActivity.mService.getMediaPosition(),
			// HomeActivity.mService.getMediaDuration()));
			// // 更新时间
			// mCurrentTime.setText(calcSpecialTime(HomeActivity.mService.getMediaPosition()
			// / 1000, HomeActivity.mService.getMediaDuration() / 1000));
		} else { // 停止
			mPlay.setImageResource(R.drawable.music_play_selector);
			mCurrentTime.setText(null);
			mPlayBar.setProgress(0);
		}
	}

	/**
	 * 初始化主页面内容布局
	 */
	private void initContentView() {
		mList = new ArrayList<Fragment>();
		middleFragment = new SongInfoFragment();
		mList.add(middleFragment);
		middleFragment = new LrcFragment();
		mList.add(middleFragment);
		middleFragment = new SongInfoFragment();
		mList.add(middleFragment);
		// 适配布局
		hadapter = new HomeContentAdapter(fragmentManager, mList);
		musicPager.setAdapter(hadapter);
		musicPager.setPageTransformer(true, new MyTransFormer());
		musicPager.setOffscreenPageLimit(4);
		musicPager.setOnPageChangeListener(this);
		musicPager.setCurrentItem(1);

		HomeActivity.mService.setmStartlistener(this);
		// 更新歌词
		if (ConstantValue.MUSIC_CURRENT_STATE == ConstantValue.MUSIC_STATE_PLAYING) {
			mTimer = new MusicTimer(mhandler);
			mTimer.startTimer();

			// 设置播放完成监听
			HomeActivity.mService.setmEndListener(this);
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.music_lrc_play_pause: // 播放或者暂停
			if (ConstantValue.MUSIC_CURRENT_STATE == ConstantValue.MUSIC_STATE_PLAYING) {// 去暂停
				// 设置标志
				msg = new PlayMsg(ConstantValue.currentMusic, ConstantValue.MSG_PAUSE, ConstantValue.TYPE_MSG_MUSIC);
				HomeActivity.mService.MusicCoreService(msg);
				mPlay.setImageResource(R.drawable.music_play_selector);
				mTimer.stopTimer();// 停止更新歌词
			} else {// 去播放
				// 设置标志
				msg = new PlayMsg(ConstantValue.currentMusic, ConstantValue.MSG_CONTINUE, ConstantValue.TYPE_MSG_MUSIC);
				HomeActivity.mService.MusicCoreService(msg);
				mPlay.setImageResource(R.drawable.music_pause_selector);
				if (null == mTimer) {
					mTimer = new MusicTimer(mhandler);
				}
				mTimer.startTimer();// 重新播放歌词
			}
			break;
		case R.id.music_lrc_play_mode:// 模式

			break;

		case R.id.music_lrc_pre_sone:// 上一首
			// 设置标志
			msg = new PlayMsg(BaseUtils.calcInMusicByPre(ConstantValue.currentMusic), ConstantValue.MSG_NEXT_SONG, ConstantValue.TYPE_MSG_MUSIC);
			HomeActivity.mService.MusicCoreService(msg);
			break;
		case R.id.music_lrc_next_song:// 下一首
			// 设置标志
			msg = new PlayMsg(BaseUtils.calcInMusicByMusicNextMusic(ConstantValue.currentMusic), ConstantValue.MSG_NEXT_SONG, ConstantValue.TYPE_MSG_MUSIC);
			HomeActivity.mService.MusicCoreService(msg);
			break;

		case R.id.music_lrc_menu:// 播放列表菜单
			Intent palyQuen = new Intent(MusicActivity.this, PlayQueueActivity.class);
			// startActivityForResult(palyQuen, 0x1);// 屏蔽动画
			// Call immediately after one of the flavors of
			// startActivity(Intent) or finish() to specify an explicit
			startActivity(palyQuen);
			overridePendingTransition(R.anim.music_ui_in, R.anim.music_ui_out);
			break;
		}
	}

	@Override
	public void OnMusicEndplay() {
		if (null != mTimer)
			mTimer.stopTimer();
		// 更新当前UI.
		if (null != mPlay) {
			updateViewState();
		}
	}

	/**
	 * 计算进度比例
	 * 
	 * @param currentTime
	 * @param totalTime
	 * @return
	 */
	private int calcRate(int currentTime, int totalTime) {
		int rate = 0;
		if (totalTime != 0) {
			rate = (int) ((float) currentTime / totalTime * 100);
		}
		return rate;
	}

	/**
	 * 计算特殊时间 00:00/00:00
	 * 
	 * @param curTime
	 *            /1000
	 * @param totalTime
	 *            /1000
	 * @return
	 */
	private String calcSpecialTime(int curTime, int totalTime) {
		int curminute = curTime / 60;
		int cursecond = curTime % 60;
		int curminute_ = totalTime / 60;
		int cursecond_ = totalTime % 60;

		String curTimeString = String.format("%02d:%02d", curminute, cursecond) + "/" + String.format("%02d:%02d", curminute_, cursecond_);
		return curTimeString;
	}

	/**
	 * 音乐开始播放的时候刷新UI.
	 */
	@Override
	public void OnStartPlay(Music music) {

		// 刷新歌词界面.
		refreshUI(HomeActivity.mService.getMediaPosition(), HomeActivity.mService.getMediaDuration());
	}
}

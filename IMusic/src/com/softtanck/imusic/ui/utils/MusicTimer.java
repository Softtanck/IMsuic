package com.softtanck.imusic.ui.utils;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;
import android.os.Message;

/**
 * 
 * @Description TODO 一个定时器，控制歌曲播放进度
 * 
 * @author Tanck
 * 
 * @date May 24, 2015 3:42:50 PM
 * 
 */
public class MusicTimer {

	/**
	 * 刷新常量
	 */
	public final static int REFRESH_PROGRESS_EVENT = 0x100;

	private static final int INTERVAL_TIME = 1000;
	private Handler[] mHandler;
	private Timer mTimer;
	private TimerTask mTimerTask;

	private int what;
	private boolean mTimerStart = false;

	public MusicTimer(Handler... handler) {
		this.mHandler = handler;
		this.what = REFRESH_PROGRESS_EVENT;

		mTimer = new Timer();
	}

	/**
	 * 开始计时
	 */
	public void startTimer() {
		if (mHandler == null || mTimerStart) {
			return;
		}
		mTimerTask = new MyTimerTask();
		mTimer.schedule(mTimerTask, INTERVAL_TIME, INTERVAL_TIME);
		mTimerStart = true;
	}

	/**
	 * 停止计时
	 */
	public void stopTimer() {
		if (!mTimerStart) {
			return;
		}
		mTimerStart = false;
		if (mTimerTask != null) {
			mTimerTask.cancel();
			mTimerTask = null;
		}
	}

	/**
	 * 
	 * @Description TODO 发送消息定时任务
	 * 
	 * @author Tanck
	 * 
	 * @date May 24, 2015 3:43:26 PM
	 * 
	 */
	class MyTimerTask extends TimerTask {

		@Override
		public void run() {
			if (mHandler != null) {
				for (Handler handler : mHandler) {
					Message msg = handler.obtainMessage(what);
					msg.sendToTarget();
				}
			}
		}

	}
}

package com.softtanck.imusic.service;

import com.softtanck.imusic.bean.PlayMsg;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

/**
 * 
 * @Description TODO 音乐播放服务器.[用于发送更新歌词和播放音乐]
 * 
 * @author Tanck
 * 
 * @date May 7, 2015 11:10:03 AM
 * 
 */
public class PlayService extends Service {

	/**
	 * 音乐播放器
	 */
	private MediaPlayer mplayer;

	/**
	 * 本地服务对象
	 */
	private IBinder mBinder = new LocalBinder(PlayService.this);

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mplayer = new MediaPlayer();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		if (mplayer.isPlaying()) {
			mplayer.stop();
			mplayer.release();
			mplayer = null;
		}
		super.onDestroy();
	}
}

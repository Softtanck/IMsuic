package com.softtanck.imusic.service;

import com.softtanck.imusic.ConstantValue;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.IBinder;

/**
 * 
 * @Description TODO 音乐播放服务
 * 
 * @author Tanck
 * 
 * @date Apr 17, 2015 1:25:35 PM
 * 
 */
public class PlayService extends Service implements OnCompletionListener,
		OnPreparedListener {

	/**
	 * 播放器
	 */
	private MediaPlayer mPlayer;

	/**
	 * 播放消息接收者
	 */
	private MsgReceiver receiver;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mPlayer = new MediaPlayer();
		mPlayer.setOnPreparedListener(this);
		mPlayer.setOnCompletionListener(this);

		// 注册播放服务器的接口
		receiver = new MsgReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(ConstantValue.UPDATE_STATE);
		registerReceiver(receiver, filter);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// List<LrcContent> mLrcList = new ArrayList<LrcContent>();
		// LogUtils.d("----------");
		// for (int i = 0; i < 20; i++) {
		// LrcContent lrcContent = new LrcContent();
		// lrcContent.setLrcStr("---------" + i);
		// mLrcList.add(lrcContent);
		// }
		// LrcFragment.lrcView.setmLrcList(mLrcList);
		return super.onStartCommand(intent, flags, startId);
	}

	private void play() {

	}

	/**
	 * 当一首歌播放完成后CallBack
	 * 
	 * @param mp
	 */
	@Override
	public void onCompletion(MediaPlayer mp) {

	}

	/**
	 * 准备播放的时候
	 * 
	 * @param mp
	 */
	@Override
	public void onPrepared(MediaPlayer mp) {

	}

	/**
	 * 
	 * @Description TODO 播放消息接收者
	 * 
	 * @author Tanck
	 * 
	 * @date Apr 17, 2015 5:04:57 PM
	 * 
	 */
	private class MsgReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {

		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiver);
		receiver = null;
	}

}

package com.softtanck.imusic.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;

import com.softtanck.imusic.ConstantValue;
import com.softtanck.imusic.HandlerMessageContainer;
import com.softtanck.imusic.R;
import com.softtanck.imusic.bean.Music;
import com.softtanck.imusic.bean.PlayMsg;
import com.softtanck.imusic.ui.HomeActivity;
import com.softtanck.imusic.utils.BaseUtils;

/**
 * 
 * @Description TODO 音乐播放服务器.[用于发送更新歌词和播放音乐]
 * 
 * @author Tanck
 * 
 * @date May 7, 2015 11:10:03 AM
 * 
 */
public class PlayService extends Service implements OnCompletionListener {

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
		mplayer.setOnCompletionListener(this);// 设置播放完成监听.
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

	@Override
	public void onCompletion(MediaPlayer mp) {
		mp.reset();
		ConstantValue.MUSIC_CURRENT_STATE = ConstantValue.MUSIC_STATE_STOP;
		if (null != HomeActivity.mplay_pause) {// 更新UI
			HomeActivity.mplay_pause.setImageResource(R.drawable.music_play_selector);
		}
	}

	/**
	 * 音乐播放器总的消息分发处理
	 * 
	 * @param msg
	 */
	public void MusicCoreService(PlayMsg msg) {
		switch (msg.getMsgType()) {
		case ConstantValue.TYPE_MSG_MUSIC:// 音乐类型
			int userIntent = msg.getMsgIntent();
			Music music = msg.getMusic();
			switch (userIntent) {
			case ConstantValue.MSG_PLAY: // 播放
				play(music);
				break;
			case ConstantValue.MSG_PAUSE: // 暂停
				pause(music);
				break;
			case ConstantValue.MSG_NEXT_SONG: // 下一首
				next(music);
				break;
			case ConstantValue.MSG_PRE_SONG: // 上一首
				pre(music);
				break;
			case ConstantValue.MSG_SEQUENCE:// 顺序播放

				// TODO 顺序播放
				break;
			case ConstantValue.MSG_RANDM_SONG:// 随机播放

				// TODO 随机播放
				break;

			case ConstantValue.MSG_CIRCLE:// 循环播放

				// TODO 循环播放
				break;
			}

			// 发送消息,为了可维护性.
			sendMsgToUi(music, ConstantValue.TYPE_MSG_MUSIC);

			break;
		default:
			break;
		}

	}

	/**
	 * 发送消息
	 * 
	 * @param music
	 *            音乐实体
	 * @param typeMsgMusic
	 *            消息类型
	 */
	private void sendMsgToUi(Music music, int typeMsgMusic) {
		Message senMessage = new Message();
		senMessage.arg1 = typeMsgMusic;// 音乐类型
		senMessage.what = music.hashCode(); // handler更新标志
		Bundle data = new Bundle();
		data.putSerializable(ConstantValue.MUSIC_CURRENT_OBJECT, music);
		senMessage.setData(data);
		HandlerMessageContainer.sendAllMessage(senMessage);
	}

	/**
	 * 播放
	 * 
	 * @param music
	 */
	private void play(Music music) {
		try {
			if (mplayer.isPlaying()) {
				mplayer.stop();
			}
			// 非暂停状态
			if (ConstantValue.MUSIC_CURRENT_STATE != ConstantValue.MUSIC_STATE_PAUSE) {
				mplayer.reset();
				mplayer.setDataSource(music.getFileUrl());
				mplayer.prepare();
			}
			mplayer.start();

			ConstantValue.currentMusicPostion = BaseUtils.calcInMusicPosition(music) - 1;
			ConstantValue.currentMusic = music;
			ConstantValue.MUSIC_CURRENT_STATE = ConstantValue.MUSIC_STATE_PLAYING;// 设置状态为播放
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 暂停
	 * 
	 * @param music
	 */
	private void pause(Music music) {
		if (mplayer.isPlaying()) {
			mplayer.pause();
			ConstantValue.MUSIC_CURRENT_STATE = ConstantValue.MUSIC_STATE_PAUSE;// 设置状态为暂停
		}
	}

	/**
	 * 下一首
	 * 
	 * @param music
	 */
	private void next(Music music) {
		ConstantValue.MUSIC_CURRENT_STATE = ConstantValue.MUSIC_STATE_PLAYING;
		play(music);
	}

	/**
	 * 上一首
	 * 
	 * @param music
	 */
	private void pre(Music music) {
		ConstantValue.MUSIC_CURRENT_STATE = ConstantValue.MUSIC_STATE_PLAYING;
		play(music);
	}

}

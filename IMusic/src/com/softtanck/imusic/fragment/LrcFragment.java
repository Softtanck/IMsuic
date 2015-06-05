package com.softtanck.imusic.fragment;

import java.util.List;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.TextView;

import com.softtanck.imusic.BaseFragment;
import com.softtanck.imusic.ConstantValue;
import com.softtanck.imusic.R;
import com.softtanck.imusic.adapter.LyricAdapter;
import com.softtanck.imusic.bean.LyricSentence;
import com.softtanck.imusic.bean.Music;
import com.softtanck.imusic.lrc.LyricLoadHelper;
import com.softtanck.imusic.lrc.LyricLoadHelper.LyricListener;
import com.softtanck.imusic.service.message.HandlerMessageListener;
import com.softtanck.imusic.ui.HomeActivity;
import com.softtanck.imusic.ui.OnMusicStartPlayListener;
import com.softtanck.imusic.utils.LogUtils;

/**
 * 
 * @Description TODO 歌词界面
 * 
 * @author Tanck
 * 
 * @date Apr 16, 2015 5:59:12 PM
 * 
 */
@SuppressLint("NewApi")
public class LrcFragment extends BaseFragment implements HandlerMessageListener, LyricListener, OnMusicStartPlayListener {

	/**
	 * 歌词布局
	 */
	// public static LrcView lrcView;

	/**
	 * 新的歌词布局
	 */
	private ListView mlrcView;

	/**
	 * 歌词适配器
	 */
	public static LyricAdapter adapter;

	/**
	 * 歌词加载帮助类
	 */
	public static LyricLoadHelper loadHelper;

	/**
	 * 歌曲标题
	 */
	public static TextView mMusicTitle;

	/**
	 * 空
	 */
	public static TextView mEmpty;

	@Override
	public void onAttached() {
	}

	@Override
	public void onDeatch() {
	}

	@Override
	public void handlerMessage(Message msg) {

		// if (MusicTimer.REFRESH_PROGRESS_EVENT == msg.what) {// 消息类型
		// LogUtils.d("msg:"+HomeActivity.mService.getMediaPosition());
		// refreshSeekProgress(HomeActivity.mService.getMediaPosition(),
		// HomeActivity.mService.getMediaDuration());
		// }

	}

	@Override
	public int getLayoutView() {
		return R.layout.fragment_music_lrc;
	}

	@Override
	public void onViewCreate(View view, Bundle savedInstanceState) {

		initView(view);

		mhandler.setListener(this);

	}

	/**
	 * 初始化布局
	 */
	private void initView(View view) {
		// lrcView = (LrcView) view.findViewById(R.id.music_lrc);

		HomeActivity.mService.setmStartlistener(this);
		mlrcView = (ListView) view.findViewById(R.id.lv_music_lrc);

		mMusicTitle = (TextView) view.findViewById(R.id.music_lrc_fg_musictitle);
		mEmpty = (TextView) view.findViewById(R.id.music_lrc_empty);

		initLrc();

	}

	/**
	 * 初始化歌词
	 */
	private void initLrc() {

		if (null != ConstantValue.currentMusic) {
			mMusicTitle.setText(ConstantValue.currentMusic.getTitle());
			loadHelper = new LyricLoadHelper();
			adapter = new LyricAdapter(context);
			loadHelper.setLyricListener(this);
			mlrcView.setAdapter(adapter);
			mlrcView.setEmptyView(mEmpty);
			mlrcView.startAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_in));
			loadHelper.loadLyric("/storage/sdcard0/Music/" + "jx" + ".lrc"); // 加载歌词路径
		}
	}

	@Override
	protected void getservciesData() {

	}

	@Override
	public void onLyricLoaded(List<LyricSentence> lyricSentences, int indexOfCurSentence) {
		adapter.setLyric(lyricSentences);
		adapter.setCurrentSentenceIndex(indexOfCurSentence);
		adapter.notifyDataSetChanged();
		if (ConstantValue.MUSIC_CURRENT_STATE == ConstantValue.MUSIC_STATE_PAUSE) {// 暂停状态
			loadHelper.notifyTime(HomeActivity.mService.getMediaPosition());
		}
	}

	@Override
	public void onLyricSentenceChanged(int indexOfCurSentence) {
		adapter.setCurrentSentenceIndex(indexOfCurSentence);
		adapter.notifyDataSetChanged();
		mlrcView.smoothScrollToPositionFromTop(indexOfCurSentence, mlrcView.getHeight() / 2, 500);
	}

	/**
	 * 音乐开始播放的时候
	 * 
	 * @param music
	 */
	@Override
	public void OnStartPlay(Music music) {
		initLrc();
	}

}

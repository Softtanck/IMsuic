package com.softtanck.imusic.ui;

import com.softtanck.imusic.bean.Music;

/**
 * 
 * @Description TODO 当音乐开始播放的监听
 * 
 * @author Tanck
 * 
 * @date May 13, 2015 5:06:26 PM
 * 
 */
public interface OnMusicStartPlayListener {

	/**
	 * 当音乐开始播放回调
	 * 
	 * @param music
	 */
	public void OnStartPlay(Music music);

}

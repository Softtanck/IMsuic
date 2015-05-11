package com.softtanck.imusic.fragment;

import com.softtanck.imusic.bean.Music;

/**
 * 
 * @Description TODO 当音乐开始播放的时候
 * 
 * @author Tanck
 * 
 * @date May 11, 2015 11:20:48 AM
 * 
 */
public interface OnPlayAnimListener {
	/**
	 * 播放
	 * 
	 * @param music
	 */
	public void OnAnimStarted(Music music);

	/**
	 * 结束
	 * 
	 * @param music
	 */
	public void OnAnimEnded(Music music);
}

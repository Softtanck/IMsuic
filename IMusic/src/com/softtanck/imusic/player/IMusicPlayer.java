package com.softtanck.imusic.player;

/**
 * 
 * @Description TODO 音乐播放器播放类
 * 
 * @author Tanck
 * 
 * @date May 11, 2015 11:04:19 AM
 * 
 */
public class IMusicPlayer {

	private static IMusicPlayer musicPlayer;

	public static IMusicPlayer getInstance() {
		if (null == musicPlayer) {
			musicPlayer = new IMusicPlayer();
		}
		return musicPlayer;
	}

	private void IMusicPlayer() {

	}

}

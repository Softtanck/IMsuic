package com.softtanck.imusic.service;

import android.os.Binder;

/**
 * 
 * @Description TODO 返回Client Service对象
 * 
 * @author Tanck
 * 
 * @date May 7, 2015 11:13:22 AM
 * 
 */
public class LocalBinder extends Binder {

	/**
	 * 播放音乐服务
	 */
	private PlayService playService;

	public LocalBinder(PlayService playService) {
		this.playService = playService;
	}

	/**
	 * 获取音乐服务
	 * 
	 * @return
	 */
	public PlayService getService() {
		return playService;
	}
}
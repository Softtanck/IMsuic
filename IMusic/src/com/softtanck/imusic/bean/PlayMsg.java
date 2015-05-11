package com.softtanck.imusic.bean;

import com.softtanck.imusic.BaseMessage;

/**
 * 
 * @Description TODO 播放消息
 * 
 * @author Tanck
 * 
 * @date May 7, 2015 2:22:56 PM
 * 
 */
public class PlayMsg extends BaseMessage {

	/**
	 * 音乐实体
	 */
	private Music music;

	/**
	 * 消息意图
	 */
	private int msgIntent;

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}

	public int getMsgIntent() {
		return msgIntent;
	}

	public void setMsgIntent(int msgIntent) {
		this.msgIntent = msgIntent;
	}

	public PlayMsg(){
		
	}
	
	/**
	 * 
	 * @param music
	 *            音乐实体
	 * @param msgIntent
	 *            播放意图
	 */
	public PlayMsg(Music music, int msgIntent, int msgType) {
		super(msgType);
		this.music = music;
		this.msgIntent = msgIntent;
	}

}

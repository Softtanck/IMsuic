package com.softtanck.imusic.bean;

/**
 * 
 * @Description TODO 歌词
 * 
 * @author Tanck
 * 
 * @date Apr 17, 2015 12:54:53 PM
 * 
 */
public class LrcContent {
	private String lrcStr; // 歌词内容
	private int lrcTime; // 歌词当前时间

	public String getLrcStr() {
		return lrcStr;
	}

	public void setLrcStr(String lrcStr) {
		this.lrcStr = lrcStr;
	}

	public int getLrcTime() {
		return lrcTime;
	}

	public void setLrcTime(int lrcTime) {
		this.lrcTime = lrcTime;
	}
}
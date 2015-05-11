package com.softtanck.imusic;

/**
 * 
 * @Description TODO 播放消息父类
 * 
 * @author Tanck
 * 
 * @date May 7, 2015 11:34:42 AM
 * 
 */
public class BaseMessage {

	/**
	 * 消息类型
	 */
	private int msgType;

	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	public BaseMessage() {
	}

	public BaseMessage(int msgType) {
		this.msgType = msgType;
	}
}

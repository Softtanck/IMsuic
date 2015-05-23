package com.softtanck.imusic.service.message;

import android.os.Handler;
import android.os.Message;

/**
 * 
 * @Description TODO 消息接收者
 * 
 * @author Tanck
 * 
 * @date May 11, 2015 4:41:35 PM
 * 
 */
public class MyHandler extends Handler {

	/**
	 * 消息监听
	 */
	private HandlerMessageListener listener;

	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		if (null != listener) {
			listener.handlerMessage(msg);
		}
	}

	public void setListener(HandlerMessageListener listener) {
		this.listener = listener;
	}

}

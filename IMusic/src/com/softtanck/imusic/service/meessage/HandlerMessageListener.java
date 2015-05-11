package com.softtanck.imusic.service.meessage;

import android.os.Message;

/**
 * 
 * @Description TODO 消息处理接口
 * 
 * @author Tanck
 * 
 * @date May 11, 2015 4:42:30 PM
 * 
 */
public interface HandlerMessageListener {

	/**
	 * 处理消息回调
	 * 
	 * @param msg
	 */
	public void handlerMessage(Message msg);

}

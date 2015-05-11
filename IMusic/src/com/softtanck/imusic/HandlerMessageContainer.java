package com.softtanck.imusic;

import java.util.ArrayList;
import java.util.List;

import com.softtanck.imusic.utils.LogUtils;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

/**
 * 
 * @Description TODO handle管理
 * 
 * @author Tanck
 * 
 * @date May 11, 2015 4:21:04 PM
 * 
 */
public class HandlerMessageContainer {

	/** 消息列表容器 */
	public static List<Handler> handlers = new ArrayList<Handler>();

	/**
	 * 添加一个handler
	 * 
	 * @param handler
	 */
	public static void addHandler(Handler handler) {
		if (!handlers.contains(handler)) {
			handlers.add(handler);
		}
	}

	/**
	 * 删除handler
	 * 
	 * @param handler
	 */
	public static void removeHandler(Handler handler) {
		handlers.remove(handler);
	}

	/**
	 * 向所有的注册的发送消息
	 * 
	 * @param msg
	 */
	public static void sendAllMessage(Message msg) {
		for (Handler mhHandler : handlers) {
			mhHandler.handleMessage(msg);
		}
	}
}

package com.softtanck.imusic;

import java.util.ArrayList;
import java.util.List;

import com.softtanck.imusic.bean.Music;

/**
 * 
 * @Description TODO 存放应用程序全局变量
 * 
 * @author Tanck
 * 
 * @date Apr 14, 2015 9:09:25 PM
 * 
 */
public class ConstantValue {

	public interface App {
		/**
		 * 存放音乐播放配置
		 */
		String SHARENAME_CONFIG = "IMsuic_Config";

	}

	/**
	 * 屏幕宽度
	 */
	public static int WINDOW_WIDTH = 0;

	/**
	 * 屏幕高度
	 */
	public static int WINDOW_HIGHT = 0;

	/**
	 * 当前消息标志
	 */
	public static int CURRENT_TAG = 0;

	/**
	 * 更新播放器状态
	 */
	public static final String UPDATE_STATE = "com.softtanck.imusic.UPDATE_STATE";

	/******************* 播放相关常量 ***********************/

	/**
	 * 播放
	 */
	public static final int MSG_PLAY = 0;

	/**
	 * 暂停
	 */
	public static final int MSG_PAUSE = 1;

	/**
	 * 停止
	 */
	public static final int MSG_STOP = 2;

	/**
	 * 下一首
	 */
	public static final int MSG_NEXT_SONG = 3;

	/**
	 * 上一首
	 */
	public static final int MSG_PRE_SONG = 4;

	/**
	 * 随机
	 */
	public static final int MSG_RANDM_SONG = 5;

	/**
	 * 顺序播放
	 */
	public static final int MSG_SEQUENCE = 6;

	/**
	 * 循环
	 */
	public static final int MSG_CIRCLE = 7;

	/**
	 * 继续播放
	 */
	public static final int MSG_CONTINUE = 12;

	/**
	 * 准备
	 */
	public static final int MSG_PREPARE = 11;

	/*********************** 传递类 ***********************/

	/**
	 * 更新歌词
	 */
	public static final int MSG_UPDATE_LRC = 8;

	/**
	 * 更新图标
	 */
	public static final int MSG_UPDATE_ICON = 9;

	/**
	 * 音乐相关
	 */
	public static final int TYPE_MSG_MUSIC = 10;

	// TODO Other msg;

	/********************** 音乐状态 **************************/

	/**
	 * 音乐当前状态
	 */
	public static int MUSIC_CURRENT_STATE = 0;

	/**
	 * 音乐当前播放对象
	 */
	public static String MUSIC_CURRENT_OBJECT = "";

	/**
	 * 正在播放
	 */
	public static final int MUSIC_STATE_PLAYING = 1;

	/**
	 * 暂停
	 */
	public static final int MUSIC_STATE_PAUSE = 2;

	/**
	 * 停止
	 */
	public static final int MUSIC_STATE_STOP = 3;

	// TODO Other state;

	/**
	 * 当前正在播放的音乐对象
	 */
	public static Music currentMusic;

	/**
	 * 当前播放音乐的位置
	 */
	public static int currentMusicPostion = 0;

	/**
	 * 本地音乐集合
	 */
	public static List<Music> mlocalMusics = new ArrayList<Music>();

}

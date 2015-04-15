package com.softtanck.imusic;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

/**
 * 
 * 
 * @Description Activity容器管理
 * 
 * @author Tanck
 * 
 * @date Jan 19, 2015 11:05:47 AM
 * 
 * 
 */
public class ActivityContainer {

	/** 活动列表容器 */
	public static List<Activity> activities = new ArrayList<Activity>();

	/**
	 * 添加活动
	 * 
	 * @param activity
	 */
	public static void addActivity(Activity activity) {
		if (!activities.contains(activity)) {
			activities.add(activity);
		}
	}

	/**
	 * 删除活动
	 * 
	 * @param activity
	 */
	private static void removeActivity(Activity activity) {
		activities.remove(activity);
	}

	/**
	 * 结束指定活动
	 * 
	 * @param activity
	 */
	public static void finishActivity(Activity activity) {
		if (activities.contains(activity)) {
			activity.finish();
			removeActivity(activity);
		}
	}

	/**
	 * 活动大小
	 * 
	 * @return
	 */
	public static int size() {
		return activities.size();
	}

	/**
	 * 结束所有活动
	 */
	public static void finishAll() {
		for (Activity activity : activities) {
			if (!activity.isFinishing())
				activity.finish();
		}
		System.exit(0);
	}

}

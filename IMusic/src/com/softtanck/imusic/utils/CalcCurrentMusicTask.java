package com.softtanck.imusic.utils;

import com.softtanck.imusic.bean.Music;

import android.os.AsyncTask;

/**
 * 
 * @Description TODO 计算相关任务
 * 
 * @author Tanck
 * 
 * @date May 13, 2015 4:26:14 PM
 * 
 */
public class CalcCurrentMusicTask extends AsyncTask<Music, Void, Music> {

	@Override
	protected Music doInBackground(Music... params) {
		return BaseUtils.calcInMusicByMusic(params[0]);
	}
}

package com.softtanck.imusic.utils;

import com.softtanck.imusic.ConstantValue;
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
public class CalcCurrentMusicPositionTask extends AsyncTask<Music, Void, Integer> {

	@Override
	protected Integer doInBackground(Music... params) {
		ConstantValue.currentMusicPostion = BaseUtils.calcInMusicPosition(params[0]);
		return 0;
	}
}

package com.softtanck.imusic.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.softtanck.imusic.bean.Music;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

/**
 * 
 * @Description TODO 音乐工具类
 * 
 * @author Tanck
 * 
 * @date Apr 17, 2015 1:35:02 PM
 * 
 */
public class MusicUtil {

	/**
	 * 获取sd卡所有的音乐文件
	 * 
	 * @param context
	 *            上下文
	 * @return 如果null 证明没有记录
	 */
	public static List<Music> getAllMusic(Context context) {

		/**
		 * 歌曲列表
		 */
		List<Music> mList = null;

		Cursor cursor = context.getContentResolver().query(
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
				new String[] { MediaStore.Audio.Media._ID,
						MediaStore.Audio.Media.DISPLAY_NAME,
						MediaStore.Audio.Media.TITLE,
						MediaStore.Audio.Media.DURATION,
						MediaStore.Audio.Media.ARTIST,
						MediaStore.Audio.Media.ALBUM,
						MediaStore.Audio.Media.YEAR,
						MediaStore.Audio.Media.MIME_TYPE,
						MediaStore.Audio.Media.SIZE,
						MediaStore.Audio.Media.DATA },
				MediaStore.Audio.Media.MIME_TYPE + "=? or "
						+ MediaStore.Audio.Media.MIME_TYPE + "=?",
				new String[] { "audio/mpeg", "audio/x-ms-wma" }, null);

		// 是否有记录
		if (cursor.moveToFirst()) {
			mList = new ArrayList<Music>();
			Music mic = null;
			do {
				mic = new Music();
				// 文件名
				mic.setFileName(cursor.getString(1));
				// 歌曲名
				mic.setTitle(cursor.getString(2));
				// 时长
				mic.setDuration(cursor.getInt(3));
				// 歌手名
				mic.setSinger(cursor.getString(4));
				// 专辑名
				mic.setAlbum(cursor.getString(5));

				// 发行时间--有可能有些没有
				if (cursor.getString(6) != null) {
					mic.setYear(cursor.getString(6));
				} else {
					mic.setYear("未知");
				}

				// 歌曲格式--MediaStore.Audio.Media.MIME_TYPE,
				if ("audio/mpeg".equals(cursor.getString(7).trim())) {
					mic.setType("mp3");
				} else if ("audio/x-ms-wma".equals(cursor.getString(7).trim())) {
					mic.setType("wma");
				}

				// 文件大小
				if (cursor.getString(8) != null) {
					float size = cursor.getInt(8) / 1024f / 1024f;
					mic.setSize((size + "").substring(0, 4) + "M");
				} else {
					mic.setSize("未知");
				}
				// 文件路径
				if (cursor.getString(9) != null) {
					mic.setFileUrl(cursor.getString(9));
				}
				mList.add(mic);

			} while (cursor.moveToNext());
			// 千万别忘记关闭游标
			cursor.close();
		}
		return mList;
	}

}

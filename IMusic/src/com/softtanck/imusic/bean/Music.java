package com.softtanck.imusic.bean;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @Description TODO
 * 
 * @author Tanck
 * 
 * @date Apr 17, 2015 1:32:22 PM
 * 
 */
@SuppressLint("SimpleDateFormat")
public class Music {

	private String fileName; // 文件名
	private String title; // 歌曲名
	private int duration; // 歌曲的播放时长
	private String singer; // 歌手
	private String album;// 专辑名
	private String year;// 发行时间
	private String type; // 格式
	private String size;// 歌曲大小
	private String fileUrl;// 路径
	private String time; // 转换后歌曲时长 格式 mm:ss

	public Music(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName.substring(0, fileName.length() - 4);

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		// duration=duration/1000/60;

		this.duration = duration;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getTime() {
		Date data = new Date(duration);
		SimpleDateFormat smp = new SimpleDateFormat("mm:ss");
		String str = smp.format(data);
		return str;
	}

	public Music() {
		super();
	}

	public Music(String fileName, String title, int duration, String singer, String album, String year, String type, String size, String fileUrl) {
		super();
		this.fileName = fileName;
		this.title = title;
		this.duration = duration;
		this.singer = singer;
		this.album = album;
		this.year = year;
		this.type = type;
		this.size = size;
		this.fileUrl = fileUrl;
	}

}

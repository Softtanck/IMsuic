package com.softtanck.imusic.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.softtanck.imusic.R;
import com.softtanck.imusic.bean.Music;
import com.softtanck.imusic.ui.HomeActivity;
import com.softtanck.imusic.utils.LogUtils;

/**
 * 
 * @Description TODO 本地音乐加载视图
 * 
 * @author Tanck
 * 
 * @date May 8, 2015 2:32:08 PM
 * 
 */
public class LocalMusicAdapter extends BaseAdapter {

	private Context context;

	private List<Music> list;

	private String UNKNOW = "<unknown>";

	public LocalMusicAdapter(Context context, List<Music> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
		if (null == convertView) {

			holder = new ViewHolder();

			convertView = View.inflate(context, R.layout.local_music_item, null);

			holder.musicName = (TextView) convertView.findViewById(R.id.tv_local_music_name);

			holder.musicSingger = (TextView) convertView.findViewById(R.id.tv_local_music_singer);

			holder.ablumView = (ImageView) convertView.findViewById(R.id.iv_local_music_ablumimg);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		try {
			holder.musicName.setText(list.get(position).getTitle());

			String tempString = list.get(position).getSinger();

			if (UNKNOW.equals(tempString))
				tempString = "未知";

			holder.musicSingger.setText(list.get(position).getTime());
		} catch (Exception e) {
			((HomeActivity) context).showToast("您的程序遇到一点问题:" + e.getMessage());
		}

		return convertView;
	}

	class ViewHolder {
		TextView musicName;// 音乐名字
		ImageView ablumView;// 音乐专辑图片
		TextView musicSingger;// 音乐歌手名字
	}

}

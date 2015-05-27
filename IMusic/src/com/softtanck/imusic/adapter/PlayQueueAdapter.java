package com.softtanck.imusic.adapter;

import java.util.List;

import com.softtanck.imusic.R;
import com.softtanck.imusic.bean.Music;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 
 * @Description TODO 播放队列适配器
 * 
 * @author Tanck
 * 
 * @date May 27, 2015 6:15:45 PM
 * 
 */
public class PlayQueueAdapter extends BaseAdapter {
	private Context context;

	private List<Music> mList;

	public PlayQueueAdapter(Context context, List<Music> list) {
		this.context = context;
		this.mList = list;
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int arg0) {
		return mList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
		if (null == convertView) {

			holder = new ViewHolder();

			convertView = View.inflate(context, R.layout.music_paly_queue_item, null);

			holder.tvNumber = (TextView) convertView.findViewById(R.id.tv_play_queue_number);
			holder.tvAuthor = (TextView) convertView.findViewById(R.id.tv_play_queue_author);
			holder.tvName = (TextView) convertView.findViewById(R.id.tv_play_queue_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tvAuthor.setText(mList.get(position).getSinger());

		holder.tvName.setText(mList.get(position).getTitle());

		holder.tvNumber.setText(String.valueOf(position));

		return convertView;
	}

	class ViewHolder {
		TextView tvName;// 歌曲名字
		TextView tvAuthor;// 作者名字
		TextView tvNumber;// 序号
	}

}

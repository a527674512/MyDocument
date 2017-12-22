package com.example.lhf.mydocument.activity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class MyBaseAdapter extends BaseAdapter {
	protected Context mContext;
	protected int mResource;
	protected int[] mResIds;

	/**
	 * 初始化Adapter
	 * @param context 与MyBaseAdapter运行时要显示的View关联的Context
	 * @param resource 布局文件的资源标识，该布局文件用于定义列表项 这个布局文件应该至少包含resIds中的view资源
	 * @param resIds 需要获取引用的view id
	 */
	public MyBaseAdapter(Context context, int resource, int[] resIds) {
		this.mContext = context;
		this.mResource = resource;
		this.mResIds = resIds;
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		if(null == convertView){
			convertView = newView();
		}
		bindView(position, convertView);
		return convertView;
	}

	private View newView(){
		View layout = LayoutInflater.from(mContext).inflate(mResource, null);
		for (int i = 0; i < mResIds.length; i++) {
			View view = layout.findViewById(mResIds[i]);
			Log.d("TAG", "newView" + view.getClass().getName() + " " + mResIds[i]);
			layout.setTag(mResIds[i], view);
		}
		return layout;
	}

	public abstract void bindView(int position, View view);
}

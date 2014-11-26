package com.course_design.adapter;

import java.util.List;


import com.course_design.activity.R;
import com.course_design.object.Cache;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CacheAdapter_Full_Cast extends BaseAdapter {

	private Cache one_cache;
	private List<Cache> cache_list;
	private Context mContext;
	private int  TEXT_COLOR=0xff000000;
	public CacheAdapter_Full_Cast(Context mContext,List<Cache> cache_list)
	{
		 this.cache_list=cache_list;
	     this.mContext=mContext;	 
	}
	public int getCount() 
	{
		return (this.cache_list.size());
	}

	public Object getItem(int position) 
	{
		return (this.getItem(position));
	}

	public long getItemId(int pos)
	{
		return (pos);
	}

	public View getView(int position, View contentView, ViewGroup parent) 
	{
		this.one_cache=this.cache_list.get(position);
		ViewHolder viewholder=null;
		if(contentView==null)
		{
		    contentView=LayoutInflater.from(mContext).inflate(R.layout.cache_item, null);
		    viewholder=new ViewHolder();
			viewholder.memory_tag=(TextView)contentView.findViewById(R.id.memory_tag);
			viewholder.cache_arr_num=(TextView)contentView.findViewById(R.id.cache_arr_num);
			viewholder.cache_item_layout=(LinearLayout)contentView.findViewById(R.id.cache_layout);
			viewholder.memory_tag.setText("主存标记:"+this.one_cache.getMemory_Tag()+"");
			viewholder.memory_tag.setTextColor(TEXT_COLOR);
			viewholder.cache_arr_num.setVisibility(View.GONE);
			viewholder.cache_item_layout.setBackgroundResource(R.drawable.cache_memory_layout1);
		    contentView.setTag(viewholder);
		}
		else
		{
			viewholder=(ViewHolder)contentView.getTag();
			viewholder.memory_tag.setText("主存标记:"+this.one_cache.getMemory_Tag()+"");
			viewholder.cache_arr_num.setVisibility(View.GONE);
			viewholder.memory_tag.setTextColor(TEXT_COLOR);
			viewholder.cache_item_layout.setBackgroundResource(R.drawable.cache_memory_layout1);
		}
			
		return contentView;
	}

	 private class ViewHolder
	 {
		    TextView memory_tag;
		    TextView cache_arr_num;
		    LinearLayout cache_item_layout;
	 };
	private void Bind_ID(ViewHolder viewholder,View contentView)
	{
		  viewholder.memory_tag=(TextView)contentView.findViewById(R.id.memory_tag);
		  viewholder.cache_arr_num=(TextView)contentView.findViewById(R.id.cache_arr_num);
		  viewholder.cache_item_layout=(LinearLayout)contentView.findViewById(R.id.cache_layout);
	}
	

}

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

public class CacheAdapater_Direction_Cast extends BaseAdapter {

	
	private Cache one_cache;
	private List<Cache> cache_list;
	private Context mContext;
	
	public CacheAdapater_Direction_Cast(Context mContext,List<Cache> cache_list)
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
		int seletcted_pos=position%8;
		ViewHolder viewholder=null;
		int random_num_memory_tag=(int)(Math.random()*256);
		if(contentView==null)
		{
		    contentView=LayoutInflater.from(mContext).inflate(R.layout.cache_item, null);
		    viewholder=new ViewHolder();
			viewholder.memory_tag=(TextView)contentView.findViewById(R.id.memory_tag);
			viewholder.cache_arr_num=(TextView)contentView.findViewById(R.id.cache_arr_num);
			viewholder.cache_item_layout=(LinearLayout)contentView.findViewById(R.id.cache_layout);
			viewholder.memory_tag.setText("主存标记:"+random_num_memory_tag);
			viewholder.cache_arr_num.setText("cache组号"+this.one_cache.getCache_Array_Num()+"");
			this.setcolor_for_background(viewholder.cache_item_layout, position);
		    contentView.setTag(viewholder);
		}
		else
		{
			viewholder=(ViewHolder)contentView.getTag();
		    viewholder.cache_arr_num.setText("cache组号"+this.one_cache.getCache_Array_Num()+"");
		    this.setcolor_for_background(viewholder.cache_item_layout, position);
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
	private void setcolor_for_background(LinearLayout memory_layout,int position)
	{   
		 switch(position)
		 {
		 case 0:
			   memory_layout.setBackgroundResource(R.drawable.cache_memory_layout);
			   break;
		 case 1:
			   memory_layout.setBackgroundResource(R.drawable.cache_memory_layout1);
		       break;
		 case 2:
			 memory_layout.setBackgroundResource(R.drawable.cache_memory_layout2);
		      break;
		 case 3:
			 memory_layout.setBackgroundResource(R.drawable.cache_memory_layout3);
		     break;
		 case 4:
			 memory_layout.setBackgroundResource(R.drawable.cache_memory_layout4);
			 break;
		 case 5:
			 memory_layout.setBackgroundResource(R.drawable.cache_memory_layout5);
			 break;
		 case 6:
			 memory_layout.setBackgroundResource(R.drawable.cache_memory_layout6);
		     break;
		 case 7:
			 memory_layout.setBackgroundResource(R.drawable.cache_memory_layout7);
			 break;
		 }
	}
	
}

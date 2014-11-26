package com.course_design.adapter;

import java.util.List;


import com.course_design.activity.R;
import com.course_design.object.Memory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MemoryAdapter_Full_Cast extends BaseAdapter {

	private Memory one_memory;
	private List<Memory> memory_list;
	private Context mContext;
	
	public MemoryAdapter_Full_Cast(Context mContext,List<Memory> memory_list)
	{
		   this.mContext=mContext;
		   this.memory_list=memory_list;
	}
	public int getCount() {
		return (this.memory_list.size());
	}

	public Object getItem(int position) {
		return (this.memory_list.get(position));
	}

	public long getItemId(int pos) {
		return (pos);
	}

	public View getView(int position, View contentView, ViewGroup arg2) {
		this.one_memory=this.memory_list.get(position);
        Viewholder viewholder=null;
        if(contentView==null)
        {
             contentView=LayoutInflater.from(mContext).inflate(R.layout.memory_item, null);
             viewholder=new Viewholder();
             viewholder.memory_num=(TextView)contentView.findViewById(R.id.memory_num);
             viewholder.memory_arr_num=(TextView)contentView.findViewById(R.id.memory_arr_num);
             viewholder.memory_layout=(LinearLayout)contentView.findViewById(R.id.memory_layout);
                                                                           
             viewholder.memory_layout.setBackgroundResource(R.drawable.cache_memory_layout);
             viewholder.memory_num.setText("主存组号:"+this.one_memory.getMemory_num()+"");
             viewholder.memory_arr_num.setVisibility(View.INVISIBLE);
             contentView.setTag(viewholder);
        }else{

        	viewholder=(Viewholder)contentView.getTag();
        	viewholder.memory_layout.setBackgroundResource(R.drawable.cache_memory_layout);
        	viewholder.memory_num.setText("主存组号:"+this.one_memory.getMemory_num()+"");
            viewholder.memory_arr_num.setVisibility(View.INVISIBLE);
        }
		return contentView;
	}
     private class Viewholder{
    	  TextView memory_num;
    	  TextView memory_arr_num;
    	  LinearLayout memory_layout;
     }
    
    

}

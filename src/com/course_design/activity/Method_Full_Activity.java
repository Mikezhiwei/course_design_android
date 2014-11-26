package com.course_design.activity;

import java.util.ArrayList;
import java.util.List;

import com.course_design.adapter.CacheAdapater_Direction_Cast;
import com.course_design.adapter.CacheAdapter_Full_Cast;
import com.course_design.adapter.MemoryAdapter_Deirction_Cast;
import com.course_design.adapter.MemoryAdapter_Full_Cast;
import com.course_design.object.Cache;
import com.course_design.object.Memory;
import com.course_design.utils.Address_Utils;
import com.course_design.utils.Animation_Utils;
import com.course_design.utils.Cast_Utils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Method_Full_Activity extends Activity {
    
	  private List<Cache> Cache_list;
	  private List<Memory> Memory_list;
	  
	  private ListView cache_list_view;
	  private ListView memory_list_view;
	  private EditText address_input;
	  private Button  search_add_btn;
	  
	  private Cache one_cache;
	  private Memory one_memory;
	  
	  private CacheAdapter_Full_Cast cache_adapter;
	  private MemoryAdapter_Full_Cast memory_adapter;
	  
	  private Cast_Utils cast_utils;
	  private Address_Utils add_utils;
	  
	protected void onCreate(Bundle savedInstanceState)
	{
		  super.onCreate(savedInstanceState);
		  this.setContentView(R.layout.method_one_direction_layout);
		  this.initial();
	}
	private void initial()
	{
		  this.cache_list_view=(ListView)this.findViewById(R.id.cache_list);
		  this.memory_list_view=(ListView)this.findViewById(R.id.memory_list);
		  this.address_input=(EditText)this.findViewById(R.id.input_address);
		  this.search_add_btn=(Button)this.findViewById(R.id.search_btn);
		  this.search_add_btn.setOnClickListener(search_btn_listener);
		  this.cache_adapter=new CacheAdapter_Full_Cast(this,this.load_cache_list());
		  this.memory_adapter=new MemoryAdapter_Full_Cast(this,this.load_memory_list());
		  this.cache_list_view.setAdapter(cache_adapter);
		  this.memory_list_view.setAdapter(this.memory_adapter);
		  this.cast_utils=new Cast_Utils();
		  this.add_utils=new Address_Utils();
		  this.cache_list_view.setOnItemClickListener(cache_item_listener);
   	      this.address_input.setHint("请输入主存前11位二进制数字");
	}
	private List<Cache> load_cache_list()
	{
		  this.Cache_list=new ArrayList<Cache>();
		  for(int index=0;index<8;index++)
		  {
			    this.one_cache=new Cache();
			    this.one_cache.setMemory_Tag((int)(Math.random()*2048));
			    this.one_cache.setCache_Array_Num(index);
			    this.Cache_list.add(one_cache);
		  }
		  return(this.Cache_list);
		  
	}
	private  List<Memory> load_memory_list()
	{
		 this.Memory_list=new ArrayList<Memory>();
		 for(int memory_num=0;memory_num<32;memory_num++)
		 {
			  this.one_memory=new Memory();
			  this.one_memory.setMmeory_num(memory_num);
			  this.Memory_list.add(one_memory);
		 }
		 return(this.Memory_list);
		 
	}
	private OnItemClickListener cache_item_listener=new OnItemClickListener(){
		public void onItemClick(AdapterView<?> arg0, View view, int position,long arg3) 
		{
			   for(int index=memory_list_view.getFirstVisiblePosition();index<=memory_list_view.getLastVisiblePosition();index++)
			   {  
				   /*
				    *理解listview的getchindat，listview的视图只会加载初始化的几个视图，
				    *其他的都是空，所以要用 index-memory_list_view.getFirstVisiblePosition()获取之前的视图
				    */
				      View contentView=memory_list_view.getChildAt(index-memory_list_view.getFirstVisiblePosition());
				      LinearLayout changedlayout=(LinearLayout)contentView.findViewById(R.id.memory_layout);
				      changedlayout.startAnimation(Animation_Utils.getAplahaAnimation());
			   }
			  
		}
	};
    
	private OnClickListener search_btn_listener=new OnClickListener()
	{
		  public void onClick(View v) 
		  {
			  
			  String address=address_input.getText().toString().trim();
			  if(address.length()==11)
			  {	  
					   int address_find=add_utils.swtich_binary_to_decimal(address);
					   int index=0;
					   for(index=0;index<Cache_list.size();index++)
					   {
						     if(address_find==Cache_list.get(index).getMemory_Tag())
						     {
						    	  Toast.makeText(getApplicationContext(), "命中", 1000).show();
						    	  View contentView=cache_list_view.getChildAt(index);
						    	  Toast.makeText(getApplicationContext(), index+"", 1000).show();
								  LinearLayout new_add_layout=(LinearLayout)contentView.findViewById(R.id.cache_layout);
								  new_add_layout.startAnimation(Animation_Utils.getAplahaAnimation());
						    	  break;
						     }
					   }
					   if(index==Cache_list.size())
					   {
						    Toast.makeText(getApplicationContext(), "未命中,cache新增", 1000).show();
						    one_cache=new Cache();
						    one_cache.setMemory_Tag(address_find);
						    Cache_list.remove(0);
						    Cache_list.add(one_cache);
						    View contentView=cache_list_view.getChildAt(index-1);
						    if(contentView==null)
						    {
						    	 
						    }else{
						    	LinearLayout new_add_layout=(LinearLayout)contentView.findViewById(R.id.cache_layout);
						    	new_add_layout.startAnimation(Animation_Utils.getAplahaAnimation());
						        TextView new_add_memory_tag=(TextView)contentView.findViewById(R.id.memory_tag);
						        new_add_memory_tag.setText(address_find+"");
						    }
						    cache_adapter.notifyDataSetChanged();
						    //list改变之后，通知adapter重新去改变listview的布局
					   }
				   }
			       else
			       {
					   Toast.makeText(getApplicationContext(), "请输入11位地址", 1000).show();
				   }
					   
		  }
	};
	
	
}

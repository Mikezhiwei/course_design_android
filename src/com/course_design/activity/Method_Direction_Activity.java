package com.course_design.activity;

import java.util.ArrayList;
import java.util.List;

import com.course_design.adapter.CacheAdapater_Direction_Cast;
import com.course_design.adapter.MemoryAdapter_Deirction_Cast;
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Method_Direction_Activity extends Activity {
	
	  private List<Cache> Cache_list;
	  private List<Memory> Memory_list;
	  
	  private ListView cache_list_view;
	  private ListView memory_list_view;
	  private EditText address_input;
	  private Button  search_add_btn;
	  
	  private Cache one_cache;
	  private Memory one_memory;
	  
	  private CacheAdapater_Direction_Cast cache_adapter;
	  private MemoryAdapter_Deirction_Cast memory_adapter;
	  
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
		  
		  this.cache_adapter=new CacheAdapater_Direction_Cast(this,this.load_cache_list());
		  this.memory_adapter=new MemoryAdapter_Deirction_Cast(this,this.load_memory_list());
		  this.cache_list_view.setAdapter(cache_adapter);
		  this.memory_list_view.setAdapter(this.memory_adapter);
		  this.cast_utils=new Cast_Utils();
		  this.cache_list_view.setOnItemClickListener(cache_item_listener);
		  this.add_utils=new Address_Utils();
   	      this.search_add_btn.setOnClickListener(search_btn_listener);
   	      this.address_input.setHint("请输入主存前11位二进制数字");
	}
	private List<Cache> load_cache_list()
	{
		  this.Cache_list=new ArrayList<Cache>();
		  for(int index=0;index<8;index++)
		  {
			    this.one_cache=new Cache();
			    this.one_cache.setCache_Array_Num(index);
			    this.Cache_list.add(one_cache);
		  }
		  return(this.Cache_list);
		  
	}
	private  List<Memory> load_memory_list()
	{
		 this.Memory_list=new ArrayList<Memory>();
		 for(int memory_num=0;memory_num<2;memory_num++)
		 {
			   for(int arr_num=0;arr_num<8;arr_num++)
			   {
                    this.one_memory=new Memory();
                    this.one_memory.setMmeory_num(memory_num);
                    this.one_memory.setMemory_arr_num(arr_num);
                    this.Memory_list.add(one_memory);
			   }
		 }
		 
		 return(this.Memory_list);
		 
	}
	private OnItemClickListener cache_item_listener=new OnItemClickListener(){

		public void onItemClick(AdapterView<?> arg0, View view, int position,long arg3) 
		{
			  int res_num[]=cast_utils.direction_cast_utils(position, 2);
			  //仅仅只看主存的前面两组,作为示例
			  View cache_view=cache_list_view.getChildAt(position);
			  LinearLayout cache_layout=(LinearLayout)cache_view.findViewById(R.id.cache_layout);
			  for(int index=0;index<res_num.length;index++)
			  {
				  if(ensure_visiable_pos(res_num[index]))
				  {
					  View contentView=memory_list_view.getChildAt(res_num[index]-memory_list_view.getFirstVisiblePosition());
					  LinearLayout changedlayout=(LinearLayout)contentView.findViewById(R.id.memory_layout);
					  changedlayout.startAnimation(Animation_Utils.getAplahaAnimation());
				  }
			  }
		}
		   
	};
	
	private boolean ensure_visiable_pos(int changed_pos)
	{
		  final int first_vis_pos=this.memory_list_view.getFirstVisiblePosition();
		  final int last_vis_pos=this.memory_list_view.getLastVisiblePosition();
		  if(changed_pos<=last_vis_pos &&changed_pos>=first_vis_pos)
		  {
			    return true;
		  }else {
			    return false;
		  }
		  
	}
	private OnClickListener search_btn_listener=new OnClickListener()
	{
	     public void onClick(View v)
	     {
	    	    String address=address_input.getText().toString().trim();
		    	if(address.length()==11)
		    	{
			    	  int memory_tag=add_utils.swtich_binary_to_decimal(address.substring(0, 8));
		              int cache_arr_num=add_utils.swtich_binary_to_decimal(address.substring(8));
		              if(memory_tag<0 || cache_arr_num<0)
		              {
		            	     Toast.makeText(getApplicationContext(), "输入有误", 10000).show();
		              }
		              else
		              {
		            	  deal_search_input(memory_tag,cache_arr_num);
		              }
			     
		      }else{
		    	     Toast.makeText(getApplicationContext(), "输入地址必须是11位", 1000).show();
		      }
	     }
	};
	
	private void deal_search_input(int memory_tag,int cache_arr_num)
	{
		 View cacheView=cache_list_view.getChildAt(cache_arr_num);
		 LinearLayout cache_layout=(LinearLayout)cacheView.findViewById(R.id.cache_layout);
	     TextView  memory_tag_checked=(TextView)cacheView.findViewById(R.id.memory_tag);
	     String str=memory_tag_checked.getText()+"";
	     String []str_split=str.split(":");
	     int cache_tag=Integer.parseInt(str_split[1]);
	     if(cache_tag==memory_tag)
	     {
	    	   Toast.makeText(getApplicationContext(), "命中",1000).show();
	    	   cache_layout.setAnimation(Animation_Utils.getAplahaAnimation());
	     }
	     else
	     {
	          memory_tag_checked.setText("主存序号:"+memory_tag);
	          Toast.makeText(getApplicationContext(), "未命中,cache装入", 1000).show();
	          cache_layout.setAnimation(Animation_Utils.getAplahaAnimation());
	          
	     }
	}

}

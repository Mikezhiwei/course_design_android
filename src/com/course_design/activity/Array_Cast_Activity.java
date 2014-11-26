package com.course_design.activity;

import java.util.ArrayList;
import java.util.List;

import com.course_design.adapter.CacheAdapater_Direction_Cast;
import com.course_design.adapter.CacheAdapter_Arr_Cast;
import com.course_design.adapter.MemoryAdapter_Arr_Cast;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Array_Cast_Activity extends Activity {
	
	  private List<Cache> Cache_list;
	  private List<Memory> Memory_list;
	  
	  private ListView cache_list_view;
	  private ListView memory_list_view;
	  private EditText address_input;
	  private Button  search_add_btn;
	  
	  private Cache one_cache;
	  private Memory one_memory;
	  
	  private CacheAdapter_Arr_Cast cache_adapter;
	  private MemoryAdapter_Arr_Cast memory_adapter;
	  
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
		  this.cache_list_view.setOnItemClickListener(cache_item_listener);
		  this.cache_adapter=new CacheAdapter_Arr_Cast(this,this.load_cache_list());
		  this.memory_adapter=new MemoryAdapter_Arr_Cast(this,this.load_memory_list());
		  this.cache_list_view.setAdapter(this.cache_adapter);
		  this.memory_list_view.setAdapter(this.memory_adapter);
		  this.cast_utils=new Cast_Utils();
		  this.add_utils=new Address_Utils();
		  this.search_add_btn.setOnClickListener(search_btn_listener);
  	      this.address_input.setHint("请输入主存前11位二进制数字");
	}
	private List<Cache> load_cache_list()
	{
		  this.Cache_list=new ArrayList<Cache>();
		  for(int index=0;index<4;index++)
		  {
			    for(int arr_num=0;arr_num<2;arr_num++)
			    {
			    	      this.one_cache=new Cache();
			    	      this.one_cache.setCache_Arr_inner_Num(arr_num);//cache组内序号
			    	      this.one_cache.setCache_Array_Num(index);//cache组号
			    	      this.Cache_list.add(one_cache);
			    }
		  }
		  return(this.Cache_list);
		  
	}
	private  List<Memory> load_memory_list()
	{
		 this.Memory_list=new ArrayList<Memory>();
		 for(int memory_num=0;memory_num<2;memory_num++)
		 {
			   for(int arr_num=0;arr_num<4;arr_num++)
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
		         Cache selected_cache=Cache_list.get(position);
		         int res_arr[]=cast_utils.array_cast_utils(selected_cache.getCache_Array_Num(), 2);
		         for(int index=0;index<res_arr.length;index++)
		         {
		        	   View contentView= memory_list_view.getChildAt(res_arr[index]);
		        	   LinearLayout selected_layout=(LinearLayout)contentView.findViewById(R.id.memory_layout);
                       selected_layout.startAnimation(Animation_Utils.getAplahaAnimation());		        	   
	 	         }

		  }
	  };
	  private OnClickListener search_btn_listener=new OnClickListener(){
			public void onClick(View v) 
			{
				   String address=address_input.getText().toString().trim();
				   if(address.length()==11)
				   {
					        int memory_tag=add_utils.swtich_binary_to_decimal(address.substring(0, 9));
					        int cache_arr_num=add_utils.swtich_binary_to_decimal(address.substring(9));
					        if(memory_tag<0 || cache_arr_num<0)
					        {
					        	Toast.makeText(getApplicationContext(), "输入有误", 1000).show();
					        }
					        else
					        {
					        	/*
					        	 * 因为cache只是分了两组而已,所以循环了两次
					        	 * */
					        	int index;
					        	for(index=0;index<2;index++)
					        	{
						        	    View contentView=cache_list_view.getChildAt(cache_arr_num*2+index);
						        	    TextView  checked_memory_tag=(TextView)contentView.findViewById(R.id.memory_tag_arr_cast);
						        	    String str=checked_memory_tag.getText()+"";
	                                    String str_arr[]=str.split(":");
	                                    int cheked_tag=Integer.parseInt(str_arr[1]);
	                                    if(cheked_tag==memory_tag)
	                                    {
	                                    	break;
	                                    }
					        	}
					        	if(index<2)
					        	{
					        		 Toast.makeText(getApplicationContext(), "命中", 1000).show();
					        		 View contentView=cache_list_view.getChildAt(cache_arr_num*2+index);
						        	 LinearLayout checkedlayout=(LinearLayout)contentView.findViewById(R.id.cache_layout_arr_cast);
						        	 checkedlayout.startAnimation(Animation_Utils.getAplahaAnimation());
					        	}else
					        	{
					        		 Toast.makeText(getApplicationContext(), "未命中,cache装入", 1000).show();
					        		 View contentView=cache_list_view.getChildAt(cache_arr_num*2);
						        	 LinearLayout checkedlayout=(LinearLayout)contentView.findViewById(R.id.cache_layout_arr_cast);
						        	 TextView  checked_memory_tag=(TextView)contentView.findViewById(R.id.memory_tag_arr_cast);
						        	 checked_memory_tag.setText("主存序号:"+memory_tag); 
						        	 checkedlayout.startAnimation(Animation_Utils.getAplahaAnimation());
						        	 
					        	}
					        }
				   }else{
					    Toast.makeText(getApplicationContext(), "请输入11位2进制数字", 1000).show();
				   }
			}
		          
	  };
		       
	  

}

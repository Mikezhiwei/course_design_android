package com.course_design.activity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity {

	   private Button direction_btn;
	   private Button full_relate_btn;
	   private Button array_relate_btn;
	   
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.course_choose_layout);
	    this.initial();
	}
	
	private void initial()
	{
		   this.direction_btn=(Button)this.findViewById(R.id.method_one_direction);
		   this.full_relate_btn=(Button)this.findViewById(R.id.method_two_full);
		   this.array_relate_btn=(Button)this.findViewById(R.id.method_three_array);
	       
		   this.direction_btn.setOnClickListener(new OnClickListener(){
				public void onClick(View v) {
				      Intent method_direction=new Intent();
				      method_direction.setClass(getApplicationContext(),Method_Direction_Activity.class);
				      startActivity(method_direction);
				}
			   
		   });
		   this.full_relate_btn.setOnClickListener(new OnClickListener(){
			     public void onClick(View v) 
			     {
			    	   Intent method_full=new Intent();
			    	   method_full.setClass(getApplicationContext(), Method_Full_Activity.class);
			    	   startActivity(method_full);
			     }
		   });
		   this.array_relate_btn.setOnClickListener(new OnClickListener(){
			    public void onClick(View v) 
			    {
			         Intent method_array=new Intent();
			         method_array.setClass(getApplicationContext(), Array_Cast_Activity.class);
			         startActivity(method_array);
			    }
		   });
	}
	
	
	
  

}

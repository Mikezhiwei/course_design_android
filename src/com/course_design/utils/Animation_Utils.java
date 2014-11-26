package com.course_design.utils;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class Animation_Utils {
	
	   public static Animation getAplahaAnimation()
	   {
		   final Animation aplhaanimation=new  AlphaAnimation(1.0f,0.2f);
		   aplhaanimation.setDuration(2000);
		   aplhaanimation.setAnimationListener(new AnimationListener(){
			public void onAnimationEnd(Animation arg0) {
				aplhaanimation.setFillAfter(false);//动画完成后是否回复原状态			
			}
			public void onAnimationRepeat(Animation arg0) {
				
			}
			public void onAnimationStart(Animation arg0) {
				
			}
			   
		   });
		   return(aplhaanimation);
	   }

}

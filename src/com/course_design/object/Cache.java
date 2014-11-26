package com.course_design.object;

public class Cache {
	
	   
	         private int Cache_Num;//cache的组号
	         private int Memory_Tag;
	         private int Cache_Arr_Nun;//cache组内序号
	         
	         public void setCache_Array_Num(int Cache_Num)
	         {
	        	  this.Cache_Num=Cache_Num;
	         }
	         public int getCache_Array_Num()
	         {
	        	  return(this.Cache_Num);
	         }
	         
	         public void setMemory_Tag(int Memory_Tag)
	         {
	        	  this.Memory_Tag=Memory_Tag;
	         }
	         public int getMemory_Tag()
	         {
	        	 return(this.Memory_Tag);
	         }
	         
	         public void setCache_Arr_inner_Num(int Cache_Arr_Num)//组内序号
	         {
	        	  this.Cache_Arr_Nun=Cache_Arr_Num;
	         }
	         public int getCache_Arr_inner_Num()
	         {
	        	 return(this.Cache_Arr_Nun);
	         }
	         

}

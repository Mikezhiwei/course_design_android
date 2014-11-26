package com.course_design.utils;

public class Cast_Utils {
	
	
	        /*
	         * ���ϵ���������ֱ��ӳ��,ȫ��ϵӳ��,����ӳ��,��ַת��
	         * */
	        public int[] direction_cast_utils(int cache_num,int memory_arr_length)
	        {
	        	    int result_arr[]=new int[memory_arr_length];
	        	    for(int index=0;index<memory_arr_length;index++)
	        	    {
	        	    	    result_arr[index]=cache_num+index*8;
	        	    }
	        	    return (result_arr);
	        }
	        
	        public int[]full_cast_utils(int memory_arr_length)
	        {
	        	   int result_arr[]=new int[memory_arr_length*8];
	        	   for(int index=0;index<result_arr.length;index++)
	        	   {
	        		     result_arr[index]=index;
	        	   }
	        	   return(result_arr);
	        }
	        
	        public int[]array_cast_utils(int cache_num,int memory_arr_length)
	        {
	        	int result_arr[]=new int[memory_arr_length];
	        	for(int index=0;index<result_arr.length;index++)
	        	{
	        		  result_arr[index]=cache_num+index*4;
	        	}
	        	return (result_arr);
	        }

}

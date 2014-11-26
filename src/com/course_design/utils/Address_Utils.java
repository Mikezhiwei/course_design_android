package com.course_design.utils;

public class Address_Utils {
	
	
	    public int swtich_binary_to_decimal(String address_input)
	    {
	       return(this.recursion_get_address(address_input));
	    }
	    /*
	     * µİ¹éËÑÑ°²Ù×÷Êı
	     * */
	    private int  recursion_get_address(String address_input)
	    {
	    	     if(!address_input.equals(""))
	    	     {
	    	    	 char binary_one=address_input.charAt(0);
	    	    	 if(binary_one=='0')
	    	    	 {
	    	    		 return (0+recursion_get_address(address_input.substring(1)));
	    	    	 }
		    	     else if(binary_one=='1')
		    	     {
		    	          int sum=1;
		    	          sum=sum<<(address_input.length()-1);
		    	          return (sum+recursion_get_address(address_input.substring(1)));
		    	     }else{
		    	    	  return (-32768);
		    	     }
	            }
	    	    else
	            {
	            	  return 0;
	            }
	    }    

}

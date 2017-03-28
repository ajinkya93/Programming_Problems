
public class ReplaceMultiplePatterns {

	public static String replacePattern(String input, String pattern){
		if(input.isEmpty()){
		 System.out.println("No input");
		 return "";
	}
	if(pattern.isEmpty() || !(input.contains(pattern))){
		//System.out.println(input);
		return input;
	}

	/*
	 int i = 0; //input.length();
	 int j = 0;//pattern.length(); 
	 int prevIndex = Integer.MIN_VALUE;
	 boolean flag = false;
	
	 while((i < input.length()) && (j < pattern.length())){
		 while((i < input.length()) && (input.charAt(i) != pattern.charAt(j)))
			i++; 
	//int startIndex = i;
	//boolean flag = true;
		while((i < input.length()) && (j < pattern.length()) && (input.charAt(i) == pattern.charAt(j)))
		         j++; 
		int endIndex = i + j;
		if(i - prevIndex == pattern.length()){
	          flag = true;
		if(endIndex< input.length())
		     input = input.substring(0,prevIndex)+"X"+ input.substring(endIndex);
	           else
		    input = input.substring(0,prevIndex+1)+"X";
		}
	  else {
	           flag = false;
	           if(endIndex< input.length())
	        	   input = input.substring(0,i-1)+"X"+input.substring(endIndex);
	           else
	        	   input = input.substring(0,prevIndex-1)+"X";
	  	}
	j = 0;
	//prevIndex = startIndex;
	if(!flag)
		prevIndex = i;
	i = i + pattern.length();
   }
	 */
	int i = 0; //input.length();
	int j = 0;
	int enteredIndex = -1;
	while((i < input.length()) && (j < pattern.length())){
		System.out.println("i at entry: "+i);
		//System.out.println("enteredIndex at entry: "+ enteredIndex);
		 while((i < input.length()) && (input.charAt(i) != pattern.charAt(j)))
			i++;
		 while((i < input.length()) && (j < pattern.length()) && (input.charAt(i) == pattern.charAt(j)))
	         j++;
		 System.out.println("i before diff: "+i);
		 //System.out.println("i - enteredIndex : "+ (i - enteredIndex));
		 if(i - enteredIndex == pattern.length()){
			 //System.out.println("Value of i: "+i);
			// i = i + pattern.length();
			 if(i+pattern.length() < input.length()){
				 input = input.substring(0,i)+"X"+input.substring(i+pattern.length());
			 	System.out.println("Modified Input: "+input);
		 }
			 else
				 input = input.substring(0,enteredIndex+1);
		 }
		 else{
			 if((i+pattern.length() < input.length())){
				 input = input.substring(0,i)+"X"+input.substring(i+pattern.length());
				 System.out.println("Modified Input: "+input);
				 enteredIndex = i;
				System.out.println("enteredIndex: "+enteredIndex);
				i = i + 1;
				j = 0;
			 }
		 //break;
			 else{
				 if(i == input.length())
					 break;
					 input = input.substring(0,i)+"X";
			 }
	}
		
		//i = i + 1;
		 System.out.println("i at exit: "+i);
		  
	}
	 return input;
}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input = replacePattern("aaaaa","aa");
		System.out.println("result: "+input);
	}

}

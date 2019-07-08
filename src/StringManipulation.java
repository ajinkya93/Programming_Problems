import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StringManipulation {

	//generate all possible combinations of strings
	public static void perm(String str, int l, int r){
		if(l == r)
			System.out.println(str);
		else{
			for(int i = l ; i <= r ; i++){
				str = swap(str,l,i);
				perm(str,l+1,r);
				str = swap(str,l,i);
			}
		}
	}
	
	public static String swap(String s, int i, int j){
		char charArray[] = s.toCharArray();
		char temp = charArray[i];
		charArray[i] = charArray[j];
		charArray[j] = temp;
		return String.valueOf(charArray);
	}
	
	//Takes O(n * m) space and running time
	//Time complexity of brute force approach O(n * m^2) [O(n^3)] where n refers to 
	//length of string 1 and there can be m^2 substrings at the most
	
	public static void longestCommonSubstring(char a[], char b[]){
		int lcs_length = -1;
		if(a.length == 0 || b.length == 0){
			System.out.println("Length of longest common substring is 0");
		}
		else{
			int lcs[][] = new int[a.length+1][b.length+1];
			
			for(int i = 0 ; i < a.length ; i++){
				lcs[i][0] = 0;
			}
			
			for(int j = 0 ; j < b.length ; j++){
				lcs[0][j] = 0;
			}
			
			for(int i = 1 ; i <= a.length ; i++){
				for(int j = 1 ; j <= b.length ; j++){
					if(a[i - 1] == b[j - 1])
						lcs[i][j] = 1 + lcs[i - 1][j - 1];
					else
						lcs[i][j] = 0;
				}
			}
			
			for(int i = 0 ; i < a.length ; i++){
				for(int j = 0 ; j < b.length; j++){
					if(lcs_length < lcs[i][j])
						lcs_length = lcs[i][j];
				}
			}
			System.out.println("Length of longest common substring is "+lcs_length);
		}
	}
	
	
	//Matching word from a bag of words based on the longest common prefix
	//O(n * m): n is the length of target word and m is the total number of characters in the bag
	
	public static void longestCommonPrefix(ArrayList<String> words, String target){
		int maxLength = 0;
		String result = null;
		
		if(target.equals(null) || words.size() == 0){
			System.out.println("Either the target is empty OR the bag of words is empty");
			return;
		}
		for(int i = 0 ; i < words.size(); i++){
			String temp = words.get(i);
			int counter = Math.min(target.length(), temp.length());
			int j = 0;
			int length = 0;
			while(j < counter){
				if(target.charAt(j) == temp.charAt(j)){
					length++;
					j++;
				}
				else 
					break;
			}
			if(length > maxLength){
				maxLength = length;
				result = temp;
			}
		}
		
		System.out.println("Length of common prefix: "+maxLength+", Closest Match: "+result);
	}
	
	public static void strinComp(String str){
		
		char a[] = str.toCharArray();
		int startIndex = 0, count = 1;
		int i = 0;
		int resLen = 0;
		
		//check to see if the string can be compressed
		/*
		for(i = 0; i < str.length()-1;i++){
			if(str.charAt(i) == str.charAt(i+1))
				count++;
			else{
				if(count < 2){
					System.out.println("The string "+str+" cannot be compressed");
					return;
				}
				count = 0;
			}
		}
		*/
		count = 1;
		for(i = 0; i <= a.length - 2; i++){
			if(a[i]==a[i+1])
				count++;
			else{
				a[startIndex] = a[i];
				a[startIndex+1] = Character.forDigit(count, 10);
				startIndex +=2;
				count = 1;
				resLen +=2;
			}
		}
		a[startIndex] = a[i];
		a[startIndex+1] = Character.forDigit(count, 10);
		resLen +=2;
		
		System.out.println("Compressed String:");
		for(int j = 0 ; j < resLen;j++)
			System.out.print(a[j]);
	}
	
	public static void longestCommonSubsequence(char a[], char b[]){
		
		if(a.length == 0 || b.length == 0){
			System.out.println("LENGTH of longest common subsequence is 0");
			return;
		}
		
		int lcs[][] = new int[a.length+1][b.length+1];
		//int lcs[][] = new int[a.length][b.length];
		
		for(int i = 0 ; i <= a.length ; i++)
		//for(int i = 0 ; i < a.length ; i++)
			lcs[i][0] = 0;
		for(int j = 0 ; j <= b.length; j++)
		//for(int j = 0 ; j < b.length; j++)
			lcs[0][j] = 0;
		
		for(int i = 1 ; i <= a.length; i++){
		//for(int i = 1 ; i < a.length; i++){
			for(int j = 1 ; j <= b.length ; j++){
			//for(int j = 1 ; j < b.length ; j++){
				if(a[i - 1] == b[j - 1])
					lcs[i][j] = 1 + lcs[i - 1][j - 1];
				else
					lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
			}
		}
		
		System.out.println("Length of longest common subsequence is "+lcs[a.length][b.length]);
		//System.out.println("Length of longest common subsequence is "+lcs[a.length-1][b.length-1]);
		
		String tempResult = " ";
		int i = a.length,j = b.length;
		//int i = a.length-1,j = b.length-1;
		while(i > 0 && j > 0){
			if(a[i - 1] == b[j - 1]){
				tempResult += a[i-1];
				i--;
				j--;
			}
			else if(lcs[i - 1][j] > lcs[i][j - 1]){
				i--;
			}
			else
				j--;
		}
		char[] subSequence = new char[tempResult.length()];
		int l = tempResult.length();
		for(int k = 0 ; k < l; k++){
			subSequence[k] = tempResult.charAt((l-1) - k);
		}
		System.out.println("Longest common subsequence: "+String.valueOf(subSequence));
	}
	
	//method to find and return a list of anagrams together.
	//Complexity: O(n) , n is the number of words in the input list
	public static void anagramsTogether(List<String> list){
		if(list.size() == 0){
			System.out.println("Empty List");
			return;
		}
		
		List<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		HashMap<String,ArrayList<String>> hm = new HashMap<String,ArrayList<String>>();
		
		for(int i = 0 ; i < list.size(); i++){
			String str = list.get(i);
			char ch[] = str.toCharArray();
			Arrays.sort(ch);
			String key = String.valueOf(ch);
			if(hm.containsKey(key))
				hm.get(key).add(str);
			else{
				ArrayList<String> temp = new ArrayList<String>();
				temp.add(str);
				hm.put(key,temp);
			}
		}
		
		Set<Map.Entry<String, ArrayList<String>>> entrySet = hm.entrySet();
		for(Map.Entry entry: entrySet){
			result.add((ArrayList<String>)entry.getValue());
		}
		
		for(int i = 0 ; i < result.size();i++){
			System.out.println(result.get(i));
		}
	}
	
	//method to generate all possible combinations of the string with every letter capitalized
	//Complexity: O(2^n) where n is the number of characters in the string ?
	//as in the worst case, every character can either be lowercase or uppercase
	
	public static void capitalizeComb(String str, int i,boolean flag){
		if(i >= str.length() || str.length() == 0)
			return;
		if(Character.isAlphabetic(str.charAt(i))){
			//System.out.println("i: "+i);
			//System.out.println("str: "+str);
			String prefix = "";
			char change = ' ';
			String postfix = "";
			if(!(i <= 0)){
				//prefix = str.substring(0, i - 1);
				prefix = str.substring(0, i);
				//System.out.println("prefix: "+prefix);
			}
			change = Character.toUpperCase(str.charAt(i));
			//System.out.println("change: "+change);
			if(i <= str.length() - 2){
				postfix = str.substring(i + 1);
				//System.out.println("postfix: "+postfix);
			}
			//String oldString = str;
			String newString = prefix+change+postfix;
			System.out.println(newString);
			capitalizeComb(newString, i+1,flag);
			capitalizeComb(str, i+1,flag);
		}
		else{
			if(!flag){
			System.out.println(str);
			flag = true;
			}
			capitalizeComb(str, ++i,flag);
		}
	}
	
	/*method to find longest palindromic substring in a string
	Complexity : DP Solution : O(n^2) both time and space
	Use memoization table[][] : 
	1. Mark table[i][i] as true as single characters are palindromic.
	table[i][j] = true indicates that that the sting S_i....S_j is palindromic
	2. Check for palindromes of length 2
	3. Check for palindromes of length >= 3
	*/
	
	public static String longestpalindromicSubstring(String s){
		if(s.length() == 0)
			return "";
		if(s.length() == 1)
			return s;
		
		int n = s.length();
		int startIndex = 0, maxLength = 1;
		boolean table[][] = new boolean[n][n];
		
		//Single character is palindromic
		for(int i = 0; i < n; i++)
			table[i][i] = true;
		
		//Look for palindromes of length 2
		for(int i = 0; i < n - 1; i++){
			if(s.charAt(i) == s.charAt(i + 1)){
				table[i][i+1] = true;
				startIndex = i;
				maxLength = 2;
			}
		}
		//Look for palindromes of length >= 3
		for(int len = 3; len <= n; len++){
			for(int i = 0; i < n-len+1; i++){
				int j = i+len-1;
				/*
				 * +*******+ : if the first and last characters are same(+) and all the characters in between
				 * (String *******) are palindromic as evident from their truth values (true)
				 */
				if((s.charAt(i) == s.charAt(j)) && (table[i+1][j-1])){
					table[i][j] = true;
					if(len > maxLength){
						maxLength = len;
						startIndex = i;
					}
				}
			}
		}
		return s.substring(startIndex, startIndex+maxLength);
	}
	
	//method to find minimum no. of operations needed (Insert, Remove, Replace) to transform str1 to str 2
	public static int editDistance(int m, int n, String str1, String str2){
		if(m == str1.length())
			return str2.length() - n;
		if(n == str2.length())
			return str1.length() - m;
		if(str1.charAt(m) == str2.charAt(n))
			return editDistance(m+1, n+1, str1, str2);
		return 1 + minOfThreeOperations(editDistance(m, n+1, str1, str2),//Insert
										editDistance(m+1, n, str1, str2),//Remove
										editDistance(m+1, n+1, str1, str2)//Replace
										);
	}
	
	//Helper method to find minimum of three values
	public static int minOfThreeOperations(int x, int y, int z){
		return (x < y && x < z)?x:(y < x && y < z?y:z);
	}
	
	public static void isValidShuffleUniqueChar(String s1, String s2, String s3){
		if((s1.length() + s2.length()) != s3.length()){
			System.out.println(s3+" : Not a valid shuffle of "+s1+" and "+s2);
			return;
			}
		
		int index_in_s1 = -1, index_in_s2 = -1, prev_index_in_s1 = -1, prev_index_in_s2 = -1, i = 0;
		for( ; i < s3.length();){
			if(s1.indexOf(s3.charAt(i)) >= 0){
				index_in_s1 = s1.indexOf(s3.charAt(i));
				if(index_in_s1 > prev_index_in_s1){
					prev_index_in_s1 = index_in_s1;
					i++;
				}
				else{
					System.out.println(s3+" : Not a valid shuffle of "+s1+" and "+s2);
					return;
				}
			}
			
			else if(s2.indexOf(s3.charAt(i)) >= 0){
				index_in_s2 = s2.indexOf(s3.charAt(i));
				if(index_in_s2 > prev_index_in_s2){
					prev_index_in_s2 = index_in_s2;
					i++;
				}
				else{
					System.out.println(s3+" : Not a valid shuffle of "+s1+" and "+s2);
					return;
				}
			}
		}
		if(i == s3.length())
			System.out.println(s3+" : Valid shuffle of "+s1+" and "+s2);
	}
	
	public static boolean isValidShuffleDupChar(String s1, String s2, String s3,HashMap<String,String> cache){
		
		if(cache.containsKey(s1)){
			if(cache.get(s1).equals(s2))
				return false;
		}
		
		
		if((s1.length() + s2.length()) != s3.length()){
			//System.out.println(s3+" : Not a valid shuffle of "+s1+" and "+s2);
			return false;
			}
		
		if(s1.isEmpty() || s2.isEmpty() || s3.isEmpty()){
			if(s1.concat(s2).equals(s3))
				return true;
			else if(s2.concat(s1).equals(s3))
				return true;
			else
				return false;
		}
		
		if((s1.charAt(0) != s3.charAt(0)) && (s2.charAt(0) != s3.charAt(0)))
			return false;

		if((s1.charAt(0) == s3.charAt(0) && (isValidShuffleDupChar(s1.substring(1),s2,s3.substring(1),cache))))
				return true;

		if((s2.charAt(0) == s3.charAt(0) && (isValidShuffleDupChar(s1,s2.substring(1),s3.substring(1),cache))))
			return true;
		
		cache.put(s1,s2);
		return false;
	}
	
	/*
	 * Given a binary pattern that contains ‘?’ wildcard character at few positions, 
	 * find all possible combinations of binary strings that can be formed by 
	 * replacing the wildcard character by either 0 or 1.
	 * Key : Rep?By0And1
	 * */
	public static void  findIndicesWithSplChar(StringBuilder stringToManipulate) {
	    ArrayList<String> results = new ArrayList<String>();
	    ArrayList<Integer> setOfIndices = new ArrayList<Integer>();
	    String originalString = stringToManipulate.toString();
	    
	    if(stringToManipulate.length() == 0) {
        	System.out.println("The input string " + stringToManipulate + " is either empty or contains a special character(s) other than ?.");
        	return;
        }
	    
        for (int i = 0 ; i < stringToManipulate.length() ; i++) {
            if (stringToManipulate.charAt(i) == '?')
		        setOfIndices.add(i);
        }
        
        if(setOfIndices.size() == 0) {
        	System.out.println("The input string " + stringToManipulate + " is either empty or contains a special character(s) other than ?.");
        	return;
        }
        
        // Recurse to replace ? with 0 and 1 respectively
        generateCombinations (0, stringToManipulate, setOfIndices, results, true);	
        
        // Print the results
        System.out.println("Test String - " + originalString);
        System.out.println("Results ... Size : " + results.size());
        for(int i = 0 ; i < results.size(); i++) {
        	System.out.println(results.get(i));
        }
        System.out.println();
    }
    
    public static void generateCombinations (int index, StringBuilder stringToManipulate, ArrayList<Integer> setOfIndices, ArrayList<String> results, boolean proceedForward) {
        // If there are no wildcard characters left ahead to replace with 0 and 1,
    	// add this string to the results list
    	if(!proceedForward) {
	        results.add (stringToManipulate.toString());
	        return;
        }
	
    	// Replace the the current ? with 0 and recurse
    	stringToManipulate.setCharAt(setOfIndices.get(index),'0');
    	
    	// If this is the last wildcard character to be replaced, flip the 'proceedForward' flag
    	// so as to store these strings in the results list as there is no more recursion possible
    	if(index == setOfIndices.size() - 1)
    		proceedForward = false;
    	generateCombinations (index + 1, stringToManipulate, setOfIndices, results, proceedForward);
    	// Replace the the current ? with 1 and recurse
    	stringToManipulate.setCharAt(setOfIndices.get(index),'1');
    	generateCombinations (index + 1, stringToManipulate,setOfIndices, results, proceedForward);
    }
	
	public static void main(String[] args) {
		/* Driver for isValidShuffleUniqueChar and isValidShuffleDupChar
		//isValidShuffleUniqueChar("abc", "def", "dabfce");
		HashMap<String,String> cache = new HashMap<String,String>();
		String s1 = new String("abc");
		String s2 = new String("def");
		String s3 = new String("dabfce");
		boolean flag = isValidShuffleDupChar(s1,s2,s3, cache);
		if(flag)
			System.out.println(s3+" : Valid shuffle of "+s1+" and "+s2);
		else
			System.out.println(s3+" : Not a valid shuffle of "+s1+" and "+s2);
		*/
		
		/*Driver for perm
		String s = new String("abc");
		System.out.println("All possible permutations of string "+s+" are as follows: ");
		
		perm(s,0,s.length()-1);
		*/
		
		
		//Driver for longestCommonSubstring
		//longestCommonSubstring("GeeksforGeeks".toCharArray(),"GeeksQuiz".toCharArray());
		
		//Driver for longestCommonSubsequence
		//longestCommonSubsequence("nematode_knowledge".toCharArray(),"empty_bottle".toCharArray());
		//longestCommonSubsequence("AGGTAB".toCharArray(),"GXTXAYB".toCharArray());
		
		/*Driver for longestCommonPrefix
		ArrayList<String> words = new ArrayList<String>();
		words.add("Army");
		words.add("Arm");
		words.add("Art");
		words.add("Array");
		longestCommonPrefix(words, "Arrogant");
		*/
		
		//Driver for strinComp
		//strinComp("aaabcddd");
		//strinComp("abb");
		//strinComp("aaabbbccc");
		
		/*Driver for anagramsTogether
		ArrayList<String> inputList = new ArrayList<String>();
		inputList.add("abc");
		inputList.add("def");
		inputList.add("cba");
		inputList.add("fed");
		anagramsTogether(inputList);
		*/
		
		//Driver for capitalizeComb
		//capitalizeComb("0ab",0,false);
		//capitalizeComb("000", 0,false);
		//capitalizeComb("0ab1", 0, false);
		//capitalizeComb("", 0, false);
		//System.out.println();
		//capitalizeComb("abc",0, false);
		//System.out.println();
		//capitalizeComb("a", 0, false);
		//capitalizeComb("abcd", 0, false);
		
		/*Split sentences separated by multiple characters like .?!
		String review = "This restaurant is very good!!I will highly recommend it.Ellipsis...Has anybody been there?";
		//String r= "ele55...RandomA.Random2?Random3!Random4 ";
		String sentences[] = review.split("[\\.{1,3}\\?!]");
		for(String str: sentences)
			if(!str.isEmpty())
				System.out.println(str);
		*/
		
		/*Driver for longestpalindromicSubstring()
		String s = new String("forgeeksskeegfor");
		String res = longestpalindromicSubstring(s);
		if(s.length() != 0)
			System.out.println("Longest palindromic substring within "+s+" is "+res+" and its length is "+res.length());
		*/
		
		/*Driver for editDistance()
		String str1 = "";
		String str2 = "";
		
		System.out.println("Operations required to transform "+str1+" to "+str2+" is "+editDistance(0, 0, str1,str2));
		*/
		
		/*
		 *  Driver for findIndicesWithSplChar(). Key: Rep?By0And1
		 * */
		findIndicesWithSplChar(new StringBuilder("1?11?00?1?"));
		findIndicesWithSplChar(new StringBuilder("?"));
		findIndicesWithSplChar(new StringBuilder("10%01"));
		findIndicesWithSplChar(new StringBuilder("abcdef"));
		findIndicesWithSplChar(new StringBuilder(""));
		findIndicesWithSplChar(new StringBuilder("???"));
	}

}

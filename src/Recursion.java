import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Recursion {
	static int counter;
	public static int generateNthFiboNum(int n){
		if(n == 0)
			return 0;
		if(n == 1)
			return 1;
		else{
			n = generateNthFiboNum(n-1) + generateNthFiboNum(n-2);
		}
		return n;
	}

	public static int computePaths(int i, int j, int a, int b, int mat[][]){
		if((i > a) || (j > b))
			return 0;
		if((i == a) && (j == b))
			return 1;
		if(isValidPath(i,j,mat))
			return computePaths(i+1,j,a,b,mat) + computePaths(i,j+1,a,b,mat);
		return 0;
	}
	
	public static boolean isValidPath(int row, int col, int mat[][]){
		if(mat[row][col] == -1)
			return false;
		else
			return true;
	}
	
	//Method to print all subsets of a set. Will work only if n is not too big. (~ n < 64)
	//Time Complexity : O(n*2^n)
	public static void enumerateSets(char a[]){
		int size = a.length;
		for(int i = 0 ; i < Math.pow(2,size) ; i++){
			System.out.print("{");
			for(int j = 0 ; j < size; j++){
				if((i & 1<<j) > 0)
					System.out.print(a[j]);
			}
			System.out.println("}");
		}
	}
	
	//Recursive approach for enumerating all the subsets. Complexity: O(2^n) since we need to
	//enumerate all 2^n possibilities
	public static Set<Set<Integer>> enumerateSets(Set<Integer> originalSet){
		Set<Set<Integer>> result = new HashSet<Set<Integer>>();
		if(originalSet.isEmpty()){
			result.add(new HashSet<Integer>());
			return result;
		}
		List<Integer> list = new ArrayList<Integer>(originalSet);
		Integer head = list.get(0);
		
		Set<Integer> remaining = new HashSet<Integer>(list.subList(1,list.size()));
		for(Set<Integer> set : enumerateSets(remaining)){
			HashSet<Integer> newSet = new HashSet<Integer>();
			newSet.add(head);
			System.out.println("head: "+head);
			newSet.addAll(set);
			System.out.println("set: "+set.toString());
			System.out.println("newSet: "+newSet.toString());
			result.add(newSet);
			result.add(set);
			System.out.println("result: "+result.toString());
		}
		return result;
	}
	
	public static ArrayList<String> getPerms(String s){
		ArrayList<String> permutations = new ArrayList<String>();
		if(s == null)
			return null;
		else if(s.length() == 0){
			permutations.add("");
			return permutations;
		}
		char first = s.charAt(0);
		String rest = s.substring(1);
		ArrayList<String> words = getPerms(rest);
		for(String word: words){
			for(int j = 0 ; j <= word.length(); j++){
				permutations.add(insertChar(word,first,j));
			}
		}
		return permutations;
	}

	public static String insertChar(String word, char first, int j){

		
	String left = null, right = null;
	if(j > 0)
		left = word.substring(0,j);
	else
		left = "";
	if(j < word.length())
		right = word.substring(j);
	else
		right = "";
	return (left+first+right);
		
	
		/*
		String left = word.substring(0,j);
		String right = word.substring(j);
		return left+first+right;
		*/
		
	}
	
	public static List<String> generateAllCombinations(int n){
		ArrayList<String> result = new ArrayList<String>();
		generateAllCombinations(result,"",n,n);
		return result;
	}
	
	public static void generateAllCombinations(ArrayList<String> result, String s, int left, int right){
		counter++;
		
		if(left > right)
			return;
		if((left == 0) && (right == 0)){
			result.add(s);
			return;
		}
		if(left > 0)
			generateAllCombinations(result, s+"(", left - 1, right);
		if(right > 0)
			generateAllCombinations(result, s+")", left, right - 1);
	}
	
	public static int lengthLongestPath(String input) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0); // "dummy" length
        int maxLen = 0;
        for(String s:input.split("\n")){
            int lev = s.lastIndexOf("\t")+1; // number of "\t"
            //System.out.println("lev: "+lev);
            while(lev+1<stack.size()) stack.pop(); // find parent
            int len = stack.peek()+s.length()-lev+1; // remove "/t", add"/"
            stack.push(len);
            // check if it is file
            if(s.contains(".")) maxLen = Math.max(maxLen, len-1); 
        }
        return maxLen;
    }
	
	public static int[][] paintFill(int screen[][], int row, int col, int old_color, int new_color){
		System.out.println("In bigger paintFill");
		System.out.println("row: "+row+" col: "+col);
		System.out.println("screen.length: "+screen.length);
		System.out.println("screen[0].length "+screen[0].length);
		
		if((row < 0) || (col < 0) || (row >= screen.length) || (col >= screen[0].length)){
			System.out.println("row: "+row+" col: "+col);
			return screen;
		}
		if(screen[row][col] == old_color){
			System.out.println("row: "+row+" col: "+col);
			screen[row][col] = new_color;
			
			paintFill(screen,row + 1, col, old_color, new_color); //right
			paintFill(screen,row, col + 1, old_color, new_color); //bottom
			paintFill(screen,row - 1, col, old_color, new_color); //up
			paintFill(screen,row, col - 1, old_color, new_color); //left	
		}
		return screen;
	}
	public static int[][] paintFill(int screen[][],int old_color, int new_color){
		System.out.println("Here");
		int [][] result =  paintFill(screen,0,1,old_color,new_color);
		return result;
	}
	
	public static int count_ways(int n, int denom){
		int next_denom = 0;
		switch(denom){
		case 25:
			next_denom = 10;
			break;
		case 10:
			next_denom = 5;
			break;
		case 5:
			next_denom = 1;
			break;
		case 1:
			return 1;
		}
		int ways = 0;
		for(int i = 0; i * denom <= n ; i++){
			ways += count_ways(n - i * denom , next_denom);
		}
		return ways;
	}
	
	static int makeChange(int total) {
    	if (total < 0) return 0;
    	// Select coins with the biggest denomination first.
    	return count_ways(total, 25);
    }
	
	public static void main(String args[]){
		/* Driver for generateNthFiboNum
		int res = generateNthFiboNum(3);
		System.out.println("3rd term in the fib seq is "+res);
		*/
		
		/* Driver for computePaths
		int mat[][] = new int[][]{{1,1,-1},{1,1,1},{-1,1,1}};
		int no_of_paths = computePaths(0,0,2,2,mat);
		System.out.println("No. of paths from 0,0 to 2,2 are: "+no_of_paths);
		
		int mat1[][] = new int[][]{{1,1,1,-1},{1,-1,1,1},{1,1,1,1}};
		int no_of_paths_1 = computePaths(0,0,2,3,mat1);
		System.out.println("No. of paths from 0,0 to 2,2 are: "+no_of_paths_1);
		*/
		
		/* Driver for enumerateSets
		char a[] = new char[]{'a','b','c'};
		enumerateSets(a);
		
		Set<Integer> originalSet = new HashSet<Integer>(Arrays.asList(1,2,3));
		Set<Set<Integer>> result = enumerateSets(originalSet);
		System.out.println("Powerset: "+result.toString());
		
		*/
		
		/*Driver for getPerms
		ArrayList<String> permutations = getPerms("abc");
		System.out.println(permutations.toString());
		
		*/
		/*
		List<String> result = generateAllCombinations(3);
		System.out.println("result: "+result.toString());
		System.out.println("counter: "+counter);
		*/
		
		/* Driver for longest absolute file path length
		int maxLength = lengthLongestPath("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext");
		System.out.println("maxLength: "+maxLength);
		*/
		
		/* Drive for paintFill
		int[][] screen = new int[][]{{1,2,2,1},{1,2,2,1},{1,2,2,1}};
		int [][] result = paintFill(screen,2,3);
		for(int i = 0 ; i < result.length; i++){
			for(int j = 0 ; j < result[0].length; j++){
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}
		
		*/
		
		//Driver for count_ways
		int no_of_ways = makeChange(25);
		System.out.println("Ways: "+no_of_ways);
		}
		
}

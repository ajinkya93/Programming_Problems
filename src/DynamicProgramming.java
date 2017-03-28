
public class DynamicProgramming {
	//method to compute number of paths from the top-left corner of the grid to its 
	//bottom right corner
	public static int computePaths(int a, int b){
		int numWays[][] = new int [a][b]; // Table to hold already computed ways to get 
		//to the cell i,j
		//Topmost row: There is only one way to reach a particular cell(from left) => 1
		for(int col = 0 ; col <= b-1 ; col++)
			numWays[0][col] = 1;
		//Leftmost column: There is only one way to reach a particular cell(from top) => 1
		for(int row = 0 ; row <= b-1 ; row++)
			numWays[row][0] = 1;
		for(int i = 1; i <= a-1; i++){
			for(int j = 1; j<= b-1 ; j++){
				numWays[i][j] = numWays[i-1][j] + numWays[i][j-1];
			}
		}
		return numWays[a-1][b-1];
	}
	
	//method to compute  paths using recursion
	public static int computePaths_recursion(int i, int j, int a, int b){
		if((i > a) || (j > b))
			return 0;
		if((i == a-1) && (j == b-1))
			return 1;
		return computePaths_recursion(i+1,j,a,b) + computePaths_recursion(i,j+1,a,b);
	}
	public static void main(String args[]){
		int no_of_paths = computePaths(15,9);
		int no_of_paths_rec = computePaths_recursion(0,0,15,9);
		System.out.println("no_of_paths from 0,0 to 15,9 is: "+no_of_paths);
		System.out.println("no_of_paths_rec from 0,0 to 15,9 is: "+no_of_paths_rec);
	}
}

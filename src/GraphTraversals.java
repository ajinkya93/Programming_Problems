import java.util.Iterator;
import java.util.LinkedList;

public class GraphTraversals {
	
	int no_of_vertices;
	int[][] adjMatrix;
	
	GraphTraversals(int no_of_vertices){
		this.no_of_vertices = no_of_vertices;
		adjMatrix = new int[no_of_vertices][no_of_vertices];
	}
	
	public void dfs(int [][] adjMatrix){
		boolean visited[] = new boolean[no_of_vertices];
		this.adjMatrix = adjMatrix;
		dfsUtil(0,visited);
	}
	
	public void dfsUtil(int v, boolean [] visited){

		visited[v] = true;
		System.out.print(v+" ");
		
		
		LinkedList<Integer> adjacent = new LinkedList<Integer>();
		for(int j = 0 ; j < no_of_vertices; j++){
			if( adjMatrix[v][j] == 1)
				adjacent.add(j);
		}
		Iterator<Integer> it = adjacent.iterator();
		while(it.hasNext()){
			int n = it.next();
			if(!visited[n])
				dfsUtil(n,visited);
		}
	}
	
	public void bfs(int v, int [][] adjMatrix){
		boolean visited[] = new boolean[adjMatrix.length];
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(v);
		while(!queue.isEmpty()){
			int n = queue.poll();
			if(!visited[n])
				visited[n] = true;
			System.out.print(n+" ");
			LinkedList<Integer> adjacent = new LinkedList<Integer>();
			for(int i = 0 ; i < adjMatrix.length; i++){
				if(adjMatrix[n][i] == 1)
					adjacent.add(i);
			 Iterator<Integer> it = adjacent.iterator();
			 while(it.hasNext()){
				 int next = it.next();
				 if(!visited[next]){
					 visited[next] = true;
					 queue.add(next);
				 }
			 }
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int adjMatrix [][] = new int[][]{{0,1,1,1,0,0,0},{0,0,0,0,1,1,0},{0,0,0,0,0,0,1,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0}};
		GraphTraversals g1 = new GraphTraversals(7);
		System.out.println("DFS traversal");
		g1.dfs(adjMatrix);
		System.out.println();
		System.out.println("BFS traversal");
		g1.bfs(0, adjMatrix);
	}

}

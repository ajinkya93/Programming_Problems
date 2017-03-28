import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class TreeNode{
	int data;
	TreeNode left, right;
	//TreeNode parent;	//Required for inOrderSuccessor(). Uncomment while calling this method
	
	TreeNode(){
		data = 0;
		left = right = null;
		//parent = null;	//Required for inOrderSuccessor(). Uncomment while calling this method
	}
	TreeNode(int data){
		this.data = data;
		left = right = null;
		//parent = null;	//Required for inOrderSuccessor(). Uncomment while calling this method
	}
}
public class BinaryTree extends TreeNode{
	TreeNode root;
	LinkedList<Integer> ll;
	Queue<TreeNode> q;
	ArrayList<LinkedList> al;
	static int count,level;
	static int procedureCalls;
	static boolean v1 = false, v2 = false; //Required for commonAncestor()
	
	public void inOrderRecursive(TreeNode root){
		if(root != null){
			inOrderRecursive(root.left);
			System.out.printf("%d\t",root.data);
			inOrderRecursive(root.right);
		}
	}
	public void preOrderRecursive(TreeNode root){
		if(root != null){
			System.out.printf("%d\t",root.data);
			preOrderRecursive(root.left);
			preOrderRecursive(root.right);
		}
	}
	
	public void postOrderRecursive(TreeNode root){
		if(root != null){
			postOrderRecursive(root.left);
			postOrderRecursive(root.right);
			System.out.printf("%d\t", root.data);
		}
	}
	
	public void postOrderIterative(TreeNode root){
		TreeNode current = root;
		java.util.Stack<TreeNode> s = new java.util.Stack<TreeNode>();
		int temp1 = 0;
		while((!s.empty()) || (current != null)){
			if(current != null){
				s.push(current);
				current = current.left;
			}
			else{
				TreeNode t = s.peek();
				if(t.right == null){
					t = s.pop();
					System.out.printf("%d\t",t.data);
					temp1 = t.data;
					t = s.peek();
					current = t.right;
					if(current.data == temp1){
						current = null;
						TreeNode t1 = s.pop();
						System.out.printf("%d\t",t1.data);
						temp1 = t1.data;
					}
				}
				
				else{
					current = t.right;
					if(current.data == temp1){
						current = null;
						TreeNode t1 = s.pop();
						System.out.printf("%d\t",t1.data);
						temp1 = t1.data;
					}
				}
			}
		}
		System.out.println();
	}
	public void inOrderIterative(TreeNode root){
		TreeNode current = root;
		java.util.Stack<TreeNode> s = new java.util.Stack<TreeNode>();
		while((!s.empty()) || (current != null)){
			if(current != null){
				s.push(current);
				current = current.left;
			}
			else{
				TreeNode t = s.pop();
				System.out.printf("%d\t",t.data);
				current = t.right;
			}
		}
		System.out.println();
	}
	public boolean isBalanced(BinaryTree t){
		root = t.root;
		if(root == null)
			return true;
		int a = maxDepth(root);
		int b = minDepth(root);
		System.out.println("maxdepth from root: "+a);
		System.out.println("minDepth from root: "+b);
		return(a - b <= 1);
		//return (maxDepth(root) - minDepth(root) <= 1);
	}
	
	private int minDepth(TreeNode root) {
		// TODO Auto-generated method stub
		if(root == null)
			return 0;
		return (1 + Math.min(minDepth(root.left), minDepth(root.right)));
	}

	public int maxDepth(TreeNode root){
		if(root == null)
			return 0;
		return(1 + Math.max(maxDepth(root.left),maxDepth(root.right)));
	}
	
	//create a binary tree of the minimal height from a given sorted array
	private void constructBTree(int array[]){
		TreeNode temp, root;
		java.util.LinkedList<TreeNode> queue = new java.util.LinkedList<TreeNode>();
		int i = 1, j = 2 , count = 0 , height = 0;
		root = new TreeNode(array[0]);
		
		temp = root; 
		queue.add(temp);
		
		while((i < array.length) && (j < array.length)){
			if(i < array.length){
				TreeNode lChild = new TreeNode(array[i]);
				temp.left = lChild;
				queue.add(lChild);
				count++;
				i = i +2;
			}
			
			if(j < array.length){
				TreeNode rChild = new TreeNode(array[j]);
				temp.right = rChild;
				queue.add(rChild);
				count++;
				j = j +2;
			}
			
			temp = queue.poll(); // Remove the node at the front of the queue to insert other array elements
			if(count == 2){ //if the node already has 2 children
				count = 0 ;
				height++;
				
				if(!queue.isEmpty())
					temp = queue.poll(); //Fetch the new node under which new array elements will be inserted
				else
					break;
			}
		}
		System.out.println("The binary tree was constructed successfully.");
		System.out.println("Height of the binary tree is : "+height);
		System.out.println("In order traversal of the tree:");
		inOrderRecursive(root);
		System.out.println();
		System.out.println("Pre order traversal of the tree:");
		preOrderRecursive(root);
		System.out.println();
		System.out.println("Post order traversal of the tree:");
		postOrderRecursive(root);
		System.out.println();
	}
	
	//method to create Linked Lists from the given binary tree
	private void createLL(){
		count = 0;
		level = 0;
		TreeNode temp = this.root;
		ll = new LinkedList<Integer>();
		q = new LinkedList<TreeNode>();
		al = new ArrayList<java.util.LinkedList>();
		
		if(temp == null){
			System.out.println("Tree is empty.");
			return;
		}
		else{
			ll.add(temp.data);
			q.add(temp);
			count++;
			System.out.println("count: "+count);
		}
		while(!q.isEmpty()){
			 temp = (TreeNode) q.poll();
			 if(count == (int)(Math.pow(2, level))){
				 count = 0;
				 level++;
				 al.add(ll);
				 ll = new LinkedList<Integer>();
				 addToLL(temp);
			 }
			 else
				 addToLL(temp);
		}
		if(ll.size() > 0)
			al.add(ll);
		System.out.println("Done");
	}
	
	private void addToLL(TreeNode temp){
		TreeNode lChild = temp.left;
		if(lChild == null)
			;
		else{
			ll.add(lChild.data);
			q.add(lChild);
			count++;
		}
		TreeNode rChild = temp.right;
		if(rChild == null)
			;
		else{
			ll.add(rChild.data);
			q.add(rChild);
			count++;
		}
	}
	
	/*
	//method to find inOrderSuccessor of a node
	public int inOrderSuccessor(TreeNode n){
	TreeNode current = n;
	
	if(current.right != null){
		current = current.right;
		while(current.left != null){
			current = current.left;
		}
		return current.data;
	}
	else{
		if(current.parent.left == current)
			return current.parent.data;
		else{
			if(current.parent.right == current)
				current = current.parent;
			while((current !=null) && (current != current.parent.left))
				current = current.parent;
			return current.parent.data;
		}
		//return current.data;
	}
	}
	*/
	//method to find the first common ancestor
	public TreeNode commonAncestor(TreeNode node, TreeNode n1, TreeNode n2){
		if(node == null)
			return null;
		else if(node.data == n1.data){
			v1 = true;
			return node;
		}
		else if(node.data == n2.data){
			v2 = true;
			return node;
		}
		
		TreeNode ca_in_left = commonAncestor(node.left, n1, n2);
		TreeNode ca_in_right = commonAncestor(node.right, n1, n2);
		
		if((ca_in_left != null) && (ca_in_right != null)){
			return node;
		}
		return (ca_in_left != null) ? ca_in_left: ca_in_right;
		
	}
	
	public static boolean containsTree(TreeNode t1, TreeNode t2){
		procedureCalls++;
		if(t2 == null){
			procedureCalls++;
			return true; 
		}//Empty tree is always a subtree
		else return subTree(t1,t2);
	}
	
	public static boolean subTree(TreeNode r1, TreeNode r2){
		if(r1 == null){
			procedureCalls++;
			return false;//Bigger Tree r1 is empty. More precisely in the first iteration, this implies, the root of the first tree is empty
		}
		procedureCalls = procedureCalls + 2;
		if(r1.data == r2.data){
			if(matchTree(r1,r2)){
				return true;//Return true if r2 is a subtree of r1. This will happen when root(r2) == root(r1)
			}
		}
		procedureCalls = procedureCalls + 2;
		return(subTree(r1.left,r2) || subTree(r1.right,r2));
	}
	
	public static boolean matchTree(TreeNode r1, TreeNode r2){
		if( r2 == null && r1 == null){
			procedureCalls = procedureCalls + 2;
			return true; //We have reached the child node in both the trees.
		}
		if(r1 == null || r2 == null){
			procedureCalls = procedureCalls + 2;
			return false; //We have reached the child node for one of the trees while the other tree still has children
		}
		if(r1.data != r2.data){
			procedureCalls = procedureCalls + 2;
			return false; //Unequal Nodes
		}
		return(matchTree(r1.left,r2.left) && matchTree(r1.right,r2.right));
	}
	
	void findSum(TreeNode head, int sum, ArrayList<Integer> buffer,int level) {
			 if (head == null) return;
			 int tmp = sum;
			 buffer.add(head.data);
			 for (int i = level;i >- 1; i--){
			 tmp -= buffer.get(i);
			 if (tmp == 0) print(buffer, i, level);
			 }
			 ArrayList<Integer> c1 = (ArrayList<Integer>) buffer.clone();
			 ArrayList<Integer> c2 = (ArrayList<Integer>) buffer.clone();
			 findSum(head.left, sum, c1, level + 1);
			 findSum(head.right, sum, c2, level + 1);
			 }
			
			 void print(ArrayList<Integer> buffer, int level, int i2) {
			 for (int i = level; i <= i2; i++) {
			 System.out.print(buffer.get(i) + " ");
			 }
			 System.out.println();
			 }
	
    public void computeSum(TreeNode head, int currSum, int givenSum, String path, TreeNode prev){
    	int count = 0;
    	boolean dont_go_along_left = false, dont_go_along_right = false;
    	if(head == null){
    		count++;
    		if(count == 1)
    			dont_go_along_left = true;
    		if(count == 2){
    			dont_go_along_right = true;
    		}
    		return;
    	}
    	currSum = currSum + head.data;
    	path = path+ Integer.toString(head.data)+" ";
    	if(currSum == givenSum){
    		System.out.println(path);
    		if(count == 2){
    			return;
    		}
    		currSum = 0;
    		head = prev;
    		path = " ";
    	}
    	//else{
    		prev = head;
    	//}
    		//if(!dont_go_along_left)
    			computeSum(head.left, currSum , givenSum, path, prev);
    	currSum = head.data;
    	path = " " +Integer.toString(head.data)+" ";
    	System.out.println("prev : "+prev.data);
    	//if(!dont_go_along_right)
    		computeSum(head.right, currSum , givenSum, path, prev);
    	
    }			 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		BinaryTree btree = new BinaryTree();
		TreeNode n1 = new TreeNode(1);
		btree.root = n1;
		TreeNode n2 = new TreeNode(2);
		n1.left = n2;
		TreeNode n3 = new TreeNode(3);
		n1.right = n3;
		/*
		TreeNode n4 = new TreeNode(4);
		n2.left = n4;
		TreeNode n5 = new TreeNode(5);
		n2.right = n5;
		TreeNode n6 = new TreeNode(6);
		n3.left = n6;
		TreeNode n7 = new TreeNode(7);
		n3.right = n7;
		*/
		
		
		//TreeNode n5 = new TreeNode(5);
		//n4.right = n5;
		boolean retval = btree.isBalanced(btree);
		if(retval)
			System.out.println("Balanced binary tree");
		else{
			System.out.println("Unbalanced binary tree");
		}
		
		/*
		System.out.println("Recursive InOrder Traversal of the Tree:");
		btree.inOrderRecursive(btree.root);
		System.out.println();
		System.out.println("Iterative InOrder Traversal of the Tree:");
		btree.inOrderIterative(btree.root);
		System.out.println("Recursive PreOrder Traversal of the Tree:");
		btree.preOrderRecursive(btree.root);
		System.out.println();
		System.out.println("Recursive PostOrder Traversal of the Tree:");
		btree.postOrderRecursive(btree.root);
		System.out.println();
		System.out.println("Iterative PostOrder Traversal of the Tree:");
		btree.postOrderIterative(btree.root);
		
		*/
		
		/*
		int a[] = new int[]{1,2,3,4,5};
		BinaryTree bTree = new BinaryTree();
		bTree.constructBTree(a);
		//bTree.inOrderRecursive(root);
		 * 
		 */
		
		/*Driver for createLL()
		btree.createLL();
		*/
		
		/*Driver for inOrderSuccessor()
		BinaryTree btree = new BinaryTree();
		TreeNode n1 = new TreeNode(20);
		btree.root = n1;
		TreeNode n2 = new TreeNode(8);
		n1.left = n2;
		n2.parent = n1;
		TreeNode n3 = new TreeNode(22);
		n1.right = n3;
		n3.parent = n1;
		TreeNode n4 = new TreeNode(4);
		n2.left = n4;
		n4.parent = n2;
		TreeNode n5 = new TreeNode(12);
		n2.right = n5;
		n5.parent = n2;
		TreeNode n6 = new TreeNode(10);
		n5.left = n6;
		n6.parent = n5;
		TreeNode n7 = new TreeNode(14);
		n5.right = n7;
		n7.parent = n5;
		
		int result = btree.inOrderSuccessor(n7);
		System.out.println("In-order successor of "+n7.data+" is : "+result);
		*/
		
		/*Driver for commonAncestor()
		 * 
		 * 
		BinaryTree btree = new BinaryTree();
		TreeNode n1 = new TreeNode(1);
		btree.root = n1;
		TreeNode n2 = new TreeNode(2);
		n1.left = n2;
		TreeNode n3 = new TreeNode(3);
		n1.right = n3;
		TreeNode n4 = new TreeNode(4);
		n2.left = n4;
		TreeNode n5 = new TreeNode(5);
		n2.right = n5;
		TreeNode n6 = new TreeNode(6);
		n3.left = n6;
		TreeNode n7 = new TreeNode(7);
		n3.right = n7;
		TreeNode n8 = new TreeNode(8);
		
		TreeNode ca = btree.commonAncestor(btree.root, n4, n7);
		if(v1 && v2){
			System.out.println("The first common ancestor of "+n4.data+" and "+n7.data+" is "+ca.data);
		}
		else{
			System.out.println("One or both of the nodes from "+n4.data+" and "+n7.data+" does not exist");
		}
		
		*/
		/* Driver for containsTree() 
		 * 
		BinaryTree btree = new BinaryTree();
		TreeNode n1 = new TreeNode(1);
		btree.root = n1;
		TreeNode n2 = new TreeNode(2);
		n1.left = n2;
		TreeNode n3 = new TreeNode(3);
		n1.right = n3;
		TreeNode n4 = new TreeNode(4);
		n2.left = n4;
		TreeNode n5 = new TreeNode(5);
		n2.right = n5;
		//TreeNode n6 = new TreeNode(6);
		//n3.left = n6;
		//TreeNode n7 = new TreeNode(7);
		//n3.right = n7;
		
		BinaryTree subtree = new BinaryTree();
		TreeNode subtree_n1 = new TreeNode(11);
		subtree.root = subtree_n1;
		TreeNode subtree_n2 = new TreeNode(12);
		subtree_n1.left = subtree_n2;
		TreeNode subtree_n3 = new TreeNode(13);
		subtree_n1.right = subtree_n3;
		TreeNode subtree_n4 = new TreeNode(14);
		subtree_n2.left = subtree_n4;
		TreeNode subtree_n5 = new TreeNode(15);
		subtree_n2.right = subtree_n5;
		
		boolean issubtreeSubTreeofbtree = containsTree(btree.root, subtree.root);
		
		if(issubtreeSubTreeofbtree)
			System.out.println("Yes, second tree is a subtree of first");
		else
			System.out.println("No, second tree is not a subtree of first");
		
		System.out.println("NUmber of procedure calls: "+procedureCalls);
		*/
		
		/*Driver for findSum() 
		ArrayList<Integer> buffer = new ArrayList<Integer>();
		BinaryTree btree = new BinaryTree();
		TreeNode n1 = new TreeNode(1);
		btree.root = n1;
		TreeNode n2 = new TreeNode(4);
		n1.left = n2;
		TreeNode n3 = new TreeNode(1);
		n2.left = n3;
		TreeNode n4 = new TreeNode(1);
		n2.right = n4;
		//btree.findSum(btree.root, 5, buffer, 0);
		btree.computeSum(btree.root,0,5," ",btree.root);
		*/
	}

}

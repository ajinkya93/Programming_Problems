import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

abstract class TestingConcepts {
	
	final int a;
	final int b;
	
	TestingConcepts(){
		this.a = geta();
		this.b = getb();
		System.out.println("Superclass constructor");
	}

	abstract int geta();
	abstract int getb();
	
	
	public static void main(String[] args) {
		
		TestingConcepts2 obj = new TestingConcepts2();
		System.out.println("obj.val: "+obj.val);
		// TODO Auto-generated method stub
		/*
		ArrayList<Integer> al = new ArrayList<Integer>();
		al.add(1);
		al.add(2);
		al.add(3);
		al.add(4);
		al.add(5);
		
		System.out.println("al.get(2): "+al.get(2));
		
		al.remove(2);
		
		System.out.println("al.get(2): "+al.get(2));
		
		
		
	//	Vector v = new Vector();
	//	v.add(1);
	//	v.add(2);
	//	v.add(3);
	//	v.add(4);
	//	v.add(5);
	//	v.add(6);
	//	v.add(7);
	//	v.add(8);
	//	v.add(9);
	//	v.add(10);
	//	v.add(11);
	//	v.add(12);
	//	v.add(13);
	//	v.add(14);
	//	Iterator it = v.iterator();
	//	while(it.hasNext()){
	//		System.out.println(it.next());
	//	}
	*/	
	}
    

}

class TestingConcepts2 extends TestingConcepts{
	int val;
	
	TestingConcepts2(){
		System.out.println("Subclass constructor");
	}
	TestingConcepts2(int val){
		this.val = val;
		System.out.println("Subclass constructor");
	}
	
	int geta(){
		return val;
	}
	
	int getb(){
		return val;
	}
	
	public static void main(String args[]){
		TestingConcepts2 obj = new TestingConcepts2(5);
	}
}

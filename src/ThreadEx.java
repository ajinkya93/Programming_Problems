import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/* Creating threads by extending Thread class */

class Table{
	
	public static  void printTable(int n){
		synchronized(Table.class){ //synchronized block
			for(int i = 1 ; i <= 10; i++)
				System.out.println(n*i);
			System.out.println("*******************************************************");
			try{
				Thread.sleep(2000);
			}
			catch(InterruptedException ie){
				System.out.println(ie);
			}
		} //synchronized block end
	}
}

class ThreadSync1 extends Thread{
	Table t;  //Required for synchronization example
	ThreadSync1(Table t){
		this.t = t;
	}
	
	public void run(){
		t.printTable(50);
	}
}

class ThreadSync2 extends Thread{
	Table t; //Required for synchronization example
	ThreadSync2(Table t){
		this.t = t;
	}
	public void run(){
		t.printTable(100);
	}
}
public class ThreadEx extends Thread {
   
	ThreadEx(String name){
		this.setName(name);
	}
	

	/* One implementation of run() */
	public void run(){
		System.out.println(Thread.currentThread().getName()+" is RUNNING.");
		if(Thread.currentThread().isDaemon()){
			try{
				
				for(int i = 5 ; i > 0 ; i--){
					System.out.println(currentThread().getName()+" is going to sleep for "+(i*1000)+" seconds.");
					Thread.sleep(i*1000);
				}
				
				Thread.sleep(2000);
			}
			catch(InterruptedException ie){
				System.out.println(ie);
			}
			System.out.println(Thread.currentThread().getName()+" has ended.");
		}
		
		else{
			System.out.println(Thread.currentThread().getName()+" is running normally.");
		}
		
			
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		ThreadEx t1 = new ThreadEx("THREAD-ONE");
		ThreadEx t2 = new ThreadEx("THREAD-TWO");
		ThreadEx t3 = new ThreadEx("THREAD-THREE");
		ThreadEx t4 = new ThreadEx("THREAD-FOUR");
		ThreadEx t5 = new ThreadEx("THREAD-FIVE");
		ThreadEx t6 = new ThreadEx("THREAD-SIX");
		ThreadEx t7 = new ThreadEx("THREAD-SEVEN");
		*/
		
		/* Testing out variuos methods of Thread class
		System.out.println(t1.getName()+"'s current priority is "+t1.getPriority());
		t1.setPriority(Thread.MAX_PRIORITY);
		System.out.println(t2.getName()+"'s current priority is "+t2.getPriority());
		t2.setPriority(Thread.MIN_PRIORITY);
		
		System.out.println("Is "+t1.getName()+" a daemon thread? "+t1.isDaemon());
		System.out.println("Is "+t2.getName()+" a daemon thread? "+t2.isDaemon());
		
		*/
		//t1.setDaemon(true);
		//if(t1.isDaemon())
			//System.out.println("t1 is now a Daemon thread");
		/*
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		*/
		
		/*Driver for Thread Pool using ExecutorService and Executors classes
		ExecutorService executor = Executors.newFixedThreadPool(7);
		executor.execute(t1);
		executor.execute(t2);
		executor.execute(t3);
		executor.execute(t4);
		executor.execute(t5);
		executor.execute(t6);
		executor.execute(t7);
		
		executor.shutdown();
		while(!executor.isTerminated()){}
			System.out.println("All threads have finished.");
			*/
		
		/* Driver for ThreadGroup class
		ThreadGroup threadGroup = new ThreadGroup("Primary ThreadGroup");
		Thread tgt1 = new Thread(threadGroup,"TG Thread 1");
		Thread tgt2 = new Thread(threadGroup,"TG Thread 2");
		tgt1.start();
		tgt2.start();
		System.out.println("ThreadGroup Name: "+threadGroup.getName());
		System.out.println("Number of threads from the group currently active: "+threadGroup.activeCount());
		System.out.println("ThreadGroup parent: "+threadGroup.getParent());
		threadGroup.list();
		System.out.println("Interrupting the threadGroup now.");
		threadGroup.interrupt();
		System.out.println("Number of threads from the group active after the interrupt: "+threadGroup.activeGroupCount());
		threadGroup.list();
		*/
		
		/*Driver for synchronization*/
		Table tableObj = new Table();
		ThreadSync1 ts1 = new ThreadSync1(tableObj);
		ThreadSync2 ts2 = new ThreadSync2(tableObj);
		
		ts1.start();
		ts2.start();
	}

}


/*Creating Threads by implementing Runnable interface
public class ThreadEx implements Runnable{
	
	String name;
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void run(){
		System.out.println(this.getName() +" is running.");
	}
	
	public static void main(String args[]){
		ThreadEx tx1 = new ThreadEx();
		ThreadEx tx2 = new ThreadEx();
		
		Thread t1 = new Thread(tx1);
		Thread t2 = new Thread(tx2);
		
		tx1.setName("FT");
		tx2.setName("ST");
		
		t1.start();
		
		t2.start();
		
	}
}

*/
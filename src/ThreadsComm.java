class BankTransaction{
	
	int amount = 1000;
	
	public synchronized void deposit(int amt){
		System.out.println("Depositing "+amt+"...");
		amount += amt;
		System.out.println("Deposit of "+amt+" completed. New balance: "+amount);
		notify(); //Use of notify()
	}
	
	public synchronized void withdraw(int amt){
		try{
		if((amt < 0) || (amount - amt < 1000)){
			System.out.println("Amount invalid or too big to withdraw. Waiting until specified amount is corrected/ more money is deposited.");
			wait(); //Use of wait()
		}	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		amount -= amt;
		System.out.println(amt+" was successfully withdrawn. New Balance: "+amount);
	}
}
public class ThreadsComm extends Thread {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final BankTransaction b = new BankTransaction();
		new Thread(){public void run(){b.withdraw(20000);}}.start();
		new Thread(){public void run(){b.withdraw(10000);}}.start();
		new Thread(){public void run(){b.deposit(20000);}}.start();
		new Thread(){public void run(){b.deposit(10000);}}.start();
	}
}

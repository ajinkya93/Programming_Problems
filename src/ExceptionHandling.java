import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ExceptionHandling {

	public static void fileRead(String fname) throws NullPointerException,FileNotFoundException,IOException{
		BufferedReader br = null;
		FileReader fr = null;
		try{
			fr = new FileReader(fname);

			br = new BufferedReader(fr);
			String str = br.readLine();
			while(str != null){
				System.out.println(str);
				str = br.readLine();
			}
		
		}catch(FileNotFoundException fne){
			System.out.println("File not found. Error msg: "+fne);
		}catch(IOException ie){
			System.out.println("Error opening the file. Error msg: "+ie);
		}catch(NullPointerException npe){
			System.out.println("Error opening the file. Error msg: "+npe);
		}
		
		finally{
			if(br != null)
				br.close();
			if(fr != null)
				fr.close();
		}
	}
	
	public static void main(String[] args) throws NullPointerException,FileNotFoundException,IOException,CustomException {
		// TODO Auto-generated method stub
		//fileRead("D:\\Eclipse_Projects\\Programming _Problems\\src\\Sample.txt");
		//fileRead("Sample.txt");
		//CustomException.fileWrite("D:\\Eclipse_Projects\\Programming _Problems\\src\\Sample.txt");
		CustomException.fileWrite("Sample");
		System.out.println("Success!!");
	}

}

class CustomException extends Exception{
	CustomException(String s){
		super(s);
	}
	
	public static void fileWrite(String fname) throws CustomException,IOException,FileNotFoundException,NullPointerException{
		FileWriter fw = null;
		BufferedWriter bw = null;	
		try{
			if(fname.equals("Sample")){
				throw new CustomException("Custom Exception thrown as the file name is Sample");
			}
			System.out.println("Here");
			fw = new FileWriter(fname,true);
			bw = new BufferedWriter(fw);
			bw.write("THIS LINE WAS ALSO WRITTEN TO THE FILE FROM A JAVA PROGRAM. HAHAHAHA(Evil Laugh)");
			bw.newLine();
			bw.flush();
		}catch(CustomException cse){
			System.out.println(cse);
			return;
		}catch(FileNotFoundException fne){
			System.out.println(fne);
		}
		finally{
			System.out.println("Executing finally block...");
			if(bw != null)
				bw.close();
			if(fw != null)
				fw.close();
		}
	}
}
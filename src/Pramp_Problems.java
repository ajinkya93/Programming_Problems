import java.util.ArrayList;
import java.util.List;
public class Pramp_Problems {
	
	public static ArrayList<Character> sentReverseImpl2(ArrayList<Character> sentence){
		ArrayList<Character> result = new ArrayList<Character>();
		return result;
	}

	//Implementation 1
	public static ArrayList<Character> sentReverse(ArrayList<Character> sentence){
		ArrayList<Character> result = new ArrayList<Character>();
		//System.out.println(sentence.toString());
		
		
		
		int i = sentence.size()-1;
		//int index = 0;
		for(; i >= 0 ;){
			ArrayList<Character> temp = new ArrayList<Character>();
			while((i >= 0) && (sentence.get(i) != ' ')){
				temp.add(sentence.get(i));
				i--;
				//addTheWordtoResult(temp);
			}
			for(int j = temp.size()-1; j >=0; j--){
				//System.out.println("j: "+j);
				result.add(temp.get(j));
				//index++;
			}
			if(i != -1)
				result.add(' ');
			i--;
		}
		
		return result;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//List<Character> sentence = ['p', 'e', 'r', 'f', 'e', 'c', 't', '  ', 'm', 'a', 'k', 'e', 's', '  ', 'p', 'r', 'a', 'c', 't', 'i', 'c', 'e'];
		ArrayList<Character> sentence = new ArrayList<Character>();
		sentence.add('a');
		sentence.add('b');
		sentence.add('c');
		sentence.add(' ');
		sentence.add('d');
		sentence.add('e');
		sentence.add('f');
		sentence.add(' ');
		sentence.add('g');
		sentence.add('h');
		sentence.add('i');
		ArrayList<Character> result = sentReverse(sentence);
		System.out.println(result.toString());
	}

}

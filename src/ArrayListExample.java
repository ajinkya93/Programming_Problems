import java.util.ArrayList;
class Book{
	String name;
	int id;
	Book(String name, int id){
		this.name = name;
		this.id = id;
	}
	
	public String getName(){
		return name;
	}
	
	public int getID(){
		return id;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj == null)
			return false;
		if(obj == this)
			return true;
		if(!(obj instanceof Book))
			return false;
		Book bookObj = (Book)obj;
		if((this.name == bookObj.name)&&(this.id == bookObj.id))
			return true;
		return false;
		
	}
}
public class ArrayListExample {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Book> books = new ArrayList<Book>();

		
		Book b1 = new Book("Book1",12345);
		Book b2 = new Book("Book2",22345);
		Book b3 = new Book("Book3",12345);
		Book b4 = new Book("Book4",112233);
		Book b5 = new Book("Book1",12345);
		Book b[] = new Book[]{b1,b2,b3,b4,b5};
		
		boolean f = true;
		for(int i = 0 ; i < 5 ; i++){
			for(int j = 0 ; j < books.size(); j++){
				if(b[i].equals(books.get(j))){
					System.out.println("The object with name: "+books.get(j).getName()+" and ID: "+books.get(j).getID()+" already exists and cannot be added.");
					f = false;
					break;
				}
			}
				if(f){
					books.add(b[i]);
					//System.out.println("The object with name: "+books.get(j).getName()+" and ID: "+books.get(j).getID()+" was successfully added.");
				}
	
		}
		
		System.out.println("Printing the contents of Al books");
		for(Book book: books){
			System.out.println("Name: "+book.getName()+" ID: "+book.getID());
		}
	}
}



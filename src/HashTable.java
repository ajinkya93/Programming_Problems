
class HashEntry{
	int key;
	int val;
	
	HashEntry(int key, int val){
		this.key = key;
		this.val = val;
	}
	
	public int getKey(){
		return key;
	}
	
	public int getVal(){
		return val;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj == null)
			return false;
		if(obj == this)
			return true;
		if(!(obj instanceof HashEntry))
			return false;
		HashEntry he = (HashEntry)obj;
		if(this.key == he.key)
			return true;
		return false;
	}
}

public class HashTable {

HashEntry[] table;
HashTable(){
	table = new HashEntry[16];
}

public void put(int key,int val){

	int hash = hashCode(key);
	System.out.println("hash: "+hash);
	//int hash = he.hashCode();
	int prev = hash; //To avoid infinite loop
	while(table[hash] != null && table[hash].getKey() != key){
		hash = (hash+1)%16;
		if(hash == prev){
			System.out.println("No space to enter new key-value pair");
			return;
		}		
	}
		table[hash] = new HashEntry(key,val);
		System.out.println(key+" and "+val+" pair was successfully inserted in the HT");
	//table[hash] = he;
}

public int get(int key){
	int hash = hashCode(key);
	//int hash = he.hashCode();
	
	while(table[hash] != null && table[hash].getKey() != key)
		hash = (hash + 1)%16;
	if(table[hash] == null){
		System.out.println("No entry for the given key found");
		return -1;
	}
	
	/*
	if(table[hash] == null){
		System.out.println("No entry for the given key found");
		return -1;
	}
	*/
	return(table[hash].getVal());
}

public int hashCode(int key){
	int result = 17;
	result = 37 * result + key;
	return result%16;
}



public static void main(String ar[]){
	
	HashTable ht = new HashTable();
	ht.put(1, 100);
	ht.put(2, 200);
	ht.put(1,300);
	int res_1 = ht.get(1);
	System.out.println("Value for key 1 is "+res_1);
	int res_2 = ht.get(2);
	System.out.println("Value for key 2 is "+res_2);
	ht.get(3);
}


}

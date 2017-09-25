
public class Table<K extends Identifiers, V extends Sets>{
	
	List<Tuple> table;
	
	Table(){
		table = new List<>();
	}

	public void init() {
		this.table = new List<>();
	}

	public void add(Identifiers key, Sets value) {
		Tuple newtuple = new Tuple(key, value);
		table.insert(newtuple);	
	}

	public Sets find(Identifiers key) {
		table.goToFirst();
		for(int i = 0; i < table.size(); i++){
			String compare_key = table.retrieve().key.identifier.toString();
			if(compare_key.equals(key.identifier.toString())){
//				System.out.println("geen match");
				return table.retrieve().value;
			} else{
				table.goToNext();
			}
		}
		return null;
	}
	
	public Table<K, V> clone() {
		Table<K,V> new_table = new Table<>();
		table.goToFirst();
		for( int i = 0; i<table.size(); i++){
			Identifiers key = table.retrieve().key;
			Sets value = table.retrieve().value;
			new_table.add(key, value);
		}
		return new_table;
		
	}

	
	private class Tuple implements Data<Tuple>{
	
		Identifiers key;
		Sets value;
		
		public Tuple(){
			key = null; 
			value = null;
		}
		
		public Tuple(Identifiers key, Sets value){
			this.key = key;
			this.value = value;
		}
		
		public Table<K,V>.Tuple clone(){
			Tuple clone = new Tuple(this.key, this.value);
			return clone;
		}
		
		public int compareTo(Table<K, V>.Tuple compare){
			if(key.equals(compare.key)){
				return 1;
			}
			else{
				return 0;
			}
		}

	}
	
}

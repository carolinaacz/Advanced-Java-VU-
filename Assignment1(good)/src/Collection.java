public class Collection {
	private Identifier[] idArray;
	private static final int MAX_AMOUNT_OF_ELEMENTS = 20;
	private int amountElements;

	public Collection() {
		idArray = new Identifier[amountElements];
		amountElements = 0;
	}

	private void copyElements(Identifier[] dest, Identifier[] src, int amount) {
		for (int i = 0; i < amount; i++) {
			dest[i] = new Identifier(src[i]);
		}
	}

	public Collection(Collection src) {
		idArray = new Identifier[src.idArray.length];
		amountElements = src.amountElements;
		copyElements(idArray, src.idArray, amountElements);
	}
	
	public Collection(Collection src, int shorter) {
		idArray = new Identifier[src.idArray.length-shorter];
		amountElements = src.amountElements;
		copyElements(idArray, src.idArray, amountElements);
	}
	
	void init() {
		idArray = new Identifier[0];
		amountElements = 0;
	}
	
	public void add(Identifier identifier) throws MyOwnException{
		if (amountElements == idArray.length) {
			increaseSize();
		}
		idArray[amountElements] = new Identifier(identifier);
		amountElements += 1;
		
	}
	
	public int size() throws MyOwnException {
		return amountElements;
	}
	
	public void print() throws MyOwnException {
		for(int i=0; i<idArray.length; i++){
			System.out.print(idArray[i].identifier);
			if(i != idArray.length -1){
				System.out.print(" ");
			}
		}
		return;
	}
	
	private void increaseSize () throws MyOwnException{
		Identifier[] result = new Identifier[1 + idArray.length];
		if(idArray.length == MAX_AMOUNT_OF_ELEMENTS){
			throw new MyOwnException("Too much identifiers");
		}
		copyElements(result, idArray, amountElements);
	        idArray = result;
	    }
	
	public boolean isEmpty () throws MyOwnException {
		return amountElements == 0;
	}
	
	public Identifier getnextID(Collection collection, int i) throws MyOwnException{
		Identifier identifierAt = collection.idArray[(amountElements-(i+1))];
		return identifierAt;
	}
	
	public boolean has_ID(Identifier identifier) throws MyOwnException{
		for(int j =0; j < size();j++){
			if(idArray[j].identifier.toString().equals(identifier.identifier.toString())){
				return true;
			}
		}
		return false;
	}
	
	public Collection delete(Identifier identifier, Collection difference) throws MyOwnException{
		for (int i = 0; i < size(); i++) {
			if (difference.idArray[i].identifier.toString().equals(identifier.identifier.toString())){
				for (int j = i; j < size()-1; j++) {
					difference.idArray[j] = difference.idArray[j + 1];
				}
				difference.amountElements -= 1;
				difference = new Collection(difference, 1);
				return difference;
			}
		}
		return difference;
	}
		
	

	public Collection intersection(Collection collection2) throws MyOwnException{
		Collection intersection = new Collection();
		for(int i=0; i < size() ;i++){
			for(int j =0; j < collection2.size();j++){
				if(idArray[i].identifier.toString().equals(collection2.idArray[j].identifier.toString())){
					intersection.add(idArray[i]);
			}
		}
		
	}
		return intersection;
	
}
	public Collection difference(Collection collection2) throws MyOwnException{
		Collection difference = new Collection(this);
		for(int i=0; i < size() ;i++){
			for(int j =0; j < collection2.size();j++){
				if(idArray[i].identifier.toString().equals(collection2.idArray[j].identifier.toString())){
					difference = difference.delete(idArray[i], difference);
				}
			}
		}
		
		return difference;
	
}
	public Collection union(Collection collection2) throws MyOwnException{
		Collection union = new Collection(this);
		for(int i=0; i < collection2.size() ;i++){
			if(has_ID(collection2.idArray[i]) == false){
				union.add(collection2.idArray[i]);
			}
		}
		return union;
		
	}
	
	public Collection symdifference(Collection collection2) throws MyOwnException{
		Collection symdifference = new Collection();
		Collection add1 = new Collection();
		Collection add2 = new Collection();
		add1 = this.difference(collection2);
		add2 = collection2.difference(this);
		symdifference = add1.union(add2);
		return symdifference;

}
		
}
public class Sets<E extends Data<E>>implements SetInterface<E>{
	
	private NaturalNumbers[] idArray;
	private int amountElements;
	

	public Sets() {
		idArray = new NaturalNumbers[amountElements];
		amountElements = 0;
	}

	public void copyElements(NaturalNumbers[] dest, NaturalNumbers[] src, int amount) throws APException {
		for (int i = 0; i < amount; i++) {
			dest[i] = new NaturalNumbers(src[i].NatNumbers.toString());
		}
	}

	public Sets(Sets src) throws APException {
		idArray = new NaturalNumbers[src.idArray.length];
		amountElements = src.amountElements;
		copyElements(idArray, src.idArray, amountElements);
	}
	
	public Sets(Sets src, int shorter) throws APException {
		idArray = new NaturalNumbers[src.idArray.length-shorter];
		amountElements = src.amountElements;
		copyElements(idArray, src.idArray, amountElements);
	}
	
	public void init() {
		idArray = new NaturalNumbers[0];
		amountElements = 0;
	}
	
	public void add(NaturalNumbers numbers) throws APException{
		for(int i=0; i<idArray.length; i++){
			String check1 = idArray[i].NatNumbers.toString();
			String check2 = numbers.NatNumbers.toString();
			if(check1.equals(check2)){
				return;
			}
		}
		if (amountElements == idArray.length) {
			increaseSize();
		}
		idArray[amountElements] = numbers;
		amountElements += 1;
	}
	
	public int size(){
		return amountElements;
	}
	
	private void increaseSize () throws APException{
	NaturalNumbers[] result = new NaturalNumbers[1 + idArray.length];
	copyElements(result, idArray, amountElements);
        idArray = result;
	}
	
	public void print() throws APException {
		if(idArray.length == 0){
			System.out.println(" ");
		}else{
			for(int i=0; i<idArray.length; i++){
				System.out.print(idArray[i].NatNumbers.toString());
				if(i != idArray.length -1){
					System.out.print(" ");
				}
				if(i == idArray.length-1){
					System.out.println(" ");
				}
			}
		}
		return;
	}
	
	
	
	public boolean isEmpty () throws APException {
		return amountElements == 0;
	}
	
	public NaturalNumbers getnextID(Sets set, int i) throws APException{
		NaturalNumbers identifierAt = set.idArray[(amountElements-(i+1))];
		return identifierAt;
	}
	
	public boolean has_ID(NaturalNumbers identifier) throws APException{
		for(int j =0; j < size();j++){
			if(idArray[j].NatNumbers.toString().equals(identifier.NatNumbers.toString())){
				return true;
			}
		}
		return false;
	}
	
	public Sets delete(NaturalNumbers identifier, Sets difference) throws APException{
		for (int i = 0; i < size(); i++) {
			if (difference.idArray[i].NatNumbers.toString().equals(identifier.NatNumbers.toString())){
				for (int j = i; j < size()-1; j++) {
					difference.idArray[j] = difference.idArray[j + 1];
				}
				difference.amountElements -= 1;
				difference = new Sets(difference, 1);
				return difference;
			}
		}
		return difference;
	}
		
	

	public Sets intersection(Sets set2) throws APException{
		Sets intersection = new Sets();
		for(int i=0; i < size() ;i++){
			for(int j =0; j < set2.size();j++){
				if(idArray[i].NatNumbers.toString().equals(set2.idArray[j].NatNumbers.toString())){
					intersection.add(idArray[i]);
			}
		}
		
	}
		return intersection;
	
}
	public Sets difference(Sets set2) throws APException{
		Sets difference = new Sets(this);
		for(int i=0; i < size() ;i++){
			for(int j =0; j < set2.size();j++){
				if(idArray[i].NatNumbers.toString().equals(set2.idArray[j].NatNumbers.toString())){
					difference = difference.delete(idArray[i], difference);
				}
			}
		}
		
		return difference;
	
}
	public Sets union(Sets set2) throws APException{
		Sets union = new Sets(this);
		for(int i=0; i < set2.size() ;i++){
			if(has_ID(set2.idArray[i]) == false){
				union.add(set2.idArray[i]);
			}
		}
		return union;
		
	}
	
	public Sets symdifference(Sets set2) throws APException{
		Sets symdifference = new Sets();
		Sets add1 = new Sets();
		Sets add2 = new Sets();
		add1 = this.difference(set2);
		add2 = set2.difference(this);
		symdifference = add1.union(add2);
		return symdifference;

	}
	

	public Sets clone() {
		// TODO Auto-generated method stub
		return null;
	}





}
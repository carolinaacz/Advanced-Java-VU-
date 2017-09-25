public class Identifiers implements IdentifiersInterface{

	
		
	String identifier;
	
	Identifiers(){
		identifier = new String();
		/*(identifier.append('a');*/
	}	
	
	Identifiers(String c){
		identifier = new String();
		identifier = c;
	}
	
	public void init() {
		identifier = new String();
	}

	public int length() {
		return identifier.length();
	}

	public boolean compareTo(Identifiers other) {
		if(identifier.equals(other)){
			return true;
		} else{
			return false;
		}
	}

	public String ReturnString(){
		return identifier;
	}


}
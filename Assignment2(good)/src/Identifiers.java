public class Identifiers<E extends Data<E>> implements IdentifiersInterface{

		
	StringBuffer identifier;
	
	Identifiers(){
		identifier = new StringBuffer();
	}	
	
	Identifiers(String string){
		identifier = new StringBuffer();
	}	
	
	Identifiers(char c){
		identifier = new StringBuffer(Character.toString(c));
	}
	
	public void addChar(char i){
		identifier.append(i);
	}
	
	public String charToString(){
		return identifier.toString();
	}
	
	char getChar(int i){
		return identifier.charAt(i);
	}

	public int length() {
		return identifier.length();
	}

	public boolean compare(Identifiers other) {
		return false;
	}

	public void empty() {
		identifier = new StringBuffer();
	}

}
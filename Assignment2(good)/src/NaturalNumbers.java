import java.lang.StringBuffer;

public class NaturalNumbers implements Clonable, Data, NaturalNumbersInterface  {
	
	
	
	StringBuffer NatNumbers;

	public NaturalNumbers(){
		NatNumbers = new StringBuffer();
	}
	
	public NaturalNumbers(String numbers) throws APException{
		if(parseNatural(numbers) == true){
			NatNumbers = new StringBuffer(numbers);
		} else{
			throw new APException("Is not an natural number \n");
		}
	}
	
	public void add(String numbers) throws APException{
		if(parseNatural(numbers) == true){
			if(parseZero() == true){
				NatNumbers.append(numbers);
			}
		} else{
			throw new APException("Is not an natural number \n");
		}
	}

	private boolean parseZero()throws APException{
		String check_zero = NatNumbers.toString();
		if(check_zero.length() > 0){
			Character char_zero = check_zero.charAt(0);
			if(char_zero == '0' && check_zero.length() > 0){
				throw new APException("After 0 comes , or } \n");
			}
		}
		return true;
		
	}
	
	private boolean parseNatural(String numbers){
		if(numbers.length() == 0){
			return true;
		} else if (numbers.charAt(0) == '0' && numbers.length() == 1){
			return true;
		} else if(numbers.charAt(0) == '0'){
			return false;
		}
		
		for(int i=0; i< numbers.length(); i++){
			if(!Character.isDigit(numbers.charAt(i))){
				return false;
			}
				
		}
		return true;
	}
	
	public int parseLength() {
		
		return NatNumbers.length();
	}

	public String parseNumber(){
		
		return NatNumbers.toString();
	}

	
	public Object clone(){
		NaturalNumbers clone_nat = null;
		String get_string = new String(NatNumbers.toString());
		try {
			clone_nat = new NaturalNumbers(get_string);
		} catch (APException e) {
			System.out.printf("natural numbers can't be cloned \n");
		}
		return clone_nat;	
	}

	public boolean equals(NaturalNumbers nat_numb) {
		String own_nat = NatNumbers.toString();
		String checker_nat = nat_numb.parseNumber();
		
		if(checker_nat.equals(own_nat)){
			return true;
		}
		return false;
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}


}


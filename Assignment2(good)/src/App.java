import java.util.Scanner;

public class App {
	
	
	static Table table = new Table();
	int brackets = 0;

	public static void main(String[] args) {
		new App().start();
	}

	public void start() {
		Scanner input = new Scanner(System.in);
		while (input.hasNextLine()) {
			try {
				Scanner user_input = new Scanner(input.nextLine());
				parseStatement(user_input);
			} catch (APException e) {
				System.out.printf("%s", e);
			}
		}
	}

	private void parseStatement(Scanner user_input) throws APException {
		user_input.useDelimiter("");
		user_input.skip("\\s*");
		if (user_input.hasNext("\\/")) {
			return;
		} else if (user_input.hasNext("\\?")) {
			user_input.next();
			Sets printset = parseExpression(user_input);
			if(user_input.hasNext()){
				throw new APException("No end of line \n");
			}
			printset.print();
		} else if (user_input.hasNext("[a-zA-Z]")) {
			parseAssignment(user_input);
		} else if (!user_input.hasNext()){
			throw new APException("Scanner is empty \n");
		} else {
			throw new APException("Wrong character \n");
		}
	}
	

	private void parseAssignment(Scanner assignment_input) throws APException {
		Identifiers name_identifier = parseFirstLetterBefore(assignment_input);
		assignment_input.skip("\\s*");
		
		if(assignment_input.hasNext("=")){
			assignment_input.next();
			Sets sets = parseExpression(assignment_input);
			if(assignment_input.hasNext()){
				throw new APException("Wrong statement \n");
			}
			table.add(name_identifier, sets);
		} else {
			throw new APException("Wrong character \n");
		}
	}
	

	private Sets parseExpression(Scanner expression_input) throws APException {
		expression_input.useDelimiter("");
		expression_input.skip("\\s*");
	
		Sets final_term = new Sets();
		Sets calculate_term = new Sets();
		final_term = parseTerm(expression_input);
		Character additive;
		
		while(expression_input.hasNext() && hasAdditive(expression_input) == true && !expression_input.hasNext("\\)")){
			additive = nextAdditive(expression_input);
			calculate_term = parseTerm(expression_input);
			if(additive.equals('+')){
				final_term = final_term.union(calculate_term);
			}else if(additive.equals('-')){
				final_term = final_term.difference(calculate_term);
			}else if(additive.equals('|')){
				final_term = final_term.symdifference(calculate_term);
			}else{
				throw new APException("Wrong additive \n");
			}
		}
		
		if(final_term == null){
			throw new APException("Identifier is undefined \n");
		}
		
		return final_term;
	}

	
	
	private Sets parseTerm(Scanner term_input) throws APException {
		term_input.useDelimiter("");
		
		Sets final_term = new Sets();
		Sets calculate_term = new Sets();
		Character c;
		
		final_term = parseFactor(term_input);
		
		while(term_input.hasNext() && !hasAdditive(term_input) && hasMultiplicative(term_input)){
			if(term_input.hasNext("\\}")){
				return final_term;
			}
			c= nextMultiplicative(term_input);
			calculate_term = parseFactor(term_input);
			final_term = final_term.intersection(calculate_term);			
		}
		
		return final_term;
	}
	
	private static char nextMultiplicative(Scanner multi) throws APException{
		multi.useDelimiter("");
		multi.skip("\\s*");
		char m = 0;
		if(multi.hasNext("\\*")==true){
			m = multi.next().charAt(0);
			return m;
		}throw new APException("multiplicative character needed \n");
	}
	
	private static char nextAdditive(Scanner additive) throws APException{
		additive.useDelimiter("");
		additive.skip("\\s*");
		char add = 0;
		if(additive.hasNext("\\+")==true || additive.hasNext("\\|")==true || additive.hasNext("\\-")==true){
			add = additive.next().charAt(0);
			return add;
		}
		throw new APException("additive character needed \n");
	}

	private Sets parseFactor(Scanner factor_input) throws APException {
		factor_input.useDelimiter("");
		factor_input.skip("\\s*");
		
		Sets factor = new Sets();
		
		if (factor_input.hasNext("[a-zA-Z]")){
			Identifiers p = parseFirstLetterBefore(factor_input);
			factor = table.find(p);
		}else if(factor_input.hasNext("\\(")){
			factor_input.next();
			factor = parseExpression(factor_input);
			parseClosing(factor_input);
		}else if(factor_input.hasNext("\\{")){
			factor_input.next();
			factor = parseSet(factor_input);
		}else{
			throw new APException("Wrong character \n");
		}
		
		return factor;
				
	}
	
	private void parseClosing(Scanner closing_bracket) throws APException{
		closing_bracket.useDelimiter("");
		if(!closing_bracket.hasNext("\\)")){
			throw new APException("Missing bracket \n");
		}
		closing_bracket.next();
	}
	
	private Sets parseSet(Scanner set_input) throws APException {
		set_input.useDelimiter("");
		set_input.skip("\\s*");
		NaturalNumbers buffer;
		Sets natnumbers;
		buffer = new NaturalNumbers();
		natnumbers = new Sets();
		int check_end_line = 0;
		int check_space = 0;
		
		while(set_input.hasNext()){
			String check = set_input.next();
			if(check.equals("}")){
				if(check_space == 3){
					throw new APException("number expected \n");
				}
				natnumbers.add(buffer);
				buffer = new NaturalNumbers();
				check_end_line = 1;
				break;
			}else if(check.equals(",")){
				if(check_space == 0 || check_space == 3){
					throw new APException("number expected \n");
				}
				check_space = 3;
				natnumbers.add(buffer);
				buffer = new NaturalNumbers();
			}else if(check.equals(" ") && check_space != 2){
				continue;
			}else if(check.equals(" ") && check_space == 2){
				check_space = 1;
				continue;
			}else{
				if(check_space == 1){
					throw new APException("number expected \n");
				}
				buffer.add(check);
				check_space = 2;
			}
		}
		
		
		
		if(check_end_line == 0){
			throw new APException("number expected \n");
		}
		
		return natnumbers;
		
	}


	private Identifiers parseFirstLetterBefore(Scanner user_input) throws APException {
		user_input.useDelimiter("");
		Identifiers identifier = new Identifiers();
		if (user_input.hasNext("\\s*")) {
			// skip
			user_input.next();
		} else if (user_input.hasNext("[a-zA-Z]") && identifier.length()==0) {
			// Make identifier
			identifier.addChar(user_input.next().charAt(0));
			identifier = parseWholeIdentifierbefore(user_input, identifier);
		} else if (user_input.hasNext("=")) {
			return identifier;
		} else {
			throw new APException("Identifier before =-sign is wrong \n");
		}
		
		return identifier;
	}

	private Identifiers parseWholeIdentifierbefore(Scanner user_input,Identifiers identifier) throws APException {
		boolean parsing_identifier = true;
		
		while (parsing_identifier == true) {
			if (user_input.hasNext("[a-zA-Z0-9]")) {
				// add letter
				identifier.addChar(user_input.next().charAt(0));
			} else if (user_input.hasNext(" ") || user_input.hasNext("=") || !user_input.hasNext() || user_input.hasNext("\\*") || user_input.hasNext("\\+")|| user_input.hasNext("\\-") || user_input.hasNext("\\|") || user_input.hasNext("\\)")  ){
				parsing_identifier = false;
				return identifier;
			} else {
				
				throw new APException("wrong character \n");
			}
		}
		
		return identifier;

	}
	
	private static boolean hasAdditive(Scanner additive){
		additive.useDelimiter("");
		additive.skip("\\s*");
		if (additive.hasNext("\\+") || additive.hasNext("\\|") || additive.hasNext("\\-")){
			return true;
		}
		else{
			return false;
		}
	}
	
	private static boolean hasMultiplicative(Scanner multiplicative){
		multiplicative.useDelimiter("");
		multiplicative.skip("\\s*");
		if (multiplicative.hasNext("\\*")){
			return true;
		}
		else{
			return false;
		}
	}
	


}
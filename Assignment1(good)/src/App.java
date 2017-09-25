import java.util.Scanner;

public class App {

	boolean system_on;
	boolean read_set;
	Identifier identifier1;
	Identifier identifier2;
	Collection collection1 = new Collection();
	Collection collection2 = new Collection();
	Scanner user_input;
	Scanner in = new Scanner(System.in);
	Collection intersection = new Collection();
	Collection difference = new Collection();
	Collection union = new Collection();
	Collection symdifference = new Collection();

	public static void main(String[] args) {
		new App().start();
	}

	public void start() {
		system_on = true;
		
		while (system_on == true) {

				read_set = true;
				while (read_set == true) {
					try {
						System.out.print("Give first collection: ");
						
						if(!in.hasNext()){
							System.out.println("system exit");
							system_on = false;
							return;
						}
						input(user_input, identifier1, collection1);
					}catch (MyOwnException e){
						empty_set(collection1);
						System.out.println(e);
					}
				}


				read_set = true;
				while (read_set == true) {
					try {
						System.out.print("Give second collection: ");
						if(!in.hasNext()){
							System.out.println("system exit");
							system_on = false;
							return;
						}
						input(user_input, identifier2, collection2);
					} catch (MyOwnException e) {
						empty_set(collection2);
						System.out.println(e);}
				}
				try{
					parse_operations();
					empty_sets();
				} catch (MyOwnException e) {
					System.out.println(e);}

			}
		}

	private void parseStatement(Scanner user_input, Identifier identifier,Collection collection) throws MyOwnException {
		user_input.useDelimiter("");
		user_input.skip("\\s*");
		if (user_input.hasNext("\\{")) {
			user_input.next();
			parseFirstLetter(user_input, identifier, collection);
		} else {
			throw new MyOwnException("missing bracket '{'");
		}
	}

	private void parseFirstLetter(Scanner user_input, Identifier identifier,
			Collection collection) throws MyOwnException {
		if (user_input.hasNext("\\s*")) {
			// skip
			user_input.next();
			parseFirstLetter(user_input, identifier, collection);
		} else if (user_input.hasNext("[a-zA-Z]")) {
			// add identifier
			identifier = new Identifier(user_input.next());
			parseWholeIdentifier(user_input, identifier, collection);
		} else if (user_input.hasNext("\\}")) {
			// end set
			user_input.next();
			read_set = false;
		} else {
			throw new MyOwnException("first letter wrong");
		}

	}

	private void parseWholeIdentifier(Scanner user_input,Identifier identifier, Collection collection) throws MyOwnException {
	
		while (user_input.hasNext()) {

			if (user_input.hasNext("\\s*")) {
				// add identifier
				if (collection.size()>10){
					throw new MyOwnException("Too much identifiers");
				}
				collection.add(identifier);
				user_input.next();
				parseFirstLetter(user_input, identifier, collection);
			} else if (user_input.hasNext("[a-zA-Z0-9]")) {
				// add letter
				identifier.addChar(user_input.next());
			} else if (user_input.hasNext("\\}")) {
				// end set
				if (collection.size()>10){
					throw new MyOwnException("Too much identifiers");
				}
				collection.add(identifier);
				user_input.next();
				read_set = false;
			} else {
				throw new MyOwnException("wrong character");
			}
		}

	}

	private void input(Scanner user_input, Identifier identifier,Collection collection) throws MyOwnException {
		user_input = new Scanner(in.nextLine());
		parseStatement(user_input, identifier, collection);
		if (read_set == true) {
			throw new MyOwnException("missing bracket: '}'");
		}
	}
	private void result(Collection collection) throws MyOwnException{
		System.out.print("{");
		collection.print();
		System.out.println("}");
	}
	
	private void parse_operations() throws MyOwnException{
		difference = collection1.difference(collection2);
		intersection = collection1.intersection(collection2);
		union = collection1.union(collection2);
		symdifference = collection1.symdifference(collection2);
		System.out.print("intersection: ");
		result(intersection);
		System.out.print("difference: ");
		result(difference);
		System.out.print("union: ");
		result(union);
		System.out.print("symmetric difference: ");
		result(symdifference);
	}
	
	private void empty_set(Collection collection){
		collection.init();
	}
	
	private void empty_sets(){
		collection1.init();
		collection2.init();
	}
	

}
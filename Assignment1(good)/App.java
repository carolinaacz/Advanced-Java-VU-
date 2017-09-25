import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class App {

	public static String first_collection;
	public static String second_collection;
	Scanner first = new Scanner(System.in);
	Identifiers a = new Identifiers();
	Identifiers b = new Identifiers();
	CollectionsOfIdentifiers collection1 = new CollectionsOfIdentifiers();
	CollectionsOfIdentifiers collection2 = new CollectionsOfIdentifiers();
	String identifiers = null;
	List<String> list1 = new ArrayList<String>();
	List<String> list2 = new ArrayList<String>();

	public String input() {
		Scanner user_input = new Scanner(System.in);
		String input_outcome = user_input.nextLine();
		return input_outcome;	
	}
	
	public void empty(String input) throws MyOwnException {
		if (input.length() == 0){
			throw new MyOwnException("Enter identifier");
		}
	}

	public void brackets(String input) throws MyOwnException {
		char f_bracket = input.charAt(0);
		char l_bracket = input.charAt(input.length() - 1);
		if (f_bracket == '{' && l_bracket == '}') {
			input = removeCharAt(input, 0);
		} else if (f_bracket == '{' && l_bracket != '}') {
			throw new MyOwnException("Missing '}'");
		} else if (f_bracket != '{' && l_bracket == '}') {
			throw new MyOwnException("Missing '{'");
		} else {
			throw new MyOwnException("Missing both brackets");
		}
	}

	public void requirements(String input, Identifiers identifier,
			CollectionsOfIdentifiers collection) throws MyOwnException {

		int teller = 0;
		int MAX_LENGTH = 9;
		
		for (int i = 0; i < input.length(); i++) {

			if (Character.isLetterOrDigit(input.charAt(i)) && teller > 0) {
				identifier.addChar(input.charAt(i));
				teller = teller + 1;
			} 
			else if (Character.isLetter(input.charAt(i)) && teller == 0) {
				identifier.addChar(input.charAt(i));
				teller = teller + 1;
			} 
			else if (Character.isWhitespace(input.charAt(i)) && teller == 0) {
				throw new MyOwnException("Double whitespace");
			}
			else if (input.charAt(i) == '{') {
				continue;
			} 
			else if (collection.length() > MAX_LENGTH) {
				throw new MyOwnException("Too much identifiers");
			} 
			else if (Character.isWhitespace(input.charAt(i))
					|| input.charAt(i) == '}') {
				identifiers = identifier.charToString();
				collection.addIdentifier(identifiers);
				identifier.empty();
				teller = 0;
			} 
			else {
				throw new MyOwnException(
						"One of the identifiers is not correct");
			}

		}
	}

	public String builder(List<String> input) {
		StringBuilder sb = new StringBuilder();
		for (String s : input) {
			sb.append(" ");
			sb.append(s);
		}
		if (sb.length() > 0) {
			sb.delete(0, 1);
		}
		sb.insert(0, "{");
		sb.insert(sb.length(), "}");

		return sb.toString();
	}

	public List<String> difference(List<String> list1, List<String> list2) {
		List<String> list = new ArrayList<String>();

		for (String t : list1) {
			list.add(t);
			if (list2.contains(t)) {
				list.remove(t);
			}
		}
		if(list.contains("")){
			list.remove("");
		}

		return list;
	}

	public List<String> union(List<String> list1, List<String> list2) {
		Set<String> set = new HashSet<String>();

		set.addAll(list1);
		set.addAll(list2);
		if(set.contains("")){
			set.remove("");
		}

		return new ArrayList<String>(set);
	}

	public List<String> intersection(List<String> list1, List<String> list2) {
		List<String> list = new ArrayList<String>();

		for (String t : list1) {
			if (list2.contains(t)) {
				list.add(t);
			}
		}
		if(list.contains("")){
			list.remove("");
		}

		return list;
	}

	public List<String> symmetric_difference(List<String> list1,
			List<String> list2) {
		Set<String> set = new HashSet<String>();

		set.addAll(list1);
		set.addAll(list2);
		for (String t : list1) {
			if (list2.contains(t)) {
				set.remove(t);
			}
		}
		if(set.contains("")){
			set.remove("");
		}

		return new ArrayList<String>(set);
	}

	public static String removeCharAt(String s, int pos) {
		return s.substring(0, pos) + s.substring(pos + 1);
	}

	public void start() throws Exception {
		
		try{
			System.out.print("Give first collection: ");
			first_collection = new App().input();
			new App().empty(first_collection);
			new App().requirements(first_collection, a, collection1);
			new App().brackets(first_collection);
		}
		catch(MyOwnException e) {
			System.out.println(e.getMessage());
		}
		
		while(second_input=true){
			try{
				System.out.print("Give second collection: ");
				second_collection = new App().input();
				new App().empty(second_collection);
				new App().brackets(second_collection);
				new App().requirements(second_collection, b, collection2);
				second_input=false;
			}
			catch(MyOwnException e) {
				System.out.println(e.getMessage());
				System.out.printl('hallo'));
			}
		}

		List<String> dif = new App().difference(collection1.idlist,
				collection2.idlist);
		List<String> inter = new App().intersection(collection1.idlist,
				collection2.idlist);
		List<String> union = new App().union(collection1.idlist,
				collection2.idlist);
		List<String> symdif = new App().symmetric_difference(
				collection1.idlist, collection2.idlist);

		String d = new App().builder(dif);
		String is = new App().builder(inter);
		String un = new App().builder(union);
		String sd = new App().builder(symdif);

		System.out.println("Difference: " + d);
		System.out.println("Intersection: " + is);
		System.out.println("Union: " + un);
		System.out.println("Sym.dif: " + sd);

	}

	public static void main(String[] args) throws Exception {

		while (true) {
				new App().start();
			}
		}
	}

}
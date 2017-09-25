import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {
	
	BinarySearchTree Tree = new BinarySearchTree();
	boolean CaseSensitive = true;
	boolean descending = false;
	int LoopStart = 0;
	
	
	public void start(String[] args){
		try{
			parseCommand(args);
		} catch (APException e) {
			System.out.println(e);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	public void parseCommand(String[] args) throws APException, FileNotFoundException{
		parseAny(args);
		parseOptions(args);
		parseFiles(args);
		parsePrint();
		}
	
	public void parseFiles(String args[]) throws APException, FileNotFoundException{
		if (args.length > (LoopStart)){
			for (int i=LoopStart; i < args.length; i++) {
				parseFile(args[i]);
			}
		} else{
			throw new APException("No files");
		}

	}
	
	public void parseFile(String args) throws APException, FileNotFoundException{
		File file = new File(args);
		Scanner identifiers = new Scanner(file);
		parseIdentifiers(identifiers);
		identifiers.close();
	}
	
	
	public void parseIdentifiers(Scanner scan){
		scan.useDelimiter("[^A-Za-z0-9]");
		while(scan.hasNext()){
			parseIdentifier(scan.next());
		}
		
	}
	
	public void parseIdentifier(String word){
		word = word.replaceAll("[^A-Za-z0-9]", " ");
		if(word.isEmpty()){
			return;
		}
		if(Character.isLetter(word.charAt(0))){
			if(CaseSensitive == false){
				word = word.toLowerCase();
			}
			Identifiers new_id = new Identifiers(word);
			Tree.add(new_id);
		}
		
	}
	
	public void parseOptions(String[] args) throws APException{
		int OptionStart;
		OptionStart = 0;
		
		for(int i=0; i<args.length ; i++){
			if(args[i].contains("-")){
				OptionStart += 1;
			}
		}
		
		
		for(int i=0; i<OptionStart ; i++){
			if(args[i].equals("-d")){
				descending = true;
				LoopStart = LoopStart + 1;
			}else if(args[i].equals("-i")){
				CaseSensitive = false;
				LoopStart = LoopStart + 1;
			}else{
				throw new APException("Wrong option");
			}
		}
	}
	
	public void parseAny(String[] args) throws APException{
		if(args.length > 0){
			return;
		} else if (args.length == 0){
			throw new APException("No command line");
		}
		
	}

	
	public void parsePrint(){
		if(descending == true){
			Tree.printDescending();
		} else if (descending == false){
			Tree.printAscending();
		}
	}
	
	public static void main(String[] args){
		new Main().start(args);
	}

}

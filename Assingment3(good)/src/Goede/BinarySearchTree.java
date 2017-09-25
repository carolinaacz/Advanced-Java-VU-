import java.util.ArrayList;
import java.util.Iterator;

public class BinarySearchTree{
	
	TreeNode RootNode;
	
	BinarySearchTree(){
	}
	
	
	public void add(Identifiers id) {
		TreeNode add_node = new TreeNode(id, null, null);
		if(RootNode == null){
			RootNode = add_node;
		}else{
			try {
				parseNode(add_node);
			} catch (APException e) {
				System.out.printf("%s",e);
			}
		}
	}

	public void parseNode(TreeNode node) throws APException{
		TreeNode parent = null;
		TreeNode root = RootNode;
		
		while(root != null){
			parent = root;
			if( node.getData().ReturnString().compareTo(root.getData().ReturnString()) > 0){
				root = root.getRight();
			}else if(node.getData().ReturnString().compareTo(root.getData().ReturnString()) == 0){
				node.updateRight(root.getRight());
				root.updateRight(node);
				return;
			}else {
				root = root.getLeft();
			}
		}
		
		updateParent(parent,node);
	}
	
	private void updateParent(TreeNode parent, TreeNode node){
		String compare_one = node.getData().ReturnString();
		String compare_two = parent.getData().ReturnString();
		if(compare_one.compareTo(compare_two)<0){
			parent.updateLeft(node);
		}else{
			parent.updateRight(node);
		}
	}
	
	public void printDescending(){
		Iterator<Identifiers> descending = descendingIterator();
		ArrayList<String> odd = parseOddCount(descending);
		for (int i = 0; i < odd.size(); i++) {
		     System.out.println(odd.get(i).toString());
		}
	}
	

	public void printAscending(){
		Iterator<Identifiers> ascending = ascendingIterator();
		ArrayList<String> odd = parseOddCount(ascending);
		for (int i = 0; i < odd.size(); i++) {
		     System.out.println(odd.get(i).toString());
		}
	}		

	public ArrayList<String> parseOddCount(Iterator<Identifiers> whole_list){
		ArrayList<String> OddCount = new ArrayList<String>();
		while(whole_list.hasNext()){
			String checker = whole_list.next().identifier;
			if(!OddCount.contains(checker)){
				OddCount.add(checker);
			} else{
				OddCount.remove(checker);
			}
		}
		return OddCount;
	}
	
	public Iterator<Identifiers> descendingIterator() {
		ArrayList<Identifiers> descendingArrayList = new ArrayList<Identifiers>();
		TreeNode x = RootNode;
		fillArrayDescending(descendingArrayList, x);
		return descendingArrayList.iterator();
	}
	
	public Iterator<Identifiers> ascendingIterator() {
		ArrayList<Identifiers> ascendingArrayList = new ArrayList<Identifiers>();
		TreeNode x = RootNode;
		fillArrayAscending(ascendingArrayList, x);
		return ascendingArrayList.iterator();
	}

	public void fillArrayDescending(ArrayList<Identifiers> list, TreeNode node){
		if (node!= null){
			if(node.getRight()!=null && node.equals(node.getRight())){
				fillArrayDescending(list,node.getRight().getRight());
				fillArrayDescending(list,node.getRight().getLeft());
			}else{
				fillArrayDescending(list,node.getRight());
				list.add(node.getData());
			}
			fillArrayDescending(list,node.getLeft());
		}
	}
	
	public void fillArrayAscending(ArrayList<Identifiers> list, TreeNode node){
		if (node != null){
			fillArrayAscending(list,node.getLeft());
			if(node.getRight()!=null && node.equals(node.getRight())){
				fillArrayAscending(list,node.getRight().getLeft());
				fillArrayAscending(list,node.getRight().getRight());
			}else{
				list.add(node.getData());
				fillArrayAscending(list,node.getRight());
			}
		}
	}

	

	class TreeNode implements Clonable {

		private Identifiers identifier;
		private TreeNode left_id;
		private TreeNode right_id;

		public TreeNode (Identifiers id, TreeNode left, TreeNode right) {
			this.identifier = id;
			this.left_id = left;
			this.right_id = right;
		}

		public Object clone () {
			TreeNode clone = new TreeNode(identifier,left_id,right_id);
			return clone;
		}

		public Identifiers getData(){
			return identifier;
		}

		public TreeNode getLeft(){
			return left_id;
		}
		public TreeNode getRight(){
			return right_id;
		}

		public void updateLeft(TreeNode newChild){
			left_id=newChild;
		}

		public void updateRight(TreeNode newChild){
			right_id=newChild;
		}

		public boolean hasLeft(){
			if(left_id!=null){
				return true;
			}
			return false;
		}

		public	boolean hasRight(){
			if(right_id!=null){
				return true;
			}
			return false;
		}

		public boolean equals(TreeNode n){
			return identifier.equals(n.getData());
		}

	}


}

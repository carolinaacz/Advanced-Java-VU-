import java.util.ArrayList;
import java.util.Iterator;

/** ADT for the class BinaryTree.
*
* @author Tom von Hegedus and Jeroen Baars
 * @param <TreeNode>
* @elements
*	
* @structure 
*	linear
* @domain
*	-
* @constructor
* BinarySearchTree();
*	    <dl>
*		<dt><b>PRE-condition</b><dd>-
*		<dt><b>POST-condition</b><dd>BinarySearchTree is empty.
*	    </dl>
**/

public interface BinarySearchInterface<TreeNode> {
	
	/**  Add
     * @precondition
     *	    -
     * @postcondition
     *	    Adds a identifier to the tree
     **/
	public void add(Identifiers id);
	
	/**  Add
     * @precondition
     *	    -
     * @postcondition
     *	    Adds a identifier to the tree
     **/
	public void updateParent(TreeNode parent, TreeNode node);
	
	/**  Add
     * @precondition
     *	    -
     * @postcondition
     *	    Adds a identifier to the tree
     **/
	public void printAscending();
	
	/**  Add
     * @precondition
     *	    -
     * @postcondition
     *	    Adds a identifier to the tree
     **/
	public ArrayList<String> parseOddCount(Iterator<Identifiers> whole_list);
	

}
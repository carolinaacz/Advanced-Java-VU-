/** ADT for the class Table.
*
* @author Jeroen Baars Tom von Hegedus
* @elements
*	one List, containing Nodes containing variables containing Identifier and set
* @structure 
*	linear
* @domain
*	all Tables
* @constructor
*	Table();
* 		<dl>
*		<dt><b>PRE-condition</b><dd>-
*		<dt><b>POST-condition</b><dd>The new
*		Table-object is initialized with a List.
*	    </dl>
*	<br>
**/

public interface TableInterface extends Clonable<Table<Identifiers, Sets>> {

	
	/** clears the table
	 * @return 
     * @precondition
     *	    -
     * @postcondition
     *	    a empty table is returned
     **/
	public void init();
	
	
	/** a new variable is added to the end of the list
     * @precondition
     *	    -
     * @postcondition
     *	    the variable is added to correct position of the list
     **/
	public void add(Identifiers key, Sets value);
	
	
	/** returns the set of identifiers with the Identifier
     * @precondition
     *	    -
     * @postcondition
     *	   finds identifier id
     **/
	public Sets find(Identifiers K);
	
	/** returns clone of table
     * @precondition
     *	    -
     * @postcondition
     *	   returns a clone of table
     **/
	public Table clone();
		

}


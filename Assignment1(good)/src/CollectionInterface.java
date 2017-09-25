/** ADT for the class CollectionsOfIdentifiers.
 *
 * @authors Jeroen Baars and Tom von Hegedus
 * @elements
 *	identifier of the type Identifier
 * @structure 
 *	none
 * @domain
 *	 0 and max 20 identifiers
 *	
 * @constructor
 *	CollectionsOfIdentifiers();
 *	    <dl>
 *		<dt><b>PRE-condition</b><dd>
			-
 *		<dt><b>POST-condition</b><dd>The new
 *		CollectionOfIdentifiers
 *	    </dl>
 *	<br>
 *	CollectionsOfIdentifier (CollectionOfIdentifiers src);
 *	    <dl>
 *		<dt><b>PRE-condition</b><dd>-
 *		<dt><b>POST-condition</b><dd>The new
 *		CollectionOfIdentifiers-object contains a copy of the src.
 *	    </dl>
 **/
public interface CollectionInterface<String>{


    /** add
     * @precondition
     *	    -
     * @postcondition
     *	    adds id to Collection
     **/
    public void add(Identifier identifier);


   /** size
     * @precondition
     *	    -
     * @postcondition
     *	    returns size of Collection
     **/
    public int size();
    
    /** intersection
     * @precondition
     *	    two collections
     * @postcondition
     *	    returns collection with the intersections of two collections
     **/
    public Collection intersection(Collection collection2);
    
    /** difference
     * @precondition
     *	    two collections
     * @postcondition
     *	    returns collection with the differences of two collections
     **/
    public Collection difference(Collection collection2);
    
    /** union
     * @precondition
     *	    two collections
     * @postcondition
     *	    returns collection with the unions of two collections
     **/
    public Collection union(Collection collection2);
    
    /** symmetric difference
     * @precondition
     *	    two collections
     * @postcondition
     *	    returns collection with the symmetric differences of two collections
     **/
    public Collection symdifference(Collection collection2);
    
    /** has id
     * @precondition
     *	    -
     * @postcondition
     *	    returns true if identifier is in array
     **/
    public boolean has_ID(Identifier identifier);
    
    /** increase size
     * @precondition
     *	    -
     * @postcondition
     *	    increases the size of a collection
     **/
    public void increaseSize();
    
    /** print
     * @precondition
     *	    -
     * @postcondition
     *	    prints collection
     **/
    public void print();
    
    /** copy
     * @precondition
     *	    -
     * @postcondition
     *	    copies elements from collection
     **/
    public void copyElements(Identifier[] dest, Identifier[] src, int amount);
}
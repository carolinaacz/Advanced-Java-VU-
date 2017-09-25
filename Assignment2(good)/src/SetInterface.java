/** ADT for the class Set<E extends Data<E>>.
 *
 * @authors Jeroen Baars and Tom von Hegedus
 * @elements
 *	Objects of type E extending Data
 * @structure 
 *	none
 * @domain
 *	all sets containing E
 * @constructor
 *	Set();
 *	    <dl>
 *		<dt><b>PRE-condition</b><dd>
 *			-
 *		<dt><b>POST-condition</b><dd>A new
 *		Set is empty
 *	    </dl>
**/
public interface SetInterface<E extends Data<E>> extends Clonable<Sets<E>> {


    /** add
     * @precondition
     *	    -
     * @postcondition
     *	    adds Natural Numbers n to set
     **/
    public void add(NaturalNumbers n) throws APException;


   /** remove
     * @precondition
     *	    set is not empty
     * @postcondition
     *	    removes Natural Numbers n from set
     **/
    public Sets<E> delete(NaturalNumbers n, Sets difference) throws APException;

   /** size
     * @precondition
     *	    -
     * @postcondition
     *	    returns size of the set
     **/
     public int size();


   /** init
     * @precondition
     *	    -
     * @postcondition
     *	   clears the set
     **/
    public void init();

   
    /** difference
     * @precondition
     *	    -
     * @postcondition
     *	    returns all elements contained in the 1st but not the 2nd set.
     **/
    Sets<E> difference(Sets<E> s) throws APException;


    /** union
     * @precondition
     *	    -
     * @postcondition
     *	    returns all elements of both sets.
     **/
    Sets<E> union(Sets<E> s) throws APException;


    /** intersect
     * @precondition
     *	    -
     * @postcondition
     *	    returns all elements contained in both sets    
     **/
    Sets<E> intersection(Sets<E> s) throws APException;

    /** symmetric difference
     * @precondition
     *	    -
     * @postcondition
     *	    returns all elements of both sets that are not contained in the intersection.
     **/
    Sets<E> symdifference(Sets<E> s) throws APException;
}

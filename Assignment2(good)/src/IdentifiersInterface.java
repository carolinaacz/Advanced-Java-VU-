/** ADT for the class Identifier.
 *
 * @author Jeroen Baars & Tom von Hegedus
 * @elements
 *	Characters van het type Char
 * @structure 
 *	linear
 * @domain
 *	Start with letter
 *  non empty
 *
 * @constructor
 *	Identifier();
 *	    <dl>
 *		<dt><b>PRE-condition</b><dd>-
 *		<dt><b>POST-condition</b><dd>The new
 *		Identifier-object contains only the empty object.
 *	    </dl>
 *	<br>
 *
 **/
public interface IdentifiersInterface{


    /** adds a character
     * @precondition
     *	    -
     * @postcondition
     *	    adds a char c to Identifier
     **/
	public void addChar(char c);


    

    /** length
     * @precondition
     *	    - 
     * @postcondition
     *	    return length of Identifier
     **/
    public int length();




 
/** Returns new identifier
    * @precondition
     *	    - 
     * @postcondition
     *	    returns empty identifier
     **/
   public void empty();

/** compare to other identifiers
    * @precondition
     *	    - 
     * @postcondition
     *	    return true if identifiers are the same
     *		return false if identifiers are not the same
     **/
   public boolean compare(Identifiers other);

   
}
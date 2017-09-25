/** ADT for the class Identifier.
 *
 * @author Jeroen Baars & Tom van Hegedus
 * @elements
 *	Characters van het type Char
 * @structure 
 *	linear
 * @domain
 *	Needs to be alphanumeric
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
public interface IdentifierInterface {


    /** adds a character
     * @precondition
     *	    -
     * @postcondition
     *	    adds a String c to Identifier
     **/
    public void addChar(String x);


    /** changes identifier to string
     * @precondition
     *	    identifier in char
     * @postcondition
     *	 identifier is changed to string
     **/
    public String getChar();


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
   public void init();
}
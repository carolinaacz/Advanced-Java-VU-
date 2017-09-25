/** ADT for the class NaturalNumber.

 *

 * @author Jeroen Baars & Tom von Hegedus

 * @elements

 *      Natural numbers

 * @structure

 *      linear

 * @domain

 *      Needs to be Natural Numbers

 * @constructor

 *      NaturalNumbers();

 *          <dl>

 *             <dt><b>PRE-condition</b><dd>-

 *             <dt><b>POST-condition</b><dd>The new

 *             NaturalNumber-object contains only the empty object.

 *          </dl>

 *      <br>

 *

 **/

	public interface NaturalNumbersInterface {

    /** adds a character

     * @precondition

     *      -

     * @postcondition

     *      adds a string number to NaturalNumber

     **/

    public void add(String numbers) throws APException;

 

    /** length

     * @precondition

     *      -

     * @postcondition

     *      return the length of NaturalNumber

     **/

    public int parseLength();
 

/** compare to other NaturalNumber

    * @precondition

     *      -

     * @postcondition

     *      Returns true if they are the same

     **/

    public boolean equals(NaturalNumbers nat_numb);

 

/** clones object and returns clone

     * @precondition

     *          -

     * @postcondition

     *          a deep-copy of the object is returned

     **/

    public Object clone();
}
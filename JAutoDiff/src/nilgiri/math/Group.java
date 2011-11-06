package nilgiri.math;

/** A class X implements the Group&ltX&rt interface indicates that X has properties of being a group.
 * @author uniker9
 *
 * @param <X> A set forms a group. 
 */
public interface Group<X> {

	/** Returns an object of X whose value is (- this).  
	 * @return - this
	 */
	X negate();

	/** Returns an object of X whose value is (this + i_v).
	 * @param i_v
	 * @return this + i_v 
	 */
	X plus(X i_v);

	/** Returns an object of X whose value is (this - i_v).
	 * @param i_v
	 * @return this - i_v
	 */
	X minus(X i_v);

	/** Returns an object of X whose value is the summation (sum_{1}^{i_n}(this)).
	 * Group&ltX&rt guarantees the results for natural numbers only.  
	 * @param i_n a natural number
	 * @return sum_{1}^{i_n}(this)
	 */
	X mul(long i_n);
}

package nilgiri.math;

/** A class X implements the Ring&ltX&rt interface indicates that X has properties of being a ring.
 * @author uniker9
 *
 * @param <X>  A set forms a ring.
 */
public interface Ring<X> extends CommutativeGroup<X> {

	/** Returns an object of X whose value is the product (this * i_v).
	 * @param i_v
	 * @return this * i_v
	 */
	X mul(X i_v);

	/**  Returns an object of X whose value is the power (this ^ i_n).
	 * Ring<X> guarantees the results for natural numbers only.   
	 * @param i_n a natural number
	 * @return this ^ i_n
	 */
	X pow(int i_n);

}

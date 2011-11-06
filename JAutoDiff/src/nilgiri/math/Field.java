package nilgiri.math;

/** A class X implements the Field&ltX&rt interface indicates that X has properties of being a field.
 * @author uniker9
 *
 * @param <X> A set forms a field.
 */
public interface Field<X> extends CommutativeRing<X> {

	/** Returns an object of X whose value is (1 / this).  
	 * @return 1 / this
	 */
	X inverse();

	/** Returns an object of X whose value is (this / i_v). 
	 * @param i_v
	 * @return this / i_v
	 */
	X div(X i_v);
}

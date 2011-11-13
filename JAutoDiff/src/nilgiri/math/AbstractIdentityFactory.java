package nilgiri.math;

/** An abstract factory for creating the zero element and unity of X forms a field.
 * @author uniker9
 *
 * @param <X> A set forms a field.
 */
public interface AbstractIdentityFactory<X> {

	/** Returns the zero element (additive identity) of X.
	 * @return the zero element of X.
	 */
	X zero();

	/** Returns the unity (multiplicative identity) of X.
	 * @return the unity of X.
	 */
	X one();

}

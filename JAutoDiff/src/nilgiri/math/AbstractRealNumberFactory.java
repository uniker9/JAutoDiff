package nilgiri.math;

/** An abstract factory for creating the fundamental functions defined on real numbers.
 * @author uniker9
 *
 * @param <X> Real numbers.
 */
public interface AbstractRealNumberFactory<X extends RealNumber<X>> extends AbstractIdentityFactory<X>{

	
	
	/** Returns an object of DoubleReal whose value is i_v.  
	 * @param i_v
	 * @return X whose value is i_v
	 */
	public X val(double i_v);

	
	/** Returns an object of X whose value is cos(i_x). 
	 * @param i_x
	 * @return cos(i_x)
	 */
	X cos(X i_x);

	/** Returns an object of X whose value is sin(i_x). 
	 * @param i_x
	 * @return sin(i_x)
	 */
	X sin(X i_x);

	/** Returns an object of X whose value is tan(i_x). 
	 * @param i_x
	 * @return tan(i_x)
	 */
	X tan(X i_x);

	/** Returns an object of X whose value is exp(i_x). 
	 * @param i_x
	 * @return exp(i_x)
	 */
	X exp(X i_x);

	/** Returns an object of X whose value is log(i_x). 
	 * @param i_x
	 * @return log(i_x)
	 */
	X log(X i_x);

	/** Returns an object of X whose value is i_x ^ i_y. 
	 * @param i_x
	 * @param i_y
	 * @return i_x ^ i_y
	 */
	X pow(X i_x, X i_y);


	/** Returns an object of X whose value is sqrt(i_x)
	 * @param i_x
	 * @return sqrt(i_x)
	 */
	X sqrt(X i_x);
	
	/** Returns an object of X whose value is i_x * i_x
	 * @param i_x
	 * @return i_x * i_x
	 */
	X square(X i_x);

}

package nilgiri.math;

/** Let R denote a class implements RealNumber&ltR&gt. 
 * A class X implements the ComplexNumber&ltR, X&gt interface indicates that 
 * X is a complex number whose real and imaginary parts are of R.d
 * @author uniker9
 *
 * @param <R> Real numbers.
 * @param <X> Complex numbers which is (x + i y) where x, y in R.  
 */
public interface ComplexNumber<R extends RealNumber<R>, X> extends
		Field<X> {

	/** Returns an object of X whose value is the conjugate (this.re() - this.im()).  
	 * @return (this.re() - this.im()).
	 */
	X conjugate();

	/** Returns an object of R whose value is the real part of this.
	 * @return the real part of this.
	 */
	R re();

	/** Returns an object of R whose value is the imaginary part of this.
	 * @return the imaginary part of this.
	 */
	R im();
}

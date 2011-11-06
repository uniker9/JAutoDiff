package nilgiri.math.autodiff;

import nilgiri.math.Field;

/** An abstract function requires one argument in X. 
 * @author uniker9
 *
 * @param <X> A set forms a field.
 */
public abstract class AbstractUnaryFunction<X extends Field<X>> extends
		DifferentialFunction<X> {

	private DifferentialFunction<X> m_x;

	/** Constructs a new AbstractUnaryFunction.
	 * @param i_v the argument.
	 */
	public AbstractUnaryFunction(DifferentialFunction<X> i_v) {

		if (i_v != null) {
			m_x = i_v;
		} else {
			throw new IllegalArgumentException("Input not null variable.");
		}
	}
	
	/** Returns the argument of this function.
	 * @return the argument.
	 */
	public DifferentialFunction<X> arg() {
		return m_x;
	} 
}

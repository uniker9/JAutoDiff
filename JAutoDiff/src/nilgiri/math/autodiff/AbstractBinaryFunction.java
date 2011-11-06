package nilgiri.math.autodiff;

import nilgiri.math.Field;

/** An abstract function requires two arguments in X.
 * @author uniker9
 *
 * @param <X> A set forms a field.
 */
public abstract class AbstractBinaryFunction<X extends Field<X>> extends
		DifferentialFunction<X> {

	private DifferentialFunction<X> m_x1;
	private DifferentialFunction<X> m_x2;

	/** Constructs a new AbstractBinaryFunction.
	 * @param i_v1 the 1st (left) argument.
	 * @param i_v2 the 2nd (right) argument.
	 */
	public AbstractBinaryFunction(DifferentialFunction<X> i_v1,
			DifferentialFunction<X> i_v2) {

		if (i_v1 != null && i_v2 != null) {
			m_x1 = i_v1;
			m_x2 = i_v2;
		} else {
			throw new IllegalArgumentException("Input not null variables.");
		}
	}
	
	/** Returns the 1st (left) argument of this function.
	 * @return the 1st (left) argument.
	 */
	public DifferentialFunction<X> larg() {
		return m_x1;
	}
	/** Returns the 2nd (right) argument of this function.
	 * @return the 2nd (right) argument.
	 */
	public DifferentialFunction<X> rarg() {
		return m_x2;
	}
}
package nilgiri.math.autodiff;

import nilgiri.math.Field;

//This class expresses a function with 1 argument.
//exam. cos(x), exp(x), and etc.

public abstract class AbstractUnaryFunction<X extends Field<X>> extends
		DifferentialFunction<X> {

	private DifferentialFunction<X> m_x;

	public AbstractUnaryFunction(DifferentialFunction<X> i_v) {

		if (i_v != null) {
			m_x = i_v;
		} else {
			throw new IllegalArgumentException("Input not null variable.");
		}
	}
	
	public DifferentialFunction<X> arg() {
		return m_x;
	} 
}

package nilgiri.math.autodiff;

import nilgiri.math.Field;

//This class expresses a function with 2 arguments
//exam. operators, power(x, 3.0), etc.

public abstract class AbstractBinaryFunction<X extends Field<X>> extends
		DifferentialFunction<X> {

	private DifferentialFunction<X> m_x1;
	private DifferentialFunction<X> m_x2;

	public AbstractBinaryFunction(DifferentialFunction<X> i_v1,
			DifferentialFunction<X> i_v2) {

		if (i_v1 != null && i_v2 != null) {
			m_x1 = i_v1;
			m_x2 = i_v2;
		} else {
			throw new IllegalArgumentException("Input not null variables.");
		}
	}
	
	public DifferentialFunction<X> larg() {
		return m_x1;
	}
	public DifferentialFunction<X> rarg() {
		return m_x2;
	}
}
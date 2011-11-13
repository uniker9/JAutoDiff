package nilgiri.math;

/** A singleton factory for creating the zero element and unity of DoubleComplex.
 * @author uniker9
 *
 */
public class DoubleComplexFactory implements
		AbstractIdentityFactory<DoubleComplex> {

	private static final DoubleComplexFactory m_INSTANCE = new DoubleComplexFactory();

	private DoubleComplexFactory() {
	}

	/** Returns the singleton object of this class.
	 * @return the singleton object of this class.
	 */
	public static DoubleComplexFactory instance() {
		return m_INSTANCE;
	}

	/** Returns an object of DoubleComplex whose value is i_v.  
	 * @param i_re
	 * @param i_im
	 * @return DoubleComplex(i_v)
	 */
	public DoubleComplex create(double i_re, double i_im) {
		return new DoubleComplex(i_re, i_im);
	}
	
	private static final DoubleComplex m_ZERO = new DoubleComplex(0.0, 0.0);
	private static final DoubleComplex m_UNIT = new DoubleComplex(1.0, 0.0);
	
	public DoubleComplex zero() {
		return m_ZERO;
	}

	public DoubleComplex one() {
		return m_UNIT;
	}

}

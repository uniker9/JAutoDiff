package nilgiri.math;

/** A singleton factory for creating the zero element and unity of DoubleReal.
 * @author uniker9
 *
 */
public class DoubleRealFactory implements AbstractIdentityFactory<DoubleReal> {

	private static final DoubleRealFactory m_INSTANCE = new DoubleRealFactory();

	private DoubleRealFactory() {
	}

	/** Returns the singleton object of this class.
	 * @return the singleton object of this class.
	 */
	public static DoubleRealFactory instance() {
		return m_INSTANCE;
	}

	/** Returns an object of DoubleReal whose value is i_v.  
	 * @param i_v
	 * @return DoubleReal(i_v)
	 */
	public DoubleReal create(double i_v) {
		return new DoubleReal(i_v);
	}

	private static final DoubleReal m_ZERO = new DoubleReal(0.0);
	private static final DoubleReal m_UNIT = new DoubleReal(1.0);

	public DoubleReal zero() {
		return m_ZERO;
	}

	public DoubleReal one() {
		return m_UNIT;
	}

	
}

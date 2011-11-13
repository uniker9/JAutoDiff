package nilgiri.math.ia;

import nilgiri.math.AbstractIdentityFactory;

/** A singleton factory for creating the zero element and unity of DoubleRealInterval.
 * @author uniker9
 *
 */
public class DoubleRealIntervalFactory implements AbstractIdentityFactory<DoubleRealInterval> {

	private static final DoubleRealIntervalFactory m_INSTANCE = new DoubleRealIntervalFactory();

	private DoubleRealIntervalFactory() {
	}

	/** Returns the singleton object of this class.
	 * @return the singleton object of this class.
	 */
	public static DoubleRealIntervalFactory instance() {
		return m_INSTANCE;
	}

	/** Returns an object of DoubleRealInterval whose value is i_v.  
	 * @return DoubleRealInterval()
	 */
	public DoubleRealInterval create() {
		return new DoubleRealInterval();
	}
	
	/** Returns an object of DoubleRealInterval whose value is i_v.  
	 * @param i_v
	 * @return DoubleRealInterval(i_v)
	 */
	public DoubleRealInterval create(double i_v) {
		return new DoubleRealInterval(i_v);
	}
	
	/** Returns an object of DoubleRealInterval whose value is [i_lo, i_hi].  
	 * @param i_l0
 	 * @param i_hi
	 * @return DoubleRealInterval(i_lo, i_hi)
	 */
	public DoubleRealInterval create(double i_lo, double i_hi) {
		return new DoubleRealInterval(i_lo, i_hi);
	}
	
	

	private static final DoubleRealInterval m_ZERO = new DoubleRealInterval(IA_RealIntervalFactory.instance().zero());
	private static final DoubleRealInterval m_UNIT = new DoubleRealInterval(IA_RealIntervalFactory.instance().one());

	public DoubleRealInterval zero() {
		return m_ZERO;
	}

	public DoubleRealInterval one() {
		return m_UNIT;
	}

	
}

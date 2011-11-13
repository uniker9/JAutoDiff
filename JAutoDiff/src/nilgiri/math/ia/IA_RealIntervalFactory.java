package nilgiri.math.ia;

import net.sourceforge.interval.ia_math.RealInterval;
import nilgiri.math.AbstractIdentityFactory;

/** A singleton factory for creating the zero element and unity of RealInterval.
 * @author uniker9
 *
 */
public class IA_RealIntervalFactory implements AbstractIdentityFactory<RealInterval> {

	private static final IA_RealIntervalFactory m_INSTANCE = new IA_RealIntervalFactory();

	private IA_RealIntervalFactory() {
	}

	/** Returns the singleton object of this class.
	 * @return the singleton object of this class.
	 */
	public static IA_RealIntervalFactory instance() {
		return m_INSTANCE;
	}

	/** Returns an object of RealInterval whose value is i_v.  
	 * @return RealInterval()
	 */
	public RealInterval create() {
		return new RealInterval();
	}
	
	/** Returns an object of RealInterval whose value is i_v.  
	 * @param i_v
	 * @return RealInterval(i_v)
	 */
	public RealInterval create(double i_v) {
		return new RealInterval(i_v);
	}
	
	/** Returns an object of RealInterval whose value is [i_lo, i_hi].  
	 * @param i_l0
 	 * @param i_hi
	 * @return RealInterval(i_lo, i_hi)
	 */
	public RealInterval create(double i_lo, double i_hi) {
		return new RealInterval(i_lo, i_hi);
	}
	
	

	private static final RealInterval m_ZERO = new RealInterval(0.0);
	private static final RealInterval m_UNIT = new RealInterval(1.0);

	public RealInterval zero() {
		return m_ZERO;
	}

	public RealInterval one() {
		return m_UNIT;
	}

	
}

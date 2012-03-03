package nilgiri.math.ia;

import net.sourceforge.interval.ia_math.IAMath;
import net.sourceforge.interval.ia_math.RealInterval;
import nilgiri.math.AbstractIdentityFactory;
import nilgiri.math.AbstractRealNumberFactory;

/** A singleton factory for creating the zero element and unity of DoubleRealInterval.
 * @author uniker9
 *
 */
public class DoubleRealIntervalFactory implements AbstractRealNumberFactory<DoubleRealInterval>{

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
//	public DoubleRealInterval create() {
//		return new DoubleRealInterval();
//	}
	
	/** Returns an object of DoubleRealInterval whose value is i_v.  
	 * @param i_v
	 * @return DoubleRealInterval(i_v)
	 */
	public DoubleRealInterval val(double i_v) {
		return new DoubleRealInterval(i_v);
	}
	
	/** Returns an object of DoubleRealInterval whose value is [i_lo, i_hi].  
	 * @param i_l0
 	 * @param i_hi
	 * @return DoubleRealInterval(i_lo, i_hi)
	 */
	public DoubleRealInterval val(double i_lo, double i_hi) {
		return new DoubleRealInterval(i_lo, i_hi);
	}

	/** Returns an object of DoubleRealInterval whose value is [i_lo, i_hi].  
	 * @param i_center the center of this
 	 * @param i_eps the tolerance of this
	 * @return DoubleRealInterval(i_center-i_eps, i_center+i_eps)
	 */
	public DoubleRealInterval valWithTolerance(double i_center, double i_eps) {
		return new DoubleRealInterval(i_center-i_eps, i_center+i_eps);
	}

	

	private static final DoubleRealInterval m_ZERO = new DoubleRealInterval(IA_RealIntervalFactory.instance().zero());
	private static final DoubleRealInterval m_UNIT = new DoubleRealInterval(IA_RealIntervalFactory.instance().one());

	public DoubleRealInterval zero() {
		return m_ZERO;
	}

	public DoubleRealInterval one() {
		return m_UNIT;
	}

	public DoubleRealInterval cos(DoubleRealInterval i_x) {
		return new DoubleRealInterval(IAMath.cos(i_x.viewInternal()));
	}

	public DoubleRealInterval sin(DoubleRealInterval i_x) {
		return new DoubleRealInterval(IAMath.sin(i_x.viewInternal()));
	}

	public DoubleRealInterval tan(DoubleRealInterval i_x) {
		return new DoubleRealInterval(IAMath.tan(i_x.viewInternal()));
	}

	public DoubleRealInterval exp(DoubleRealInterval i_x) {
		return new DoubleRealInterval(IAMath.exp(i_x.viewInternal()));
	}

	public DoubleRealInterval log(DoubleRealInterval i_x) {
		return new DoubleRealInterval(IAMath.log(i_x.viewInternal()));
	}

	public DoubleRealInterval pow(DoubleRealInterval i_x, DoubleRealInterval i_y) {
		return new DoubleRealInterval(IAMath.power(i_x.viewInternal(), i_y.viewInternal()));
	}

	public DoubleRealInterval sqrt(DoubleRealInterval i_x) {
		return new DoubleRealInterval(IAMath.integerRoot(i_x.viewInternal(), new RealInterval(2)));
	}

	public DoubleRealInterval square(DoubleRealInterval i_x) {
		return new DoubleRealInterval(IAMath.integerPower(i_x.viewInternal(), new RealInterval(2)));
	}

	
}

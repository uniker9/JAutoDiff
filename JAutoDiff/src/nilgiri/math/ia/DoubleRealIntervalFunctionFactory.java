package nilgiri.math.ia;

import net.sourceforge.interval.ia_math.IAMath;
import nilgiri.math.AbstractRealFunctionFactory;

/** A singleton factory for creating the fundamental functions defined on DoubleRealInterval.
 * @author uniker9
 *
 */
public class DoubleRealIntervalFunctionFactory implements AbstractRealFunctionFactory<DoubleRealInterval> {

	private static final DoubleRealIntervalFunctionFactory m_INSTANCE = new DoubleRealIntervalFunctionFactory();

	private DoubleRealIntervalFunctionFactory() {
	}

	/** Returns the singleton object of this class.
	 * @return the singleton object of this class.
	 */
	public static DoubleRealIntervalFunctionFactory instance() {
		return m_INSTANCE;
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

}

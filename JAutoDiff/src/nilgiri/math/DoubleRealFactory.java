package nilgiri.math;

/** A singleton factory for creating the zero element and unity of DoubleReal.
 * @author uniker9
 *
 */
public class DoubleRealFactory implements AbstractRealNumberFactory<DoubleReal> {

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
	public DoubleReal val(double i_v) {
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


	public DoubleReal cos(DoubleReal i_x) {
		return new DoubleReal(Math.cos(i_x.doubleValue()));
	}

	public DoubleReal sin(DoubleReal i_x) {
		return new DoubleReal(Math.sin(i_x.doubleValue()));
	}

	public DoubleReal tan(DoubleReal i_x) {
		return new DoubleReal(Math.tan(i_x.doubleValue()));
	}

	public DoubleReal exp(DoubleReal i_x) {
		return new DoubleReal(Math.exp(i_x.doubleValue()));
	}

	public DoubleReal log(DoubleReal i_x) {
		return new DoubleReal(Math.log(i_x.doubleValue()));
	}

	public DoubleReal pow(DoubleReal i_x, DoubleReal i_y) {
		return new DoubleReal(Math.pow(i_x.doubleValue(), i_y.doubleValue()));
	}
	
	public DoubleReal sqrt(DoubleReal i_x) {
		return new DoubleReal(Math.sqrt(i_x.doubleValue()));
	}
	
	public DoubleReal square(DoubleReal i_x) {
		return new DoubleReal(i_x.doubleValue() * i_x.doubleValue());
	}
	
}

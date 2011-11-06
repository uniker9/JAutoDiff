package nilgiri.math;

/** A singleton factory for creating the fundamental functions defined on DoubleReal.
 * @author uniker9
 *
 */
public class DoubleRealFunctionFactory implements AbstractRealFunctionFactory<DoubleReal> {

	private static final DoubleRealFunctionFactory m_INSTANCE = new DoubleRealFunctionFactory();

	private DoubleRealFunctionFactory() {
	}

	/** Returns the singleton object of this class.
	 * @return the singleton object of this class.
	 */
	public static DoubleRealFunctionFactory instance() {
		return m_INSTANCE;
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

}

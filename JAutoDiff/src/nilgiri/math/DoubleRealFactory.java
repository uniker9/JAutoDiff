package nilgiri.math;

public class DoubleRealFactory implements AbstractRealNumberFactory<DoubleReal> {

	private static final DoubleRealFactory m_INSTANCE = new DoubleRealFactory();

	private DoubleRealFactory() {
	}

	public static DoubleRealFactory instance() {
		return m_INSTANCE;
	}

	private static final DoubleReal m_ZERO = new DoubleReal(0.0);
	private static final DoubleReal m_UNIT = new DoubleReal(1.0);

	public DoubleReal create(double i_v) {
		return new DoubleReal(i_v);
	}

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

	public DoubleReal pow(DoubleReal i_x, int i_y) {
		return new DoubleReal(Math.pow(i_x.doubleValue(), i_y));
	}

	public DoubleReal pow(DoubleReal i_x, DoubleReal i_y) {
		return new DoubleReal(Math.pow(i_x.doubleValue(), i_y.doubleValue()));
	}

}

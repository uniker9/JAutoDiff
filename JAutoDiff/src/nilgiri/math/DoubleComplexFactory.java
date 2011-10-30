package nilgiri.math;

public class DoubleComplexFactory implements
		AbstractFieldFactory<DoubleComplex> {

	private static final DoubleComplexFactory m_INSTANCE = new DoubleComplexFactory();

	private DoubleComplexFactory() {
	}

	public static DoubleComplexFactory instance() {
		return m_INSTANCE;
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

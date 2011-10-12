package nilgiri.math.autodiff;

import nilgiri.math.FieldNumber;

public class PolynomialTerm<X extends FieldNumber<X>> extends
		AbstractUnaryFunction<X> {

	protected long m_scale;
	protected int m_exponent;

	public PolynomialTerm(long i_scale, DifferentialFunction<X> i_v,
			int i_exponent) {
		// scale v^{exponent}
		super(i_v);
		m_scale = i_scale;
		m_exponent = i_exponent;
	}

	@Override
	public X getValue() {
		return (arg().getValue().pow(m_exponent)).multi(m_scale);
	}

	@Override
	public DifferentialFunction<X> diff(Variable<X> i_v) {
		return (new PolynomialTerm<X>(m_scale - 1, arg(), m_exponent)).multi(arg()
				.diff(i_v));
	}

	@Override
	public String toString() {
		return m_scale + arg().toString() + "^" + m_exponent;
	}

	public DifferentialFunction<X> inverse() {
		return new PolynomialTerm<X>(m_scale, arg(), -m_exponent);
	}

	public DifferentialFunction<X> negate() {
		return new PolynomialTerm<X>(-m_scale, arg(), m_exponent);
	}

}

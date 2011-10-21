package nilgiri.math.autodiff;

import nilgiri.math.AbstractFieldNumberFactory;
import nilgiri.math.FieldNumber;

// This class expresses constant number. 
// This must be immutable.
//For example,  0, 1, ... for DoubleReal
//For example,  [0, 0], [1, 3],... for UInterval

public class Constant<X extends FieldNumber<X>> extends DifferentialFunction<X> {

	private X m_x;
	private AbstractFieldNumberFactory<X> m_factory;

	public Constant(X i_v, AbstractFieldNumberFactory<X> i_factory) {
		if (i_v != null && i_factory != null) {
			m_x = i_v;
			m_factory = i_factory;
		} else {
			throw new IllegalArgumentException("Input not null value.");
		}
	}

	protected AbstractFieldNumberFactory<X> factory() {
		return m_factory;
	}

	// public Constant<X> like(X i_v){
	// return new Constant<X>(i_v, m_RNFactory);
	// }

	public X getValue() {
		return m_x;
	}

	public DifferentialFunction<X> diff(Variable<X> i_v) {
		return new Zero<X>(m_factory);
	}

	public String toString() {
		return getValue().toString();
	}

	public boolean isConstant() {
		return true;
	}

	public Constant<X> plus(Constant<X> i_v) {
		return new Constant<X>(m_x.plus(i_v.m_x), m_factory);
	}

	protected Constant<X> plusee(Constant<X> i_v) {
		return new Constant<X>(i_v.m_x.plus(this.m_x), m_factory);
	}

	public Constant<X> multi(Constant<X> i_v) {
		return new Constant<X>(m_x.mul(i_v.m_x), m_factory);
	}

	protected Constant<X> multiee(Constant<X> i_v) {
		return new Constant<X>(i_v.m_x.mul(this.m_x), m_factory);
	}

	public Constant<X> inverse() {
		return new Constant<X>(m_x.inverse(), m_factory);
	}

	public Constant<X> negate() {
		return new Constant<X>(m_x.negate(), m_factory);
	}

}

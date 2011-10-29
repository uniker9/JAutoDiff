package nilgiri.math.autodiff;

import nilgiri.math.AbstractFieldNumberFactory;
import nilgiri.math.Field;

public class Variable<X extends Field<X>> extends DifferentialFunction<X> {

	private X m_x;
	private AbstractFieldNumberFactory<X> m_factory;
	private String m_name;

	public Variable(String i_name, X i_v,
			AbstractFieldNumberFactory<X> i_factory) {
		setName(i_name);
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
	
	private void setName(String i_name) {
		if (i_name != null) {
			m_name = i_name;// new String(i_name);
		} else {
			throw new IllegalArgumentException("Input not null value.");
		}
	}

	public void set(X i_v) {
		if (i_v != null) {
			m_x = i_v;
		} else {
			throw new IllegalArgumentException("Input not null value.");
		}
	}

	public X getValue() {
		// return m_x.clone();
		return m_x;
	}
	public String getName() {
		return m_name;
	}
	public Constant<X> diff(Variable<X> i_v) {
		return (this == i_v) ? new One<X>(m_factory) : new Zero<X>(m_factory);
	}

	@Override
	public String toString() {
		return getName();
	}

	public boolean isVariable() {
		return true;
	}

}
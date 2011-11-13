package nilgiri.math.autodiff;


import nilgiri.math.AbstractIdentityFactory;
import nilgiri.math.Field;

/** A constant in X forms a field.
 * Computations are done correctly 
 * while objects of this class and those values are immutable.
 * @author uniker9
 *
 * @param <X> A set forms a field.
 */
public class Constant<X extends Field<X>> extends DifferentialFunction<X> {

	private X m_x;
	private AbstractIdentityFactory<X> m_factory;

	protected Constant(X i_v, AbstractIdentityFactory<X> i_factory) {
		if (i_v != null && i_factory != null) {
			m_x = i_v;
			m_factory = i_factory;
		} else {
			throw new IllegalArgumentException("Input not null value.");
		}
	}
	
	
	protected AbstractIdentityFactory<X> factory() {
		return m_factory;
	}

	@Override
	public boolean isConstant() {
		return true;
	}

	@Override
	public X getValue() {
		return m_x;
	}

	@Override
	public DifferentialFunction<X> diff(Variable<X> i_v) {
		return new Zero<X>(m_factory);
	}

	@Override
	public String toString() {
		return getValue().toString();
	}
	
	@Override
	protected DifferentialFunction<X> plused(DifferentialFunction<X> i_v) {
		return i_v.isConstant() ? new Constant<X>(i_v.getValue().plus(this.m_x), m_factory) : super.plused(i_v);
	}
	
	@Override
	protected DifferentialFunction<X> muled(DifferentialFunction<X> i_v) {
		return i_v.isConstant() ? new Constant<X>(i_v.getValue().mul(this.m_x), m_factory) : super.muled(i_v);
	} 

	public DifferentialFunction<X> inverse() {
	//public Constant<X> inverse() {
		return new Constant<X>(m_x.inverse(), m_factory);
	}

	public DifferentialFunction<X> negate() {
	//public Constant<X> negate() {
		return new Constant<X>(m_x.negate(), m_factory);
	}

	//This class must be immutable.
	//set and assign must not be implemented.  
	@SuppressWarnings("unused")
	private final void set(X i_x){
	}
	@SuppressWarnings("unused")
	private final void assign(X i_x){
	}

}

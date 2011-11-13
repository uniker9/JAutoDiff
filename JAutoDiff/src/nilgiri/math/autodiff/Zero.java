package nilgiri.math.autodiff;

import nilgiri.math.AbstractIdentityFactory;
import nilgiri.math.Field;

/** This class represents the zero element of X forms a field.
 * @author uniker9
 *
 * @param <X> A set forms a field.
 */
public class Zero<X extends Field<X>> extends Constant<X> {

	/** Constructs an object whose value is zero.
	 * @param i_factory
	 */
	public Zero(AbstractIdentityFactory<X> i_factory) {
		super(i_factory.zero(), i_factory);
	}

	@Override
	public DifferentialFunction<X> plus(DifferentialFunction<X> i_v) {
		return i_v;
	}
	
	@Override
	protected DifferentialFunction<X> plused(DifferentialFunction<X> i_v) {
		return i_v;
	}
	
	@Override
	public DifferentialFunction<X> mul(DifferentialFunction<X> i_v) {
		return this;
	}
	
	@Override
	protected DifferentialFunction<X> muled(DifferentialFunction<X> i_v) {
		return this;
	}

	@Override
	public DifferentialFunction<X> inverse() {
	//public Constant<X> inverse() {
		//TODO
		return null;
	}

	@Override
	public DifferentialFunction<X> negate() {
	//public Constant<X> negate() {
		return this;
	}

}

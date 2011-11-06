package nilgiri.math.autodiff;

import nilgiri.math.Field;

/** This class represents the inverse element of an argument in X with respect to addition.
 * @author uniker9
 *
 * @param <X> A set forms a field.
 */
public class Negative<X extends Field<X>> extends AbstractUnaryFunction<X> {

	/** Constructs an object whose value is (- i_v).
	 * @param i_v
	 */
	public Negative(DifferentialFunction<X> i_v) {
		super(i_v);
	}

	@Override
	public X getValue() {
		return arg().getValue().negate();
	}

	@Override
	public DifferentialFunction<X> diff(Variable<X> i_v) {
		return (arg().diff(i_v)).negate();
	}

	@Override
	public String toString() {
		return "-" + arg().toString() + "";
	}

	public DifferentialFunction<X> negate() {
		return arg();
	}

}

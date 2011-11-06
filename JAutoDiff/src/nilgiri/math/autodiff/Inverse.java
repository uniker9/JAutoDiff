package nilgiri.math.autodiff;

import nilgiri.math.Field;

/** This class represents the inverse element of an argument in X with respect to multiplication.
 * @author uniker9
 *
 * @param <X> A set forms a field.
 */
public class Inverse<X extends Field<X>> extends AbstractUnaryFunction<X> {

	/** Constructs an object whose value is (1 / i_v).
	 * @param i_v
	 */
	public Inverse(DifferentialFunction<X> i_v) {
		super(i_v);
	}

	@Override
	public X getValue() {
		return arg().getValue().inverse();
	}

	@Override
	public DifferentialFunction<X> diff(Variable<X> i_v) {
		return new PolynomialTerm<X>(-1L, arg(), -2).mul(arg().diff(i_v));
	}

	@Override
	public String toString() {
		return "(" + arg().toString() + ")^(-1)";
	}

	public DifferentialFunction<X> inverse() {
		return arg();
	}
}

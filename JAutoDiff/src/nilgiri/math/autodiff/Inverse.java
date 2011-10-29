package nilgiri.math.autodiff;

import nilgiri.math.Field;

public class Inverse<X extends Field<X>> extends AbstractUnaryFunction<X> {

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

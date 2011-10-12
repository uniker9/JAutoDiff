package nilgiri.math.autodiff;

import nilgiri.math.FieldNumber;

public class Negative<X extends FieldNumber<X>> extends
		AbstractUnaryFunction<X> {

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

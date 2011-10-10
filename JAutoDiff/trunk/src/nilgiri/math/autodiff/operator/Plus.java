package nilgiri.math.autodiff.operator;

import nilgiri.math.FieldNumber;

import nilgiri.math.autodiff.DifferentialFunction;
import nilgiri.math.autodiff.Variable;

public class Plus<X extends FieldNumber<X>> extends AbstractOperator<X> {

	public Plus(DifferentialFunction<X> i_v1, DifferentialFunction<X> i_v2) {
		super(i_v1, i_v2);
	}

	@Override
	public X getValue() {
		return larg().getValue().plus(rarg().getValue());
	}

	@Override
	public DifferentialFunction<X> diff(Variable<X> i_v1) {
		return larg().diff(i_v1).plus(rarg().diff(i_v1));
	}

	@Override
	public String toString() {
		return "(" + larg().toString() + "+" + rarg().toString() + ")";
	}
}

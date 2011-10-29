package nilgiri.math.autodiff.operator;

import nilgiri.math.Field;

import nilgiri.math.autodiff.DifferentialFunction;
import nilgiri.math.autodiff.Variable;

public class Multi<X extends Field<X>> extends AbstractOperator<X> {

	public Multi(DifferentialFunction<X> i_v1, DifferentialFunction<X> i_v2) {
		super(i_v1, i_v2);
	}

	@Override
	public X getValue() {
		return larg().getValue().mul(rarg().getValue());
	}

	@Override
	public DifferentialFunction<X> diff(Variable<X> i_v1) {

		return (larg().diff(i_v1).mul(rarg())).plus(larg().mul(rarg().diff(i_v1)));
	}

	@Override
	public String toString() {
		return "(" + larg().toString() + "*" + rarg().toString() + ")";
	}
}

package nilgiri.math.autodiff.operator;

import nilgiri.math.Field;

import nilgiri.math.autodiff.DifferentialFunction;
import nilgiri.math.autodiff.AbstractBinaryFunction;

public abstract class AbstractOperator<X extends Field<X>> extends
		AbstractBinaryFunction<X> {

	public AbstractOperator(DifferentialFunction<X> i_v1,
			DifferentialFunction<X> i_v2) {
		super(i_v1, i_v2);
	}

}

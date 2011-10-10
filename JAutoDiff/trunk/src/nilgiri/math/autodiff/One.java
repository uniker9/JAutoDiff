package nilgiri.math.autodiff;

import nilgiri.math.AbstractFieldNumberFactory;

import nilgiri.math.FieldNumber;

public class One<X extends FieldNumber<X>> extends Constant<X> {

	public One(AbstractFieldNumberFactory<X> i_factory) {
		super(i_factory.one(), i_factory);
	}

	public DifferentialFunction<X> multi(DifferentialFunction<X> i_v) {
		return i_v;
	}

	protected DifferentialFunction<X> multiee(DifferentialFunction<X> i_v) {
		return i_v;
	}

}

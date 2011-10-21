package nilgiri.math.autodiff;

import nilgiri.math.AbstractFieldNumberFactory;

import nilgiri.math.FieldNumber;

public class One<X extends FieldNumber<X>> extends Constant<X> {

	public One(AbstractFieldNumberFactory<X> i_factory) {
		super(i_factory.one(), i_factory);
	}

	public DifferentialFunction<X> mul(DifferentialFunction<X> i_v) {
		return i_v;
	}

	protected DifferentialFunction<X> muled(DifferentialFunction<X> i_v) {
		return i_v;
	}

}

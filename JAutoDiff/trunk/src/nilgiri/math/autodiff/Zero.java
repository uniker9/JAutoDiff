package nilgiri.math.autodiff;

import nilgiri.math.AbstractFieldNumberFactory;
import nilgiri.math.FieldNumber;

public class Zero<X extends FieldNumber<X>> extends Constant<X> {

	public Zero(AbstractFieldNumberFactory<X> i_RNFactory) {
		super(i_RNFactory.zero(), i_RNFactory);
	}

	public DifferentialFunction<X> plus(DifferentialFunction<X> i_v) {
		return i_v;
	}

	protected DifferentialFunction<X> plusee(DifferentialFunction<X> i_v) {
		return i_v;
	}

	public Constant<X> multi(DifferentialFunction<X> i_v) {
		return this;
	}

	protected Constant<X> multiee(DifferentialFunction<X> i_v) {
		return this;
	}

	public Constant<X> inverse() {
		return null;
	}

	public Constant<X> negate() {
		return this;
	}

}

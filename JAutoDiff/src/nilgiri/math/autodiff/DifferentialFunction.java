package nilgiri.math.autodiff;

import nilgiri.math.FieldNumber;
import nilgiri.math.autodiff.DifferentialField;

import nilgiri.math.autodiff.operator.Plus;
import nilgiri.math.autodiff.operator.Multi;

public abstract class DifferentialFunction<X extends FieldNumber<X>> implements
		DifferentialField<Variable<X>, DifferentialFunction<X>> {

	public DifferentialFunction() {
	}

	public abstract X getValue();

	public abstract String toString();

	public abstract DifferentialFunction<X> diff(Variable<X> i_v1);

	public DifferentialFunction<X> plus(DifferentialFunction<X> i_v) {
		return i_v.plusee(this);
	}

	protected DifferentialFunction<X> plusee(DifferentialFunction<X> i_v) {
		return new Plus<X>(i_v, this);
	}

	public DifferentialFunction<X> minus(DifferentialFunction<X> i_v) {
		return plus(i_v.negate());
	}

	public DifferentialFunction<X> multi(DifferentialFunction<X> i_v) {
		return i_v.multiee(this);
	}

	protected DifferentialFunction<X> multiee(DifferentialFunction<X> i_v) {
		return new Multi<X>(i_v, this);
	}

	public DifferentialFunction<X> divide(DifferentialFunction<X> i_v) {
		return multi(i_v.inverse());
	}

	public DifferentialFunction<X> inverse() {
		return new Inverse<X>(this);
	}

	public DifferentialFunction<X> negate() {
		return new Negative<X>(this);
	}

	public DifferentialFunction<X> multi(long i_n) {
		return new PolynomialTerm<X>(i_n, this, 1);
	}

	public DifferentialFunction<X> pow(int i_n) {
		return new PolynomialTerm<X>(1L, this, i_n);
	}
}

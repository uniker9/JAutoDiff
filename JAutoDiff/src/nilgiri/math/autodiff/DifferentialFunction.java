package nilgiri.math.autodiff;

import nilgiri.math.Field;
import nilgiri.math.autodiff.DifferentialField;

import nilgiri.math.autodiff.operator.Plus;
import nilgiri.math.autodiff.operator.Multi;

public abstract class DifferentialFunction<X extends Field<X>> implements
		DifferentialField<Variable<X>, DifferentialFunction<X>> {

	public DifferentialFunction() {
	}

	public abstract X getValue();

	public abstract String toString();

	public abstract DifferentialFunction<X> diff(Variable<X> i_v1);

	public DifferentialFunction<X> plus(DifferentialFunction<X> i_v) {
		return i_v.plused(this);
	}

	protected DifferentialFunction<X> plused(DifferentialFunction<X> i_v) {
		return new Plus<X>(i_v, this);
	}

	public DifferentialFunction<X> minus(DifferentialFunction<X> i_v) {
		return plus(i_v.negate());
	}

	public DifferentialFunction<X> mul(DifferentialFunction<X> i_v) {
		return i_v.muled(this);
	}

	protected DifferentialFunction<X> muled(DifferentialFunction<X> i_v) {
		return new Multi<X>(i_v, this);
	}

	public DifferentialFunction<X> div(DifferentialFunction<X> i_v) {
		return mul(i_v.inverse());
	}

	public DifferentialFunction<X> inverse() {
		return new Inverse<X>(this);
	}

	public DifferentialFunction<X> negate() {
		return new Negative<X>(this);
	}

	public DifferentialFunction<X> mul(long i_n) {
		return new PolynomialTerm<X>(i_n, this, 1);
	}

	public DifferentialFunction<X> pow(int i_n) {
		return new PolynomialTerm<X>(1L, this, i_n);
	}
}

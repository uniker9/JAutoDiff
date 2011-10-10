package nilgiri.math.autodiff;

import nilgiri.math.FieldNumber;

public interface Differential<X extends FieldNumber<X>, F> {
	public F diff(Variable<X> i_v);
}

package nilgiri.math.autodiff;

import nilgiri.math.Ring;
import nilgiri.math.FieldNumber;

public interface DifferentialRing<X extends FieldNumber<X>, F> extends Ring<F>,
		Differential<X, F> {

}

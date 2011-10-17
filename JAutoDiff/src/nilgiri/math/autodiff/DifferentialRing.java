package nilgiri.math.autodiff;

import nilgiri.math.Ring;

public interface DifferentialRing<X, F> extends Ring<F>,
		Differential<X, F> {

}

package nilgiri.math.autodiff;

import nilgiri.math.Group;

public interface DifferentialGroup<X, F> extends Group<F>,
		Differential<X, F> {

}

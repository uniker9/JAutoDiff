package nilgiri.math.autodiff;

import nilgiri.math.Field;

public interface DifferentialField<X, F> extends
		Field<F>, Differential<X, F> {

}

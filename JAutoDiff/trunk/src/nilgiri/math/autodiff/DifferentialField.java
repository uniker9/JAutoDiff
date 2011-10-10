package nilgiri.math.autodiff;

import nilgiri.math.Field;
import nilgiri.math.FieldNumber;

public interface DifferentialField<X extends FieldNumber<X>, F> extends
		Field<F>, Differential<X, F> {

}

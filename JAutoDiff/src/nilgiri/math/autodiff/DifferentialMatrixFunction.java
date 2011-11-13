package nilgiri.math.autodiff;

import nilgiri.math.Field;
import nilgiri.math.Ring;

public interface DifferentialMatrixFunction<X extends Field<X>> extends
		Ring<DifferentialMatrixFunction<X>>, Differential<X, DifferentialMatrixFunction<X>> {

}

package nilgiri.math.autodiff;

import nilgiri.math.Field;
import nilgiri.math.Group;

public interface DifferentialVectorFunction<X extends Field<X>> extends
		Group<DifferentialVectorFunction<X>>, Differential<Variable<X>, DifferentialVectorFunction<X>> {
	
}

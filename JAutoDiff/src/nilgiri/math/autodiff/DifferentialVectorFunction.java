package nilgiri.math.autodiff;

import nilgiri.math.CommutativeGroup;

import nilgiri.math.Field;


/**
 * @author uniker9
 *
 * @param <X> A set forms a field.
 */
public interface DifferentialVectorFunction<X extends Field<X>> extends
		CommutativeGroup<DifferentialVectorFunction<X>>, Differential<X, DifferentialVectorFunction<X>> {
	
}

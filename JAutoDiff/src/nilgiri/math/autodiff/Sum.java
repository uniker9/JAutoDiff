package nilgiri.math.autodiff;

import nilgiri.math.Field;


/** A sum of two objects of DifferentialFunction&ltX&gt.
 * @author uniker9
 *
 * @param <X> A set forms a field.
 */
public class Sum<X extends Field<X>> extends AbstractBinaryFunction<X> {

	/** Constructs an object whose value is  (i_v1 + i_v2)
	 * @param i_v1 left argument of an addition operator +.
	 * @param i_v2 right argument of an addition operator +.
	 */
	public Sum(DifferentialFunction<X> i_v1, DifferentialFunction<X> i_v2) {
		super(i_v1, i_v2);
	}

	@Override
	public X getValue() {
		return larg().getValue().plus(rarg().getValue());
	}

	@Override
	public DifferentialFunction<X> diff(Variable<X> i_v1) {
		return (larg() == rarg()) 
			? larg().diff(i_v1).mul(2L) // Field is commutative with respect to addition.  
			: larg().diff(i_v1).plus(rarg().diff(i_v1));
	}

	@Override
	public String toString() {
		return "(" + larg().toString() + "+" + rarg().toString() + ")";
	}
}

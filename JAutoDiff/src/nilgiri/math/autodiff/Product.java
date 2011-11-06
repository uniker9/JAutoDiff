package nilgiri.math.autodiff;

import nilgiri.math.Field;


/** A product of two objects of DifferentialFunction&lt<X&rt>.
 * @author uniker9
 *
 * @param <X> A set forms a field.
 */
public class Product<X extends Field<X>> extends AbstractBinaryFunction<X> {

	/** Constructs an object whose value is  (i_v1 * i_v2)
	 * @param i_v1 left argument of a multiplication operator *.
	 * @param i_v2 right argument of a multiplication operator *.
	 */
	public Product(DifferentialFunction<X> i_v1, DifferentialFunction<X> i_v2) {
		super(i_v1, i_v2);
	}

	@Override
	public X getValue() {
		return larg().getValue().mul(rarg().getValue());
	}

	@Override
	public DifferentialFunction<X> diff(Variable<X> i_v1) {

		return (larg().diff(i_v1).mul(rarg())).plus(larg().mul(rarg().diff(i_v1)));
	}

	@Override
	public String toString() {
		return "(" + larg().toString() + "*" + rarg().toString() + ")";
	}
}

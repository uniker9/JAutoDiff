package nilgiri.math.autodiff;

import nilgiri.math.Field;



/** This interface Differential&ltX, D&gt indicates objects implements this are differential with respect to Variable&ltX&gt and the derivatives are of D.  
 * @author uniker9
 *
 * @param <X>
 * @param <D>
 */
public interface Differential<X extends Field<X>, D> {

	/** Returns an object of D whose value is the derivative with respect to i_v.
	 * @param i_v
	 * @return the derivative with respect to i_v.
	 */
	public D diff(Variable<X> i_v);
}

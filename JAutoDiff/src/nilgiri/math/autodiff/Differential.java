package nilgiri.math.autodiff;



/** This interface Differential&ltX, D&gt indicates objects implements this are differential with respect to V and the derivatives are of D.  
 * @author uniker9
 *
 * @param <V>
 * @param <D>
 */
public interface Differential<V, D> {

	/** Returns an object of D whose value is the derivative with respect to i_v.
	 * @param i_v
	 * @return the derivative with respect to i_v.
	 */
	public D diff(V i_v);
}

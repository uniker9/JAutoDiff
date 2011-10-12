package nilgiri.math;

public interface Field<X> extends Ring<X> {

	// commutative
	X inverse();

	X divide(X i_v);

}

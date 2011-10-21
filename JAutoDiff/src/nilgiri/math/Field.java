package nilgiri.math;

public interface Field<X> extends Ring<X> {

	// commutative
	X inverse();

	X div(X i_v);

}

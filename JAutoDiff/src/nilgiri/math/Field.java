package nilgiri.math;

// commutative
public interface Field<X> extends Ring<X> {

	X inverse();

	X div(X i_v);
}

package nilgiri.math;

public interface Group<X> {

	X negate();

	X plus(X i_v);

	X minus(X i_v);

	X multi(long i_n);
}

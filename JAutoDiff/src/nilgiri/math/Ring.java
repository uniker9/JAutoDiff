package nilgiri.math;

public interface Ring<X> extends Group<X> {

	X mul(X i_v);

	X pow(int i_n);

}

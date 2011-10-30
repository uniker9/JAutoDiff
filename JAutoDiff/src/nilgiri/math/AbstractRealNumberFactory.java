package nilgiri.math;

public interface AbstractRealNumberFactory<X extends RealNumber<X>> extends
		AbstractFieldFactory<X> {

	X cos(X i_x);

	X sin(X i_x);

	X tan(X i_x);

	X exp(X i_x);

	X log(X i_x);

	X pow(X i_x, X i_y);

}

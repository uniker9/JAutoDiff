package nilgiri.math;

public interface ComplexNumber<R extends RealNumber<R>, X> extends
		FieldNumber<X> {

	X conjugate();

	R re();

	R im();
}

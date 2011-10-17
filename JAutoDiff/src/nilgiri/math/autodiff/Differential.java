package nilgiri.math.autodiff;



public interface Differential<X, D> {
	public D diff(X i_v);
}

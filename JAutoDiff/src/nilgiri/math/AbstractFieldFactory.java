package nilgiri.math;

public interface AbstractFieldFactory<X extends Field<X>> {

	X zero();

	X one();

}

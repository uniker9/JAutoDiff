package nilgiri.math;

public interface AbstractFieldNumberFactory<X extends Field<X>> {

	X zero();

	X one();

}

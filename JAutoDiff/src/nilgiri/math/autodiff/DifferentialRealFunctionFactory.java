package nilgiri.math.autodiff;

import java.util.ArrayList;

import nilgiri.math.AbstractIdentityFactory;
import nilgiri.math.AbstractRealNumberFactory;

import nilgiri.math.RealNumber;

public class DifferentialRealFunctionFactory<X extends RealNumber<X>> {

	
	protected AbstractRealNumberFactory<X> m_factory;	

	/**
	 * @param i_RNFactory
	 * @param i_RFFactory
	 */
	public DifferentialRealFunctionFactory(AbstractRealNumberFactory<X> i_factory) {
		if (i_factory != null) {
			m_factory = i_factory;
		} else {
			throw new IllegalArgumentException("Input not null value.");
		}
	}

	public Constant<X> val(X i_x) {
		return new Constant<X>(i_x, m_factory);
	}
	public ConstantVector<X> val(X ...i_x){
		int size = i_x.length;
		ArrayList<Constant<X> > list = new ArrayList<Constant<X> >(size);
		for(int i = 0; i < size; i++){
			list.add(val(i_x[i]));
		}
		return new ConstantVector<X>(m_factory, list);
	}
	
	//ZeroVector
	public ConstantVector<X> zero(int i_size){
		ArrayList<Constant<X> > list = new ArrayList<Constant<X> >(i_size);
		for(int i = 0; i < i_size; i++){
			list.add(zero());
		}
		return new ConstantVector<X>(m_factory, list);
	}
	
	public Variable<X> var(String i_name, X i_x) {
		return new Variable<X>(i_name, i_x, m_factory);
	}
	public VariableVector<X> var(String i_name, X ...i_x){
		int size = i_x.length;
		ArrayList<Variable<X> > list = new ArrayList<Variable<X> >(size);
		for(int i = 0; i < size; i++){
			list.add(var(i_name+String.valueOf(i), i_x[i]));
		}
		return new VariableVector<X>(m_factory, list);
	}
	public VariableVector<X> var(String i_name, int i_size){
		ArrayList<Variable<X> > list = new ArrayList<Variable<X> >(i_size);
		for(int i = 0; i < i_size; i++){
			list.add(var(i_name+String.valueOf(i), m_factory.zero()));
		}
		return new VariableVector<X>(m_factory, list);
	}
	
	public DifferentialVectorFunction<X> function(DifferentialFunction<X> ...i_x){
		int size = i_x.length;
		ArrayList<DifferentialFunction<X> > list = new ArrayList<DifferentialFunction<X> >(size);
		for(int i = 0; i < size; i++){
			list.add(i_x[i]);
		}
		return new DifferentialVectorFunction<X>(m_factory, list);
	}
	
	
	// --------------
	public Zero<X> zero() {
		return new Zero<X>(m_factory);
	}

	public One<X> one() {
		return new One<X>(m_factory);
	}

	public DifferentialFunction<X> cos(DifferentialFunction<X> i_x) {
		return new AbstractUnaryFunction<X>(i_x) {
			@Override
			public X getValue() {
				return m_factory.cos(arg().getValue());
			}

			@Override
			public DifferentialFunction<X> diff(Variable<X> i_v) {
				return (sin(arg()).mul(arg().diff(i_v))).negate();
			}

			@Override
			public String toString() {
				return "cos(" + arg().toString() + ")";
			}

		};
	}

	public DifferentialFunction<X> sin(DifferentialFunction<X> i_x) {
		return new AbstractUnaryFunction<X>(i_x) {
			@Override
			public X getValue() {
				return m_factory.sin(arg().getValue());
			}

			@Override
			public DifferentialFunction<X> diff(Variable<X> i_v) {
				return cos(arg()).mul(arg().diff(i_v));
			}

			@Override
			public String toString() {
				return "sin(" + arg().toString() + ")";
			}
		};
	}

	public DifferentialFunction<X> tan(DifferentialFunction<X> i_x) {
		return new AbstractUnaryFunction<X>(i_x) {
			@Override
			public X getValue() {
				return m_factory.tan(arg().getValue());
			}

			@Override
			public DifferentialFunction<X> diff(Variable<X> i_v) {
				// return new
				// Inverse<X>(cos(arg()).multi(cos(arg()))).multi(arg().diff(i_v));
				// return new Inverse<X>( new Square<X>( cos(arg())
				// )).multi(arg().diff(i_v));
				// return pow(cos(arg()), -2).multi(arg().diff(i_v));
				return (new PolynomialTerm<X>(1, cos(arg()), -2)).mul(arg()
						.diff(i_v));
			}

			@Override
			public String toString() {
				return "tan(" + arg().toString() + ")";
			}
		};
	}

	public DifferentialFunction<X> exp(DifferentialFunction<X> i_x) {
		return new AbstractUnaryFunction<X>(i_x) {
			@Override
			public X getValue() {
				return m_factory.exp(arg().getValue());
			}

			@Override
			public DifferentialFunction<X> diff(Variable<X> i_v) {
				return exp(arg()).mul(arg().diff(i_v));
			}

			@Override
			public String toString() {
				return "exp(" + arg().toString() + ")";
			}
		};
	}

	public DifferentialFunction<X> log(DifferentialFunction<X> i_x) {
		return new AbstractUnaryFunction<X>(i_x) {

			@Override
			public X getValue() {
				return m_factory.log(arg().getValue());
			}

			@Override
			public DifferentialFunction<X> diff(Variable<X> i_v) {
				return new Inverse<X>(arg()).mul(arg().diff(i_v));
			}

			@Override
			public String toString() {
				return "log(" + arg().toString() + ")";
			}
		};
	}

	/*
	 * public DifferentialFunction<X> pow(DifferentialFunction<X> i_x, final int
	 * i_y){
	 * 
	 * return new AbstractUnaryFunction<X>(i_x){
	 * 
	 * @Override public X getValue(){ return m_RNFactory.pow(arg().getValue(),
	 * i_y); }
	 * 
	 * @Override public DifferentialFunction<X> diff(Variable<X> i_v){
	 * //m_RNFactory. return
	 * RealFunctionFactory.this.constant(m_RNFactory.create(i_y)).multi(pow(arg(),
	 * i_y - 1)).multi(arg().diff(i_v)); }
	 * 
	 * @Override public String toString(){ return arg().toString() + "^" +
	 * String.valueOf(i_y) +" "; } }; }
	 */

	public DifferentialFunction<X> pow(DifferentialFunction<X> i_x,
			Constant<X> i_y) {

		return new AbstractBinaryFunction<X>(i_x, i_y) {

			@Override
			public X getValue() {
				return m_factory.pow(larg().getValue(), rarg().getValue());
			}

			@Override
			public DifferentialFunction<X> diff(Variable<X> i_v) {
				Constant<X> ym1 = DifferentialRealFunctionFactory.this.val(rarg()
						.getValue().minus(m_factory.one()));
				return rarg().mul(DifferentialRealFunctionFactory.this.pow(larg(), ym1))
						.mul(larg().diff(i_v));
			}

			@Override
			public String toString() {
				return "pow(" + larg().toString() + ", " + rarg().toString() + ")";
			}
		};
	}
	
	public DifferentialFunction<X> sqrt(DifferentialFunction<X> i_x) {
		return new AbstractUnaryFunction<X>(i_x) {

			@Override
			public X getValue() {
				return m_factory.sqrt(arg().getValue());
			}

			@Override
			public DifferentialFunction<X> diff(Variable<X> i_v) {
				return ((sqrt(arg()).inverse()).div(
						DifferentialRealFunctionFactory.this.val(m_factory.one().mul(2L)			
						))).mul(arg().diff(i_v));
			}

			@Override
			public String toString() {
				return "sqrt(" + arg().toString() + ")";
			}
		};
	}
	
	public DifferentialFunction<X> square(DifferentialFunction<X> i_x) {
		return new AbstractUnaryFunction<X>(i_x) {

			@Override
			public X getValue() {
				return m_factory.square(arg().getValue());
			}

			@Override
			public DifferentialFunction<X> diff(Variable<X> i_v) {
				return arg().mul(
						DifferentialRealFunctionFactory.this.val(m_factory.one().mul(2L)			
						)).mul(arg().diff(i_v));
			}

			@Override
			public String toString() {
				return "square(" + arg().toString() + ")";
			}
		};
	}
	
}

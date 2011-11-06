package nilgiri.math.autodiff;

import nilgiri.math.AbstractFieldFactory;
import nilgiri.math.AbstractRealFunctionFactory;

import nilgiri.math.RealNumber;

public class DifferentialRealFunctionFactory<X extends RealNumber<X>> implements 
	AbstractFieldFactory<DifferentialFunction<X>> {
	
	protected AbstractFieldFactory<X> m_RNFactory;
	protected AbstractRealFunctionFactory<X> m_RFFactory;	

	/**
	 * @param i_RNFactory
	 * @param i_RFFactory
	 */
	public DifferentialRealFunctionFactory(AbstractFieldFactory<X> i_RNFactory,
			AbstractRealFunctionFactory<X> i_RFFactory) {
		if (i_RNFactory != null && i_RFFactory != null) {
			m_RNFactory = i_RNFactory;
			m_RFFactory = i_RFFactory;
		} else {
			throw new IllegalArgumentException("Input not null value.");
		}
	}

	public Constant<X> constant(X i_x) {
		return new Constant<X>(i_x, m_RNFactory);
	}

	public Variable<X> variable(String i_name, X i_x) {
		return new Variable<X>(i_name, i_x, m_RNFactory);
	}

	// --------------
	public DifferentialFunction<X> zero() {
		return new Zero<X>(m_RNFactory);
	}

	public DifferentialFunction<X> one() {
		return new One<X>(m_RNFactory);
	}

	public DifferentialFunction<X> cos(DifferentialFunction<X> i_x) {
		return new AbstractUnaryFunction<X>(i_x) {
			@Override
			public X getValue() {
				return m_RFFactory.cos(arg().getValue());
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
				return m_RFFactory.sin(arg().getValue());
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
				return m_RFFactory.tan(arg().getValue());
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
				return m_RFFactory.exp(arg().getValue());
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
				return m_RFFactory.log(arg().getValue());
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
				return m_RFFactory.pow(larg().getValue(), rarg().getValue());
			}

			@Override
			public DifferentialFunction<X> diff(Variable<X> i_v) {
				Constant<X> ym1 = DifferentialRealFunctionFactory.this.constant(rarg()
						.getValue().minus(m_RNFactory.one()));
				return rarg().mul(DifferentialRealFunctionFactory.this.pow(larg(), ym1))
						.mul(larg().diff(i_v));
			}

			@Override
			public String toString() {
				return "pow(" + larg().toString() + ", " + rarg().toString() + ")";
			}
		};
	}

}

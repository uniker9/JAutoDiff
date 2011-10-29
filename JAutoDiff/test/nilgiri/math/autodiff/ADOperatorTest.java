package nilgiri.math.autodiff;


import junit.framework.Assert;

import nilgiri.math.DoubleReal;
import nilgiri.math.DoubleRealFactory;

import org.junit.Test;


public class ADOperatorTest {
	DoubleRealFactory RF = DoubleRealFactory.instance();
	RealFunctionFactory<DoubleReal> DF = new RealFunctionFactory<DoubleReal>(RF);

	
	double value_x = 3.0;
	double value_y = 5.0;
	double value_p = 7.0;
	double value_q = 9.0;
	

	Variable<DoubleReal> x = DF.variable("x", new DoubleReal(value_x));
	Variable<DoubleReal> y = DF.variable("y", new DoubleReal(value_y));
	Constant<DoubleReal> p = DF.constant(new DoubleReal(value_p));
	Constant<DoubleReal> q = DF.constant(new DoubleReal(value_q));
	
	final DifferentialFunction<DoubleReal> ZERO = DF.zero();
	final DifferentialFunction<DoubleReal> ONE = DF.one();
	
	
	private void test(double i_expected, DifferentialFunction<DoubleReal> i_f){
		Assert.assertEquals(i_f.toString(), i_expected, i_f.getValue().doubleValue());
	}
	
	@Test
	public void testPlus() {
		test(value_x + value_y, x.plus(y));
		test(value_x + value_p, x.plus(p));
		test(value_p + value_y, p.plus(y));
		test(value_p + value_q, p.plus(q));

		test(0.0 + value_y, ZERO.plus(y));
		test(value_p + 0.0, p.plus(ZERO));
		test(1.0 + value_q, ONE.plus(q));
		test(value_x + 1.0, x.plus(ONE));

		test(2.0, ONE.plus(ONE)); 
		test(1.0, ONE.plus(ZERO)); 
		test(1.0, ZERO.plus(ONE)); 
		test(0.0, ZERO.plus(ZERO)); 
	}
	
	
	@Test
	public void testPlusDiff() {
		test(2.0, (x.plus(x)).diff(x));
		test(0.0, (x.plus(x)).diff(y));
		test(1.0, (x.plus(y)).diff(x));
		test(1.0, (x.plus(y)).diff(y));
		test(1.0, (x.plus(p)).diff(x));
		test(0.0, (p.plus(x)).diff(y));
		test(0.0, (p.plus(q)).diff(x));
		
		test(0.0, (ZERO.plus(y)).diff(x));
		test(1.0, (ZERO.plus(y)).diff(y));
		test(1.0, (x.plus(ZERO)).diff(x));
		test(0.0, (x.plus(ZERO)).diff(y));

		test(0.0, (ONE.plus(y)).diff(x));
		test(1.0, (ONE.plus(y)).diff(y));
		test(1.0, (x.plus(ONE)).diff(x));
		test(0.0, (x.plus(ONE)).diff(y));
		
		test(0.0, ONE.plus(ONE).diff(x)); 
		test(0.0, ONE.plus(ZERO).diff(y)); 
		test(0.0, ZERO.plus(ONE).diff(x)); 
		test(0.0, ZERO.plus(ZERO).diff(y));
	}

}

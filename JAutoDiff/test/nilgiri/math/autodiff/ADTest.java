package nilgiri.math.autodiff;

import org.junit.Test;


import junit.framework.Assert;
import nilgiri.math.DoubleReal;
import nilgiri.math.DoubleRealFactory;
import nilgiri.math.DoubleRealFunctionFactory;

public class ADTest {
	private final DoubleRealFactory RNFactory = DoubleRealFactory.instance();
	private final DoubleRealFunctionFactory RFFactory = DoubleRealFunctionFactory.instance();
	private final DifferentialRealFunctionFactory<DoubleReal> DFFactory = new DifferentialRealFunctionFactory<DoubleReal>(RNFactory, RFFactory);

	private void test(double i_expected, DifferentialFunction<DoubleReal> i_f){
		String func_str = i_f.toString();
		double func_value = i_f.getValue().doubleValue();
		
		System.out.println(func_str +" = "+ func_value +" is expected as "+ i_expected);
		Assert.assertEquals(func_str, i_expected, func_value);
	}

	@Test
	public void testFunc() {
		double vx = 3.0;
		double vy = 5.0;
		double vq = 8.0;

		Variable<DoubleReal> x = DFFactory.variable("x", new DoubleReal(vx));
		Variable<DoubleReal> y = DFFactory.variable("y", new DoubleReal(vy));
		Constant<DoubleReal> q = DFFactory.constant(new DoubleReal(vq));

		//================================================================================
		//Construct functions
		//================================================================================
		//h = q*x*( cos(x*y) + y )
		DifferentialFunction<DoubleReal> h = q.mul(x).mul( DFFactory.cos( x.mul(y) ).plus(y) );
		
		//ph/px = q*( cos(x*y) + y ) + q*x*( -sin(x*y)*y ) 
		DifferentialFunction<DoubleReal> dhpx = h.diff(x);

		//ph/py = q*x*( -sin(x*y)*x + 1.0) 
		DifferentialFunction<DoubleReal> dhpy = h.diff(y);
		
		//p2h/px2 = q*( -sin(x*y)*y + y ) + q*( -sin(x*y)*y ) + q*x*( -cos(x*y)*y*y ) 
		DifferentialFunction<DoubleReal> d2hpxpx = dhpx.diff(x);

		//p2h/pypx = q*( -sin(x*y)*x + 1.0 ) + q*x*( -sin(x*y) - cos(x*y)*y*y ) 
		DifferentialFunction<DoubleReal> d2hpypx = dhpx.diff(y);

		//================================================================================
		//Test functions { h, ph/px, ph/py, p2h/px2, p2h/pypx }.
		//================================================================================
		test(vq*vx*( Math.cos(vx*vy) + vy ), h);
		test(vq*( Math.cos(vx*vy) + vy ) + vq*vx*(-Math.sin(vx*vy)*vy ), dhpx);
		test(vq*vx*( -Math.sin(vx*vy)*vx + 1.0 ), dhpy);
		test(vq*( -Math.sin(vx*vy)*vy ) + vq*( -Math.sin(vx*vy)*vy ) + vq*vx*(-Math.cos(vx*vy)*vy*vy), d2hpxpx);
		test(vq*( -Math.sin(vx*vy)*vx + 1.0 ) + vq*vx*( -Math.sin(vx*vy) - Math.cos(vx*vy)*vx*vy ), d2hpypx);

		//================================================================================
		//Change values of the variables.
		//================================================================================
		vx = 4.0;
		vy = 7.0;
		x.set(new DoubleReal(vx));
		y.set(new DoubleReal(vy));

		//================================================================================
		//Re-Test functions { h, ph/px, ph/py, p2h/px2, p2h/pypx }.
		//================================================================================
		//No reconstruction of the functions is necessary 
		//to get values of the functions for new values of variables.
		test(vq*vx*( Math.cos(vx*vy) + vy ), h);
		test(vq*( Math.cos(vx*vy) + vy ) + vq*vx*(-Math.sin(vx*vy)*vy ), dhpx);
		test(vq*vx*( -Math.sin(vx*vy)*vx + 1.0 ), dhpy);
		test(vq*( -Math.sin(vx*vy)*vy ) + vq*( -Math.sin(vx*vy)*vy ) + vq*vx*(-Math.cos(vx*vy)*vy*vy), d2hpxpx);
		test(vq*( -Math.sin(vx*vy)*vx + 1.0 ) + vq*vx*( -Math.sin(vx*vy) - Math.cos(vx*vy)*vx*vy ), d2hpypx);

	}
}

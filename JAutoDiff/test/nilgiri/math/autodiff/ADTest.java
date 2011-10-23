package nilgiri.math.autodiff;

import org.junit.Test;

import junit.framework.Assert;
import nilgiri.math.DoubleReal;
import nilgiri.math.DoubleRealFactory;

public class ADTest {
	private final DoubleRealFactory RF = DoubleRealFactory.instance();
	private final RealFunctionFactory<DoubleReal> DF = new RealFunctionFactory<DoubleReal>(RF);

	private void test(double i_expected, DifferentialFunction<DoubleReal> i_f){
		String func_str = i_f.toString();
		double func_value = i_f.getValue().doubleValue();
		
		System.out.println(func_str +" = "+ func_value);
		Assert.assertEquals(func_str, i_expected, func_value);
	}

	@Test
	public void testFunc() {
		double vx = 3.0;
		double vy = 5.0;
		double vq = 8.0;

		Variable<DoubleReal> x = DF.variable("x", new DoubleReal(vx));
		Variable<DoubleReal> y = DF.variable("y", new DoubleReal(vy));
		Constant<DoubleReal> q = DF.constant(new DoubleReal(vq));


		//h = q*x*( cos(x*y) + y )
		DifferentialFunction<DoubleReal> h = q.mul(x).mul( DF.cos( x.mul(y) ).plus(y) );
		test(vq*vx*( Math.cos(vx*vy) + vy ), h);

		//dh/dx = q*( cos(x*y) + y ) + q*x*( -sin(x*y)*y ) 
		DifferentialFunction<DoubleReal> dhdx = h.diff(x);
		test(vq*( Math.cos(vx*vy) + vy ) + vq*vx*(-Math.sin(vx*vy)*vy ), dhdx);

		//dh/dy = q*x*( -sin(x*y)*x + 1.0) 
		DifferentialFunction<DoubleReal> dhdy = h.diff(y);
		test(vq*vx*( -Math.sin(vx*vy)*vx + 1.0 ), dhdy);

		//d2h/dxdx = q*( -sin(x*y)*y + y ) + q*( -sin(x*y)*y ) + q*x*( -cos(x*y)*y*y ) 
		DifferentialFunction<DoubleReal> d2hdxdx = dhdx.diff(x);
		test(vq*( -Math.sin(vx*vy)*vy ) + vq*( -Math.sin(vx*vy)*vy ) + vq*vx*(-Math.cos(vx*vy)*vy*vy), d2hdxdx);

		//d2h/dydx = q*( -sin(x*y)*x + 1.0 ) + q*x*( -sin(x*y) - cos(x*y)*y*y ) 
		DifferentialFunction<DoubleReal> d2hdydx = dhdx.diff(y);
		test(vq*( -Math.sin(vx*vy)*vx + 1.0 ) + vq*vx*( -Math.sin(vx*vy) - Math.cos(vx*vy)*vx*vy ), d2hdydx);


		//Reset Value
		vx = 4.0;
		vy = 7.0;
		x.set(new DoubleReal(vx));
		y.set(new DoubleReal(vy));

		// No reconstruction of the functions is necessary to get values of the functions for new values of variables.
		
		//h
		test(vq*vx*( Math.cos(vx*vy) + vy ), h);

		//dh/dx = q*( cos(x*y) + y ) + q*x*( -sin(x*y)*y ) 
		test(vq*( Math.cos(vx*vy) + vy ) + vq*vx*(-Math.sin(vx*vy)*vy ), dhdx);

		//dh/dy = q*x*( -sin(x*y)*x + 1.0) 
		test(vq*vx*( -Math.sin(vx*vy)*vx + 1.0 ), dhdy);

		//d2h/dxdx = q*( -sin(x*y)*y + y ) + q*( -sin(x*y)*y ) + q*x*( -cos(x*y)*y*y ) 
		test(vq*( -Math.sin(vx*vy)*vy ) + vq*( -Math.sin(vx*vy)*vy ) + vq*vx*(-Math.cos(vx*vy)*vy*vy), d2hdxdx);

		//d2h/dydx = q*( -sin(x*y)*x + 1.0 ) + q*x*( -sin(x*y) - cos(x*y)*y*y ) 
		test(vq*( -Math.sin(vx*vy)*vx + 1.0 ) + vq*vx*( -Math.sin(vx*vy) - Math.cos(vx*vy)*vx*vy ), d2hdydx);

	}
}

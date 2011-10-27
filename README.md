# JAutoDiff : An Automatic Differentiation Library (Pure Java)

[Automatic differentiation](http://en.wikipedia.org/wiki/Automatic_differentiation/) 
is a tequnique to compute the derivatives of a function algebraically.  
There are many implementation in C/C++ (ex. 
[FADBAD++](http://www.fadbad.com/fadbad.html), 
[ADOL-C](http://projects.coin-or.org/ADOL-C), etc.), while are few in Java.

*JAutoDiff* is an automatic differentiation library coded in 100%  pure Java.  
This library provides a framework to compute derivatives of functions 
on arbitrary types of [field](http://en.wikipedia.org/wiki/Field_\(mathematics\)) 
using [generics](http://en.wikipedia.org/wiki/Generics_in_Java).

*Attention : At present, only adapters for double precision real numbers has been implemented,
  while adapters for [ia_math](http://interval.sourceforge.net/interval/java/ia_math/README.html), 
  one of the implementations of [interval arithmetic](http://en.wikipedia.org/wiki/Interval_arithmetic), 
  will be available soon.*

# Sample

	final DoubleRealFactory RF = DoubleRealFactory.instance();
	final RealFunctionFactory<DoubleReal> DF = new RealFunctionFactory<DoubleReal>(RF);


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



## Result

	((8.0*x)*(cos((x*y))+y)) = 101.7674900913883
	((8.0*(cos((x*y))+y))+((8.0*x)*-(sin((x*y))*y))) = -44.112044121724594
	((8.0*x)*(-(sin((x*y))*x)+1.0)) = -22.82072449131241
	((8.0*-(sin((x*y))*y))+((8.0*-(sin((x*y))*y))+((8.0*x)*-((cos((x*y))*y)*y)))) = 403.78972050272347
	((8.0*(-(sin((x*y))*x)+1.0))+((8.0*x)*-(((cos((x*y))*x)*y)+sin((x*y))))) = 250.27383230163406
	((8.0*x)*(cos((x*y))+y)) = 193.19661227796587
	((8.0*(cos((x*y))+y))+((8.0*x)*-(sin((x*y))*y))) = -12.383743511471195
	((8.0*x)*(-(sin((x*y))*x)+1.0)) = -2.6759409034072377
	((8.0*-(sin((x*y))*y))+((8.0*-(sin((x*y))*y))+((8.0*x)*-((cos((x*y))*y)*y)))) = 1479.024550089191
	((8.0*(-(sin((x*y))*x)+1.0))+((8.0*x)*-(((cos((x*y))*x)*y)+sin((x*y))))) = 853.1568857652521


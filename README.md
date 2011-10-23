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

	DoubleRealFactory RF = DoubleRealFactory.instance();
	RealFunctionFactory<DoubleReal> DF = new RealFunctionFactory<DoubleReal>(RF);

	double vx = 2.0;
	double vy = 5.0;
	double vq = 10.0;
	Variable<DoubleReal> x = DF.variable("x", new DoubleReal(vx));
	Variable<DoubleReal> y = DF.variable("y", new DoubleReal(vy));
	Constant<DoubleReal> q = DF.constant(new DoubleReal(vq));

	DifferentialFunction<DoubleReal> cosxx = DF.cos(x.multi(x));
	DifferentialFunction<DoubleReal> h = q.multi(x.multi(cosxx).plus(y));
	DifferentialFunction<DoubleReal> dhdx = h.diff(x);
	DifferentialFunction<DoubleReal> dhdy = h.diff(y);

	DoubleReal value_h = h.getValue();
	DoubleReal value_dhdx = dhdx.getValue();
	DoubleReal value_dhdy = dhdy.getValue();
	
	System.out.println("h : "+ h +" = "+ value_h);
	System.out.println("dhdx : "+ dhdx +" = "+ value_dhdx);
	System.out.println("dhdy : "+ dhdy +" = "+ value_dhdy);
	
	

## Result

	h : (10.0*((x*cos((x*x)))+y)) = 36.92712758272776
	dhdx : (10.0*(cos((x*x))+(x*-(sin((x*x))*(x+x))))) = 54.00776341599814
	dhdy : 10.0 = 10.0


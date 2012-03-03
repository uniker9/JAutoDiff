package nilgiri.planarSolarSystem;



import java.awt.Color;
import java.util.Iterator;
import javax.swing.JFrame;
import nilgiri.math.autodiff.DifferentialRealFunctionFactory;
import nilgiri.math.autodiff.DifferentialVectorFunction;
import nilgiri.math.autodiff.Variable;
import nilgiri.math.ia.DoubleRealInterval;
import nilgiri.math.ia.DoubleRealIntervalFactory;
import nilgiri.processing.AbstractViewerPApplet;
import nilgiri.processing.PAppletFrame;
import nilgiri.processing.ViewConfig2D;

import processing.core.PApplet;

public class PSimApplet extends AbstractViewerPApplet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MoverSimulator<DoubleRealInterval> m_simulator;
	private int m_steps = 0;
	
	private static final double AU = 149597870000.0; // Astronomical Unit : the mean Earth-Sun distance.
	
	public void setup(){
		
		int windowWidth = 800;
		int windowHeight = 800;

		size(windowWidth, windowHeight);
		textFont(createFont("Lucida Console", 12));
		ViewConfig2D vc = viewConfig();
		vc.setScale((float)(0.3f*windowWidth/AU)); 
				
		Color bgcolor = new Color(0, 0, 0);
		this.setBackground(bgcolor);
		background(bgcolor.getRGB());
		
		stroke(255);
		strokeWeight(0.1f);
		
		//----------------------------------------
		// Setup Simulation
		//----------------------------------------
		DoubleRealIntervalFactory VF = DoubleRealIntervalFactory.instance();
		DifferentialRealFunctionFactory<DoubleRealInterval> FF = new DifferentialRealFunctionFactory<DoubleRealInterval>(VF);
		m_simulator = new MoverSimulator<DoubleRealInterval>(VF, FF);
		m_simulator.setT(VF.val(0.0, 100000.0));
		m_simulator.setDT(VF.val(1000.0));
		
		Variable<DoubleRealInterval> t = m_simulator.getT();
		
		OrbitFactory<DoubleRealInterval> MF = new OrbitFactory<DoubleRealInterval>(VF, FF);
		
		final int DIM = 2;

		
		//----------------------------------------
		// Create Sun
		//----------------------------------------
		DifferentialVectorFunction<DoubleRealInterval> sun_orbit = MF.createCircularOrbit(	
				FF.zero(DIM),
				t,
				VF.zero(), //a
				VF.zero(), //omega
				VF.zero(), //t0
				VF.zero() //theta(t0)
				);
		AnalyticalMover<DoubleRealInterval> sun  = new AnalyticalMover<DoubleRealInterval>(
				t, FF.val(VF.val(1392000000.0)), sun_orbit);
		sun.addDrawer(new CircularMoverDrawer(this, 20.0f/vc.getScale(), new Color(255,69, 0)));
		sun.addDrawer(new StringDrawer(this, "Sun", 20, 0, 1.5f/vc.getScale(), Color.white));
		m_simulator.addAnalyticalMover("Sun", sun);
		
		
		//----------------------------------------
		// Create Earth
		//----------------------------------------
		DifferentialVectorFunction<DoubleRealInterval> earth_orbit
		= MF.createCircularOrbit(	
				sun.position(),
				t,
				VF.val(AU), //Semi-major axis [m]
				VF.val(2.0*Math.PI/(365.0*24.0*3600.0)), //  2-Pi / (Orbital Period) [rad/s]
				VF.zero(), //t0
				VF.zero() //theta(t0)
				);
		AnalyticalMover<DoubleRealInterval> earth  = new AnalyticalMover<DoubleRealInterval>(
				t, FF.val(VF.val(6356752.0)), earth_orbit);
		earth.addDrawer(new CircularMoverDrawer(this, 5.0f/vc.getScale(), new Color(0, 0, 205)));
		earth.addDrawer(new StringDrawer(this, "Earth", 10, 0, 1.5f/vc.getScale(), Color.white));
		m_simulator.addAnalyticalMover("Earth", earth);
		
		//----------------------------------------
		// Create Moon
		//----------------------------------------
		DifferentialVectorFunction<DoubleRealInterval> moon_orbit
		= MF.createCircularOrbit(	
				earth.position(),
				t, 
				VF.val(38440000.0*1000.0), //Semi-major axis [m]
				VF.val(2.0*Math.PI/(30.0*24.0*3600.0)), // 2-Pi / (Orbital Period) [rad/s]
				VF.zero(),
				VF.zero()
				);
		AnalyticalMover<DoubleRealInterval> moon  = new AnalyticalMover<DoubleRealInterval>(
				t, FF.val(VF.val(137150.0)), moon_orbit);
		moon.addDrawer(new CircularMoverDrawer(this, 3.0f/vc.getScale(), Color.YELLOW));//new Color(192, 192, 192)));
		moon.addDrawer(new StringDrawer(this, "Moon", 10, 0, 1.5f/vc.getScale(), Color.white));
		m_simulator.addAnalyticalMover("Moon", moon);
		
		setStepsPerFrame(10);
	}
	
	public void setStepsPerFrame(int i_steps){ //Steps Per Frame
		m_steps = i_steps;
	}
	
	public void draw(){
		background(getBackground().getRGB());
		pushMatrix();
		translate(0.5f*getWidth(), 0.5f*getHeight());
		pushMatrix();
		float[] translation = new float[2];
		ViewConfig2D vc = viewConfig();

		scale(vc.getScale());				
		vc.getTranslation(translation);
		translate(translation[0], translation[1]);
		
		
		
		Iterator<AbstractMover<DoubleRealInterval> > itr =  m_simulator.getMoverIterator();

		while(itr.hasNext()){
			AbstractMover<DoubleRealInterval> mover = itr.next();
		
			pushMatrix();
			DifferentialVectorFunction<DoubleRealInterval> pos = mover.position();  
			DoubleRealInterval x = pos.get(0).getValue();
			DoubleRealInterval y = pos.get(1).getValue();
			translate((float)x.center(), (float)-y.center());

			// Draw Bounds
			float w = (float) x.width();
			float h = (float) y.width();
			fill(Color.GRAY.getRGB());
			rect(-0.5f*w, -0.5f*h, w, h);
			
			// Draw Objects
			mover.draw();
			
			popMatrix();
		}
		popMatrix();
		
		popMatrix();
		
		// Draw Header Field
		fill(getBackground().getRGB());
		rect(0, 0, getWidth(), 30);
		fill(255);
		
		int baseline = 20;
		DoubleRealInterval t = m_simulator.getT().getValue();
		double tcenter = t.center();
		double trange = 0.5*t.width();
		text("t : "+ String.format("%6.5f +- %6.5f", tcenter, trange), 50, baseline);
		text(getMouseGestureMode().getName(), getWidth()-150, baseline);
		
		
		
		for(int i = 0; i < m_steps; i++){
			m_simulator.nextStep();
		}
	}
	
	
	public static void main(String[] args){

		PApplet applet = new PSimApplet();
		applet.init();

		

		try{
			JFrame frame = new PAppletFrame(applet);
			frame.pack();
			frame.setLocation(200, 200);
			frame.setVisible(true);
		}catch(Exception ex){
			ex.printStackTrace();
		}

	}
	
	
}

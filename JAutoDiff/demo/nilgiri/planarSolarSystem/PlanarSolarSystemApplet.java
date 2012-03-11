package nilgiri.planarSolarSystem;



import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import javax.swing.JFrame;

import net.sourceforge.interval.ia_math.RealInterval;
import nilgiri.math.autodiff.DifferentialRealFunctionFactory;
import nilgiri.math.autodiff.DifferentialVectorFunction;
import nilgiri.math.autodiff.Variable;
import nilgiri.math.ia.DoubleRealInterval;
import nilgiri.math.ia.DoubleRealIntervalFactory;
import nilgiri.processing.AbstractViewerPApplet;
import nilgiri.processing.MouseGestureScaleMode;
import nilgiri.processing.MouseGestureTranslationMode;
import nilgiri.processing.PAppletFrame;
import nilgiri.processing.ViewConfig2D;

import processing.core.PApplet;

public class PlanarSolarSystemApplet extends AbstractViewerPApplet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MoverSimulator<DoubleRealInterval> m_simulator; // must be initialized in setup()
	private int m_steps = 0;
	
	private enum StateSpaceType{POSITION, VELOCITY, ACCEL, NONE};	
	private StateSpaceType m_sstype = StateSpaceType.NONE;
	private String m_sstypeName = "";

	private boolean m_drawWithBounds = false; 
	
	
	private static final double AU = 149597870000.0; // Astronomical Unit : the mean Earth-Sun distance.

	
	private LocalCanvasOriginTranslater m_LCOTranslater = new LocalCanvasOriginTranslater();
	private class LocalCanvasOriginTranslater{
		public void translate(AbstractMover<DoubleRealInterval> i_mover){
		}
	}
	private BoundsDrawer m_boundsDrawer = new BoundsDrawer();
	private class BoundsDrawer{
		public void draw(AbstractMover<DoubleRealInterval> i_mover){
		}
	}

	public StateSpaceType getStateSpaceType(){
		return m_sstype;
	}
	public void setStateSpaceType(StateSpaceType i_s){
		m_sstype = i_s;
 		setDrawWithBounds(false);
		ViewConfig2D vc = viewConfig();
		switch(i_s){
		case POSITION:
			m_sstypeName = "STATE [POSIITON]";
			m_LCOTranslater = new LocalCanvasOriginTranslater(){
				public void translate(AbstractMover<DoubleRealInterval> i_mover){
					PlanarSolarSystemApplet.this.translateLCO(i_mover.position());
				}
			};
			vc.setScale((float)(0.3f*this.getWidth()/AU));
			vc.setTranslation(0.0f, 0.0f);
			break;
		case VELOCITY:
			m_sstypeName = "STATE [VELOCITY]"; 
			m_LCOTranslater = new LocalCanvasOriginTranslater(){
				public void translate(AbstractMover<DoubleRealInterval> i_mover){
					PlanarSolarSystemApplet.this.translateLCO(i_mover.velocity());
				}
			};
			vc.setScale(1e-3f);
			vc.setTranslation(0.0f, 0.0f);
			break;
		case ACCEL:
			m_sstypeName = "STATE [ACCEL]"; 
			m_LCOTranslater = new LocalCanvasOriginTranslater(){
				public void translate(AbstractMover<DoubleRealInterval> i_mover){
					PlanarSolarSystemApplet.this.translateLCO(i_mover.accel());
				}
			};
			vc.setScale(1e+3f);
			vc.setTranslation(0.0f, 0.0f);
			break;
		default:
			m_LCOTranslater = new LocalCanvasOriginTranslater();
			break;
		}
	}
	private void translateLCO(DifferentialVectorFunction<DoubleRealInterval> i_pos){ //LocalCanvasOrigin
		DoubleRealInterval x = i_pos.get(0).getValue();
		DoubleRealInterval y = i_pos.get(1).getValue();
		translate((float)x.center(), (float)-y.center());
	}
	
	
	public void setup(){
		
		int windowWidth = 800;
		int windowHeight = 800;

		size(windowWidth, windowHeight);
		textFont(createFont("Lucida Console", 12));
				
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
		sun.addDrawer(new OvalDrawer(this, 20.0f, new Color(255,69, 0)));
		sun.addDrawer(new StringDrawer(this, "Sun", 22, 0, 1.0f, Color.white));
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
		earth.addDrawer(new OvalDrawer(this, 5.0f, new Color(0, 0, 205)));
		earth.addDrawer(new StringDrawer(this, "Earth", 7, 0, 1.0f, Color.white));
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
		moon.addDrawer(new OvalDrawer(this, 3.0f, Color.YELLOW));//new Color(192, 192, 192)));
		moon.addDrawer(new StringDrawer(this, "Moon", 5, 0, 1.0f, Color.white));
		m_simulator.addAnalyticalMover("Moon", moon);
		
		setStateSpaceType(StateSpaceType.POSITION);
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
			m_LCOTranslater.translate(mover);
			m_boundsDrawer.draw(mover);
			scale(1.0f/viewConfig().getScale());
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
		text(m_sstypeName +"    t : "+ String.format("%6.5f +- %6.5f", tcenter, trange), 50, baseline);
		text(getMouseGestureMode().getName(), getWidth()-150, baseline);
		
		
		
		for(int i = 0; i < m_steps; i++){
			m_simulator.nextStep();
		}
	}
	public boolean getDrawWithBounds(){
		return m_drawWithBounds;
	}
	public void setDrawWithBounds(boolean i_drawWithBounds){
		m_drawWithBounds = i_drawWithBounds;
		if(!m_drawWithBounds){
			m_boundsDrawer = new BoundsDrawer();
		}else{
			switch(getStateSpaceType()){
			case POSITION:
				m_boundsDrawer = new BoundsDrawer(){
					public void draw(AbstractMover<DoubleRealInterval> i_mover){
						noFill();
						drawRect(i_mover.position());
					}
				};
				break;
			case VELOCITY:
				m_boundsDrawer = new BoundsDrawer(){
					public void draw(AbstractMover<DoubleRealInterval> i_mover){
						noFill();
						drawRect(i_mover.velocity());
					}
				};
				break;
			case ACCEL:
				m_boundsDrawer = new BoundsDrawer(){
					public void draw(AbstractMover<DoubleRealInterval> i_mover){
						noFill();
						drawRect(i_mover.accel());
					}
				};
				break;
			default:
				m_boundsDrawer = new BoundsDrawer();
				break;
			}
		}
	}
	private void drawRect(DifferentialVectorFunction<DoubleRealInterval> i_pos){
		DoubleRealInterval x = i_pos.get(0).getValue();
		DoubleRealInterval y = i_pos.get(1).getValue();
		float w = (float)(x.width());
		float h = (float)(y.width());
		rect(-0.5f*w, -0.5f*h, w, h);
	}
	
	
	public void keyPressed(){
		switch(keyCode){
		case KeyEvent.VK_P:
			setStateSpaceType(StateSpaceType.POSITION);
			break;
		case KeyEvent.VK_V:
			setStateSpaceType(StateSpaceType.VELOCITY);
			break;
		case KeyEvent.VK_A:
			setStateSpaceType(StateSpaceType.ACCEL);
			break;
		case KeyEvent.VK_B:
			setDrawWithBounds(!getDrawWithBounds());
			break;
		default:
			super.keyPressed();
		}
	}
	
	public static void main(String[] args){

		PApplet applet = new PlanarSolarSystemApplet();
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

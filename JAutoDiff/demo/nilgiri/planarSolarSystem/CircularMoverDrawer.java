package nilgiri.planarSolarSystem;

import java.awt.Color;
import processing.core.PApplet;

public class CircularMoverDrawer implements Drawer {
	
	private PApplet m_applet;
	private Color m_color;
	private float m_radius;
	
	public CircularMoverDrawer(PApplet i_applet, float i_radius, Color i_color){
		m_applet = i_applet;
		m_color = i_color;
		m_radius = i_radius;
	}
	
	public void draw() {
		m_applet.fill(m_color.getRed(), m_color.getGreen(), m_color.getBlue());
		m_applet.ellipseMode(PApplet.RADIUS);
		m_applet.ellipse(0.0f, 0.0f, m_radius, m_radius);
		//getApplet().draw();
	}

}

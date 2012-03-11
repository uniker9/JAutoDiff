package nilgiri.planarSolarSystem;

import java.awt.Color;

import nilgiri.processing.AbstractViewerPApplet;
import processing.core.PApplet;

public class OvalDrawer implements Drawer {
	
	private AbstractViewerPApplet m_applet;
	private Color m_color;
	private float m_rx;
	private float m_ry;
	
	public OvalDrawer(AbstractViewerPApplet i_applet, float i_r, Color i_color){
		this(i_applet, i_r, i_r, i_color);
	}
	public OvalDrawer(AbstractViewerPApplet i_applet, float i_rx, float i_ry, Color i_color){
		m_applet = i_applet;
		m_color = i_color;
		m_rx = i_rx;
		m_ry = i_ry;
	}
	
	public void draw() {
//		m_applet.pushMatrix();
		//m_applet.scale(1.0f/m_applet.viewConfig().getScale());
		m_applet.fill(m_color.getRGB());
		m_applet.ellipseMode(PApplet.RADIUS);
		m_applet.ellipse(0.0f, 0.0f, m_rx, m_ry);
//		m_applet.popMatrix();
	}

}

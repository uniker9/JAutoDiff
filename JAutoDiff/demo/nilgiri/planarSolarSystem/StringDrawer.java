package nilgiri.planarSolarSystem;

import java.awt.Color;

import nilgiri.processing.AbstractViewerPApplet;
import nilgiri.processing.ViewConfig2D;


public class StringDrawer implements Drawer {
	
	private AbstractViewerPApplet m_applet;
	private Color m_color;
	private String m_str;
	private int m_x;
	private int m_y;
	private float m_size;
	
	public StringDrawer(AbstractViewerPApplet i_applet, String i_str, int i_x, int i_y, float i_size, Color i_color){
		m_applet = i_applet;
		m_str = i_str;
		m_x = i_x;
		m_y = i_y;
		m_size = i_size;
		m_color = i_color;
	}
	
	public void draw() {
		m_applet.pushMatrix();
		
		//m_applet.scale(m_size/m_applet.viewConfig().getScale());
		m_applet.scale(m_size);
		m_applet.translate(m_x, m_y);
		m_applet.fill(m_color.getRGB());
		m_applet.text(m_str, 0, 0);
		m_applet.popMatrix();
		
	}

}

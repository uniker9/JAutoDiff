package nilgiri.processing;


public abstract class MouseGestureMode {
	
	private String m_name;
	
	protected MouseGestureMode(String i_name){
		m_name = i_name;
	}
	public String getName(){
		return m_name;
	}
	
	abstract public void mouseDragged();
}

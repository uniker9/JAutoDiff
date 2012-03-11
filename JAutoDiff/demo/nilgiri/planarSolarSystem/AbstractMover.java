package nilgiri.planarSolarSystem;

import java.util.ArrayList;

import java.util.Iterator;
import nilgiri.math.RealNumber;
import nilgiri.math.autodiff.DifferentialFunction;
import nilgiri.math.autodiff.DifferentialVectorFunction;
import nilgiri.math.autodiff.Variable;

abstract public class AbstractMover<R extends RealNumber<R> >{

	protected Variable<R> m_t;
	protected ArrayList<Drawer> m_drawerList;
//	protected MoverDrawer<R> m_drawer; 
	
	AbstractMover(Variable<R> i_t){//, MoverDrawer<R> i_drawer){
		m_t = i_t;
//		m_drawer = i_drawer;
		m_drawerList = new ArrayList<Drawer>();
	}

	public Variable<R> t(){
		return m_t;
	}
	
	abstract DifferentialFunction<R> radius();
	abstract DifferentialVectorFunction<R> position();
	abstract DifferentialVectorFunction<R> velocity();
	abstract DifferentialVectorFunction<R> accel();
	

//	/*
	public void addDrawer(Drawer i_drawer){
		m_drawerList.add(i_drawer);
	}
	public void delDrawer(int i_index){
		m_drawerList.remove(i_index);
	}
	public void delDrawer(Drawer i_drawer){
		m_drawerList.remove(i_drawer);
	}
//	*/
	public void draw(){
//		m_drawer.draw(this);
//		/*
		Iterator<Drawer> itr = m_drawerList.iterator();
		while(itr.hasNext()){
			Drawer drawer = itr.next();
			drawer.draw();
		}
//		*/
	}
}

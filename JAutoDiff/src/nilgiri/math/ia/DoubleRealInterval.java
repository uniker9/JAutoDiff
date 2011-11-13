package nilgiri.math.ia;

import net.sourceforge.interval.ia_math.IAMath;
import net.sourceforge.interval.ia_math.RealInterval;
import nilgiri.math.RealNumber;

public class DoubleRealInterval implements RealNumber<DoubleRealInterval>, Cloneable {
	
	private RealInterval m_x; 
	

	public DoubleRealInterval() {
		m_x = new RealInterval();
	}
	public DoubleRealInterval(double i_v) {
		m_x = new RealInterval(i_v);
	}
	public DoubleRealInterval(double i_v1, double i_v2) {
		m_x = new RealInterval(i_v1, i_v2);
	}
	
	public RealInterval viewInternal() {
		return m_x;
	}

	protected DoubleRealInterval(RealInterval i_v){
		m_x = i_v;
	}
	
	public String toString() {
		return "[" + String.valueOf(m_x.lo()) +", "+ String.valueOf(m_x.hi())  +"]";
	}

	public Object clone() {
		return new DoubleRealInterval((RealInterval)m_x.clone());
	}

	public DoubleRealInterval inverse() {
		return new DoubleRealInterval(IAMath.odiv(IA_RealIntervalFactory.instance().one(), this.m_x));
	}

	public DoubleRealInterval negate() {
		return new DoubleRealInterval(IAMath.sub(IA_RealIntervalFactory.instance().zero(), this.m_x));
	}

	// Operators for DoubleReal

	public DoubleRealInterval plus(DoubleRealInterval i_rd) {
		return new DoubleRealInterval(IAMath.add(this.m_x, i_rd.m_x));
	}

	public DoubleRealInterval minus(DoubleRealInterval i_rd) {
		return new DoubleRealInterval(IAMath.sub(this.m_x, i_rd.m_x));
	}

	public DoubleRealInterval mul(DoubleRealInterval i_rd) {
		return new DoubleRealInterval(IAMath.mul(this.m_x, i_rd.m_x));
	}

	public DoubleRealInterval div(DoubleRealInterval i_rd) {
		return new DoubleRealInterval(IAMath.odiv(this.m_x, i_rd.m_x));
	}

	// Operators for double
	public DoubleRealInterval plus(double i_v) {
		return new DoubleRealInterval(IAMath.add(m_x, new RealInterval(i_v)));
	}

	public DoubleRealInterval minus(double i_v) {
		return new DoubleRealInterval(IAMath.sub(m_x, new RealInterval(i_v)));
	}

	public DoubleRealInterval prod(double i_v) {
		return new DoubleRealInterval(IAMath.mul(m_x, new RealInterval(i_v)));
	}

	public DoubleRealInterval div(double i_v) {
		return new DoubleRealInterval(IAMath.odiv(m_x, new RealInterval(i_v)));
	}

	public DoubleRealInterval pow(double i_v) {
		return new DoubleRealInterval(IAMath.power(m_x, new RealInterval(i_v)));
	}

	public DoubleRealInterval pow(int i_n) {
		return new DoubleRealInterval(IAMath.power(m_x, new RealInterval(i_n)));
	}

	public DoubleRealInterval mul(long i_n) {
		return new DoubleRealInterval(IAMath.mul(m_x, new RealInterval(i_n)));
	}

}

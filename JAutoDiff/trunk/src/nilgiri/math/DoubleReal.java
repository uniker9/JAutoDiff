package nilgiri.math;

public class DoubleReal implements RealNumber<DoubleReal> {

	private double m_x;

	public DoubleReal() {
		this(0.0);
	}

	public DoubleReal(double i_v) {
		m_x = i_v;
	}

	public DoubleReal(DoubleReal i_rd) {
		this(i_rd.m_x);
	}

	public void assign(DoubleReal i_rd) {
		m_x = i_rd.m_x;
	}

	public double value() {
		return m_x;
	}

	public double modulus() {
		return Math.abs(m_x);
	}

	public String toString() {
		return String.valueOf(m_x);
	}

	public DoubleReal clone() {
		return new DoubleReal(m_x);
	}

	public DoubleReal inverse() {
		return new DoubleReal(1.0 / m_x);
	}

	public DoubleReal negate() {
		return new DoubleReal(-m_x);
	}

	// Operators for DoubleReal

	public DoubleReal plus(DoubleReal i_rd) {
		return new DoubleReal(m_x + i_rd.m_x);
	}

	public DoubleReal minus(DoubleReal i_rd) {
		return new DoubleReal(m_x - i_rd.m_x);
	}

	public DoubleReal multi(DoubleReal i_rd) {
		return new DoubleReal(m_x * i_rd.m_x);
	}

	public DoubleReal divide(DoubleReal i_rd) {
		return new DoubleReal(m_x / i_rd.m_x);
	}

	// Operators for double

	public DoubleReal plus(double i_v) {
		return new DoubleReal(m_x + i_v);
	}

	public DoubleReal minus(double i_v) {
		return new DoubleReal(m_x - i_v);
	}

	public DoubleReal prod(double i_v) {
		return new DoubleReal(m_x * i_v);
	}

	public DoubleReal div(double i_v) {
		return new DoubleReal(m_x / i_v);
	}

	public DoubleReal pow(double i_v) {
		return new DoubleReal(Math.pow(m_x, i_v));
	}

	public DoubleReal pow(int i_n) {
		return new DoubleReal(Math.pow(m_x, i_n));
	}

	public DoubleReal multi(long i_n) {
		return new DoubleReal(m_x * i_n);
	}

}

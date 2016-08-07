package util;

public class DieChooserFixed
implements DieChooser {

	private int value;
	public void setValue(int x){value=x;}
	
	@Override
	public void init() {
	}

	@Override
	public Die getDie() {
		Die r=new Die();
		r.setHigh(value);
		r.setLow(value);
		r.init();
		return r;
	}
}

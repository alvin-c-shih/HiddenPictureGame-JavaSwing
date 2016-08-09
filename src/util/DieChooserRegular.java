package util;

public class DieChooserRegular
implements DieChooser {

	private int high=10;
	public void setHigh(int x){high=x;}

	private int low=0;
	public void setLow(int x){low=x;}

	@Override
	public void init() {
	}

	@Override
	public Die getDie() {
		Die r=new Die();
		r.setHigh(high);
		r.setLow(low);
		r.init();
		return r;
	}
}

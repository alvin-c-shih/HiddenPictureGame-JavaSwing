package util;

public class DieChooserProgressive
implements DieChooser {

	private int high=5;
	public void setHigh(int x){high=x;}

	private int low=3;
	public void setLow(int x){low=x;}

	private int increment=1;
	public void setIncrement(int x){increment=x;}

	private int current;
	public void setCurrent(int x){current=x;}
	
	@Override
	public void init() {
	}

	@Override
	public Die getDie() {
		Die r=new Die();
		r.setHigh(current);
		r.setLow(current);
		r.init();
		current+=increment;
		if(current>high){
			current=low;
		}
		return r;
	}
}

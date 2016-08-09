package util;

import java.util.Calendar;
import java.util.Random;

public class Die {
	private int high=10;
	protected void setHigh(int x){high=x;}

	private int low=0;
	protected void setLow(int x){low=x;}
	
	// Seeding on millisecond will not be sufficient to distinguish random
	//  number generators.  Add a counter for that.
	protected static int dieCount = 0;
	
	{
		++dieCount;
	}
	
	protected Random random=new Random(dieCount + Calendar.getInstance().getTimeInMillis());

	public void init(){
		//TODO: validate low<=high
	}
	
	public int nextInt(){
		return random.nextInt(high-low+1)+low;
	}
}

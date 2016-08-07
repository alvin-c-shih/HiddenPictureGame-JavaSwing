package game_logic;

import java.util.List;


public interface GameI {
	public int getRows();
	public void setRows(int x);
	public int getCols();
	public void setCols(int x);
	/**
	 * One-time initialization of the GameGenerator.
	 * Useful if a subclass has to override property values
	 *  from the superclass.
	 */
	public void init();
	
	/**
	 * Generate n QuestionAnswer objects.
	 * @param n
	 * @return
	 */
	public List<QuestionAnswer> generate(int n);
}

package game_logic;

public abstract class GameBase 
implements GameI
{
	private int rows=4;
	public int getRows(){return rows;}
	public void setRows(int x){rows=x;}
	
    private int cols=4;
    public int getCols(){return cols;}
    public void setCols(int x){cols=x;}
}

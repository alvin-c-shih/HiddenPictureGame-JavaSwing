package game_logic;

import java.util.List;

public class GameCycler
extends GameBase
{
	private GameI[] games;
	public void setGames(GameI[] x){games=x;}
	
	private int gameNum=0;
	
	public void init(){
		for(GameI game:games){
			game.setRows(super.getRows());
			game.setCols(super.getCols());
			game.init();
		}
	}
	
	public List<QuestionAnswer> generate(int n){
		GameI game=games[gameNum];
		++gameNum;
		if(gameNum>=games.length){
			gameNum=0;
		}
		return game.generate(n);
	}

}

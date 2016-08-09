import game_logic.GameBase;
import game_logic.GameI;
import game_logic.QuestionAnswer;

import java.util.ArrayList;
import java.util.List;

/* Questions from a list of different games. */
class ComposerGame
extends GameBase
{
	
	private GameI[] games;
	public void setGames(GameI[] x){games=x;}
	    
	@Override
	public void init(){
	}
	
	@Override
	public List<QuestionAnswer> generate(int n){		
		List<QuestionAnswer> r=new ArrayList<QuestionAnswer>(n);
		for(int i=0;i<n;++i){
			int gameNum = i % games.length;
			GameI game = games[gameNum];
			r.addAll(game.generate(1));
		}
		return r;
	}
}
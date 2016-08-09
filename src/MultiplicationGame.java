import game_logic.GameBase;
import game_logic.QuestionAnswer;

import java.util.ArrayList;
import java.util.List;

import util.Die;
import util.DieChooser;
import util.StringUtil;

// multiplier + multiplicand = product
class MultiplicationGame
extends GameBase
{
	private DieChooser multiplierDieChooser;
	public void setMultiplierDieChooser(DieChooser x){multiplierDieChooser=x;}
	
	private DieChooser multiplicandDieChooser;
	public void setMultiplicandDieChooser(DieChooser x){multiplicandDieChooser=x;}
	
    final String questionTemplate=
    	"<html>"
    	+"<table cellspacing='0' cellpadding='0'>"
		+"<tr><td align='right'>%s</td></tr>"
		+"<tr><td align='right'><u>x %s</u></td></tr>"
		+"</table>"
		+"</html>";

	Die multiplierDie;
	Die multiplicandDie;
	@Override
	public void init(){
		multiplierDie=multiplierDieChooser.getDie();
		multiplicandDie=multiplicandDieChooser.getDie();
	}
	
	@Override
	public List<QuestionAnswer> generate(int n){
		
		List<QuestionAnswer> r=new ArrayList<QuestionAnswer>(n);
		for(int i=0;i<n;++i){
			final int multiplier=multiplierDie.nextInt();
			final int multiplicand=multiplicandDie.nextInt();
			final int product=multiplier*multiplicand;
			
			final String multiplierStr=Integer.toString(multiplier);
			final String multiplicandStr=Integer.toString(multiplicand);
			final String productStr=Integer.toString(product);
			
			final int numDigits=Math.max(multiplierStr.length(), multiplicandStr.length());

			final String questionHtml=String.format(questionTemplate,
					StringUtil.pad(multiplierStr,numDigits),
					StringUtil.pad(multiplicandStr,numDigits));
			
			final String correct = "Correct!  "+ multiplier + " x " + multiplicand + " = " + product;
			
			String incorrect = "Take your time and multiply carefully.";
			if(multiplier==0 || multiplicand==0){
				incorrect="Hint: anything times 0 equals 0.";
			}else if(multiplier==1 || multiplicand==1){
				incorrect="Hint: anything times 1 equals itself.";
			}

			r.add(new QuestionAnswer(questionHtml,productStr,correct,incorrect));
		}
		return r;
	}
}
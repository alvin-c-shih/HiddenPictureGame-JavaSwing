import game_logic.GameBase;
import game_logic.QuestionAnswer;

import java.util.ArrayList;
import java.util.List;

import util.Die;
import util.DieChooser;
import util.StringUtil;

// dividend / divisor = quotient
class DivisionWithoutRemainderGame
extends GameBase
{
	private DieChooser divisorDieChooser;
	public void setdivisorDieChooser(DieChooser x){divisorDieChooser=x;}
	
	private DieChooser quotientDieChooser;
	public void setquotientDieChooser(DieChooser x){quotientDieChooser=x;}
	
    final String questionTemplate=
    	"<html>"
    	+"<table cellspacing='0' cellpadding='0'>"
		+"<tr><td align='right'>%s</td></tr>"
		+"<tr><td align='right'><u>&divide;ac %s</u></td></tr>"
		+"</table>"
		+"</html>";

	Die divisorDie;
	Die quotientDie;

    @Override
	public void init(){
		divisorDie=divisorDieChooser.getDie();
		quotientDie=quotientDieChooser.getDie();    	
    }

	@Override
	public List<QuestionAnswer> generate(int n){
		List<QuestionAnswer> r=new ArrayList<QuestionAnswer>(n);
		for(int i=0;i<n;++i){
			final int quotient=quotientDie.nextInt();
			final int divisor=divisorDie.nextInt();
			final int dividend=divisor*quotient;
			
			final String dividendStr=Integer.toString(dividend);
			final String divisorStr=Integer.toString(divisor);
			final String quotientStr=Integer.toString(quotient);
			
			int numDigits=Math.max(dividendStr.length(), divisorStr.length());

			String questionHtml=String.format(questionTemplate,
					StringUtil.pad(dividendStr,numDigits),
					StringUtil.pad(divisorStr,numDigits));
			
			String correct = "Correct!  "+ dividend + " / " + divisor + " = " + quotient;

			String incorrect = "Take your time and divide carefully.";
			if(divisor==1){
				incorrect="Hint: anything divided by 1 is the same number.";
			}
			
			r.add(new QuestionAnswer(questionHtml,quotientStr,correct,incorrect));
		}
		return r;
	}
}
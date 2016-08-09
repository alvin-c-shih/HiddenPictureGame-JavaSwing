import game_logic.GameBase;
import game_logic.QuestionAnswer;

import java.util.ArrayList;
import java.util.List;

import util.Die;
import util.DieChooser;
import util.StringUtil;

// dividend + divisor = quotient
class DivisionGame
extends GameBase
{
	private DieChooser dividendDieChooser;
	public void setdividendDieChooser(DieChooser x){dividendDieChooser=x;}
	
	private DieChooser divisorDieChooser;
	public void setdivisorDieChooser(DieChooser x){divisorDieChooser=x;}
	
    final String questionTemplate=
    	"<html>"
    	+"<table cellspacing='0' cellpadding='0'>"
		+"<tr><td align='right'>%s</td></tr>"
		+"<tr><td align='right'><u>&divide; %s</u></td></tr>"
		+"</table>"
		+"</html>";

	Die dividendDie;
	Die divisorDie;
	@Override
	public void init(){
		dividendDie=dividendDieChooser.getDie();
		divisorDie=divisorDieChooser.getDie();
	}
	
	@Override
	public List<QuestionAnswer> generate(int n){
		
		List<QuestionAnswer> r=new ArrayList<QuestionAnswer>(n);
		for(int i=0;i<n;++i){
			final int dividend=dividendDie.nextInt();
			final int divisor=divisorDie.nextInt();
			final int quotient=dividend / divisor;
			final int remainder = dividend % divisor;
			
			final String dividendStr=Integer.toString(dividend);
			final String divisorStr=Integer.toString(divisor);
			final String quotientStr=Integer.toString(quotient);
			final String remainderStr= (remainder==0) ? "" : " R " + Integer.toString(remainder);
			final String answerStr= quotientStr + remainderStr;
			
			final int numDigits=Math.max(dividendStr.length(), divisorStr.length());

			final String questionHtml=String.format(questionTemplate,
					StringUtil.pad(dividendStr,numDigits),
					StringUtil.pad(divisorStr,numDigits));
			
			final String correct = "Correct!  "+ dividendStr + " divided by " + divisorStr + " = " + answerStr;
			
			String incorrect = "Take your time and divide carefully.";
			if(divisor==1){
				incorrect="Hint: divided by 1 equals itself.";
			}

			r.add(new QuestionAnswer(questionHtml,answerStr,correct,incorrect));
		}
		return r;
	}
}
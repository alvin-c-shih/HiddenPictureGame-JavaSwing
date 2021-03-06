import game_logic.GameBase;
import game_logic.QuestionAnswer;

import java.util.ArrayList;
import java.util.List;

import util.Die;
import util.DieChooser;
import util.StringUtil;

// minuend - subtrahend = difference
class SubtractionGame
extends GameBase
{
	private DieChooser subtrahendDieChooser;
	public void setSubtrahendDieChooser(DieChooser x){subtrahendDieChooser=x;}
	
	private DieChooser differenceDieChooser;
	public void setDifferenceDieChooser(DieChooser x){differenceDieChooser=x;}
	
    final String questionTemplate=
    	"<html>"
    	+"<table cellspacing='0' cellpadding='0'>"
		+"<tr><td align='right'>%s</td></tr>"
		+"<tr><td align='right'><u>- %s</u></td></tr>"
		+"</table>"
		+"</html>";

	Die subtrahendDie;
	Die differenceDie;

    @Override
	public void init(){
		subtrahendDie=subtrahendDieChooser.getDie();
		differenceDie=differenceDieChooser.getDie();    	
    }

	@Override
	public List<QuestionAnswer> generate(int n){
		List<QuestionAnswer> r=new ArrayList<QuestionAnswer>(n);
		for(int i=0;i<n;++i){
			final int difference=differenceDie.nextInt();
			final int subtrahend=subtrahendDie.nextInt();
			final int minuend=subtrahend+difference;
			
			final String minuendStr=Integer.toString(minuend);
			final String subtrahendStr=Integer.toString(subtrahend);
			final String differenceStr=Integer.toString(difference);
			
			int numDigits=Math.max(minuendStr.length(), subtrahendStr.length());

			String questionHtml=String.format(questionTemplate,
					StringUtil.pad(minuendStr,numDigits),
					StringUtil.pad(subtrahendStr,numDigits));
			
			String correct = "Correct!  "+ minuend + " - " + subtrahend + " = " + difference;

			String incorrect = "Take your time and subtract carefully.";
			if(subtrahend==0){
				incorrect="Hint: anything minus 0 is the same number.";
			}else if(subtrahend==9 && minuend>=10){
				incorrect = "Hint: what's "+ minuend + " - 10 + 1 ?";
			}else if(subtrahend==8 && minuend>=10){
				incorrect = "Hint: what's "+ minuend + " - 10 + 2 ?";
			}else if(subtrahend>0){
				incorrect = "Hint: what's "+ minuend + " - " + (subtrahend-1) + " - 1 ?";
			}
			
			r.add(new QuestionAnswer(questionHtml,differenceStr,correct,incorrect));
		}
		return r;
	}
}
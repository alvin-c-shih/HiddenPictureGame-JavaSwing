import game_logic.GameBase;
import game_logic.QuestionAnswer;

import java.util.ArrayList;
import java.util.List;

import util.Die;
import util.DieChooser;
import util.StringUtil;

// augend + addend = sum
class AdditionGame
extends GameBase
{
	private DieChooser augendDieChooser;
	public void setAugendDieChooser(DieChooser x){augendDieChooser=x;}
	
	private DieChooser addendDieChooser;
	public void setAddendDieChooser(DieChooser x){addendDieChooser=x;}
	
    final String questionTemplate=
    	"<html>"
    	+"<table cellspacing='0' cellpadding='0'>"
		+"<tr><td align='right'>%s</td></tr>"
		+"<tr><td align='right'><u>+ %s</u></td></tr>"
		+"</table>"
		+"</html>";

	Die augendDie;
	Die addendDie;
	@Override
	public void init(){
		augendDie=augendDieChooser.getDie();
		addendDie=addendDieChooser.getDie();
	}
	
	@Override
	public List<QuestionAnswer> generate(int n){
		
		List<QuestionAnswer> r=new ArrayList<QuestionAnswer>(n);
		for(int i=0;i<n;++i){
			final int augend=augendDie.nextInt();
			final int addend=addendDie.nextInt();
			final int sum=addend+augend;
			
			final String augendStr=Integer.toString(augend);
			final String addendStr=Integer.toString(addend);
			final String sumStr=Integer.toString(sum);
			
			final int numDigits=Math.max(augendStr.length(), addendStr.length());

			final String questionHtml=String.format(questionTemplate,
					StringUtil.pad(augendStr,numDigits),
					StringUtil.pad(addendStr,numDigits));
			
			final String correct = "Correct!  "+ augend + " + " + addend + " = " + sum;
			
			String incorrect = "Take your time and add carefully.";
			if(addend==0){
				incorrect="Hint: anything plus 0 is the same number.";
			}else if(addend==9){
				incorrect = "Hint: what's "+ augend + " + 10 - 1 ?";
			}else if(addend==8){
				incorrect = "Hint: what's "+ augend + " + 10 - 2 ?";
			}else if(addend>0){
				incorrect = "Hint: what's "+ augend + " + " + (addend-1) + " + 1 ?";
			}

			r.add(new QuestionAnswer(questionHtml,sumStr,correct,incorrect));
		}
		return r;
	}
}
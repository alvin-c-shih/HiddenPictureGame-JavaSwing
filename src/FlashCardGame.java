import game_logic.GameBase;
import game_logic.QuestionAnswer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.Die;
import util.StringUtil;

// augend + addend = sum
class FlashCardGame
extends GameBase
{
	private String name;
	public void setName(String x){name=x;}
	// exprSpec x+y;x-y;...
	private String exprSpec;
	public void setExprSpec(String x){exprSpec=x;}
	
    final String questionTemplate=
    	"<html>"
    	+"<table cellspacing='0' cellpadding='0'>"
		+"<tr><td align='right'>%s</td></tr>"
		+"<tr><td align='right'><u>%s%s</u></td></tr>"
		+"</table>"
		+"</html>";
	
    private Die die;
    
    static class Expr
    {
    	public Expr(int x,String op,int y)
    	{
    		this.x=x;
    		this.op=op;
    		this.y=y;
    	}
    	public int x;
    	public String op;
    	public int y;
    }
    
    private Expr[] exprs;
    
	@Override
	public void init(){
		String[] exprStrs = exprSpec.replaceAll("\\s","").split(";");
		exprs = new Expr[exprStrs.length];
		
		Pattern p = Pattern.compile("^([0-9]+)([-+])([0-9]+)$");
		
		int i=0;
		for(String exprStr : exprStrs){
			Matcher m = p.matcher(exprStr);
			if(!m.matches()){
				throw new RuntimeException("Could not match exprStr: '"+exprStr+"'");
			}
			exprs[i] = new Expr(Integer.parseInt(m.group(1)),
								m.group(2),
								Integer.parseInt(m.group(3)));
			++i;
		}
		
		util.DieChooserRegular dieChooser = new util.DieChooserRegular();
		dieChooser.setLow(0);
		dieChooser.setHigh(exprs.length-1);
		die = dieChooser.getDie();
	}
	
	@Override
	public List<QuestionAnswer> generate(int n){
		
		List<QuestionAnswer> r=new ArrayList<QuestionAnswer>(n);
		for(int i=0;i<n;++i){
			final Expr expr = exprs[die.nextInt()];
			
			int ans;
			if (expr.op.equals("+")){
				ans=expr.x+expr.y;
			}else if (expr.op.equals("-")){
				ans=expr.x-expr.y;		
			}else{
				throw new RuntimeException("unsupported operation: '"+expr.op+"'");
			}
			final String xStr=Integer.toString(expr.x);
			final String yStr=Integer.toString(expr.y);
			final String ansStr=Integer.toString(ans);
			
			final int numDigits=Math.max(xStr.length(), yStr.length());

			final String questionHtml=String.format(questionTemplate,
					StringUtil.pad(xStr,numDigits),
					expr.op,
					StringUtil.pad(yStr,numDigits));
			
			final String correct = "Correct!  "
					+ expr.x + " " + expr.op + " " + expr.y + " = " + ans;
			
			String incorrect = "Remember the rule for: "+name;
			r.add(new QuestionAnswer(questionHtml,ansStr,correct,incorrect));
		}
		return r;
	}
}
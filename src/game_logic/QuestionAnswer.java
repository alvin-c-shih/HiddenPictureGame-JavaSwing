package game_logic;
/**
 * Hold questions and answers.
 * Also holds message to confirm correct answer, and
 *  reminders for things to watch if the answer was incorrect.
 * The strings can be plain text or HTML as supported by
 *  JLabel and JButton.
 */
public class QuestionAnswer{
	private String question;
	public String getQuestion(){return question;}
	
	private String answer;
	public String getAnswer(){return answer;}
	
	private String correct;
	public String getCorrect(){return correct;}
	
	private String incorrect;
	public String getIncorrect(){return incorrect;}

	public QuestionAnswer(String question,String answer,
			String correct,String incorrect){
		this.question=question;
		this.answer=answer;
		this.correct=correct;
		this.incorrect=incorrect;
	}
}
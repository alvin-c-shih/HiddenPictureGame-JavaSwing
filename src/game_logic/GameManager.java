package game_logic;
import gui.BackgroundPanel;
import gui.GridPanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import util.ExceptionUtil;
import util.ImageUtil;

public class GameManager
implements ActionListener
{
	protected JFrame frame; // required for dialog boxes
	protected BackgroundPanel backgroundPanel;
	protected GridPanel gridPanel;
	protected JLabel questionLabel;
	protected JLabel messageLabel;
	
	protected GameI questionGenerator;
	
	protected Random random=new Random(Calendar.getInstance().getTimeInMillis());
	
	protected List<QuestionAnswer> questionAnswers;
	protected QuestionAnswer currentQuestion;

	private Font questionFont = new Font("Courier", Font.BOLD, 26);
	
	public GameManager(JFrame frame,
			BackgroundPanel backgroundPanel,
			GridPanel gridPanel,
			JLabel questionLabel,
			JLabel messageLabel,
			GameI questionGenerator){
		this.frame=frame;
		this.backgroundPanel=backgroundPanel;
		this.gridPanel=gridPanel;
		this.questionLabel=questionLabel;
		this.messageLabel=messageLabel;
		this.questionGenerator=questionGenerator;

		gridPanel.setRows(questionGenerator.getRows());
		gridPanel.setCols(questionGenerator.getCols());
    	gridPanel.init();
		gridPanel.setActionListener(this);
        questionLabel.setFont(questionFont);
        questionGenerator.init();
        newGame();
	}

	/**
	 * Things to do at the start of each new game.
	 * @throws MalformedURLException
	 */
	public void newGame(){
		messageLabel.setText("");
		try{
			backgroundPanel.setBgIcon(ImageUtil.getImageIcon());
		}catch(Exception ex){
			ExceptionUtil.fatalExceptionDialog(frame,ex);
		}
		questionAnswers=questionGenerator.generate(gridPanel.getButtonCount());
		updateButtonAnswers();
		gridPanel.showButtons();
        nextQuestion();
	}

	protected void updateButtonAnswers(){
		final int n=gridPanel.getButtonCount();
		for(int i=0;i<n;++i){
			JButton b=gridPanel.getButton(i);
			b.setText(questionAnswers.get(i).getAnswer());
		}
	}

	void nextQuestion() {
		int i=random.nextInt(questionAnswers.size());
		currentQuestion=questionAnswers.get(i);
        questionLabel.setText(currentQuestion.getQuestion());
	}
		
	protected int newGameWait=5000;  // how long to sleep
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source=(JButton)e.getSource();
		String buttonText=source.getText();
		if(buttonText.equals(currentQuestion.getAnswer())){
			// correct
			source.setVisible(false);
			
			// Although removed, currentQuestion still valid until
			// we call setupQuestion().
			questionAnswers.remove(currentQuestion);
			
			if(questionAnswers.isEmpty()){
				// done
				// Not displaying the correct answer since
				//  it may be a hassle to combine HTML with the
				//  message below.
				questionLabel.setText("");
				String message="You completed the picture!  Good job!";
				messageLabel.setText(message);
				Timer timer=new Timer(newGameWait, new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						newGame();
					}
				});
				timer.setRepeats(false);
				timer.start();
			}else{
				messageLabel.setText(currentQuestion.getCorrect());
				nextQuestion();
			}
		}else{
			Object[] options={"Go to next question."};
			// incorrect
			//messageLabel.setText(currentQuestion.getIncorrect());
			JOptionPane.showOptionDialog(frame,
					currentQuestion.getIncorrect(),
					"Incorrect.",
					JOptionPane.YES_OPTION,
					JOptionPane.ERROR_MESSAGE,
					null,
					options,
					null);
			nextQuestion();
		}
	}
}
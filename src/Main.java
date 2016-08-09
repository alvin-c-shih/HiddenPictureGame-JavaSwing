import game_logic.GameI;
import game_logic.GameManager;
import gui.BackgroundPanel;
import gui.GridPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import util.ExceptionUtil;

//http://www.java-tips.org/java-se-tips/javax.swing/wrap-a-swing-jcomponent-in-a-background-image.html

public class Main {    
    
	// TODO: Make gridPanel button font scale with size of buttons
	
    public static void main(String[] args) { 	
    	ClassPathXmlApplicationContext appContext
    		= new ClassPathXmlApplicationContext(new String[]{"beans.xml"});

    	GameI game=(GameI)appContext.getBean("gameCycler");
    	
        JFrame frame = new JFrame("Hidden Picture Game");

    // Create some GUI
        JPanel foregroundPanel = new JPanel(new BorderLayout(10, 10));
        foregroundPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        foregroundPanel.setOpaque(false);
        
        foregroundPanel.add(new JLabel("Click the answers to the math problems to reveal the picture."), BorderLayout.NORTH);
    	GridPanel gridPanel = new GridPanel();
    	BackgroundPanel backgroundPanel = new BackgroundPanel(gridPanel);
        {
        	foregroundPanel.add(backgroundPanel,BorderLayout.CENTER);
        }
        JLabel questionLabel=new JLabel();
        {
	        //BorderLayout ignores min/max size and only checks preferred.
	        //http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4345920
	        questionLabel.setPreferredSize(new Dimension(50, 50));
	        questionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	        foregroundPanel.add(questionLabel, BorderLayout.WEST);
        }
        JLabel messageLabel=new JLabel();
        {
	        //BorderLayout ignores min/max size and only checks preferred.
	        //http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4345920
	        messageLabel.setPreferredSize(new Dimension(200, 50));
	        foregroundPanel.add(messageLabel, BorderLayout.SOUTH);
        }
        
        frame.setContentPane(foregroundPanel);
        // pack before MathGame.init() so background image can know
        //  what size it is.
        frame.pack();

        try{
        	//Don't need a variable since it hooks into Swing
        	// framework.
            new GameManager(frame,backgroundPanel,gridPanel,questionLabel,messageLabel,
            		game);
        }catch(Exception ex){
        	ExceptionUtil.fatalExceptionDialog(frame, ex);
        }
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}//class Main
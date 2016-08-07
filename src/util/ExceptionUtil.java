package util;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class ExceptionUtil {
	public static void fatalExceptionDialog(JFrame frame,Exception ex){
		//custom title, error icon
		JOptionPane.showMessageDialog(frame,
		    ex.getMessage(),
		    "Fatal Exception",
		    JOptionPane.ERROR_MESSAGE);
		ex.printStackTrace();
		System.exit(1);
	}
}

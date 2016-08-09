package gui;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

// create wrapper JPanel

public class BackgroundPanel 
extends JPanel
implements ComponentListener{
    // Set up constraints so that the user supplied component and the
    // background image label overlap and resize identically
    GridBagConstraints gbConstraints=new GridBagConstraints();
    {
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 0;
        gbConstraints.weightx = 1.0;
        gbConstraints.weighty = 1.0;
        gbConstraints.fill = GridBagConstraints.BOTH;
        gbConstraints.anchor = GridBagConstraints.NORTHWEST;
    }
	/**
	 * Wraps a Swing JComponent in a background image. Simply invokes the overloded
	 * variant with Top/Leading alignment for background image.
	 *
	 * @param component - to wrap in the a background image
	 * @param backgroundIcon - the background image (Icon)
	 * @return the wrapping JPanel
	 */
	public BackgroundPanel(JComponent component) {
	    this(component,
	            SwingConstants.CENTER,
	            SwingConstants.CENTER);
	}

	private ImageIcon bgIcon0=null; // original image
	public void setBgIcon(ImageIcon x){
		bgIcon0=x;
		// Fire the handler that would scale the image to fit the background.
		componentResized(null);
	}
	
	ImageIcon bgIcon1=new ImageIcon(); // scaled image

	/**
	 * Wraps a Swing JComponent in a background image. The vertical and horizontal
	 * alignment of background image can be specified using the alignment
	 * contants from JLabel.
	 *
	 * @param component - to wrap in the a background image
	 * @param bgIcon - the background image (Icon)
	 * @param verticalAlignment - vertical alignment. See contants in JLabel.
	 * @param horizontalAlignment - horizontal alignment. See contants in JLabel.
	 * @return the wrapping JPanel
	 */
	public BackgroundPanel(JComponent component,
	        int verticalAlignment,
	        int horizontalAlignment) {
	    super(new GridBagLayout());
	    
	    // make the passed in swing component transparent
	    component.setOpaque(false);
	    
	    // add the passed in swing component first to ensure that it is in front
	    add(component,gbConstraints);
	    
	    // create a label to paint the background image
	    JLabel backgroundImage = new JLabel(bgIcon1);
	    
	    // set minimum and preferred sizes so that the size of the image
	    // does not affect the layout size
	    backgroundImage.setPreferredSize(new Dimension(1,1));
	    backgroundImage.setMinimumSize(new Dimension(1,1));
	    
	    // align the image as specified.
	    backgroundImage.setVerticalAlignment(verticalAlignment);
	    backgroundImage.setHorizontalAlignment(horizontalAlignment);
	    
	    // add the background label
	    add(backgroundImage, gbConstraints);

	    addComponentListener(this);
	}
	@Override
	public void componentHidden(ComponentEvent arg0){}
	
	@Override
	public void componentMoved(ComponentEvent arg0){}
	
	@Override
	public void componentResized(ComponentEvent arg0) {
		if(null==bgIcon0){return;}
		Image image0=bgIcon0.getImage();
		
		int w=image0.getWidth(bgIcon0.getImageObserver());
	    int h=image0.getHeight(bgIcon0.getImageObserver());
		    
	    double aspect=w/(double)h;
	
	    final int w1=getWidth();
	    final int h1=getHeight();
	    
	    w=(int)(h1*aspect);
	    h=h1;
	
	    if(w>w1 || h>h1){
	    	w=w1;
	    	h=(int)(w1/aspect);
	    }
	    bgIcon1.setImage(image0.getScaledInstance(w, h, Image.SCALE_DEFAULT));
	}
	
	@Override
	public void componentShown(ComponentEvent arg0){}
}

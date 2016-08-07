package gui;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class GridPanel
extends JPanel
implements ActionListener
{
	private int rows=4;
	public int getRows(){return rows;}
	public void setRows(int x){rows=x;}
	
    private int cols=4;
    public int getCols(){return cols;}
    public void setCols(int x){cols=x;}
    
    private int hGap=3;
    public int getHGap(){return hGap;}
    public void setHGap(int x){hGap=x;}
    
    private int vGap=3;
    public int getVGap(){return vGap;}
    public void setVGap(int x){vGap=x;}

    private ActionListener actionListener=null;
    public void setActionListener(ActionListener x){actionListener=x;}
    
    public JButton getButton(int i){return (JButton)getComponent(i);}
    public int getButtonCount(){return getComponentCount();}
    
	public GridPanel() {
		setPreferredSize(new Dimension(640,480));
	}

	private Font buttonFont = new Font("Ariel", Font.PLAIN, 22);
	
	public void init()
	{
		setLayout(new GridLayout(rows,cols,hGap,vGap));
		removeAll(); // clear out buttons in case of re-init
		for(int r=0;r<rows;++r){
	    	for(int c=0;c<cols;++c){
	    		JButton b=new JButton();
	    		b.setFocusable(false); // no default clicking using Enter
	    		b.setFont(buttonFont);
	    		b.addActionListener(this);  // route all button presses through here
	    		add(b);
	    	}
	    }
	}
	@Override
	public void actionPerformed(ActionEvent x) {
		if(null!=actionListener){
			actionListener.actionPerformed(x);
		}
	}
	
	public void showButtons() {
    	final int n=getButtonCount();
    	for(int i=0;i<n;++i){
    		JButton b=getButton(i);
    		b.setVisible(true);
    	}
	}
}

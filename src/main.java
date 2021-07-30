/*
 * 
 * Author Jared Wal
 */


import java.io.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.image.BufferedImage;
public class main extends JFrame implements MouseListener, KeyListener{

	    public MapGUI panel;
	    JLabel label;
	    
	    int windowWidth = 1594;
	    int windowHeight = 807;
		
	
	    public static BufferedImage img;

		public static void main(String args[]) throws IOException, InterruptedException {
			main frame = new main();
			while(true){
				
	            frame.getPanel().repaint();
	        
	            //The program waits a while before rerendering
	            Thread.sleep(1000);
	            
	        }
			
		}
	    	
	    
		
		
	    public main() {
	    	
	    	this.setSize(windowWidth, windowHeight);

			
	        this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.getContentPane().setBackground(Color.white);

			label = new JLabel();
			label.setBounds(0,0,windowWidth,windowHeight);
			label.setBackground(Color.blue);
			label.setOpaque(true);
			label.addMouseListener(this);
			
			this.add(label);
	        
	        
	        // creates a new MainEngine Panel
	        panel = new MapGUI();
	        panel.setBackground(Color.lightGray);
	        

	        //add Panel to Frame
	        this.add(panel);
	       

	        this.setVisible(true);
	    }

	    
		  //gets the panel 
	    public MapGUI getPanel(){
	        return panel;
	    }



		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("Mouse Clicked");
			MapGUI.changeMapModeCounter();
		}





		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}





		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}





		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}





		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			System.out.println("Key Pressed: " + e);
		}


		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
}



	


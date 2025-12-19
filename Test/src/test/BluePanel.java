package test;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



public class BluePanel extends JPanel implements ActionListener {
	private int rep = 50;
	private StatChangeListener listener;


	private boolean drawCircleNow = false;  
	private int circleX = 0;                
	private int circleY = 0;                
	private int circleRadius = 0;           
	

	public int getRep() {
	    return rep;
	}
	
	BluePanel(){
		this.setBackground(new Color(0x4A7B9D));
		this.setBounds(0, 250, 750, 250);
		this.setLayout(null);
		
		JButton button1 = new JButton("Increase Rep");
		button1.setBounds(300, 50, 150, 25);
		button1.setFocusable(false);
		button1.addActionListener(e -> {
            rep += 10;
            listener.onRepChanged(rep);

        });
		this.add(button1);
		
		JButton button2 = new JButton("Decrease Rep");
		button2.setBounds(300, 85, 150, 25);
		button2.setFocusable(false);
		button2.addActionListener(e -> {
			rep -= 10;
            listener.onRepChanged(rep);
            drawCircleNow = true;
            repaint();
        });
		this.add(button2);
		
		
		JButton button3 = new JButton("Increase Rep ++");
		button3.setBounds(300, 120, 150, 25);
		button3.setFocusable(false);
		button3.addActionListener(e -> {
			rep += 20;
			listener.onRepChanged(rep);
        });
		this.add(button3);
		
		JButton button4 = new JButton("Decrease Rep --");
		button4.setBounds(300, 155, 150, 25);
		button4.setFocusable(false);
		button4.addActionListener(e -> {
			rep -= 20;
			listener.onRepChanged(rep);
        });
		this.add(button4);
		
		
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    if (drawCircleNow) {
	        Main.drawCircle(g, circleX, circleY, getCircleRadius());
	    }
	}

	// Source - https://stackoverflow.com/a
    // Posted by Justin
    // Retrieved 2025-12-10, License - CC BY-SA 3.0
	public void showCircle(int x, int y, int radius) {
	    circleX = x;
	    circleY = y;
	    setCircleRadius(radius);
	    drawCircleNow = true;
	    repaint();
	}
 
	
	
	public void setStatChangeListener(StatChangeListener listener) {
	    this.listener = listener;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public int getCircleRadius() {
		return circleRadius;
	}

	public void setCircleRadius(int circleRadius) {
		this.circleRadius = circleRadius;
	}


	
}

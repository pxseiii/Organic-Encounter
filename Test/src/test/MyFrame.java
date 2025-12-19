package test;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MyFrame extends JFrame {
	JButton button;
	
	
	MyFrame(){
		
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(750,550);
		this.setLayout(null);
		
		
		/*============================
		 * THESE ARE FOR PRACTICE, IGNORE PLS
		 * //JFrame frame = new JFrame();
		JLabel healthIconLabel;
	    JLabel repIconLabel;
	    JLabel moneyIconLabel;		
	    
	    JLayeredPane statPane = new JLayeredPane();
	    statPane.setBounds(0, 0, 750, 250);
	    
		/* 
		ImageIcon healthIcon = new ImageIcon("images/health.png");
        Image scaledHealth = healthIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        healthIconLabel = new JLabel(new ImageIcon(scaledHealth));
        healthIconLabel.setText("Health: 100");
        healthIconLabel.setVerticalTextPosition(JLabel.BOTTOM);
        healthIconLabel.setHorizontalTextPosition(JLabel.CENTER);
        
        
        
        // rep factor
        ImageIcon repIcon = new ImageIcon("images/rep.png");
        Image scaledRep = repIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel repIconLabel = new JLabel(new ImageIcon(scaledRep));
        repIconLabel.setBounds(20, 20, 100, 100);
        statPane.add(repIconLabel, Integer.valueOf(2));   // top layer
        
       
        
        
        /*
        // money factor
        ImageIcon moneyIcon = new ImageIcon("images/money.png");
        Image scaledMoney = moneyIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        moneyIconLabel = new JLabel(new ImageIcon(scaledMoney));
		
		
		JPanel greenPanel = new JPanel();
		greenPanel.setBackground(new Color(0x00FFA2));
		greenPanel.setBounds(0, 0, 750, 250);
		
		greenPanel.setLayout(new BorderLayout());
		//greenPanel.add(healthIconLabel, BorderLayout.WEST);
		//greenPanel.add(moneyIconLabel, BorderLayout.EAST);
		greenPanel.add(repIconLabel);
		
		 JProgressBar bar = new JProgressBar();
	     bar.setValue(50);
	        
	     JLabel barLabel = new JLabel();
	     barLabel.setBounds(300, 0 ,100, 100);
	     barLabel.add(bar);
		
		
		buttonPanel buttonPanel = new buttonPanel();
		buttonPanel.setStatChangeListener(new StatChangeListener() {
			/*
			@Override
            public void onHealthChanged(int newHealth) {
                healthIconLabel.setText("Health: " + newHealth);
            }

            @Override
            public void onRepChanged(int newRep) {
                repIconLabel.setText("Rep: " + newRep);
                bar.setValue(newRep);
            }
            
        });
		

		MyFrame frame = new MyFrame();
		greenPanel.setBounds(0, 0, 750, 250);
		layeredPane.add(greenPanel, JLayeredPane.PALETTE_LAYER);

		buttonPanel.setBounds(0, 250, 750, 300);
		layeredPane.add(buttonPanel, JLayeredPane.DEFAULT_LAYER);

		bar.setBounds(300, 20, 150, 25);
		layeredPane.add(bar, JLayeredPane.MODAL_LAYER);

		frame.add(layeredPane);
		frame.setVisible(true);
		
		
		
		//label practice
		/*
		ImageIcon image1 = new ImageIcon("C:\\Users\\somet\\Downloads\\lol.jpg");
		Border border = BorderFactory.createLineBorder(Color.green, 3);
		
		JLabel label = new JLabel();
		label.setText("Aeiou");
		label.setIcon(image1);
		label.setVerticalTextPosition(JLabel.TOP);
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setForeground(Color.RED);
		label.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		label.setIconTextGap(0); //how far away
		label.setBackground(Color.BLACK);
		label.setOpaque(true); //displays background color
		
		label.setVerticalAlignment(JLabel.TOP);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBounds(200, 10, 250, 250);
		label.setBorder(border);
		
		ImageIcon image2 = new ImageIcon("C:\\Users\\somet\\Desktop\\Untitled.jpg");
		Border border2 = BorderFactory.createLineBorder(Color.MAGENTA, 3);
		
		JLabel label2= new JLabel();
		label2.setText("holla holla get $ hahaha");
		label2.setIcon(image2);
		label2.setVerticalTextPosition(JLabel.TOP);
		label2.setHorizontalTextPosition(JLabel.CENTER);
		label2.setForeground(Color.WHITE);
		label2.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		label2.setIconTextGap(100); //how far away
		label2.setBackground(new Color(0xFFCAB1));
		label2.setOpaque(true); //displays background color
		
		label2.setVerticalAlignment(JLabel.BOTTOM);
		label2.setHorizontalAlignment(JLabel.CENTER);
		
		label2.setBounds(200, 400, 250, 350);
		label2.setBorder(border2);
		
		
		JFrame frame = new JFrame("Hello!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setSize(670,800);
		frame.setVisible(true);
		frame.setLayout(null);
		frame.add(label);
		frame.add(label2);
		//frame.pack();
		
		ImageIcon image = new ImageIcon("amogus.png");
		
		frame.setIconImage(image.getImage()); //sets window icon
		// frame.getContentPane().setBackground(Color.green); //change color bg
		frame.getContentPane().setBackground(new Color(0x32E875));
		
		//new MyFrame();
		////*
		JButton button = new JButton();
		button.setBounds(120, 150, 250, 100);
		button.setText(":)");
		button.setIcon(image);
		
		button.addActionListener(e -> {
			System.out.println("!!");
		});
		*/
		
		//button.setFocusable(false);
		/*
		this.setIconImage(image.getImage()); //sets window icon
		// this.getContentPane().setBackground(Color.green); //change color bg
		this.getContentPane().setBackground(new Color(0x32E875)); 
		
		 */
		///
		 
		 
		

	}
}

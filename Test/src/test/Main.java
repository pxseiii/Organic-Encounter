	package test;
	import java.awt.*;
	import javax.swing.*;
	import java.awt.Graphics;
	import java.awt.Graphics2D;
	import java.awt.image.BufferedImage;
	
	@interface newComment{
		String author();
		String date();
		String purpose();
	}
	
	@newComment(
		author = "miks",
		date = "08-12",
		purpose = "shitty code to test the fill up thing for icons"
			)
	
	
	public class Main extends JFrame{
		public static void main(String[] args) {
			
			//wot de fok:
	
	
			
			JLabel repIconLabel;
		
	        // main layeredPane
	        JLayeredPane layeredPane = new JLayeredPane();
	        layeredPane.setBounds(0, 0, 750,550);
	        layeredPane.setLayout(null);
	
	        // stat Window (NORTH)
	        JLayeredPane statPane = new JLayeredPane();
	        statPane.setBounds(0, 0, 750, 250);
	        statPane.setLayout(null);
	        statPane.setBackground(new Color(0xFF00));
	        statPane.setOpaque(true);
	
	
	
	        // PNG (in stat window)
	        ImageIcon repIcon = new ImageIcon("images/rep.png");      
	        
	       //Image scaledRep = repIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); 
	        
	        BufferedImage rep = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
	        Graphics2D g = rep.createGraphics();
	
	        g.drawImage(repIcon.getImage(), 0, 0, 100, 100, null);
	        g.dispose();
	
	        // do not ask me how this works this shit was made by ai
	        for (int y = 0; y < rep.getHeight(); y++) {
	            for (int x = 0; x < rep.getWidth(); x++) {
	
	                int argb = rep.getRGB(x, y);
	                int alpha = (argb >>> 24);
	
	                if (alpha != 0) {
	                	 Color bg = statPane.getBackground(); // desired color
	                     rep.setRGB(
	                         x, y,
	                         (alpha << 24) | (bg.getRed() << 16) | (bg.getGreen() << 8) | bg.getBlue()
	                     );
	                }
	            }
	        }
	
	
	        //maybe dpat bufferedImage? para machange color bg ng png using RGB
	        repIconLabel = new JLabel(new ImageIcon(rep));
	
	        repIconLabel.setBounds(325, 20, 100, 150);
	        repIconLabel.setText("Rep: 50");
	        repIconLabel.setVerticalTextPosition(JLabel.BOTTOM);
	        repIconLabel.setHorizontalTextPosition(JLabel.CENTER);
	        repIconLabel.setIconTextGap(40);
	        statPane.add(repIconLabel, Integer.valueOf(2));   // top layer
	
	        
	        // progressBar (in stat window)
	        JProgressBar bar = new JProgressBar(SwingConstants.VERTICAL);
	        bar.setValue(50);
	        bar.setOpaque(true);
	        bar.setBounds(340, 20, 75, 95); //you have to manually tweak this to fit the icon
	        bar.setPreferredSize(new Dimension(100, 100));
	        bar.setForeground(Color.BLACK);
	
	        statPane.add(bar, Integer.valueOf(1));             // bottom layer
	
	        
	        
	        layeredPane.add(statPane);
	
	        //SOUTH
	        BluePanel buttonPanel = new BluePanel();
	        buttonPanel.setBounds(0, 250, 750, 300);
	        
		    
	
	        
	        buttonPanel.setStatChangeListener(new StatChangeListener() {
	        	@Override
	        	public void onRepChanged(int newRep) {
	
	        	    repIconLabel.setText("Rep: " + newRep);
	        	    
	        	    int tick = ((bar.getValue() + 20 == newRep) || (bar.getValue() - 20 == newRep)) ? 7 : 5;
	
	        	    Timer timer = new Timer(67, null); 
	        	    //Note: maybe make new timer FOR circle so after a certain time, it disappears
	        	    
	        	    if (((bar.getValue() + 20) == newRep) || ((bar.getValue() - 20) == newRep)) { 
	        	    	timer.setDelay(30); //make go fasterer
	        	    }
	        	    
	        	    timer.addActionListener(e -> {
	
	        	        int current = bar.getValue();
	        	        
	
	        	        if (current < newRep) { //increase
	        	            bar.setValue(++current);
	        	        } else if (current > newRep) { //decrease
	        	        	bar.setValue(--current);
	        	        } //else TRY CATCH
	        	        
	        	        int currentRadius = buttonPanel.getCircleRadius();
	        	        if (currentRadius < tick) {
	        	            buttonPanel.setCircleRadius(currentRadius + 1);
	        	        } else if (currentRadius > tick) {
	        	            buttonPanel.setCircleRadius(currentRadius - 1);
	        	        }
	        	        buttonPanel.showCircle(350, 20, buttonPanel.getCircleRadius());
	        	        
	        	        
	        	        if (current == newRep) {
	        	            timer.stop();
	        	            buttonPanel.showCircle(350, 20, 0); 
	        	            // ↑ essentially makes it it disappear. alternatively, i have to make it fade for it to be smoother
	        	        }
	        	        
	        	        
	        	    });
	
	        	    timer.start(); //starts the entire function up there
	        	    
	        	    
	        	    
	        	   
	        	}
	
	        });
	
	        layeredPane.add(buttonPanel, JLayeredPane.DEFAULT_LAYER);
	
	
	        MyFrame frame = new MyFrame();
	
	        frame.add(layeredPane);
	        frame.setVisible(true);
	        
			
	
			
		}
		
	 public static void drawCircle(Graphics g, int x, int y, int radius) {
			
	       int diameter = radius * 2;
	
	       //shift x and y by the radius of the circle in order to correctly center it
	       g.fillOval(x - radius, y - radius, diameter, diameter); 
	
	     }
	 
	 public static BufferedImage toBufferedImage(Image img)
	 {
	     if (img instanceof BufferedImage)
	     {
	         return (BufferedImage) img;
	     }
	
	     // Create a buffered image with transparency
	     BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
	
	     // Draw the image on to the buffered image
	     Graphics2D bGr = bimage.createGraphics();
	     bGr.drawImage(img, 0, 0, null);
	     bGr.dispose();
	
	     // Return the buffered image
	     return bimage;
	 }	
	}

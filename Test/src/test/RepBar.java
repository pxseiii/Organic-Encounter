package test;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class RepBar extends JPanel {

    private BufferedImage repImage;
    private int rep = 100; // 0–100%

    public RepBar(String imagePath) {
        try {
            repImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setPreferredSize(new Dimension(100, 100));
    }

    public void setRep(int newRep) {
        rep = Math.max(0, Math.min(100, newRep));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        // 1️⃣ Draw colored fill
        int fillHeight = getHeight() * rep / 100;
        g2.setColor(Color.GREEN);
        g2.fillRect(0, getHeight() - fillHeight, getWidth(), fillHeight);

        // 2️⃣ Draw PNG overlay scaled to panel size
        if (repImage != null) {
            g2.drawImage(repImage, 0, 0, getWidth(), getHeight(), null);
        }

        g2.dispose();
    }
}

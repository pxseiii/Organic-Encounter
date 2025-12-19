package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class PanelFader extends AbstractAction {
    private JPanel cardPanel;
    private FadeOverlay overlay;
    private Timer fadeTimer;
    private int alphaValue;
    private String fromPanel, toPanel;
    private CardLayout layout;
    private boolean fadingOut = true;

    private static final int FADE_DELAY = 30; // ms between steps
    private static final int ALPHA_STEP = 15; // how fast it fades

    private Runnable onFadeComplete;

    public PanelFader(JPanel cardPanel) {
        super("Fade Panels");
        this.cardPanel = cardPanel;
        this.layout = (CardLayout) cardPanel.getLayout();
    }

    public void setFromTo(String from, String to) {
        this.fromPanel = from;
        this.toPanel = to;
    }

    public void setOnFadeComplete(Runnable r) {
        this.onFadeComplete = r;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        alphaValue = 0;
        fadingOut = true;

        layout.show(cardPanel, fromPanel);

        SwingUtilities.invokeLater(() -> {
            Window window = SwingUtilities.getWindowAncestor(cardPanel);
            JRootPane rootPane = ((RootPaneContainer) window).getRootPane();

            overlay = new FadeOverlay(cardPanel.getSize());
            rootPane.setGlassPane(overlay);
            overlay.setVisible(true);

            fadeTimer = new Timer(FADE_DELAY, e2 -> fadeStep());
            fadeTimer.start();
        });
    }

    private void fadeStep() {
        overlay.setAlpha(alphaValue);

        if (fadingOut) {
            alphaValue += ALPHA_STEP;
            if (alphaValue >= 255) {
                alphaValue = 255;
                // switch panel when fully black
                layout.show(cardPanel, toPanel);
                fadingOut = false;
            }
        } else { // fade in
            alphaValue -= ALPHA_STEP;
            if (alphaValue <= 0) {
                alphaValue = 0;
                fadeTimer.stop();
                overlay.setVisible(false);
                if (onFadeComplete != null) onFadeComplete.run();
            }
        }
    }

    static class FadeOverlay extends JComponent {
        private int alpha;
        private Dimension size;

        public FadeOverlay(Dimension size) {
            this.size = size;
            setOpaque(false);
            setSize(size);
        }

        public void setAlpha(int alpha) {
            this.alpha = alpha;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(new Color(0, 0, 0, alpha));
            g2.fillRect(0, 0, size.width, size.height);
            g2.dispose();
        }
    }
}

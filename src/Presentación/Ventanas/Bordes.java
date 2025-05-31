package Presentación.Ventanas;

import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import java.awt.*;

    public class Bordes extends AbstractBorder implements Border {
        private final int radio;

        public Bordes(int radio) {
            this.radio = radio;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.white);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.drawRoundRect(x, y, width - 1, height - 1, radio, radio);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(4, 10, 4, 10);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = insets.right = 10;
            insets.top = insets.bottom = 4;
            return insets;
        }
}

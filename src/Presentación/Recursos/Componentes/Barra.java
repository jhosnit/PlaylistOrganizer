package Presentación.Recursos.Componentes;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class Barra extends BasicScrollBarUI {

    private final Color COLOR_BARRA_MOVER = new Color(75, 55, 144);
    private final Color COLOR_FONDO_BARRA = new Color(30, 30, 30);

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle limitesBarra) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(COLOR_BARRA_MOVER);
        g2.fillRoundRect(limitesBarra.x, limitesBarra.y, limitesBarra.width, limitesBarra.height, 0, 0);
        g2.dispose();
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle limiteFondoBarra) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(COLOR_FONDO_BARRA);
        g2.fillRect(limiteFondoBarra.x, limiteFondoBarra.y, limiteFondoBarra.width, limiteFondoBarra.height);
        g2.dispose();
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return crearBotonInvisible();
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return crearBotonInvisible();
    }

    private JButton crearBotonInvisible() {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(0, 0));
        button.setMinimumSize(new Dimension(0, 0));
        button.setMaximumSize(new Dimension(0, 0));
        button.setVisible(false);
        return button;
    }
}

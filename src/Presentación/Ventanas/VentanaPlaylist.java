package Presentación.Ventanas;

import javax.swing.*;
import java.awt.*;

public class VentanaPlaylist extends JPanel implements Runnable {
    private static final int ANCHO_VENTANA = 1200;
    private static final int ALTO_VENTANA = 800;
    private Thread hiloPlaylist;

    public VentanaPlaylist() {
        setPreferredSize(new Dimension(ANCHO_VENTANA, ALTO_VENTANA));
        setBackground(Color.lightGray);
        iniciarHiloJuego();
    }

    public void iniciarHiloJuego() {
        hiloPlaylist = new Thread(this);
        hiloPlaylist.start();
    }

    @Override
    public void run() {
        // Lógica del hilo
        while (true) {
            //controlar música...
            try {
                Thread.sleep(1000 / 60); // 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics.setFont(graphics.getFont().deriveFont(Font.BOLD, 45F));
        String texto = "Ordenador de Playlists";
        int x = obtenerXParaTextoCentrado(texto,graphics2D);
        super.paintComponent(graphics);
        graphics.setColor(Color.green);
        graphics.drawString(texto, x, 45);
    }

    private int obtenerXParaTextoCentrado(String texto, Graphics2D graphics2D) {
        int tamañoDeTexto;
        tamañoDeTexto = (int) graphics2D.getFontMetrics().getStringBounds(texto, graphics2D).getWidth();
        return (ANCHO_VENTANA / 2) - (tamañoDeTexto / 2);
    }
}

package Presentación.Ventanas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VentanaPlaylist extends JPanel {

    private static final int ANCHO_VENTANA = 1200;
    private static final int ALTO_VENTANA = 800;
    private Image imagen;

    public VentanaPlaylist() {
        setPreferredSize(new Dimension(ANCHO_VENTANA, ALTO_VENTANA));
        setBackground(new Color(18, 18, 18, 255));
        setLayout(null); // Usamos layout absoluto para posicionar botones y evitar paneles

        imagen = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Presentación/Recursos/Icono/logo.png"));
        agregarBarraSuperior();
        agregarBotones();
    }

    private void agregarBotones() {
        JButton btnDuracion = crearBoton("Duración", 250, 200);
        JButton btnNombre = crearBoton("Nombre", 500, 200);
        JButton btnArtista = crearBoton("Artista", 750, 200);

        add(btnDuracion);
        add(btnNombre);
        add(btnArtista);
    }

    private JButton crearBoton(String texto, int x, int y) {
        JButton boton = new JButton(texto);
        boton.setBounds(x, y, 200, 60);
        boton.setFocusPainted(false);
        boton.setContentAreaFilled(false);
        boton.setBorder(new Bordes(50));
        boton.setForeground(Color.WHITE);
        return boton;
    }

    private void agregarBarraSuperior() {
        JPanel barraSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        barraSuperior.setBackground(Color.BLACK);
        barraSuperior.setBounds(0, 0, ANCHO_VENTANA, 80); // Tamaño fijo
        barraSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));

        JButton btnCasa = new JButton("\uD83D\uDD03");
        btnCasa.setPreferredSize(new Dimension(60, 60));
        btnCasa.setFocusPainted(false);
        btnCasa.setContentAreaFilled(false);
        btnCasa.setBorder(new Bordes(50));
        btnCasa.setForeground(Color.WHITE);

        JPanel panelBusqueda = new JPanel(new BorderLayout());
        panelBusqueda.setBackground(Color.BLACK);
        panelBusqueda.setBorder(new Bordes(50));
        panelBusqueda.setPreferredSize(new Dimension(500, 60));

        JLabel iconoBusqueda = new JLabel("🔍 ");
        iconoBusqueda.setForeground(Color.LIGHT_GRAY);
        iconoBusqueda.setBorder(new EmptyBorder(0, 10, 0, 0));

        JTextField campo = new JTextField(" ");
        campo.setForeground(Color.LIGHT_GRAY);
        campo.setBackground(Color.BLACK);
        campo.setBorder(null);

        panelBusqueda.add(iconoBusqueda, BorderLayout.WEST);
        panelBusqueda.add(campo, BorderLayout.CENTER);

        barraSuperior.add(btnCasa);
        barraSuperior.add(panelBusqueda);
        add(barraSuperior);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 45F));

        String texto = "Playlist Organizer";
        int x = obtenerXParaTextoCentrado(texto, g2d);
        g2d.setColor(new Color(75, 55, 144));
        g2d.drawString(texto, x, 150);

        if (imagen != null) {
            g2d.drawImage(imagen, 950, 86, 100, 100, this);
        }
    }

    private int obtenerXParaTextoCentrado(String texto, Graphics2D g2d) {
        int anchoTexto = (int) g2d.getFontMetrics().getStringBounds(texto, g2d).getWidth();
        return (ANCHO_VENTANA - anchoTexto) / 2;
    }
}

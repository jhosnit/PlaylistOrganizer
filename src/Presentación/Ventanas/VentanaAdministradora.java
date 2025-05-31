package Presentación.Ventanas;

import java.awt.*;
import javax.swing.*;
import java.util.Objects;

public class VentanaAdministradora extends JPanel {

    private static volatile VentanaAdministradora instancia;

    public VentanaAdministradora() {
        crearVentana();
    }

    public static JFrame crearVentana() {
        JFrame ventana = new JFrame();
        Image iconoJuego = new ImageIcon(Objects.requireNonNull(VentanaAdministradora.class.getResource(
                "/Presentación/Recursos/Imagenes/icono.png"))).getImage();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setTitle("Playlist Organizer");

        ventana.setIconImage(iconoJuego);
        VentanaPlaylist ventanaPlaylist = new VentanaPlaylist();
        ventana.add(ventanaPlaylist);

        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        return ventana;
    }

    public static VentanaAdministradora obtenerVentana() {
        VentanaAdministradora ventanaAMostrar = instancia;
        if (ventanaAMostrar == null) {
            synchronized (VentanaAdministradora.class) {
                ventanaAMostrar = instancia;
                if (ventanaAMostrar == null) {
                    instancia = ventanaAMostrar = new VentanaAdministradora();
                }
            }
        }
        return ventanaAMostrar;
    }

}

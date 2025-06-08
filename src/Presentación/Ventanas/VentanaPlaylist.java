package Presentación.Ventanas;

import Lógica.Modelo.Canción;
import Lógica.Servicios.Estabilidad;
import Lógica.Modelo.PlayList;
import Presentación.Recursos.Componentes.Barra;
import Presentación.Recursos.Componentes.Bordes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class VentanaPlaylist extends JPanel {

    private static final int ANCHO_VENTANA = 1200;
    private static final int ALTO_VENTANA = 800;
    private static final int ALTO_BOTON = 60;
    private static final int ANCHO_BOTON_BARRA = 60;
    private static final int ANCHO_BOTON_ORDEN = 200;
    private static final int TAMAÑO_LETRA = 13;
    private static final int X = 0;
    private static final int Y = 0;

    PlayList playlist = new PlayList();
    private JPanel panelCanciones;
    private int estadoIngreso = 0;
    private String tempNombre = "";
    private String tempArtista = "";
    private JTextField barraDeEscritura;
    private JLabel iconoBarraEscritura;
    private JLabel etiquetaCosteComputacional;


    public VentanaPlaylist() {
        setPreferredSize(new Dimension(ANCHO_VENTANA, ALTO_VENTANA));
        setBackground(new Color(18, 18, 18, 255));

        setLayout(null); // Posicionar botones y no paneles

        agregarPanelSuperior();
        agregarBotonesOrdenamiento();
        agregarPanelCanciones();
        agregarEtiquetaCosteComputacional();
        actualizarPanelCanciones();

    }

    private void agregarPanelSuperior() {
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelSuperior.setBackground(Color.BLACK);
        panelSuperior.setBounds(X, Y, ANCHO_VENTANA, 90);

        JButton botonMas = crearBoton("➕", X, Y, ANCHO_BOTON_BARRA, ALTO_BOTON, false);
        botonMas.addActionListener(e -> {
            estadoIngreso = 1;
            barraDeEscritura.setText("");
            barraDeEscritura.setEnabled(true);
            iconoBarraEscritura.setFont(new Font("", Font.BOLD, TAMAÑO_LETRA));
            iconoBarraEscritura.setText("Nombre   ");
            barraDeEscritura.requestFocus();
            limpiarCosteComputacional();
        });

        JButton botonReorganizar = crearBoton("\uD83D\uDD03", X, Y, ANCHO_BOTON_BARRA, ALTO_BOTON, false);
        botonReorganizar.addActionListener(e -> {
            playlist.revolverCanciones(); // Revolver las canciones
            actualizarPanelCanciones();
            limpiarCosteComputacional();
        });

        JButton botonBiblioteca = crearBoton("\uD83D\uDCD7 ", X, Y, ANCHO_BOTON_BARRA, ALTO_BOTON, false);
        botonBiblioteca.addActionListener(e -> {
            playlist = new PlayList(); // Reinicia la lista con una nueva instancia
            playlist.cargarCancionesIniciales(); // Carga las canciones por defecto
            actualizarPanelCanciones();
            limpiarCosteComputacional();
        });

        JPanel panelBarraEscritura = new JPanel(new BorderLayout());
        panelBarraEscritura.setBackground(Color.BLACK);
        panelBarraEscritura.setBorder(new Bordes(50));
        panelBarraEscritura.setPreferredSize(new Dimension(500, ALTO_BOTON));

        // Icono
        iconoBarraEscritura = new JLabel("\uD83C\uDFB5   ");
        iconoBarraEscritura.setFont(new Font("", Font.BOLD, 20));
        iconoBarraEscritura.setForeground(Color.WHITE);
        iconoBarraEscritura.setBorder(new EmptyBorder(0, 10, 0, 0));

        // Escritura dentro de la barra
        barraDeEscritura = new JTextField("");
        barraDeEscritura.setEnabled(false);
        barraDeEscritura.setFont(new Font("", Font.PLAIN, TAMAÑO_LETRA)); // Escritura del texto
        barraDeEscritura.setForeground(Color.WHITE); // Color que el usuario escribe
        barraDeEscritura.setBackground(Color.BLACK); // Color del fondo
        barraDeEscritura.setCaretColor(Color.WHITE); // Color del cursor
        barraDeEscritura.setBorder(null);
        barraDeEscritura.addActionListener(e -> {
            String entrada = barraDeEscritura.getText().trim();

            switch (estadoIngreso) {
                case 1:
                    tempNombre = entrada;
                    barraDeEscritura.setText("");
                    iconoBarraEscritura.setFont(new Font("", Font.BOLD, TAMAÑO_LETRA));
                    iconoBarraEscritura.setText("Artista   ");
                    estadoIngreso = 2;
                    break;
                case 2:
                    tempArtista = entrada;
                    barraDeEscritura.setText("");
                    iconoBarraEscritura.setFont(new Font("", Font.BOLD, TAMAÑO_LETRA));
                    iconoBarraEscritura.setText("Duración   ");
                    estadoIngreso = 3;
                    break;
                case 3:
                    try {
                        int duracion = Integer.parseInt(entrada);
                        playlist.agregarCancion(tempNombre, tempArtista, duracion);
                        actualizarPanelCanciones();

                        barraDeEscritura.setText("");
                        barraDeEscritura.setBorder(null);
                        iconoBarraEscritura.setText("\uD83C\uDFB5   ");
                        iconoBarraEscritura.setFont(new Font("", Font.BOLD, 20));
                        iconoBarraEscritura.setForeground(Color.WHITE);
                        iconoBarraEscritura.setBorder(new EmptyBorder(0, 10, 0, 0));
                        estadoIngreso = 0;
                        barraDeEscritura.setEnabled(false);
                    } catch (NumberFormatException ex) {
                        barraDeEscritura.setText("");
                    }
                    break;
            }
        });

        panelBarraEscritura.add(iconoBarraEscritura, BorderLayout.WEST);
        panelBarraEscritura.add(barraDeEscritura, BorderLayout.CENTER);

        panelSuperior.add(botonMas);
        panelSuperior.add(botonBiblioteca);
        panelSuperior.add(panelBarraEscritura);
        panelSuperior.add(botonReorganizar);

        add(panelSuperior);
    }

    private void agregarBotonesOrdenamiento() {
        JButton botonNombre = crearBoton("Nombre", 250, 200, ANCHO_BOTON_ORDEN, ALTO_BOTON, true);
        botonNombre.addActionListener(e -> {
            ArrayList<Canción> copia = new ArrayList<>(playlist.getListaDeCanciones());

            long inicio = System.nanoTime();
            playlist.ordenarCancionesPorNombre();
            long fin = System.nanoTime();

            actualizarPanelCanciones();

            if (playlist.getListaDeCanciones().isEmpty()) {
                mostrarCosteComputacional("");
            } else {
                boolean estable = Estabilidad.esEstable(copia, playlist.getListaDeCanciones(), "nombre");
                mostrarCosteComputacional("Coste computacional (Nombre): " + ((fin - inicio) / 1e6) + " ms"
                        + (estable ? " | Estable" : " | No estable"));
            }
        });

        JButton botonArtista = crearBoton("Artista", 500, 200, ANCHO_BOTON_ORDEN, ALTO_BOTON, true);
        botonArtista.addActionListener(e -> {
            ArrayList<Canción> copia = new ArrayList<>(playlist.getListaDeCanciones());

            long inicio = System.nanoTime();
            playlist.ordenarCancionesPorArtista();
            long fin = System.nanoTime();

            actualizarPanelCanciones();

            if (playlist.getListaDeCanciones().isEmpty()) {
                mostrarCosteComputacional("");
            } else {
                boolean estable = Estabilidad.esEstable(copia, playlist.getListaDeCanciones(), "artista");
                mostrarCosteComputacional("Coste computacional (Artista): " + ((fin - inicio) / 1e6) + " ms"
                        + (estable ? " | Estable" : " | No estable"));
            }
        });

        JButton botonDuracion = crearBoton("Duración", 750, 200, ANCHO_BOTON_ORDEN, ALTO_BOTON, true);
        botonDuracion.addActionListener(e -> {
            ArrayList<Canción> copia = new ArrayList<>(playlist.getListaDeCanciones());

            long inicio = System.nanoTime();
            playlist.ordenarCancionesPorDuracion();
            long fin = System.nanoTime();

            actualizarPanelCanciones();

            if (playlist.getListaDeCanciones().isEmpty()) {
                mostrarCosteComputacional("");
            } else {
                boolean estable = Estabilidad.esEstable(copia, playlist.getListaDeCanciones(), "duración");
                mostrarCosteComputacional("Coste computacional (Duración): " + ((fin - inicio) / 1e6) + " ms"
                        + (estable ? " | Estable" : " | No estable"));
            }
        });

        add(botonNombre);
        add(botonArtista);
        add(botonDuracion);
    }

    private void agregarPanelCanciones() {
        panelCanciones = new JPanel();
        panelCanciones.setLayout(new BoxLayout(panelCanciones, BoxLayout.Y_AXIS));
        panelCanciones.setBackground(new Color(25, 25, 25));

        JScrollPane barraDeslizante = new JScrollPane(panelCanciones);
        barraDeslizante.getVerticalScrollBar().setUI(new Barra());
        barraDeslizante.setBounds(150, 300, 900, 450);
        barraDeslizante.setBorder(null);
        barraDeslizante.getVerticalScrollBar().setUnitIncrement(16);

        add(barraDeslizante);
        actualizarPanelCanciones();
    }

    private void agregarEtiquetaCosteComputacional() {
        etiquetaCosteComputacional = new JLabel(" ");
        etiquetaCosteComputacional.setBounds(411, 760, 700, 30);
        etiquetaCosteComputacional.setForeground(Color.GREEN);
        etiquetaCosteComputacional.setFont(new Font("", Font.BOLD, 16));
        add(etiquetaCosteComputacional);
    }

    private JButton crearBoton(String texto, int x, int y, int ancho, int alto, boolean usarXY) {
        JButton boton = new JButton(texto);
        if (usarXY) {
            boton.setBounds(x, y, ancho, alto);
        } else {
            boton.setPreferredSize(new Dimension(ancho, alto));
        }
        boton.setFont(new Font("", Font.BOLD, 18));
        boton.setFocusPainted(false);
        boton.setContentAreaFilled(false);
        boton.setBorder(new Bordes(50));
        boton.setForeground(Color.WHITE);
        return boton;
    }

    private void mostrarCosteComputacional(String mensaje) {
        etiquetaCosteComputacional.setText(mensaje);
    }

    private void limpiarCosteComputacional() {
        etiquetaCosteComputacional.setText(" ");
    }

    private void actualizarPanelCanciones() {
        panelCanciones.removeAll();

        // Recorre la lista de canciones
        for (int i = 0; i < playlist.getListaDeCanciones().size(); i++) {
            Canción canción = playlist.getListaDeCanciones().get(i);
            String texto = (i + 1) + ". " + canción.getNombre() + " - " + canción.getArtista() + " (" + canción.getDuracion() + "s)";

            JLabel etiqueta = new JLabel(texto);
            etiqueta.setForeground(Color.WHITE);
            etiqueta.setFont(new Font("Arial", Font.BOLD, 18));
            etiqueta.setBorder(new EmptyBorder(10, 20, 10, 20));

            panelCanciones.add(etiqueta);
        }

        // Para que los cambios se vean en pantalla
        panelCanciones.revalidate();
        panelCanciones.repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 45F));

        String texto = "Playlist Organizer";
        g2d.setColor(new Color(75, 55, 144));
        g2d.drawString(texto, 411, 150);

    }

}

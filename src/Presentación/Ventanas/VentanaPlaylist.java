package Presentación.Ventanas;

import Lógica.Canción;
import Lógica.PlayList;
import Presentación.Recursos.Componentes.Barra;
import Presentación.Recursos.Componentes.Bordes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VentanaPlaylist extends JPanel {

    private static final int ANCHO_VENTANA = 1200;
    private static final int ALTO_VENTANA = 800;
    private static final int ALTO_BOTON = 60;
    private static final int ANCHO_BOTON_BARRA = 60;
    private static final int ANCHO_BOTON_ORDEN = 200;
    private static final int X = 0;
    private static final int Y = 0;


    PlayList playlist = new PlayList();
    private JPanel panelCanciones;

    private Image imagen;

    public VentanaPlaylist() {
        setPreferredSize(new Dimension(ANCHO_VENTANA, ALTO_VENTANA));
        setBackground(new Color(18, 18, 18, 255));
        cargarImagenLogo();

        setLayout(null); // Usamos layout absoluto para posicionar botones y evitar paneles+

        agregarBarraSuperior();
        agregarBotones();
        agregarPanelCanciones();

    }

    private void cargarImagenLogo() {
        imagen = Toolkit.getDefaultToolkit().getImage(
                getClass().getResource("/Presentación/Recursos/Imagenes/logo.png"));
    }

    private void agregarBotones() {
        JButton botonDuracion = crearBoton("Duración", 250, 200, ANCHO_BOTON_ORDEN, ALTO_BOTON, true);
        JButton botonNombre = crearBoton("Nombre", 500, 200, ANCHO_BOTON_ORDEN, ALTO_BOTON, true);
        JButton botonArtista = crearBoton("Artista", 750, 200, ANCHO_BOTON_ORDEN, ALTO_BOTON, true);

        add(botonDuracion);
        add(botonNombre);
        add(botonArtista);
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


    private void agregarBarraSuperior() {
        JPanel barraSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        barraSuperior.setBackground(Color.BLACK);
        barraSuperior.setBounds(X, Y, ANCHO_VENTANA, 90);


        JButton botonMas = crearBoton("➕", X, Y, ANCHO_BOTON_BARRA, ALTO_BOTON, false);
        JButton botonReorganizar = crearBoton("\uD83D\uDD03", X, Y, ANCHO_BOTON_BARRA, ALTO_BOTON, false);
        JButton botonBiblioteca = crearBoton("\uD83D\uDCD7 ", X, Y, ANCHO_BOTON_BARRA, ALTO_BOTON, false);
        JButton botonCosteComputacional = crearBoton("\uD83D\uDCCA", X, Y, ANCHO_BOTON_BARRA, ALTO_BOTON, false);
        JButton botonInestable = crearBoton("\uD83D\uDCC9", X, Y, ANCHO_BOTON_BARRA, ALTO_BOTON, false);

        botonMas.addActionListener(e -> mostrarDialogoAgregarCancion());
        JPanel panelBusqueda = new JPanel(new BorderLayout());
        panelBusqueda.setBackground(Color.BLACK);
        panelBusqueda.setBorder(new Bordes(50));
        panelBusqueda.setPreferredSize(new Dimension(500, ALTO_BOTON));

        JLabel iconoBusqueda = new JLabel("\uD83D\uDD0D ");

        iconoBusqueda.setFont(new Font("", Font.BOLD, 20));
        iconoBusqueda.setForeground(Color.WHITE);
        iconoBusqueda.setBorder(new EmptyBorder(0, 10, 0, 0));

        JTextField campo = new JTextField(" ");
        campo.setForeground(Color.LIGHT_GRAY);
        campo.setBackground(Color.BLACK);
        campo.setBorder(null);

        panelBusqueda.add(iconoBusqueda, BorderLayout.WEST);
        panelBusqueda.add(campo, BorderLayout.CENTER);

        barraSuperior.add(botonReorganizar);
        barraSuperior.add(botonMas);
        barraSuperior.add(botonBiblioteca);

        barraSuperior.add(panelBusqueda);

        barraSuperior.add(botonCosteComputacional);
        barraSuperior.add(botonInestable);
        add(barraSuperior);
    }

    private void agregarPanelCanciones() {
        panelCanciones = new JPanel();
        panelCanciones.setLayout(new BoxLayout(panelCanciones, BoxLayout.Y_AXIS));
        panelCanciones.setBackground(new Color(25, 25, 25));

        JScrollPane scroll = new JScrollPane(panelCanciones);
        scroll.getVerticalScrollBar().setUI(new Barra());
        scroll.setBounds(150, 300, 900, 450);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        add(scroll);

        actualizarPanelCanciones();
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
        g2d.drawImage(imagen, 950, 86, 100, 100, this);

    }

    private int obtenerXParaTextoCentrado(String texto, Graphics2D g2d) {
        int anchoTexto = (int) g2d.getFontMetrics().getStringBounds(texto, g2d).getWidth();
        return (ANCHO_VENTANA - anchoTexto) / 2;
    }

    private void mostrarDialogoAgregarCancion() {
        JDialog dialogo = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Agregar canción", true);
        dialogo.setSize(400, 300);
        dialogo.setLayout(new BorderLayout());
        dialogo.setUndecorated(true);
        dialogo.getRootPane().setBorder(BorderFactory.createLineBorder(new Color(75, 55, 144), 2));

        JPanel panelContenido = new JPanel(new GridLayout(3, 2, 10, 10));
        panelContenido.setBackground(Color.BLACK);
        panelContenido.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel labelNombre = crearLabel("Nombre:");
        JTextField campoNombre = crearCampo();

        JLabel labelArtista = crearLabel("Artista:");
        JTextField campoArtista = crearCampo();

        JLabel labelDuracion = crearLabel("Duración (segundos):");
        JTextField campoDuracion = crearCampo();

        panelContenido.add(labelNombre);
        panelContenido.add(campoNombre);
        panelContenido.add(labelArtista);
        panelContenido.add(campoArtista);
        panelContenido.add(labelDuracion);
        panelContenido.add(campoDuracion);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.setBackground(Color.BLACK);

        JButton botonAceptar = crearBotonDialogo("Aceptar", new Color(75, 55, 144));
        JButton botonCancelar = crearBotonDialogo("Cancelar", Color.DARK_GRAY);

        botonAceptar.addActionListener(e -> {
            String nombre = campoNombre.getText().trim();
            String artista = campoArtista.getText().trim();
            String duracionTexto = campoDuracion.getText().trim();
            try {
                int duracion = Integer.parseInt(duracionTexto);
                playlist.agregarCancion(nombre, artista, duracion);
                actualizarPanelCanciones();
                dialogo.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Duración inválida. Debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        botonCancelar.addActionListener(e -> dialogo.dispose());

        panelBotones.add(botonCancelar);
        panelBotones.add(botonAceptar);

        dialogo.add(panelContenido, BorderLayout.CENTER);
        dialogo.add(panelBotones, BorderLayout.SOUTH);

        dialogo.setLocationRelativeTo(this);
        dialogo.setVisible(true);
    }

    private void actualizarPanelCanciones() {
        panelCanciones.removeAll(); // Limpia el contenido anterior del panel

        // Recorre la lista de canciones y crea etiquetas para cada una
        for (int i = 0; i < playlist.getListaDeCanciones().size(); i++) {
            Canción canción = playlist.getListaDeCanciones().get(i);
            String texto = (i + 1) + ". " + canción.getNombre() + " - " + canción.getArtista() + " (" + canción.getDuracion()+ "s)";

            JLabel etiqueta = new JLabel(texto);
            etiqueta.setForeground(Color.WHITE);
            etiqueta.setFont(new Font("Arial", Font.BOLD, 18));
            etiqueta.setBorder(new EmptyBorder(10, 20, 10, 20));

            panelCanciones.add(etiqueta);
        }

        // Asegura que los cambios se vean en pantalla
        panelCanciones.revalidate();
        panelCanciones.repaint();
    }

    private JLabel crearLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    private JTextField crearCampo() {
        JTextField campo = new JTextField();
        campo.setBackground(new Color(30, 30, 30));
        campo.setForeground(Color.WHITE);
        campo.setCaretColor(Color.WHITE);
        campo.setBorder(BorderFactory.createLineBorder(new Color(75, 55, 144)));
        return campo;
    }

    private JButton crearBotonDialogo(String texto, Color colorFondo) {
        JButton boton = new JButton(texto);
        boton.setForeground(Color.WHITE);
        boton.setBackground(colorFondo);
        boton.setFocusPainted(false);
        boton.setBorder(new Bordes(30));
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        return boton;
    }
}

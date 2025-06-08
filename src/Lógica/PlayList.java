package Lógica;

import java.util.ArrayList;
import java.util.Collections;

public class PlayList {
    ArrayList<Canción> listaDeCanciones = new ArrayList<>();
    Ordenamiento ordenarLista = new Ordenamiento();

    public void agregarCancion(String nombre, String artista, int duración) {
        listaDeCanciones.add(new Canción(nombre, artista, duración));
    }

    public ArrayList<Canción> getListaDeCanciones() {
        return listaDeCanciones;
    }

    public void cargarCancionesIniciales() {
        agregarCancion("Rosas", "La Oreja de Van Gogh", 240);
        agregarCancion("Fix You", "Coldplay", 300);
        agregarCancion("Billie Jean", "Michael Jackson", 294);
        agregarCancion("Shape of You", "Ed Sheeran", 233);
        agregarCancion("Viva la Vida", "Coldplay", 242);
        agregarCancion("Imagine", "John Lennon", 183);
        agregarCancion("Hotel California", "Eagles", 390);
        agregarCancion("Why Are Sundays So Depressing", "The Strokes", 590);
    }

    public void revolverCanciones() {
        Collections.shuffle(listaDeCanciones);
    }

    public void ordenarCancionesPorNombre() {
        ordenarLista.ordenamientoNombreBurbuja(listaDeCanciones);
    }

    public void ordenarCancionesPorArtista() {
        ordenarLista.ordenamientoArtistaInserción(listaDeCanciones);
    }

    public void ordenarCancionesPorDuracion() {
        ordenarLista.ordenamientoDuraciónSelección(listaDeCanciones);
    }

}

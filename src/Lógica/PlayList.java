package Lógica;

import java.util.ArrayList;

public class PlayList {
    ArrayList<Canción> listaDeCanciones = new ArrayList<>();

    public void agregarCancion(String nombre, String artista, int duración) {
        listaDeCanciones.add(new Canción(nombre, artista, duración));
    }

    public ArrayList<Canción> getListaDeCanciones() {
        return listaDeCanciones;
    }
}

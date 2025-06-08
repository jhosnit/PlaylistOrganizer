package Lógica;

import java.util.ArrayList;

public class Ordenamiento {

    public void ordenamientoNombreBurbuja(ArrayList<Canción> listaCanciones) {
        Canción aux;
        for (int i = 0; i < listaCanciones.size(); i++) {
            for (int j = 0; j < listaCanciones.size() - 1; j++) {
                if (listaCanciones.get(j).getNombre().compareToIgnoreCase(listaCanciones.get(j + 1).getNombre()) > 0) {
                    aux = listaCanciones.get(j);
                    listaCanciones.set(j, listaCanciones.get(j + 1));
                    listaCanciones.set(j + 1, aux);
                }
            }
        }
    }

    public void ordenamientoArtistaInserción(ArrayList<Canción> listaCanciones) {
        for (int i = 1; i < listaCanciones.size(); i++) {
            Canción canciónActual = listaCanciones.get(i);
            int j = i - 1;
            while (j > -1 && canciónActual.getArtista().compareToIgnoreCase(listaCanciones.get(j).getArtista()) < 0) {
                listaCanciones.set(j + 1, listaCanciones.get(j));
                j--;
            }
            listaCanciones.set(j + 1, canciónActual);
        }
    }

    public void ordenamientoDuraciónSelección(ArrayList<Canción> listaCanciones) {
        for (int i = 0; i < listaCanciones.size() - 1; i++) {
            int indiceMin = i;
            for (int j = i + 1; j < listaCanciones.size(); j++) {
                if (listaCanciones.get(j).getDuracion() < listaCanciones.get(indiceMin).getDuracion()) {
                    indiceMin = j;
                }
            }
            if (indiceMin != i) {
                Canción aux = listaCanciones.get(i);
                listaCanciones.set(i, listaCanciones.get(indiceMin));
                listaCanciones.set(indiceMin, aux);
            }
        }
    }

}

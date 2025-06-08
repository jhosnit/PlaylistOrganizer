package Lógica;

import java.util.Objects;

public class Canción {
    String nombre;
    String artista;
    int duracion;

    public Canción(String nombre, String artista, int duracion) {
        this.nombre = nombre;
        this.artista = artista;
        this.duracion = duracion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getArtista() {
        return artista;
    }

    public int getDuracion() {
        return duracion;
    }

    @Override
    public String toString() {
        return nombre + " - " + artista + " (" + duracion + "s)";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Canción otra = (Canción) obj;
        return this.nombre.equalsIgnoreCase(otra.nombre) &&
                this.artista.equalsIgnoreCase(otra.artista) &&
                this.duracion == otra.duracion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre.toLowerCase(), artista.toLowerCase(), duracion);
    }

}

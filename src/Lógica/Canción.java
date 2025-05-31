package Lógica;

public class Canción {
    String nombre;
    String artista;
    int duracion;

    public Canción(String nombre, String artista, int duracion){
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
}

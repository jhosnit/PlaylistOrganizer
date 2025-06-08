package Lógica;

import java.util.*;

public class Estabilidad {

    public static boolean esEstable(List<Canción> original, List<Canción> ordenada, String clave) {
        Map<String, Queue<Canción>> originalesPorClave = new HashMap<>();

        for (Canción c : original) {
            String valor = obtenerClave(c, clave);
            originalesPorClave.putIfAbsent(valor, new LinkedList<>());
            originalesPorClave.get(valor).add(c);
        }

        for (Canción c : ordenada) {
            String valor = obtenerClave(c, clave);
            Queue<Canción> cola = originalesPorClave.get(valor);
            if (cola == null || cola.isEmpty()) return false;

            Canción esperado = cola.poll();
            if (c != esperado) return false;
        }
        return true;
    }

    private static String obtenerClave(Canción c, String clave) {
        return switch (clave.toLowerCase()) {
            case "nombre" -> c.getNombre().toLowerCase();
            case "artista" -> c.getArtista().toLowerCase();
            case "duración" -> String.valueOf(c.getDuracion());
            default -> throw new IllegalArgumentException("Clave no válida");
        };
    }

}

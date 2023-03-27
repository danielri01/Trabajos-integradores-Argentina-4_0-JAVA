package EjercicioEntregable1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.io.File;

public class Main {
    public static void main(String[] args) throws IOException {

        // INICIO EL PROGRAMA //
        // Inicializacion de variables //
        Ronda ronda = new Ronda();
        Pronostico pronostico = new Pronostico();
        String Linea;
        String[] Campos;

        // Leo la cantidad de posiciones que tiene Resultados.csv para saber la cantidad de partidos que hay. //
        List<String> lineas;
        File archivo = new File("Resultados.csv");
        String Pathabsoluto= archivo.getAbsolutePath();

        Path Direccion = Paths.get(Pathabsoluto);
        lineas = Files.readAllLines(Direccion);
        int Cantidad = lineas.size();


        // Genero la cantidad de partidos por ronda sabiendo los partidos que hay en Resultados.csv //
        ronda.generarPartidos(Cantidad);

        // Genero los partidos para todos los resultados//
        for (int i = 0; i < Cantidad - 1; i++) {
            ronda.partidos[i].CargarResultados(i + 1);

            // Compruebo los resultados con los pronosticos y sumo la cantidad de acertados //
            ronda.comprobarResultadosPronostico(i);
        }

        // Informo la cantidad de puntos //
        if (ronda.puntosAcertados > 0) {
            System.out.println("--------------------------------");
            System.out.println("La puntuacion final es de : " + ronda.puntosAcertados);
            System.out.println("--------------------------------");
        } else {
            System.out.println("No se acertó ningun partido.");
        }
    }
}

package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
public class Pronostico {
    public static void analizarPronostico(String Equipo1, String Equipo2, int Resultado, Participantes participantes) throws IOException {


        // Defino mis variables //
        List<String> lineas;
        File archivo = new File("Pronosticos.csv");
        String Pathabsoluto = archivo.getAbsolutePath();
        Path Direccion = Paths.get(Pathabsoluto);
        String Equipo1Pronostico;
        String Equipo2Pronostico;
        String NombreGanador;
        String[] campos;
        int i = 1;


        // Analizo el Pronostico.csv para encontrar mi partido //
        // TOMO TODAS LAS LINEAS DEL ARCHIVO Y LO PASO A UNA LISTA //
        lineas = Files.readAllLines(Direccion);
        String linea = lineas.get(i);
        int a;
        // Busco mi partido en la lista //
        while ((i) < lineas.size()) {

            // Declaro mis equipos por linea (Se que los nombres de los equipos estan en la posicion 1 y 5. //
            linea = lineas.get(i);
            campos = linea.split(",");
            Equipo1Pronostico = campos[1].toUpperCase();
            Equipo2Pronostico = campos[5].toUpperCase();
            NombreGanador = campos[0].toUpperCase();
            a = -1;

            if (Equipo1.toUpperCase().equals(Equipo1Pronostico.toUpperCase()) && Equipo2.toUpperCase().equals(Equipo2Pronostico.toUpperCase())) {
                if (campos[2].toUpperCase().equals("X")) {
                    a = 1; // Significa que gano el equipo1.
                } else if (campos[3].toUpperCase().equals("X")) {
                    a = 2; // Significa que hubo un empate
                } else if (campos[4].toUpperCase().equals("X")) {
                    a = 3; // Significa que gano el equipo2.
                }
                if (a == Resultado) {
                    participantes.cargarParticipantes(1, NombreGanador);
                } else if (a != Resultado) {
                    participantes.cargarParticipantes(0, NombreGanador);
                }
            }
            i = i + 1;
        }
    }
}

package org.example;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class test {
    private Ronda ronda;
    private Pronostico pronostico;
    private int PartidosJugados;
    private String[] AuxLinea;
    private List<String> lineas;
    private Participantes participantes;


    @Before
    public void testResultado() throws IOException {
        ronda = new Ronda();
        pronostico = new Pronostico();
        int auxRondas = 0;
        File archivo = new File("Resultados.csv");
        String Pathabsoluto = archivo.getAbsolutePath();
        Path Direccion = Paths.get(Pathabsoluto);
        lineas = Files.readAllLines(Direccion);
        PartidosJugados = lineas.size();


        for (String linea : Files.readAllLines(Direccion)) {
            AuxLinea = linea.split(",");
            if (!AuxLinea[0].equals("Ronda")) {
                if (Integer.parseInt(AuxLinea[0]) > auxRondas) {
                    auxRondas = Integer.parseInt(AuxLinea[0]);
                }
            }
        }

        // Genero la cantidad de partidos que hay en Resultados.csv //
        ronda.generarPartidos(PartidosJugados);
        for (int i = 1; i < PartidosJugados; i++) {
            ronda.partidos[i].CargarResultados(i);
        }

        // Genero a los participantes.
        participantes = new Participantes();
    }

    @Test
    public void test1() throws IOException {
        String PersonaEvaluada = "Pedro";
        int e = 1;

        // Analizo 2 rondas
        // I es el numero de rondas
        for (int i = 0; i < 2; i++) {
            AuxLinea = lineas.get(e).split(","); // Representa cada linea de Resultados.cvs //
            while ((e < (PartidosJugados)) && (AuxLinea[0].equals("" + (i + 1)))) { //Mientras e sea menor que la cantidad de partidos jugados y la ronda corresponda..
                ronda.comprobarResultadosPronostico(e, participantes); // Envio cada uno de los resultados pronosticos para compararlos con los reales y sumar.
                AuxLinea = lineas.get(e).split(",");
                e = e + 1;

            } // Avanzo de linea
        }
        int w = participantes.cantRonda.get(PersonaEvaluada.toUpperCase());
        assertEquals(5, w);
        System.out.println("Test 1 completado");
    }


    @Test
    public void test2() throws IOException {
        String PersonaEvaluada = "Mariana";
        int e = 1;

        // Analizo 2 rondas
        // I es el numero de rondas
        for (int i = 0; i < 2; i++) {
            AuxLinea = lineas.get(e).split(","); // Representa cada linea de Resultados.cvs //
            while ((e < (PartidosJugados)) && (AuxLinea[0].equals("" + (i + 1)))) { //Mientras e sea menor que la cantidad de partidos jugados y la ronda corresponda..
                ronda.comprobarResultadosPronostico(e, participantes); // Envio cada uno de los resultados pronosticos para compararlos con los reales y sumar.
                AuxLinea = lineas.get(e).split(",");
                e = e + 1;

            } // Avanzo de linea
        }
        int w = participantes.cantRonda.get(PersonaEvaluada.toUpperCase());
        assertEquals(3, w);
        System.out.println("Test 2 completado");
    }
}

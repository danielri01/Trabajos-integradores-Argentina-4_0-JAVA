import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        // INICIO EL PROGRAMA //
        // Inicializacion de variables //
        Ronda ronda = new Ronda();
        Pronostico pronostico = new Pronostico();

        // Leo la cantidad de posiciones que tiene Resultados.csv para saber la cantidad de partidos que hay. //

        // *Entrega 2*//
        // Ahora analizo la cantidad de rondas que hay en pronostico.csv //

        int auxRondas = 0;

        File archivo = new File("Resultados.csv");
        String Pathabsoluto = archivo.getAbsolutePath();
        Path Direccion = Paths.get(Pathabsoluto);
        List<String> lineas = Files.readAllLines(Direccion);
        int PartidosJugados = lineas.size();

        String[] AuxLinea;
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
        Participantes participantes = new Participantes();
        participantes.GenerarArray(auxRondas);

        // Genero las rondas //
        int e = 1; // e va a ser mi cuenta para leer las lineas de Resultados.csv //
        HashMap<String, Integer> PuntosTotales = new HashMap<String, Integer>();

        for (int i = 0; i < auxRondas; i++) {
            AuxLinea = lineas.get(e).split(","); // Representa cada linea de Resultados.cvs //

            while ((e < (PartidosJugados)) && (AuxLinea[0].equals("" + (i + 1)))) { //Mientras e sea menor que la cantidad de partidos jugados y la ronda corresponda..
                ronda.comprobarResultadosPronostico(e, i, participantes); // Envio cada uno de los resultados pronosticos para compararlos con los reales y sumar.
                e = e + 1;
                if (e < 9) {
                    AuxLinea = lineas.get(e).split(",");
                } // Avanzo de linea
            }

            // Informo los datos //
            System.out.println("En la ronda " + (i + 1) + " los participantes suman un total de puntos : ");
            for (String llave : participantes.cantRonda[i].keySet()) {

                int v = participantes.cantRonda[i].get(llave);
                if (PuntosTotales.containsKey(llave)) { PuntosTotales.put(llave, PuntosTotales.get(llave) + v); }
                else { PuntosTotales.put(llave,v); }

                if (v > 0) {
                    System.out.println("Participante : " + llave + ", Puntos : " + v);
                }
            }
            System.out.println("");
        }
        for (String llave : PuntosTotales.keySet()) {
            System.out.println("Los puntos totales obtenidos por el Participante : " + llave + ", son de : " + PuntosTotales.get(llave)+ " Puntos con : "+
                    PuntosTotales.get(llave)+" aciertos");
        }
        Scanner leer = new Scanner(System.in);
        String SinCerrar = leer.nextLine();
    }
}
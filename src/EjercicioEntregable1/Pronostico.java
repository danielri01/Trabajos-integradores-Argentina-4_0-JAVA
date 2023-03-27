package EjercicioEntregable1;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;
public class Pronostico {
    public static int analizarPronostico(String Equipo1, String Equipo2) throws IOException {


        // Defino mis variables //
        List<String> lineas;
        Path Direccion = Paths.get("Pronostico.csv");
        String Equipo1Pronostico;
        String Equipo2Pronostico;
        int i = 1;


        // Analizo el Pronostico.csv para encontrar mi partido //
        // TOMO TODAS LAS LINEAS DEL ARCHIVO Y LO PASO A UNA LISTA //
        lineas = Files.readAllLines(Direccion);
        String linea = lineas.get(i);

        // Declaro mis equipos por linea (Se que los nombres de los equipos estan en la posicion 1 y 5. //
        String[] campos = linea.split(",");
        Equipo1Pronostico = campos[0];
        Equipo2Pronostico = campos[4];

        // Busco mi partido en la lista //
        while ((!Equipo1.equals(Equipo1Pronostico) || !Equipo2.equals(Equipo2Pronostico)) && i < lineas.size()) {

            // Entre en el while, significa que esta linea no es. Paso a la siguiente//
            linea = lineas.get(i);
            campos = linea.split(",");
            Equipo1Pronostico = campos[0];
            Equipo2Pronostico = campos[4];
            i = i + 1;
        }


        if (Equipo1.equals(Equipo1Pronostico) && Equipo2.equals(Equipo2Pronostico)) {

            // Significa que encontro el partido en Pronostico.csv //
            // Analizo el ganador. //
            if (campos[1].equals("X")) {
                i = 1; // Significa que gano el equipo1.
            } else if (campos[2].equals("X")) {
                i = 2; // Significa que hubo un empate
            } else if (campos[3].equals("X")) {
                i = 3; // Significa que gano el equipo2.
            }
        } else {
            i = 0; // No existe el equipo en el Pronostico.csv //
        }

        // Devuelvo el resultado //
        return i;
    }
}


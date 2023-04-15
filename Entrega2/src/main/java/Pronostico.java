import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;
public class Pronostico {
    public static void analizarPronostico(String Equipo1, String Equipo2, int Posicion, int Resultado, Participantes participantes) throws IOException {


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
            if (Equipo1.equals(Equipo1Pronostico) && Equipo2.equals(Equipo2Pronostico)) {
                if (campos[2].toUpperCase().equals("X")) {
                    a = 1; // Significa que gano el equipo1.
                } else if (campos[3].toUpperCase().equals("X")) {
                    a = 2; // Significa que hubo un empate
                } else if (campos[4].toUpperCase().equals("X")) {
                    a = 3; // Significa que gano el equipo2.
                }

                if (a == Resultado) {
                    participantes.cargarParticipantes(Posicion, 1, NombreGanador);
                } else if (a != Resultado) {
                    participantes.cargarParticipantes(Posicion, 0, NombreGanador);
                }
            }
            i = i + 1;
        }
    }
}

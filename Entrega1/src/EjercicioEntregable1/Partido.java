package EjercicioEntregable1;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;
public class Partido {
    public String equipo1;
    public String equipo2;
    public int golesEquipo1;
    public int golesEquipo2;

 public void CargarResultados (int NroPartido) throws IOException {

     // Defino mis variables //
     List<String> lineas;
     Path Direccion = Paths.get("Resultados.csv");

     // TOMO TODAS LAS LINEAS DEL ARCHIVO Y LO PASO A UNA LISTA //
     lineas = Files.readAllLines(Direccion);
     String linea = lineas.get(NroPartido);
     String[] campos = linea.split(",");

     // Sabiendo en que posicion está cada cosa //
     this.equipo1 = campos[0];
     this.equipo2 = campos[3];
     this.golesEquipo1 = Integer.parseInt(campos[1]);
     this.golesEquipo2 = Integer.parseInt(campos[2]);
 }

 public static int Ganador(int r1, int r2) {
     int e = 0; // Significa que hubo un error.
     if (r1 == r2) {
         e = 2; // Significa que hubo un empate
     } else if (r1 < r2) {
         e = 3; // Significa que gano el equipo2.
     } else if (r1 > r2) {
         e = 1; // Significa que gano el equipo1.
     }
     return e;
 }
}



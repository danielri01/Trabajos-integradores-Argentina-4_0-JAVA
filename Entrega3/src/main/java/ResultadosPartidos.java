import java.util.ArrayList;
import java.util.HashMap;

public class ResultadosPartidos {

    // String:  Representa la Key de fase. //
    // List : Representa la lista de equipos por ronda. //
    public HashMap<String, ArrayList<Equipo>[]> PuntosParticipantes = new HashMap<>();

    // String : Representa el nombre de la fase (key) //
    // ArrayList : Representa los participantes de esa fase //
    public HashMap<String, ArrayList<informacioneParticipantes>> informacionPredicciones = new HashMap<>();

    // String : Representa los nombres de los participantes (key)
    // Integer : Representa los puntos (valor) //
    public HashMap<String, Integer> CantidadPuntos = new HashMap<>();

    // Compruebo que esa persona acertó el pronostico con un booleano //
    public static boolean comprobarResultadosPartido(String a1, String a2) {

        if (a1.equalsIgnoreCase(a2)) {
            return true;
        }

        else {
            return false;
        }
    }

    // Compruebo que el partido que se esta leyendo en el pronostico coincida con mi partido //
    public static boolean mismosEquipos(String e1, String e2, String eq1, String eq2) {

        if (e1.equalsIgnoreCase(eq1) && e2.equalsIgnoreCase(eq2)) {
            return true;
        }

        else {
            return false;
        }
    }

}
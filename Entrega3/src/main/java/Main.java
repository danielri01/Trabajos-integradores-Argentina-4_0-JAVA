import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {


        // Cargo el valor de cada acierto //
        List<String> lineasAciertos = CargarResultados("AciertosPuntaje");
        String linea[] = lineasAciertos.get(1).split(",");
        int valorPorAcierto = Integer.parseInt(linea[1]);
        int valorPorRonda = Integer.parseInt(linea[2]);
        int valorPorFase = Integer.parseInt(linea[3]);



        // Conectamos con la base de datos //
        List<String> lineasConfig = CargarResultados("Config");
        linea = lineasConfig.get(1).split(",");

        String url = linea[1];
        String user = linea[2];


        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection
                con = DriverManager.getConnection(url, user, "");
        Statement stmt = con.createStatement();
        Statement stmt2 = con.createStatement();
        Statement stmt3 = con.createStatement();


        // Obtenemos el nombre de todas las fases //
        String sql = "SELECT DISTINCT Fase FROM resultados";
        ResultSet lasFases = stmt3.executeQuery(sql);
        ResultadosPartidos Resultados = new ResultadosPartidos();

        // Recorremos los resultados //
        while (lasFases.next()) {
            int maxRonda = 0;
            String nombreFase = lasFases.getString("Fase");

            // Obtenemos la informacion del partido de la DB //
            String COMinformacionFase = "SELECT Equipo1, Equipo2, Ronda, Goles1, Goles2, Resultado FROM resultados WHERE fase = '" + nombreFase + "'";
            ResultSet InformacionFase = stmt.executeQuery(COMinformacionFase);

            // Consultamos la cantidad de rondas por esa fase //
            String ConsultaRondas = "SELECT MAX(ronda) AS max_ronda FROM resultados WHERE fase = '" + nombreFase + "'";
            ResultSet CantRondasFase = stmt2.executeQuery(ConsultaRondas);

            // Guardamos el resultado en un Integer //
            if (CantRondasFase.next()) {
                maxRonda = CantRondasFase.getInt("max_ronda");
            }
            CantRondasFase.close();

            // Creo mi lista de equipos //
            ArrayList<Equipo>[] equipo = new ArrayList[maxRonda];
            for (int i = 0; i < maxRonda; i++) {
                equipo[i] = new ArrayList<>();
            }

            // Generamos la informacion de los partidos que hay en esa fase //
            while (InformacionFase.next()) {

                // Genero la informacion de los equipos //
                Equipo equipoInfo = new Equipo();
                equipoInfo.cargarGolesEquipo(Integer.parseInt(InformacionFase.getString("Goles1")), Integer.parseInt(InformacionFase.getString("Goles2")));
                equipoInfo.cargarNombreEquipo(InformacionFase.getString("Equipo1"), InformacionFase.getString("Equipo2"));

                // Cargo la lista con los datos de mi equipo en la posicion de la ronda //
                equipo[((Integer.parseInt(InformacionFase.getString("Ronda"))) - 1)].add(equipoInfo);
                equipoInfo.cargarResultado(InformacionFase.getString("Resultado"));

            }

            // Cargamos el hashmap con los datos //
            if (!Resultados.PuntosParticipantes.containsKey(lasFases.getString("Fase").toUpperCase())) {
                Resultados.PuntosParticipantes.put(lasFases.getString("Fase").toUpperCase(), equipo);
            }

            InformacionFase.close();
        }

        // Cerramos la conexion //
        con.close();

        // Cargo mi archivo de predicciones //
        List<String> lineasPronostico = CargarResultados("Predicciones");

        // Recorro las lineas de mi archivo de predicciones //
        for (String lineas : lineasPronostico) {

            linea = lineas.toUpperCase().split(",");

            // Creo un nuevo objeto para cargarlo en el hashmap //
            informacioneParticipantes infoPar = new informacioneParticipantes();
            infoPar.cargarInfoParticipantes(linea);
            infoPar.setResultado(linea[3]);

            // Lo cargo //
            if (!Resultados.informacionPredicciones.containsKey(linea[0])) {
                ArrayList<informacioneParticipantes> listaAux = new ArrayList<>();
                listaAux.add(infoPar);
                Resultados.informacionPredicciones.put(linea[0], listaAux);
            } else {
                Resultados.informacionPredicciones.get(linea[0]).add(infoPar);
            }
        }


        // Recorrer cada Fase (Octavos, grupos, etc..)//
        for (Map.Entry<String, ArrayList<Equipo>[]> Recorrer : Resultados.PuntosParticipantes.entrySet()) {
            String clave = Recorrer.getKey();
            ArrayList<Equipo>[] valor = Recorrer.getValue();
            HashMap<String, Boolean> puntosExtraFase = new HashMap<>();

            // Recorrer cada Ronda (Rondas, en este caso solamente los grupos tienen diferentes rondas) //
            for (int i = 0; i < valor.length; i++) {
                ArrayList<Equipo> equipos = valor[i];

                // Recorro todos los pronosticos ('j') //
                for (Equipo equipo : equipos) {

                    // Creo mis predicciones en base a la fase para no evaluar tantas veces //
                    ArrayList<informacioneParticipantes> listaPredicciones = Resultados.informacionPredicciones.get(clave);

                    // Busco en mi lista //
                    for (int e = 0; e < listaPredicciones.size(); e++) {

                        // Genero un hashmap de puntos por ronda para verificar si acerto todos los partidos de esta ronda (i) //
                        HashMap<String, Boolean> puntosExtraRonda = new HashMap<>();
                        informacioneParticipantes auxLista = listaPredicciones.get(e);

                        // Pregunto si los partidos de mi pronostico coinciden con los partidos de la Database //
                        if (ResultadosPartidos.mismosEquipos(equipo.getEquipo1(), equipo.getEquipo2(), auxLista.getEquipo1(), auxLista.getEquipo2())) {

                            // Genero mi hashmap de puntos extras ya que esta persona tiene un pronostico en este partido //
                            puntosExtraRonda.put(auxLista.getNombre().toUpperCase(), true);

                            // Pregunto si esta persona ya habia entrado a el hashmap de los puntos extra por fase, para no perder el valor true //
                            if (!puntosExtraFase.containsKey(auxLista.getNombre())) {
                                puntosExtraFase.put(auxLista.getNombre().toUpperCase(), true);
                            }

                            // Compruebo el resultado el partido, si entro acertó el resultado //
                            if (ResultadosPartidos.comprobarResultadosPartido(equipo.getResultado(), auxLista.getResultado())) {

                                // Significa que acertó el resultado //

                                // Sumo los puntos por acierto //
                                if (!Resultados.CantidadPuntos.containsKey(auxLista.getNombre())) {
                                    Resultados.CantidadPuntos.put(auxLista.getNombre(), valorPorAcierto);
                                } else {
                                    int Puntos = Resultados.CantidadPuntos.get(auxLista.getNombre()) + valorPorAcierto;
                                    Resultados.CantidadPuntos.put(auxLista.getNombre(), Puntos);
                                }
                            } else {

                                // No acerto el resultado, por lo tanto perdio el bonus de ronda y de fase //
                                puntosExtraRonda.put(auxLista.getNombre().toUpperCase(), false);
                                puntosExtraFase.put(auxLista.getNombre().toUpperCase(), false);
                            }
                        }

                        // Compruebo quienes acertaron todos los resultados de la ronda //
                        evaluarCumpleReglas(valorPorRonda, Resultados, puntosExtraRonda);
                    }
                }
            }
            // Compruebo quienes acertaron todos los partidos de la fase (Partidos de : Octavo, Grupos, Semis, etc..) //
            evaluarCumpleReglas(valorPorFase, Resultados, puntosExtraFase);
        }

        // Informo los puntos //

        System.out.println("La cantidad de puntos es la siguiente : ");

        for (Map.Entry<String, Integer> PuntosTotales : Resultados.CantidadPuntos.entrySet()) {
            String clave = PuntosTotales.getKey();
            int valor = PuntosTotales.getValue();
            System.out.println("Participante "+clave+". Tiene una cantidad de :  "+valor+" puntos.");
        }
    }

    // Evaluo si cumple las condiciones (chequeo los booleanos de todos los participantes que participaron en esa ronda/fase)
    private static void evaluarCumpleReglas(int valorPorFase, ResultadosPartidos resultados, HashMap<String, Boolean> puntosExtraFase) {
        for (Map.Entry<String, Boolean> puntosRonda : puntosExtraFase.entrySet()) {
            String claveParticipante = puntosRonda.getKey();
            if (puntosExtraFase.get(claveParticipante.toUpperCase())) {
                int puntosToT = resultados.CantidadPuntos.get(claveParticipante);
                resultados.CantidadPuntos.put(claveParticipante, (puntosToT + valorPorFase));
            }
        }
    }

    // Devuelve una lista con el texto de los archivos //
    private static List<String> CargarResultados(String archivoALeer) throws IOException {

        File archivo = new File(archivoALeer + (".csv"));
        String Pathabsoluto = archivo.getAbsolutePath();
        Path Direccion = Paths.get(Pathabsoluto);
        List<String> aux = Files.readAllLines(Direccion);
        return aux;
    }
}
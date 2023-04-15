import java.io.IOException;

public class Ronda {
    public Partido[] partidos;
    public int puntosAcertados = 0;

    public void generarPartidos (int cantRondas) {
        partidos = new Partido[cantRondas];
        for (int i = 0; i < cantRondas; i++) {
            partidos[i] = new Partido();
        }
    }

    public void comprobarResultadosPronostico(int NroPartido, int Posicion, Participantes participantes) throws IOException {

        String equipo1 = partidos[NroPartido].equipo1;
        String equipo2 = partidos[NroPartido].equipo2;
        int golesEquipo1 = partidos[NroPartido].golesEquipo1;
        int golesEquipo2 = partidos[NroPartido].golesEquipo2;

        // Analiza en que posicion buscar la X //
        int ResultadoPartido = Partido.Ganador(golesEquipo1,golesEquipo2);
        Pronostico.analizarPronostico(equipo1,equipo2,Posicion,ResultadoPartido,participantes);

    }
}

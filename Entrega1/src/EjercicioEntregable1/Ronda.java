package EjercicioEntregable1;

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

    public void comprobarResultadosPronostico(int NroPartido) throws IOException {

        //-------------------//
        // 1 : Ganó equipo 1 //
        // 2 : Empate        //
        // 3 : Ganó equipo 2 //
        //-------------------//

        String equipo1 = partidos[NroPartido].equipo1;
        String equipo2 = partidos[NroPartido].equipo2;
        int golesEquipo1 = partidos[NroPartido].golesEquipo1;
        int golesEquipo2 = partidos[NroPartido].golesEquipo2;
        int ResultadoPartido = Partido.Ganador(golesEquipo1,golesEquipo2);

        int ResultadoPronostico = Pronostico.analizarPronostico(equipo1,equipo2);

        if ((ResultadoPronostico == 0) || (ResultadoPartido == 0)) {
            System.out.println("Hubo un error");
        } else if (ResultadoPronostico == ResultadoPartido) {
            System.out.println("Hubo un acierto en el pronostico del partido: "+equipo1+" vs "+equipo2+". Se suma 1 punto!" );
            this.puntosAcertados = this.puntosAcertados + 1;
        } else { System.out.println("El pronostico del partido: "+equipo1+" vs "+equipo2+". Fue erroneo. Se suman 0 puntos"); }

    }
}

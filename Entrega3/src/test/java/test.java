import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class test {

    informacioneParticipantes Participante = new informacioneParticipantes();

    Equipo equipo = new Equipo();
    @Test
    public void testA() {

        String[] Lineas = new String[5];
        int i;
        Lineas[1] = "Mariela";
        Lineas[2] = "Argentina";
        Lineas[4] = "Arabia Saudita";
        Lineas[3] = "Gana2";

        Participante.cargarInfoParticipantes(Lineas);
        Participante.setResultado("GANA1");

        equipo.cargarNombreEquipo("Argentina","Arabia Saudita");
        equipo.cargarGolesEquipo(0, 1);
        equipo.cargarResultado("GANA1");

        if (ResultadosPartidos.mismosEquipos(Participante.getEquipo1(),Participante.getEquipo2(),equipo.getEquipo1(),equipo.getEquipo2()));
        {
            if (ResultadosPartidos.comprobarResultadosPartido(equipo.getResultado(), Participante.getResultado())) ;
            {
                i = 1;
            }
        }
        assertEquals(1, i);


    }
}

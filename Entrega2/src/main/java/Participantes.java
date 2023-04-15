import java.util.HashMap;
public class Participantes {
    public HashMap<String, Integer>[] cantRonda;

    // Genero la cantida de posiciones //
    public void GenerarArray (int i) {
        this.cantRonda = new HashMap[i];
        for (int e = 0; e < i; e++) {
            this.cantRonda[e] = new HashMap<>();
        }
    }

    // Cargo mis datos en mi lista //
    public void cargarParticipantes (int Posicion ,int Puntos, String Nombre) {
        int a = 0;
        if (this.cantRonda[Posicion].containsKey(Nombre)) {
            a = this.cantRonda[Posicion].get(Nombre) + Puntos;
            this.cantRonda[Posicion].put(Nombre,a);
        } else {
            this.cantRonda[Posicion].put(Nombre,(a + Puntos));
        }
    }
}


package org.example;

import java.util.HashMap;
public class Participantes {
    public HashMap<String, Integer> cantRonda = new HashMap<>();

    // Cargo mis datos en mi lista //
    public void cargarParticipantes (int Puntos, String Nombre) {
        int a = 0;
        if (this.cantRonda.containsKey(Nombre.toUpperCase())) {
            a = this.cantRonda.get(Nombre.toUpperCase()) + Puntos;
            this.cantRonda.put(Nombre.toUpperCase(),a);
        } else {
            this.cantRonda.put(Nombre.toUpperCase(),(a + Puntos));
        }
    }
}


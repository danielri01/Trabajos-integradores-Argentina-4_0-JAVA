public class informacioneParticipantes {


    private String nombre;
    private String equipo1;
    private String equipo2;
    private String resultado;

    // Cargo los datos de una linea de Pronostico.csv //
    public void cargarInfoParticipantes (String[] Lineas){
        this.nombre = Lineas[1].toUpperCase();
        this.equipo1 = Lineas[2].toUpperCase();
        this.equipo2 = Lineas[4].toUpperCase();
    }

    // Getters y Setters //
    public String getNombre() {
        return nombre;
    }
    public String getEquipo1() {
        return equipo1;
    }
    public String getEquipo2() {
        return equipo2;
    }
    public String getResultado(){
        return this.resultado;
    }
    public void setResultado(String resultado) {
        this.resultado = resultado.toUpperCase();
    }


}

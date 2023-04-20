public class Equipo {
    // Representa los equipos goles y resultado //
    private String equipo1;
    private String equipo2;
    private int golesEquipo1;
    private int golesEquipo2;
    private String Resultado;

    // Inicializador vacio //
    public void InicializarEquipo() {
        this.equipo1 = "";
        this.equipo2 = "";
        this.golesEquipo1 = 0;
        this.golesEquipo2 = 0;
    }

    // Setters y Getters //
    public void cargarNombreEquipo(String e1, String e2) {
        this.equipo1 = e1.toUpperCase();
        this.equipo2 = e2.toUpperCase();
    }

    public void cargarGolesEquipo(int g1, int g2) {
        this.golesEquipo1 = g1;
        this.golesEquipo2 = g2;
    }
    public void cargarResultado (String Res) {
        this.Resultado = Res.toUpperCase();
    }

    public int getGolesEquipo2(){
        return this.golesEquipo1;
    }

    public int getGoles2(){
        return this.golesEquipo2;
    }

    public String getEquipo1() {
        return equipo1;
    }

    public String getEquipo2() {
        return equipo2;
    }

    public String getResultado () {
        return Resultado;
    }

}

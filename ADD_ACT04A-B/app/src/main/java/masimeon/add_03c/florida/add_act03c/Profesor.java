package masimeon.add_03c.florida.add_act03c;

import java.util.UUID;

public class Profesor {

    private String nombre;
    private Integer edad;
    private String ciclo;
    private String cursotutor;
    private String despacho;
    private Integer id;

    public Profesor(String nombre, Integer edad, String ciclo, String cursotutor, String despacho) {
        this.nombre = nombre;
        this.edad = edad;
        this.ciclo = ciclo;
        this.cursotutor = cursotutor;
        this.despacho = despacho;
    }

    public void setID(int id){
        this.id=id;
    }

    public Integer getID(){ return id; }

    public String getNombre() {
        return nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public String getCiclo() {
        return ciclo;
    }

    public String getTutorCurso() {
        return cursotutor;
    }

    public String getDespacho() {
        return despacho;
    }
}

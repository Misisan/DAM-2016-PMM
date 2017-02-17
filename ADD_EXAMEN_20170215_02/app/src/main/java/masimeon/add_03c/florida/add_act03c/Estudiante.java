package masimeon.add_03c.florida.add_act03c;

import java.util.UUID;

public class Estudiante {
   // private String id;
    private String nombre;
    private Integer edad;
    private String ciclo;
    private String curso;
    private Integer notaMedia;
    private Integer id;

    public Estudiante(String nombre, Integer edad, String ciclo, String curso, Integer notaMedia) {
       // this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.edad = edad;
        this.ciclo = ciclo;
        this.curso = curso;
        this.notaMedia = notaMedia;
    }

    /*public String getIdEstudiante() {
        return id;
    }*/

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

    public String getCurso() {
        return curso;
    }

    public Integer getNotaMedia() {
        return notaMedia;
    }
}

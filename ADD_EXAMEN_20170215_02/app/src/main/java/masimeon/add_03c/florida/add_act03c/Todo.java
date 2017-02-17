package masimeon.add_03c.florida.add_act03c;

public class Todo {
    private String nombre;
    private Integer id;
    private String tipo;

    public Todo(String nombre, Integer id, String tipo) {
        this.nombre = nombre;
        this.id=id;
        this.tipo=tipo;
    }

    public Integer getID(){ return id; }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }
}

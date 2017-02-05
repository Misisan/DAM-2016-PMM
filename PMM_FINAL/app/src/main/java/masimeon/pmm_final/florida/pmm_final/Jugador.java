package masimeon.pmm_final.florida.pmm_final;


import java.io.Serializable;

public class Jugador{

    private String nombre;
    private String nick;
    private Integer edad;
    private Integer puntos=0;
    private Integer id;
    private Integer activo;
    private String img;

    public Jugador( String nombre, String nick){
        this.nombre=nombre;
        this.nick=nick;
    }

    public Jugador(){}

    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNick(String nick){
        this.nick=nick;
    }

    public String getNick(){
        return nick;
    }

    public void setEdad(Integer edad){
        this.edad=edad;
    }

    public Integer getEdad(){
        return edad;
    }

    public void setPuntos(Integer puntos){
        this.puntos=puntos;
    }

    public Integer getPuntos(){
        return puntos;
    }

    public void setId(Integer id){
        this.id=id;
    }

    public Integer getId(){
        return id;
    }

    public void setActivo(Integer act){
        this.activo=act;
    }

    public Integer getActivo(){
        return activo;
    }

    public void setImg(String img){this.img=img;}

    public String getImg(){return img;}

}

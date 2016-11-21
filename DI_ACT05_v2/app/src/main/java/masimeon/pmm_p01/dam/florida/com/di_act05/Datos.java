package masimeon.pmm_p01.dam.florida.com.di_act05;

import android.widget.TextView;

/**
 * Clase casi inecesaria, pero como solo es casi la dejo para poder aprovecharla en un futuro
 */

public class Datos {

    protected String nombre;
    //Este id lo necesitamos por su getter, necesario para el Adapter_datos al extender de BaseAdapter
    protected long id;

    public Datos(String nombre){
        this.nombre=nombre;
    }

    public String getNombre(){
        return nombre;
    }

    public long getId(){
        return id;
    }
}

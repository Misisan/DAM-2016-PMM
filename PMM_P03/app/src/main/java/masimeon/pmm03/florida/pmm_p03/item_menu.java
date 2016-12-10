package   masimeon.pmm03.florida.pmm_p03;


import android.graphics.drawable.Drawable;

public class item_menu {

    private String titulo;
    private int icono;

    public item_menu(String t, int i){
        titulo=t;
        icono=i;
    }

    public String getTitulo(){
        return titulo;
    }

    public int getIcono(){
        return icono;
    }
}



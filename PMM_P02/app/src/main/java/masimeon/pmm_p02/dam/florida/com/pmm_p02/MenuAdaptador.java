package masimeon.pmm_p02.dam.florida.com.pmm_p02;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuAdaptador extends ArrayAdapter {

    private Context contexto;
    private ArrayList<item_menu> arraylist;
   // private ArrayList<ImageView> arraylist2;

    public MenuAdaptador(Context contexto, ArrayList<item_menu> arraylist) {
        super(contexto, 0, arraylist);

        this.contexto=contexto;
        this.arraylist=arraylist;
        //this.arraylist2=arraylist2;
    }

    public View getView (int posicion, View itemMenuView, ViewGroup parent){

        LayoutInflater inflater =  (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);

        //Nos aseguramos que existe el view para que no nos de un error
        if(itemMenuView==null){
            itemMenuView = inflater.inflate(R.layout.items_listview, parent, false);
        }

        //Capturamos la imagen y el texto de la lista de items para poder cambiarlos
        ImageView img = (ImageView) itemMenuView.findViewById(R.id.itemlista_img);
        TextView txt = (TextView) itemMenuView.findViewById(R.id.itemlista_txt);
        LinearLayout fondo = (LinearLayout) itemMenuView.findViewById(R.id.lista_items_menuppal);

        //Y los cambiamos si lo necesitamos
        txt.setText(arraylist.get(posicion).getTitulo());
        img.setImageResource(arraylist.get(posicion).getIcono());

        //Jugamos con el color de fondo del ítem
        switch (posicion){
            case 0:
                fondo.setBackgroundColor(Color.argb(150,245,245,100));
                //fondo.setBackgroundColor(Color.parseColor("#F0000F"));
                break;
            case 1:
                fondo.setBackgroundColor(Color.argb(150,103,245,100));
                break;
            case 2:
                fondo.setBackgroundColor(Color.argb(150,221,100,245));
                break;
            case 3:
                fondo.setBackgroundColor(Color.argb(150,255,0,0));
                break;
            default:
        }

        return itemMenuView;

    }
}

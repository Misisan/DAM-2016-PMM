package masimeon.add_03c.florida.add_act03c;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Miguel Ángel on 15/02/2017.
 */

public class AdapterExamen extends ArrayAdapter {

    private Context contexto;
    private Activity activity;
    private ArrayList<Todo> arraylist;
    private MyDBAdapter db;

    public AdapterExamen(Context contexto, Activity activity, ArrayList<Todo> arraylist, MyDBAdapter db) {
        super(contexto, 0, arraylist);
        this.activity=activity;
        this.contexto=contexto;
        this.arraylist=arraylist;
        this.db=db;
    }

    public View getView (final int posicion, View itemMenuView, ViewGroup parent){

        //Inflamos las views
        LayoutInflater inflater =  (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
        if(itemMenuView==null){
            itemMenuView = inflater.inflate(R.layout.items_listview, parent, false);
        }

        //Recuperamos la información del arraylist y lo montamos en el texto de la lista
        Integer id = arraylist.get(posicion).getID();
        String nombre = arraylist.get(posicion).getNombre();
        String tipo = arraylist.get(posicion).getTipo();

        TextView txt = (TextView) itemMenuView.findViewById(R.id.itemlista_txt);
        txt.setText(tipo+": "+id + " | " + nombre);


        txt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                PopupMenu menupopup = new PopupMenu(activity, v);
                menupopup.getMenuInflater().inflate(R.menu.menu_context, menupopup.getMenu());
                menupopup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.item_del:

                                if(arraylist.get(posicion).getTipo().equals("Prof")) {
                                    //Borramos el ítem seleccionado
                                    db.borrarProfesor(arraylist.get(posicion).getID());
                                    //Reconstruimos la activity para que se vean los cambios
                                    activity.recreate();
                                }

                                if(arraylist.get(posicion).getTipo().equals("Est")){
                                    //Borramos el ítem seleccionado
                                    db.borrarEstudiante(arraylist.get(posicion).getID());
                                    //Reconstruimos la activity para que se vean los cambios
                                    activity.recreate();
                                }
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                menupopup.show();
            }
        });
        return itemMenuView;
    }
}

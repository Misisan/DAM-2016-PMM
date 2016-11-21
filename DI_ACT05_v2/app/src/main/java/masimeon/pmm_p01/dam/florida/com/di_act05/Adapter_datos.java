package masimeon.pmm_p01.dam.florida.com.di_act05;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
  *  La magia está hecha con esta class que prepara el array para la listview
 **/

public class Adapter_datos extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Datos> items_listview;

    //En el constructor simplemente adquirimos los datos necesarios cuando se cree el objeto
    public Adapter_datos(Activity activity, ArrayList<Datos> items){
        this.activity=activity;
        this.items_listview=items;
    }

    /*Método necesario al extender el BaseAdapter, con ArrayAdapter no seria necesario*/
    @Override
    public int getCount() {
        return items_listview.size();
    }

    /*Método necesario al extender el BaseAdapter, con ArrayAdapter no seria necesario*/
    @Override
    public Object getItem(int i) {
        return items_listview.get(i);
    }

    /*Método necesario al extender el BaseAdapter, con ArrayAdapter no seria necesario*/
    @Override
    public long getItemId(int i) {
        return items_listview.get(i).getId();
    }

    //Método getView para obtener el xml, la lista y prepararlo para el ListView
    @Override
    public View getView(int i, final View view, ViewGroup viewGroup) {

        //Primero no s aseguramos que hay algo en la view que entra, y si no la inflamos
        View v = view;
        if(view==null){
            //Con el inflater alimentamos la vista con el lista_items.xml de ítems personalizados
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.lista_items, null);
        }

        //Cargamos del ArrayList el objeto con el que trabajar
        Datos date = items_listview.get(i);
        //Obtenemos el elemento gráfico de lista_items.xml al que modificar sus propiedades
        TextView txt = (TextView) v.findViewById(R.id.txtItem);

        //Ponemos el nombre de laa lista en el Text del ítem
        txt.setText(date.getNombre());
        // y en la ContentDescription para utilizarlo como título del ContextMenu
        txt.setContentDescription(date.getNombre());

        //A dicho TextView le asignamos un ContextMenu,
        //lo tenemos definido con sus métodos (onCreateContextMenu, onContextItemSelected)
        // en el Main Activity, esta asiganción podemos realizarla en el MainActivity si creamos o vinculamos
        //allí los ítems, pero al hacerlo aquí debemos ponerlo aquí
        activity.registerForContextMenu(txt);

        //Listener para el TextView recuperado
        txt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //Creamos un menú popup, lo vinculamos al elemeento que lo lanza y le ponemos el inflater para pillar el popup.xml
                PopupMenu menupopup = new PopupMenu(activity, v);
                menupopup.getMenuInflater().inflate(R.menu.popup, menupopup.getMenu());

                //Le ponemos el listener al item
                menupopup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.item_menuPopUp_01:
                                Toast.makeText(activity, "Si funcionara te mostraría", Toast.LENGTH_LONG).show();
                                return true;
                            case R.id.item_menuPopUp_02:
                                Toast.makeText(activity, "Si funcionara te eliminaría", Toast.LENGTH_LONG).show();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                //Mostrar el popup
                menupopup.show();
            }
        });
        return v;
    }

}

package masimeon.pmm_p01.dam.florida.com.di_act05;

import android.app.Activity;
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


    public Adapter_datos(Activity activity, ArrayList<Datos> items){
        this.activity=activity;
        this.items_listview=items;
    }

    @Override
    public int getCount() {
        return items_listview.size();
    }

    @Override
    public Object getItem(int i) {
        return items_listview.get(i);
    }

    @Override
    public long getItemId(int i) {
        return items_listview.get(i).getId();
    }

    @Override
    public View getView(int i, final View view, ViewGroup viewGroup) {
        View v = view;

        if(view==null){
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.lista_items, null);
        }

        Datos date = items_listview.get(i);
       TextView txt = (TextView) v.findViewById(R.id.txtItem);
        txt.setText(date.getNombre());

        activity.registerForContextMenu(txt);

        txt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Creamos un menú popup, lo vinculamos al elemeento que lo lanza y le ponemos el inflater
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

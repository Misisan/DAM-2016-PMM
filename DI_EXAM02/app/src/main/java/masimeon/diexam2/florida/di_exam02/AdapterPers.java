package masimeon.diexam2.florida.di_exam02;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class AdapterPers extends ArrayAdapter {

    private Context contexto;
    private Activity activity;
    private ArrayList<String> arraylist;

    public AdapterPers(Context contexto, Activity activity, ArrayList<String> arraylist) {
        super(contexto, 0, arraylist);
        this.activity=activity;
        this.contexto=contexto;
        this.arraylist=arraylist;
    }

    public View getView (final int posicion, View itemMenuView, ViewGroup parent){

        LayoutInflater inflater =  (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);

        if(itemMenuView==null){
            itemMenuView = inflater.inflate(R.layout.items_listview, parent, false);
        }

        TextView txt = (TextView) itemMenuView.findViewById(R.id.itemlista_txt);
        txt.setText(arraylist.get(posicion));


        txt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                PopupMenu menupopup = new PopupMenu(activity, v);
                menupopup.getMenuInflater().inflate(R.menu.context_menu, menupopup.getMenu());
                menupopup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.item_add:
                                Toast.makeText(activity, "Se añadirá detrás del "+String.valueOf(posicion), Toast.LENGTH_LONG).show();
                                return true;
                            case R.id.item_del:
                                Toast.makeText(activity, "Se eliminará el "+String.valueOf(posicion+1), Toast.LENGTH_LONG).show();
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

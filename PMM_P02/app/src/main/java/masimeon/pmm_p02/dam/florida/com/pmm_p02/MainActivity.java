package masimeon.pmm_p02.dam.florida.com.pmm_p02;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        //Array para alimentar al Adapter
        String[] txtOpc = new String[]{"Opc1","Opc2","Opc3"};

        //Como el Adaptador necesita una lista convertimos el array anterior a lista
        ArrayList<String> listaItemMenu = new ArrayList<String>(Arrays.asList(txtOpc));

        //Ahora creamos el adaptardor que cargue nustra lista en el ListView
        //ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaItemMenu);
        */

        //ArrayList para alimentar al adapter
        ArrayList<item_menu> listaItemMenu = new ArrayList<item_menu>();
        item_menu item01 = new item_menu("JUGAR", R.drawable.icon_dado);
        item_menu item02 = new item_menu("OPCIONES", R.drawable.icon_tuerca);
        item_menu item03 = new item_menu("AYUDA", R.drawable.icon_info);
        item_menu item04 = new item_menu("SALIR", R.drawable.icon_cruz);
        listaItemMenu.add(item01);
        listaItemMenu.add(item02);
        listaItemMenu.add(item03);
        listaItemMenu.add(item04);

        //Ahora creamos el adaptardor que cargue nuestra lista en el ListView
        MenuAdaptador adaptador = new MenuAdaptador(this, listaItemMenu);

        //Definismo la vista ListView y le conectador el adaptador
        final ListView list = (ListView) findViewById(R.id.LayoutCuerpo);
        list.setAdapter(adaptador);

        //Añadimos el Listener a la lista
        list.setOnItemClickListener(new listenerLista());

    }

    protected void onResume(){
        super.onResume();
    }

    protected void onPause(){
        super.onPause();
    }

    //Inner class para el listener de la listview
    private class listenerLista implements AdapterView.OnItemClickListener{

        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            Toast.makeText(MainActivity.this, "Click :)", Toast.LENGTH_SHORT).show();
        }

    }
}

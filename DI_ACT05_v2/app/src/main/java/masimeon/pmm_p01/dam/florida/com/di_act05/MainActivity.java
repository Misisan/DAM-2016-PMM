package masimeon.pmm_p01.dam.florida.com.di_act05;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializo la lista con la que trabajar
        lista = (ListView)findViewById(R.id.listview);

        //Creo el array de datos con el que trabajar
        ArrayList<Datos> ArrayDatos = new ArrayList<Datos>();

        //Relleno el array
        //Le pasamos el objeto Datos pq ahi tenemos definida toda la info,
        //aunque en este caso no sería necesario, podríamos pasarle directamente un string
        ArrayDatos.add(new Datos("Antonio"));
        ArrayDatos.add(new Datos("Carlos"));
        ArrayDatos.add(new Datos("Vladimir"));

        //Paso la información a la class Adapter_datos donde se atan todos los componentes
        Adapter_datos adap = new Adapter_datos(this, ArrayDatos);

        /* Vinculo el adaptador a la vista para que el ListView sepa de donde sacarlo todo */
        lista.setAdapter(adap);
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menu_info) {
        super.onCreateContextMenu(menu, v, menu_info);

        MenuInflater inflater = getMenuInflater();
        //  inflater.inflate(R.menu.context_menu, menu);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menu_info;

        //Cuando llamemos al menú pondremos de ContentDescription del TextView lo mismo que el Tittle
        //y así podremos poner como título del menú el nombre del item que llama
        menu.setHeaderTitle(v.getContentDescription().toString());

        /* Con todo esto le pasamos el context_menu.xml al inflate e inflamos */
        inflater.inflate(R.menu.context_menu, menu);

    }

    public boolean onContextItemSelected(MenuItem item) {
        //AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.item_menuContext_01:
                Toast.makeText(getApplicationContext(), "Has seleccionado la opción 1 de mostrar", Toast.LENGTH_LONG).show();
                return true;
            case R.id.item_menuContext_02:
                Toast.makeText(getApplicationContext(), "Has seleccionado la opción 2 de eliminar", Toast.LENGTH_LONG).show();
                return true;
            default:
                return false;
        }
    }

}

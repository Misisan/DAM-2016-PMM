package masimeon.diexam2.florida.di_exam02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;

/*
El menú está hecho con un PopUp Menu porque por alguna razón no he conseguido hacerlo con el
ContextMenu, el items_listview.xml no es totalmente necesario para este ejercicio, pero lo he incluido
para poder personalizar un poco mejor la parte visual de los view de la ListView
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listview = (ListView) findViewById(R.id.listview);

        String[] Nums = new String[]{"uno","dos","tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve", "diez"};
        ArrayList<String> listaNums = new ArrayList<String>(Arrays.asList(Nums));
        AdapterPers adaptador = new AdapterPers(this, this, listaNums);

        listview.setAdapter(adaptador);
    }

}

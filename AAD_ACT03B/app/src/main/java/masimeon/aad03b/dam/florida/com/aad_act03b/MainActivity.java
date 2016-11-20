package masimeon.aad03b.dam.florida.com.aad_act03b;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //String para las preferencias
    public static final String Preferencias = "Preferencias";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Traemos los elementos de la parte gráfica al código
        final Button btn = (Button) findViewById(R.id.btn);
        final EditText nombre = (EditText) findViewById(R.id.txtNombre);
        final EditText dni = (EditText) findViewById(R.id.txtDNI);
        final EditText nac = (EditText) findViewById(R.id.txtNac);
        final EditText sexo = (EditText) findViewById(R.id.txtSex);

        //Listener para el botón
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Creamos el objeto para tener referencias compartidas dentro de esta app
                SharedPreferences misPrefs = getSharedPreferences(Preferencias, Activity.MODE_PRIVATE);

                // Cargamos un editor para modificarlas y le decimos cuales queremos editar
                SharedPreferences.Editor editarPrefs = misPrefs.edit();

                // Le pasamos los nuevos valores
                editarPrefs.putString("PrefNombre", String.valueOf(nombre.getText()));
                editarPrefs.putString("PrefDNI", String.valueOf(dni.getText()));
                editarPrefs.putString("PrefNac", String.valueOf(nac.getText()));
                editarPrefs.putString("PrefSex", String.valueOf(sexo.getText()));

                //Chuleta para los distintos tipos de variables
                //objEditor.putTipoVariable("ID variable", valor);
                /*editarPrefs.putBoolean("isTrue",true);
                editarPrefs.putFloat("lastFloat", (float) 1.5);
                editarPrefs.putInt("wholeNumber",12);
                editarPrefs.putLong("aNumber",2);
                editarPrefs.putString("textEntryValue","mensaje");*/

                // Guardamos los cambios terminado la edición
                editarPrefs.commit();

                //Llamamos al 2o Main y lo iniciamos
                Intent llamadaActivity = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(llamadaActivity);
            }
        });
    }
}

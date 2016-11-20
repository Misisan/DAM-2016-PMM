package masimeon.aad03b.dam.florida.com.aad_act03b;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import static masimeon.aad03b.dam.florida.com.aad_act03b.MainActivity.Preferencias;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Traemos los objetos del entorno gráfico al código
        final TextView nombre = (TextView) findViewById(R.id.txtRecNombre);
        final TextView dni = (TextView) findViewById(R.id.txtRecDNI);
        final TextView nac = (TextView) findViewById(R.id.txtRecNac);
        final TextView sex = (TextView) findViewById(R.id.txtRecSex);

        // Creamos el objeto de las referencias compartidas dentro de esta app
        SharedPreferences misPrefs = getSharedPreferences(Preferencias, Activity.MODE_PRIVATE);

        // Con el objeto de Preferencias creado ya podemos llamarlo para recuperar la información
        nombre.setText(misPrefs.getString("PrefNombre", ""));
        dni.setText(misPrefs.getString("PrefDNI", ""));
        nac.setText(misPrefs.getString("PrefNac", ""));
        sex.setText(misPrefs.getString("PrefSex", ""));
    }

}

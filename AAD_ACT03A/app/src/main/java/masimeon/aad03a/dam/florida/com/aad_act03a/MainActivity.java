package masimeon.aad03a.dam.florida.com.aad_act03a;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private String archivo = "archivomemo.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creamos los objetos necesarios para instanciarlos a los elementos de la
        //parte visual con los que vamos a interactuar
        final EditText txt = (EditText) findViewById(R.id.texto);
        final Button btn_add = (Button) findViewById(R.id.btn_add);
        final Button btn_show = (Button) findViewById(R.id.btn_show);
        final TextView txtshow = (TextView) findViewById(R.id.txt_show);

        //Listener para el botón de añadir
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Comprobamos que el texto no está vacio
                if((int) txt.length()>0){

                    //Forzamos que se oculte el teclado
                    InputMethodManager controlTeclado = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    controlTeclado.hideSoftInputFromWindow(txt.getWindowToken(), 0);

                    //Guardamos el texto introducido concatenado con el anteriormente guardado
                    guardarinfo(mostrarinfo()+"\n"+String.valueOf(txt.getText()));
                    Toast.makeText(MainActivity.this, "Texto guardado", Toast.LENGTH_SHORT).show();

                    //Dejamos los texto vacios para un mejor funcionamiento visual
                    txt.setText("");
                    txtshow.setText("");
                }else{
                    Toast.makeText(MainActivity.this, "Debes introducir algún texto", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtshow.setText(mostrarinfo());
                Toast.makeText(MainActivity.this, "Texto cargado", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void guardarinfo(String txt){

        try {
            //Creamos el objeto del archivo donde vamos a guardar
            FileOutputStream archivoSalida = openFileOutput(archivo, Context.MODE_PRIVATE);
            DataOutputStream datosAgrabar = new DataOutputStream(archivoSalida);

            //Grabamos los datos en el archivo
            datosAgrabar.writeBytes(txt);

            //Cerramos el archivo
            archivoSalida.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String mostrarinfo(){

        try {
            //Creamos el objeto para el archivo de donde cargaremos la info
            FileInputStream archivoEntrada = openFileInput(archivo);
            InputStreamReader datosEntrada = new InputStreamReader(archivoEntrada);

            char[] BufferEntrada = new char[100];
            String textoLeido = "";

            int cantidadDatosLeidos;
            //Si hay algún dato entonces guardalo en el String
            while((cantidadDatosLeidos = datosEntrada.read(BufferEntrada)) > 0){
                // Convertimos los datos leidos en Strings
                String datosLeidos = String.copyValueOf(BufferEntrada, 0, cantidadDatosLeidos);
                textoLeido = datosLeidos;
            }

           return textoLeido;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Vacio1";
    }
}


package masimeon.add_03c.florida.add_act03c;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EstNuevoActivity extends AppCompatActivity {

    private EditText nombre;
    private EditText edad;
    private EditText ciclo;
    private EditText curso;
    private EditText nota;
    private MyDBAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_est_nuevo);

        //Vinculamos los textos introducidos en variables
        Button crear = (Button) findViewById(R.id.btn_crearEstudiante);
        nombre = (EditText) findViewById(R.id.txt_newNombre);
        edad = (EditText) findViewById(R.id.txt_newEdad);
        ciclo = (EditText) findViewById(R.id.txt_newCiclo);
        curso = (EditText) findViewById(R.id.txt_newCurso);
        nota = (EditText) findViewById(R.id.txt_newNota);
        db = new MyDBAdapter(this);

        crear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){

                //Convertimos los datos a String para poder utilizarla en la clase Estudiante
                String n = nombre.getText().toString();
                String e = edad.getText().toString();
                String ci = ciclo.getText().toString();
                String cu = curso.getText().toString();
                String nt = nota.getText().toString();

                //Compronamos que no haya nada vacío antes
                if (!TextUtils.isEmpty(n)||!TextUtils.isEmpty(e)||!TextUtils.isEmpty(ci)||!TextUtils.isEmpty(cu)||!TextUtils.isEmpty(nt)){
                    try{
                        //Creamos el objeto estudiante y lanzamos el método que lo inserta en la BBDD
                        Estudiante est = new Estudiante( n, Integer.parseInt(e), ci, cu, Integer.parseInt(nt));
                        db.insertarEstudiante(est);
                        Toast.makeText(EstNuevoActivity.this, "Creación exitosa", Toast.LENGTH_SHORT).show();
                        //Cerramos la activity
                        finish();
                    }catch(Exception ex){
                        //Utilizamos este cath para controlar errores, podríamos poden otro IF para dejar este catch por si falla la creación
                        Toast.makeText(EstNuevoActivity.this, "Debes introducir números en edad y/o nota media", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(EstNuevoActivity.this, "Falta introducir información necesaria", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

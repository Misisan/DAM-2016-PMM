package masimeon.add_03c.florida.add_act03c;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfNuevoActivity extends AppCompatActivity {

    private EditText nombre;
    private EditText edad;
    private EditText ciclo;
    private EditText curso;
    private EditText desp;
    private MyDBAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_nuevo);

        //Vinculamos los textos introducidos en variables
        Button crear = (Button) findViewById(R.id.btn_crearProfesor);
        nombre = (EditText) findViewById(R.id.txt_newNombreP);
        edad = (EditText) findViewById(R.id.txt_newEdadP);
        ciclo = (EditText) findViewById(R.id.txt_newCicloP);
        curso = (EditText) findViewById(R.id.txt_newCursoP);
        desp = (EditText) findViewById(R.id.txt_newNotaP);
        db = new MyDBAdapter(this);

        crear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){

                //Convertimos los datos a String para poder utilizarla en la clase Estudiante
                String n = nombre.getText().toString();
                String e = edad.getText().toString();
                String ci = ciclo.getText().toString();
                String cu = curso.getText().toString();
                String d = desp.getText().toString();

                //Compronamos que no haya nada vacío antes
                if (!TextUtils.isEmpty(n)||!TextUtils.isEmpty(e)||!TextUtils.isEmpty(ci)||!TextUtils.isEmpty(cu)||!TextUtils.isEmpty(d)){
                    try{
                        //Creamos el objeto estudiante y lanzamos el método que lo inserta en la BBDD
                        Profesor prof = new Profesor( n, Integer.parseInt(e), ci, cu, d);
                        db.insertarProfesor(prof);
                        Toast.makeText(ProfNuevoActivity.this, "Creación exitosa", Toast.LENGTH_SHORT).show();
                        //Cerramos la activity
                        finish();
                    }catch(Exception ex){
                        //Utilizamos este cath para controlar errores, podríamos poden otro IF para dejar este catch por si falla la creación
                        Toast.makeText(ProfNuevoActivity.this, "Debes introducir números en edad", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(ProfNuevoActivity.this, "Falta introducir información necesaria", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

package masimeon.add_03c.florida.add_act03c;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ToggleButton togEst;
    private ToggleButton togProf;
    private Spinner curso;
    private Spinner ciclo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Vinculamos los elementos del layout
        curso = (Spinner) findViewById(R.id.combo_curso);
        ciclo = (Spinner) findViewById(R.id.combo_ciclo);
        Button buscar = (Button) findViewById(R.id.btn_buscar);
        Button addEST = (Button) findViewById(R.id.btn_crearEST);
        Button addPROF = (Button) findViewById(R.id.btn_crearPROF);
        togEst = (ToggleButton) findViewById(R.id.tg_est);
        togProf = (ToggleButton) findViewById(R.id.tg_prof);

        Button examen = (Button) findViewById(R.id.btn_examen);

        //Conectamos con el adaptador de la BBDD
        MyDBAdapter db = new MyDBAdapter(this);

        //Rellenamos el combo para el filtro por curso
        ArrayList<String> comboCurso = db.rellenarComboCurso();
        ArrayAdapter<String> comboCursoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, comboCurso);
        comboCursoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        curso.setAdapter(comboCursoAdapter);

        //Rellenamos el combo para el filtro ciclo
        ArrayList<String> comboCiclo = db.rellenarComboCiclo();
        ArrayAdapter<String> comboCicloAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, comboCiclo);
        comboCicloAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ciclo.setAdapter(comboCicloAdapter);


        examen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent llamada_app = new Intent(MainActivity.this, ExamenActivity.class);
                startActivity(llamada_app);
            }
        });

        //Listener para el botón de buscar
        buscar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){


                if (togEst.isChecked() && !togProf.isChecked()){
                    Intent llamada_app = new Intent(MainActivity.this, EstudiantesActivity.class);
                    //Enviamos los filtros de búsqueda seleccionados a la siguiente activity
                    llamada_app.putExtra("curso", curso.getItemAtPosition(curso.getSelectedItemPosition()).toString());
                    llamada_app.putExtra("ciclo", ciclo.getItemAtPosition(ciclo.getSelectedItemPosition()).toString());
                    startActivity(llamada_app);
                }

                if (!togEst.isChecked() && togProf.isChecked()){
                    Intent llamada_app = new Intent(MainActivity.this, ProfesoresActivity.class);
                    //Enviamos los filtros de búsqueda seleccionados a la siguiente activity
                    llamada_app.putExtra("curso", curso.getItemAtPosition(curso.getSelectedItemPosition()).toString());
                    llamada_app.putExtra("ciclo", ciclo.getItemAtPosition(ciclo.getSelectedItemPosition()).toString());
                    startActivity(llamada_app);
                }

                if (togEst.isChecked() && togProf.isChecked()){
                    Intent llamada_app = new Intent(MainActivity.this, TodoActivity.class);
                    //Enviamos los filtros de búsqueda seleccionados a la siguiente activity
                    llamada_app.putExtra("curso", curso.getItemAtPosition(curso.getSelectedItemPosition()).toString());
                    llamada_app.putExtra("ciclo", ciclo.getItemAtPosition(ciclo.getSelectedItemPosition()).toString());
                    startActivity(llamada_app);
                }

                if (!togEst.isChecked() && !togProf.isChecked()){
                    Toast.makeText(MainActivity.this, "Debes seleccionar alguna opción", Toast.LENGTH_LONG).show();
                }


            }
        });

        //Listener para el botón de añadir un nuevo estudiante
        addEST.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                Intent llamada_app = new Intent(MainActivity.this, EstNuevoActivity.class);
                startActivity(llamada_app);
            }
        });

        //Listener para el botón de añadir un nuevo profesor
        addPROF.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                Intent llamada_app = new Intent(MainActivity.this, ProfNuevoActivity.class);
                startActivity(llamada_app);
            }
        });


    }

    @Override
    protected void onResume(){
        super.onResume();

        //MainActivity.this.recreate();
    }

}
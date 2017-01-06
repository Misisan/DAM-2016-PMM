package masimeon.add_03c.florida.add_act03c;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class EstudiantesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiantes);

        //Recibimos los filtros seleccionados
        Intent intent = getIntent();
        String curso = intent.getStringExtra("curso");
        String ciclo = intent.getStringExtra("ciclo");

        MyDBAdapter db = new MyDBAdapter(this);

        //Cargamos el listview de estudiantes
        ListView listview = (ListView) findViewById(R.id.listview_estudiantes);
        ArrayList<Estudiante> estudiantes = db.obtenerEstudiantes(curso, ciclo);
        AdapterEstudiantes adaptador = new AdapterEstudiantes(this, this, estudiantes, db);
        listview.setAdapter(adaptador);
    }
}

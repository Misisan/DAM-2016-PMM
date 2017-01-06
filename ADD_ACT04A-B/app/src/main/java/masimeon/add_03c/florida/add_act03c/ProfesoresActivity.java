package masimeon.add_03c.florida.add_act03c;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ProfesoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesores);

        //Recibimos los filtros seleccionados
        Intent intent = getIntent();
        String curso = intent.getStringExtra("curso");
        String ciclo = intent.getStringExtra("ciclo");

        MyDBAdapter db = new MyDBAdapter(this);

        //Cargamos el ListView
        ListView listview = (ListView) findViewById(R.id.listview_profesores);
        ArrayList<Profesor> prof = db.obtenerProfesores(curso, ciclo);
        AdapterProfesor adaptador = new AdapterProfesor(this, this, prof, db);
        listview.setAdapter(adaptador);
    }
}

package masimeon.add_03c.florida.add_act03c;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ExamenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examen);

        MyDBAdapter db = new MyDBAdapter(this);

        //Cargamos el ListView
        ListView listview = (ListView) findViewById(R.id.listview_examen);

        String letra = "P";

        ArrayList<Profesor> prof = db.obtenerProfesoresExamen(letra);
        ArrayList<Estudiante> est = db.obtenerEstudiantesExamen(letra);
        ArrayList<Todo> todo = new ArrayList<Todo>();

        //Recorremos el array de Profesores y lo cargamos en el de Todo
        for(int i=0;i<prof.size();i++) {
            Todo t = new Todo(prof.get(i).getNombre(), prof.get(i).getID(), "Prof");
            todo.add(t);
        }

        //Recorremos el array de Estudiantes y lo cargamos en el de Todo
        for(int i=0;i<est.size();i++) {
            Todo t = new Todo(est.get(i).getNombre(), est.get(i).getID(), "Est");
            todo.add(t);
        }

        //Cargamos el listview
        AdapterExamen adaptador = new AdapterExamen(this, this, todo, db);
        listview.setAdapter(adaptador);
    }
}

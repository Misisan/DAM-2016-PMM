package masimeon.add_03c.florida.add_act03c;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class TodoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        MyDBAdapter db = new MyDBAdapter(this);

        //Recibimos los filtros seleccionados
        Intent intent = getIntent();
        String curso = intent.getStringExtra("curso");
        String ciclo = intent.getStringExtra("ciclo");

        //Cargamos el ListView
        ListView listview = (ListView) findViewById(R.id.listview_todo);
        ArrayList<Profesor> prof = db.obtenerProfesores(curso, ciclo);
        ArrayList<Estudiante> est = db.obtenerEstudiantes(curso, ciclo);
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
        AdapterTodo adaptador = new AdapterTodo(this, this, todo, db);
        listview.setAdapter(adaptador);
    }
}

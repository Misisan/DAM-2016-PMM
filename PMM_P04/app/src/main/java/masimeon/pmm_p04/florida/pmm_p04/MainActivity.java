package masimeon.pmm_p04.florida.pmm_p04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FragmentMenu.ListFragmentListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Escondemos la barra de títulos de esta Activity
        getSupportActionBar().hide();

        if(findViewById(R.id.fragment_contenido) != null) {
            if (savedInstanceState != null) {
                return;
            }

            //Declaramos los fragment a cargar
            FragmentWelcome frag1 = new FragmentWelcome();

            frag1.setArguments(getIntent().getExtras());

            //Asignamos los fragments declarados al fragment contenedor del layout
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_contenido, frag1).commit();
            //En versiones más nuevas de API11:
            //getFragmentManager().beginTransaction().add(R.id.fragment_contenido, frag1).commit();
        }
    }

    public void onListSelected(int position, item_menu item){
        Toast.makeText(this, item.getTitulo(), Toast.LENGTH_SHORT).show();
    }

}

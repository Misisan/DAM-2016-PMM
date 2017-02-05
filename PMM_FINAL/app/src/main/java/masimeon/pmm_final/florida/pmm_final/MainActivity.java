package masimeon.pmm_final.florida.pmm_final;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FragmentMenu.ListFragmentListener, FragmentPerfil.UserListener{

    private FragmentWelcome frag_wel;
    private FragmentPerfil frag_perf;
    private FragmentJuego frag_joc;
    private FragmentListaJ frag_listaJ;
    private Jugador j;
    private MyDBAdapter db;
    private MediaPlayer sonido;
    private MediaPlayer sonido_clickMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Escondemos la barra de títulos de esta Activity
        getSupportActionBar().hide();

        //Cargamos la canción de fondo************************************************************************/
        sonido = MediaPlayer.create(this, R.raw.fondo);
        sonido_clickMenu = MediaPlayer.create(this,R.raw.click);
        sonido_clickMenu.setVolume(10,10);
        sonido.start();
        sonido.setVolume(3,3);
        sonido.setLooping(true);
        /***************************************************************************************************/
        //Creamos al objeto jugador por defecto
        j = new Jugador("Desconocido", "Desconocido");
        j.setId(0);
        j.setPuntos(0);

        db = new MyDBAdapter(this);

        //Si no hay ningún fragment cargado cargamos el de bienvenida
        if(findViewById(R.id.fragment_contenido) != null) {
            if (savedInstanceState != null) {
                return;
            }
            bienvenida();
        }
    }

    //Listener para los botones del menú
    public void onListSelected(int position, item_menu item){
        sonido_clickMenu.start();
        switch(position){
            case 0: //Botón juego
                juego();
                break;
            case 1: //Botón de Perfil
                perfil();
                break;
            case 2: //Botón lista de jugadores
                lista();
                break;
            case 3:
                Jugador j = db.obtenerJugadorActivo();
                Toast.makeText(this, "Sal tu solo, que ya eres mayorcito ;)", Toast.LENGTH_SHORT).show();
                break;
            default:
                bienvenida();
        }
    }

    //Listener para el botón de perfil
    public void onUserListener(Jugador j){
        //Recogemos el jugador creado en perfil y volvemos al fragment de bienvenida
        this.j=j;
        Toast.makeText(this, "Bienvenido "+j.getNombre(), Toast.LENGTH_SHORT).show();
        bienvenida();
    }

    //Fragment de la pantalla de bienvenida
    public void bienvenida(){
        //Declaramos el fragment a cargar
       if(frag_wel == null) {
           //Como el fragment aún no está creado lo creamos
            frag_wel = new FragmentWelcome();
            //frag_wel.setArguments(getIntent().getExtras());

            //Asignamos los fragments declarados al fragment contenedor del layout
                //getSupportFragmentManager().beginTransaction().add(R.id.fragment_contenido, frag_wel).commit();
                //En versiones más nuevas de API11:
                //getFragmentManager().beginTransaction().add(R.id.fragment_contenido, frag_wel).commit();
            FragmentTransaction frag = getSupportFragmentManager().beginTransaction();
            frag.add(R.id.fragment_contenido, frag_wel);
            frag.commit();

        }else{
           //Iniciamos el manager de fragments
           FragmentTransaction frag = getSupportFragmentManager().beginTransaction();
           //Indicamos el fragment y el contenido a reemplazar
           frag.replace(R.id.fragment_contenido, frag_wel);
           //Reemplazamos
           frag.commit();
       }
    }

    //Fragment de la pantalla de perfil
    public void perfil(){
            //Este fragment siempre queremos que se cree nuevo para poder insertar múltiples usuarios
            frag_perf = new FragmentPerfil();
            FragmentTransaction frag = getSupportFragmentManager().beginTransaction();
            frag.add(R.id.fragment_contenido, frag_perf);
            //Indcamos la opción de poder volver atrás
            frag.addToBackStack(null);
            frag.commit();
    }

    //Fragment de la pantalla de lista de jugadores
    public void lista(){
        if(frag_listaJ==null) {
            frag_listaJ = new FragmentListaJ();
            FragmentTransaction frag = getSupportFragmentManager().beginTransaction();
            frag.add(R.id.fragment_contenido, frag_listaJ);
            //Indcamos la opción de poder volver atrás
            frag.addToBackStack(null);
            frag.commit();
        }else{
            FragmentTransaction frag = getSupportFragmentManager().beginTransaction();
            frag.replace(R.id.fragment_contenido, frag_listaJ);
            //Indcamos la opción de poder volver atrás
            frag.addToBackStack(null);
            frag.commit();
        }
    }

    public void juego(){
        if(frag_joc==null) {
            frag_joc = new FragmentJuego();
            FragmentTransaction frag = getSupportFragmentManager().beginTransaction();
            //Metemos el Bundle dentro del fragment
           /* frag_joc.setArguments(infoJugador());*/
            //Metemos el fragment dentro del containder
            frag.add(R.id.fragment_contenido, frag_joc);
            //Añadimos la opción de volver atrás
            frag.addToBackStack(null);
            //Ejecutamos todo
            frag.commit();
        }else{
            FragmentTransaction frag = getSupportFragmentManager().beginTransaction();
            /*frag_joc.setArguments(infoJugador());*/
            frag.replace(R.id.fragment_contenido, frag_joc);
            frag.addToBackStack(null);
            frag.commit();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sonido.release();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sonido.start();
        sonido.setLooping(true);
    }
}

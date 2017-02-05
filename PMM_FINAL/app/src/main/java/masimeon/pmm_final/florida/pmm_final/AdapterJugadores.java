package masimeon.pmm_final.florida.pmm_final;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class AdapterJugadores extends ArrayAdapter {

    private Context contexto;
    private Activity activity;
    private ArrayList<Jugador> arraylist;
    private MyDBAdapter db;
    private MediaPlayer click;
    private MediaPlayer clickBorrar;

    //Variable callback para que desde aquí se pueda llamar al método implementado en el main
    public FragmentPerfil.UserListener mCallback;

    public interface UserListener {
        public void onUserListener(Jugador j);
    }


    public AdapterJugadores(Context contexto, Activity activity, ArrayList<Jugador> arraylist, MyDBAdapter db) {
        super(contexto, 0, arraylist);

        this.activity=activity;
        this.contexto=contexto;
        this.arraylist=arraylist;
        this.db=db;
    }

    public View getView (final int posicion, View v, ViewGroup parent){

        //Inflamos las views
        LayoutInflater inflater =  (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
        if(v==null){
            v = inflater.inflate(R.layout.items_listview_jugadores, parent, false);
        }

        click = MediaPlayer.create(getContext(), R.raw.click);
        click.setVolume(10,10);

        clickBorrar = MediaPlayer.create(getContext(), R.raw.basura);
        clickBorrar.setVolume(10,10);

        //Recuperamos la información del arraylist y lo montamos en el texto de la lista
        Integer id = arraylist.get(posicion).getId();
        String nombre = arraylist.get(posicion).getNombre();
        String nick = arraylist.get(posicion).getNick();
        Integer puntos = arraylist.get(posicion).getPuntos();
        String direccimg = arraylist.get(posicion).getImg();

        //Enlazamos con los elementos visuales
        TextView txt = (TextView) v.findViewById(R.id.itemlistajugadores_txt);
        TextView txt2 = (TextView) v.findViewById(R.id.itemlistajugadores_txt2);
        TextView txtP = (TextView) v.findViewById(R.id.itemlistajugadores_txt_puntos);
        LinearLayout todo = (LinearLayout) v.findViewById(R.id.item_listajugadores_todo);
        ImageView img = (ImageView) v.findViewById(R.id.item_listajugadores_img);
        if (direccimg != null) {
            img.setImageURI(Uri.parse(direccimg));
        }else{
            img.setImageResource(R.drawable.cabeza);
        }

        //Realizamos unas pequeñas modificaciones
        txt.setText(nick);
        txt.setBackgroundColor(Color.argb(150,255,255,255));
        txt.setTextColor(Color.WHITE);
        txt2.setText("Nombre: "+nombre);
        txt2.setBackgroundColor(Color.argb(150,255,255,255));
        txt2.setTextColor(Color.WHITE);
        txtP.setText(puntos+" pts");
        txtP.setBackgroundColor(Color.argb(150,255,255,255));
        txtP.setTextColor(Color.WHITE);

        if(db.esElJugadorActivo(id)) {
            txt.setBackgroundColor(Color.argb(255,249,255,40));
            txt.setTextColor(Color.BLACK);
            txt2.setBackgroundColor(Color.argb(255,249,255,40));
            txt2.setTextColor(Color.BLACK);
            txtP.setBackgroundColor(Color.argb(255,249,255,40));
            txtP.setTextColor(Color.BLACK);
        }


        todo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                PopupMenu menupopup = new PopupMenu(activity, v);
                menupopup.getMenuInflater().inflate(R.menu.menu_context, menupopup.getMenu());
                menupopup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.item_del:
                                clickBorrar.start();
                                //Borramos el ítem seleccionado
                                db.borrarJugador(arraylist.get(posicion).getId());
                                //Reconstruimos la activity para que se vean los cambios
                                activity.recreate();
                                return true;

                            case R.id.item_selecc:
                                click.start();
                                db.limpiarJugador();
                                db.seleccionarJugador(arraylist.get(posicion).getId());
                                Jugador rescatado = db.obtenerJugadorActivo();
                                Toast.makeText(getContext(), "Jugador "+rescatado.getNick()+" ("+rescatado.getNombre()+") seleccionado", Toast.LENGTH_SHORT).show();
                                activity.recreate();
                                return true;
                            case R.id.item_refresh:
                                click.start();
                                db.limpiarJugador();
                                activity.recreate();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                menupopup.show();
            }
        });
        return v;
    }
}


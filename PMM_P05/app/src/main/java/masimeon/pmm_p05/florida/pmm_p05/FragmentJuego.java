package masimeon.pmm_p05.florida.pmm_p05;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentJuego extends Fragment {

    //Definimos los parámetros que revibirá el fragment de la activity
    static final String JUGADOR_ACT = "jugador_act";
    static final String PUNTOS_ACT="puntos_act";
    private String jugador_act;
    private String puntos_act;

    public FragmentJuego() {}
/*
    public static FragmentJuego newInstance(String param1, String param2) {
        //Se crea el fragment
        FragmentJuego fragment = new FragmentJuego();
        //Se crea el BUndle para recoger la info
        Bundle args = new Bundle();
        //Se encapsula la info dentro del Bundle
        args.putString(JUGADOR_ACT, param1);
        //Se mete el Bundle con la info dentro del fragment creado
        fragment.setArguments(args);

        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //Si el parámetro no es nulo se carga la infor en la variable
            jugador_act = getArguments().getString(JUGADOR_ACT);
            puntos_act = getArguments().getString(PUNTOS_ACT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflamos el layout para este fragment
        View v = inflater.inflate(R.layout.fragment_juego, container, false);

        //Vinculamos los elementos gráficos sobre los que trabajar
        TextView nick = (TextView) v.findViewById(R.id.joc_txtnick);
        TextView puntos = (TextView) v.findViewById(R.id.joc_txtpuntos);

        //Ponemos la info recuperada el los textviews
        nick.setText(jugador_act);
        puntos.setText("Puntos: "+puntos_act);

        return v;
    }

    public void onButtonPressed(Uri uri) {}

    @Override
    public void onAttach(Context context) {super.onAttach(context);}

    @Override
    public void onDetach() {super.onDetach();}
}

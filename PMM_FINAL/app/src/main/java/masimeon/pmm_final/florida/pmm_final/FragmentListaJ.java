package masimeon.pmm_final.florida.pmm_final;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class FragmentListaJ extends Fragment {
    private  MyDBAdapter db;

    //Variable callback para que desde aquí se pueda llamar al método implementado en el main
    public FragmentPerfil.UserListener mCallback;

    public interface UserListener {
        public void onUserListener(Jugador j);
    }

    public FragmentListaJ() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflamos el view
        View v = inflater.inflate(R.layout.fragment_lista_j, container, false);

        db = new MyDBAdapter(getContext());

        //Cargamos el listview de jugadores
        ListView listview = (ListView) v.findViewById(R.id.listview_jugadores);
        ArrayList<Jugador> estudiantes = db.obtenerJugadores();
        AdapterJugadores adaptador = new AdapterJugadores(getContext(), getActivity(), estudiantes, db);
        listview.setAdapter(adaptador);

        return v;
    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void onButtonPressed(Uri uri) {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mCallback = (FragmentPerfil.UserListener) getActivity();
        }catch(ClassCastException e){
            throw new ClassCastException(getActivity().toString()+"debe implementar listenerUser");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}

package masimeon.pmm_p05.florida.pmm_p05;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class FragmentPerfil extends Fragment {

    private EditText nombre;
    private EditText nick;

    //Variable callback para que desde aquí se pueda llamar al método implementado en el main
    public UserListener mCallback;

    public interface UserListener {
        public void onUserListener(Jugador j);
    }

    public FragmentPerfil() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflamos el view
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        //Vinculamos los elementos visuales
      /*  nombre = (EditText) v.findViewById(R.id.perfil_txtNombre);
        nick = (EditText) v.findViewById(R.id.perfil_txtNick);
        Button btnadd = (Button) v.findViewById(R.id.perfil_btnadd);
        btnadd.setOnClickListener(new listenerUser());*/
        return v;
    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Vinculamos los elementos visuales
        nombre = (EditText) getActivity().findViewById(R.id.perfil_txtNombre);
        nick = (EditText) getActivity().findViewById(R.id.perfil_txtNick);
        Button btnadd = (Button) getActivity().findViewById(R.id.perfil_btnadd);
        btnadd.setOnClickListener(new listenerUser());

    }

    public void onButtonPressed(Uri uri) {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mCallback = (UserListener) getActivity();
        }catch(ClassCastException e){
            throw new ClassCastException(getActivity().toString()+"debe implementar listenerUser");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    //Inner class para el listener de la listview
    private class listenerUser implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Jugador j = new Jugador(nombre.getText().toString(), nick.getText().toString());
            mCallback.onUserListener(j);
        }

    }

}

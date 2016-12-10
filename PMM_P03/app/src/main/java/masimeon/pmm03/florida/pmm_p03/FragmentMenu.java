package masimeon.pmm03.florida.pmm_p03;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FragmentMenu extends Fragment {

    ListFragmentListener mCallback;

    public interface ListFragmentListener {
        public void onListSelected(int position,String item);
    }

    public FragmentMenu() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }


    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //ArrayList para alimentar al adapter
        ArrayList<item_menu> listaItemMenu = new ArrayList<item_menu>();
        item_menu item01 = new item_menu("JUGAR", R.drawable.icon_dado);
        item_menu item02 = new item_menu("OPCIONES", R.drawable.icon_tuerca);
        item_menu item03 = new item_menu("AYUDA", R.drawable.icon_info);
        item_menu item04 = new item_menu("SALIR", R.drawable.icon_cruz);
        listaItemMenu.add(item01);
        listaItemMenu.add(item02);
        listaItemMenu.add(item03);
        listaItemMenu.add(item04);

        //Ahora creamos el adaptardor que cargue nuestra lista en el ListView
        MenuAdaptador adaptador = new MenuAdaptador(getActivity(), listaItemMenu);

        //Definismo la vista ListView y le conectador el adaptador
        final ListView list = (ListView) getActivity().findViewById(R.id.LayoutCuerpo);
        list.setAdapter(adaptador);

        //Añadimos el Listener a la lista
        list.setOnItemClickListener(new listenerLista());
    }

    //Inner class para el listener de la listview
    private class listenerLista implements AdapterView.OnItemClickListener{

        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            Toast.makeText(getActivity(), "Click :)", Toast.LENGTH_SHORT).show();
        }

    }
}

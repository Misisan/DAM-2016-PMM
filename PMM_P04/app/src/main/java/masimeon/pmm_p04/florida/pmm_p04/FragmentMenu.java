package masimeon.pmm_p04.florida.pmm_p04;

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

    //Variable callback para que desde aquí se pueda llamar al método implementado en el main
    public ListFragmentListener mCallback;

    public interface ListFragmentListener {
        public void onListSelected(int position, item_menu item);
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

        try{
            mCallback = (ListFragmentListener) getActivity();
        }catch(ClassCastException e){
            throw new ClassCastException(getActivity().toString()+"debe implementar OnHeadlineSelectedListener");
        }
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
        AdaptadorMenu adaptador = new AdaptadorMenu(getActivity(), listaItemMenu);

        //Definismo la vista ListView y le conectador el adaptador
        final ListView list = (ListView) getActivity().findViewById(R.id.LayoutCuerpo);
        list.setAdapter(adaptador);

        //Añadimos el Listener a la lista
        list.setOnItemClickListener(new listenerLista());
    }

    //Inner class para el listener de la listview
    private class listenerLista implements AdapterView.OnItemClickListener{

        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            item_menu item = (item_menu) parent.getItemAtPosition(position);
            mCallback.onListSelected(position, item);
        }

    }
}

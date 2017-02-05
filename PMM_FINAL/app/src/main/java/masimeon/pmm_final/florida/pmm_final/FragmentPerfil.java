package masimeon.pmm_final.florida.pmm_final;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.ColorInt;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FragmentPerfil extends Fragment {

    private EditText nombre;
    private EditText nick;
    private String DireccImg;
    private MediaPlayer clickMenu;;
    /******************************************************************************************************/
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Uri fileUri;
    /*****************************************************************************************************/

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

        return v;
    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Vinculamos los elementos visuales
        nombre = (EditText) getActivity().findViewById(R.id.perfil_txtNombre);
        nick = (EditText) getActivity().findViewById(R.id.perfil_txtNick);
        ImageButton btnadd = (ImageButton) getActivity().findViewById(R.id.perfil_btnadd);
        btnadd.setOnClickListener(new listenerUser());

        clickMenu = MediaPlayer.create(getContext(), R.raw.click);
        clickMenu.setVolume(10,10);

/*********************************************************************************************************************************/
        ImageButton b = (ImageButton) getActivity().findViewById(R.id.cam);
        b.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickMenu.start();
                // create Intent to take a picture and return control to the calling application
                Intent camara = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
                //Le pasamos al intent camara donde se guardará la imagen
                //camara.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name
                camara.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, fileUri);
                // start the image capture Intent
                //startActivityForResult(camara, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                //Lanzamos el intent de la camara, que es una activity,
                // desde la activity en la que hemos cargado el fragment
                getActivity().startActivityFromFragment(FragmentPerfil.this, camara, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            }
        });
        /************************************************************************************************************************/
    }

    //Cámara************************************************************************************************************/
    private Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        //Almacenamiento publico que se está utilizando por defecto

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "Cámara");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("Cámara", "fallo al crear el directorio");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            DireccImg=mediaStorageDir.getPath() + File.separator +"IMG_"+ timeStamp + ".jpg";
            mediaFile = new File(DireccImg);
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }
/*********************************************************************************************************/
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
            clickMenu.start();
            Jugador j = new Jugador(nombre.getText().toString(), nick.getText().toString());
            j.setImg(DireccImg);
            //Insertamos al jugador en la BBDD
            MyDBAdapter db = new MyDBAdapter(getContext());
            db.insertarJugador(j);

            mCallback.onUserListener(j);

        }



    }

}

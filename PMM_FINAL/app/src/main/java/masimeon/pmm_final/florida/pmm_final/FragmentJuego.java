package masimeon.pmm_final.florida.pmm_final;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class FragmentJuego extends Fragment {

    //Definimos los parámetros que recibirá el fragment de la activity
    static final String JUGADOR_ACT = "jugador_act";
    private int jugador_act;
    private Integer puntos_act;

    /****Variables para el juego****/

    private MediaPlayer sonidoDados;
    private MediaPlayer sonidoRepetir;
    private MediaPlayer sonidoOpe;
    private MediaPlayer sonidoOK;
    private MediaPlayer sonidoKO;

    //BBDD
    private MyDBAdapter db;
    private Jugador player;

    //Dados valores
    private ArrayList<Integer> DadosDe6 = new ArrayList<Integer>();
    private ArrayList<Integer> DadosDe3 = new ArrayList<Integer>();
    private ArrayList<Integer> DadosDe12 = new ArrayList<Integer>();

    //Dados en el layout
    private ImageView dado01;
    private ImageView dado02;
    private ImageView dado03;
    private ImageView dado04;
    private ImageView dado05;
    private ImageView dado07;

    //Tirada de cada dado
    private int tirada_dado01;
    private int tirada_dado02;
    private int tirada_dado03;
    private int tirada_dado04;
    private int tirada_dado05;
    private int tirada_dado07;

    //Info para el jugador
    private TextView puntos;

    //Semáforos para el control de los botones
    private Boolean edit_num;//Pulsar dado S(true)/N(false)
    private Boolean edit_sign;//Pulsar el + o - S(true)/N(false)
    private Boolean suma_activa;//+(true) -(false)

    //Botones
    private Button btn_Repetir;
    private Button btn_Suma;
    private Button btn_Resta;
    private Button btn_MathDice;

    //Pantalla
    private TextView pantalla;
    private int resultado; //Variable donde almacenar el resultado;
    private TextView pantallaRes;

    public FragmentJuego() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //Si el parámetro no es nulo se carga la infor en la variable
           // jugador_act = getArguments().getInt(JUGADOR_ACT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflamos el layout para este fragment
        View v = inflater.inflate(R.layout.fragment_juego, container, false);

        //Preparamos los sonidos
        sonidoDados = MediaPlayer.create(getContext(), R.raw.dado);
        sonidoDados.setVolume(10,10);

        sonidoRepetir = MediaPlayer.create(getContext(), R.raw.repetir);
        sonidoRepetir.setVolume(10,10);

        sonidoOpe = MediaPlayer.create(getContext(), R.raw.suma);
        sonidoOpe.setVolume(10,10);

        sonidoKO = MediaPlayer.create(getContext(), R.raw.ko);
        sonidoKO.setVolume(10,10);

        sonidoOK = MediaPlayer.create(getContext(), R.raw.ok);
        sonidoOK.setVolume(10,10);


        //Recuperamos el jugador según la ID proporcionada
        db = new MyDBAdapter(getContext());
        player = db.obtenerJugadorActivo();

        //Vinculamos los elementos gráficos sobre los que trabajar
        TextView nick = (TextView) v.findViewById(R.id.joc_txtnick);
        puntos = (TextView) v.findViewById(R.id.joc_txtpuntos);

        //Ponemos la info recuperada del jugador
        nick.setText(player.getNick());
        puntos_act=player.getPuntos();
        //Toast.makeText(getContext(), player.getNombre(), Toast.LENGTH_SHORT).show();
        puntos.setText("Puntos: "+String.valueOf(puntos_act));

        btn_Repetir = (Button) v.findViewById(R.id.btn_Repetir);
        pantalla = (TextView) v.findViewById(R.id.joc_txtPantalla);
        pantallaRes = (TextView) v.findViewById(R.id.joc_txtResultado);
        btn_Suma = (Button) v.findViewById(R.id.joc_btnSuma);
        btn_Resta = (Button) v.findViewById(R.id.joc_btnResta);
        btn_MathDice = (Button) v.findViewById(R.id.joc_btnMathDice);

        dado01 = (ImageView) v.findViewById(R.id.joc_dado01);
        dado02 = (ImageView) v.findViewById(R.id.joc_dado02);
        dado03 = (ImageView) v.findViewById(R.id.joc_dado03);
        dado04 = (ImageView) v.findViewById(R.id.joc_dado04);
        dado05 = (ImageView) v.findViewById(R.id.joc_dado05);
        dado07 = (ImageView) v.findViewById(R.id.joc_dado07);

        //Rellenamos los Array de los dados
        DadosDe6.add(getResources().getIdentifier("dado06_1", "drawable",getActivity().getPackageName()));
        DadosDe6.add(getResources().getIdentifier("dado06_2", "drawable",getActivity().getPackageName()));
        DadosDe6.add(getResources().getIdentifier("dado06_3", "drawable",getActivity().getPackageName()));
        DadosDe6.add(getResources().getIdentifier("dado06_4", "drawable",getActivity().getPackageName()));
        DadosDe6.add(getResources().getIdentifier("dado06_5", "drawable",getActivity().getPackageName()));
        DadosDe6.add(getResources().getIdentifier("dado06_6", "drawable",getActivity().getPackageName()));

        DadosDe3.add(getResources().getIdentifier("dado03_1", "drawable",getActivity().getPackageName()));
        DadosDe3.add(getResources().getIdentifier("dado03_2", "drawable",getActivity().getPackageName()));
        DadosDe3.add(getResources().getIdentifier("dado03_3", "drawable",getActivity().getPackageName()));

        DadosDe12.add(getResources().getIdentifier("dado12_1", "drawable",getActivity().getPackageName()));
        DadosDe12.add(getResources().getIdentifier("dado12_2", "drawable",getActivity().getPackageName()));
        DadosDe12.add(getResources().getIdentifier("dado12_3", "drawable",getActivity().getPackageName()));
        DadosDe12.add(getResources().getIdentifier("dado12_4", "drawable",getActivity().getPackageName()));
        DadosDe12.add(getResources().getIdentifier("dado12_5", "drawable",getActivity().getPackageName()));
        DadosDe12.add(getResources().getIdentifier("dado12_6", "drawable",getActivity().getPackageName()));
        DadosDe12.add(getResources().getIdentifier("dado12_7", "drawable",getActivity().getPackageName()));
        DadosDe12.add(getResources().getIdentifier("dado12_8", "drawable",getActivity().getPackageName()));
        DadosDe12.add(getResources().getIdentifier("dado12_9", "drawable",getActivity().getPackageName()));
        DadosDe12.add(getResources().getIdentifier("dado12_10", "drawable",getActivity().getPackageName()));
        DadosDe12.add(getResources().getIdentifier("dado12_11", "drawable",getActivity().getPackageName()));
        DadosDe12.add(getResources().getIdentifier("dado12_12", "drawable",getActivity().getPackageName()));

        nuevatirada();

        listenerbotones();
        return v;
    }

    public void onButtonPressed(Uri uri) {}

    @Override
    public void onAttach(Context context) {super.onAttach(context);}

    @Override
    public void onDetach() {super.onDetach();}

    public void nuevatirada(){

        sonidoRepetir.start();

        //Ponemos los semáforos al sitio
        edit_num=true;
        edit_sign=false;
        suma_activa=true;

        //Reiniciamos la pantalla y el resultado
        pantalla.setText("");
        resultado = 0;
        pantallaRes.setText("");

        //Tirada aleatoria de dados
        tirada_dado01 = new Random().nextInt(DadosDe6.size());
        tirada_dado02 = new Random().nextInt(DadosDe6.size());
        tirada_dado03 = new Random().nextInt(DadosDe6.size());
        tirada_dado04 = new Random().nextInt(DadosDe3.size());
        tirada_dado05 = new Random().nextInt(DadosDe3.size());
        tirada_dado07 = new Random().nextInt(DadosDe12.size());

        //Cargamos las imagenes de la tirada
        dado01.setImageResource(DadosDe6.get(tirada_dado01));
        dado02.setImageResource(DadosDe6.get(tirada_dado02));
        dado03.setImageResource(DadosDe6.get(tirada_dado03));
        dado04.setImageResource(DadosDe3.get(tirada_dado04));
        dado05.setImageResource(DadosDe3.get(tirada_dado05));
        dado07.setImageResource(DadosDe12.get(tirada_dado07));

        //Activamos los dados
        dado01.setEnabled(true);
        dado02.setEnabled(true);
        dado03.setEnabled(true);
        dado04.setEnabled(true);
        dado05.setEnabled(true);

        //Ponemos los listeners a los botones
        listenerdados();

        //Desactivamos el botón de volver a tirar para evitar trampas
        btn_Repetir.setEnabled(false);

    }

    public void listenerdados() {

        //Asignamos los listeners a los dados
        dado01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Entramos solo si está activa la edición de números
                if (edit_num){
                    listenerDados(dado01, tirada_dado01+1);
                }
            }
        });

        dado02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_num){
                    listenerDados(dado02, tirada_dado02+1);
                }
            }
        });

        dado03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_num){
                    listenerDados(dado03, tirada_dado03+1);
                }
            }
        });

        dado04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_num){
                    listenerDados(dado04, tirada_dado04+1);
                }
            }
        });

        dado05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_num){
                    listenerDados(dado05, tirada_dado05+1);
                }
            }
        });

        dado07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sonidoDados.start();
                Toast.makeText(getContext(), String.valueOf(tirada_dado07+1), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void listenerDados(ImageView dado, int tirada){
        sonidoDados.start();
        //Colocamos la imagen de dado pulsado
        dado.setImageResource(R.drawable.dado_selec);
        //Desactivamos el dado para evitar volver a pulsarlo
        dado.setEnabled(false);
        //Ponemos el resultado en la pantalla y según si es suma o resta aplicamos la operación al resultado
        pantalla.setText(pantalla.getText()+String.valueOf(tirada));
        //Toast.makeText(getContext(), String.valueOf(tirada), Toast.LENGTH_SHORT).show();
        if (suma_activa) {
            resultado = resultado + (tirada);
        }else{
            resultado = resultado - (tirada);
        }
        //Ponemos los semáforos al contrario para obligar a poner un signo y no dejar poner un número
        edit_num=false;
        edit_sign=true;
    }

    public void listenerbotones(){
        btn_Repetir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nuevatirada();
            }
        });

        btn_Suma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_sign) {
                    sonidoOpe.start();
                    pantalla.setText(pantalla.getText()+"+");
                    suma_activa = true;
                    edit_sign = false;
                    edit_num = true;
                }
            }
        });

        btn_Resta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_sign) {
                    sonidoOpe.start();
                    pantalla.setText(pantalla.getText()+"-");
                    suma_activa = false;
                    edit_sign = false;
                    edit_num = true;
                }
            }
        });

        btn_MathDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_sign && pantalla.getText().length()>2) {

                    btn_Repetir.setEnabled(true);
                    if ((tirada_dado07+1)==resultado){
                        sonidoOK.start();
                        //Calculamos los puntos
                        int puntosPartida = pantalla.length()*2;
                        //Sacamos los mensajes
                        pantallaRes.setText("Ganas "+String.valueOf(puntosPartida)+" pts");
                        Toast.makeText(getContext(), "Enhorabuena!", Toast.LENGTH_SHORT).show();
                        //Actualizamos la puntuación total
                        puntos_act= puntosPartida + puntos_act;
                        puntos.setText("Puntos: "+String.valueOf(puntos_act));

                        //Actualizamos la info de la BBDD
                        db.actJugador(puntos_act);
                    }else{
                        sonidoKO.start();
                        pantallaRes.setText(String.valueOf(tirada_dado07)+" no es igual a: "+String.valueOf(resultado));
                        Toast.makeText(getContext(), "Has fallado!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), "Operación incompleta. Completa 1 operación mínimo para hacer MathDice!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}

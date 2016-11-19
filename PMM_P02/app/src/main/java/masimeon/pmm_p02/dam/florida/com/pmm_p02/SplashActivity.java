package masimeon.pmm_p02.dam.florida.com.pmm_p02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Visibilizamos el splash
        setContentView(R.layout.activity_splash);
        // Escondemos la barra de títulos de esta Activity
        getSupportActionBar().hide();

        //Empezamos con el retraso de inicio de pantalla
        TimerTask retraso_inicio = new TimerTask() {
            @Override
            public void run() {

                // Iniciamos el MainActivity.class
                //Hacemos una llamada donde indicamos con el .this la activity actual
                //y con el .class (llamamos a la clase) la quq queremos llamar
                Intent llamada_app = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(llamada_app);

                //Cerramos esta class/activity para que el usuario no pueda volver a ella
                finish();
            }
        };

        //Una vez creada la tarea que crea el retraso e inicia la siguiente activity
        //solo nos falta crear un temporizador con el tiempo de retraso
        Timer temporizador = new Timer();
        temporizador.schedule(retraso_inicio, 4000); //Aquí le indicamos el tiempo, puede ser interesante sacar este dato en una variable global


    }

    protected void onResume(){
        super.onResume();
    }

    protected void onPause(){
        super.onPause();
    }
}

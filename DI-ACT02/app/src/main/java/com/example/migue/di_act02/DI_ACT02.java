package com.example.migue.di_act02;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.Toast;
import android.content.Context;

public class DI_ACT02 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_di_act02);

        //Botones Toggle
        final ToggleButton button_fondo = (ToggleButton) findViewById(R.id.btn_fondo);
        final ToggleButton button_letra = (ToggleButton) findViewById(R.id.btn_letras);
        final LinearLayout layout_inf = (LinearLayout) findViewById(R.id.layout_inf);

        button_fondo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                if(button_fondo.isChecked()){
                    layout_inf.setBackgroundColor(Color.RED);
                }else{
                    layout_inf.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });

        button_letra.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                if(button_letra.isChecked()){
                    button_letra.setTextColor(Color.RED);
                }else{
                    button_letra.setTextColor(Color.BLACK);
                }
            }
        });

        //Botón de checkbox
        final CheckBox check = (CheckBox) findViewById(R.id.checkMostrar);
        final TextView txt_oculto = (TextView) findViewById(R.id.txtOculto);

        check.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                if(check.isChecked()){
                    /*String message = getResources().getString(R.string.txt_oculto_on);
                    txt_oculto.setText(message);*/
                    txt_oculto.setVisibility(View.VISIBLE);
                }else{
                    /*String message = getResources().getString(R.string.txt_oculto_off);
                    txt_oculto.setText(message);*/
                    txt_oculto.setVisibility(View.INVISIBLE);
                }
            }
        });

        //Rating bar
        final TextView txt_rating = (TextView) findViewById(R.id.txtRating);
        final RatingBar estrella = (RatingBar) findViewById(R.id.rating);

        estrella.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){
            @Override
            public void onRatingChanged(RatingBar rating, float ratingNum, boolean fromUser){
                txt_rating.setText("["+Math.round(ratingNum)+"/5]");
            }
        });

        //Zona de click largo
        final TextView txt_clickL = (TextView) findViewById(R.id.txtClickLargo);

        View.OnLongClickListener listenerClickL = new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                //Variables necesarias para el Toast
                Context contextToast = getApplicationContext();
                CharSequence txtToast = "¡Muchas gracias!";
                int duracionToast = Toast.LENGTH_LONG;
                //Creamos el toast y lo mostramos
                Toast toast = Toast.makeText(contextToast, txtToast, duracionToast);
                toast.show();
                return true;
            }
        };

        txt_clickL.setOnLongClickListener(listenerClickL);
    }
}

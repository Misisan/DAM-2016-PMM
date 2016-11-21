package masimeon.diexam1.florida.di_exam01;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView text = (TextView) findViewById(R.id.txt);
        final LinearLayout fondo = (LinearLayout) findViewById(R.id.fondo);
        final RadioButton btn_F1 = (RadioButton) findViewById(R.id.btnR_fondoB);
        final RadioButton btn_F2 = (RadioButton) findViewById(R.id.btnR_fondoG);
        final RadioButton btn_F3 = (RadioButton) findViewById(R.id.btnR_fondoR);
        final RadioButton btn_T1 = (RadioButton) findViewById(R.id.btnR_textW);
        final RadioButton btn_T2 = (RadioButton) findViewById(R.id.btnR_textY);
        final RadioButton btn_T3 = (RadioButton) findViewById(R.id.btnR_textB);
        final CheckBox check = (CheckBox) findViewById(R.id.check);

        btn_F1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                if (btn_F1.isChecked()){
                    fondo.setBackgroundColor(Color.BLACK);
                    btn_F2.setChecked(false);
                    btn_F3.setChecked(false);
                }
            }
        });

        btn_F2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                if (btn_F2.isChecked()){
                    fondo.setBackgroundColor(Color.GREEN);
                    btn_F1.setChecked(false);
                    btn_F3.setChecked(false);
                }
            }
        });

        btn_F3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                if (btn_F3.isChecked()){
                    fondo.setBackgroundColor(Color.RED);
                    btn_F2.setChecked(false);
                    btn_F1.setChecked(false);
                }
            }
        });

        btn_T1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                if (btn_T1.isChecked()){
                    text.setTextColor(Color.WHITE);
                    btn_T2.setChecked(false);
                    btn_T3.setChecked(false);
                }
            }
        });

        btn_T2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                if (btn_T2.isChecked()){
                    text.setTextColor(Color.YELLOW);
                    btn_T1.setChecked(false);
                    btn_T3.setChecked(false);
                }
            }
        });

        btn_T3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                if (btn_T3.isChecked()){
                    text.setTextColor(Color.BLUE);
                    btn_T2.setChecked(false);
                    btn_T1.setChecked(false);
                }
            }
        });

        check.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                if (check.isChecked()){
                    text.setVisibility(View.VISIBLE);
                }else{
                    text.setVisibility(View.INVISIBLE);
                }
            }
        });


    }
}

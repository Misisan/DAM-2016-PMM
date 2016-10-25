package com.example.migue.di_act04;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Asociamos el menú a la app
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return true;
    }

    //Controlamos las opciones del menú
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item01:
                Toast.makeText(getApplication(), getApplicationContext().getString(R.string.msj01), Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_item02:
                Toast.makeText(getApplication(), getApplicationContext().getString(R.string.msj02), Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_item03:
                return false;
            case R.id.menu_item04:
                Toast.makeText(getApplication(), getApplicationContext().getString(R.string.msj04), Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_item05:
                Toast.makeText(getApplication(), getApplicationContext().getString(R.string.msj05), Toast.LENGTH_LONG).show();
                findViewById(R.id.activity_main).setBackgroundColor(Color.RED);
                return true;
            case R.id.menu_item06:
                Toast.makeText(getApplication(), getApplicationContext().getString(R.string.msj06), Toast.LENGTH_LONG).show();
                return true;
            case R.id.submenu_item01:
                Toast.makeText(getApplication(), getApplicationContext().getString(R.string.msj03_01), Toast.LENGTH_LONG).show();
                return true;
            case R.id.submenu_item02:
                Toast.makeText(getApplication(), getApplicationContext().getString(R.string.msj03_02), Toast.LENGTH_LONG).show();
                return true;
            case R.id.submenu_item03:
                Toast.makeText(getApplication(), getApplicationContext().getString(R.string.msj03_03), Toast.LENGTH_LONG).show();
                return true;
            case R.id.submenu_item04:
                Toast.makeText(getApplication(), getApplicationContext().getString(R.string.msj03_04), Toast.LENGTH_LONG).show();
                return true;
            default:
                return false;
        }
    }
}

package org.itiud.snakeproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Parámetros para el tamaño de la pantalla del dispositivo
        DisplayMetrics ds = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(ds);
        ParametrosC.SCREEN_HEIGHT = ds.heightPixels;
        ParametrosC.SCREEN_WIDTH = ds.widthPixels;
        setContentView(R.layout.activity_main);
    }
}
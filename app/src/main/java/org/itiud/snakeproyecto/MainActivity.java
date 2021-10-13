package org.itiud.snakeproyecto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.os.Handler;
import android.widget.TextView;


import org.itiud.logica.Interfaz;
import org.itiud.logica.ParametrosC;


public class MainActivity extends AppCompatActivity {

    private int minutos = 0, segundos = 0;
    private int puntua = 0;
    private TextView txtCrono, txtPunt;
    private Interfaz juego;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Parámetros para el tamaño de la pantalla del dispositivo
        DisplayMetrics ds = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(ds);
        ParametrosC.SCREEN_HEIGHT = ds.heightPixels;
        ParametrosC.SCREEN_WIDTH = ds.widthPixels;
        setContentView(R.layout.activity_main);

        this.txtCrono = findViewById(R.id.txt_cronometro);
        timer();
        this.txtPunt = findViewById(R.id.txt_puntaje);
        //juego.cargarPuntuaciones();

    }

    /*private void puntaje(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(juego.puntuacion() == true){
                    puntua+=10;
                    txtPunt.setText("x "+puntua);
                    puntaje();
                }
            }
        }, 10000);
    }*/


    private void timer() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                segundos++;
                if (segundos > 60) {
                    segundos = 0;
                    minutos++;
                }
                txtCrono.setText(minutos + ":" + segundos);
                timer();
            }
        }, 1000);
    }


    public void Reset(View view) {
        Intent intent = new Intent(this, Segundo.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("REINICIAR");
        builder.setMessage("¿Desea reiniciar el juego?");
        builder.setPositiveButton("Confirmar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                    }
                });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
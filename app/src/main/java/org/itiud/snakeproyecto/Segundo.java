package org.itiud.snakeproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Segundo extends AppCompatActivity {

    private Button bntIniciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundo);

        this.bntIniciar = findViewById(R.id.btnIniciar);
        Intent inGame = new Intent(this,MainActivity.class);

        this.bntIniciar.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             startActivity(inGame);
             overridePendingTransition(0,0);}
         });

    }

}
package com.carlitos.Pronacej.ActivitysPadres;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.carlitos.Pronacej.R;

public class CategoriaMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_menu);

        ConstraintLayout categoriaUno = findViewById(R.id.Opcion1);
        ConstraintLayout categoriaDos = findViewById(R.id.Opcion2);
        ConstraintLayout categoriaTres = findViewById(R.id.Opcion3);
        ConstraintLayout categoriaCuatro = findViewById(R.id.Opcion4);

        // Recuperar el nombre del usuario desde SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String userName = sharedPreferences.getString("userName", "Usuario");

        // Actualizar todos los TextView4 con el nombre del usuario
        TextView textView4 = findViewById(R.id.textView4);
        textView4.setText("Bienvenido\n" + userName);

        // Se establecen las funciones del botón
        categoriaUno.setOnClickListener(view -> {
            // Intent para pasar a otro activity
            Intent intent = new Intent(CategoriaMenu.this, OpcionesCentrosJuvenilesActivity.class);
            // Llamado a la acción de intent
            startActivity(intent);
        });


        categoriaCuatro.setOnClickListener(view -> {
            // Intent para pasar a otro activity
            Intent intent = new Intent(CategoriaMenu.this, OpcionPronacejDigital.class);
            // Llamado a la acción de intent
            startActivity(intent);
        });
    }
}

package com.carlitos.Pronacej.ActivitysPadres;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.carlitos.Pronacej.FiltrosCjdr.FiltroEdadCjdr;
import com.carlitos.Pronacej.FiltrosCjdr.FiltroEducativaCjdr;
import com.carlitos.Pronacej.FiltrosCjdr.FiltroInfraccionCjdr;
import com.carlitos.Pronacej.FiltrosCjdr.FiltroLaboralCjdr;
import com.carlitos.Pronacej.FiltrosCjdr.FiltroPoblacionCjdr;
import com.carlitos.Pronacej.FiltrosCjdr.FiltroSimpleCjdr;
import com.carlitos.Pronacej.FiltrosCjdr.FiltroTratamientoCjdr;
import com.carlitos.Pronacej.FiltrosSoa.FiltroSimpleSoa;
import com.carlitos.Pronacej.OpcionesCjdr.PoblacionCjdrActivity;
import com.carlitos.Pronacej.OpcionesCjdr.InfraccionesCometidasCjdrActivity;
import com.carlitos.Pronacej.OpcionesCjdr.InsercionLaboralCjdrActivity;
import com.carlitos.Pronacej.OpcionesCjdr.TratamientoDiferenciadoCjdrActivity;
import com.carlitos.Pronacej.OpcionesCjdr.InsercionEducativaCjdrActivity;
import com.carlitos.Pronacej.R;

public class MenuCjdrActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cjdr);

        ConstraintLayout opcionOcho= findViewById(R.id.Opcion8);
        ConstraintLayout opcionUno = findViewById(R.id.Opcion1);
        ConstraintLayout opcionDos = findViewById(R.id.Opcion2);
        ConstraintLayout opcionTres = findViewById(R.id.Opcion3);
        ConstraintLayout opcionCuatro = findViewById(R.id.Opcion4);
        ConstraintLayout opcionCinco = findViewById(R.id.Opcion5);



        // Eventos que abrirá las otras actividades
        opcionUno.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuCjdrActivity.this, FiltroEdadCjdr.class);
                startActivity(intent);
            }
        });

        opcionDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuCjdrActivity.this, FiltroTratamientoCjdr.class);
                startActivity(intent);
            }
        });

        opcionTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuCjdrActivity.this, FiltroEducativaCjdr.class);
                startActivity(intent);
            }
        });

        opcionCuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuCjdrActivity.this, FiltroLaboralCjdr.class);
                startActivity(intent);
            }
        });

        opcionCinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuCjdrActivity.this, FiltroInfraccionCjdr.class);
                startActivity(intent);
            }
        });

        opcionOcho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuCjdrActivity.this, FiltroSimpleCjdr.class);
                startActivity(intent);
            }
        });

    }

}

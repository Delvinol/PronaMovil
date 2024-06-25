package com.carlitos.Pronacej.ActivitysPadres;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.carlitos.Pronacej.FiltroCjdrTotal.FiltroEducativaTotalCjdr;
import com.carlitos.Pronacej.FiltroCjdrTotal.FiltroInfraccionTotalCjdr;
import com.carlitos.Pronacej.FiltroCjdrTotal.FiltroLaboralTotalCjdr;
import com.carlitos.Pronacej.FiltroCjdrTotal.FiltroPoblacionTotalCjdr;
import com.carlitos.Pronacej.FiltroCjdrTotal.FiltroTratamientoTotalCjdr;
import com.carlitos.Pronacej.FiltrosCjdr.FiltroEdadCjdr;
import com.carlitos.Pronacej.FiltrosCjdr.FiltroEducativaCjdr;
import com.carlitos.Pronacej.FiltrosCjdr.FiltroInfraccionCjdr;
import com.carlitos.Pronacej.FiltrosCjdr.FiltroLaboralCjdr;
import com.carlitos.Pronacej.FiltrosCjdr.FiltroPoblacionCjdr;
import com.carlitos.Pronacej.FiltrosCjdr.FiltroSimpleCjdr;
import com.carlitos.Pronacej.FiltrosCjdr.FiltroTratamientoCjdr;
import com.carlitos.Pronacej.FiltrosSoa.FiltroEducativaSoa;
import com.carlitos.Pronacej.FiltrosSoa.FiltroInfraccionSoa;
import com.carlitos.Pronacej.FiltrosSoa.FiltroLaboralSoa;
import com.carlitos.Pronacej.FiltrosSoa.FiltroPoblacionSoa;
import com.carlitos.Pronacej.FiltrosSoa.FiltroTratamientoSoa;
import com.carlitos.Pronacej.R;

public class MenuTotalesCjdrActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_totales_cjdr);

        ConstraintLayout opcionOcho = findViewById(R.id.Opcion8);
        ConstraintLayout opcionUno = findViewById(R.id.Opcion1);
        ConstraintLayout opcionDos = findViewById(R.id.Opcion2);
        ConstraintLayout opcionTres = findViewById(R.id.Opcion3);
        ConstraintLayout opcionCuatro = findViewById(R.id.Opcion4);
        ConstraintLayout opcionCinco = findViewById(R.id.Opcion5);



        // Eventos que abrirá las otras actividades
        opcionUno.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuTotalesCjdrActivity.this, FiltroEdadCjdr.class);
                startActivity(intent);
            }
        });

        opcionDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuTotalesCjdrActivity.this, FiltroTratamientoTotalCjdr.class);
                startActivity(intent);
            }
        });

        opcionTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuTotalesCjdrActivity.this, FiltroEducativaTotalCjdr.class);
                startActivity(intent);
            }
        });

        opcionCuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuTotalesCjdrActivity.this, FiltroLaboralTotalCjdr.class);
                startActivity(intent);
            }
        });

        opcionCinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuTotalesCjdrActivity.this, FiltroInfraccionTotalCjdr.class);
                startActivity(intent);
            }
        });

        opcionOcho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuTotalesCjdrActivity.this, FiltroSimpleCjdr.class);
                startActivity(intent);
            }
        });

    }
}

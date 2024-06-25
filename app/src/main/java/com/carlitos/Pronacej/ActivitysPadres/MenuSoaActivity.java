package com.carlitos.Pronacej.ActivitysPadres;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.carlitos.Pronacej.FiltrosCjdr.FiltroEducativaCjdr;
import com.carlitos.Pronacej.FiltrosCjdr.FiltroInfraccionCjdr;
import com.carlitos.Pronacej.FiltrosCjdr.FiltroLaboralCjdr;
import com.carlitos.Pronacej.FiltrosCjdr.FiltroPoblacionCjdr;
import com.carlitos.Pronacej.FiltrosCjdr.FiltroTratamientoCjdr;
import com.carlitos.Pronacej.FiltrosSoa.FiltroEdadSoa;
import com.carlitos.Pronacej.FiltrosSoa.FiltroEducativaSoa;
import com.carlitos.Pronacej.FiltrosSoa.FiltroInfraccionSoa;
import com.carlitos.Pronacej.FiltrosSoa.FiltroLaboralSoa;
import com.carlitos.Pronacej.FiltrosSoa.FiltroPoblacionSoa;
import com.carlitos.Pronacej.FiltrosSoa.FiltroSimpleSoa;
import com.carlitos.Pronacej.FiltrosSoa.FiltroTratamientoSoa;
import com.carlitos.Pronacej.OpcionesCjdr.PoblacionCjdrActivity;
import com.carlitos.Pronacej.OpcionesCjdr.InfraccionesCometidasCjdrActivity;
import com.carlitos.Pronacej.OpcionesCjdr.InsercionEducativaCjdrActivity;
import com.carlitos.Pronacej.OpcionesCjdr.InsercionLaboralCjdrActivity;
import com.carlitos.Pronacej.OpcionesCjdr.TratamientoDiferenciadoCjdrActivity;
import com.carlitos.Pronacej.OpcionesSoa.InfraccionesCometidasSoaActivity;
import com.carlitos.Pronacej.OpcionesSoa.InsercionEducativaSoaActivity;
import com.carlitos.Pronacej.OpcionesSoa.InsercionLaboralSoaActivity;
import com.carlitos.Pronacej.OpcionesSoa.PoblacionSoaActivity;
import com.carlitos.Pronacej.OpcionesSoa.TratamientoDiferenciadoSoaActivity;
import com.carlitos.Pronacej.R;



public class MenuSoaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_soa);

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
                Intent intent = new Intent(MenuSoaActivity.this, FiltroEdadSoa.class);
                startActivity(intent);
            }
        });

        opcionDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuSoaActivity.this, FiltroTratamientoSoa.class);
                startActivity(intent);
            }
        });

        opcionTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuSoaActivity.this, FiltroEducativaSoa.class);
                startActivity(intent);
            }
        });

        opcionCuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuSoaActivity.this, FiltroLaboralSoa.class);
                startActivity(intent);
            }
        });

        opcionCinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuSoaActivity.this, FiltroInfraccionSoa.class);
                startActivity(intent);
            }
        });

        opcionOcho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuSoaActivity.this, FiltroSimpleSoa.class);
                startActivity(intent);
            }
        });

    }


}

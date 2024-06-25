package com.carlitos.Pronacej.OpcionesCjdr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.carlitos.Pronacej.R;
import com.carlitos.Pronacej.ResultadosCjrd.ResultadosSeguroMedicoCjdr;
import com.carlitos.Pronacej.ResultadosCjrd.ResultadosSituacionJuridicaCjdr;
import com.carlitos.Pronacej.ResultadosCjrd.ResultadosSituacionLaboralActualCjdr;
import com.carlitos.Pronacej.ResultadosSoa.ResultadosSeguroMedicoSoa;
import com.carlitos.Pronacej.ResultadosSoa.ResultadosSituacionLaboralActualSoa;

public class InsercionLaboralCjdrActivity extends AppCompatActivity {

    private int seguro_sis;
    private int seguro_essalud;
    private int seguro_particular;
    private int seguro_ninguno;
    private int inser_labo_interna;
    private int inser_labo_externa;
    private int no_trabaja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insercion_laboral_cjdr);

        Intent intent = getIntent();
        seguro_sis = intent.getIntExtra("seguro_sis", 0);
        seguro_essalud = intent.getIntExtra("seguro_essalud", 0);
        seguro_particular = intent.getIntExtra("seguro_particular", 0);
        seguro_ninguno = intent.getIntExtra("seguro_ninguno", 0);
        inser_labo_interna = intent.getIntExtra("inser_labo_interna", 0);
        inser_labo_externa = intent.getIntExtra("inser_labo_externa", 0);
        no_trabaja = intent.getIntExtra("no_trabaja", 0);
        // Asignar click listener a los ConstraintLayouts
        ConstraintLayout opcion1 = findViewById(R.id.Opcion1);
        ConstraintLayout opcion2 = findViewById(R.id.Opcion2);

        opcion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad correspondiente al presionar el ConstraintLayout 1
                Intent intentOpcion1 = new Intent(InsercionLaboralCjdrActivity.this, ResultadosSituacionLaboralActualCjdr.class);
                intentOpcion1.putExtra("inser_labo_interna", inser_labo_interna);
                intentOpcion1.putExtra("inser_labo_externa", inser_labo_externa);
                intentOpcion1.putExtra("no_trabaja", no_trabaja);
                startActivity(intentOpcion1);
            }
        });

        opcion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad correspondiente al presionar el ConstraintLayout 2
                Intent intentOpcion2 = new Intent(InsercionLaboralCjdrActivity.this, ResultadosSeguroMedicoCjdr.class);
                intentOpcion2.putExtra("seguro_sis", seguro_sis);
                intentOpcion2.putExtra("seguro_essalud", seguro_essalud);
                intentOpcion2.putExtra("seguro_particular", seguro_particular);
                intentOpcion2.putExtra("seguro_ninguno", seguro_ninguno);
                startActivity(intentOpcion2);
            }
        });
    }
}

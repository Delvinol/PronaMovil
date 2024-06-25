package com.carlitos.Pronacej.OpcionesCjdr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.carlitos.Pronacej.FiltrosCjdr.FiltroTratamientoCjdr;
import com.carlitos.Pronacej.R;
import com.carlitos.Pronacej.ResultadosCjrd.ResultadoAdnCjdr;
import com.carlitos.Pronacej.ResultadosCjrd.ResultadoAgresoresSexualesCjdr;
import com.carlitos.Pronacej.ResultadosCjrd.ResultadoIntervencionTerapeuticaCjdr;
import com.carlitos.Pronacej.ResultadosCjrd.ResultadoJusticiaTerapeuticaCjdr;
import com.carlitos.Pronacej.ResultadosCjrd.ResultadosFirmesAdelanteCjdr;
import com.carlitos.Pronacej.ResultadosCjrd.ResultadosProgramasCjdr;
import com.carlitos.Pronacej.ResultadosCjrd.ResultadosSaludMentalCjdr;
import com.carlitos.Pronacej.ResultadosCjrd.ResultadosSituacionJuridicaCjdr;
import com.carlitos.Pronacej.ResultadosCjrd.ResultadosTotalPoblacionCjdr;

public class TratamientoDiferenciadoCjdrActivity extends AppCompatActivity{

    private int participa_programa_uno;
    private int participa_programa_dos;
    private int participa_programa_tres;
    private int participa_programa_cuatro;
    private int participa_programa_cinco;
    private int participa_programa_no;
    private int justicia_si;
    private int justicia_no;
    private int agresor_si;
    private int agresor_no;
    private int salud_si;
    private int salud_no;
    private int adn_si;
    private int adn_no;
    private int intervencion_aplica;
    private int intervencion_no_aplica;
    private int firmes_aplica;
    private int firmes_no_aplica;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tratamiento_diferenciado_cjdr);

        Intent intent = getIntent();
        participa_programa_uno = intent.getIntExtra("participa_programa_uno", 0);
        participa_programa_dos = intent.getIntExtra("participa_programa_dos", 0);
        participa_programa_tres = intent.getIntExtra("participa_programa_tres", 0);
        participa_programa_cuatro = intent.getIntExtra("participa_programa_cuatro", 0);
        participa_programa_cinco = intent.getIntExtra("participa_programa_cinco", 0);
        participa_programa_no = intent.getIntExtra("participa_programa_no", 0);
        justicia_si = intent.getIntExtra("justicia_si", 0);
        justicia_no = intent.getIntExtra("justicia_no", 0);
        agresor_si = intent.getIntExtra("agresor_si", 0);
        agresor_no = intent.getIntExtra("agresor_no", 0);
        salud_si = intent.getIntExtra("salud_si", 0);
        salud_no = intent.getIntExtra("salud_no", 0);
        adn_si = intent.getIntExtra("adn_si", 0);
        adn_no = intent.getIntExtra("adn_no", 0);
        intervencion_aplica = intent.getIntExtra("intervencion_aplica", 0);
        intervencion_no_aplica = intent.getIntExtra("intervencion_no_aplica", 0);
        firmes_aplica = intent.getIntExtra("firmes_aplica", 0);
        firmes_no_aplica = intent.getIntExtra("firmes_no_aplica", 0);
        // Asignar click listener a los ConstraintLayouts
        ConstraintLayout opcion1 = findViewById(R.id.Opcion1);
        ConstraintLayout opcion2 = findViewById(R.id.Opcion2);
        ConstraintLayout opcion3 = findViewById(R.id.Opcion3);
        ConstraintLayout opcion4 = findViewById(R.id.Opcion4);
        ConstraintLayout opcion5 = findViewById(R.id.Opcion5);
        ConstraintLayout opcion6 = findViewById(R.id.Opcion6);
        ConstraintLayout opcion7 = findViewById(R.id.Opcion7);

        opcion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad correspondiente al presionar el ConstraintLayout 1
                Intent intentOpcion1 = new Intent(TratamientoDiferenciadoCjdrActivity.this, ResultadosProgramasCjdr.class);
                intentOpcion1.putExtra("participa_programa_uno", participa_programa_uno);
                intentOpcion1.putExtra("participa_programa_dos", participa_programa_dos);
                intentOpcion1.putExtra("participa_programa_tres", participa_programa_tres);
                intentOpcion1.putExtra("participa_programa_cuatro", participa_programa_cuatro);
                intentOpcion1.putExtra("participa_programa_cinco", participa_programa_cinco);
                intentOpcion1.putExtra("participa_programa_no", participa_programa_no);
                startActivity(intentOpcion1);
            }
        });

        opcion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad correspondiente al presionar el ConstraintLayout 2
                Intent intentOpcion2 = new Intent(TratamientoDiferenciadoCjdrActivity.this, ResultadoJusticiaTerapeuticaCjdr.class);
                intentOpcion2.putExtra("justicia_si", justicia_si);
                intentOpcion2.putExtra("justicia_no", justicia_no);
                startActivity(intentOpcion2);
            }
        });

        opcion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad correspondiente al presionar el ConstraintLayout 1
                Intent intentOpcion3 = new Intent(TratamientoDiferenciadoCjdrActivity.this, ResultadoAgresoresSexualesCjdr.class);
                intentOpcion3.putExtra("agresor_si", agresor_si);
                intentOpcion3.putExtra("agresor_no", agresor_no);
                startActivity(intentOpcion3);
            }
        });

        opcion4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad correspondiente al presionar el ConstraintLayout 2
                Intent intentOpcion4 = new Intent(TratamientoDiferenciadoCjdrActivity.this, ResultadosSaludMentalCjdr.class);
                intentOpcion4.putExtra("salud_si", salud_si);
                intentOpcion4.putExtra("salud_no", salud_no);
                startActivity(intentOpcion4);
            }
        });

        opcion5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad correspondiente al presionar el ConstraintLayout 1
                Intent intentOpcion5 = new Intent(TratamientoDiferenciadoCjdrActivity.this, ResultadoAdnCjdr.class);
                intentOpcion5.putExtra("adn_si", adn_si);
                intentOpcion5.putExtra("adn_no", adn_no);
                startActivity(intentOpcion5);
            }
        });

        opcion6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad correspondiente al presionar el ConstraintLayout 2
                Intent intentOpcion6 = new Intent(TratamientoDiferenciadoCjdrActivity.this, ResultadoIntervencionTerapeuticaCjdr.class);
                intentOpcion6.putExtra("intervencion_aplica", intervencion_aplica);
                intentOpcion6.putExtra("intervencion_no_aplica", intervencion_no_aplica);
                startActivity(intentOpcion6);
            }
        });

        opcion7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad correspondiente al presionar el ConstraintLayout 2
                Intent intentOpcion7 = new Intent(TratamientoDiferenciadoCjdrActivity.this, ResultadosFirmesAdelanteCjdr.class);
                intentOpcion7.putExtra("firmes_aplica", firmes_aplica);
                intentOpcion7.putExtra("firmes_no_aplica", firmes_no_aplica);
                startActivity(intentOpcion7);
            }
        });

    }
}

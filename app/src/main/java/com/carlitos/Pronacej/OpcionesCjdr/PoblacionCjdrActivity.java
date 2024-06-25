package com.carlitos.Pronacej.OpcionesCjdr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.carlitos.Pronacej.R;
import com.carlitos.Pronacej.ResultadosCjrd.ResultadoReporteDiarioCJdr;
import com.carlitos.Pronacej.ResultadosCjrd.ResultadosEstadoCivilCjdr;
import com.carlitos.Pronacej.ResultadosCjrd.ResultadosEstadoCjrd;
import com.carlitos.Pronacej.ResultadosCjrd.ResultadosSexoCjrd;
import com.carlitos.Pronacej.ResultadosCjrd.ResultadosSituacionJuridicaCjdr;
import com.carlitos.Pronacej.ResultadosCjrd.ResultadosTotalPoblacionCjdr;
import com.github.mikephil.charting.charts.PieChart;

import java.util.ArrayList;
import java.util.HashMap;


public class PoblacionCjdrActivity extends AppCompatActivity {

    private ArrayList<HashMap<String, String>> reportData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poblacion_cjdr);

        Intent intent = getIntent();

        // Asignar click listener a los ConstraintLayouts
        reportData = (ArrayList<HashMap<String, String>>) intent.getSerializableExtra("reportData");

        // Asignando click listener a los Constraint Layouts
        ConstraintLayout opcion1 = findViewById(R.id.Opcion1);
        ConstraintLayout opcion2 = findViewById(R.id.Opcion2);
        ConstraintLayout opcion3 = findViewById(R.id.Opcion3);
        ConstraintLayout opcion4 = findViewById(R.id.Opcion4);

        opcion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad correspondiente al presionar el ConstraintLayout 1
                Intent intentOpcion1 = new Intent(PoblacionCjdrActivity.this, ResultadoReporteDiarioCJdr.class);
                intentOpcion1.putExtra("reportData", filterData("centro_cjdr", "poblacion_cjdr"));
                startActivity(intentOpcion1);
            }
        });

        opcion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad correspondiente al presionar el ConstraintLayout 2
                Intent intentOpcion2 = new Intent(PoblacionCjdrActivity.this, ResultadosSituacionJuridicaCjdr.class);
                intentOpcion2.putExtra("reportData", filterData("centro_cjdr", "sentenciados_cjdr", "procesados_cjdr"));
                startActivity(intentOpcion2);
            }
        });

        opcion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad correspondiente al presionar el ConstraintLayout 2
                Intent intentOpcion3 = new Intent(PoblacionCjdrActivity.this, ResultadosEstadoCjrd.class);
                intentOpcion3.putExtra("reportData", filterData("centro_cjdr", "egresos_cjdr", "ingresos_cjdr"));
                startActivity(intentOpcion3);
            }
        });

        opcion4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad correspondiente al presionar el ConstraintLayout 2
                Intent intentOpcion4 = new Intent(PoblacionCjdrActivity.this, ResultadosEstadoCivilCjdr.class);
                intentOpcion4.putExtra("reportData", filterData("centro_cjdr", "mayores_cjdr", "menores_cjdr"));
                startActivity(intentOpcion4);
            }
        });
    }

    private ArrayList<HashMap<String, String>> filterData(String... keys) {
        ArrayList<HashMap<String, String>> filteredData = new ArrayList<>();
        for (HashMap<String, String> data : reportData) {
            HashMap<String, String> filteredEntry = new HashMap<>();
            for (String key : keys) {
                filteredEntry.put(key, data.get(key));
            }
            filteredData.add(filteredEntry);
        }
        return filteredData;
    }

}

package com.carlitos.Pronacej.OpcionesSoa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.carlitos.Pronacej.OpcionesCjdr.PoblacionCjdrActivity;
import com.carlitos.Pronacej.R;
import com.carlitos.Pronacej.ResultadosCjrd.ResultadosSituacionJuridicaCjdr;
import com.carlitos.Pronacej.ResultadosCjrd.ResultadosTotalPoblacionCjdr;
import com.carlitos.Pronacej.ResultadosSoa.ResultadoReporteDiarioSoa;
import com.carlitos.Pronacej.ResultadosSoa.ResultadosEstadoCivilSoa;
import com.carlitos.Pronacej.ResultadosSoa.ResultadosEstadoSoa;
import com.carlitos.Pronacej.ResultadosSoa.ResultadosSexoSoa;
import com.carlitos.Pronacej.ResultadosSoa.ResultadosSituacionJuridicaSoa;
import com.carlitos.Pronacej.ResultadosSoa.ResultadosTotalPoblacionSoa;
import com.carlitos.Pronacej.Utils.CjdrService;
import com.carlitos.Pronacej.Utils.SoaService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PoblacionSoaActivity extends AppCompatActivity {
    private ArrayList<HashMap<String, String>> reportData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poblacion_soa);

        Intent intent = getIntent();
        reportData = (ArrayList<HashMap<String, String>>) intent.getSerializableExtra("reportData");
        // Asignar click listener a los ConstraintLayouts
        ConstraintLayout opcion1 = findViewById(R.id.Opcion1);
        ConstraintLayout opcion2 = findViewById(R.id.Opcion2);

        opcion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad correspondiente al presionar el ConstraintLayout 1
                Intent intentOpcion1 = new Intent(PoblacionSoaActivity.this, ResultadoReporteDiarioSoa.class);
                intentOpcion1.putExtra("reportData", filterData("centro_soa", "poblacion_soa"));
                startActivity(intentOpcion1);
            }
        });

        opcion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad correspondiente al presionar el ConstraintLayout 2
                Intent intentOpcion2 = new Intent(PoblacionSoaActivity.this, ResultadosSituacionJuridicaSoa.class);
                intentOpcion2.putExtra("reportData", filterData("centro_soa", "varones_soa", "mujeres_soa"));
                startActivity(intentOpcion2);
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

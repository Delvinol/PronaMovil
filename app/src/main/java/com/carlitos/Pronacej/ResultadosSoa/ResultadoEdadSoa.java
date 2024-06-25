package com.carlitos.Pronacej.ResultadosSoa;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.carlitos.Pronacej.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class ResultadoEdadSoa extends AppCompatActivity {

    private BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_edad_soa);

        barChart = findViewById(R.id.barChart);

        // Obtener los datos pasados desde la actividad anterior
        String datosJson = getIntent().getStringExtra("datosEdad");
        if (datosJson != null) {
            try {
                JSONArray jsonArray = new JSONArray(datosJson);
                if (jsonArray.length() > 0) {
                    JSONObject datos = jsonArray.getJSONObject(0);
                    crearGrafico(datos);
                } else {
                    Log.e("ResultadoEdadSoa", "No se encontraron datos en la lista");
                }
            } catch (JSONException e) {
                Log.e("ResultadoEdadSoa", "Error al parsear JSON", e);
            }
        } else {
            Log.e("ResultadoEdadSoa", "No se recibieron datos");
        }
    }

    private void crearGrafico(JSONObject datos) throws JSONException {
        ArrayList<BarEntry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();

        // Mapeo de datos a valores de entrada
        int i = 0;
        Iterator<String> keys = datos.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            if (!key.equals("estado_ing")) { // Excluir "estado_ing"
                float valor = (float) datos.getDouble(key);
                entries.add(new BarEntry(i, valor));
                labels.add(key);
                i++;
            }
        }

        BarDataSet dataSet = new BarDataSet(entries, "Edades");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setValueTextSize(12f);

        BarData barData = new BarData(dataSet);
        barChart.setData(barData);

        // Configurar el eje X
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        barChart.getXAxis().setGranularity(1f);
        barChart.getXAxis().setGranularityEnabled(true);

        // Configurar el eje Y
        barChart.getAxisLeft().setGranularity(1f);
        barChart.getAxisRight().setEnabled(false);

        // Configurar leyenda y descripción
        barChart.getLegend().setEnabled(true);
        barChart.getDescription().setEnabled(false);

        barChart.invalidate(); // refrescar
    }
}
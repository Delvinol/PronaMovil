package com.carlitos.Pronacej.ResultadosCjrd;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.carlitos.Pronacej.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ResultadoEdadCjdr extends AppCompatActivity {

    private BarChart barChart;
    private TextView[] textViewsPorcentaje = new TextView[8];
    private TextView[] textViewsNombre = new TextView[8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_edad_cjdr);

        barChart = findViewById(R.id.barChart);

        // Referencias a los TextView de porcentaje
        textViewsPorcentaje[0] = findViewById(R.id.textViewCatorcePorcentaje);
        textViewsPorcentaje[1] = findViewById(R.id.textViewQuincePorcentaje);
        textViewsPorcentaje[2] = findViewById(R.id.textViewDieciseisPorcentaje);
        textViewsPorcentaje[3] = findViewById(R.id.textViewDiecisietePorcentaje);
        textViewsPorcentaje[4] = findViewById(R.id.textViewDieciochoPorcentaje);
        textViewsPorcentaje[5] = findViewById(R.id.textViewDiecinuevePorcentaje);
        textViewsPorcentaje[6] = findViewById(R.id.textViewVeintePorcentaje);
        textViewsPorcentaje[7] = findViewById(R.id.textViewVeintiunoPorcentaje);

        // Referencias a los TextView de nombres
        textViewsNombre[0] = findViewById(R.id.textViewCatorce);
        textViewsNombre[1] = findViewById(R.id.textViewQuince);
        textViewsNombre[2] = findViewById(R.id.textViewDieciseis);
        textViewsNombre[3] = findViewById(R.id.textViewDiecisiete);
        textViewsNombre[4] = findViewById(R.id.textViewDieciocho);
        textViewsNombre[5] = findViewById(R.id.textViewDiecinueve);
        textViewsNombre[6] = findViewById(R.id.textViewVeinte);
        textViewsNombre[7] = findViewById(R.id.textViewVeintiuno);

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

        // Calcular el total de todos los valores
        float total = 0;
        Iterator<String> keys = datos.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            if (!key.equals("estado_ing")) { // Excluir "estado_ing"
                total += datos.getDouble(key);
            }
        }

        // Mapeo de datos a valores de entrada
        int i = 0;
        keys = datos.keys(); // Reiniciar el iterador
        while (keys.hasNext()) {
            String key = keys.next();
            if (!key.equals("estado_ing")) { // Excluir "estado_ing"
                float valor = (float) datos.getDouble(key);
                float porcentaje = (valor / total) * 100;
                entries.add(new BarEntry(i, valor));
                labels.add(key);

                // Asignar datos a los TextView
                if (i < textViewsPorcentaje.length && i < textViewsNombre.length) {
                    textViewsPorcentaje[i].setText(String.format("%.2f%%", porcentaje));
                    textViewsNombre[i].setText(key);
                }

                i++;
            }
        }

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.Pronacej5));
        colors.add(getResources().getColor(R.color.Pronacej8));
        colors.add(getResources().getColor(R.color.Pronacej6));
        colors.add(getResources().getColor(R.color.Pronacej3));
        colors.add(getResources().getColor(R.color.Pronacej2));
        colors.add(getResources().getColor(R.color.Pronacej7));
        colors.add(getResources().getColor(R.color.Pronacej9));
        colors.add(getResources().getColor(R.color.Pronacej10));

        BarDataSet dataSet = new BarDataSet(entries, "Edades");
        dataSet.setColors(colors);
        dataSet.setValueTextSize(12f);

        BarData barData = new BarData(dataSet);
        barChart.setData(barData);

        // Configurar el eje X
        barChart.getXAxis().setValueFormatter(new CustomAxisValueFormatter(labels));
        barChart.getXAxis().setGranularity(1f);
        barChart.getXAxis().setGranularityEnabled(true);

        // Habilitar zoom y scroll
        barChart.setScaleEnabled(true);
        barChart.setPinchZoom(true);

        // Configurar el eje Y
        barChart.getAxisLeft().setGranularity(1f);
        barChart.getAxisRight().setEnabled(false);

        // Configurar leyenda y descripción
        barChart.getLegend().setEnabled(true);
        barChart.getDescription().setEnabled(false);

        barChart.invalidate(); // refrescar
    }

    // Clase formateadora personalizada para el eje X
    class CustomAxisValueFormatter extends IndexAxisValueFormatter {

        private final ArrayList<String> originalLabels;

        public CustomAxisValueFormatter(ArrayList<String> labels) {
            super(labels);
            this.originalLabels = labels;
        }

        @Override
        public String getFormattedValue(float value) {
            int index = (int) value;
            if (index >= 0 && index < originalLabels.size()) {
                String label = originalLabels.get(index);
                if (barChart.getViewPortHandler().getScaleX() > 1) {
                    // Mostrar la etiqueta completa si el zoom está activo
                    return label;
                } else {
                    // Mostrar solo las primeras 6 letras si no hay zoom
                    return label.length() > 6 ? label.substring(0, 6) : label;
                }
            }
            return "";
        }
    }
}

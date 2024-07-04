package com.carlitos.Pronacej.ResultadosCjrd;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.carlitos.Pronacej.ActivitysPadres.CategoriaMenu;
import com.carlitos.Pronacej.R;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.HashMap;

public class ResultadosEstadoCjrd extends AppCompatActivity {

    private HorizontalBarChart barChart;
    private ArrayList<HashMap<String, String>> reportData;
    private TextView[] textViewsPorcentaje = new TextView[10];
    private TextView[] textViewsNombre = new TextView[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_estado_cjdr);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(ResultadosEstadoCjrd.this, CategoriaMenu.class);
                startActivity(intentHome);
            }
        });

        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Llamar al método onBackPressed para ir atrás
            }
        });

        barChart = findViewById(R.id.barChart);

        // Obtener los datos pasados desde la actividad anterior
        Intent intent = getIntent();
        reportData = (ArrayList<HashMap<String, String>>) intent.getSerializableExtra("reportData");

        // Verificar si reportData es null
        if (reportData == null || reportData.isEmpty()) {
            // Manejo de error si no hay datos
            Log.e("ResultadosEstadoCjrd", "No hay datos en reportData");
            return;
        }

        // Log de datos de entrada
        for (int i = 0; i < reportData.size(); i++) {
            Log.d("ResultadosEstadoCjrd", "Datos de entrada - Centro: " + reportData.get(i).get("centro_cjdr") +
                    ", Egresos: " + reportData.get(i).get("egresos_cjdr") +
                    ", Ingresos: " + reportData.get(i).get("ingresos_cjdr"));
        }

        // Referencias a los TextView de porcentaje
        textViewsPorcentaje[9] = findViewById(R.id.textViewAlfonsoUgartePorcentaje);
        textViewsPorcentaje[8] = findViewById(R.id.textViewMarcavallePorcentaje);
        textViewsPorcentaje[7] = findViewById(R.id.textViewPucallpaPorcentaje);
        textViewsPorcentaje[6] = findViewById(R.id.textViewEl_TamboPorcentaje);
        textViewsPorcentaje[5] = findViewById(R.id.textViewTrujilloPorcentaje);
        textViewsPorcentaje[4] = findViewById(R.id.textViewJose_QuinonesPorcentaje);
        textViewsPorcentaje[3] = findViewById(R.id.textViewMiguel_GrauPorcentaje);
        textViewsPorcentaje[2] = findViewById(R.id.textViewSanta_MargaritaPorcentaje);
        textViewsPorcentaje[1] = findViewById(R.id.textViewAnexoIIIPorcentaje);
        textViewsPorcentaje[0] = findViewById(R.id.textViewLimaPorcentaje);

        // Referencias a los TextView de nombres
        textViewsNombre[9] = findViewById(R.id.textViewAlfonso_Ugarte);
        textViewsNombre[8] = findViewById(R.id.textViewMarcavalle);
        textViewsNombre[7] = findViewById(R.id.textViewPucallpa);
        textViewsNombre[6] = findViewById(R.id.textViewEl_Tambo);
        textViewsNombre[5] = findViewById(R.id.textViewTrujillo);
        textViewsNombre[4] = findViewById(R.id.textViewJose_Quinones);
        textViewsNombre[3] = findViewById(R.id.textViewMiguel_Grau);
        textViewsNombre[2] = findViewById(R.id.textViewSanta_Margarita);
        textViewsNombre[1] = findViewById(R.id.textViewAnexoIII);
        textViewsNombre[0] = findViewById(R.id.textViewLima);

        // Crear los objetos BarEntry a partir de los datos
        ArrayList<BarEntry> entries = new ArrayList<>();

        // Calcular la población total
        float totalEgresos = 0;
        float totalIngresos = 0;
        for (HashMap<String, String> data : reportData) {
            totalEgresos += safeParseFloat(data.get("egresos_cjdr"));
            totalIngresos += safeParseFloat(data.get("ingresos_cjdr"));
        }

        // Asignar los datos a los TextView y crear las entradas del gráfico
        for (int i = 0; i < reportData.size(); i++) {
            HashMap<String, String> data = reportData.get(i);
            float egresos = safeParseFloat(data.get("egresos_cjdr"));
            float ingresos = safeParseFloat(data.get("ingresos_cjdr"));
            float total = ingresos + egresos;

            // Evitar NaN y Infinity
            float porcentajeEgresos = total == 0 ? 0 : (egresos / total) * 100;
            float porcentajeIngresos = total == 0 ? 0 : (ingresos / total) * 100;

            entries.add(new BarEntry(i, new float[]{porcentajeIngresos, porcentajeEgresos}));
            textViewsPorcentaje[i].setText(String.format("Egresos: %.0f, \n Ingresos: %.0f", egresos, ingresos));
            textViewsNombre[i].setText(data.get("centro_cjdr"));
        }

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(ContextCompat.getColor(this, R.color.Pronacej2));
        colors.add(ContextCompat.getColor(this, R.color.Pronacej1));

        // Crear el conjunto de datos para el gráfico de barras
        BarDataSet dataSet = new BarDataSet(entries, "");
        dataSet.setColors(colors);
        dataSet.setValueTextSize(12f);
        dataSet.setValueTextColor(ContextCompat.getColor(this, R.color.black));
        dataSet.setStackLabels(new String[]{"Ingresos", "Egresos"});

        // Asigna el ValueFormatter al dataSet para mostrar porcentajes
        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.format("%.1f%%", value); // Mostrar porcentajes en las barras
            }
        });

        // Crear los datos del gráfico de barras
        BarData barData = new BarData(dataSet);

        // Configurar la leyenda
        barChart.getLegend().setEnabled(true);
        barChart.getLegend().setTextSize(8f);

        // Configurar descripción del gráfico
        barChart.getDescription().setEnabled(false);

        // Añadir margen adicional a la izquierda y derecha del gráfico
        barChart.setExtraLeftOffset(10f); // Ajusta el valor según sea necesario
        barChart.setExtraRightOffset(50f); // Añadir margen derecho

        // Configurar el eje X
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return reportData.get((int) value).get("centro_cjdr");
            }
        });
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setLabelCount(reportData.size());
        xAxis.setLabelRotationAngle(0);  // Cambia el ángulo de rotación a 0 para que las etiquetas estén en horizontal

        YAxis yAxisLeft = barChart.getAxisLeft();
        yAxisLeft.setGranularity(1f);
        yAxisLeft.setAxisMinimum(0f);

        YAxis yAxisRight = barChart.getAxisRight();
        yAxisRight.setEnabled(false);

        // Establecer los datos en el gráfico y refrescar
        barChart.setData(barData);
        barChart.invalidate(); // refrescar
    }

    private float safeParseFloat(String value) {
        try {
            if (value == null || value.equals("null") || value.isEmpty()) {
                return 0;
            }
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {
            Log.e("ResultadosEstadoCjrd", "Error al parsear float: " + value, e);
            return 0;
        }
    }
}

package com.carlitos.Pronacej.ResultadosSoa;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.carlitos.Pronacej.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class ResultadosSaludMentalSoa extends AppCompatActivity {

    private int salud_si;
    private int salud_no;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultados_salud_mental_soa);

        // Obtener los valores de salud mental desde el intent
        salud_si = getIntent().getIntExtra("salud_si", 0);
        salud_no = getIntent().getIntExtra("salud_no", 0);

        int totalSalud = salud_no + salud_si;

        // Calcular los porcentajes
        double porcentajesalud_si = (double) salud_si / totalSalud * 100;
        double porcentajesalud_no = (double) salud_no / totalSalud * 100;

        // Configurar el gráfico de pastel
        PieChart pieChart = findViewById(R.id.pieChart);
        pieChart.getDescription().setEnabled(false);

        // Crear las entradas para el gráfico de pastel
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(salud_si, "Salud Sí"));
        entries.add(new PieEntry(salud_no, "Salud No"));

        // Crear el conjunto de datos del gráfico de pastel
        PieDataSet dataSet = new PieDataSet(entries, "Estado de Salud Mental");
        dataSet.setColors(getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_red_light));

        // Configurar el tamaño del texto dentro del gráfico de pastel
        dataSet.setValueTextSize(12f);

        // Configurar la leyenda
        Legend legend = pieChart.getLegend();
        legend.setEnabled(true); // Habilitar la leyenda

        // Agregar los datos al gráfico de pastel
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.invalidate(); // Refrescar el gráfico


        ((TextView) findViewById(R.id.textViewsalud_siPorcentaje)).setText(String.format("%.2f%%", porcentajesalud_si));
        ((TextView) findViewById(R.id.textViewsalud_si)).setText("Salud mental");


        ((TextView) findViewById(R.id.textViewsalud_noPorcentaje)).setText(String.format("%.2f%%", porcentajesalud_no));
        ((TextView) findViewById(R.id.textViewsalud_no)).setText("Sin salud mental");

    }
}

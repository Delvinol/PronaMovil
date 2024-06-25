package com.carlitos.Pronacej.ResultadosCjrd;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.carlitos.Pronacej.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class ResultadoJusticiaTerapeuticaCjdr extends AppCompatActivity {

    private int justicia_si;
    private int justicia_no;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_justicia_terapeutica_cjdr);

        justicia_si = getIntent().getIntExtra("justicia_si", 0);
        justicia_no = getIntent().getIntExtra("justicia_no", 0);

        // Configurar el gráfico de pastel
        PieChart pieChart = findViewById(R.id.pieChart);
        pieChart.getDescription().setEnabled(false);

        // Crear las entradas para el gráfico de pastel
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(justicia_si, "Justicia Sí"));
        entries.add(new PieEntry(justicia_no, "Justicia No"));

        // Configurar los colores para las entradas
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(android.R.color.holo_green_light));
        colors.add(getResources().getColor(android.R.color.holo_red_light));

        // Crear el conjunto de datos del gráfico de pastel
        PieDataSet dataSet = new PieDataSet(entries, "Resultados de Justicia Terapéutica");
        dataSet.setColors(colors);

        // Configurar el tamaño del texto dentro de las entradas
        dataSet.setValueTextSize(12f);

        // Agregar leyenda
        PieData data = new PieData(dataSet);
        pieChart.setData(data);

        // Configurar la leyenda
        pieChart.getLegend().setEnabled(true);

        pieChart.invalidate(); // Refrescar el gráfico

        // Calcular los porcentajes
        int total = justicia_si + justicia_no;
        float porcentaje_si = ((float) justicia_si / total) * 100;
        float porcentaje_no = ((float) justicia_no / total) * 100;

        ((TextView) findViewById(R.id.textView14Porcentaje)).setText(String.format("%.2f%%", porcentaje_si));
        ((TextView) findViewById(R.id.textView14)).setText("Si tiene justicia");
        ((TextView) findViewById(R.id.textViewjusticia_noPorcentaje)).setText(String.format("%.2f%%", porcentaje_no));
        ((TextView) findViewById(R.id.textViewjusticia_no)).setText("No tiene justicia");
    }
}

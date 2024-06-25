package com.carlitos.Pronacej.ResultadosCjrd;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.carlitos.Pronacej.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class ResultadosSituacionLaboralActualCjdr extends AppCompatActivity {

    private int inser_labo_interna;
    private int inser_labo_externa;
    private int no_trabaja;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultados_situacion_laboral_cjdr);

        // Obtener los valores de las variables desde el intent
        inser_labo_interna = getIntent().getIntExtra("inser_labo_interna", 0);
        inser_labo_externa = getIntent().getIntExtra("inser_labo_externa", 0);
        no_trabaja = getIntent().getIntExtra("no_trabaja", 0);

        int totalSLA = inser_labo_externa + inser_labo_interna + no_trabaja;

        // Calcular los porcentajes
        double porcentajeinser_labo_interna = (double) inser_labo_interna / totalSLA * 100;
        double porcentajeinser_labo_externa = (double) inser_labo_externa / totalSLA * 100;
        double porcentajeno_trabaja = (double) no_trabaja / totalSLA * 100;

        ((TextView) findViewById(R.id.textViewinser_labo_internaPorcentaje)).setText(String.format("%.2f%%", porcentajeinser_labo_interna));
        ((TextView) findViewById(R.id.textViewinser_labo_interna)).setText("Trabajo interno");

        ((TextView) findViewById(R.id.textViewinser_labo_externaPorcentaje)).setText(String.format("%.2f%%", porcentajeinser_labo_externa));
        ((TextView) findViewById(R.id.textViewinser_labo_externa)).setText("Trabajo externo");

        ((TextView) findViewById(R.id.textViewno_trabajaPorcentaje)).setText(String.format("%.2f%%", porcentajeno_trabaja));
        ((TextView) findViewById(R.id.textViewno_trabaja)).setText("No trabaja");

        // Configurar el gráfico de barras
        BarChart barChart = findViewById(R.id.barChart);
        barChart.getDescription().setEnabled(false);

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, inser_labo_interna));
        entries.add(new BarEntry(1, inser_labo_externa));
        entries.add(new BarEntry(2, no_trabaja));

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.Pronacej6));
        colors.add(getResources().getColor(R.color.Pronacej2));
        colors.add(getResources().getColor(R.color.Pronacej3));

        BarDataSet dataSet = new BarDataSet(entries, "Situación Laboral Actual");
        dataSet.setColors(colors);
        dataSet.setValueTextSize(12f);

        BarData barData = new BarData(dataSet);
        barChart.setData(barData);
        barChart.invalidate(); // Refrescar el gráfico

        // Customize X axis
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                switch ((int) value) {
                    case 0:
                        return "Interna";
                    case 1:
                        return "Externa";
                    case 2:
                        return "No Trabaja";
                    default:
                        return "";
                }
            }
        });

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // Intervalo mínimo entre etiquetas
        xAxis.setLabelCount(3); // Asegura que solo haya 3 etiquetas

        // Customize legend
        Legend legend = barChart.getLegend();
        legend.setEnabled(true);
        legend.setTextSize(12f);
        legend.setForm(Legend.LegendForm.SQUARE);
        legend.setFormSize(12f);
        legend.setXEntrySpace(10f);
        legend.setTextColor(getResources().getColor(R.color.black));
    }
}

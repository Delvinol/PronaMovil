package com.carlitos.Pronacej.ResultadosCjrd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.carlitos.Pronacej.ActivitysPadres.CategoriaMenu;
import com.carlitos.Pronacej.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

public class ResultadosLaboralIngresoCjdr extends AppCompatActivity {

    private int trabaja_formal;
    private int trabaja_informal;
    private int trabaja_sin;

    private TextView textViewTotalCantidad;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultados_laboral_ingreso_cjdr);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        // Referencia al TextView del total de cantidad
        textViewTotalCantidad = findViewById(R.id.textViewTotalCantidad);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(ResultadosLaboralIngresoCjdr.this, CategoriaMenu.class);
                startActivity(intentHome);
            }
        });
        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Llamar al método onBackPressed para ir atrás
            }
        });

        Intent intent = getIntent();
        int trabaja_formal = intent.getIntExtra("trabaja_formal", 0);
        int trabaja_informal = intent.getIntExtra("trabaja_informal", 0);
        int trabaja_sin = intent.getIntExtra("trabaja_sin", 0);

        int totalSLA = trabaja_formal + trabaja_informal + trabaja_sin;
        textViewTotalCantidad.setText(String.format("Total: %d", Math.round(totalSLA)));

// Calcular los porcentajes
        double porcentajeTrabajaInformal = (double) trabaja_informal / totalSLA * 100;
        double porcentajeTrabajaFormal = (double) trabaja_formal / totalSLA * 100;
        double porcentajeTrabajaSin = (double) trabaja_sin / totalSLA * 100;

        ((TextView) findViewById(R.id.textViewinser_labo_internaPorcentaje)).setText(String.format("%d", (int) Math.round(trabaja_informal)));
        ((TextView) findViewById(R.id.textViewinser_labo_interna)).setText("Trabaja Informalmente");

        ((TextView) findViewById(R.id.textViewinser_labo_externaPorcentaje)).setText(String.format("%d", (int) Math.round(trabaja_formal)));
        ((TextView) findViewById(R.id.textViewinser_labo_externa)).setText("Trabaja Formalmente");

        ((TextView) findViewById(R.id.textView_sin_Porcentaje)).setText(String.format("%d", (int) Math.round(trabaja_sin)));
        ((TextView) findViewById(R.id.textViewinser_sin)).setText("Sin Trabajo");


        // Configurar el gráfico de pastel
        PieChart pieChart = findViewById(R.id.pieChart);
        pieChart.getDescription().setEnabled(false);
        pieChart.setUsePercentValues(true);

        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry((float) porcentajeTrabajaInformal, "Trabaja Informalmente"));
        entries.add(new PieEntry((float) porcentajeTrabajaFormal, "Trabaja Formalmente"));
        entries.add(new PieEntry((float) porcentajeTrabajaSin, "Sin Trabajo"));

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.Pronacej6));
        colors.add(getResources().getColor(R.color.Pronacej2));
        colors.add(getResources().getColor(R.color.Pronacej5));

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(colors);
        dataSet.setValueTextSize(12f);
        dataSet.setValueFormatter(new PercentFormatter(pieChart));

        PieData pieData = new PieData(dataSet);
        pieChart.setData(pieData);
        pieChart.invalidate(); // Refrescar el gráfico

        // Customize legend
        Legend legend = pieChart.getLegend();
        legend.setEnabled(true);
        legend.setTextSize(12f);
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setFormSize(12f);
        legend.setXEntrySpace(10f);
        legend.setTextColor(getResources().getColor(R.color.black));
    }
}

package com.carlitos.Pronacej.ResultadosSoa;

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
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class ResultadoAdnSoa extends AppCompatActivity {

    private int adn_si;
    private int adn_no;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_adn_soa);
        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(ResultadoAdnSoa.this, CategoriaMenu.class);
                startActivity(intentHome);
            }

        });
        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Llamar al método onBackPressed para ir atrás
            }
        });

        // Obtener los datos de la actividad anterior
        adn_si = getIntent().getIntExtra("adn_si", 0);
        adn_no = getIntent().getIntExtra("adn_no", 0);

        // Calcular el total
        int total = adn_si + adn_no;

        // Calcular los porcentajes
        double por_si = (total != 0) ? (adn_si * 100.0 / total) : 0;
        double por_no = (total != 0) ? (adn_no * 100.0 / total) : 0;

        // Actualizar los TextView con los valores correctos
        TextView textView13 = findViewById(R.id.textView13);
        textView13.setText(String.format("%d", adn_si));

        TextView textView7 = findViewById(R.id.textView7);
        textView7.setText(String.format("Sí Participan"));

        TextView textView135 = findViewById(R.id.textView135);
        textView135.setText(String.format("%d", adn_no));

        TextView textView75 = findViewById(R.id.textView75);
        textView75.setText(String.format("No Participan"));

        TextView txtSummary = findViewById(R.id.textView28);
        String summaryText = String.format(
                "El gráfico muestra que existe un %.2f%% (%d) de personas que NO tienen ADN del familiar mientras que el resto %.2f%% (%d) tienen ADN familiar.",
                por_no, adn_no, por_si, adn_si
        );
        txtSummary.setText(summaryText);

        // Configurar el gráfico de pie
        PieChart pieChart = findViewById(R.id.pieChart);
        pieChart.getDescription().setEnabled(false);
        pieChart.setUsePercentValues(false);
        pieChart.setEntryLabelColor(getResources().getColor(android.R.color.black));
        pieChart.setEntryLabelTextSize(12f);

        // Crear las entradas para el gráfico de pie
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry((float) por_si, "Si Participan"));
        entries.add(new PieEntry((float) por_no, "No Participan"));

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(android.R.color.holo_green_light));
        colors.add(getResources().getColor(android.R.color.holo_red_light));

        // Crear el conjunto de datos del gráfico de pie
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(colors);
        dataSet.setValueTextSize(12f);
        dataSet.setValueFormatter(new PercentValueFormatter());



        // Agregar los datos al gráfico de pie
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.invalidate();


    }
    public class PercentValueFormatter extends com.github.mikephil.charting.formatter.ValueFormatter {
        public String getFormattedValue(float value) {
            return String.format("%.1f%%", value);
        }
    }
}

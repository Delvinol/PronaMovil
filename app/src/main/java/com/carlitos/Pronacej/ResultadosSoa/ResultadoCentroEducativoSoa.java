package com.carlitos.Pronacej.ResultadosSoa;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.carlitos.Pronacej.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

public class ResultadoCentroEducativoSoa extends AppCompatActivity {

    private int cebr;
    private int ceba;
    private int cepre;
    private int academia;
    private int cetpro;
    private int instituto;
    private int universidad;
    private int ninguno;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_centro_educativo_soa);

        // Obtener los valores de las variables desde el intent
        cebr = getIntent().getIntExtra("cebr", 0);
        ceba = getIntent().getIntExtra("ceba", 0);
        cepre = getIntent().getIntExtra("cepre", 0);
        academia = getIntent().getIntExtra("academia", 0);
        cetpro = getIntent().getIntExtra("cetpro", 0);
        instituto = getIntent().getIntExtra("instituto", 0);
        universidad = getIntent().getIntExtra("universidad", 0);
        ninguno = getIntent().getIntExtra("ninguno", 0);

        // Calcular el total de personas
        int totalPersonas = cebr + ceba + cepre + academia + cetpro + instituto + universidad + ninguno;

        // Calcular los porcentajes
        double porcentajeCebr = (double) cebr / totalPersonas * 100;
        double porcentajeCeba = (double) ceba / totalPersonas * 100;
        double porcentajeCepre = (double) cepre / totalPersonas * 100;
        double porcentajeAcademia = (double) academia / totalPersonas * 100;
        double porcentajeCetpro = (double) cetpro / totalPersonas * 100;
        double porcentajeInstituto = (double) instituto / totalPersonas * 100;
        double porcentajeUniversidad = (double) universidad / totalPersonas * 100;
        double porcentajeNinguno = (double) ninguno / totalPersonas * 100;

        // Configurar TextViews para mostrar los porcentajes y nombres
        ((TextView) findViewById(R.id.textViewcebrPorcentaje)).setText(String.format("%.2f%%", porcentajeCebr));
        ((TextView) findViewById(R.id.textViewcebr)).setText("CEBR");

        ((TextView) findViewById(R.id.textViewcebaPorcentaje)).setText(String.format("%.2f%%", porcentajeCeba));
        ((TextView) findViewById(R.id.textViewceba)).setText("CEBA");

        ((TextView) findViewById(R.id.textViewceprePorcentaje)).setText(String.format("%.2f%%", porcentajeCepre));
        ((TextView) findViewById(R.id.textViewcepre)).setText("CEPRE");

        ((TextView) findViewById(R.id.textViewacademiaPorcentaje)).setText(String.format("%.2f%%", porcentajeAcademia));
        ((TextView) findViewById(R.id.textViewacademia)).setText("Academia");

        ((TextView) findViewById(R.id.textViewcetproPorcentaje)).setText(String.format("%.2f%%", porcentajeCetpro));
        ((TextView) findViewById(R.id.textViewcetpro)).setText("CETPRO");

        ((TextView) findViewById(R.id.textViewinstitutoPorcentaje)).setText(String.format("%.2f%%", porcentajeInstituto));
        ((TextView) findViewById(R.id.textViewinstituto)).setText("Instituto");

        ((TextView) findViewById(R.id.textViewuniversidadPorcentaje)).setText(String.format("%.2f%%", porcentajeUniversidad));
        ((TextView) findViewById(R.id.textViewuniversidad)).setText("Universidad");

        ((TextView) findViewById(R.id.textViewningunoPorcentaje)).setText(String.format("%.2f%%", porcentajeNinguno));
        ((TextView) findViewById(R.id.textViewninguno)).setText("Ninguno");

        // Configurar el gráfico de barras
        BarChart barChart = findViewById(R.id.barChart);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, cebr));
        entries.add(new BarEntry(1, ceba));
        entries.add(new BarEntry(2, cepre));
        entries.add(new BarEntry(3, academia));
        entries.add(new BarEntry(4, cetpro));
        entries.add(new BarEntry(5, instituto));
        entries.add(new BarEntry(6, universidad));
        entries.add(new BarEntry(7, ninguno));

        // Configurar los nombres en la leyenda
        String[] legendLabels = {"CEBR", "CEBA", "CEPRE", "Academia", "CETPRO", "Instituto", "Universidad", "Ninguno"};

        BarDataSet dataSet = new BarDataSet(entries, "Centro Educativo");

        // Asignar colores a las barras
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.Pronacej1));
        colors.add(getResources().getColor(R.color.Pronacej2));
        colors.add(getResources().getColor(R.color.Pronacej3));
        colors.add(getResources().getColor(R.color.Pronacej4));
        colors.add(getResources().getColor(R.color.Pronacej5));
        colors.add(getResources().getColor(R.color.Pronacej6));
        colors.add(getResources().getColor(R.color.Pronacej7));
        colors.add(getResources().getColor(R.color.Pronacej8));
        dataSet.setColors(colors);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);

        BarData barData = new BarData(dataSets);
        barChart.setData(barData);

        barChart.getDescription().setEnabled(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new CustomAxisValueFormatter(legendLabels, barChart));

        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setDrawGridLines(false);

        barChart.getAxisRight().setEnabled(false);

        Legend legend = barChart.getLegend();
        legend.setTextSize(12f);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        // Habilitar zoom y scroll
        barChart.setScaleEnabled(true);
        barChart.setPinchZoom(true);

        barChart.invalidate();
    }

    // Clase formateadora personalizada para el eje X
    class CustomAxisValueFormatter extends IndexAxisValueFormatter {

        private final String[] originalLabels;
        private final BarChart barChart;

        public CustomAxisValueFormatter(String[] labels, BarChart barChart) {
            super(labels);
            this.originalLabels = labels;
            this.barChart = barChart;
        }

        @Override
        public String getFormattedValue(float value) {
            int index = (int) value;
            if (index >= 0 && index < originalLabels.length) {
                String label = originalLabels[index];
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

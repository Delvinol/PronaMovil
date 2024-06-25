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

public class ResultadoAgresoresSexualesCjdr extends AppCompatActivity {

    private int agresor_si;
    private int agresor_no;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_agresores_sexuales_cjdr);
        TextView txtSummary = findViewById(R.id.textView28);
        TextView txtPorcentajeSi = findViewById(R.id.textView13);
        TextView txtMensajeSi = findViewById(R.id.textView7);
        TextView txtPorcentajeNo = findViewById(R.id.textView135);
        TextView txtMensajeNo = findViewById(R.id.textView75);

        // Obtener los valores de las variables desde el intent
        agresor_si = getIntent().getIntExtra("agresor_si", 18);
        agresor_no = getIntent().getIntExtra("agresor_no", 72);

        // Calcular el total
        int total = agresor_si + agresor_no;

        // Calcular los porcentajes
        double porcentajeAgresorSi = (total != 0) ? (agresor_si * 100.0 / total) : 0;
        double porcentajeAgresorNo = (total != 0) ? (agresor_no * 100.0 / total) : 0;

        // Configurar los textos de los TextView
        txtPorcentajeSi.setText(String.format("%.0f%%", porcentajeAgresorSi));
        txtMensajeSi.setText(String.format("Agresor; %d personas", agresor_si));
        txtPorcentajeNo.setText(String.format("%.0f%%", porcentajeAgresorNo));
        txtMensajeNo.setText(String.format("No Agresor; %d personas", agresor_no));

        // Configurar el mensaje en el TextView de resumen
        String summaryText = String.format(
                "El gráfico muestra que existe un %.0f%% (%d) de personas que tienen indicios de agresión mientras que el resto %.0f%% (%d) no tienen historia agresiva.",
                porcentajeAgresorSi, agresor_si, porcentajeAgresorNo, agresor_no
        );
        txtSummary.setText(summaryText);

        // Configurar el gráfico de barras
        BarChart barChart = findViewById(R.id.barChart);
        barChart.getDescription().setEnabled(false);

        // Crear las entradas para el gráfico de barras
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, agresor_si));
        entries.add(new BarEntry(1, agresor_no));

        // Configurar los colores para las barras
        int[] colors = {
                getResources().getColor(android.R.color.holo_red_light),  // Agresor
                getResources().getColor(android.R.color.holo_green_light)     // No Agresor
        };

        // Crear el conjunto de datos del gráfico de barras
        BarDataSet dataSet = new BarDataSet(entries, "");
        dataSet.setColors(colors);
        dataSet.setValueTextSize(12f);

        // Configurar el eje X
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new XAxisFormatter());
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(2);

        // Configurar la leyenda
        Legend legend = barChart.getLegend();
        legend.setEnabled(true); // Habilitar la leyenda
        legend.setForm(Legend.LegendForm.SQUARE);
        legend.setTextSize(12f);
        legend.setXEntrySpace(20f);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        // Agregar los datos al gráfico de barras
        BarData data = new BarData(dataSet);
        barChart.setData(data);
        barChart.invalidate(); // Refrescar el gráfico
    }

    private class XAxisFormatter extends ValueFormatter {
        @Override
        public String getFormattedValue(float value) {
            int intValue = Math.round(value);
            if (intValue == 0) {
                return "Agresor";
            } else {
                return "No Agresor";
            }
        }
    }
}

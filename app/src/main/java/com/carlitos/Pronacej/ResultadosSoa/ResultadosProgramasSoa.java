package com.carlitos.Pronacej.ResultadosSoa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.carlitos.Pronacej.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class ResultadosProgramasSoa extends AppCompatActivity {

    private int participa_programa_uno;
    private int participa_programa_dos;
    private int participa_programa_tres;
    private int participa_programa_cuatro;
    private int participa_programa_cinco;
    private int participa_programa_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_programacion_soa);

        //Obtener los valores de los programas
        Intent intent = getIntent();
        participa_programa_uno = intent.getIntExtra("participa_programa_uno", 0);
        participa_programa_dos = intent.getIntExtra("participa_programa_dos", 0);
        participa_programa_tres = intent.getIntExtra("participa_programa_tres", 0);
        participa_programa_cuatro = intent.getIntExtra("participa_programa_cuatro", 0);
        participa_programa_cinco = intent.getIntExtra("participa_programa_cinco", 0);
        participa_programa_no = intent.getIntExtra("participa_programa_no", 0);

        // Calcular el total de participantes
        int totalParticipantes = participa_programa_uno + participa_programa_dos + participa_programa_tres + participa_programa_cuatro + participa_programa_cinco + participa_programa_no;

        // Calcular los porcentajes
        double porcentajeUno = (double) participa_programa_uno / totalParticipantes * 100;
        double porcentajeDos = (double) participa_programa_dos / totalParticipantes * 100;
        double porcentajeTres = (double) participa_programa_tres / totalParticipantes * 100;
        double porcentajeCuatro = (double) participa_programa_cuatro / totalParticipantes * 100;
        double porcentajeCinco = (double) participa_programa_cinco / totalParticipantes * 100;
        double porcentajeNo = (double) participa_programa_no / totalParticipantes * 100;

        // Configurar el gráfico de barras
        BarChart barChart = findViewById(R.id.barChart);
        barChart.getDescription().setEnabled(false);
        barChart.setDrawGridBackground(false);

        // Configurar los datos para el gráfico
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, participa_programa_uno));
        entries.add(new BarEntry(1f, participa_programa_dos));
        entries.add(new BarEntry(2f, participa_programa_tres));
        entries.add(new BarEntry(3f, participa_programa_cuatro));
        entries.add(new BarEntry(4f, participa_programa_cinco));
        entries.add(new BarEntry(5f, participa_programa_no));

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.Pronacej5));
        colors.add(getResources().getColor(R.color.Pronacej4));
        colors.add(getResources().getColor(R.color.Pronacej8));
        colors.add(getResources().getColor(R.color.Pronacej6));
        colors.add(getResources().getColor(R.color.Pronacej3));
        colors.add(getResources().getColor(R.color.Pronacej2));

        BarDataSet barDataSet = new BarDataSet(entries, "Participación en programas");
        barDataSet.setColors(colors);
        barDataSet.setValueTextSize(12f);

        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.5f);

        // Configurar ejes y leyenda
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        Legend legend = barChart.getLegend();
        legend.setWordWrapEnabled(true);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        // Establecer los datos en el gráfico
        barChart.setData(barData);
        barChart.invalidate();

        ((TextView) findViewById(R.id.textView14Porcentaje)).setText(String.format("%.2f%%", porcentajeUno));
        ((TextView) findViewById(R.id.textView14)).setText("Programa uno");
        ((TextView) findViewById(R.id.textViewparticipa_programa_dosPorcentaje)).setText(String.format("%.2f%%", porcentajeDos));
        ((TextView) findViewById(R.id.textViewparticipa_programa_dos)).setText("Programa dos");
        ((TextView) findViewById(R.id.textViewparticipa_programa_tresPorcentaje)).setText(String.format("%.2f%%", porcentajeTres));
        ((TextView) findViewById(R.id.textViewparticipa_programa_tres)).setText("Programa tres");
        ((TextView) findViewById(R.id.textViewparticipa_programa_cuatroPorcentaje)).setText(String.format("%.2f%%", porcentajeCuatro));
        ((TextView) findViewById(R.id.textViewparticipa_programa_cuatro)).setText("Programa cuatro");
        ((TextView) findViewById(R.id.textViewQuincePorcentaje)).setText(String.format("%.2f%%", porcentajeCinco));
        ((TextView) findViewById(R.id.textViewCatorce)).setText("Programa cinco");
        ((TextView) findViewById(R.id.textViewparticipa_programa_noPorcentaje)).setText(String.format("%.2f%%", porcentajeNo));
        ((TextView) findViewById(R.id.textViewparticipa_programa_no)).setText("Sin programa");
    }
}

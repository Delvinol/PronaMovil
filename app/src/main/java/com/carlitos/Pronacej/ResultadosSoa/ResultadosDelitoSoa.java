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
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ResultadosDelitoSoa extends AppCompatActivity {

    private int autoaborto;
    private int exposicion_peligro;
    private int feminicidio;
    private int homicidio_c;
    private int homicidio_s;
    private int lesiones_g;
    private int lesiones_l;
    private int parricidio;
    private int sicariato;
    private int otros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_delito_soa);

        // Obtener los valores de los delitos específicos desde el intent
        Intent intent = getIntent();
        autoaborto = intent.getIntExtra("autoaborto", 0);
        exposicion_peligro = intent.getIntExtra("exposicion_peligro", 0);
        feminicidio = intent.getIntExtra("feminicidio", 0);
        homicidio_c = intent.getIntExtra("homicidio_c", 0);
        homicidio_s = intent.getIntExtra("homicidio_s", 0);
        lesiones_g = intent.getIntExtra("lesiones_g", 0);
        lesiones_l = intent.getIntExtra("lesiones_l", 0);
        parricidio = intent.getIntExtra("parricidio", 0);
        sicariato = intent.getIntExtra("sicariato", 0);
        otros = intent.getIntExtra("otros", 0);

        // Calcular el total de delitos
        int totalDelitos = autoaborto + exposicion_peligro + feminicidio + homicidio_c + homicidio_s +
                lesiones_g + lesiones_l + parricidio + sicariato + otros;

        // Calcular los porcentajes
        double porcentajeAutoaborto = (double) autoaborto / totalDelitos * 100;
        double porcentajeExposicionPeligro = (double) exposicion_peligro / totalDelitos * 100;
        double porcentajeFeminicidio = (double) feminicidio / totalDelitos * 100;
        double porcentajeHomicidioC = (double) homicidio_c / totalDelitos * 100;
        double porcentajeHomicidioS = (double) homicidio_s / totalDelitos * 100;
        double porcentajeLesionesG = (double) lesiones_g / totalDelitos * 100;
        double porcentajeLesionesL = (double) lesiones_l / totalDelitos * 100;
        double porcentajeParricidio = (double) parricidio / totalDelitos * 100;
        double porcentajeSicariato = (double) sicariato / totalDelitos * 100;
        double porcentajeOtros = (double) otros / totalDelitos * 100;

        // Construir el texto
        String texto = String.format("Autoaborto: %d personas (%.2f%%) " +
                        "Exposición al Peligro: %d personas (%.2f%%) " +
                        "Feminicidio: %d personas (%.2f%%) " +
                        "Homicidio Calificado: %d personas (%.2f%%) " +
                        "Homicidio Simple: %d personas (%.2f%%) " +
                        "Lesiones Graves: %d personas (%.2f%%) " +
                        "Lesiones Leves: %d personas (%.2f%%) " +
                        "Parricidio: %d personas (%.2f%%) " +
                        "Sicariato: %d personas (%.2f%%) " +
                        "Otros: %d personas (%.2f%%)",
                autoaborto, porcentajeAutoaborto,
                exposicion_peligro, porcentajeExposicionPeligro,
                feminicidio, porcentajeFeminicidio,
                homicidio_c, porcentajeHomicidioC,
                homicidio_s, porcentajeHomicidioS,
                lesiones_g, porcentajeLesionesG,
                lesiones_l, porcentajeLesionesL,
                parricidio, porcentajeParricidio,
                sicariato, porcentajeSicariato,
                otros, porcentajeOtros);

        ((TextView) findViewById(R.id.textViewAlfonsoUgartePorcentaje)).setText(String.format("%.2f%%", porcentajeAutoaborto));
        ((TextView) findViewById(R.id.textViewAlfonso_Ugarte)).setText("Autoaborto");

        ((TextView) findViewById(R.id.textViewMarcavallePorcentaje)).setText(String.format("%.2f%%", porcentajeExposicionPeligro));
        ((TextView) findViewById(R.id.textViewMarcavalle)).setText("Exposición al Peligro");

        ((TextView) findViewById(R.id.textViewPucallpaPorcentaje)).setText(String.format("%.2f%%", porcentajeFeminicidio));
        ((TextView) findViewById(R.id.textViewPucallpa)).setText("Feminicidio");

        ((TextView) findViewById(R.id.textViewEl_TamboPorcentaje)).setText(String.format("%.2f%%", porcentajeHomicidioC));
        ((TextView) findViewById(R.id.textViewEl_Tambo)).setText("Homicidio Calificado");

        ((TextView) findViewById(R.id.textViewTrujilloPorcentaje)).setText(String.format("%.2f%%", porcentajeHomicidioS));
        ((TextView) findViewById(R.id.textViewTrujillo)).setText("Homicidio Simple");

        ((TextView) findViewById(R.id.textViewJose_QuinonesPorcentaje)).setText(String.format("%.2f%%", porcentajeLesionesG));
        ((TextView) findViewById(R.id.textViewJose_Quinones)).setText("Lesiones Graves");

        ((TextView) findViewById(R.id.textViewMiguel_GrauPorcentaje)).setText(String.format("%.2f%%", porcentajeLesionesL));
        ((TextView) findViewById(R.id.textViewMiguel_Grau)).setText("Lesiones Leves");

        ((TextView) findViewById(R.id.textViewSanta_MargaritaPorcentaje)).setText(String.format("%.2f%%", porcentajeParricidio));
        ((TextView) findViewById(R.id.textViewSanta_Margarita)).setText("Parricidio");

        ((TextView) findViewById(R.id.textViewAnexoIIIPorcentaje)).setText(String.format("%.2f%%", porcentajeSicariato));
        ((TextView) findViewById(R.id.textViewAnexoIII)).setText("Sicariato");
        ((TextView) findViewById(R.id.textViewLimaPorcentaje)).setText(String.format("%.2f%%", porcentajeOtros));
        ((TextView) findViewById(R.id.textViewLima)).setText("Otros");


        // Mostrar el texto en el TextView
        TextView textView28 = findViewById(R.id.textView28);
        textView28.setText(texto);

        // Configurar el gráfico de barras
        BarChart barChart = findViewById(R.id.barChart);
        barChart.getDescription().setEnabled(false);
        barChart.setDrawGridBackground(false);

        // Configurar los datos para el gráfico
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, autoaborto));
        entries.add(new BarEntry(1, exposicion_peligro));
        entries.add(new BarEntry(2, feminicidio));
        entries.add(new BarEntry(3, homicidio_c));
        entries.add(new BarEntry(4, homicidio_s));
        entries.add(new BarEntry(5, lesiones_g));
        entries.add(new BarEntry(6, lesiones_l));
        entries.add(new BarEntry(7, parricidio));
        entries.add(new BarEntry(8, sicariato));
        entries.add(new BarEntry(9, otros));

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.Pronacej1));
        colors.add(getResources().getColor(R.color.Pronacej2));
        colors.add(getResources().getColor(R.color.Pronacej3));
        colors.add(getResources().getColor(R.color.Pronacej4));
        colors.add(getResources().getColor(R.color.Pronacej5));
        colors.add(getResources().getColor(R.color.Pronacej6));
        colors.add(getResources().getColor(R.color.Pronacej7));
        colors.add(getResources().getColor(R.color.Pronacej8));
        colors.add(getResources().getColor(R.color.Pronacej9));
        colors.add(getResources().getColor(R.color.Pronacej10));

        BarDataSet barDataSet = new BarDataSet(entries, "Lista de Delitos Específicos");
        barDataSet.setColors(colors);
        barDataSet.setValueTextSize(12f);

        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.5f);

        // Configurar ejes y leyenda
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{
                "Autoaborto", "Exposición Peligro", "Feminicidio", "Homicidio C",
                "Homicidio S", "Lesiones G", "Lesiones L", "Parricidio",
                "Sicariato", "Otros"}));

        Legend legend = barChart.getLegend();
        legend.setWordWrapEnabled(true);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        // Establecer los datos en el gráfico
        barChart.setData(barData);
        barChart.invalidate();
    }
}
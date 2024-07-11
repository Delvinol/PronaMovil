package com.carlitos.Pronacej.ResultadosSoa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.carlitos.Pronacej.ActivitysPadres.CategoriaMenu;
import com.carlitos.Pronacej.R;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.DecimalFormat;
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
    private TextView textViewTotalCantidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_delito_soa);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(ResultadosDelitoSoa.this, CategoriaMenu.class);
                startActivity(intentHome);
            }

        });
        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Llamar al método onBackPressed para ir atrás
            }
        });

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

        // Referencia al TextView del total de cantidad
        textViewTotalCantidad = findViewById(R.id.textViewTotalCantidad);

        // Calcular el total de delitos
        int totalDelitos = autoaborto + exposicion_peligro + feminicidio + homicidio_c + homicidio_s +
                lesiones_g + lesiones_l + parricidio + sicariato + otros;
        textViewTotalCantidad.setText(String.format("Total: %d", Math.round(totalDelitos)));
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

        // Configurar los TextView para mostrar los números
        ((TextView) findViewById(R.id.textViewAlfonsoUgartePorcentaje)).setText(String.valueOf(autoaborto));
        ((TextView) findViewById(R.id.textViewAlfonso_Ugarte)).setText("Autoaborto");

        ((TextView) findViewById(R.id.textViewMarcavallePorcentaje)).setText(String.valueOf(exposicion_peligro));
        ((TextView) findViewById(R.id.textViewMarcavalle)).setText("Exposición al Peligro");

        ((TextView) findViewById(R.id.textViewPucallpaPorcentaje)).setText(String.valueOf(feminicidio));
        ((TextView) findViewById(R.id.textViewPucallpa)).setText("Feminicidio");

        ((TextView) findViewById(R.id.textViewEl_TamboPorcentaje)).setText(String.valueOf(homicidio_c));
        ((TextView) findViewById(R.id.textViewEl_Tambo)).setText("Homicidio Calificado");

        ((TextView) findViewById(R.id.textViewTrujilloPorcentaje)).setText(String.valueOf(homicidio_s));
        ((TextView) findViewById(R.id.textViewTrujillo)).setText("Homicidio Simple");

        ((TextView) findViewById(R.id.textViewJose_QuinonesPorcentaje)).setText(String.valueOf(lesiones_g));
        ((TextView) findViewById(R.id.textViewJose_Quinones)).setText("Lesiones Graves");

        ((TextView) findViewById(R.id.textViewMiguel_GrauPorcentaje)).setText(String.valueOf(lesiones_l));
        ((TextView) findViewById(R.id.textViewMiguel_Grau)).setText("Lesiones Leves");

        ((TextView) findViewById(R.id.textViewSanta_MargaritaPorcentaje)).setText(String.valueOf(parricidio));
        ((TextView) findViewById(R.id.textViewSanta_Margarita)).setText("Parricidio");

        ((TextView) findViewById(R.id.textViewAnexoIIIPorcentaje)).setText(String.valueOf(sicariato));
        ((TextView) findViewById(R.id.textViewAnexoIII)).setText("Sicariato");

        ((TextView) findViewById(R.id.textViewLimaPorcentaje)).setText(String.valueOf(otros));
        ((TextView) findViewById(R.id.textViewLima)).setText("Otros");

        // Configurar el gráfico de barras horizontales
        HorizontalBarChart barChart = findViewById(R.id.barChart);
        barChart.getDescription().setEnabled(false);
        barChart.setDrawGridBackground(false);

        // Configurar los datos para el gráfico en porcentaje
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, (float) porcentajeAutoaborto));
        entries.add(new BarEntry(1, (float) porcentajeExposicionPeligro));
        entries.add(new BarEntry(2, (float) porcentajeFeminicidio));
        entries.add(new BarEntry(3, (float) porcentajeHomicidioC));
        entries.add(new BarEntry(4, (float) porcentajeHomicidioS));
        entries.add(new BarEntry(5, (float) porcentajeLesionesG));
        entries.add(new BarEntry(6, (float) porcentajeLesionesL));
        entries.add(new BarEntry(7, (float) porcentajeParricidio));
        entries.add(new BarEntry(8, (float) porcentajeSicariato));
        entries.add(new BarEntry(9, (float) porcentajeOtros));

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

        BarDataSet dataSet = new BarDataSet(entries, "Delitos");
        dataSet.setColors(colors);
        BarData data = new BarData(dataSet);
        data.setBarWidth(0.9f); // Ancho de las barras
        data.setValueTextSize(14f); // Aumenta el tamaño del texto

        // Nuevo formateador personalizado
        data.setValueFormatter(new ValueFormatter() {
            private DecimalFormat mFormat = new DecimalFormat("0.0");
            @Override
            public String getFormattedValue(float value) {
                return mFormat.format(value) + "%";
            }
        });

        barChart.setData(data);
        barChart.setFitBars(true);

        // Configurar el eje X
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{
                "Autoaborto", "Exposición al Peligro", "Feminicidio", "Homicidio Calificado",
                "Homicidio Simple", "Lesiones Graves", "Lesiones Leves", "Parricidio",
                "Sicariato", "Otros"
        }));
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setLabelCount(10);

        // Configurar el eje Y (izquierda y derecha)
        YAxis yAxisLeft = barChart.getAxisLeft();
        yAxisLeft.setDrawGridLines(false);
        yAxisLeft.setGranularity(1f);
        yAxisLeft.setAxisMinimum(0f);

        YAxis yAxisRight = barChart.getAxisRight();
        yAxisRight.setEnabled(false);

        Legend legend = barChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        barChart.invalidate(); // refrescar el gráfico
    }
}
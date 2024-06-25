package com.carlitos.Pronacej.FiltrosSoa;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.carlitos.Pronacej.FiltrosCjdr.FiltroPoblacionCjdr;
import com.carlitos.Pronacej.OpcionesSoa.PoblacionSoaActivity;
import com.carlitos.Pronacej.R;
import com.carlitos.Pronacej.ResultadosCjrd.ResultadoReporteDiarioCJdr;
import com.carlitos.Pronacej.ResultadosSoa.ResultadoReporteDiarioSoa;
import com.carlitos.Pronacej.Utils.Apis;
import com.carlitos.Pronacej.Utils.CjdrService;
import com.carlitos.Pronacej.Utils.SoaService;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FiltroSimpleSoa extends AppCompatActivity {

    private EditText etFechaInicio;
    private TextView tvErrorFecha;
    private Button btnGenerarGrafico;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private String selectedDate;
    private SoaService soaService;
    private PieChart pieChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filtro_simple_soa);

        initDatePicker();
        dateButton = findViewById(R.id.etFechaInicio);
        selectedDate = getTodaysDate();
        dateButton.setText(selectedDate);

        tvErrorFecha = findViewById(R.id.tvErrorFecha);
        btnGenerarGrafico = findViewById(R.id.btnEnviar);
        soaService = Apis.getSoaService();

        btnGenerarGrafico.setOnClickListener(view -> {
            showSelectedDate(etFechaInicio);

            String fechaInicio = showSelectedDate(etFechaInicio).toString().trim();

            if (validarFechaFormato(fechaInicio)) {
                tvErrorFecha.setVisibility(View.GONE);
                llamarEndPoint(fechaInicio);
            } else {
                tvErrorFecha.setVisibility(View.VISIBLE);
            }
        });
    }

    private boolean validarFechaFormato(String fecha) {
        String pattern = "^\\d{4}-\\d{2}-\\d{2}$";
        return fecha.matches(pattern);
    }

    private void llamarEndPoint(String fecha_Seleccionada) {
        Call<List<Map<String, Object>>> call = soaService.obtenerReporteDiarioSoa(fecha_Seleccionada);
        call.enqueue(new Callback<List<Map<String, Object>>>() {
            @Override
            public void onResponse(Call<List<Map<String, Object>>> call, Response<List<Map<String, Object>>> response) {
                if (response.isSuccessful()) {
                    List<Map<String, Object>> reportes = response.body();
                    ArrayList<HashMap<String, String>> reportData = new ArrayList<>();

                    for (Map<String, Object> reporte : reportes) {
                        HashMap<String, String> entry = new HashMap<>();
                        entry.put("centro_soa", (String) reporte.get("centro_soa"));
                        entry.put("poblacion_soa", String.valueOf(reporte.get("poblacion_soa")));
                        entry.put("varones_soa", String.valueOf(reporte.get("varones_soa")));
                        entry.put("mujeres_soa", String.valueOf(reporte.get("mujeres_soa")));
                        reportData.add(entry);
                    }

                    Intent intent = new Intent(FiltroSimpleSoa.this, PoblacionSoaActivity.class);
                    intent.putExtra("reportData", reportData);
                    startActivity(intent);
                } else {
                    Log.d("API_FAILURE", "Error: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Map<String, Object>>> call, Throwable t) {
                Log.e("API_ERROR", "Error en la llamada API", t);
            }
        });
    }

    private void initDatePicker() {
        // Establece el Locale a español
        Locale locale = new Locale("es", "ES");
        Locale.setDefault(locale);

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                selectedDate = makeDateString(dayOfMonth, month, year);
                dateButton.setText(selectedDate);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, dayOfMonth);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }
    private String makeDateString(int dayOfMonth, int month, int year) {
        return getMonthFormat(month) + " " + dayOfMonth + " " + year;
    }
    private String getMonthFormat(int month) {
        if (month == 1) return "ENE";
        if (month == 2) return "FEB";
        if (month == 3) return "MAR";
        if (month == 4) return "ABR";
        if (month == 5) return "MAY";
        if (month == 6) return "JUN";
        if (month == 7) return "JUL";
        if (month == 8) return "AGO";
        if (month == 9) return "SEP";
        if (month == 10) return "OCT";
        if (month == 11) return "NOV";
        if (month == 12) return "DIC";
        return "ENE";
    }
    private String formatDateToYMD(int year, int month, int dayOfMonth) {
        return String.format("%04d-%02d-%02d", year, month, dayOfMonth);
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    public String showSelectedDate(View view) {
        String[] dateParts = selectedDate.split(" ");
        int day = Integer.parseInt(dateParts[1]);
        int month = getMonthNumber(dateParts[0]);
        int year = Integer.parseInt(dateParts[2]);
        String formattedDate = formatDateToYMD(year, month, day);

        String formato;
        formato = formattedDate;
        return formattedDate;

    }


    private int getMonthNumber(String month) {
        switch (month) {
            case "ENE": return 1;
            case "FEB": return 2;
            case "MAR": return 3;
            case "ABR": return 4;
            case "MAY": return 5;
            case "JUN": return 6;
            case "JUL": return 7;
            case "AGO": return 8;
            case "SEP": return 9;
            case "OCT": return 10;
            case "NOV": return 11;
            case "DIC": return 12;
            default: return 1;
        }
    }
    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(dayOfMonth, month, year);
    }
}

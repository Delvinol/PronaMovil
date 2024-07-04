package com.carlitos.Pronacej.OpcionesSoa;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.carlitos.Pronacej.ActivitysPadres.CategoriaMenu;
import com.carlitos.Pronacej.R;
import com.carlitos.Pronacej.ResultadosSoa.ResultadoAdnSoa;
import com.carlitos.Pronacej.ResultadosSoa.ResultadoAgresoresSexualesSoa;
import com.carlitos.Pronacej.ResultadosSoa.ResultadoIntervencionTera;
import com.carlitos.Pronacej.ResultadosSoa.ResultadoIntervencionTerapeuticaSoa;
import com.carlitos.Pronacej.ResultadosSoa.ResultadoJusticiaTerapeuticaSoa;
import com.carlitos.Pronacej.ResultadosSoa.ResultadosFirmesAdelanteSoa;
import com.carlitos.Pronacej.ResultadosSoa.ResultadosProgramasSoa;
import com.carlitos.Pronacej.Utils.Apis;
import com.carlitos.Pronacej.Utils.SoaService;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TratamientoDiferenciadoSoaActivity extends AppCompatActivity {

    private int participa_programa_uno;
    private int participa_programa_dos;
    private int participa_programa_tres;
    private int participa_programa_cuatro;
    private int participa_programa_cinco;
    private int participa_programa_no;
    private int justicia_si;
    private int justicia_no;
    private int agresor_si;
    private int agresor_no;
    private int salud_si;
    private int salud_no;
    private int adn_si;
    private int adn_no;
    private int comunidad_si;
    private int comunidad_no;
    private int firmes_aplica;
    private int firmes_no_aplica;

    private TextView tvErrorFecha;
    private Button btnGenerarGrafico;
    private SoaService soaService;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private String selectedDate;
    private CheckBox cbIncluirEstadoIng;
    private CheckBox cbIncluirEstadoAten;
    private int intervencion_aplica;
    private int intervencion_no_aplica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tratamiento_diferenciado_soa);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(TratamientoDiferenciadoSoaActivity.this, CategoriaMenu.class);
                startActivity(intentHome);
            }

        });
        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Llamar al método onBackPressed para ir atrás
            }
        });


        initDatePicker();
        dateButton = findViewById(R.id.etFechaInicio);
        selectedDate = getTodaysDate();
        dateButton.setText(selectedDate);

        cbIncluirEstadoIng = findViewById(R.id.cbIncluirEstadoIng);
        cbIncluirEstadoAten = findViewById(R.id.cbIncluirEstadoAten);

        tvErrorFecha = findViewById(R.id.tvErrorFecha);
        btnGenerarGrafico = findViewById(R.id.btnEnviar);
        soaService = Apis.getSoaService();

        btnGenerarGrafico.setOnClickListener(view -> {
            String fechaInicio = showSelectedDate().toString().trim();
            boolean incluirEstadoIng = cbIncluirEstadoIng.isChecked();

            if (validarFechaFormato(fechaInicio)) {
                tvErrorFecha.setVisibility(View.GONE);
                llamarEndPoint(fechaInicio, incluirEstadoIng);
                Toast.makeText(TratamientoDiferenciadoSoaActivity.this, "Fecha Actualizada", Toast.LENGTH_SHORT).show();

            } else {
                tvErrorFecha.setVisibility(View.VISIBLE);
            }
        });
        setupCheckBoxListeners();

        // Obtener extras del intent si existen
        Intent intent = getIntent();
        participa_programa_uno = intent.getIntExtra("participa_programa_uno", 0);
        participa_programa_dos = intent.getIntExtra("participa_programa_dos", 0);
        participa_programa_tres = intent.getIntExtra("participa_programa_tres", 0);
        participa_programa_cuatro = intent.getIntExtra("participa_programa_cuatro", 0);
        participa_programa_cinco = intent.getIntExtra("participa_programa_cinco", 0);
        participa_programa_no = intent.getIntExtra("participa_programa_no", 0);
        justicia_si = intent.getIntExtra("justicia_si", 0);
        justicia_no = intent.getIntExtra("justicia_no", 0);
        agresor_si = intent.getIntExtra("agresor_si", 0);
        agresor_no = intent.getIntExtra("agresor_no", 0);
        salud_si = intent.getIntExtra("salud_si", 0);
        salud_no = intent.getIntExtra("salud_no", 0);
        adn_si = intent.getIntExtra("adn_si", 0);
        adn_no = intent.getIntExtra("adn_no", 0);
        comunidad_no = intent.getIntExtra("comunidad_no", 0);
        comunidad_si = intent.getIntExtra("comunidad_si", 0);
        firmes_aplica = intent.getIntExtra("firmes_aplica", 0);
        firmes_no_aplica = intent.getIntExtra("firmes_no_aplica", 0);
        intervencion_aplica = intent.getIntExtra("intervencion_aplica", 0);
        intervencion_no_aplica = intent.getIntExtra("intervencion_no_aplica", 0);

        // Asignar click listener a los ConstraintLayouts
        ConstraintLayout opcion1 = findViewById(R.id.Opcion1);
        ConstraintLayout opcion2 = findViewById(R.id.Opcion2);
        ConstraintLayout opcion3 = findViewById(R.id.Opcion3);
        ConstraintLayout opcion5 = findViewById(R.id.Opcion5);
        ConstraintLayout opcion6 = findViewById(R.id.Opcion6);
        ConstraintLayout opcion7 = findViewById(R.id.Opcion7);
        ConstraintLayout opcion10 = findViewById(R.id.Opcion10);


        opcion1.setOnClickListener(v -> {
            Intent intentOpcion1 = new Intent(TratamientoDiferenciadoSoaActivity.this, ResultadosProgramasSoa.class);
            intentOpcion1.putExtra("participa_programa_uno", participa_programa_uno);
            intentOpcion1.putExtra("participa_programa_dos", participa_programa_dos);
            intentOpcion1.putExtra("participa_programa_tres", participa_programa_tres);
            intentOpcion1.putExtra("participa_programa_cuatro", participa_programa_cuatro);
            intentOpcion1.putExtra("participa_programa_cinco", participa_programa_cinco);
            intentOpcion1.putExtra("participa_programa_no", participa_programa_no);
            startActivity(intentOpcion1);
        });

        opcion2.setOnClickListener(v -> {
            Intent intentOpcion2 = new Intent(TratamientoDiferenciadoSoaActivity.this, ResultadoJusticiaTerapeuticaSoa.class);
            intentOpcion2.putExtra("justicia_si", justicia_si);
            intentOpcion2.putExtra("justicia_no", justicia_no);
            startActivity(intentOpcion2);
        });

        opcion3.setOnClickListener(v -> {
            Intent intentOpcion3 = new Intent(TratamientoDiferenciadoSoaActivity.this, ResultadoAgresoresSexualesSoa.class);
            intentOpcion3.putExtra("agresor_si", agresor_si);
            intentOpcion3.putExtra("agresor_no", agresor_no);
            startActivity(intentOpcion3);
        });

        opcion5.setOnClickListener(v -> {
            Intent intentOpcion5 = new Intent(TratamientoDiferenciadoSoaActivity.this, ResultadoAdnSoa.class);
            intentOpcion5.putExtra("adn_si", adn_si);
            intentOpcion5.putExtra("adn_no", adn_no);
            startActivity(intentOpcion5);
        });

        opcion6.setOnClickListener(v -> {
            Intent intentOpcion6 = new Intent(TratamientoDiferenciadoSoaActivity.this, ResultadoIntervencionTerapeuticaSoa.class);
            intentOpcion6.putExtra("comunidad_no", comunidad_no);
            intentOpcion6.putExtra("comunidad_si", comunidad_si);
            startActivity(intentOpcion6);
        });

        opcion7.setOnClickListener(v -> {
            Intent intentOpcion7 = new Intent(TratamientoDiferenciadoSoaActivity.this, ResultadosFirmesAdelanteSoa.class);
            intentOpcion7.putExtra("firmes_aplica", firmes_aplica);
            intentOpcion7.putExtra("firmes_no_aplica", firmes_no_aplica);
            startActivity(intentOpcion7);
        });

        opcion10.setOnClickListener(v -> {
            Intent intentOpcion10 = new Intent(TratamientoDiferenciadoSoaActivity.this, ResultadoIntervencionTera.class);
            intentOpcion10.putExtra("intervencion_aplica", intervencion_aplica);
            intentOpcion10.putExtra("intervencion_no_aplica", intervencion_no_aplica);
            startActivity(intentOpcion10);
            Log.d(String.valueOf(intervencion_aplica),"DATO APLICA");
            Log.d(String.valueOf(intervencion_no_aplica),"DATO NO APLICA");
        });
    }

    private void setupCheckBoxListeners() {
        cbIncluirEstadoIng.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                cbIncluirEstadoAten.setChecked(false);
            }
        });

        cbIncluirEstadoAten.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                cbIncluirEstadoIng.setChecked(false);
            }
        });
    }

    private boolean validarFechaFormato(String fecha) {
        String pattern = "^\\d{4}-\\d{2}-\\d{2}$";
        return fecha.matches(pattern);
    }

    private void llamarEndPoint(String fechaInicio, boolean incluirEstadoIng) {
        Call<List<Map<String, Object>>> call = soaService.obtenerTD(fechaInicio, fechaInicio, incluirEstadoIng);
        call.enqueue(new Callback<List<Map<String, Object>>>() {
            @Override
            public void onResponse(Call<List<Map<String, Object>>> call, Response<List<Map<String, Object>>> response) {
                if (response.isSuccessful()) {
                    List<Map<String, Object>> data = response.body();
                    if (data != null && !data.isEmpty()) {
                        Map<String, Object> firstElement = data.get(0);
                        participa_programa_uno = getIntValue(firstElement, "participa_programa_uno");
                        participa_programa_dos = getIntValue(firstElement, "participa_programa_dos");
                        participa_programa_tres = getIntValue(firstElement, "participa_programa_tres");
                        participa_programa_cuatro = getIntValue(firstElement, "participa_programa_cuatro");
                        participa_programa_cinco = getIntValue(firstElement, "participa_programa_cinco");
                        participa_programa_no = getIntValue(firstElement, "participa_programa_no");
                        justicia_si = getIntValue(firstElement, "justicia_si");
                        justicia_no = getIntValue(firstElement, "justicia_no");
                        agresor_si = getIntValue(firstElement, "agresor_si");
                        agresor_no = getIntValue(firstElement, "agresor_no");
                        salud_si = getIntValue(firstElement, "salud_si");
                        salud_no = getIntValue(firstElement, "salud_no");
                        adn_si = getIntValue(firstElement, "adn_si");
                        adn_no = getIntValue(firstElement, "adn_no");
                        comunidad_no = getIntValue(firstElement, "comunidad_no");
                        comunidad_si = getIntValue(firstElement, "comunidad_si");
                        firmes_aplica = getIntValue(firstElement, "firmes_aplica");
                        firmes_no_aplica = getIntValue(firstElement, "firmes_no_aplica");                        firmes_no_aplica = getIntValue(firstElement, "firmes_no_aplica");
                        intervencion_aplica = getIntValue(firstElement, "intervencion_aplica");
                        intervencion_no_aplica = getIntValue(firstElement, "intervencion_no_aplica");


                        // Aquí puedes actualizar la UI con los datos obtenidos
                    }
                } else {
                    // Maneja el caso de error
                }
            }

            @Override
            public void onFailure(Call<List<Map<String, Object>>> call, Throwable t) {
                // Maneja el caso de fallo de la llamada
            }
        });
    }

    private int getIntValue(Map<String, Object> map, String key) {
        Object value = map.get(key);
        if (value instanceof Double) {
            return ((Double) value).intValue();
        } else if (value instanceof Integer) {
            return (Integer) value;
        } else {
            return 0; // O cualquier valor por defecto que consideres adecuado
        }
    }

    private void initDatePicker() {
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
        switch (month) {
            case 1:
                return "ENE";
            case 2:
                return "FEB";
            case 3:
                return "MAR";
            case 4:
                return "ABR";
            case 5:
                return "MAY";
            case 6:
                return "JUN";
            case 7:
                return "JUL";
            case 8:
                return "AGO";
            case 9:
                return "SEP";
            case 10:
                return "OCT";
            case 11:
                return "NOV";
            case 12:
                return "DIC";
            default:
                return "ENE";
        }
    }

    private String formatDateToYMD(int year, int month, int dayOfMonth) {
        return String.format("%04d-%02d-%02d", year, month, dayOfMonth);
    }

    public void openDatePickerInicio(View view) {
        datePickerDialog.show();
    }

    public String showSelectedDate() {
        String[] dateParts = selectedDate.split(" ");
        int day = Integer.parseInt(dateParts[1]);
        int month = getMonthNumber(dateParts[0]);
        int year = Integer.parseInt(dateParts[2]);
        return formatDateToYMD(year, month, day);
    }

    private int getMonthNumber(String month) {
        switch (month) {
            case "ENE":
                return 1;
            case "FEB":
                return 2;
            case "MAR":
                return 3;
            case "ABR":
                return 4;
            case "MAY":
                return 5;
            case "JUN":
                return 6;
            case "JUL":
                return 7;
            case "AGO":
                return 8;
            case "SEP":
                return 9;
            case "OCT":
                return 10;
            case "NOV":
                return 11;
            case "DIC":
                return 12;
            default:
                return 1;
        }
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(dayOfMonth, month, year);
    }
}

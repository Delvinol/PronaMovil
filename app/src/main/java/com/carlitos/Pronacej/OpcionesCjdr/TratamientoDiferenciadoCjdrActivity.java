package com.carlitos.Pronacej.OpcionesCjdr;

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
import com.carlitos.Pronacej.ResultadosCjrd.ResultadoAdnCjdr;
import com.carlitos.Pronacej.ResultadosCjrd.ResultadoAgresoresSexualesCjdr;
import com.carlitos.Pronacej.ResultadosCjrd.ResultadoIntervencionTerapeuticaCjdr;
import com.carlitos.Pronacej.ResultadosCjrd.ResultadoJusticiaTerapeuticaCjdr;
import com.carlitos.Pronacej.ResultadosCjrd.ResultadosFirmesAdelanteCjdr;
import com.carlitos.Pronacej.ResultadosCjrd.ResultadosProgramasCjdr;
import com.carlitos.Pronacej.ResultadosCjrd.ResultadosProgramasGradualesCjdr;
import com.carlitos.Pronacej.ResultadosCjrd.ResultadosSaludMentalCjdr;
import com.carlitos.Pronacej.Time.MonthYearPickerDialog;
import com.carlitos.Pronacej.Utils.Apis;
import com.carlitos.Pronacej.Utils.CjdrService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TratamientoDiferenciadoCjdrActivity extends AppCompatActivity {

    private static final String TAG = "TD_Cjdr";
    private int participa_programa_uno;
    private int participa_programa_dos;
    private int participa_programa_tres;
    private int participa_programa_cuatro;
    private int participa_programa_cinco;
    private int participa_programa_no;
    private int programa_uno;
    private int programa_dos;
    private int programa_tres;
    private int programa_cuatro;
    private int programa_no_aplica;
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
    private Button btnMonthYearPicker;
    private CjdrService cjdrService;
    private CheckBox cbIncluirEstadoIng;
    private CheckBox cbIncluirEstadoAten;
    private int selectedYear, selectedMonth;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tratamiento_diferenciado_cjdr);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(v -> {
            Intent intentHome = new Intent(TratamientoDiferenciadoCjdrActivity.this, CategoriaMenu.class);
            startActivity(intentHome);
        });

        ButtonBack.setOnClickListener(v -> onBackPressed());

        btnMonthYearPicker = findViewById(R.id.btnMonthYearPicker);
        calendar = Calendar.getInstance();
        selectedYear = calendar.get(Calendar.YEAR);
        selectedMonth = calendar.get(Calendar.MONTH);
        updateDateButtonText();

        cbIncluirEstadoIng = findViewById(R.id.cbIncluirEstadoIng);
        cbIncluirEstadoAten = findViewById(R.id.cbIncluirEstadoAten);

        tvErrorFecha = findViewById(R.id.tvErrorFecha);
        btnGenerarGrafico = findViewById(R.id.btnEnviar);
        cjdrService = Apis.getCjdrService();

        btnGenerarGrafico.setOnClickListener(view -> {
            llamarEndPoint();
            Toast.makeText(TratamientoDiferenciadoCjdrActivity.this, "Fecha Actualizada", Toast.LENGTH_SHORT).show();
        });

        setupCheckBoxListeners();

        Intent intent = getIntent();
        participa_programa_uno = intent.getIntExtra("participa_programa_uno", 0);
        participa_programa_dos = intent.getIntExtra("participa_programa_dos", 0);
        participa_programa_tres = intent.getIntExtra("participa_programa_tres", 0);
        participa_programa_cuatro = intent.getIntExtra("participa_programa_cuatro", 0);
        participa_programa_cinco = intent.getIntExtra("participa_programa_cinco", 0);
        participa_programa_no = intent.getIntExtra("participa_programa_no", 0);
        programa_uno = intent.getIntExtra("programa_uno", 0);
        programa_dos = intent.getIntExtra("programa_dos", 0);
        programa_tres = intent.getIntExtra("programa_tres", 0);
        programa_cuatro = intent.getIntExtra("programa_cuatro", 0);
        programa_no_aplica = intent.getIntExtra("programa_no_aplica", 0);
        justicia_si = intent.getIntExtra("justicia_si", 0);
        justicia_no = intent.getIntExtra("justicia_no", 0);
        agresor_si = intent.getIntExtra("agresor_si", 0);
        agresor_no = intent.getIntExtra("agresor_no", 0);
        salud_si = intent.getIntExtra("salud_si", 0);
        salud_no = intent.getIntExtra("salud_no", 0);
        adn_si = intent.getIntExtra("adn_si", 0);
        adn_no = intent.getIntExtra("adn_no", 0);
        comunidad_si = intent.getIntExtra("comunidad_si", 0);
        comunidad_no = intent.getIntExtra("comunidad_no", 0);
        firmes_aplica = intent.getIntExtra("firmes_aplica", 0);
        firmes_no_aplica = intent.getIntExtra("firmes_no_aplica", 0);

        ConstraintLayout opcion1 = findViewById(R.id.Opcion1);
        ConstraintLayout opcion2 = findViewById(R.id.Opcion2);
        ConstraintLayout opcion3 = findViewById(R.id.Opcion3);
        ConstraintLayout opcion4 = findViewById(R.id.Opcion4);
        ConstraintLayout opcion5 = findViewById(R.id.Opcion5);
        ConstraintLayout opcion6 = findViewById(R.id.Opcion6);
        ConstraintLayout opcion7 = findViewById(R.id.Opcion7);
        ConstraintLayout opcion8 = findViewById(R.id.Opcion8);

        opcion1.setOnClickListener(v -> {
            Intent intentOpcion1 = new Intent(TratamientoDiferenciadoCjdrActivity.this, ResultadosProgramasCjdr.class);
            intentOpcion1.putExtra("participa_programa_uno", participa_programa_uno);
            intentOpcion1.putExtra("participa_programa_dos", participa_programa_dos);
            intentOpcion1.putExtra("participa_programa_tres", participa_programa_tres);
            intentOpcion1.putExtra("participa_programa_cuatro", participa_programa_cuatro);
            intentOpcion1.putExtra("participa_programa_cinco", participa_programa_cinco);
            intentOpcion1.putExtra("participa_programa_no", participa_programa_no);
            startActivity(intentOpcion1);
        });

        opcion2.setOnClickListener(v -> {
            Intent intentOpcion2 = new Intent(TratamientoDiferenciadoCjdrActivity.this, ResultadoJusticiaTerapeuticaCjdr.class);
            intentOpcion2.putExtra("justicia_si", justicia_si);
            intentOpcion2.putExtra("justicia_no", justicia_no);
            startActivity(intentOpcion2);

        });

        opcion3.setOnClickListener(v -> {
            Intent intentOpcion3 = new Intent(TratamientoDiferenciadoCjdrActivity.this, ResultadoAgresoresSexualesCjdr.class);
            intentOpcion3.putExtra("agresor_si", agresor_si);
            intentOpcion3.putExtra("agresor_no", agresor_no);
            startActivity(intentOpcion3);
        });

        opcion4.setOnClickListener(v -> {
            Intent intentOpcion4 = new Intent(TratamientoDiferenciadoCjdrActivity.this, ResultadosSaludMentalCjdr.class);
            intentOpcion4.putExtra("salud_si", salud_si);
            intentOpcion4.putExtra("salud_no", salud_no);
            startActivity(intentOpcion4);
        });

        opcion5.setOnClickListener(v -> {
            Intent intentOpcion5 = new Intent(TratamientoDiferenciadoCjdrActivity.this, ResultadoAdnCjdr.class);
            intentOpcion5.putExtra("adn_si", adn_si);
            intentOpcion5.putExtra("adn_no", adn_no);
            startActivity(intentOpcion5);
        });

        opcion6.setOnClickListener(v -> {
            Intent intentOpcion6 = new Intent(TratamientoDiferenciadoCjdrActivity.this, ResultadoIntervencionTerapeuticaCjdr.class);
            intentOpcion6.putExtra("comunidad_si", comunidad_si);
            intentOpcion6.putExtra("comunidad_no", comunidad_no);
            startActivity(intentOpcion6);
        });

        opcion7.setOnClickListener(v -> {
            Intent intentOpcion7 = new Intent(TratamientoDiferenciadoCjdrActivity.this, ResultadosFirmesAdelanteCjdr.class);
            intentOpcion7.putExtra("firmes_aplica", firmes_aplica);
            intentOpcion7.putExtra("firmes_no_aplica", firmes_no_aplica);
            startActivity(intentOpcion7);
        });

        opcion8.setOnClickListener(v -> {
            Intent intentOpcion8 = new Intent(TratamientoDiferenciadoCjdrActivity.this, ResultadosProgramasGradualesCjdr.class);
            intentOpcion8.putExtra("programa_uno", programa_uno);
            intentOpcion8.putExtra("programa_dos", programa_dos);
            intentOpcion8.putExtra("programa_tres", programa_tres);
            intentOpcion8.putExtra("programa_cuatro", programa_cuatro);
            intentOpcion8.putExtra("programa_no_aplica", programa_no_aplica);
            startActivity(intentOpcion8);
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

    private void llamarEndPoint() {
        calendar.set(selectedYear, selectedMonth, 1);
        String fechaInicio = new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(calendar.getTime());

        calendar.set(selectedYear, selectedMonth, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        String fechaFin = new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(calendar.getTime());

        boolean incluirEstadoIng = cbIncluirEstadoIng.isChecked();

        Call<List<Map<String, Object>>> call = cjdrService.obtenerTD(fechaInicio, fechaFin, incluirEstadoIng);
        call.enqueue(new Callback<List<Map<String, Object>>>() {
            @Override
            public void onResponse(Call<List<Map<String, Object>>> call, Response<List<Map<String, Object>>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    List<Map<String, Object>> data = response.body();
                    if (data != null && !data.isEmpty()) {
                        Map<String, Object> firstElement = data.get(0);
                        participa_programa_uno = getIntValue(firstElement, "participa_programa_uno");
                        participa_programa_dos = getIntValue(firstElement, "participa_programa_dos");
                        participa_programa_tres = getIntValue(firstElement, "participa_programa_tres");
                        participa_programa_cuatro = getIntValue(firstElement, "participa_programa_cuatro");
                        participa_programa_cinco = getIntValue(firstElement, "participa_programa_cinco");
                        participa_programa_no = getIntValue(firstElement, "participa_programa_no");
                        programa_uno = getIntValue(firstElement, "programa_uno");
                        programa_dos = getIntValue(firstElement, "programa_dos");
                        programa_tres = getIntValue(firstElement, "programa_tres");
                        programa_cuatro = getIntValue(firstElement, "programa_cuatro");
                        programa_no_aplica = getIntValue(firstElement, "programa_no_aplica");
                        justicia_si = getIntValue(firstElement, "justicia_si");
                        justicia_no = getIntValue(firstElement, "justicia_no");
                        agresor_si = getIntValue(firstElement, "agresor_si");
                        agresor_no = getIntValue(firstElement, "agresor_no");
                        salud_si = getIntValue(firstElement, "salud_si");
                        salud_no = getIntValue(firstElement, "salud_no");
                        adn_si = getIntValue(firstElement, "adn_si");
                        adn_no = getIntValue(firstElement, "adn_no");
                        comunidad_si = getIntValue(firstElement, "comunidad_si");
                        comunidad_no = getIntValue(firstElement, "comunidad_no");
                        firmes_aplica = getIntValue(firstElement, "firmes_aplica");
                        firmes_no_aplica = getIntValue(firstElement, "firmes_no_aplica");
                    }
                } else {
                    Log.e(TAG, "Error en la respuesta: " + response.message());
                    Toast.makeText(TratamientoDiferenciadoCjdrActivity.this, "Error al obtener datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Map<String, Object>>> call, Throwable t) {
                Log.e(TAG, "Fallo en la llamada: " + t.getMessage());
                Toast.makeText(TratamientoDiferenciadoCjdrActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
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

    public void openMonthYearPicker(View view) {
        MonthYearPickerDialog pd = new MonthYearPickerDialog();
        pd.setListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                selectedYear = year;
                selectedMonth = monthOfYear;
                updateDateButtonText();
            }
        });
        pd.show(getSupportFragmentManager(), "MonthYearPickerDialog");
    }

    private void updateDateButtonText() {
        calendar.set(Calendar.YEAR, selectedYear);
        calendar.set(Calendar.MONTH, selectedMonth);
        String monthYearStr = new SimpleDateFormat("MMM yyyy", new Locale("es", "ES"))
                .format(calendar.getTime());
        btnMonthYearPicker.setText(monthYearStr.toUpperCase());
    }

}

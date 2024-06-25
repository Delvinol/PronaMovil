package com.carlitos.Pronacej.ActivitysPadres;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.carlitos.Pronacej.Model.RegisterRequest;
import com.carlitos.Pronacej.Model.RegisterResponse;
import com.carlitos.Pronacej.R;
import com.carlitos.Pronacej.Utils.Client;
import com.carlitos.Pronacej.Utils.LoginService;

import java.time.LocalDate;
import java.time.LocalDateTime;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText etName, etLastName, etEmail, etEntity, etDni;
    private Button btnRegister;
    private LoginService loginService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etName);
        etLastName = findViewById(R.id.etLastName);
        etEmail = findViewById(R.id.etEmail);
        etEntity = findViewById(R.id.etEntity);
        etDni = findViewById(R.id.etDni);
        btnRegister = findViewById(R.id.btnRegister);

        // Crear instancia de ApiService
        loginService = Client.getClient("http://181.176.172.117:8081/").create(LoginService.class);
        //loginService = Client.getClient("http://192.168.0.101:8080/").create(LoginService.class);

        btnRegister.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        String name = etName.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String entity = etEntity.getText().toString().trim();
        String dni = etDni.getText().toString().trim();

        if (name.isEmpty() || lastName.isEmpty() || email.isEmpty() || entity.isEmpty()) {
            // Si algún campo está vacío, muestra un mensaje de error
            Toast.makeText(RegisterActivity.this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            // Establecer el valor de typeUserId a 2 directamente
            int typeUserId = 2;
            int state = (short)0;
            LocalDate expirationDate = null;
            RegisterRequest registerRequest = new RegisterRequest(typeUserId, name, lastName, email, entity,expirationDate, (short) state, dni);

            Call<RegisterResponse> registerCall = loginService.register(registerRequest);
            registerCall.enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                    if (response.isSuccessful()) {
                        RegisterResponse registerResponse = response.body();
                        // Maneja la respuesta de registro exitosa
                        Toast.makeText(RegisterActivity.this, "Solicitud Enviada", Toast.LENGTH_SHORT).show();

                        // Redirige al usuario al menú de inicio de sesión
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish(); // Esto evita que el usuario pueda regresar a la actividad de registro presionando el botón de retroceso
                    } else {
                        // Maneja el error
                        Toast.makeText(RegisterActivity.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {
                    // Maneja el error de la solicitud
                    Toast.makeText(RegisterActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

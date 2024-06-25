package com.carlitos.Pronacej.ActivitysPadres;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.carlitos.Pronacej.Model.AuthRequest;
import com.carlitos.Pronacej.Model.AuthResponse;
import com.carlitos.Pronacej.R;
import com.carlitos.Pronacej.Utils.Client;
import com.carlitos.Pronacej.Utils.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin;

    private TextView btnRegistrar;
    private LoginService loginService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegistrar = findViewById(R.id.bntRegistrar);


        // Crear instancia de ApiService
        loginService = Client.getClient("http://181.176.172.117:8081").create(LoginService.class);

        //loginService = Client.getClient("http://192.168.0.101:8080").create(LoginService.class);
        btnLogin.setOnClickListener(v -> loginUser());
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad de registro
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loginUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        AuthRequest loginRequest = new AuthRequest(email, password);

        Call<AuthResponse> loginCall = loginService.authenticate(loginRequest);
        loginCall.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    AuthResponse loginResponse = response.body();
                    // Manejar la respuesta de inicio de sesión exitoso
                    Toast.makeText(LoginActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                    // Redirigir a la siguiente actividad (por ejemplo, al menú principal)
                    Intent intent = new Intent(LoginActivity.this, CategoriaMenu.class);
                    startActivity(intent);
                    finish(); // Terminar esta actividad para evitar que el usuario pueda regresar presionando el botón de retroceso
                } else {
                    // Manejar el error
                    Toast.makeText(LoginActivity.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                // Manejar el error de la solicitud
                Toast.makeText(LoginActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

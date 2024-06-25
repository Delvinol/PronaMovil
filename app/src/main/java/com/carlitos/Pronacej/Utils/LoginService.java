package com.carlitos.Pronacej.Utils;

import com.carlitos.Pronacej.Model.AuthRequest;
import com.carlitos.Pronacej.Model.AuthResponse;
import com.carlitos.Pronacej.Model.RegisterRequest;
import com.carlitos.Pronacej.Model.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("/api/v1/auth/authenticate")
    Call<AuthResponse> authenticate(@Body AuthRequest authRequest);

    @POST("/api/v1/auth/register")
    Call<RegisterResponse> register(@Body RegisterRequest registerRequest);
}

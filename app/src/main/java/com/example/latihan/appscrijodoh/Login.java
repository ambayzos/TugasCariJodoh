package com.example.latihan.appscrijodoh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.latihan.appscrijodoh.entity.User;
import com.example.latihan.appscrijodoh.service.APIClient;
import com.example.latihan.appscrijodoh.service.AutInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnProcessLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.editusername);
        edtPassword = findViewById(R.id.editpassword);
        btnProcessLogin = findViewById(R.id.buttonLogin);

        btnProcessLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setUsername(edtUsername.getText().toString());
                user.setPassword(edtPassword.getText().toString());

                AutInterface autInterface = APIClient.getRetrofit().create(AutInterface.class);
                Call<String> call = autInterface.loginuser(user);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Intent iMenuHome = new Intent(Login.this, MenuHome.class);
                        iMenuHome.putExtra("token", response.body());
                        Toast.makeText(Login.this, response.body(), Toast.LENGTH_LONG).show();
                        System.out.println(response.body());
                        startActivity(iMenuHome);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(Login.this, "Login tidak berhasil", Toast.LENGTH_LONG).show();
                        System.out.println(t);
                    }
                });
            }
        });
    }
}
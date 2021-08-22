package com.example.latihan.appscrijodoh;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnLogin){
            Intent iHomeLogin = new Intent(MainActivity.this, Login.class);
            startActivity(iHomeLogin);
        }else if(view.getId() == R.id.btnRegister){
            Intent iRegister = new Intent(MainActivity.this, Register.class);
            startActivity(iRegister);
        }
    }
}
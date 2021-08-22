package com.example.latihan.appscrijodoh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.latihan.appscrijodoh.entity.User;
import com.example.latihan.appscrijodoh.utility.JWTUtil;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

public class MenuHome extends AppCompatActivity {

    String token;
    Button btnDataCalon, btnPilih, btnLogout;
    User rGender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_home);

        btnPilih =findViewById(R.id.btn_pilihCalon);
        btnDataCalon = findViewById(R.id.btn_dataCalon);

        token = "Bearer "+getIntent().getStringExtra("token");

        try {
            Gson gson = new Gson();
            rGender = gson.fromJson(JWTUtil.getBodyDecode(getIntent().getStringExtra("token")), User.class);
            System.out.println(rGender);
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }


            btnPilih.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent iPilihCalon = new Intent(MenuHome.this, PilihCalonActivity.class);
                    iPilihCalon.putExtra("token", token);
                    Toast.makeText(MenuHome.this, token, Toast.LENGTH_LONG).show();
                    startActivity(iPilihCalon);
                }
            });

        btnDataCalon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuHome.this, ListDataCalon.class);
                intent.putExtra("token", token);
                Toast.makeText(MenuHome.this, token, Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });

    }
}
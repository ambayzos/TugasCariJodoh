package com.example.latihan.appscrijodoh;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;


import com.example.latihan.appscrijodoh.entity.User;

import com.example.latihan.appscrijodoh.utility.JWTUtil;
import com.google.gson.Gson;


import java.io.UnsupportedEncodingException;


public class PilihCalonActivity extends AppCompatActivity  {

    String token;

    User rGender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_calon);

        token = "Bearer "+getIntent().getStringExtra("token");

        try {
            Gson gson = new Gson();
            rGender = gson.fromJson(JWTUtil.getBodyDecode(getIntent().getStringExtra("token")), User.class);
            System.out.println(rGender);
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
    }


}
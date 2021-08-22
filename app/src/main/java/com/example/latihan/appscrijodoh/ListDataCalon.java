package com.example.latihan.appscrijodoh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.latihan.appscrijodoh.adapter.AdapterDataCalon;
import com.example.latihan.appscrijodoh.entity.User;
import com.example.latihan.appscrijodoh.service.APIClient;
import com.example.latihan.appscrijodoh.service.AutInterface;
import com.example.latihan.appscrijodoh.utility.JWTUtil;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListDataCalon extends AppCompatActivity {
    RecyclerView tv_calon;
    String token;
    AutInterface autInterface;
    User rGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data_calon);

        tv_calon = findViewById(R.id.rtv_calon);
        token = "Bearer "+getIntent().getStringExtra("token");
        try {
            Gson gson = new Gson();
            rGender = gson.fromJson(JWTUtil.getBodyDecode(getIntent().getStringExtra("token")), User.class);
            System.out.println(rGender);
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }

        if (rGender.getGender().equalsIgnoreCase("PEREMPUAN")){
            System.out.println(token);
        }else if (rGender.getGender().equalsIgnoreCase("LAKI-LAKI")){
            System.out.println(token);
        }


        autInterface = APIClient.getRetrofit().create(AutInterface.class);
        listCalon();


    }

    private void listCalon() {
        Call<ArrayList<User>> call = autInterface.getAllUser(token);
        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                AdapterDataCalon adapterDataCalon = new AdapterDataCalon(response.body(), ListDataCalon.this);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListDataCalon.this, LinearLayoutManager.VERTICAL, false);
                tv_calon.setLayoutManager(layoutManager);
                tv_calon.setAdapter(adapterDataCalon);
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {

                Toast.makeText(ListDataCalon.this,"Tidak Dapat Mengakses", Toast.LENGTH_LONG).show();
                System.out.println(t);
                finish();

            }
        });
    }
}
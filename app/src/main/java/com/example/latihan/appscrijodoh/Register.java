package com.example.latihan.appscrijodoh;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.latihan.appscrijodoh.entity.User;
import com.example.latihan.appscrijodoh.service.APIClient;
import com.example.latihan.appscrijodoh.service.AutInterface;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;

import java.io.File;

import in.mayanknagwanshi.imagepicker.ImageSelectActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    TextView txtGeo;
    EditText edtUsername, edtNamaLengkap, edtNohp, edtUmur, edtPassword;
    Spinner spnGander;
    Button btnKamera, btnDaftar;
    ImageView imageView;
    private int requestCode = 1;
    String mediaPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtGeo = findViewById(R.id.txtGeo);
        edtUsername = findViewById(R.id.editRegisusername);
        edtNamaLengkap = findViewById(R.id.editNamaLengkap);
        edtNohp = findViewById(R.id.editNohp);
        edtUmur = findViewById(R.id.editUmur);
        edtPassword = findViewById(R.id.editRegispassword);
        spnGander = findViewById(R.id.spnGender);
        imageView = findViewById(R.id.imgView);

        btnKamera = findViewById(R.id.btnKameraProfile);
        btnDaftar = findViewById(R.id.btnDaftar);

        btnKamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGambar = new Intent(getApplicationContext(), ImageSelectActivity.class);
                iGambar.putExtra(ImageSelectActivity.FLAG_COMPRESS, true);
                iGambar.putExtra(ImageSelectActivity.FLAG_CAMERA, true);
                iGambar.putExtra(ImageSelectActivity.FLAG_GALLERY, true);
                startActivityForResult(iGambar, 1);
            }
        });

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FusedLocationProviderClient mFusedLocationProviderClient = LocationServices
                        .getFusedLocationProviderClient(Register.this);

                mFusedLocationProviderClient.getLastLocation()
                        .addOnSuccessListener(Register.this, new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                if (location != null){
                                    User user = new User();
                                    user.setDataLokasi(location.getLatitude()+", "+location.getLongitude());
                                    user.setUsername(edtUsername.getText().toString());
                                    user.setNamaLengkap(edtNamaLengkap.getText().toString());
                                    user.setGender(spnGander.getSelectedItem().toString());
                                    user.setNoHp(edtNohp.getText().toString());
                                    user.setAge(edtUmur.getText().toString());
                                    user.setPassword(edtPassword.getText().toString());

                                    Gson gson = new Gson();
                                    String json = gson.toJson(user);

                                    File file = new File(mediaPath);
                                    RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"),file);
                                    MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file",file.getName(),requestBody);
                                    RequestBody data = RequestBody.create(MediaType.parse("text/plain"),json);

                                    AutInterface autInterface = APIClient.getRetrofit().create(AutInterface.class);
                                    Call<String> call = autInterface.registrasi(fileToUpload, data);
                                    call.enqueue(new Callback<String>() {
                                        @Override
                                        public void onResponse(Call<String> call, Response<String> response) {
                                            Toast.makeText(Register.this, "Bererhasil Register", Toast.LENGTH_LONG).show();
                                            Intent i = new Intent(Register.this, Login.class);
                                            startActivity(i);
                                            finish();

                                        }

                                        @Override
                                        public void onFailure(Call<String> call, Throwable t) {
                                            Toast.makeText(Register.this, "Tidak berhasil Register", Toast.LENGTH_LONG).show();
                                            System.out.println(t);

                                        }
                                    });



                                }
                            }
                        });
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (this.requestCode == requestCode && resultCode == RESULT_OK){
            mediaPath = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            imageView.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
        }
    }
}
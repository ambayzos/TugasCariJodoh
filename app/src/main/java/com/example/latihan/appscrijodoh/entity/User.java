package com.example.latihan.appscrijodoh.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {


    private Long id;

    private String username;

    private String password;

    private String namaLengkap;

    private String gender;

    private String noHp;

    private String age;
    private String dataLokasi;

    private String dataFoto;

    public User(Long id, String username, String password, String namaLengkap, String gender, String noHp, String age, String dataLokasi, String dataFoto) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.namaLengkap = namaLengkap;
        this.gender = gender;
        this.noHp = noHp;
        this.age = age;
        this.dataLokasi = dataLokasi;
        this.dataFoto = dataFoto;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDataLokasi() {
        return dataLokasi;
    }

    public void setDataLokasi(String dataLokasi) {
        this.dataLokasi = dataLokasi;
    }

    public String getDataFoto() {
        return dataFoto;
    }

    public void setDataFoto(String dataFoto) {
        this.dataFoto = dataFoto;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.username);
        dest.writeString(this.password);
        dest.writeString(this.namaLengkap);
        dest.writeString(this.gender);
        dest.writeString(this.noHp);
        dest.writeString(this.age);
        dest.writeString(this.dataLokasi);
        dest.writeString(this.dataFoto);
    }

    public void readFromParcel(Parcel source) {
        this.id = (Long) source.readValue(Long.class.getClassLoader());
        this.username = source.readString();
        this.password = source.readString();
        this.namaLengkap = source.readString();
        this.gender = source.readString();
        this.noHp = source.readString();
        this.age = source.readString();
        this.dataLokasi = source.readString();
        this.dataFoto = source.readString();
    }

    protected User(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.username = in.readString();
        this.password = in.readString();
        this.namaLengkap = in.readString();
        this.gender = in.readString();
        this.noHp = in.readString();
        this.age = in.readString();
        this.dataLokasi = in.readString();
        this.dataFoto = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}

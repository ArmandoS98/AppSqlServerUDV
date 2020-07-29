package com.aescttgt.appsqlserverudv.Pojos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class JugadorDash implements Parcelable {
    private int id_jugador;
    private String nombre_jugador;
    private Date fec_nacimiento;
    private String status_jugador;
    private String nombre_pais;

    public JugadorDash() {
    }

    public JugadorDash(int id_jugador, String nombre_jugador, Date fec_nacimiento, String status_jugador, String nombre_pais) {
        this.id_jugador = id_jugador;
        this.nombre_jugador = nombre_jugador;
        this.fec_nacimiento = fec_nacimiento;
        this.status_jugador = status_jugador;
        this.nombre_pais = nombre_pais;
    }

    protected JugadorDash(Parcel in) {
        id_jugador = in.readInt();
        nombre_jugador = in.readString();
        status_jugador = in.readString();
        nombre_pais = in.readString();
    }

    public static final Creator<JugadorDash> CREATOR = new Creator<JugadorDash>() {
        @Override
        public JugadorDash createFromParcel(Parcel in) {
            return new JugadorDash(in);
        }

        @Override
        public JugadorDash[] newArray(int size) {
            return new JugadorDash[size];
        }
    };

    public int getId_jugador() {
        return id_jugador;
    }

    public void setId_jugador(int id_jugador) {
        this.id_jugador = id_jugador;
    }

    public String getNombre_jugador() {
        return nombre_jugador;
    }

    public void setNombre_jugador(String nombre_jugador) {
        this.nombre_jugador = nombre_jugador;
    }

    public Date getFec_nacimiento() {
        return fec_nacimiento;
    }

    public void setFec_nacimiento(Date fec_nacimiento) {
        this.fec_nacimiento = fec_nacimiento;
    }

    public String getStatus_jugador() {
        return status_jugador;
    }

    public void setStatus_jugador(String status_jugador) {
        this.status_jugador = status_jugador;
    }

    public String getNombre_pais() {
        return nombre_pais;
    }

    public void setNombre_pais(String nombre_pais) {
        this.nombre_pais = nombre_pais;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_jugador);
        dest.writeString(nombre_jugador);
        dest.writeString(status_jugador);
        dest.writeString(nombre_pais);
    }
}

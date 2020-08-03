package com.aescttgt.appsqlserverudv.Pojos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Equipo implements Parcelable {
    private int id_equipo;
    private String nombre_equipo;
    private int id_entrenador;
    private String nom_entrenador;
    private Date fec_nacimiento_e;

    public Equipo() {
    }

    public Equipo(int id_equipo, String nombre_equipo, int id_entrenador, String nom_entrenador, Date fec_nacimiento_e) {
        this.id_equipo = id_equipo;
        this.nombre_equipo = nombre_equipo;
        this.id_entrenador = id_entrenador;
        this.nom_entrenador = nom_entrenador;
        this.fec_nacimiento_e = fec_nacimiento_e;
    }

    protected Equipo(Parcel in) {
        id_equipo = in.readInt();
        nombre_equipo = in.readString();
        id_entrenador = in.readInt();
        nom_entrenador = in.readString();
    }

    public static final Creator<Equipo> CREATOR = new Creator<Equipo>() {
        @Override
        public Equipo createFromParcel(Parcel in) {
            return new Equipo(in);
        }

        @Override
        public Equipo[] newArray(int size) {
            return new Equipo[size];
        }
    };

    public int getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(int id_equipo) {
        this.id_equipo = id_equipo;
    }

    public String getNombre_equipo() {
        return nombre_equipo;
    }

    public void setNombre_equipo(String nombre_equipo) {
        this.nombre_equipo = nombre_equipo;
    }

    public int getId_entrenador() {
        return id_entrenador;
    }

    public void setId_entrenador(int id_entrenador) {
        this.id_entrenador = id_entrenador;
    }

    public String getNom_entrenador() {
        return nom_entrenador;
    }

    public void setNom_entrenador(String nom_entrenador) {
        this.nom_entrenador = nom_entrenador;
    }

    public Date getFec_nacimiento_e() {
        return fec_nacimiento_e;
    }

    public void setFec_nacimiento_e(Date fec_nacimiento_e) {
        this.fec_nacimiento_e = fec_nacimiento_e;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_equipo);
        dest.writeString(nombre_equipo);
        dest.writeInt(id_entrenador);
        dest.writeString(nom_entrenador);
    }
}

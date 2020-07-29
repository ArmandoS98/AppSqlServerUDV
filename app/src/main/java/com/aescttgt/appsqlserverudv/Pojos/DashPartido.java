package com.aescttgt.appsqlserverudv.Pojos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class DashPartido implements Parcelable {

    private int id_partido;
    private int id_equipo_local;
    private int id_equipo_visitante;
    private Date jornada;
    private String nombre_campeonato;
    private String Equipo_Local;
    private String Equipo_Visitante;
    private int Goles_Local;
    private int Goles_Visitante;

    public DashPartido() {
    }

    public DashPartido(int id_partido, int id_equipo_local, int id_equipo_visitante, Date jornada, String nombre_campeonato, String equipo_Local, String equipo_Visitante, int goles_Local, int goles_Visitante) {
        this.id_partido = id_partido;
        this.id_equipo_local = id_equipo_local;
        this.id_equipo_visitante = id_equipo_visitante;
        this.jornada = jornada;
        this.nombre_campeonato = nombre_campeonato;
        this.Equipo_Local = equipo_Local;
        this.Equipo_Visitante = equipo_Visitante;
        this.Goles_Local = goles_Local;
        this.Goles_Visitante = goles_Visitante;
    }

    protected DashPartido(Parcel in) {
        id_partido = in.readInt();
        id_equipo_local = in.readInt();
        id_equipo_visitante = in.readInt();
        nombre_campeonato = in.readString();
        Equipo_Local = in.readString();
        Equipo_Visitante = in.readString();
        Goles_Local = in.readInt();
        Goles_Visitante = in.readInt();
    }

    public static final Creator<DashPartido> CREATOR = new Creator<DashPartido>() {
        @Override
        public DashPartido createFromParcel(Parcel in) {
            return new DashPartido(in);
        }

        @Override
        public DashPartido[] newArray(int size) {
            return new DashPartido[size];
        }
    };

    public int getId_partido() {
        return id_partido;
    }

    public void setId_partido(int id_partido) {
        this.id_partido = id_partido;
    }

    public int getId_equipo_local() {
        return id_equipo_local;
    }

    public void setId_equipo_local(int id_equipo_local) {
        this.id_equipo_local = id_equipo_local;
    }

    public int getId_equipo_visitante() {
        return id_equipo_visitante;
    }

    public void setId_equipo_visitante(int id_equipo_visitante) {
        this.id_equipo_visitante = id_equipo_visitante;
    }

    public Date getJornada() {
        return jornada;
    }

    public void setJornada(Date jornada) {
        this.jornada = jornada;
    }

    public String getNombre_campeonato() {
        return nombre_campeonato;
    }

    public void setNombre_campeonato(String nombre_campeonato) {
        this.nombre_campeonato = nombre_campeonato;
    }

    public String getEquipo_Local() {
        return Equipo_Local;
    }

    public void setEquipo_Local(String equipo_Local) {
        Equipo_Local = equipo_Local;
    }

    public String getEquipo_Visitante() {
        return Equipo_Visitante;
    }

    public void setEquipo_Visitante(String equipo_Visitante) {
        Equipo_Visitante = equipo_Visitante;
    }

    public int getGoles_Local() {
        return Goles_Local;
    }

    public void setGoles_Local(int goles_Local) {
        Goles_Local = goles_Local;
    }

    public int getGoles_Visitante() {
        return Goles_Visitante;
    }

    public void setGoles_Visitante(int goles_Visitante) {
        Goles_Visitante = goles_Visitante;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_partido);
        dest.writeInt(id_equipo_local);
        dest.writeInt(id_equipo_visitante);
        dest.writeString(nombre_campeonato);
        dest.writeString(Equipo_Local);
        dest.writeString(Equipo_Visitante);
        dest.writeInt(Goles_Local);
        dest.writeInt(Goles_Visitante);
    }
}

package com.aescttgt.appsqlserverudv.Pojos;

import android.os.Parcel;
import android.os.Parcelable;

public class Aliniacion implements Parcelable {
    private int id_alineacion;
    private int id_jugador;
    private int id_equipo;
    private int id_partido;
    private int id_alineacion_desc;
    private String nombre_jugador;
    private String num_camisola;
    private String descripcion;

    public Aliniacion() {
    }

    public Aliniacion(int id_alineacion, int id_jugador, int id_equipo, int id_partido, int id_alineacion_desc, String nombre_jugador, String num_camisola, String descripcion) {
        this.id_alineacion = id_alineacion;
        this.id_jugador = id_jugador;
        this.id_equipo = id_equipo;
        this.id_partido = id_partido;
        this.id_alineacion_desc = id_alineacion_desc;
        this.nombre_jugador = nombre_jugador;
        this.num_camisola = num_camisola;
        this.descripcion = descripcion;
    }

    protected Aliniacion(Parcel in) {
        id_alineacion = in.readInt();
        id_jugador = in.readInt();
        id_equipo = in.readInt();
        id_partido = in.readInt();
        id_alineacion_desc = in.readInt();
        nombre_jugador = in.readString();
        num_camisola = in.readString();
        descripcion = in.readString();
    }

    public static final Creator<Aliniacion> CREATOR = new Creator<Aliniacion>() {
        @Override
        public Aliniacion createFromParcel(Parcel in) {
            return new Aliniacion(in);
        }

        @Override
        public Aliniacion[] newArray(int size) {
            return new Aliniacion[size];
        }
    };

    public int getId_alineacion() {
        return id_alineacion;
    }

    public void setId_alineacion(int id_alineacion) {
        this.id_alineacion = id_alineacion;
    }

    public int getId_jugador() {
        return id_jugador;
    }

    public void setId_jugador(int id_jugador) {
        this.id_jugador = id_jugador;
    }

    public int getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(int id_equipo) {
        this.id_equipo = id_equipo;
    }

    public int getId_partido() {
        return id_partido;
    }

    public void setId_partido(int id_partido) {
        this.id_partido = id_partido;
    }

    public int getId_alineacion_desc() {
        return id_alineacion_desc;
    }

    public void setId_alineacion_desc(int id_alineacion_desc) {
        this.id_alineacion_desc = id_alineacion_desc;
    }

    public String getNombre_jugador() {
        return nombre_jugador;
    }

    public void setNombre_jugador(String nombre_jugador) {
        this.nombre_jugador = nombre_jugador;
    }

    public String getNum_camisola() {
        return num_camisola;
    }

    public void setNum_camisola(String num_camisola) {
        this.num_camisola = num_camisola;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_alineacion);
        dest.writeInt(id_jugador);
        dest.writeInt(id_equipo);
        dest.writeInt(id_partido);
        dest.writeInt(id_alineacion_desc);
        dest.writeString(nombre_jugador);
        dest.writeString(num_camisola);
        dest.writeString(descripcion);
    }
}

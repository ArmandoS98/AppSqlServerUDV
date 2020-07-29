package com.aescttgt.appsqlserverudv.Pojos;

public class Pais {
    private int id;
    private String nombre_pais;

    public Pais() {
    }

    public Pais(int id, String nombre_pais) {
        this.id = id;
        this.nombre_pais = nombre_pais;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_pais() {
        return nombre_pais;
    }

    public void setNombre_pais(String nombre_pais) {
        this.nombre_pais = nombre_pais;
    }
}

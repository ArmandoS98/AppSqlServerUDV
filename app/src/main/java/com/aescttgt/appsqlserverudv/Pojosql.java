package com.aescttgt.appsqlserverudv;

public class Pojosql {

    private int id;
    private String campo1;
    private String campo2;

    public Pojosql() {
    }

    public Pojosql(int id, String campo1, String campo2) {
        this.id = id;
        this.campo1 = campo1;
        this.campo2 = campo2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCampo1() {
        return campo1;
    }

    public void setCampo1(String campo1) {
        this.campo1 = campo1;
    }

    public String getCampo2() {
        return campo2;
    }

    public void setCampo2(String campo2) {
        this.campo2 = campo2;
    }
}

package com.aescttgt.appsqlserverudv.Pojos;

import java.util.Date;

public class Jugador {
    private char C_JUGADOR;
    private String N_JUGADOR;
    private Date D_NACIMIENTO;

    public Jugador() {
    }

    public Jugador(char c_JUGADOR, String n_JUGADOR, Date d_NACIMIENTO) {
        C_JUGADOR = c_JUGADOR;
        N_JUGADOR = n_JUGADOR;
        D_NACIMIENTO = d_NACIMIENTO;
    }

    public char getC_JUGADOR() {
        return C_JUGADOR;
    }

    public void setC_JUGADOR(char c_JUGADOR) {
        C_JUGADOR = c_JUGADOR;
    }

    public String getN_JUGADOR() {
        return N_JUGADOR;
    }

    public void setN_JUGADOR(String n_JUGADOR) {
        N_JUGADOR = n_JUGADOR;
    }

    public Date getD_NACIMIENTO() {
        return D_NACIMIENTO;
    }

    public void setD_NACIMIENTO(Date d_NACIMIENTO) {
        D_NACIMIENTO = d_NACIMIENTO;
    }
}

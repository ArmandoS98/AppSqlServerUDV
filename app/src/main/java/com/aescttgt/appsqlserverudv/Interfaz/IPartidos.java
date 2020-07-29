package com.aescttgt.appsqlserverudv.Interfaz;

import com.aescttgt.appsqlserverudv.Pojos.DashPartido;
import com.aescttgt.appsqlserverudv.Pojos.JugadorDash;

public interface IPartidos {
    void onDashPartido(DashPartido dashPartido);
    void onDashJugador(JugadorDash jugadorDash);
}

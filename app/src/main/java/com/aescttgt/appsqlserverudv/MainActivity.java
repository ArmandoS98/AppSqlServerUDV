package com.aescttgt.appsqlserverudv;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.aescttgt.appsqlserverudv.Interfaz.IPartidos;
import com.aescttgt.appsqlserverudv.Pojos.DashPartido;
import com.aescttgt.appsqlserverudv.Pojos.Equipo;
import com.aescttgt.appsqlserverudv.Pojos.JugadorDash;
import com.aescttgt.appsqlserverudv.SharedPrefencies.SharedPrefManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements IPartidos {
    private static final String TAG = "MainActivity";
    private AppBarConfiguration mAppBarConfiguration;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_actualizar, R.id.nav_eliminar, R.id.nav_configuracion, R.id.nav_eLiminar_Entrenador, R.id.nav_agregar_Partido)
                .setDrawerLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        createSQLQueryAlineacion();
//        createSQLQueryEstadistica();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    private void intent(int destino, boolean arg, DashPartido dashPartido) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.destino), dashPartido);

        navController.navigate(destino, ((arg) ? bundle : null), cargarOpciones());
    }

    private void intent(int destino, boolean arg, Equipo equipo) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.destino), equipo);

        navController.navigate(destino, ((arg) ? bundle : null), cargarOpciones());
    }

    private void intent(int destino, boolean arg, JugadorDash equipo) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.destino), equipo);

        navController.navigate(destino, ((arg) ? bundle : null), cargarOpciones());
    }

    private NavOptions cargarOpciones() {
        NavOptions.Builder builder = new NavOptions.Builder()
                .setLaunchSingleTop(true)
                .setEnterAnim(R.anim.nav_default_enter_anim)
                .setExitAnim(R.anim.nav_default_exit_anim)
                .setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
                .setPopExitAnim(R.anim.nav_default_pop_exit_anim);

        NavOptions options = builder.build();
        return options;
    }


    @Override
    public void onDashPartido(DashPartido dashPartido) {
        intent(R.id.partidoDetalleFragment, true, dashPartido);
    }

    @Override
    public void onEquiupo(Equipo equipo) {
        intent(R.id.nav_detalle_equipo, true, equipo);
    }

    @Override
    public void onDashJugador(JugadorDash jugadorDash) {
        intent(R.id.jugadorDetalleFragment, true, jugadorDash);
    }


    private void createSQLQuery() {
        int pivote = 1;
        for (int j = 1; j <= 32; j++) {//Equipos
            int fin = (j * 11);
            int inicio = (fin - 11) + 1;
            int camisola = 1;
            System.out.println("Inicio: " + inicio + " fin: " + fin);
            for (int i = inicio; i <= fin; i++) {//Jugadores
//                System.out.println("'2020-07-09' " + j + ", Jugador: " + i);
                System.out.println("('2020-07-09','2019-08-08'," + i + "," + j + "," + camisola + "), ");
                camisola++;
            }
        }
    }

    private void createSQLQueryAlineacion() {
        int pivote = 1;
        int pocision = 1;
        for (int j = 1; j <= 32; j++) {//Equipos
            int fin = (j * 11);
            int inicio = (fin - 11) + 1;
            int camisola = 1;
            System.out.println("Inicio: " + inicio + " fin: " + fin);
            for (int i = inicio; i <= fin; i++) {//Jugadores
                if (i < (inicio + 3))
                    pocision = 1;
                else if (i > (inicio + 3) && i <= (inicio + 6))
                    pocision = 2;
                else if (i > (inicio + 6) && i <= (inicio + 9))
                    pocision = 3;
                else if (i > (inicio + 9))
                    pocision = 4;
                System.out.println("(" + i + "," + j + "," + pivote + "," + pocision + ")");
            }
            if ((j % 2) == 0)
                pivote++;
        }
    }

    private void createSQLQueryEstadistica() {
        int pivote = 1;
        int pocision = 1;
        Random rand = new Random();
        for (int j = 1; j <= 32; j++) {//Equipos
            int fin = ((j * 11));
            int inicio = (fin - 11) + 1;
//            System.out.println("Inicio: " + inicio + " fin: " + fin);
            for (int i = inicio; i <= fin; i++) {//Jugadores
                System.out.println("(" + rand.nextInt(2) + "," + rand.nextInt(2) + ",0," + (rand.nextInt(100 - 50) + 50) + "," + rand.nextInt(40) + "," + pivote + "," + j + "," + (rand.nextInt(fin - inicio) + inicio) + "),");
            }
            if ((j % 2) == 0)
                pivote++;
        }
    }

    private void createSQLPartidosQuery() {
        int pivote = 1;
        for (int j = 1; j <= 16; j += 2) {//Equipos
            System.out.println("INSERT INTO Partido (jornada,id_temporada,id_campeonato,id_equipo_local,id_equipo_visitante) VALUES ('2020-07-25',1,1," + j + "," + (j + 1) + ")");
        }
    }

    private void createSQLGolesQuery() {
        Random rand = new Random();
        int fin = 2;
        for (int j = 1; j <= 32; j++) {//Equipos
            int fin_c = (j * 11);
            int inicio = (fin_c - 11) + 1;
//            System.out.println("val: " + rand.nextInt(fin) + " Eqiopoc: " + j + " jugador = " + (rand.nextInt(fin_c - inicio) + inicio) + " jugador_asistencia = " + (rand.nextInt(fin_c - inicio) + inicio));
            System.out.println("INSERT INTO Gol (estado,id_jugador,id_partido,min_gol,asistencia,cantidad) VALUES (" + j + "," + (rand.nextInt(fin_c - inicio) + inicio) + "," + getPartido(j) + "," + rand.nextInt(100) + "," + (rand.nextInt(fin_c - inicio) + inicio) + "," + rand.nextInt(fin) + ")");
        }
    }

    private int getPartido(int i) {
        switch (i) {
            case 1:
            case 2:
                return 1;
            case 3:
            case 4:
                return 2;
            case 5:
            case 6:
                return 3;
            case 7:
            case 8:
                return 4;
            case 9:
            case 10:
                return 5;
            case 11:
            case 12:
                return 6;
            case 13:
            case 14:
                return 7;
            case 15:
            case 16:
                return 8;
            case 17:
            case 18:
                return 9;
            case 19:
            case 20:
                return 10;
            case 21:
            case 22:
                return 11;
            case 23:
            case 24:
                return 12;
            case 25:
            case 26:
                return 13;
            case 27:
            case 28:
                return 14;
            case 29:
            case 30:
                return 15;
            case 31:
            case 32:
                return 16;
        }
        return -1;
    }


}
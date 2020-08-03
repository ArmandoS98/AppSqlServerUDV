package com.aescttgt.appsqlserverudv.ui.dashView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aescttgt.appsqlserverudv.Adaptadores.DashAlineacionAdapter;
import com.aescttgt.appsqlserverudv.Adaptadores.DashJugadorAdapter;
import com.aescttgt.appsqlserverudv.Connection.DatabaseConnection;
import com.aescttgt.appsqlserverudv.Pojos.Aliniacion;
import com.aescttgt.appsqlserverudv.Pojos.DashPartido;
import com.aescttgt.appsqlserverudv.Pojos.JugadorDash;
import com.aescttgt.appsqlserverudv.R;
import com.aescttgt.appsqlserverudv.ViewAnimatorSlideUpDown.AnimSD;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PartidoDetalleFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {
    private static final String TAG = "PartidoDetalleFragment";
    private AppBarLayout appBar;
    private TabLayout tabLayout;
    private RelativeLayout vpPaginas;
    private TextView mTextViewGoles;
    private RadioButton mRadioButtonLocal;
    private RadioButton mRadioButtonVisitante;
    private RadioGroup radioGroup;
    private View contenedor;
    private DashPartido mDashPartido;
    private RecyclerView mRecyclerView;
    private DashJugadorAdapter mConsultaAdapter;
    private DashAlineacionAdapter mDashAlineacionAdapter;
    private boolean eslocal = true;
    private ImageView mTextViewTab2;
    private CardView mTextViewTab3;
    private RelativeLayout mRelativeLayoutTab2;
    private TextView mTextViewAliniaciones;
    private RecyclerView mRecyclerViewTb2;

    private TextView c1;
    private TextView c2;
    private TextView c3;
    private TextView c4;
    private TextView c5;
    private TextView c6;
    private TextView c7;
    private TextView c8;
    private TextView c9;
    private TextView c10;
    private TextView c11;

    private TextView nombre_c1;
    private TextView nombre_c2;
    private TextView nombre_c3;
    private TextView nombre_c4;
    private TextView nombre_c5;
    private TextView nombre_c6;
    private TextView nombre_c7;
    private TextView nombre_c8;
    private TextView nombre_c9;
    private TextView nombre_c10;
    private TextView nombre_c11;

    private TextView mTextViewNombreEquipo;
    private TextView mteTextViewMinsJugados;
    private TextView mteTextViewTAmarillas;
    private TextView mteTextViewTRojas;
    private TextView mteTextViewTGoles;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_partido_detalle, container, false);
        contenedor = (View) container.getParent();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDashPartido = getArguments().getParcelable(getString(R.string.destino));
        appBar = contenedor.findViewById(R.id.appBar);
        radioGroup = view.findViewById(R.id.radioGroups);
        mRelativeLayoutTab2 = view.findViewById(R.id.real2);
        mRecyclerViewTb2 = view.findViewById(R.id.recyclerviewtb2);
        mTextViewAliniaciones = view.findViewById(R.id.tv_titulo);


        mTextViewNombreEquipo = view.findViewById(R.id.tv_nombre_equipo);
        mteTextViewMinsJugados = view.findViewById(R.id.tv_min_jugados);
        mteTextViewTAmarillas = view.findViewById(R.id.tv_total_amarillas);
        mteTextViewTRojas = view.findViewById(R.id.tv_total_rojas);
        mteTextViewTGoles = view.findViewById(R.id.tv_total_goles);


        c1 = view.findViewById(R.id.tvjnumero1);
        c2 = view.findViewById(R.id.tvjnumero2);
        c3 = view.findViewById(R.id.tvjnumero3);
        c4 = view.findViewById(R.id.tvjnumero4);
        c5 = view.findViewById(R.id.tvjnumero5);
        c6 = view.findViewById(R.id.tvjnumero6);
        c7 = view.findViewById(R.id.tvjnumero7);
        c8 = view.findViewById(R.id.tvjnumero8);
        c9 = view.findViewById(R.id.tvjnumero9);
        c10 = view.findViewById(R.id.tvjnumero10);
        c11 = view.findViewById(R.id.tvjnumero11);

        nombre_c1 = view.findViewById(R.id.tvjnombre1);
        nombre_c2 = view.findViewById(R.id.tvjnombre2);
        nombre_c3 = view.findViewById(R.id.tvjnombre3);
        nombre_c4 = view.findViewById(R.id.tvjnombre4);
        nombre_c5 = view.findViewById(R.id.tvjnombre5);
        nombre_c6 = view.findViewById(R.id.tvjnombre6);
        nombre_c7 = view.findViewById(R.id.tvjnombre7);
        nombre_c8 = view.findViewById(R.id.tvjnombre8);
        nombre_c9 = view.findViewById(R.id.tvjnombre9);
        nombre_c10 = view.findViewById(R.id.tvjnombre10);
        nombre_c11 = view.findViewById(R.id.tvjnombre11);

        mTextViewTab2 = view.findViewById(R.id.tv_tab2);
        mTextViewTab3 = view.findViewById(R.id.cardviewtb3);
        mTextViewGoles = view.findViewById(R.id.tv_goles);
        mRadioButtonLocal = view.findViewById(R.id.rbEfectivo);
        mRecyclerView = view.findViewById(R.id.recyclerview);
        mRadioButtonVisitante = view.findViewById(R.id.rbTarjeta);
        mRadioButtonLocal.setText(mDashPartido.getEquipo_Local() + "     " + mDashPartido.getGoles_Local() + "     ");
        mRadioButtonVisitante.setText("     " + mDashPartido.getGoles_Visitante() + "    " + mDashPartido.getEquipo_Visitante());
        tabLayout = new TabLayout(getActivity());
        radioGroup.setOnCheckedChangeListener(this);
//
        tabLayout.addTab(tabLayout.newTab().setText("Jugadores"));
        tabLayout.addTab(tabLayout.newTab().setText("Alineacion"));
        tabLayout.addTab(tabLayout.newTab().setText("Estadisticas"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#000000"));
        appBar.addView(tabLayout);


        vpPaginas = view.findViewById(R.id.vpPaginas);
        consultarJugadores(mDashPartido.getId_equipo_local());
        if (mDashPartido.getGoles_Local() > 0)
            consultarGoles(mDashPartido.getId_equipo_local(), mDashPartido.getId_partido());
        else
            mTextViewGoles.setText("");

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        AnimSD.mostrar(mRecyclerView);
                        AnimSD.ocultar(mRelativeLayoutTab2);
                        AnimSD.ocultar(mTextViewTab3);
                        consultarJugadores((eslocal) ? mDashPartido.getId_equipo_local() : mDashPartido.getId_equipo_visitante());
                        break;
                    case 1:
                        AnimSD.ocultar(mRecyclerView);
                        AnimSD.mostrar(mRelativeLayoutTab2);
                        AnimSD.ocultar(mTextViewTab3);
                        consutaAliniacion((eslocal) ? mDashPartido.getId_equipo_local() : mDashPartido.getId_equipo_visitante());
                        break;
                    case 2:
                        AnimSD.ocultar(mRecyclerView);
                        AnimSD.ocultar(mRelativeLayoutTab2);
                        AnimSD.mostrar(mTextViewTab3);
                        consultaEstadistica((eslocal) ? mDashPartido.getId_equipo_local() : mDashPartido.getId_equipo_visitante());
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        Log.e(TAG, "onViewCreated: Val: " + mDashPartido);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        updateUI(group, radioGroup.getCheckedRadioButtonId());
    }

    private void updateUI(View v, int screenStyle) {
        if (screenStyle == R.id.rbTarjeta) {
            eslocal = false;
            if (mDashPartido.getGoles_Visitante() > 0)
                consultarGoles(mDashPartido.getId_equipo_visitante(), mDashPartido.getId_partido());
            else
                mTextViewGoles.setText("");

            switch (tabLayout.getSelectedTabPosition()) {
                case 0:
                    consultarJugadores(mDashPartido.getId_equipo_visitante());
                    break;
                case 1:
                    consutaAliniacion(mDashPartido.getId_equipo_visitante());
                    break;
                case 2:
                    consultaEstadistica(mDashPartido.getId_equipo_visitante());
                    break;
            }

        } else if (screenStyle == R.id.rbEfectivo) {
            eslocal = true;
            if (mDashPartido.getGoles_Local() > 0)
                consultarGoles(mDashPartido.getId_equipo_local(), mDashPartido.getId_partido());
            else
                mTextViewGoles.setText("");

            switch (tabLayout.getSelectedTabPosition()) {
                case 0:
                    consultarJugadores(mDashPartido.getId_equipo_local());
                    break;
                case 1:
                    consutaAliniacion(mDashPartido.getId_equipo_local());
                    break;
                case 2:
                    consultaEstadistica(mDashPartido.getId_equipo_local());
                    break;
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        appBar.removeView(tabLayout);
    }

    private void consultarJugadores(int id_equipo) {
        try {
            String storedProcudureCall = "{call ConsultarDatosJugadoresPorEquipo(?)};";
            CallableStatement cst = DatabaseConnection.getInstance(getContext()).getConnection().prepareCall(storedProcudureCall);
            cst.setInt(1, id_equipo);
            ResultSet rs = cst.executeQuery();

            ArrayList<JugadorDash> pojos = new ArrayList<>();
            while (rs.next()) {
                JugadorDash mPais = new JugadorDash();
                mPais.setId_jugador(rs.getInt("id_jugador"));
                mPais.setNombre_jugador(rs.getString("nombre_jugador"));
                mPais.setFec_nacimiento(rs.getDate("fec_nacimiento"));
                mPais.setStatus_jugador(rs.getString("status_jugador"));
                mPais.setNombre_pais(rs.getString("nombre_pais"));
                mPais.setId_pais(rs.getInt("id_pais"));
                pojos.add(mPais);
            }

            Log.e(TAG, "consultar: Paices respuesta: " + pojos);

            rs.close();
            cst.close();
            mConsultaAdapter = null;
            initRecyclerView(pojos);
        } catch (Exception e) {
            Log.e(TAG, "insetar: Error: " + e.getMessage());
        }

    }

    private void consultarGoles(int id_equipo, int id_partido) {
        try {
            String sql = "SELECT min_gol, cantidad FROM Gol g WHERE id_partido = " + id_partido + " AND estado = " + id_equipo + " ORDER BY min_gol ASC ";
            PreparedStatement pst = DatabaseConnection.getInstance(getContext()).getConnection().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            String goles = "¡GOOOOL!\n";
            while (rs.next()) {
                if (rs.getInt("cantidad") > 0)
                    goles += rs.getFloat("min_gol") + "', ";
            }
            mTextViewGoles.setText(goles.substring(1, (goles.length() - 2)));
            rs.close();
            pst.close();
        } catch (Exception e) {
            Log.e(TAG, "insetar: Error: " + e.getMessage());
        }

    }

    private void consutaAliniacion(int id_equipo) {
        try {
            String sql = "SELECT a.*, j.nombre_jugador, c.num_camisola, ad.descripcion FROM Alineacion a INNER JOIN Jugador j ON a.id_jugador = j.id_jugador INNER JOIN Contrato c ON a.id_jugador = c.id_jugador INNER JOIN Alineacion_desc ad ON ad.id_alineacion_desc = a.id_alineacion_desc WHERE a.id_equipo = " + id_equipo + " ORDER BY ad.id_alineacion_desc";
            PreparedStatement pst = DatabaseConnection.getInstance(getContext()).getConnection().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();


            ArrayList<Aliniacion> pojos = new ArrayList<>();
            while (rs.next()) {
                Aliniacion mPais = new Aliniacion();
                mPais.setId_alineacion(rs.getInt("id_alineacion"));
                mPais.setId_jugador(rs.getInt("id_jugador"));
                mPais.setId_equipo(rs.getInt("id_equipo"));
                mPais.setId_partido(rs.getInt("id_partido"));
                mPais.setId_alineacion_desc(rs.getInt("id_alineacion_desc"));
                mPais.setNombre_jugador(rs.getString("nombre_jugador"));
                mPais.setNum_camisola(rs.getString("num_camisola"));
                mPais.setDescripcion(rs.getString("descripcion"));
                pojos.add(mPais);
            }

            rs.close();
            pst.close();

            Log.e(TAG, "consutaAliniacion: ver" + pojos);

            c1.setText(pojos.get(0).getNum_camisola());
            nombre_c1.setText(pojos.get(0).getNombre_jugador().split(" ")[0].substring(0, 1) + ". " + pojos.get(0).getNombre_jugador().split(" ")[1]);

            c2.setText(pojos.get(1).getNum_camisola());
            nombre_c2.setText(pojos.get(1).getNombre_jugador().split(" ")[0].substring(0, 1) + ". " + pojos.get(1).getNombre_jugador().split(" ")[1]);

            c3.setText(pojos.get(2).getNum_camisola());
            nombre_c3.setText(pojos.get(2).getNombre_jugador().split(" ")[0].substring(0, 1) + ". " + pojos.get(2).getNombre_jugador().split(" ")[1]);

            c4.setText(pojos.get(3).getNum_camisola());
            nombre_c4.setText(pojos.get(3).getNombre_jugador().split(" ")[0].substring(0, 1) + ". " + pojos.get(3).getNombre_jugador().split(" ")[1]);

            c5.setText(pojos.get(4).getNum_camisola());
            nombre_c5.setText(pojos.get(4).getNombre_jugador().split(" ")[0].substring(0, 1) + ". " + pojos.get(4).getNombre_jugador().split(" ")[1]);

            c6.setText(pojos.get(5).getNum_camisola());
            nombre_c6.setText(pojos.get(5).getNombre_jugador().split(" ")[0].substring(0, 1) + ". " + pojos.get(5).getNombre_jugador().split(" ")[1]);

            c7.setText(pojos.get(6).getNum_camisola());
            nombre_c7.setText(pojos.get(6).getNombre_jugador().split(" ")[0].substring(0, 1) + ". " + pojos.get(6).getNombre_jugador().split(" ")[1]);

            c8.setText(pojos.get(7).getNum_camisola());
            nombre_c8.setText(pojos.get(7).getNombre_jugador().split(" ")[0].substring(0, 1) + ". " + pojos.get(7).getNombre_jugador().split(" ")[1]);

            c9.setText(pojos.get(8).getNum_camisola());
            nombre_c9.setText(pojos.get(8).getNombre_jugador().split(" ")[0].substring(0, 1) + ". " + pojos.get(8).getNombre_jugador().split(" ")[1]);

            c10.setText(pojos.get(9).getNum_camisola());
            nombre_c10.setText(pojos.get(9).getNombre_jugador().split(" ")[0].substring(0, 1) + ". " + pojos.get(9).getNombre_jugador().split(" ")[1]);

            c11.setText(pojos.get(10).getNum_camisola());
            nombre_c11.setText(pojos.get(10).getNombre_jugador().split(" ")[0].substring(0, 1) + ". " + pojos.get(10).getNombre_jugador().split(" ")[1]);
            mDashAlineacionAdapter = null;
            initRecyclerView2(pojos);
        } catch (Exception e) {
            Log.e(TAG, "insetar: Error: " + e.getMessage());
        }

    }

    private void consultaEstadistica(int id_equipo) {
        try {
            String storedProcudureCall = "{call EstadisticasPorEquipo(?,?)};";
            CallableStatement cst = DatabaseConnection.getInstance(getContext()).getConnection().prepareCall(storedProcudureCall);
            cst.setInt(1, id_equipo);
            cst.setInt(2,mDashPartido.getId_partido());
            ResultSet rs = cst.executeQuery();

            if (rs.next()) {
                mTextViewNombreEquipo.setText(rs.getString("nombre_equipo"));
                mteTextViewMinsJugados.setText(String.valueOf(rs.getInt("minsJugados")));
                mteTextViewTAmarillas.setText(String.valueOf(rs.getInt("totalAmarillas")));
                mteTextViewTRojas.setText(String.valueOf(rs.getInt("totalRojas")));
                mteTextViewTGoles.setText(String.valueOf(rs.getInt("totalGoles")));
            }

            rs.close();
            cst.close();
        } catch (Exception e) {
            Log.e(TAG, "insetar: Error: " + e.getMessage());
        }

    }

    private void initRecyclerView(ArrayList<JugadorDash> valores) {
        if (mConsultaAdapter == null) {
            mRecyclerView.removeAllViews();
            mConsultaAdapter = new DashJugadorAdapter(getContext(), valores);
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mConsultaAdapter);
    }

    private void initRecyclerView2(ArrayList<Aliniacion> valores) {
        if (mDashAlineacionAdapter == null) {
            mRecyclerViewTb2.removeAllViews();
            mDashAlineacionAdapter = new DashAlineacionAdapter(getContext(), valores);
        }

        mRecyclerViewTb2.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerViewTb2.setAdapter(mDashAlineacionAdapter);
    }

}
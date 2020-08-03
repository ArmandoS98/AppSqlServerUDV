package com.aescttgt.appsqlserverudv.ui.navViews;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aescttgt.appsqlserverudv.Adaptadores.DashJugadorAdapter;
import com.aescttgt.appsqlserverudv.Connection.DatabaseConnection;
import com.aescttgt.appsqlserverudv.Pojos.Equipo;
import com.aescttgt.appsqlserverudv.Pojos.JugadorDash;
import com.aescttgt.appsqlserverudv.R;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class JugadoresFragment extends Fragment {
    private static final String TAG = "EquipoIngresoFragment";
    private Equipo mEquipo;
    private RecyclerView mRecyclerView;
    private DashJugadorAdapter mConsultaAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_equipo_ingreso, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            mEquipo = getArguments().getParcelable(getString(R.string.destino));
            mRecyclerView = view.findViewById(R.id.recyclerview);
            new Thread(() -> {
                getActivity().runOnUiThread(() -> consultarJugadores());
            }).start();

        } catch (Exception ex) {
            Log.e(TAG, "onViewCreated: " + ex.getMessage());
        }
    }

    private void consultarJugadores() {
        try {
            String storedProcudureCall = "{call ConsultarDatosJugadoresPorEquipo(?)};";
            CallableStatement cst = DatabaseConnection.getInstance(getContext()).getConnection().prepareCall(storedProcudureCall);
            cst.setInt(1, mEquipo.getId_equipo());
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

    private void initRecyclerView(ArrayList<JugadorDash> valores) {
        if (mConsultaAdapter == null) {
            mRecyclerView.removeAllViews();
            mConsultaAdapter = new DashJugadorAdapter(getContext(), valores);
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mConsultaAdapter);
    }
}
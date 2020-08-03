package com.aescttgt.appsqlserverudv.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aescttgt.appsqlserverudv.Adaptadores.DashPartidoAdapter;
import com.aescttgt.appsqlserverudv.Adaptadores.EquiposAdapter;
import com.aescttgt.appsqlserverudv.Connection.DatabaseConnection;
import com.aescttgt.appsqlserverudv.Pojos.DashPartido;
import com.aescttgt.appsqlserverudv.Pojos.Equipo;
import com.aescttgt.appsqlserverudv.Pojos.JugadorDash;
import com.aescttgt.appsqlserverudv.R;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EquipoFragment extends Fragment {
    private static final String TAG = "EquipoFragment";

    private EquiposAdapter mConsultaAdapter;
    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_equipo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.recyclerview);
        obtenerEquipos();
    }

    private void obtenerEquipos() {
        try {
            String storedProcudureCall = "{call ObtenerInformacionEquipo};";
            CallableStatement cst = DatabaseConnection.getInstance(getContext()).getConnection().prepareCall(storedProcudureCall);
            ResultSet rs = cst.executeQuery();

            ArrayList<Equipo> pojos = new ArrayList<>();
            while (rs.next()) {
                Equipo mPais = new Equipo();
                mPais.setId_equipo(rs.getInt("id_equipo"));
                mPais.setNombre_equipo(rs.getString("nombre_equipo"));
                mPais.setId_entrenador(rs.getInt("id_entrenador"));
                mPais.setNom_entrenador(rs.getString("nom_entrenador"));
                mPais.setFec_nacimiento_e(rs.getDate("fec_nacimiento_e"));
                pojos.add(mPais);
            }


            Log.e(TAG, "consultar: Paices respuesta: " + pojos);

            rs.close();
            cst.close();
            initRecyclerView(pojos);
        } catch (Exception e) {
            Log.e(TAG, "insetar: Error: " + e.getMessage());
        }

    }

    private void initRecyclerView(ArrayList<Equipo> valores) {
        if (mConsultaAdapter == null) {
            mConsultaAdapter = new EquiposAdapter(getContext(), valores);
        }
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false));
        mRecyclerView.setAdapter(mConsultaAdapter);
    }

}
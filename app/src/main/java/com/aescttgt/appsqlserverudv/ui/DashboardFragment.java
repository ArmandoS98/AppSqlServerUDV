package com.aescttgt.appsqlserverudv.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aescttgt.appsqlserverudv.Adaptadores.DashPartidoAdapter;
import com.aescttgt.appsqlserverudv.Connection.DatabaseConnection;
import com.aescttgt.appsqlserverudv.Pojos.DashPartido;
import com.aescttgt.appsqlserverudv.R;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class DashboardFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "DashboardFragment";
    private RecyclerView mRecyclerView;
    private DashPartidoAdapter mConsultaAdapter;
    private SweetAlertDialog pDialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.recyclerview);
        consultar();
    }


    @Override
    public void onClick(View v) {

    }

    private void consultar() {
        try {

            String storedProcudureCall = "{call ObtenerInformacionPartido};";
            CallableStatement cst = DatabaseConnection.getInstance(getContext()).getConnection().prepareCall(storedProcudureCall);
            ResultSet rs = cst.executeQuery();

            ArrayList<DashPartido> pojos = new ArrayList<>();
            while (rs.next()) {
                DashPartido mPais = new DashPartido();
                mPais.setId_partido(rs.getInt("id_partido"));
                mPais.setId_equipo_local(rs.getInt("id_equipo_local"));
                mPais.setId_equipo_visitante(rs.getInt("id_equipo_visitante"));
                mPais.setJornada(rs.getDate("jornada"));
                mPais.setNombre_campeonato(rs.getString("nombre_campeonato"));
                mPais.setEquipo_Local(rs.getString("equipo_Local"));
                mPais.setEquipo_Visitante(rs.getString("equipo_Visitante"));
                mPais.setGoles_Local(rs.getInt("goles_Local"));
                mPais.setGoles_Visitante(rs.getInt("goles_Visitante"));
                pojos.add(mPais);
            }

            rs.close();
            cst.close();
            initRecyclerView(pojos);
        } catch (Exception e) {
            Log.e(TAG, "insetar: Error: " + e.getMessage());
        }

    }

    private void initRecyclerView(ArrayList<DashPartido> valores) {
        if (mConsultaAdapter == null) {
            mConsultaAdapter = new DashPartidoAdapter(getContext(), valores);
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mConsultaAdapter);
    }

}
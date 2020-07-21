package com.aescttgt.appsqlserverudv.ui.gallery;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aescttgt.appsqlserverudv.ConsultaAdapter;
import com.aescttgt.appsqlserverudv.Pojosql;
import com.aescttgt.appsqlserverudv.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GalleryFragment extends Fragment {
    private static final String TAG = "GalleryFragment";
    private RecyclerView mRecyclerView;
    private ConsultaAdapter mConsultaAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.recyclerview);
        consultar();
    }

    public Connection conexionBD() {
        Connection connection = null;
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.1.8;databaseName=test;user=db_udv;password=asantos123.;");
            Log.e(TAG, "conexionBD: Listo");
        } catch (Exception e) {
            Log.e(TAG, "conexionBD: Error: " + e.getMessage());
        }
        return connection;
    }

    private void consultar() {
        try {

            PreparedStatement pst = conexionBD().prepareStatement("SELECT * FROM android");
            ResultSet rs = pst.executeQuery();

            ArrayList<Pojosql> pojos = new ArrayList<>();
            while (rs.next()) {
                Pojosql pojosql = new Pojosql();
                pojosql.setId(rs.getInt("id"));
                pojosql.setCampo1(rs.getString("campo_1"));
                pojosql.setCampo2(rs.getString("campo_2"));
                pojos.add(pojosql);
            }
            rs.close();
            pst.close();

            //Lo cargamos
            initRecyclerView(pojos);
            Toast.makeText(getContext(), "DB CONSULTADA CON EXITO!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "insetar: Error: " + e.getMessage());
        }

    }

    private void initRecyclerView(ArrayList<Pojosql> valores) {
        if (mConsultaAdapter == null) {
            mConsultaAdapter = new ConsultaAdapter(getContext(), valores);
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mConsultaAdapter);
    }
}
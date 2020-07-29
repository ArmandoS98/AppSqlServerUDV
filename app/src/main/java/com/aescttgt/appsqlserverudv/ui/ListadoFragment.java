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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aescttgt.appsqlserverudv.Adaptadores.JugadorAdapterSub;
import com.aescttgt.appsqlserverudv.Connection.DatabaseConnection;
import com.aescttgt.appsqlserverudv.Pojos.Jugador;
import com.aescttgt.appsqlserverudv.Pojos.Pais;
import com.aescttgt.appsqlserverudv.R;
import com.aescttgt.appsqlserverudv.Utils.CustomDialogs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;
import kotlin.reflect.KFunction;

public class ListadoFragment extends Fragment {
    private static final String TAG = "GalleryFragment";
    private RecyclerView mRecyclerView;
    //    private ConsultaAdapter mConsultaAdapter;
    private JugadorAdapterSub mConsultaAdapter;
    private SweetAlertDialog pDialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.recyclerview);

        pDialog = new CustomDialogs(getContext()).dialogLoading("Consultado Datos");
        new Thread(new Runnable() {
            @Override
            public void run() {

                Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        consultar();
                    }
                });
            }
        }).start();
    }

    private void consultar() {
        try {

            PreparedStatement pst = DatabaseConnection.getInstance(getContext()).getConnection().prepareStatement("SELECT * FROM Pais");
            ResultSet rs = pst.executeQuery();

            ArrayList<Pais> pojos = new ArrayList<>();
            while (rs.next()) {
                Pais mPais = new Pais();
                mPais.setId(rs.getInt("id_pais"));
                mPais.setNombre_pais(rs.getString("nombre_pais"));
                pojos.add(mPais);
            }


            Log.e(TAG, "consultar: Paices respuesta: " + pojos);


          /*  ArrayList<Jugador> pojos = new ArrayList<>();
            while (rs.next()) {
                Jugador pojosql = new Jugador();
//                pojosql.setC_JUGADOR(rs.getCharacterStream("C_JUGADOR"));
                pojosql.setN_JUGADOR(rs.getString("N_JUGADOR"));
                pojosql.setD_NACIMIENTO(rs.getDate("D_NACIMIENTO"));
                pojos.add(pojosql);
            }*/
        /*    ArrayList<Pojosql> pojos = new ArrayList<>();
            while (rs.next()) {
                Pojosql pojosql = new Pojosql();
                pojosql.setId(rs.getInt("id"));
                pojosql.setCampo1(rs.getString("campo_1"));
                pojosql.setCampo2(rs.getString("campo_2"));
                pojos.add(pojosql);
            }*/
            rs.close();
            pst.close();

            //Lo cargamos
//            initRecyclerView(pojos);
            pDialog.dismissWithAnimation();
            Toast.makeText(getContext(), "DB CONSULTADA CON EXITO!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "insetar: Error: " + e.getMessage());
        }

    }

    private void initRecyclerView(ArrayList<Jugador> valores) {
        if (mConsultaAdapter == null) {
            mConsultaAdapter = new JugadorAdapterSub(getContext(), valores);
        }

        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false));
        mRecyclerView.setAdapter(mConsultaAdapter);
    }

}
package com.aescttgt.appsqlserverudv.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.aescttgt.appsqlserverudv.Connection.DatabaseConnection;
import com.aescttgt.appsqlserverudv.R;
import com.aescttgt.appsqlserverudv.Utils.CustomDialogs;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class EliminarEntrenadoFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "EliminarEntrenadoFragme";
    private SweetAlertDialog pDialog;
    private TextInputEditText mTextInputEditTextNombre;
    private Button mButtonEliminar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_eliminar_entrenado, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTextInputEditTextNombre = view.findViewById(R.id.txtFechaNac);
        mButtonEliminar = view.findViewById(R.id.btn_eliminar);
        mButtonEliminar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_eliminar)
            verificarSiJugadorExiste();
    }

    private void verificarSiJugadorExiste() {
        try {
            String storedProcudureCall = "{call BuscarEntrenadorPorNombres(?)};";
            CallableStatement cst = DatabaseConnection.getInstance(getContext()).getConnection().prepareCall(storedProcudureCall);
            cst.setString(1, mTextInputEditTextNombre.getText().toString());
            ResultSet rs = cst.executeQuery();

            int id_jugador = -1;
            while (rs.next())
                id_jugador = rs.getInt("id_entrenador");

            if (id_jugador != -1)
                guardarInformacion(id_jugador);
            else
                pDialog = new CustomDialogs(getContext()).dialogError("Jugador " + mTextInputEditTextNombre.getText().toString() + " No Encontrador!").setConfirmClickListener(sweetAlertDialog -> pDialog.dismissWithAnimation());
            rs.close();
            cst.close();
        } catch (Exception ex) {
            Log.e(TAG, "verificarSiJugadorExiste: " + ex.getMessage());
        }

    }

    private void guardarInformacion(int id_jugador) {
        try {
            String storedProcudureCall = "{call ElimiarEntrenadorEquipo(?)};";

            CallableStatement cst = DatabaseConnection.getInstance(getContext()).getConnection().prepareCall(storedProcudureCall);
            cst.setInt(1, id_jugador);

            int rs = cst.executeUpdate();

            if (rs > 0)
                pDialog = new CustomDialogs(getContext()).dialogResult("Entrenador Eliminado!").setConfirmClickListener(sweetAlertDialog -> pDialog.dismissWithAnimation());
            else


                Log.e(TAG, "actulizarInformaicon: Filas Fectadas: " + rs);

            cst.close();
        } catch (Exception e) {
            Log.e(TAG, "insetar: Error: " + e.getMessage());
        }
    }
}
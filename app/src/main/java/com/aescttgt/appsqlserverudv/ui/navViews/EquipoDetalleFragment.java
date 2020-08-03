package com.aescttgt.appsqlserverudv.ui.navViews;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.aescttgt.appsqlserverudv.Connection.DatabaseConnection;
import com.aescttgt.appsqlserverudv.Pojos.Equipo;
import com.aescttgt.appsqlserverudv.Pojos.JugadorDash;
import com.aescttgt.appsqlserverudv.R;
import com.aescttgt.appsqlserverudv.Utils.CustomDialogs;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class EquipoDetalleFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "JugadorIngresoFragment";
    private Equipo mEquipo;
    private TextInputEditText mTextInputEditTextNombreEquipo;
    private Button materialButtonActulizar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jugador_ingreso, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTextInputEditTextNombreEquipo = view.findViewById(R.id.txtNumeroDPI);
        try {
            mEquipo = getArguments().getParcelable(getString(R.string.destino));
            mTextInputEditTextNombreEquipo.setText(mEquipo.getNombre_equipo());
            materialButtonActulizar = view.findViewById(R.id.btn_actulizar);
            materialButtonActulizar.setOnClickListener(this);
        } catch (Exception ex) {
            Log.e(TAG, "onViewCreated: " + ex.getMessage());
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_actulizar) {
            actulizarInformaicon();
        }
    }

    SweetAlertDialog pDialog;
    private void actulizarInformaicon() {
        try {
            String storedProcudureCall = "{call UpdateNombreEquipo(?,?)};";
            CallableStatement cst = DatabaseConnection.getInstance(getContext()).getConnection().prepareCall(storedProcudureCall);
            cst.setInt(1, mEquipo.getId_equipo());
            cst.setString(2, mTextInputEditTextNombreEquipo.getText().toString());
            int rs = cst.executeUpdate();

            if (rs>0)
                pDialog = new CustomDialogs(getContext()).dialogResult("Equipo Actualizado!").setConfirmClickListener(sweetAlertDialog -> pDialog.dismissWithAnimation());


            Log.e(TAG, "actulizarInformaicon: Filas Fectadas: " + rs );

            cst.close();
        } catch (Exception e) {
            Log.e(TAG, "insetar: Error: " + e.getMessage());
        }
    }

}
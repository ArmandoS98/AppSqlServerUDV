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
import com.aescttgt.appsqlserverudv.R;
import com.aescttgt.appsqlserverudv.Utils.CustomDialogs;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.CallableStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class EntrenadorFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "EntrenadorFragment";
    private Equipo mEquipo;
    private TextInputEditText mTextInputEditTextNombreEntremado;
    private TextInputEditText mTextInputEditTextFechNacEntrenador;
    private Button materialButtonActulizar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_campeonato, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            mTextInputEditTextNombreEntremado = view.findViewById(R.id.txtNombreEntrenado);
            mTextInputEditTextFechNacEntrenador = view.findViewById(R.id.txtFechaEntrenado);
            mEquipo = getArguments().getParcelable(getString(R.string.destino));
            mTextInputEditTextNombreEntremado.setText(mEquipo.getNom_entrenador());
            try {
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String strDate = dateFormat.format(mEquipo.getFec_nacimiento_e());
                mTextInputEditTextFechNacEntrenador.setText(strDate);
            } catch (Exception ex) {
                Log.e(TAG, "onBindViewHolder: Error date " + ex.getMessage());
            }
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
            String storedProcudureCall = "{call UpdateNombreEntrenador(?,?,?)};";
            CallableStatement cst = DatabaseConnection.getInstance(getContext()).getConnection().prepareCall(storedProcudureCall);
            cst.setInt(1, mEquipo.getId_entrenador());
            cst.setString(2, mTextInputEditTextNombreEntremado.getText().toString());
            cst.setString(3, mTextInputEditTextFechNacEntrenador.getText().toString());
            int rs = cst.executeUpdate();

            if (rs>0)
                pDialog = new CustomDialogs(getContext()).dialogResult("Equipo Actualizado!").setConfirmClickListener(sweetAlertDialog -> pDialog.dismissWithAnimation());


            Log.e(TAG, "actulizarInformaicon: Filas Fectadas: " + rs);

            cst.close();
        } catch (Exception e) {
            Log.e(TAG, "insetar: Error: " + e.getMessage());
        }
    }
}
package com.aescttgt.appsqlserverudv.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.aescttgt.appsqlserverudv.Connection.DatabaseConnection;
import com.aescttgt.appsqlserverudv.Pojos.Equipo;
import com.aescttgt.appsqlserverudv.Pojos.JugadorDash;
import com.aescttgt.appsqlserverudv.R;
import com.aescttgt.appsqlserverudv.Utils.CustomDialogs;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class JugadorDetalleFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "JugadorDetalleFragment";
    private JugadorDash mEquipo;
    private TextInputEditText mTextInputEditTextNombre;
    private TextInputEditText mTextInputEditTextFechaNac;
    private Switch mASwitchActivo;
    private Spinner mTextInputEditTextNacionalidad;
    private Button mButtonActualizar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jugador_detalle, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {
            mEquipo = getArguments().getParcelable(getString(R.string.destino));

            mTextInputEditTextNombre = view.findViewById(R.id.txtNombreJugador);
            mTextInputEditTextFechaNac = view.findViewById(R.id.txtFechaNac);
            mTextInputEditTextNacionalidad = view.findViewById(R.id.tilNacionalidad);
            mASwitchActivo = view.findViewById(R.id.tilEstado);
            mButtonActualizar = view.findViewById(R.id.btn_actulizar);
            mButtonActualizar.setOnClickListener(this);


            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = dateFormat.format(mEquipo.getFec_nacimiento());
            mTextInputEditTextFechaNac.setText(strDate);

            mTextInputEditTextNombre.setText(mEquipo.getNombre_jugador());
            mASwitchActivo.setChecked(mEquipo.getStatus_jugador().equals("A"));

            mTextInputEditTextNacionalidad.setAdapter(consultarPaices());
            mTextInputEditTextNacionalidad.setSelection((mEquipo.getId_pais() - 1));

        } catch (Exception ex) {
            Log.e(TAG, "onBindViewHolder: Error date " + ex.getMessage());
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
            int pos = (int) (mTextInputEditTextNacionalidad.getSelectedItemId() + 1);
            String storedProcudureCall = "{call UpdateDatosJugos(?,?,?,?,?)};";
            CallableStatement cst = DatabaseConnection.getInstance(getContext()).getConnection().prepareCall(storedProcudureCall);
            cst.setInt(1, mEquipo.getId_jugador());
            cst.setString(2, mTextInputEditTextNombre.getText().toString());
            cst.setString(3,  mTextInputEditTextFechaNac.getText().toString());
            cst.setInt(4, pos);
            cst.setString(5, mASwitchActivo.isChecked() ? "A" : "B");
            int rs = cst.executeUpdate();

            if (rs > 0)
                pDialog = new CustomDialogs(getContext()).dialogResult("Equipo Actualizado!").setConfirmClickListener(sweetAlertDialog -> pDialog.dismissWithAnimation());


            Log.e(TAG, "actulizarInformaicon: Filas Fectadas: " + rs);

            cst.close();
        } catch (Exception e) {
            Log.e(TAG, "insetar: Error: " + e.getMessage());
        }
    }


    private ArrayAdapter<String> consultarPaices() {
        try {
            String storedProcudureCall = "{call ObtnerPaices};";
            CallableStatement cst = DatabaseConnection.getInstance(getContext()).getConnection().prepareCall(storedProcudureCall);
            ResultSet rs = cst.executeQuery();

            ArrayList<String> pojos = new ArrayList<>();
            while (rs.next()) {
                String n_pais = "";
                n_pais = rs.getString("nombre_pais");
                pojos.add(n_pais);
            }

            rs.close();
            cst.close();

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, pojos) {
                @NonNull
                @Override
                public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                    View v = super.getView(position, convertView, parent);
                    if (position == getCount()) {
                        ((TextView) v.findViewById(android.R.id.text1)).setText("");
                        ((TextView) v.findViewById(android.R.id.text1)).setHint(getItem(getCount()));
                    }
                    return v;
                }

                @Override
                public int getCount() {
                    return super.getCount() - 1;
                }
            };
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            return dataAdapter;


        } catch (Exception e) {
            Log.e(TAG, "insetar: Error: " + e.getMessage());
        }
        return null;
    }

}
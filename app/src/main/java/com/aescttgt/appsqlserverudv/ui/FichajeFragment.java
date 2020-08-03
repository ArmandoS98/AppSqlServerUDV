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
import android.widget.TextView;

import com.aescttgt.appsqlserverudv.Connection.DatabaseConnection;
import com.aescttgt.appsqlserverudv.Pojos.Equipo;
import com.aescttgt.appsqlserverudv.R;
import com.aescttgt.appsqlserverudv.Utils.CustomDialogs;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class FichajeFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "FichajeFragment";
    private TextInputEditText mTextInputEditTextNombre;
    private TextInputEditText mTextInputEditTextNumeroCamisola;
    private TextInputEditText mteTextInputEditTextFechaIngreso;
    private Spinner mSpinnerEquipos;
    private Button mButtonGuardar;
    private SweetAlertDialog pDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_eliminar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSpinnerEquipos = view.findViewById(R.id.tilEquipo);
        mTextInputEditTextNombre = view.findViewById(R.id.txtNombreJugador);
        mTextInputEditTextNumeroCamisola = view.findViewById(R.id.textCamisla);
        mteTextInputEditTextFechaIngreso = view.findViewById(R.id.text_fecha_ingreso);


        mButtonGuardar = view.findViewById(R.id.btn_guardar);
        mSpinnerEquipos.setAdapter(consultaEquipos());
        mSpinnerEquipos.setSelection(0);

        mButtonGuardar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_guardar) {
            verificarSiJugadorExiste();
        }
    }

    private void verificarSiJugadorExiste() {
        try {
            String storedProcudureCall = "{call BuscarJugadorPorNombre(?)};";
            CallableStatement cst = DatabaseConnection.getInstance(getContext()).getConnection().prepareCall(storedProcudureCall);
            cst.setString(1, mTextInputEditTextNombre.getText().toString());
            ResultSet rs = cst.executeQuery();

            int id_jugador = -1;
            while (rs.next())
                id_jugador = rs.getInt("id_jugador");

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
            int pos = (int) (mSpinnerEquipos.getSelectedItemId() + 1);
            String storedProcudureCall = "{call InsertarDataFichaje(?,?,?,?,?)};";
            CallableStatement cst = DatabaseConnection.getInstance(getContext()).getConnection().prepareCall(storedProcudureCall);
            cst.setString(1, "2020/07/10");
            cst.setString(2, "2025/01/01");
            cst.setInt(3, id_jugador);
            cst.setInt(4, pos);
            cst.setInt(5, Integer.parseInt(mTextInputEditTextNumeroCamisola.getText().toString()));
            int rs = cst.executeUpdate();

            if (rs > 0)
                pDialog = new CustomDialogs(getContext()).dialogResult("Fichaje Agregado Con Exito!").setConfirmClickListener(sweetAlertDialog -> pDialog.dismissWithAnimation());


            Log.e(TAG, "actulizarInformaicon: Filas Fectadas: " + rs);

            cst.close();
        } catch (Exception e) {
            Log.e(TAG, "insetar: Error: " + e.getMessage());
        }
    }


    private ArrayAdapter<String> consultaEquipos() {
        try {
            String storedProcudureCall = "{call ObtenerInformacionEquipo};";
            CallableStatement cst = DatabaseConnection.getInstance(getContext()).getConnection().prepareCall(storedProcudureCall);
            ResultSet rs = cst.executeQuery();

            ArrayList<String> pojos = new ArrayList<>();
            while (rs.next()) {
                String n_pais = "";
                n_pais = rs.getString("nombre_equipo");
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
                    return super.getCount();
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
package com.aescttgt.appsqlserverudv.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aescttgt.appsqlserverudv.Connection.DatabaseConnection;
import com.aescttgt.appsqlserverudv.R;
import com.aescttgt.appsqlserverudv.Utils.CustomDialogs;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ListadoFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "ListadoFragment";
    private SweetAlertDialog pDialog;
    private TextInputEditText mTextInputEditTextNombreEquipo;
    private Spinner mSpinnerEntrenadores;
    private Button mButtonGuardar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTextInputEditTextNombreEquipo = view.findViewById(R.id.txtNombreJugador);
        mSpinnerEntrenadores = view.findViewById(R.id.tilNacionalidad);
        mButtonGuardar = view.findViewById(R.id.btn_guardar);
        mSpinnerEntrenadores.setAdapter(ObtenerListadoEntrenadores());
        mSpinnerEntrenadores.setSelection(0);
        mButtonGuardar.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_guardar)
            actulizarInformaicon();
    }

    private ArrayAdapter<String> ObtenerListadoEntrenadores() {
        try {
            String storedProcudureCall = "{call ObtenerListadoEntrenadores};";
            CallableStatement cst = DatabaseConnection.getInstance(getContext()).getConnection().prepareCall(storedProcudureCall);
            ResultSet rs = cst.executeQuery();

            ArrayList<String> pojos = new ArrayList<>();
            while (rs.next()) {
                String n_pais = "";
                n_pais = rs.getString("nom_entrenador");
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

    private void actulizarInformaicon() {
        try {
            int pos = (int) (mSpinnerEntrenadores.getSelectedItemId() + 1);
            String storedProcudureCall = "{call InsertarEquipo(?,?)};";
            CallableStatement cst = DatabaseConnection.getInstance(getContext()).getConnection().prepareCall(storedProcudureCall);
            cst.setString(1, mTextInputEditTextNombreEquipo.getText().toString());
            cst.setInt(2, pos);
            int rs = cst.executeUpdate();

            if (rs > 0)
                pDialog = new CustomDialogs(getContext()).dialogResult("Equipo Ingresado Corerctamente!").setConfirmClickListener(sweetAlertDialog -> pDialog.dismissWithAnimation());


            Log.e(TAG, "actulizarInformaicon: Filas Fectadas: " + rs);

            cst.close();
        } catch (Exception e) {
            Log.e(TAG, "insetar: Error: " + e.getMessage());
        }
    }


}
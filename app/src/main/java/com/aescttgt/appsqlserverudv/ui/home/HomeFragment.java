package com.aescttgt.appsqlserverudv.ui.home;

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

import com.aescttgt.appsqlserverudv.R;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "HomeFragment";
    private TextInputEditText mTextInputEditTextCampo1;
    private TextInputEditText mTextInputEditTextCampo2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.button2).setOnClickListener(this);
        mTextInputEditTextCampo1 = view.findViewById(R.id.campo1);
        mTextInputEditTextCampo2 = view.findViewById(R.id.campo2);
    }


    @Override
    public void onClick(View v) {
        insetar();
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

    private void insetar() {
        try {
            PreparedStatement pst = conexionBD().prepareStatement("INSERT INTO android VALUES (?,?,?)");
            pst.setInt(1, 1);
            pst.setString(2, mTextInputEditTextCampo1.getText().toString());
            pst.setString(3,  mTextInputEditTextCampo2.getText().toString());
            pst.executeUpdate();

            Toast.makeText(getContext(), "AGREGADO COCN EXITO", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "insetar: Error: " + e.getMessage());
        }

    }
}
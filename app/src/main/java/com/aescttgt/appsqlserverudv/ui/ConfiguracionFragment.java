package com.aescttgt.appsqlserverudv.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aescttgt.appsqlserverudv.R;
import com.aescttgt.appsqlserverudv.SharedPrefencies.SharedPrefManager;
import com.aescttgt.appsqlserverudv.Utils.CustomDialogs;
import com.google.android.material.textfield.TextInputEditText;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ConfiguracionFragment extends Fragment implements View.OnClickListener {

    //Vista
    private TextInputEditText mTextInputEditTextIp;
    private TextInputEditText mTextInputEditTextUserName;
    private TextInputEditText mTextInputEditTextPassword;
    private TextInputEditText mTextInputEditTextDatabaseName;

    private SweetAlertDialog mSweetAlertDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_configuracion, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTextInputEditTextIp = view.findViewById(R.id.campo4);
        mTextInputEditTextPassword = view.findViewById(R.id.campo3);
        mTextInputEditTextUserName = view.findViewById(R.id.campo2);
        mTextInputEditTextDatabaseName = view.findViewById(R.id.campo1);

        view.findViewById(R.id.button2).setOnClickListener(this);
        mostarConfiguraciones();
    }

    private void mostarConfiguraciones() {
        mTextInputEditTextIp.setText(SharedPrefManager.getPrefVal(getContext(), getContext().getString(R.string.ip)));
        mTextInputEditTextDatabaseName.setText(SharedPrefManager.getPrefVal(getContext(), getContext().getString(R.string.database_name)));
        mTextInputEditTextUserName.setText(SharedPrefManager.getPrefVal(getContext(), getContext().getString(R.string.user_name)));
        mTextInputEditTextPassword.setText(SharedPrefManager.getPrefVal(getContext(), getContext().getString(R.string.password)));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button2) {
            if (!mTextInputEditTextIp.getText().toString().isEmpty() ||
                    !mTextInputEditTextDatabaseName.getText().toString().isEmpty() ||
                    !mTextInputEditTextUserName.getText().toString().isEmpty() ||
                    !mTextInputEditTextPassword.getText().toString().isEmpty()) {
                SharedPrefManager.setStringPrefVal(getContext(), getString(R.string.ip), mTextInputEditTextIp.getText().toString());
                SharedPrefManager.setStringPrefVal(getContext(), getString(R.string.database_name), mTextInputEditTextDatabaseName.getText().toString());
                SharedPrefManager.setStringPrefVal(getContext(), getString(R.string.user_name), mTextInputEditTextUserName.getText().toString());
                SharedPrefManager.setStringPrefVal(getContext(), getString(R.string.password), mTextInputEditTextPassword.getText().toString());

                mSweetAlertDialog = new CustomDialogs(getContext()).dialogResult("Informacion Guardad").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mSweetAlertDialog.dismissWithAnimation();
                    }
                });
            } else {
                mSweetAlertDialog = new CustomDialogs(getContext()).dialogError("Todos los campos son obligatorios").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mSweetAlertDialog.dismissWithAnimation();
                    }
                });
            }
        }
    }
}
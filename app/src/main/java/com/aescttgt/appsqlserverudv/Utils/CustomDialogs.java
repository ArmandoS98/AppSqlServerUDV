package com.aescttgt.appsqlserverudv.Utils;

import android.content.Context;
import android.graphics.Color;
import android.widget.Button;


import androidx.core.content.ContextCompat;

import com.aescttgt.appsqlserverudv.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class CustomDialogs {
    private Context mContext;
    private SweetAlertDialog pDialog;

    public CustomDialogs(Context mContext) {
        this.mContext = mContext;
    }

    public SweetAlertDialog dialogError(String msg) {
        pDialog = new SweetAlertDialog(mContext, SweetAlertDialog.ERROR_TYPE);
        pDialog.setTitleText(mContext.getString(R.string.error_sms));
        pDialog.setContentText(msg);
        pDialog.setContentTextSize(19);
        pDialog.setCancelable(false);
        pDialog.setConfirmText(mContext.getString(R.string.confirm_text));
        pDialog.show();

        Button btnConfirmTextColor = pDialog.findViewById(R.id.confirm_button);
        btnConfirmTextColor.setTextColor(ContextCompat.getColor(mContext, R.color.colorText));
        btnConfirmTextColor.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorAccent));

        return pDialog;
    }

    public SweetAlertDialog dialogResult(String msg) {
        pDialog = new SweetAlertDialog(mContext, SweetAlertDialog.SUCCESS_TYPE);
        pDialog.setTitleText("Exitoso!");
        pDialog.setContentText(msg);
        pDialog.setContentTextSize(19);
        pDialog.setCancelable(false);
        pDialog.setConfirmText(mContext.getString(R.string.confirm_text));
        pDialog.show();

        Button btnConfirmTextColor = pDialog.findViewById(R.id.confirm_button);
        btnConfirmTextColor.setTextColor(ContextCompat.getColor(mContext, R.color.colorTextBlack));

        return pDialog;
    }

    public SweetAlertDialog dialogLoading(String msg) {
        pDialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#1a237e"));
        pDialog.setTitleText(msg);
        pDialog.setContentTextSize(19);
        pDialog.setCancelable(false);
        pDialog.show();
        return pDialog;
    }

}

package com.aescttgt.appsqlserverudv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ConsultaAdapter extends RecyclerView.Adapter<ConsultaAdapter.ViewHolder> {
    private ArrayList<Pojosql> valores;
    private Context context;

    public ConsultaAdapter(Context context, ArrayList<Pojosql> valores) {
        this.context = context;
        this.valores = valores;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.mTextViewEncabezado.setText(valores.get(i).getCampo1());
        viewHolder.mTextViewValor.setText(valores.get(i).getCampo2());
    }

    @Override
    public int getItemCount() {
        return valores.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewEncabezado;
        private TextView mTextViewValor;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextViewEncabezado = itemView.findViewById(R.id.tv_campo_1);
            mTextViewValor = itemView.findViewById(R.id.tv_campo_2);
        }
    }
}

package com.aescttgt.appsqlserverudv.Adaptadores;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aescttgt.appsqlserverudv.Pojos.Jugador;
import com.aescttgt.appsqlserverudv.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class JugadorAdapter extends RecyclerView.Adapter<JugadorAdapter.ViewHolder> {
    private static final String TAG = "JugadorAdapter";
    private ArrayList<Jugador> valores;
    private Context context;

    public JugadorAdapter(Context context, ArrayList<Jugador> valores) {
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

        viewHolder.mTextViewEncabezado.setText(valores.get(i).getN_JUGADOR());

        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = dateFormat.format(valores.get(i).getD_NACIMIENTO());
            viewHolder.mTextViewValor.setText(strDate);
        } catch (Exception ex) {
            Log.e(TAG, "onBindViewHolder: Error date " + ex.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return valores.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextViewEncabezado;
        private TextView mTextViewValor;
        private CardView mCardView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCardView = itemView.findViewById(R.id.cartita);
            mTextViewEncabezado = itemView.findViewById(R.id.tv_campo_1);
            mTextViewValor = itemView.findViewById(R.id.tv_campo_2);

            mCardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, valores.get(getAdapterPosition()).getN_JUGADOR(), Toast.LENGTH_SHORT).show();
        }
    }
}

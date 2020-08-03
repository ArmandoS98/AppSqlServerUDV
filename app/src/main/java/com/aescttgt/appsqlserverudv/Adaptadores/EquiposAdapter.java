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

import com.aescttgt.appsqlserverudv.Interfaz.IPartidos;
import com.aescttgt.appsqlserverudv.Pojos.Equipo;
import com.aescttgt.appsqlserverudv.Pojos.Jugador;
import com.aescttgt.appsqlserverudv.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class EquiposAdapter extends RecyclerView.Adapter<EquiposAdapter.ViewHolder> {
    private static final String TAG = "EquiposAdapter";
    private ArrayList<Equipo> valores;
    private Context context;
    private IPartidos mIPartidos;
    private int mSelectedPartidoIndex;

    public EquiposAdapter(Context context, ArrayList<Equipo> valores) {
        this.context = context;
        this.valores = valores;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_equipo, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.mTextViewEncabezado.setText(valores.get(i).getNombre_equipo());
    }

    @Override
    public int getItemCount() {
        return valores.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mIPartidos = (IPartidos) context;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextViewEncabezado;
        private TextView mTextViewValor;
        private CardView mCardView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCardView = itemView.findViewById(R.id.cartita);
            mTextViewEncabezado = itemView.findViewById(R.id.tv_nombre_equipo);

            mCardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mSelectedPartidoIndex = getAdapterPosition();
            mIPartidos.onEquiupo(valores.get(mSelectedPartidoIndex));
        }
    }
}

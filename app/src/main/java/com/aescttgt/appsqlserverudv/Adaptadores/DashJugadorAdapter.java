package com.aescttgt.appsqlserverudv.Adaptadores;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aescttgt.appsqlserverudv.Interfaz.IPartidos;
import com.aescttgt.appsqlserverudv.Pojos.DashPartido;
import com.aescttgt.appsqlserverudv.Pojos.JugadorDash;
import com.aescttgt.appsqlserverudv.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DashJugadorAdapter extends RecyclerView.Adapter<DashJugadorAdapter.ViewHolder> {
    private static final String TAG = "DashJugadorAdapter";
    private ArrayList<JugadorDash> valores;
    private Context context;
    private IPartidos mIPartidos;
    private int mSelectedPartidoIndex;


    public DashJugadorAdapter(Context context, ArrayList<JugadorDash> valores) {
        this.context = context;
        this.valores = valores;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_jugador, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.mteTextViewNombreJugador.setText(valores.get(i).getNombre_jugador());
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
        private TextView mteTextViewNombreJugador;
        private CardView mCardView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCardView = itemView.findViewById(R.id.cartita);

            mteTextViewNombreJugador = itemView.findViewById(R.id.tv_nombre_jugador);
            mCardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mSelectedPartidoIndex = getAdapterPosition();
            mIPartidos.onDashJugador(valores.get(mSelectedPartidoIndex));
        }
    }
}

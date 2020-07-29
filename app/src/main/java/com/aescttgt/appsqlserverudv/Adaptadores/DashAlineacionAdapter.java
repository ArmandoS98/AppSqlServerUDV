package com.aescttgt.appsqlserverudv.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aescttgt.appsqlserverudv.Interfaz.IPartidos;
import com.aescttgt.appsqlserverudv.Pojos.Aliniacion;
import com.aescttgt.appsqlserverudv.Pojos.JugadorDash;
import com.aescttgt.appsqlserverudv.R;

import java.util.ArrayList;

public class DashAlineacionAdapter extends RecyclerView.Adapter<DashAlineacionAdapter.ViewHolder> {
    private static final String TAG = "DashJugadorAdapter";
    private ArrayList<Aliniacion> valores;
    private Context context;
    private IPartidos mIPartidos;
    private int mSelectedPartidoIndex;


    public DashAlineacionAdapter(Context context, ArrayList<Aliniacion> valores) {
        this.context = context;
        this.valores = valores;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_alineacion, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.mteTextViewNombreJugador.setText(valores.get(i).getNombre_jugador());
        viewHolder.mteTextViewPosJugador.setText(valores.get(i).getDescripcion());
        viewHolder.mteTextViewNumeroCamisolaJugador.setText(valores.get(i).getNum_camisola());
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
        private TextView mteTextViewPosJugador;
        private TextView mteTextViewNumeroCamisolaJugador;
        private CardView mCardView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCardView = itemView.findViewById(R.id.cartita);

            mteTextViewNombreJugador = itemView.findViewById(R.id.tv_nombre_jugador);
            mteTextViewPosJugador = itemView.findViewById(R.id.tv_nombre_campeonato);
            mteTextViewNumeroCamisolaJugador = itemView.findViewById(R.id.numcamisola);

            mCardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mSelectedPartidoIndex = getAdapterPosition();
//            mIPartidos.onDashJugador(valores.get(mSelectedPartidoIndex));
        }
    }
}
